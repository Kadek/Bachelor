var Loan = artifacts.require("./Loan.sol");

module.exports = function(deployer, network, accounts) {   
	// deployment steps   
	deployer.deploy(Loan, [accounts[1]], accounts[2], {
			from: accounts[0],
			value: 100
	}); 
}
