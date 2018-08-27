var Loan = artifacts.require("./Loan.sol");

module.exports = function(deployer, network, accounts) {   
	// deployment steps   
	deployer.deploy(Loan, {
			from: accounts[0],
			value: 1000
	}); 
}
