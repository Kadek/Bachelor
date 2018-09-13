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
public class Preloan extends Contract {
    private static final String BINARY = "6080604052336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000600960016101000a81548160ff0219169083600281111561006357fe5b0217905550610d1d806100776000396000f30060806040526004361061013e576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063015888d6146101435780630e4d6c71146101735780630fb5a6b4146101a057806310b0624a146101cb578063176f832e146101f657806325af34cd1461022357806338cc48311461025c5780633edc3519146102b357806350e6b86e146102e0578063572d66aa1461030b5780636da7edfc1461033857806374cbe57e1461037b5780637e9a1232146103a85780637f568f18146103d85780638475c028146104055780639a17f96a1461043e578063d148e36d1461046b578063d1d2005614610496578063d3b5dc3b146104ed578063d8dfeb4514610518578063f1653f6e14610551578063f385cecb1461057c578063f51e181a146105a7578063f6be71d1146105d2575b600080fd5b34801561014f57600080fd5b50610171600480360381019080803560ff1690602001909291905050506105ff565b005b34801561017f57600080fd5b5061019e60048036038101908080359060200190929190505050610659565b005b3480156101ac57600080fd5b506101b5610674565b6040518082815260200191505060405180910390f35b3480156101d757600080fd5b506101e061067a565b6040518082815260200191505060405180910390f35b34801561020257600080fd5b5061022160048036038101908080359060200190929190505050610680565b005b34801561022f57600080fd5b5061023861069b565b6040518082600281111561024857fe5b60ff16815260200191505060405180910390f35b34801561026857600080fd5b506102716106ae565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156102bf57600080fd5b506102de600480360381019080803590602001909291905050506106b6565b005b3480156102ec57600080fd5b506102f56106d1565b6040518082815260200191505060405180910390f35b34801561031757600080fd5b50610336600480360381019080803590602001909291905050506106d7565b005b34801561034457600080fd5b50610379600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506106f2565b005b34801561038757600080fd5b506103a660048036038101908080359060200190929190505050610986565b005b3480156103b457600080fd5b506103d6600480360381019080803560ff169060200190929190505050610bdc565b005b3480156103e457600080fd5b5061040360048036038101908080359060200190929190505050610c36565b005b34801561041157600080fd5b5061041a610c51565b6040518082600281111561042a57fe5b60ff16815260200191505060405180910390f35b34801561044a57600080fd5b5061046960048036038101908080359060200190929190505050610c64565b005b34801561047757600080fd5b50610480610c7f565b6040518082815260200191505060405180910390f35b3480156104a257600080fd5b506104ab610c85565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156104f957600080fd5b50610502610cab565b6040518082815260200191505060405180910390f35b34801561052457600080fd5b5061052d610cb1565b6040518082600381111561053d57fe5b60ff16815260200191505060405180910390f35b34801561055d57600080fd5b50610566610cc4565b6040518082815260200191505060405180910390f35b34801561058857600080fd5b50610591610cca565b6040518082815260200191505060405180910390f35b3480156105b357600080fd5b506105bc610cd0565b6040518082815260200191505060405180910390f35b3480156105de57600080fd5b506105fd60048036038101908080359060200190929190505050610cd6565b005b6000600281111561060c57fe5b600960029054906101000a900460ff16600281111561062757fe5b14151561063357600080fd5b80600960026101000a81548160ff0219169083600281111561065157fe5b021790555050565b600060085414151561066a57600080fd5b8060088190555050565b60075481565b60085481565b600060065414151561069157600080fd5b8060068190555050565b600960019054906101000a900460ff1681565b600030905090565b60006005541415156106c757600080fd5b8060058190555050565b60045481565b60006004541415156106e857600080fd5b8060048190555050565b600080600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614151561073a57600080fd5b6001600281111561074757fe5b600960029054906101000a900460ff16600281111561076257fe5b1480610792575060028081111561077557fe5b600960029054906101000a900460ff16600281111561079057fe5b145b151561079d57600080fd5b819050600160028111156107ad57fe5b600960029054906101000a900460ff1660028111156107c857fe5b1415610886578073ffffffffffffffffffffffffffffffffffffffff16631ec0987f306040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050600060405180830381600087803b15801561086957600080fd5b505af115801561087d573d6000803e3d6000fd5b5050505061093a565b8073ffffffffffffffffffffffffffffffffffffffff1663cb7c85fc306040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050600060405180830381600087803b15801561092157600080fd5b505af1158015610935573d6000803e3d6000fd5b505050505b81600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555042600a819055505050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156109e357600080fd5b600060028111156109f057fe5b600960019054906101000a900460ff166002811115610a0b57fe5b141515610a1757600080fd5b6002600960016101000a81548160ff02191690836002811115610a3657fe5b0217905550600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905060016002811115610a6d57fe5b600960029054906101000a900460ff166002811115610a8857fe5b1415610b1a578073ffffffffffffffffffffffffffffffffffffffff166361cc8558836040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b158015610afd57600080fd5b505af1158015610b11573d6000803e3d6000fd5b50505050610ba2565b8073ffffffffffffffffffffffffffffffffffffffff16638bebfca9836040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b158015610b8957600080fd5b505af1158015610b9d573d6000803e3d6000fd5b505050505b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b60006003811115610be957fe5b600960009054906101000a900460ff166003811115610c0457fe5b141515610c1057600080fd5b80600960006101000a81548160ff02191690836003811115610c2e57fe5b021790555050565b6000600254141515610c4757600080fd5b8060028190555050565b600960029054906101000a900460ff1681565b6000600354141515610c7557600080fd5b8060038190555050565b60035481565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60065481565b600960009054906101000a900460ff1681565b600a5481565b60025481565b60055481565b6000600754141515610ce757600080fd5b80600781905550505600a165627a7a7230582018200c3a1fa317307736590729ca81fd4b8f5c62394f00b3b691e73f18d0f1e60029";

