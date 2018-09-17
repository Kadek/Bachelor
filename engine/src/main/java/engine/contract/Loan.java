package engine;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
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
public class Loan extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50610e8c806100206000396000f3006080604052600436106100e6576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630937e68a146100e85780630fb5a6b41461011357806310b0624a1461013e57806317cd802d14610169578063350baf781461019457806350e6b86e146101bf57806358b6c5ad146101ea578063682c7826146101f45780636cee6d7714610257578063782b360214610261578063d148e36d146102b8578063d3b5dc3b146102e3578063d8dfeb451461030e578063dfab6ef914610347578063f385cecb1461035e578063f51e181a14610389575b005b3480156100f457600080fd5b506100fd6103b4565b6040518082815260200191505060405180910390f35b34801561011f57600080fd5b506101286103ba565b6040518082815260200191505060405180910390f35b34801561014a57600080fd5b506101536103c0565b6040518082815260200191505060405180910390f35b34801561017557600080fd5b5061017e6103c6565b6040518082815260200191505060405180910390f35b3480156101a057600080fd5b506101a96103cc565b6040518082815260200191505060405180910390f35b3480156101cb57600080fd5b506101d46103d2565b6040518082815260200191505060405180910390f35b6101f26103d8565b005b34801561020057600080fd5b50610255600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610504565b005b61025f610bab565b005b34801561026d57600080fd5b50610276610c18565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156102c457600080fd5b506102cd610c3e565b6040518082815260200191505060405180910390f35b3480156102ef57600080fd5b506102f8610c44565b6040518082815260200191505060405180910390f35b34801561031a57600080fd5b50610323610c4a565b6040518082600381111561033357fe5b60ff16815260200191505060405180910390f35b34801561035357600080fd5b5061035c610c5d565b005b34801561036a57600080fd5b50610373610c77565b6040518082815260200191505060405180910390f35b34801561039557600080fd5b5061039e610c7d565b6040518082815260200191505060405180910390f35b600c5481565b60075481565b60085481565b600a5481565b600b5481565b60045481565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561043357600080fd5b60085461044b600b5442610c8390919063ffffffff16565b11151561045757600080fd5b600754600c5410151561046957600080fd5b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc600a549081150290604051600060405180830381858888f193505050501580156104d2573d6000803e3d6000fd5b506104ea600854600b54610c9c90919063ffffffff16565b600b81905550600c60008154809291906001019190505550565b6000808391508290508073ffffffffffffffffffffffffffffffffffffffff1663fb775b466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561057157600080fd5b505af1158015610585573d6000803e3d6000fd5b505050506040513d602081101561059b57600080fd5b81019080805190602001909291905050506000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff1663fb775b466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561064f57600080fd5b505af1158015610663573d6000803e3d6000fd5b505050506040513d602081101561067957600080fd5b8101908080519060200190929190505050600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055503073ffffffffffffffffffffffffffffffffffffffff16316002819055508073ffffffffffffffffffffffffffffffffffffffff1663d148e36d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561074c57600080fd5b505af1158015610760573d6000803e3d6000fd5b505050506040513d602081101561077657600080fd5b81019080805190602001909291905050506003819055508173ffffffffffffffffffffffffffffffffffffffff166350e6b86e6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156107f157600080fd5b505af1158015610805573d6000803e3d6000fd5b505050506040513d602081101561081b57600080fd5b81019080805190602001909291905050506004819055508173ffffffffffffffffffffffffffffffffffffffff1663f51e181a6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561089657600080fd5b505af11580156108aa573d6000803e3d6000fd5b505050506040513d60208110156108c057600080fd5b81019080805190602001909291905050506005819055508173ffffffffffffffffffffffffffffffffffffffff1663d3b5dc3b6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561093b57600080fd5b505af115801561094f573d6000803e3d6000fd5b505050506040513d602081101561096557600080fd5b81019080805190602001909291905050506006819055508173ffffffffffffffffffffffffffffffffffffffff16630fb5a6b46040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156109e057600080fd5b505af11580156109f4573d6000803e3d6000fd5b505050506040513d6020811015610a0a57600080fd5b81019080805190602001909291905050506007819055508173ffffffffffffffffffffffffffffffffffffffff166310b0624a6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610a8557600080fd5b505af1158015610a99573d6000803e3d6000fd5b505050506040513d6020811015610aaf57600080fd5b81019080805190602001909291905050506008819055508173ffffffffffffffffffffffffffffffffffffffff1663d8dfeb456040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610b2a57600080fd5b505af1158015610b3e573d6000803e3d6000fd5b505050506040513d6020811015610b5457600080fd5b81019080805190602001909291905050506003811115610b7057fe5b6003811115610b7b57fe5b600960006101000a81548160ff02191690836003811115610b9857fe5b02179055506000600c8190555050505050565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc6002549081150290604051600060405180830381858888f19350505050158015610c15573d6000803e3d6000fd5b50565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60035481565b60065481565b600960009054906101000a900460ff1681565b610c65610cb8565b610c6d610d30565b610c75610bab565b565b60025481565b60055481565b6000828211151515610c9157fe5b818303905092915050565b60008183019050828110151515610caf57fe5b80905092915050565b6000610cc2610d39565b9050610d27600554610d19610ce260055485610c8390919063ffffffff16565b610d0b600254610cfd60035488610e1290919063ffffffff16565b610e1290919063ffffffff16565b610e4a90919063ffffffff16565b610e4a90919063ffffffff16565b600a8190555050565b42600b81905550565b60008060008060008093506001925060019150600090505b600654811015610e0857610daa610d9b826004540a610d8d85610d7f88600554610e1290919063ffffffff16565b610e4a90919063ffffffff16565b610e4a90919063ffffffff16565b85610c9c90919063ffffffff16565b9350610dd3610dc482600754610c8390919063ffffffff16565b84610e1290919063ffffffff16565b9250610dfb610dec600183610c9c90919063ffffffff16565b83610e1290919063ffffffff16565b9150806001019050610d51565b8394505050505090565b600080831415610e255760009050610e44565b8183029050818382811515610e3657fe5b04141515610e4057fe5b8090505b92915050565b60008183811515610e5757fe5b049050929150505600a165627a7a72305820d1312e3b9f2e105df7c14dccb8afd7b1108ca4d93de99e162c6278493a6515290029";

    public static final String FUNC_PAYMENTCOUNT = "paymentCount";

    public static final String FUNC_DURATION = "duration";

    public static final String FUNC_PAYMENTPERIOD = "paymentPeriod";

    public static final String FUNC_REPAYMENT = "repayment";

    public static final String FUNC_LATESTPAYMENTTIMESTAMP = "latestPaymentTimestamp";

    public static final String FUNC_INTERESTRECIPROCAL = "interestReciprocal";

    public static final String FUNC_CONSUMEREPAYMENT = "consumeRepayment";

    public static final String FUNC_SETLOAN = "setLoan";

    public static final String FUNC_TRANSFERMONEY = "transferMoney";

    public static final String FUNC_TAKER = "taker";

    public static final String FUNC_INTERESTSCALED = "interestScaled";

    public static final String FUNC_PRECISION = "precision";

    public static final String FUNC_COLLATERAL = "collateral";

    public static final String FUNC_STARTLOAN = "startLoan";

    public static final String FUNC_BASIS = "basis";

    public static final String FUNC_SCALE = "scale";

    protected Loan(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Loan(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<BigInteger> paymentCount() {
        final Function function = new Function(FUNC_PAYMENTCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> duration() {
        final Function function = new Function(FUNC_DURATION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> paymentPeriod() {
        final Function function = new Function(FUNC_PAYMENTPERIOD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> repayment() {
        final Function function = new Function(FUNC_REPAYMENT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> latestPaymentTimestamp() {
        final Function function = new Function(FUNC_LATESTPAYMENTTIMESTAMP, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> interestReciprocal() {
        final Function function = new Function(FUNC_INTERESTRECIPROCAL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> consumeRepayment(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_CONSUMEREPAYMENT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> setLoan(String ask, String bid) {
        final Function function = new Function(
                FUNC_SETLOAN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(ask), 
                new org.web3j.abi.datatypes.Address(bid)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transferMoney(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_TRANSFERMONEY, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<String> taker() {
        final Function function = new Function(FUNC_TAKER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> interestScaled() {
        final Function function = new Function(FUNC_INTERESTSCALED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> precision() {
        final Function function = new Function(FUNC_PRECISION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> collateral() {
        final Function function = new Function(FUNC_COLLATERAL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> startLoan() {
        final Function function = new Function(
                FUNC_STARTLOAN, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> basis() {
        final Function function = new Function(FUNC_BASIS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> scale() {
        final Function function = new Function(FUNC_SCALE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public static RemoteCall<Loan> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Loan.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Loan> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Loan.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static Loan load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Loan(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Loan load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Loan(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
