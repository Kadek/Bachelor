var Ledger = artifacts.require("Ledger");
var Preloan = artifacts.require("Preloan");

contract('Ledger unit test', function(accounts){
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
      return ledger.getAskAddressAtRow(0);
    }).then(function(address) {
      assert.equal(refAddresses[0], address, "Address " + address + " instead of " + refAddresses[0]);
      return ledger.getAskAddressAtRow(1);
    }).then(function(address) {
      assert.equal(refAddresses[1], address, "Address " + address + " instead of " + refAddresses[1]);
      return ledger.getAskAddressAtRow(2);
    }).then(function(address) {
      assert.equal(refAddresses[2], address, "Address " + address + " instead of " + refAddresses[2]);
    });
  });

  it("should allow to remove ask addresses", function() {
    return Ledger.deployed().then(function(instance){
      ledger = instance;
      return ledger.deleteAsk(0);
    }).then(function() {
      return ledger.getAskAddressCount();
    }).then(function(addressesLength) {
      assert.equal(addressesLength, 2, "Addresses length after delete is " + addressesLength + " instead of 2");
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
      return ledger.getBidAddressAtRow(0);
    }).then(function(address) {
      assert.equal(refAddresses[0], address, "Address " + address + " instead of " + refAddresses[0]);
      return ledger.getBidAddressAtRow(1);
    }).then(function(address) {
      assert.equal(refAddresses[1], address, "Address " + address + " instead of " + refAddresses[1]);
      return ledger.getBidAddressAtRow(2);
    }).then(function(address) {
      assert.equal(refAddresses[2], address, "Address " + address + " instead of " + refAddresses[2]);
    });
  });

  it("should allow to remove bid addresses", function() {
    return Ledger.deployed().then(function(instance){
      ledger = instance;
      return ledger.deleteBid(0);
    }).then(function() {
      return ledger.getBidAddressCount();
    }).then(function(addressesLength) {
      assert.equal(addressesLength, 2, "Addresses length after delete is " + addressesLength + " instead of 2");
    });
  });
});

contract('Ledger integration test', function(accounts){
  it("should allow preloan to inform ledger of it's existence and destruction", function() {
    return Ledger.deployed().then(function(instance){
      ledger = instance;
      return Preloan.deployed();
    }).then(function(instance) {
      preloan = instance;
      return preloan.setSide(1);
    }).then(function() {
      return preloan.informLedger(ledger.address);
    }).then(function() {
      return ledger.getAskAddressCount()
    }).then(function(addressesLength) {
      assert.equal(addressesLength, 1, "Addresses length after adding preloan is " + addressesLength + " instead of 1");
      return preloan.ledgerAddress.call();
    }).then(function(address) {
      assert.equal(address, ledger.address, "Preloan has address of ledger equal to " + address + " instead of " + ledger.address);
      return preloan.cancelLoanOffer(0);
    }).then(function() {
      return ledger.getAskAddressCount()
    }).then(function(addressesLength) {
      assert.equal(addressesLength, 1, "Addresses length after cancelling preloan is " + addressesLength + " instead of 0");
    });
  });
});