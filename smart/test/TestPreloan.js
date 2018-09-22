var Preloan = artifacts.require("Preloan");
var Loan = artifacts.require("Loan");
var BigNumber = require("bignumber.js");

contract('Preloan', function(accounts){
  it("should initialize with default values basis", function() {
    return Preloan.deployed().then(function(instance){
      loan = instance;
      return loan.loanState.call();
    }).then(function(loanState) {
      assert.equal(loanState.valueOf(), 0, "Default LoanState is not OFFER");
    });
  });

  function testSetters(functionName, capFunctionName, functionValue){
    it("should set positive " + functionName, async ()  => {
      loan = await Preloan.deployed();

      if(functionName === "collateral"){
        await loan["set" + capFunctionName](functionValue, "0x0");
      }else{
        await loan["set" + capFunctionName](functionValue);
      }
      var returnValue = await loan[functionName].call();
      assert.equal(returnValue.valueOf(), functionValue, "Positive " + functionName + " is not set");
    });

    it("should not set " + functionName + " more than once", function() {
      return Preloan.deployed().then(function(instance){
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

  var duration = 7;
  var paymentPeriod = 30;
  var functionNames = ["side", "basis", "interestScaled", "interestReciprocal", "scale", "precision", "duration", "paymentPeriod", "collateral"];
  var functionValues = [1, 10000, 2000000000, 5, 10000000000, 8, duration, paymentPeriod, 1];
  for( var i = 0; i < functionNames.length; i++){

    functionName = functionNames[i]; 
    functionValue = functionValues[i];
    capFunctionName = capitalizeFirstLetter(functionName);

    testSetters(functionName, capFunctionName, functionValue);
  };

});

contract("Preloan integration", function(accounts){

  var duration = 7;
  var paymentPeriod = 30;
  var basis = 1000;
  var functionNames = ["interestScaled", "interestReciprocal", "scale", "precision", "duration", "paymentPeriod", "collateral", "side"];
  var getterFunctionNames = ["giver", "taker", "interestScaled", "interestReciprocal", "scale", "precision", "duration", "paymentPeriod", "collateral"];
  var functionValues = [
    [2000000000, 5, 10000000000, 8, duration, paymentPeriod, 1, "2"],
    [2000000000, 5, 10000000000, 8, duration, paymentPeriod, 1, "1"]
  ];

  it("Should create loans", async () => {
    let Bid = await Preloan.new();
    let Ask = await Preloan.new();
    contracts = [Bid, Ask];

    for( var i = 0; i < contracts.length; i++){
      let currentContract = contracts[i];
      for( var j = 0; j < functionNames.length; j++){
        functionName = functionNames[j];
        functionValue = functionValues[i][j];
        capFunctionName = capitalizeFirstLetter(functionName);

        if(functionName === "collateral"){
          await currentContract["set" + capFunctionName](functionValue, "0x0");
        }else{
          await currentContract["set" + capFunctionName](functionValue);
        }
      };
    };

    Ask.setBasis(basis);
    await Bid.sendTransaction({
      from: accounts[0],
      value: basis
    });

    await Bid.createLoan(Ask.address);
    var loanAddress = await Bid.loanAddress();

    var loanBasis = web3.eth.getBalance(loanAddress).valueOf();
    var bidBasis = web3.eth.getBalance(Bid.address).valueOf();
    var askBasis = (await Ask.basis()).valueOf();
    var repayment = (await (await Loan.at(loanAddress)).repayment()).valueOf();
    assert.equal(loanBasis, 0, "Loan basis is wrong!");
    assert.equal(bidBasis, 0, "Bid is not empty");
    assert.equal(askBasis, 0, "Ask is not empty");
    assert.equal(repayment, 277, "repayment calculated inproperly");
  });
});

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}