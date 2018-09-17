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
    private static final String BINARY = "608060405234801561001057600080fd5b506108ac806100206000396000f3006080604052600436106100af576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806316f70413146100b45780631ec0987f146101215780635b87e7421461016457806361bc221a146101d157806361cc8558146101fc578063711a0b4e146102295780638bebfca914610296578063a93efa3c146102c3578063b2447df6146102ee578063c4e700f71461035b578063cb7c85fc14610386575b600080fd5b3480156100c057600080fd5b506100df600480360381019080803590602001909291905050506103c9565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561012d57600080fd5b50610162600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061040b565b005b34801561017057600080fd5b5061018f60048036038101908080359060200190929190505050610486565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156101dd57600080fd5b506101e66104c4565b6040518082815260200191505060405180910390f35b34801561020857600080fd5b50610227600480360381019080803590602001909291905050506104ca565b005b34801561023557600080fd5b50610254600480360381019080803590602001909291905050506105f4565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156102a257600080fd5b506102c160048036038101908080359060200190929190505050610637565b005b3480156102cf57600080fd5b506102d861075d565b6040518082815260200191505060405180910390f35b3480156102fa57600080fd5b5061031960048036038101908080359060200190929190505050610769565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561036757600080fd5b506103706107a7565b6040518082815260200191505060405180910390f35b34801561039257600080fd5b506103c7600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506107b4565b005b600080828154811015156103d957fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b60008190806001815401808255809150509060018203906000526020600020016000909192909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505060026000815480929190600101919050555050565b60018181548110151561049557fe5b906000526020600020016000915054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60025481565b600080549050811015156104dd57600080fd5b6001600080549050111561058557600060016000805490500381548110151561050257fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1660008281548110151561053c57fe5b9060005260206000200160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b600060016000805490500381548110151561059c57fe5b9060005260206000200160006101000a81549073ffffffffffffffffffffffffffffffffffffffff021916905560008054809190600190036105de919061082f565b5060026000815480929190600101919050555050565b600060018281548110151561060557fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b6001805490508110151561064a57600080fd5b600160008054905011156106f05760018080805490500381548110151561066d57fe5b9060005260206000200160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff166001828154811015156106a757fe5b9060005260206000200160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b60018080805490500381548110151561070557fe5b9060005260206000200160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556001805480919060019003610747919061082f565b5060026000815480929190600101919050555050565b60008080549050905090565b60008181548110151561077857fe5b906000526020600020016000915054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000600180549050905090565b60018190806001815401808255809150509060018203906000526020600020016000909192909190916101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505060026000815480929190600101919050555050565b81548183558181111561085657818360005260206000209182019101610855919061085b565b5b505050565b61087d91905b80821115610879576000816000905550600101610861565b5090565b905600a165627a7a723058209047c28d8c2e95b6f9ebc90c762a76203c05df74df1df2d55710bc34965d4f290029";

    public static final String FUNC_GETASKADDRESSATROW = "getAskAddressAtRow";

    public static final String FUNC_ADDASK = "addAsk";

    public static final String FUNC_BIDADDRESSES = "bidAddresses";

    public static final String FUNC_COUNTER = "counter";

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

    public RemoteCall<BigInteger> counter() {
        final Function function = new Function(FUNC_COUNTER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
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
