package engine;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.5.0.
 */
public class Ledger extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50610828806100206000396000f3006080604052600436106100a4576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806316f70413146100a95780631ec0987f146101165780635b87e7421461015957806361cc8558146101c6578063711a0b4e146101f35780638bebfca914610260578063a93efa3c1461028d578063b2447df6146102b8578063c4e700f714610325578063cb7c85fc14610350575b600080fd5b3480156100b557600080fd5b506100d460048036038101908080359060200190929190505050610393565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561012257600080fd5b50610157600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506103d5565b005b34801561016557600080fd5b506101846004803603810190808035906020019092919050505061043e565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156101d257600080fd5b506101f16004803603810190808035906020019092919050505061047c565b005b3480156101ff57600080fd5b5061021e60048036038101908080359060200190929190505050610594565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561026c57600080fd5b5061028b600480360381019080803590602001909291905050506105d7565b005b34801561029957600080fd5b506102a26106eb565b6040518082815260200191505060405180910390f35b3480156102c457600080fd5b506102e3600480360381019080803590602001909291905050506106f7565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561033157600080fd5b5061033a610735565b6040518082815260200191505060405180910390f35b34801561035c57600080fd5b50610391600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610742565b005b600080828154811015156103a357fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b60008190806001815401808255809150509060018203906000526020600020016000909192909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b60018181548110151561044d57fe5b906000526020600020016000915054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000805490508110151561048f57600080fd5b600160008054905011156105375760006001600080549050038154811015156104b457fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff166000828154811015156104ee57fe5b9060005260206000200160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b600060016000805490500381548110151561054e57fe5b9060005260206000200160006101000a81549073ffffffffffffffffffffffffffffffffffffffff0219169055600080548091906001900361059091906107ab565b5050565b60006001828154811015156105a557fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b600180549050811015156105ea57600080fd5b600160008054905011156106905760018080805490500381548110151561060d57fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1660018281548110151561064757fe5b9060005260206000200160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b6001808080549050038154811015156106a557fe5b9060005260206000200160006101000a81549073ffffffffffffffffffffffffffffffffffffffff021916905560018054809190600190036106e791906107ab565b5050565b60008080549050905090565b60008181548110151561070657fe5b906000526020600020016000915054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000600180549050905090565b60018190806001815401808255809150509060018203906000526020600020016000909192909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b8154818355818111156107d2578183600052602060002091820191016107d191906107d7565b5b505050565b6107f991905b808211156107f55760008160009055506001016107dd565b5090565b905600a165627a7a72305820b1a571997ff755e82884648de158c60d641ce426ef022ee09e3c56a0de0d61920029";

    public static final String FUNC_GETASKADDRESSATROW = "getAskAddressAtRow";

    public static final String FUNC_ADDASK = "addAsk";

    public static final String FUNC_BIDADDRESSES = "bidAddresses";

    public static final String FUNC_DELETEASK = "deleteAsk";

    public static final String FUNC_GETBIDADDRESSATROW = "getBidAddressAtRow";

    public static final String FUNC_DELETEBID = "deleteBid";

    public static final String FUNC_GETASKADDRESSCOUNT = "getAskAddressCount";

    public static final String FUNC_ASKADDRESSES = "askAddresses";

    public static final String FUNC_GETBIDADDRESSCOUNT = "getBidAddressCount";

    public static final String FUNC_ADDBID = "addBid";

    protected Ledger(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Ledger(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<String> getAskAddressAtRow(BigInteger index) {
        final Function function = new Function(FUNC_GETASKADDRESSATROW, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> addAsk(String askAddress) {
        final Function function = new Function(
                FUNC_ADDASK, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(askAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> bidAddresses(BigInteger param0) {
        final Function function = new Function(FUNC_BIDADDRESSES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> deleteAsk(BigInteger index) {
        final Function function = new Function(
                FUNC_DELETEASK, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> getBidAddressAtRow(BigInteger index) {
        final Function function = new Function(FUNC_GETBIDADDRESSATROW, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> deleteBid(BigInteger index) {
        final Function function = new Function(
                FUNC_DELETEBID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> getAskAddressCount() {
        final Function function = new Function(FUNC_GETASKADDRESSCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> askAddresses(BigInteger param0) {
        final Function function = new Function(FUNC_ASKADDRESSES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> getBidAddressCount() {
        final Function function = new Function(FUNC_GETBIDADDRESSCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> addBid(String bidAddress) {
        final Function function = new Function(
                FUNC_ADDBID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(bidAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<Ledger> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Ledger.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Ledger> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Ledger.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static Ledger load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Ledger(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Ledger load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Ledger(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
