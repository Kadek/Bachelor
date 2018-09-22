pragma solidity ^0.4.24;
import './SafeMath.sol';
import './Loan.sol';
import './Ledger.sol';

contract Preloan {

	using SafeMath for uint;

	enum LoanState {
		OFFER,
		CANCELLED
	}

	enum Collateral {
		UNDEFINED,
		NONE,	
		LEGAL,
		DEPOSIT
	}

	enum Side {
		UNDEFINED,
		ASK,
		BID
	}

	address public giver;
	address public ledgerAddress;
	uint public basis;
	uint public interestScaled;
	uint public interestReciprocal;
	uint public scale;
	uint public precision;
	uint public duration;
	uint public paymentPeriod;
	Collateral public collateral;
	LoanState public loanState;
	Side public side;
	uint public timeCreated;

	address public collateralAddress;
	address public loanAddress;

	constructor () public payable {
		giver = msg.sender;
		loanState = LoanState.OFFER;	
	}

	function () external payable {
		require(basis == 0);
		require(side == Side.BID);
		basis = msg.value;
	}

	function setBasis(uint _basis) public {
		require(basis == 0);
		require(side == Side.ASK);
		basis = _basis;
	}

	function setInterestScaled(uint _interestScaled) public {
		require(interestScaled == 0);
		interestScaled = _interestScaled;
	}

	function setInterestReciprocal(uint _interestReciprocal) public {
		require(interestReciprocal == 0);
		interestReciprocal = _interestReciprocal;
	}

	function setScale(uint _scale) public {
		require(scale == 0);
		scale = _scale;
	}

	function setPrecision(uint _precision) public {
		require(precision == 0);
		precision = _precision;
	}

	function setDuration(uint _duration) public {
		require(duration == 0);
		duration = _duration;
	}

	function setPaymentPeriod(uint _paymentPeriod) public {
		require(paymentPeriod == 0);
		paymentPeriod = _paymentPeriod;
	}

	function setCollateral(Collateral _collateral, address _collateralAddress) public {
		require(collateral == Collateral.UNDEFINED);
		collateral = _collateral;
		collateralAddress = _collateralAddress;
	}

	function setSide(Side _side) public {
		require(side == Side.UNDEFINED);
		side = _side;
	}

	function cancelLoanOffer(uint index) public {
		require(msg.sender == giver);

		require(loanState == LoanState.OFFER);
		loanState = LoanState.CANCELLED;

		Ledger ledger = Ledger(ledgerAddress);
		if(side == Side.ASK){
			ledger.deleteAsk(index);
		}else{
			ledger.deleteBid(index);
		}	

		selfdestruct(giver);
	}

	function informLedger(address incomingLedgerAddress) public {
		require(ledgerAddress == 0);
		require((side == Side.ASK) || (side == Side.BID));

		Ledger ledger = Ledger(incomingLedgerAddress);
		if(side == Side.ASK){
			ledger.addAsk(this);
		}else{
			ledger.addBid(this);
		}

		ledgerAddress = incomingLedgerAddress;
		timeCreated = now;
	}

	function getAddress() constant public returns (address){
		return this;
	}

	function createLoan(address askAddress) public{
		require(side == Side.BID);
		Preloan ask = Preloan(askAddress);
		require(ask.interestScaled() >= interestScaled);

		Loan loan = new Loan();
		if(ask.basis() >= basis){			
			address(loan).transfer(basis);
			ask.adjustBasis(address(this), basis);
			basis = 0;
		}else{
			address(loan).transfer(ask.basis());
			basis -= ask.basis();
			ask.adjustBasis(address(this), ask.basis());
		}
		loan.setLoan(askAddress, address(this));
		loan.startLoan();
		loanAddress = address(loan);
	}

	function adjustBasis(address bidAddress, uint adjustment) public {
		require(side == Side.ASK);
		Preloan bid = Preloan(bidAddress);
		require(interestScaled >= bid.interestScaled());

		basis = basis.sub(adjustment);
	}
}