pragma solidity ^0.4.24;
import './SafeMath.sol';

contract Loan {

	using SafeMath for uint;

	enum Collateral {
		UNDEFINED,
		NONE,	
		LEGAL,
		DEPOSIT
	}

	address giver;
	address public taker;
	uint public basis;
	uint public interestScaled;
	uint public interestReciprocal;
	uint public scale;
	uint public precision;
	uint public duration;
	uint public paymentPeriod;
	Collateral public collateral;

	uint public repayment;
	uint public latestPaymentTimestamp;
	uint public paymentCount;

	function setLoan(address ask, address bid) public{
		Preloan askContract = Preloan(ask);
		Preloan bidContract = Preloan(bid);

		giver = bidContract.giver();
		taker = askContract.giver();

		basis = address(this).balance;
		interestScaled = bidContract.interestScaled();
		interestReciprocal = askContract.interestReciprocal();
		scale = askContract.scale();
		precision = askContract.precision();
		duration = askContract.duration();
		paymentPeriod = askContract.paymentPeriod();
		collateral = Collateral(uint(askContract.collateral()));

		paymentCount = 0;
	}

	// allows the contract to receive ether
	function() external payable{
	}

	function startLoan() public {
		calculateRepayments();
		scheduleRepayments();

		transferMoney();
	}

	function transferMoney() public payable{
		taker.transfer(basis);
	}

	function calculateRepayments() private {		
		uint compound_interest = exp();
		repayment = (compound_interest.mul(interestScaled).mul(basis)).div(compound_interest.sub(scale)).div(scale);
	}

	function exp() private constant returns (uint){
	  uint s = 0;
	  uint N = 1;
	  uint B = 1;
	  for (uint i = 0; i < precision; ++i){
	    s = s.add(scale.mul(N).div(B).div(interestReciprocal**i));
	    N  = N.mul(duration.sub(i));
	    B  = B.mul(i.add(1));
	  }
	  return s;
	}

	function scheduleRepayments() private {
		latestPaymentTimestamp = now;
	}

	function consumeRepayment() public payable {
		require(msg.sender == giver);
		require(now.sub(latestPaymentTimestamp) > paymentPeriod);
		require(paymentCount < duration);
		giver.transfer(repayment);
		latestPaymentTimestamp = latestPaymentTimestamp.add(paymentPeriod);
		paymentCount++;
	}
}

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

	address public loanAddress;

	constructor () payable {
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

	function setCollateral(Collateral _collateral) public {
		require(collateral == Collateral.UNDEFINED);
		collateral = _collateral;
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
		loan.setLoan(askAddress, address(this));
		if(ask.basis() >= basis){			
			address(loan).transfer(basis);
			ask.adjustBasis(address(this), basis);
			basis = 0;
		}else{
			address(loan).transfer(ask.basis());
			basis -= ask.basis();
			ask.adjustBasis(address(this), ask.basis());
		}
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

contract Ledger {
	function addAsk(address askAddress) public;
    function addBid(address bidAddress) public;
    function deleteAsk(uint index) public;
    function deleteBid(uint index) public;
}