pragma solidity ^0.4.24;

import "./Loan.sol";
import "./SafeMath.sol";

contract Legal {

	using SafeMath for uint;

	string public information;

	address public taker;
	address public disputedLoanAddress;
	bool public isUnblocked;

	constructor (
		address _taker,
		string _information
	) payable public {
		taker = _taker;
		information = _information;
	}

	function withdraw(address loanAddress) public {
		require(disputedLoanAddress == address(0));
		Loan loan = Loan(loanAddress);
		require(loan.taker() == taker);
		require(address(loan).balance < loan.repayment());
		require(now.sub(loan.latestPaymentTimestamp()) > loan.paymentPeriod());
		require(loan.paymentCount() < loan.duration());

		disputedLoanAddress = loanAddress;
		isUnblocked = true;
	}

	function getLegalAddress() constant public returns(address) {
		return address(this);
	}
}