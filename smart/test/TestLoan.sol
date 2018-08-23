pragma solidity ^0.4.24;
import "truffle/Assert.sol";
import "truffle/DeployedAddresses.sol";
import "../contracts/Loan.sol";

contract TestLoan {
  function testInitialLoanNormalValues() {

    address giver = 0x6f46cf5569aefa1acc1009290c8e043747172d89; 
    uint basis = 100; 
    uint interestScaled = 100; 
    uint interestReciprocal = 100; 
    uint scale = 100;
    uint precision = 5;
    uint duration = 10; 
    Loan.Collateral collateral = Loan.Collateral.NONE;
    uint paymentPeriod = 1;

    Loan loan = Loan(DeployedAddresses.Loan(),
      giver,
      basis,
      interestScaled,
      interestReciprocal,
      scale,
      precision,
      duration,
      collateral,
      paymentPeriod
    );

    Assert.equal(loan.basis(), basis, "Loan should have 100 basis initially");
    Assert.equal(loan.interestScaled(), interestScaled, "Loan should have 100 interestScaled initially");
    Assert.equal(loan.interestReciprocal(), interestReciprocal, "Loan should have 100 interestReciprocal initially");
    Assert.equal(loan.scale(), scale, "Loan should have 100 scale initially");
    Assert.equal(loan.precision(), precision, "Loan should have 5 precision initially");
    Assert.equal(loan.duration(), duration, "Loan should have 10 duration initially");
    Assert.equal(loan.collateral(), collateral, "Loan should have NONE collateral initially");
    Assert.equal(loan.paymentPeriod(), paymentPeriod, "Loan should have 1 paymentPeriod initially");
  }
}
