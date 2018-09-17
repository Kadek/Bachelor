var Loan = artifacts.require("./Loan.sol");
var Bid = artifacts.require("./Preloan.sol");
var Ask = artifacts.require("./Preloan.sol");
var Ledger = artifacts.require("./Ledger.sol");

module.exports = function(deployer, network, accounts) {   
	// deployment steps   
	deployer.deploy(Bid, {
			from: accounts[0],
			value: 1000
	}); 
	deployer.deploy(Ask, {
			from: accounts[0]
	}); 

	deployer.deploy(Loan, {
			from: accounts[0]
	}); 

	deployer.deploy(Ledger, {
			from: accounts[0]
	}); 
}
