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
    private static final String BINARY = "6080604052604051610b90380380610b9083398101806040528101908080519060200190929190805182019291906020018051820192919050505082600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555081600090805190602001906100919291906100b1565b5080600190805190602001906100a89291906100b1565b50505050610156565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106100f257805160ff1916838001178555610120565b82800160010185558215610120579182015b8281111561011f578251825591602001919060010190610104565b5b50905061012d9190610131565b5090565b61015391905b8082111561014f576000816000905550600101610137565b5090565b90565b610a2b806101656000396000f300608060405260043610610083576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806348cd45e41461008857806351cff8d9146100b7578063782b3602146100fa5780639504e86714610151578063ed199ecb146101a8578063ef52081514610238578063f47b77401461028f575b600080fd5b34801561009457600080fd5b5061009d61031f565b604051808215151515815260200191505060405180910390f35b3480156100c357600080fd5b506100f8600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610332565b005b34801561010657600080fd5b5061010f610856565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561015d57600080fd5b5061016661087c565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156101b457600080fd5b506101bd610884565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156101fd5780820151818401526020810190506101e2565b50505050905090810190601f16801561022a5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561024457600080fd5b5061024d610922565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561029b57600080fd5b506102a4610948565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156102e45780820151818401526020810190506102c9565b50505050905090810190601f1680156103115780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b600360149054906101000a900460ff1681565b60008073ffffffffffffffffffffffffffffffffffffffff16600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614151561039057600080fd5b819050600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1663782b36026040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561043057600080fd5b505af1158015610444573d6000803e3d6000fd5b505050506040513d602081101561045a57600080fd5b810190808051906020019092919050505073ffffffffffffffffffffffffffffffffffffffff1614151561048d57600080fd5b8073ffffffffffffffffffffffffffffffffffffffff166317cd802d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156104f157600080fd5b505af1158015610505573d6000803e3d6000fd5b505050506040513d602081101561051b57600080fd5b81019080805190602001909291905050508173ffffffffffffffffffffffffffffffffffffffff163110151561055057600080fd5b8073ffffffffffffffffffffffffffffffffffffffff166310b0624a6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156105b457600080fd5b505af11580156105c8573d6000803e3d6000fd5b505050506040513d60208110156105de57600080fd5b81019080805190602001909291905050506106a08273ffffffffffffffffffffffffffffffffffffffff1663350baf786040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561065657600080fd5b505af115801561066a573d6000803e3d6000fd5b505050506040513d602081101561068057600080fd5b8101908080519060200190929190505050426109e690919063ffffffff16565b1115156106ac57600080fd5b8073ffffffffffffffffffffffffffffffffffffffff16630fb5a6b46040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561071057600080fd5b505af1158015610724573d6000803e3d6000fd5b505050506040513d602081101561073a57600080fd5b81019080805190602001909291905050508173ffffffffffffffffffffffffffffffffffffffff16630937e68a6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156107af57600080fd5b505af11580156107c3573d6000803e3d6000fd5b505050506040513d60208110156107d957600080fd5b81019080805190602001909291905050501015156107f657600080fd5b81600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506001600360146101000a81548160ff0219169083151502179055505050565b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600030905090565b60018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561091a5780601f106108ef5761010080835404028352916020019161091a565b820191906000526020600020905b8154815290600101906020018083116108fd57829003601f168201915b505050505081565b600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60008054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109de5780601f106109b3576101008083540402835291602001916109de565b820191906000526020600020905b8154815290600101906020018083116109c157829003601f168201915b505050505081565b60008282111515156109f457fe5b8183039050929150505600a165627a7a72305820cd30e71cdfd1b6b99a1f88b83d55748c8861681c0dba320097e608989929eda60029";

    public static final String FUNC_ISUNBLOCKED = "isUnblocked";

    public static final String FUNC_WITHDRAW = "withdraw";

    public static final String FUNC_TAKER = "taker";

    public static final String FUNC_GETLEGALADDRESS = "getLegalAddress";

    public static final String FUNC_AESKEY = "AESKey";

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

    public RemoteCall<String> AESKey() {
        final Function function = new Function(FUNC_AESKEY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
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

    public static RemoteCall<Legal> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, String _taker, String _information, String _AESKey) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_taker), 
                new org.web3j.abi.datatypes.Utf8String(_information), 
                new org.web3j.abi.datatypes.Utf8String(_AESKey)));
        return deployRemoteCall(Legal.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static RemoteCall<Legal> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue, String _taker, String _information, String _AESKey) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_taker), 
                new org.web3j.abi.datatypes.Utf8String(_information), 
                new org.web3j.abi.datatypes.Utf8String(_AESKey)));
        return deployRemoteCall(Legal.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor, initialWeiValue);
    }

    public static Legal load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Legal(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Legal load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Legal(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
