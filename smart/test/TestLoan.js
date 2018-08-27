var Loan = artifacts.require("Loan");

contract('Loan', function(accounts){
  it("should initialize with default basis", function() {
    return Loan.deployed().then(function(instance){
      return instance.basis.call();
    }).then(function(basis) {
      assert.equal(basis.valueOf(), 1000, "Default basis is not 1000");
    });
  });

  it("should initialize with default LoanState", function() {
    return Loan.deployed().then(function(instance){
      return instance.loanState.call();
    }).then(function(loanState) {
      assert.equal(loanState.valueOf(), 0, "Default LoanState is not OFFER");
    });
  });

  function testSetters(functionName, capFunctionName, functionValue){
    it("should set positive " + functionName, function() {
      return Loan.deployed().then(function(instance){
        loan = instance;
        return loan["set" + capFunctionName](functionValue);
      }).then(function(instance) {
        return loan[functionName].call();
      }).then(function(returnValue) {
        assert.equal(returnValue.valueOf(), functionValue, "Positive " + functionName + " is not set");
      });
    });

    it("should not set " + functionName + " more than once", function() {
      return Loan.deployed().then(function(instance){
        loan = instance;
        test = false;
        return loan["set" + capFunctionName](functionValue);
      }).catch(function(error) {
        test = true;
      }).then(function(value){
        if(test){
          assert.equal(1,1,"");
        }else{
          assert.equal(1,0, functionName + " is set twice");
        }
      });
    });
  }

  functionNames = ["interestScaled", "interestReciprocal", "scale", "precision", "duration", "paymentPeriod", "collateral"];
  functionValues = [2000000000, 5, 10000000000, 8, 7, 30, 1];
  for( var i = 0; i < functionNames.length; i++){

    functionName = functionNames[i]; 
    functionValue = functionValues[i];
    capFunctionName = capitalizeFirstLetter(functionName);

    testSetters(functionName, capFunctionName, functionValue);
  };

  it("should start the loan properly", function() {
    return Loan.deployed().then(function(instance){
      loan = instance;
      return loan.startLoan();
    }).then(function() {
      return loan.loanState.call();
    }).then(function(loanState) {
      assert.equal(loanState.valueOf(), 1, "Started loanState is not STARTED");
      return loan.taker.call();
    }).then(function(taker) {
      assert.equal(taker, accounts[0], "Loan taker is not set to the caller of function");
      return loan.repayment.call();
    }).then(function(repayment) {
      assert.equal(repayment.valueOf(), 277, "Repayments are not calculated properly");
      return loan.latest_payment_timestamp.call();
    }).then(function(latest_payment_timestamp) {
      assert.equal(latest_payment_timestamp.valueOf(), web3.eth.getBlock(web3.eth.blockNumber).timestamp, "Latest payment timestamp is not set properly");
    });
  });


  it("should not start the loan second time", function() {
    return Loan.deployed().then(function(instance){
      return instance.loanStart();
    }).catch(function(error) {
        test = true;
    }).then(function(value){
      if(test){
        assert.equal(1,1,"");
      }else{
        assert.equal(1,0, "Loan is started twice");
      }
    });
  });
});

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}