pragma solidity ^0.4.24;
import "./Ledger.sol";

contract Ledger {

	address[] askAddresses;
	address[] bidAddresses;

	function addAsk(address askAddress) public {
		askAddresses.push(askAddress);
	}

	function deleteAsk(uint index) public {
		require(index < askAddresses.length);
		askAddresses[index] = askAddresses[askAddresses.length-1];
		delete askAddresses[askAddresses.length-1];
		askAddresses.length--;
	}

	function getAskAddresses() constant public returns (address[]){
		return askAddresses;
	}

	function addBid(address bidAddress) public {
		bidAddresses.push(bidAddress);
	}

	function deleteBid(uint index) public {
		require(index < bidAddresses.length);
		bidAddresses[index] = bidAddresses[bidAddresses.length-1];
		delete bidAddresses[bidAddresses.length-1];
		bidAddresses.length--;
	}

	function getBidAddresses() constant public returns (address[]){
		return bidAddresses;
	}
}