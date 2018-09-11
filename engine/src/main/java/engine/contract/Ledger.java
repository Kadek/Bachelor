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
    private static final String BINARY = "608060405234801561001057600080fd5b5061074c806100206000396000f30060806040526004361061008e576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806316f70413146100935780631ec0987f1461010057806361cc855814610143578063711a0b4e146101705780638bebfca9146101dd578063a93efa3c1461020a578063c4e700f714610235578063cb7c85fc14610260575b600080fd5b34801561009f57600080fd5b506100be600480360381019080803590602001909291905050506102a3565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561010c57600080fd5b50610141600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506102e5565b005b34801561014f57600080fd5b5061016e6004803603810190808035906020019092919050505061034e565b005b34801561017c57600080fd5b5061019b600480360381019080803590602001909291905050506104af565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156101e957600080fd5b50610208600480360381019080803590602001909291905050506104f2565b005b34801561021657600080fd5b5061021f61064d565b6040518082815260200191505060405180910390f35b34801561024157600080fd5b5061024a610659565b6040518082815260200191505060405180910390f35b34801561026c57600080fd5b506102a1600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610666565b005b600080828154811015156102b357fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b60008190806001815401808255809150509060018203906000526020600020016000909192909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b6000805490508110151561036157600080fd5b6001600080549050111561046757600060016000805490500381548110151561038657fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff166000828154811015156103c057fe5b9060005260206000200160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600060016000805490500381548110151561041f57fe5b9060005260206000200160006101000a81549073ffffffffffffffffffffffffffffffffffffffff0219169055600080548091906001900361046191906106cf565b506104ac565b600060016000805490500381548110151561047e57fe5b9060005260206000200160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690555b50565b60006001828154811015156104c057fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b6001805490508110151561050557600080fd5b600160008054905011156106075760018080805490500381548110151561052857fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1660018281548110151561056257fe5b9060005260206000200160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506001808080549050038154811015156105bf57fe5b9060005260206000200160006101000a81549073ffffffffffffffffffffffffffffffffffffffff0219169055600180548091906001900361060191906106cf565b5061064a565b60018080805490500381548110151561061c57fe5b9060005260206000200160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690555b50565b60008080549050905090565b6000600180549050905090565b60018190806001815401808255809150509060018203906000526020600020016000909192909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b8154818355818111156106f6578183600052602060002091820191016106f591906106fb565b5b505050565b61071d91905b80821115610719576000816000905550600101610701565b5090565b905600a165627a7a7230582059030d0323120255caf355f6ce87c7fc54bb538862036e678df7a96c71bfc2c20029";

    public static final String FUNC_GETASKADDRESSATROW = "getAskAddressAtRow";

    public static final String FUNC_ADDASK = "addAsk";

    public static final String FUNC_DELETEASK = "deleteAsk";

    public static final String FUNC_GETBIDADDRESSATROW = "getBidAddressAtRow";

    public static final String FUNC_DELETEBID = "deleteBid";

    public static final String FUNC_GETASKADDRESSCOUNT = "getAskAddressCount";

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