    public static final String FUNC_SETSIDE = "setSide";

    public static final String FUNC_SETPAYMENTPERIOD = "setPaymentPeriod";

    public static final String FUNC_DURATION = "duration";

    public static final String FUNC_PAYMENTPERIOD = "paymentPeriod";

    public static final String FUNC_SETPRECISION = "setPrecision";

    public static final String FUNC_LOANSTATE = "loanState";

    public static final String FUNC_GETADDRESS = "getAddress";

    public static final String FUNC_SETSCALE = "setScale";

    public static final String FUNC_INTERESTRECIPROCAL = "interestReciprocal";

    public static final String FUNC_SETINTERESTRECIPROCAL = "setInterestReciprocal";

    public static final String FUNC_INFORMLEDGER = "informLedger";

    public static final String FUNC_CANCELLOANOFFER = "cancelLoanOffer";

    public static final String FUNC_SETCOLLATERAL = "setCollateral";

    public static final String FUNC_SETBASIS = "setBasis";

    public static final String FUNC_SIDE = "side";

    public static final String FUNC_SETINTERESTSCALED = "setInterestScaled";

    public static final String FUNC_INTERESTSCALED = "interestScaled";

    public static final String FUNC_LEDGERADDRESS = "ledgerAddress";

    public static final String FUNC_PRECISION = "precision";

    public static final String FUNC_COLLATERAL = "collateral";

    public static final String FUNC_TIMECREATED = "timeCreated";

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

    public RemoteCall<String> getAddress() {
        final Function function = new Function(FUNC_GETADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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

    public RemoteCall<TransactionReceipt> informLedger(String incomingLedgerAddress) {
        final Function function = new Function(
                FUNC_INFORMLEDGER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(incomingLedgerAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> cancelLoanOffer(BigInteger index) {
        final Function function = new Function(
                FUNC_CANCELLOANOFFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
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

    public RemoteCall<BigInteger> interestScaled() {
        final Function function = new Function(FUNC_INTERESTSCALED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> ledgerAddress() {
        final Function function = new Function(FUNC_LEDGERADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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

    public RemoteCall<BigInteger> timeCreated() {
        final Function function = new Function(FUNC_TIMECREATED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
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
