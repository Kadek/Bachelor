pragma solidity ^0.4.7;
import "./approver.sol";

contract Loan {

	enum LoanState {
		OFFER,
		STARTED,
		CANCELLED
	}

	enum Collateral {
		None,	
		Legal,
		Deposit
	}

	address giver;
	address taker;
	uint public basis
	uint public interest;
	uint public duration;
	Collateral public collateral;
	LoanState public loanState;

	uint public repayment;
	uint public repaymentAccount;

	function constructor(
		address _giver, 
		uint _basis, 
		uint _interest_scaled, 
		uint _interest_reciprocal, 
		uint _scale,
		uint _precision,
		uint _duration, 
		Collateral _collateral
	) {
		giver = _giver;
		basis = _basis;
		interest = _interest;
		interest_reciprocal = _interest_reciprocal;
		scale = _scale;
		precision = _precision;
		duration = _duration;
		collateral = _collateral;

		loanState = LoanState.OFFER;
	}

	function startLoan(address _taker){
		require(loanState == LoanState.OFFER);
		taker = _taker;

		transferMoney();
		calculateRepayments();
		scheduleRepayments();

		loanState == LoanState.STARTED;
	}

	function transferMoney() payable{
		taker.transfer(basis);
	}

	function calculateRepayments(){		
		uint compound_interest = exp(scale, interest_reciprocal, duration, precision);
		uint repayment = (compound_interest * interest_scaled * basis)/(compound_interest - scale));
	}

	function exp(uint scale, uint interest_reciprocal, uint duration, uint precision) returns (uint) {
	  uint s = 0;
	  uint N = 1;
	  uint B = 1;
	  for (uint i = 0; i < precision; ++i){
	    s += scale * N / B / (interest_reciprocal**i);
	    N  = N * (duration-i);
	    B  = B * (i+1);
	  }
	  return s;
	}

	function scheduleRepayments(){

	}

	function consumeRepayment() payable {
		giver.transfer(repayment);
		repaymentAccount -= repayment;
	}

	function loadRepaymentAccount() payable{
		repaymentAccount += msg.value;
	}

	function cancelLoanOffer(){
		require(loanState = LoanState.OFFER)
		loanState = LoanState.CANCELLED;
		suicide(giver);
	}
}