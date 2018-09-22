pragma solidity ^0.4.24;

import "./Loan.sol";
import "./SafeMath.sol";

contract Deposit {

	using SafeMath for uint;

	address public collateralHolder;
	address public taker;

	constructor (address _collateralHolder, address _taker) payable public {
		collateralHolder = _collateralHolder;
		taker = _taker;
	}

	function withdraw(address loanAddress) public {
		Loan loan = Loan(loanAddress);
		require(loan.taker() == taker);
		require(address(loan).balance < loan.repayment());
		require(now.sub(loan.latestPaymentTimestamp()) > loan.paymentPeriod());
		require(loan.paymentCount() < loan.duration());

		uint leftToPay = (loan.repayment()).mul(loan.duration().sub(loan.paymentCount()));
		(loan.giver()).transfer(leftToPay);
	}

	function cancel() public {
		require(msg.sender == collateralHolder);
		selfdestruct(msg.sender);
	}

	function getDepositAddress() constant public returns(address) {
		return address(this);
	}
}