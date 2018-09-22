package engine;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
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
public class Legal extends Contract {
    private static final String BINARY = "6080604052604051610a35380380610a35833981018060405281019080805190602001909291908051820192919050505081600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550806000908051906020019061008792919061008f565b505050610134565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106100d057805160ff19168380011785556100fe565b828001600101855582156100fe579182015b828111156100fd5782518255916020019190600101906100e2565b5b50905061010b919061010f565b5090565b61013191905b8082111561012d576000816000905550600101610115565b5090565b90565b6108f2806101436000396000f300608060405260043610610078576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806348cd45e41461007d57806351cff8d9146100ac578063782b3602146100ef5780639504e86714610146578063ef5208151461019d578063f47b7740146101f4575b600080fd5b34801561008957600080fd5b50610092610284565b604051808215151515815260200191505060405180910390f35b3480156100b857600080fd5b506100ed600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610297565b005b3480156100fb57600080fd5b506101046107bb565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561015257600080fd5b5061015b6107e1565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156101a957600080fd5b506101b26107e9565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561020057600080fd5b5061020961080f565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561024957808201518184015260208101905061022e565b50505050905090810190601f1680156102765780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b600260149054906101000a900460ff1681565b60008073ffffffffffffffffffffffffffffffffffffffff16600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415156102f557600080fd5b819050600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1663782b36026040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561039557600080fd5b505af11580156103a9573d6000803e3d6000fd5b505050506040513d60208110156103bf57600080fd5b810190808051906020019092919050505073ffffffffffffffffffffffffffffffffffffffff161415156103f257600080fd5b8073ffffffffffffffffffffffffffffffffffffffff166317cd802d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561045657600080fd5b505af115801561046a573d6000803e3d6000fd5b505050506040513d602081101561048057600080fd5b81019080805190602001909291905050508173ffffffffffffffffffffffffffffffffffffffff16311015156104b557600080fd5b8073ffffffffffffffffffffffffffffffffffffffff166310b0624a6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561051957600080fd5b505af115801561052d573d6000803e3d6000fd5b505050506040513d602081101561054357600080fd5b81019080805190602001909291905050506106058273ffffffffffffffffffffffffffffffffffffffff1663350baf786040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156105bb57600080fd5b505af11580156105cf573d6000803e3d6000fd5b505050506040513d60208110156105e557600080fd5b8101908080519060200190929190505050426108ad90919063ffffffff16565b11151561061157600080fd5b8073ffffffffffffffffffffffffffffffffffffffff16630fb5a6b46040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561067557600080fd5b505af1158015610689573d6000803e3d6000fd5b505050506040513d602081101561069f57600080fd5b81019080805190602001909291905050508173ffffffffffffffffffffffffffffffffffffffff16630937e68a6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561071457600080fd5b505af1158015610728573d6000803e3d6000fd5b505050506040513d602081101561073e57600080fd5b810190808051906020019092919050505010151561075b57600080fd5b81600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506001600260146101000a81548160ff0219169083151502179055505050565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600030905090565b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60008054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156108a55780601f1061087a576101008083540402835291602001916108a5565b820191906000526020600020905b81548152906001019060200180831161088857829003601f168201915b505050505081565b60008282111515156108bb57fe5b8183039050929150505600a165627a7a7230582025bfae9b15ee003d26848c72fd370a59ab7bb1b3a47371cb52da13aa63dc29400029";

    public static final String FUNC_ISUNBLOCKED = "isUnblocked";

    public static final String FUNC_WITHDRAW = "withdraw";

    public static final String FUNC_TAKER = "taker";

    public static final String FUNC_GETLEGALADDRESS = "getLegalAddress";

    public static final String FUNC_DISPUTEDLOANADDRESS = "disputedLoanAddress";

    public static final String FUNC_INFORMATION = "information";

    protected Legal(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Legal(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<Boolean> isUnblocked() {
        final Function function = new Function(FUNC_ISUNBLOCKED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
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

    public RemoteCall<String> getLegalAddress() {
        final Function function = new Function(FUNC_GETLEGALADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> disputedLoanAddress() {
        final Function function = new Function(FUNC_DISPUTEDLOANADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> information() {
        final Function function = new Function(FUNC_INFORMATION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static RemoteCall<Legal> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, String _taker, String _information) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_taker), 
                new org.web3j.abi.datatypes.Utf8String(_information)));
        return deployRemoteCall(Legal.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static RemoteCall<Legal> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, String _taker, String _information) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_taker), 
                new org.web3j.abi.datatypes.Utf8String(_information)));
        return deployRemoteCall(Legal.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static Legal load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Legal(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Legal load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Legal(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
