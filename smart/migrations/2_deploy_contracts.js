var Loan = artifacts.require("./Loan.sol");
var Preloan = artifacts.require("./Preloan.sol");

module.exports = function(deployer, network, accounts) {   
	// deployment steps   
	deployer.deploy(Preloan, {
			from: accounts[0],
			value: 1000
	}); 

	deployer.deploy(Loan, {
			from: accounts[0],
			value: 1000
	}); 
}
