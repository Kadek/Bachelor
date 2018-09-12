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
    private static final String BINARY = "6080604052336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000600960016101000a81548160ff0219169083600281111561006357fe5b0217905550610c70806100776000396000f300608060405260043610610128576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063015888d61461012d5780630e4d6c711461015d5780630fb5a6b41461018a57806310b0624a146101b5578063176f832e146101e057806325af34cd1461020d5780633edc35191461024657806350e6b86e14610273578063572d66aa1461029e5780636da7edfc146102cb57806374cbe57e1461030e5780637e9a12321461033b5780637f568f181461036b5780638475c028146103985780639a17f96a146103d1578063d148e36d146103fe578063d1d2005614610429578063d3b5dc3b14610480578063d8dfeb45146104ab578063f385cecb146104e4578063f51e181a1461050f578063f6be71d11461053a575b600080fd5b34801561013957600080fd5b5061015b600480360381019080803560ff169060200190929190505050610567565b005b34801561016957600080fd5b50610188600480360381019080803590602001909291905050506105c1565b005b34801561019657600080fd5b5061019f6105dc565b6040518082815260200191505060405180910390f35b3480156101c157600080fd5b506101ca6105e2565b6040518082815260200191505060405180910390f35b3480156101ec57600080fd5b5061020b600480360381019080803590602001909291905050506105e8565b005b34801561021957600080fd5b50610222610603565b6040518082600281111561023257fe5b60ff16815260200191505060405180910390f35b34801561025257600080fd5b5061027160048036038101908080359060200190929190505050610616565b005b34801561027f57600080fd5b50610288610631565b6040518082815260200191505060405180910390f35b3480156102aa57600080fd5b506102c960048036038101908080359060200190929190505050610637565b005b3480156102d757600080fd5b5061030c600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610652565b005b34801561031a57600080fd5b50610339600480360381019080803590602001909291905050506108df565b005b34801561034757600080fd5b50610369600480360381019080803560ff169060200190929190505050610b35565b005b34801561037757600080fd5b5061039660048036038101908080359060200190929190505050610b8f565b005b3480156103a457600080fd5b506103ad610baa565b604051808260028111156103bd57fe5b60ff16815260200191505060405180910390f35b3480156103dd57600080fd5b506103fc60048036038101908080359060200190929190505050610bbd565b005b34801561040a57600080fd5b50610413610bd8565b6040518082815260200191505060405180910390f35b34801561043557600080fd5b5061043e610bde565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561048c57600080fd5b50610495610c04565b6040518082815260200191505060405180910390f35b3480156104b757600080fd5b506104c0610c0a565b604051808260038111156104d057fe5b60ff16815260200191505060405180910390f35b3480156104f057600080fd5b506104f9610c1d565b6040518082815260200191505060405180910390f35b34801561051b57600080fd5b50610524610c23565b6040518082815260200191505060405180910390f35b34801561054657600080fd5b5061056560048036038101908080359060200190929190505050610c29565b005b6000600281111561057457fe5b600960029054906101000a900460ff16600281111561058f57fe5b14151561059b57600080fd5b80600960026101000a81548160ff021916908360028111156105b957fe5b021790555050565b60006008541415156105d257600080fd5b8060088190555050565b60075481565b60085481565b60006006541415156105f957600080fd5b8060068190555050565b600960019054906101000a900460ff1681565b600060055414151561062757600080fd5b8060058190555050565b60045481565b600060045414151561064857600080fd5b8060048190555050565b600080600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614151561069a57600080fd5b600160028111156106a757fe5b600960029054906101000a900460ff1660028111156106c257fe5b14806106f257506002808111156106d557fe5b600960029054906101000a900460ff1660028111156106f057fe5b145b15156106fd57600080fd5b8190506001600281111561070d57fe5b600960029054906101000a900460ff16600281111561072857fe5b14156107e6578073ffffffffffffffffffffffffffffffffffffffff16631ec0987f306040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050600060405180830381600087803b1580156107c957600080fd5b505af11580156107dd573d6000803e3d6000fd5b5050505061089a565b8073ffffffffffffffffffffffffffffffffffffffff1663cb7c85fc306040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050600060405180830381600087803b15801561088157600080fd5b505af1158015610895573d6000803e3d6000fd5b505050505b81600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561093c57600080fd5b6000600281111561094957fe5b600960019054906101000a900460ff16600281111561096457fe5b14151561097057600080fd5b6002600960016101000a81548160ff0219169083600281111561098f57fe5b0217905550600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050600160028111156109c657fe5b600960029054906101000a900460ff1660028111156109e157fe5b1415610a73578073ffffffffffffffffffffffffffffffffffffffff166361cc8558836040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b158015610a5657600080fd5b505af1158015610a6a573d6000803e3d6000fd5b50505050610afb565b8073ffffffffffffffffffffffffffffffffffffffff16638bebfca9836040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b158015610ae257600080fd5b505af1158015610af6573d6000803e3d6000fd5b505050505b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b60006003811115610b4257fe5b600960009054906101000a900460ff166003811115610b5d57fe5b141515610b6957600080fd5b80600960006101000a81548160ff02191690836003811115610b8757fe5b021790555050565b6000600254141515610ba057600080fd5b8060028190555050565b600960029054906101000a900460ff1681565b6000600354141515610bce57600080fd5b8060038190555050565b60035481565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60065481565b600960009054906101000a900460ff1681565b60025481565b60055481565b6000600754141515610c3a57600080fd5b80600781905550505600a165627a7a723058200f02d45104fc596e7f20884ee335a6738764fc5932db141c54280d4a84dfe18f0029";

    public static final String FUNC_SETSIDE = "setSide";

    public static final String FUNC_SETPAYMENTPERIOD = "setPaymentPeriod";

    public static final String FUNC_DURATION = "duration";

    public static final String FUNC_PAYMENTPERIOD = "paymentPeriod";

    public static final String FUNC_SETPRECISION = "setPrecision";

    public static final String FUNC_LOANSTATE = "loanState";

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
