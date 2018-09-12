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
    private static final String BINARY = "608060405234801561001057600080fd5b506108b8806100206000396000f3006080604052600436106100a4576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806316f70413146100a95780631ec0987f146101165780635b87e7421461015957806361cc8558146101c6578063711a0b4e146101f35780638bebfca914610260578063a93efa3c1461028d578063b2447df6146102b8578063c4e700f714610325578063cb7c85fc14610350575b600080fd5b3480156100b557600080fd5b506100d460048036038101908080359060200190929190505050610393565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561012257600080fd5b50610157600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506103d5565b005b34801561016557600080fd5b506101846004803603810190808035906020019092919050505061043e565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156101d257600080fd5b506101f16004803603810190808035906020019092919050505061047c565b005b3480156101ff57600080fd5b5061021e600480360381019080803590602001909291905050506105dd565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561026c57600080fd5b5061028b60048036038101908080359060200190929190505050610620565b005b34801561029957600080fd5b506102a261077b565b6040518082815260200191505060405180910390f35b3480156102c457600080fd5b506102e360048036038101908080359060200190929190505050610787565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561033157600080fd5b5061033a6107c5565b6040518082815260200191505060405180910390f35b34801561035c57600080fd5b50610391600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506107d2565b005b600080828154811015156103a357fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b60008190806001815401808255809150509060018203906000526020600020016000909192909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b60018181548110151561044d57fe5b906000526020600020016000915054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000805490508110151561048f57600080fd5b600160008054905011156105955760006001600080549050038154811015156104b457fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff166000828154811015156104ee57fe5b9060005260206000200160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600060016000805490500381548110151561054d57fe5b9060005260206000200160006101000a81549073ffffffffffffffffffffffffffffffffffffffff0219169055600080548091906001900361058f919061083b565b506105da565b60006001600080549050038154811015156105ac57fe5b9060005260206000200160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690555b50565b60006001828154811015156105ee57fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b6001805490508110151561063357600080fd5b600160008054905011156107355760018080805490500381548110151561065657fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1660018281548110151561069057fe5b9060005260206000200160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506001808080549050038154811015156106ed57fe5b9060005260206000200160006101000a81549073ffffffffffffffffffffffffffffffffffffffff0219169055600180548091906001900361072f919061083b565b50610778565b60018080805490500381548110151561074a57fe5b9060005260206000200160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690555b50565b60008080549050905090565b60008181548110151561079657fe5b906000526020600020016000915054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000600180549050905090565b60018190806001815401808255809150509060018203906000526020600020016000909192909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b815481835581811115610862578183600052602060002091820191016108619190610867565b5b505050565b61088991905b8082111561088557600081600090555060010161086d565b5090565b905600a165627a7a72305820f8076ec1c7a9562fd35f40cb8a541d4ac38b709770fe288dd90a5470d1aa3b640029";

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
