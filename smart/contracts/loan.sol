pragma solidity ^0.4.24;
import "./Loan.sol";
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
	uint public repaymentAccount;
	uint public latestPaymentTimestamp;
	uint public paymentCount;

	constructor () payable {
		giver = msg.sender;
		basis = msg.value;
		paymentCount = 0;
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

	function startLoan() public {
		
		taker = msg.sender;
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
		repaymentAccount = repaymentAccount.sub(repayment);
		latestPaymentTimestamp = latestPaymentTimestamp.add(paymentPeriod);
		paymentCount++;
	}

	function loadRepaymentAccount() public payable{
		repaymentAccount = repaymentAccount.add(msg.value);
	}
}