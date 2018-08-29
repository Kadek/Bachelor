var Loan = artifacts.require("Loan");
var BigNumber = require("bignumber.js");

contract('Loan', function(accounts){
  it("should initialize with default values basis", function() {
    return Loan.deployed().then(function(instance){
      loan = instance;
      return loan.basis.call();
    }).then(function(basis) {
      assert.equal(basis.valueOf(), 1000, "Default basis is not 1000");
      return loan.loanState.call();
    }).then(function(loanState) {
      assert.equal(loanState.valueOf(), 0, "Default LoanState is not OFFER");
      return loan.paymentCount.call();
    }).then(function(paymentCount) {
      assert.equal(paymentCount.valueOf(), 0, "Default paymentCount is not 0");
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

  it("should not transfer money without starting loan", function() {
    return Loan.deployed().then(function(instance){
      return instance.transferMoney();
    }).catch(function(error) {
        test = true;
    }).then(function(value){
      if(test){
        assert.equal(1,1,"");
      }else{
        assert.equal(1,0, "Money is sent without starting loan");
      }
    });
  });

  it("should start the loan properly", function() {
    return Loan.deployed().then(function(instance){
      loan = instance;
      balanceBefore = new BigNumber(web3.eth.getBalance(accounts[0]).valueOf());
      return loan.startLoan();
    }).then(function(transaction) {
      startLoanTransaction = transaction;
      return loan.loanState.call();
    }).then(function(loanState) {
      assert.equal(loanState.valueOf(), 1, "Started loanState is not STARTED");
      return loan.taker.call();
    }).then(function(taker) {
      assert.equal(taker, accounts[0], "Loan taker is not set to the caller of function");
      return loan.repayment.call();
    }).then(function(repayment) {
      assert.equal(repayment.valueOf(), 277, "Repayments are not calculated properly");
      return loan.latestPaymentTimestamp.call();
    }).then(function(latestPaymentTimestamp) {
      assert.equal(latestPaymentTimestamp.valueOf(), web3.eth.getBlock(web3.eth.blockNumber).timestamp, "Latest payment timestamp is not set properly");
      return web3.eth.getTransaction(startLoanTransaction.tx);
    }).then(function(transaction) {
      var balanceAfter = new BigNumber(web3.eth.getBalance(accounts[0]).valueOf());
      var gasPrice = new BigNumber(transaction.gasPrice);
      var gasUsed = new BigNumber(startLoanTransaction.receipt.gasUsed);
      var gasCost = gasPrice.times(gasUsed);
      assert.equal(balanceAfter.plus(gasCost).minus(balanceBefore) , 1000, "Money is not transfered or improper amount is being sent");
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


  it("should not cancel started loan", function() {
    return Loan.deployed().then(function(instance){
      loan = instance;
      return loan.cancelLoanOffer();
    }).catch(function(error) {
      test = true;
    }).then(function(value){
      if(test){
        assert.equal(1,1,"");
      }else{
        assert.equal(1,0, "Started loan gets cancelled");
      }
    });
  });
});


contract('Loan', function(accounts){
  it("should cancel offered loan", function() {
    return Loan.deployed().then(function(instance){
      loan = instance;
      return loan.cancelLoanOffer();
    }).then(function(){
      return loan.taker.call();
    }).catch(function(error) {
      test = true;
    }).then(function(value){
      if(test){
        assert.equal(1,1,"");
      }else{
        assert.equal(1,0, "Cancelled loan did not get destroyed");
      }
    });
  });
});

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}