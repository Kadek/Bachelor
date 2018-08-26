var Loan = artifacts.require("Loan");

contract('Loan', function(accounts){
  it("should initialize with default basis", function() {
    return Loan.deployed().then(function(instance){
      return instance.basis.call();
    }).then(function(basis) {
      assert.equal(basis.valueOf(), 100, "Default basis is not 100");
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
  functionValues = [100, 100, 100, 100, 100, 100, 1];
  for( var i = 0; i < functionNames.length; i++){

    functionName = functionNames[i]; 
    functionValue = functionValues[i];
    capFunctionName = capitalizeFirstLetter(functionName);

    testSetters(functionName, capFunctionName, functionValue);
  };

});

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}