pragma solidity ^0.4.24;
import './SafeMath.sol';
import './Deposit.sol';
import './Legal.sol';
import './Preloan.sol';

contract Loan {

	using SafeMath for uint;

	enum LoanState {
		UNDEFINED,
		SET,
		STARTED,
		BLOCKED
	}

	enum Collateral {
		UNDEFINED,
		NONE,	
		LEGAL,
		DEPOSIT
	}

	address public giver;
	address public taker;
	uint public basis;
	uint public interestScaled;
	uint public interestReciprocal;
	uint public scale;
	uint public precision;
	uint public duration;
	uint public paymentPeriod;
	Collateral public collateral;
	address public collateralAddress;

	uint public repayment;
	uint public latestPaymentTimestamp;
	uint public paymentCount;
	LoanState public loanState;

	function setLoan(address ask, address bid) public{
		require(loanState == LoanState.UNDEFINED);
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
		collateralAddress = askContract.collateralAddress();

		paymentCount = 0;
		loanState = LoanState.SET;
	}

	// allows the contract to receive ether
	function() external payable{
	}

	function startLoan() public {
		require(loanState == LoanState.SET);
		calculateRepayments();
		scheduleRepayments();

		transferMoney();
		loanState = LoanState.STARTED;
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
		require(loanState == LoanState.STARTED);
		require(msg.sender == giver);
		require(now.sub(latestPaymentTimestamp) > paymentPeriod);
		require(paymentCount < duration);

		if(address(this).balance < repayment){
			if(collateral == Collateral.DEPOSIT){
				Deposit deposit = Deposit(collateralAddress);
				deposit.withdraw(this);
			}else if(collateral == Collateral.LEGAL){
				Legal legal = Legal(collateralAddress);
				legal.withdraw(this);
			}
			loanState = LoanState.BLOCKED;

		}else{
			giver.transfer(repayment);
			latestPaymentTimestamp = latestPaymentTimestamp.add(paymentPeriod);
			paymentCount++;
		}

	}
}