pragma solidity ^0.4.24;

contract Preloan {

	enum LoanState {
		OFFER,
		STARTED,
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

	address giver;
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

	constructor () payable {
		giver = msg.sender;
		loanState = LoanState.OFFER;	
	}

	function setBasis(uint _basis) public {
		require(basis == 0);
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
}

contract Ledger {
	function addAsk(address askAddress) public;
    function addBid(address bidAddress) public;
    function deleteAsk(uint index) public;
    function deleteBid(uint index) public;
}