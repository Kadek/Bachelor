pragma solidity ^0.4.24;
import "./Loan.sol";
import '../node_modules/openzeppelin-solidity/contracts/math/SafeMath.sol';


contract Loan {

	using SafeMath for uint;

	enum LoanState {
		OFFER,
		STARTED,
		CANCELLED
	}

	enum Collateral {
		NONE,	
		LEGAL,
		DEPOSIT
	}

	address giver;
	address taker;
	uint public basis;
	uint public interestScaled;
	uint public interestReciprocal;
	uint public scale;
	uint public precision;
	uint public duration;
	Collateral public collateral;
	LoanState public loanState;

	uint public repayment;
	uint public repaymentAccount;
	uint public paymentPeriod;
	uint public latest_payment_timestamp;

	constructor (
		address _giver, 
		uint _basis, 
		uint _interestScaled, 
		uint _interestReciprocal, 
		uint _scale,
		uint _precision,
		uint _duration, 
		Collateral _collateral,
		uint _paymentPeriod
	) {
		giver = _giver;
		basis = _basis;
		interestScaled = _interestScaled;
		interestReciprocal = _interestReciprocal;
		scale = _scale;
		precision = _precision;
		duration = _duration;
		collateral = _collateral;

		paymentPeriod = _paymentPeriod;

		loanState = LoanState.OFFER;
	}

	function startLoan(address _taker) public {
		require(loanState == LoanState.OFFER);
		
		taker = _taker;
		calculateRepayments();
		scheduleRepayments();
		loanState == LoanState.STARTED;

		transferMoney();
	}

	function transferMoney() public payable{
		taker.transfer(basis);
	}

	function calculateRepayments() private {		
		uint compound_interest = exp();
		repayment = (compound_interest.mul(interestScaled).mul(basis)).div(compound_interest.sub(scale));
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
		latest_payment_timestamp = now;
	}

	function consumeRepayment() public payable {
		require(msg.sender == giver);
		require(now.sub(latest_payment_timestamp) > paymentPeriod);
		giver.transfer(repayment);
		repaymentAccount = repaymentAccount.sub(repayment);
		latest_payment_timestamp = latest_payment_timestamp.add(paymentPeriod);
	}

	function loadRepaymentAccount() public payable{
		repaymentAccount = repaymentAccount.add(msg.value);
	}

	function cancelLoanOffer() public {
		require(msg.sender == giver);

		require(loanState == LoanState.OFFER);
		loanState = LoanState.CANCELLED;
		selfdestruct(giver);
	}
}