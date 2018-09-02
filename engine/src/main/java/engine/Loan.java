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
    private static final String BINARY = "6080604052336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550346002819055506000600960016101000a81548160ff0219169083600281111561006a57fe5b02179055506000600d81905550610cbf806100866000396000f300608060405260043610610154576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630937e68a146101595780630e4d6c71146101845780630fb5a6b4146101b157806310b0624a146101dc578063176f832e1461020757806317cd802d1461023457806325af34cd1461025f578063350baf78146102985780633edc3519146102c357806350e6b86e146102f0578063572d66aa1461031b57806358b6c5ad146103485780636a7e3709146103525780636cee6d771461037d578063782b3602146103875780637e9a1232146103de5780639a17f96a1461040e578063b93995e61461043b578063d148e36d14610452578063d3b5dc3b1461047d578063d8dfeb45146104a8578063dfab6ef9146104e1578063f385cecb146104f8578063f51e181a14610523578063f575fe721461054e578063f6be71d114610558575b600080fd5b34801561016557600080fd5b5061016e610585565b6040518082815260200191505060405180910390f35b34801561019057600080fd5b506101af6004803603810190808035906020019092919050505061058b565b005b3480156101bd57600080fd5b506101c66105a6565b6040518082815260200191505060405180910390f35b3480156101e857600080fd5b506101f16105ac565b6040518082815260200191505060405180910390f35b34801561021357600080fd5b50610232600480360381019080803590602001909291905050506105b2565b005b34801561024057600080fd5b506102496105cd565b6040518082815260200191505060405180910390f35b34801561026b57600080fd5b506102746105d3565b6040518082600281111561028457fe5b60ff16815260200191505060405180910390f35b3480156102a457600080fd5b506102ad6105e6565b6040518082815260200191505060405180910390f35b3480156102cf57600080fd5b506102ee600480360381019080803590602001909291905050506105ec565b005b3480156102fc57600080fd5b50610305610607565b6040518082815260200191505060405180910390f35b34801561032757600080fd5b506103466004803603810190808035906020019092919050505061060d565b005b610350610628565b005b34801561035e57600080fd5b50610367610771565b6040518082815260200191505060405180910390f35b610385610777565b005b34801561039357600080fd5b5061039c610818565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156103ea57600080fd5b5061040c600480360381019080803560ff16906020019092919050505061083e565b005b34801561041a57600080fd5b5061043960048036038101908080359060200190929190505050610898565b005b34801561044757600080fd5b506104506108b3565b005b34801561045e57600080fd5b506104676109a0565b6040518082815260200191505060405180910390f35b34801561048957600080fd5b506104926109a6565b6040518082815260200191505060405180910390f35b3480156104b457600080fd5b506104bd6109ac565b604051808260038111156104cd57fe5b60ff16815260200191505060405180910390f35b3480156104ed57600080fd5b506104f66109bf565b005b34801561050457600080fd5b5061050d610a72565b6040518082815260200191505060405180910390f35b34801561052f57600080fd5b50610538610a78565b6040518082815260200191505060405180910390f35b610556610a7e565b005b34801561056457600080fd5b5061058360048036038101908080359060200190929190505050610a9b565b005b600d5481565b600060085414151561059c57600080fd5b8060088190555050565b60075481565b60085481565b60006006541415156105c357600080fd5b8060068190555050565b600a5481565b600960019054906101000a900460ff1681565b600c5481565b60006005541415156105fd57600080fd5b8060058190555050565b60045481565b600060045414151561061e57600080fd5b8060048190555050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561068357600080fd5b60085461069b600c5442610ab690919063ffffffff16565b1115156106a757600080fd5b600754600d541015156106b957600080fd5b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc600a549081150290604051600060405180830381858888f19350505050158015610722573d6000803e3d6000fd5b5061073a600a54600b54610ab690919063ffffffff16565b600b81905550610757600854600c54610acf90919063ffffffff16565b600c81905550600d60008154809291906001019190505550565b600b5481565b6001600281111561078457fe5b600960019054906101000a900460ff16600281111561079f57fe5b1415156107ab57600080fd5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc6002549081150290604051600060405180830381858888f19350505050158015610815573d6000803e3d6000fd5b50565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000600381111561084b57fe5b600960009054906101000a900460ff16600381111561086657fe5b14151561087257600080fd5b80600960006101000a81548160ff0219169083600381111561089057fe5b021790555050565b60006003541415156108a957600080fd5b8060038190555050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561090e57600080fd5b6000600281111561091b57fe5b600960019054906101000a900460ff16600281111561093657fe5b14151561094257600080fd5b6002600960016101000a81548160ff0219169083600281111561096157fe5b02179055506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b60035481565b60065481565b600960009054906101000a900460ff1681565b600060028111156109cc57fe5b600960019054906101000a900460ff1660028111156109e757fe5b1415156109f357600080fd5b33600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550610a3c610aeb565b610a44610b63565b6001600960016101000a81548160ff02191690836002811115610a6357fe5b0217905550610a70610777565b565b60025481565b60055481565b610a9334600b54610acf90919063ffffffff16565b600b81905550565b6000600754141515610aac57600080fd5b8060078190555050565b6000828211151515610ac457fe5b818303905092915050565b60008183019050828110151515610ae257fe5b80905092915050565b6000610af5610b6c565b9050610b5a600554610b4c610b1560055485610ab690919063ffffffff16565b610b3e600254610b3060035488610c4590919063ffffffff16565b610c4590919063ffffffff16565b610c7d90919063ffffffff16565b610c7d90919063ffffffff16565b600a8190555050565b42600c81905550565b60008060008060008093506001925060019150600090505b600654811015610c3b57610bdd610bce826004540a610bc085610bb288600554610c4590919063ffffffff16565b610c7d90919063ffffffff16565b610c7d90919063ffffffff16565b85610acf90919063ffffffff16565b9350610c06610bf782600754610ab690919063ffffffff16565b84610c4590919063ffffffff16565b9250610c2e610c1f600183610acf90919063ffffffff16565b83610c4590919063ffffffff16565b9150806001019050610b84565b8394505050505090565b600080831415610c585760009050610c77565b8183029050818382811515610c6957fe5b04141515610c7357fe5b8090505b92915050565b60008183811515610c8a57fe5b049050929150505600a165627a7a72305820c72c222eff20b4624312b21312491a77dae7e80c79fee08134ff1d80e931b2cf0029";

    public static final String FUNC_PAYMENTCOUNT = "paymentCount";

    public static final String FUNC_SETPAYMENTPERIOD = "setPaymentPeriod";

    public static final String FUNC_DURATION = "duration";

    public static final String FUNC_PAYMENTPERIOD = "paymentPeriod";

    public static final String FUNC_SETPRECISION = "setPrecision";

    public static final String FUNC_REPAYMENT = "repayment";

    public static final String FUNC_LOANSTATE = "loanState";

    public static final String FUNC_LATESTPAYMENTTIMESTAMP = "latestPaymentTimestamp";

    public static final String FUNC_SETSCALE = "setScale";

    public static final String FUNC_INTERESTRECIPROCAL = "interestReciprocal";

    public static final String FUNC_SETINTERESTRECIPROCAL = "setInterestReciprocal";

    public static final String FUNC_CONSUMEREPAYMENT = "consumeRepayment";

    public static final String FUNC_REPAYMENTACCOUNT = "repaymentAccount";

    public static final String FUNC_TRANSFERMONEY = "transferMoney";

    public static final String FUNC_TAKER = "taker";

    public static final String FUNC_SETCOLLATERAL = "setCollateral";

    public static final String FUNC_SETINTERESTSCALED = "setInterestScaled";

    public static final String FUNC_CANCELLOANOFFER = "cancelLoanOffer";

    public static final String FUNC_INTERESTSCALED = "interestScaled";

    public static final String FUNC_PRECISION = "precision";

    public static final String FUNC_COLLATERAL = "collateral";

    public static final String FUNC_STARTLOAN = "startLoan";

    public static final String FUNC_BASIS = "basis";

    public static final String FUNC_SCALE = "scale";

    public static final String FUNC_LOADREPAYMENTACCOUNT = "loadRepaymentAccount";

    public static final String FUNC_SETDURATION = "setDuration";

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

    public RemoteCall<BigInteger> repayment() {
        final Function function = new Function(FUNC_REPAYMENT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> loanState() {
        final Function function = new Function(FUNC_LOANSTATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> latestPaymentTimestamp() {
        final Function function = new Function(FUNC_LATESTPAYMENTTIMESTAMP, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
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

    public RemoteCall<TransactionReceipt> consumeRepayment(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_CONSUMEREPAYMENT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<BigInteger> repaymentAccount() {
        final Function function = new Function(FUNC_REPAYMENTACCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
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

    public RemoteCall<TransactionReceipt> setCollateral(BigInteger _collateral) {
        final Function function = new Function(
                FUNC_SETCOLLATERAL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(_collateral)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    public RemoteCall<TransactionReceipt> loadRepaymentAccount(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_LOADREPAYMENTACCOUNT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> setDuration(BigInteger _duration) {
        final Function function = new Function(
                FUNC_SETDURATION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_duration)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<Loan> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployRemoteCall(Loan.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static RemoteCall<Loan> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployRemoteCall(Loan.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Loan load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Loan(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Loan load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Loan(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
