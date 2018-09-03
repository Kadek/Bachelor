package engine;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
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
public class Preloan extends Contract {
    private static final String BINARY = "6080604052336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000600860016101000a81548160ff0219169083600281111561006357fe5b021790555061078e806100776000396000f300608060405260043610610112576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063015888d6146101175780630e4d6c71146101475780630fb5a6b41461017457806310b0624a1461019f578063176f832e146101ca57806325af34cd146101f75780633edc35191461023057806350e6b86e1461025d578063572d66aa146102885780637e9a1232146102b55780637f568f18146102e55780638475c028146103125780639a17f96a1461034b578063b93995e614610378578063d148e36d1461038f578063d3b5dc3b146103ba578063d8dfeb45146103e5578063f385cecb1461041e578063f51e181a14610449578063f6be71d114610474575b600080fd5b34801561012357600080fd5b50610145600480360381019080803560ff1690602001909291905050506104a1565b005b34801561015357600080fd5b50610172600480360381019080803590602001909291905050506104fb565b005b34801561018057600080fd5b50610189610516565b6040518082815260200191505060405180910390f35b3480156101ab57600080fd5b506101b461051c565b6040518082815260200191505060405180910390f35b3480156101d657600080fd5b506101f560048036038101908080359060200190929190505050610522565b005b34801561020357600080fd5b5061020c61053d565b6040518082600281111561021c57fe5b60ff16815260200191505060405180910390f35b34801561023c57600080fd5b5061025b60048036038101908080359060200190929190505050610550565b005b34801561026957600080fd5b5061027261056b565b6040518082815260200191505060405180910390f35b34801561029457600080fd5b506102b360048036038101908080359060200190929190505050610571565b005b3480156102c157600080fd5b506102e3600480360381019080803560ff16906020019092919050505061058c565b005b3480156102f157600080fd5b50610310600480360381019080803590602001909291905050506105e6565b005b34801561031e57600080fd5b50610327610601565b6040518082600281111561033757fe5b60ff16815260200191505060405180910390f35b34801561035757600080fd5b5061037660048036038101908080359060200190929190505050610614565b005b34801561038457600080fd5b5061038d61062f565b005b34801561039b57600080fd5b506103a461071c565b6040518082815260200191505060405180910390f35b3480156103c657600080fd5b506103cf610722565b6040518082815260200191505060405180910390f35b3480156103f157600080fd5b506103fa610728565b6040518082600381111561040a57fe5b60ff16815260200191505060405180910390f35b34801561042a57600080fd5b5061043361073b565b6040518082815260200191505060405180910390f35b34801561045557600080fd5b5061045e610741565b6040518082815260200191505060405180910390f35b34801561048057600080fd5b5061049f60048036038101908080359060200190929190505050610747565b005b600060028111156104ae57fe5b600860029054906101000a900460ff1660028111156104c957fe5b1415156104d557600080fd5b80600860026101000a81548160ff021916908360028111156104f357fe5b021790555050565b600060075414151561050c57600080fd5b8060078190555050565b60065481565b60075481565b600060055414151561053357600080fd5b8060058190555050565b600860019054906101000a900460ff1681565b600060045414151561056157600080fd5b8060048190555050565b60035481565b600060035414151561058257600080fd5b8060038190555050565b6000600381111561059957fe5b600860009054906101000a900460ff1660038111156105b457fe5b1415156105c057600080fd5b80600860006101000a81548160ff021916908360038111156105de57fe5b021790555050565b60006001541415156105f757600080fd5b8060018190555050565b600860029054906101000a900460ff1681565b600060025414151561062557600080fd5b8060028190555050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561068a57600080fd5b6000600281111561069757fe5b600860019054906101000a900460ff1660028111156106b257fe5b1415156106be57600080fd5b6002600860016101000a81548160ff021916908360028111156106dd57fe5b02179055506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b60025481565b60055481565b600860009054906101000a900460ff1681565b60015481565b60045481565b600060065414151561075857600080fd5b80600681905550505600a165627a7a72305820f3620d75ffd8473812c30cd2292feef20b7a618031aee4878ade12986ed7ec360029";

    public static final String FUNC_SETSIDE = "setSide";

    public static final String FUNC_SETPAYMENTPERIOD = "setPaymentPeriod";

    public static final String FUNC_DURATION = "duration";

    public static final String FUNC_PAYMENTPERIOD = "paymentPeriod";

    public static final String FUNC_SETPRECISION = "setPrecision";

    public static final String FUNC_LOANSTATE = "loanState";

    public static final String FUNC_SETSCALE = "setScale";

    public static final String FUNC_INTERESTRECIPROCAL = "interestReciprocal";

    public static final String FUNC_SETINTERESTRECIPROCAL = "setInterestReciprocal";

    public static final String FUNC_SETCOLLATERAL = "setCollateral";

    public static final String FUNC_SETBASIS = "setBasis";

    public static final String FUNC_SIDE = "side";

    public static final String FUNC_SETINTERESTSCALED = "setInterestScaled";

    public static final String FUNC_CANCELLOANOFFER = "cancelLoanOffer";

    public static final String FUNC_INTERESTSCALED = "interestScaled";

    public static final String FUNC_PRECISION = "precision";

    public static final String FUNC_COLLATERAL = "collateral";

    public static final String FUNC_BASIS = "basis";

    public static final String FUNC_SCALE = "scale";

    public static final String FUNC_SETDURATION = "setDuration";

    protected Preloan(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Preloan(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> setSide(BigInteger _side) {
        final Function function = new Function(
                FUNC_SETSIDE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(_side)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setPaymentPeriod(BigInteger _paymentPeriod) {
        final Function function = new Function(
                FUNC_SETPAYMENTPERIOD, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_paymentPeriod)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    public RemoteCall<TransactionReceipt> setPrecision(BigInteger _precision) {
        final Function function = new Function(
                FUNC_SETPRECISION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_precision)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> loanState() {
        final Function function = new Function(FUNC_LOANSTATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setScale(BigInteger _scale) {
        final Function function = new Function(
                FUNC_SETSCALE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_scale)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> interestReciprocal() {
        final Function function = new Function(FUNC_INTERESTRECIPROCAL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setInterestReciprocal(BigInteger _interestReciprocal) {
        final Function function = new Function(
                FUNC_SETINTERESTRECIPROCAL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_interestReciprocal)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setCollateral(BigInteger _collateral) {
        final Function function = new Function(
                FUNC_SETCOLLATERAL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(_collateral)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setBasis(BigInteger _basis) {
        final Function function = new Function(
                FUNC_SETBASIS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_basis)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> side() {
        final Function function = new Function(FUNC_SIDE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setInterestScaled(BigInteger _interestScaled) {
        final Function function = new Function(
                FUNC_SETINTERESTSCALED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_interestScaled)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> cancelLoanOffer() {
        final Function function = new Function(
                FUNC_CANCELLOANOFFER, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    public RemoteCall<TransactionReceipt> setDuration(BigInteger _duration) {
        final Function function = new Function(
                FUNC_SETDURATION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_duration)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<Preloan> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployRemoteCall(Preloan.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static RemoteCall<Preloan> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployRemoteCall(Preloan.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Preloan load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Preloan(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Preloan load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Preloan(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
