pragma solidity ^0.4.24;

contract Ledger {

	address[] public askAddresses;
	address[] public bidAddresses;

	function addAsk(address askAddress) public {
		askAddresses.push(askAddress);
	}

	function deleteAsk(uint index) public {
		require(index < askAddresses.length);
		if(askAddresses.length > 1){
			askAddresses[index] = askAddresses[askAddresses.length-1];
			delete askAddresses[askAddresses.length-1];
			askAddresses.length--;
		}else{
			delete askAddresses[askAddresses.length-1];
		}
	}

	function getAskAddressAtRow(uint index) constant public returns (address){
		return askAddresses[index];
	}

	function getAskAddressCount() constant public returns (uint){
		return askAddresses.length;
	}

	function addBid(address bidAddress) public {
		bidAddresses.push(bidAddress);
	}

	function deleteBid(uint index) public {
		require(index < bidAddresses.length);
		if(askAddresses.length > 1){
			bidAddresses[index] = bidAddresses[bidAddresses.length-1];
			delete bidAddresses[bidAddresses.length-1];
			bidAddresses.length--;
		}else{
			delete bidAddresses[bidAddresses.length-1];
		}
	}

	function getBidAddressAtRow(uint index) constant public returns (address){
		return bidAddresses[index];
	}

	function getBidAddressCount() constant public returns (uint){
		return bidAddresses.length;
	}
}
