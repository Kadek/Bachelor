var Ledger = artifacts.require("Ledger");

contract('Ledger', function(accounts){
  var refAddresses = [
    "0x32be343b94f860124dc4fee278fdcbd38c102d88",
    "0x32be343b94f860124dc4fee278fdcbd38c102d89",
    "0x32be343b94f860124dc4fee278fdcbd38c102d87"
  ];

  it("should not allow to delete elements from empty Ask array", function() {
    return Ledger.deployed().then(function(instance){
      ledger = instance;
      test = false;
      return ledger.deleteAsk(0);
    }).catch(function(error) {
      test = true;
    }).then(function(value){
      if(test){
        assert.equal(1,1,"");
      }else{
        assert.equal(1,0, "Contract allowed to delete from empty ask array");
      }
    });
  });

  it("should allow to add and store ask addresses", function() {
    return Ledger.deployed().then(function(instance){
      ledger = instance;
      return ledger.addAsk(refAddresses[0]);
    }).then(function() {
      return ledger.addAsk(refAddresses[1]);
    }).then(function() {
      return ledger.addAsk(refAddresses[2]);
    }).then(function() {
      return ledger.getAskAddresses.call();
    }).then(function(addresses) {
      for(var i = 0 ; i < addresses.length; i++){
        assert.equal(refAddresses[i], addresses[i], "Address " + addresses[i] + " instead of " + refAddresses[i]);
      }
    });
  });

  it("should allow to remove ask addresses", function() {
    return Ledger.deployed().then(function(instance){
      ledger = instance;
      return ledger.deleteAsk(0);
    }).then(function() {
      return ledger.getAskAddresses.call();
    }).then(function(addresses) {
      assert.equal(addresses.length, 2, "Addresses length after delete is " + addresses.length + " instead of 2");
    });
  });

  it("should not allow to delete elements from empty Bid array", function() {
    return Ledger.deployed().then(function(instance){
      ledger = instance;
      test = false;
      return ledger.deleteBid(0);
    }).catch(function(error) {
      test = true;
    }).then(function(value){
      if(test){
        assert.equal(1,1,"");
      }else{
        assert.equal(1,0, "Contract allowed to delete from empty bid array");
      }
    });
  });

  it("should allow to add and store bid addresses", function() {
    return Ledger.deployed().then(function(instance){
      ledger = instance;
      return ledger.addBid(refAddresses[0]);
    }).then(function() {
      return ledger.addBid(refAddresses[1]);
    }).then(function() {
      return ledger.addBid(refAddresses[2]);
    }).then(function() {
      return ledger.getBidAddresses.call();
    }).then(function(addresses) {
      for(var i = 0 ; i < addresses.length; i++){
        assert.equal(refAddresses[i], addresses[i], "Address " + addresses[i] + " instead of " + refAddresses[i]);
      }
    });
  });

  it("should allow to remove bid addresses", function() {
    return Ledger.deployed().then(function(instance){
      ledger = instance;
      return ledger.deleteBid(0);
    }).then(function() {
      return ledger.getBidAddresses.call();
    }).then(function(addresses) {
      assert.equal(addresses.length, 2, "Addresses length after delete is " + addresses.length + " instead of 2");
    });
  });
});