package engine;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
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
public class Deposit extends Contract {
    private static final String BINARY = "6080604052604051604080610b2b8339810180604052810190808051906020019092919080519060200190929190505050816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050610a69806100c26000396000f30060806040526004361061006d576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806312b0d4001461007257806351cff8d9146100c9578063782b36021461010c578063e27facdc14610163578063ea8a1af0146101ba575b600080fd5b34801561007e57600080fd5b506100876101d1565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156100d557600080fd5b5061010a600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506101d9565b005b34801561011857600080fd5b5061012161092d565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561016f57600080fd5b50610178610953565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156101c657600080fd5b506101cf610978565b005b600030905090565b600080829150600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1663782b36026040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561027c57600080fd5b505af1158015610290573d6000803e3d6000fd5b505050506040513d60208110156102a657600080fd5b810190808051906020019092919050505073ffffffffffffffffffffffffffffffffffffffff161415156102d957600080fd5b8173ffffffffffffffffffffffffffffffffffffffff166317cd802d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561033d57600080fd5b505af1158015610351573d6000803e3d6000fd5b505050506040513d602081101561036757600080fd5b81019080805190602001909291905050508273ffffffffffffffffffffffffffffffffffffffff163110151561039c57600080fd5b8173ffffffffffffffffffffffffffffffffffffffff166310b0624a6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561040057600080fd5b505af1158015610414573d6000803e3d6000fd5b505050506040513d602081101561042a57600080fd5b81019080805190602001909291905050506104ec8373ffffffffffffffffffffffffffffffffffffffff1663350baf786040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156104a257600080fd5b505af11580156104b6573d6000803e3d6000fd5b505050506040513d60208110156104cc57600080fd5b8101908080519060200190929190505050426109ec90919063ffffffff16565b1115156104f857600080fd5b8173ffffffffffffffffffffffffffffffffffffffff16630fb5a6b46040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561055c57600080fd5b505af1158015610570573d6000803e3d6000fd5b505050506040513d602081101561058657600080fd5b81019080805190602001909291905050508273ffffffffffffffffffffffffffffffffffffffff16630937e68a6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156105fb57600080fd5b505af115801561060f573d6000803e3d6000fd5b505050506040513d602081101561062557600080fd5b810190808051906020019092919050505010151561064257600080fd5b6108416107948373ffffffffffffffffffffffffffffffffffffffff16630937e68a6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156106ac57600080fd5b505af11580156106c0573d6000803e3d6000fd5b505050506040513d60208110156106d657600080fd5b81019080805190602001909291905050508473ffffffffffffffffffffffffffffffffffffffff16630fb5a6b46040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561074b57600080fd5b505af115801561075f573d6000803e3d6000fd5b505050506040513d602081101561077557600080fd5b81019080805190602001909291905050506109ec90919063ffffffff16565b8373ffffffffffffffffffffffffffffffffffffffff166317cd802d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156107f857600080fd5b505af115801561080c573d6000803e3d6000fd5b505050506040513d602081101561082257600080fd5b8101908080519060200190929190505050610a0590919063ffffffff16565b90508173ffffffffffffffffffffffffffffffffffffffff1663fb775b466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156108a757600080fd5b505af11580156108bb573d6000803e3d6000fd5b505050506040513d60208110156108d157600080fd5b810190808051906020019092919050505073ffffffffffffffffffffffffffffffffffffffff166108fc829081150290604051600060405180830381858888f19350505050158015610927573d6000803e3d6000fd5b50505050565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156109d357600080fd5b3373ffffffffffffffffffffffffffffffffffffffff16ff5b60008282111515156109fa57fe5b818303905092915050565b600080831415610a185760009050610a37565b8183029050818382811515610a2957fe5b04141515610a3357fe5b8090505b929150505600a165627a7a72305820188d460ce179da602a5a72432e21b0c9f35953a5b85fba1fb9a48bb24d2bd3700029";

    public static final String FUNC_GETDEPOSITADDRESS = "getDepositAddress";

    public static final String FUNC_WITHDRAW = "withdraw";

    public static final String FUNC_TAKER = "taker";

    public static final String FUNC_COLLATERALHOLDER = "collateralHolder";

    public static final String FUNC_CANCEL = "cancel";

    protected Deposit(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Deposit(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<String> getDepositAddress() {
        final Function function = new Function(FUNC_GETDEPOSITADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> withdraw(String loanAddress) {
        final Function function = new Function(
                FUNC_WITHDRAW, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(loanAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> taker() {
        final Function function = new Function(FUNC_TAKER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> collateralHolder() {
        final Function function = new Function(FUNC_COLLATERALHOLDER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> cancel() {
        final Function function = new Function(
                FUNC_CANCEL, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<Deposit> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, String _collateralHolder, String _taker) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_collateralHolder), 
                new org.web3j.abi.datatypes.Address(_taker)));
        return deployRemoteCall(Deposit.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static RemoteCall<Deposit> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, String _collateralHolder, String _taker) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_collateralHolder), 
                new org.web3j.abi.datatypes.Address(_taker)));
        return deployRemoteCall(Deposit.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static Deposit load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Deposit(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Deposit load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Deposit(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
