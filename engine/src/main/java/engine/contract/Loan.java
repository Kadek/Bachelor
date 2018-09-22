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
    private static final String BINARY = "608060405234801561001057600080fd5b50611417806100206000396000f300608060405260043610610107576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630937e68a146101095780630fb5a6b41461013457806310b0624a1461015f57806317cd802d1461018a57806325af34cd146101b5578063350baf78146101ee57806348d399e71461021957806350e6b86e1461027057806358b6c5ad1461029b578063682c7826146102a55780636cee6d7714610308578063782b360214610312578063d148e36d14610369578063d3b5dc3b14610394578063d8dfeb45146103bf578063dfab6ef9146103f8578063f385cecb1461040f578063f51e181a1461043a578063fb775b4614610465575b005b34801561011557600080fd5b5061011e6104bc565b6040518082815260200191505060405180910390f35b34801561014057600080fd5b506101496104c2565b6040518082815260200191505060405180910390f35b34801561016b57600080fd5b506101746104c8565b6040518082815260200191505060405180910390f35b34801561019657600080fd5b5061019f6104ce565b6040518082815260200191505060405180910390f35b3480156101c157600080fd5b506101ca6104d4565b604051808260038111156101da57fe5b60ff16815260200191505060405180910390f35b3480156101fa57600080fd5b506102036104e7565b6040518082815260200191505060405180910390f35b34801561022557600080fd5b5061022e6104ed565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561027c57600080fd5b50610285610513565b6040518082815260200191505060405180910390f35b6102a3610519565b005b3480156102b157600080fd5b50610306600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506108db565b005b6103106110b9565b005b34801561031e57600080fd5b50610327611126565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561037557600080fd5b5061037e61114c565b6040518082815260200191505060405180910390f35b3480156103a057600080fd5b506103a9611152565b6040518082815260200191505060405180910390f35b3480156103cb57600080fd5b506103d4611158565b604051808260038111156103e457fe5b60ff16815260200191505060405180910390f35b34801561040457600080fd5b5061040d61116b565b005b34801561041b57600080fd5b506104246111dd565b6040518082815260200191505060405180910390f35b34801561044657600080fd5b5061044f6111e3565b6040518082815260200191505060405180910390f35b34801561047157600080fd5b5061047a6111e9565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b600c5481565b60075481565b60085481565b600a5481565b600d60009054906101000a900460ff1681565b600b5481565b600960019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60045481565b6000806002600381111561052957fe5b600d60009054906101000a900460ff16600381111561054457fe5b14151561055057600080fd5b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156105ab57600080fd5b6008546105c3600b544261120e90919063ffffffff16565b1115156105cf57600080fd5b600754600c541015156105e157600080fd5b600a543073ffffffffffffffffffffffffffffffffffffffff1631101561083d5760038081111561060e57fe5b600960009054906101000a900460ff16600381111561062957fe5b141561070c57600960019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1691508173ffffffffffffffffffffffffffffffffffffffff166351cff8d9306040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050600060405180830381600087803b1580156106ef57600080fd5b505af1158015610703573d6000803e3d6000fd5b50505050610814565b6002600381111561071957fe5b600960009054906101000a900460ff16600381111561073457fe5b141561081357600960019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690508073ffffffffffffffffffffffffffffffffffffffff166351cff8d9306040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050600060405180830381600087803b1580156107fa57600080fd5b505af115801561080e573d6000803e3d6000fd5b505050505b5b6003600d60006101000a81548160ff0219169083600381111561083357fe5b02179055506108d7565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc600a549081150290604051600060405180830381858888f193505050501580156108a6573d6000803e3d6000fd5b506108be600854600b5461122790919063ffffffff16565b600b81905550600c600081548092919060010191905055505b5050565b600080600060038111156108eb57fe5b600d60009054906101000a900460ff16600381111561090657fe5b14151561091257600080fd5b8391508290508073ffffffffffffffffffffffffffffffffffffffff1663fb775b466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561097c57600080fd5b505af1158015610990573d6000803e3d6000fd5b505050506040513d60208110156109a657600080fd5b81019080805190602001909291905050506000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff1663fb775b466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610a5a57600080fd5b505af1158015610a6e573d6000803e3d6000fd5b505050506040513d6020811015610a8457600080fd5b8101908080519060200190929190505050600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055503073ffffffffffffffffffffffffffffffffffffffff16316002819055508073ffffffffffffffffffffffffffffffffffffffff1663d148e36d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610b5757600080fd5b505af1158015610b6b573d6000803e3d6000fd5b505050506040513d6020811015610b8157600080fd5b81019080805190602001909291905050506003819055508173ffffffffffffffffffffffffffffffffffffffff166350e6b86e6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610bfc57600080fd5b505af1158015610c10573d6000803e3d6000fd5b505050506040513d6020811015610c2657600080fd5b81019080805190602001909291905050506004819055508173ffffffffffffffffffffffffffffffffffffffff1663f51e181a6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610ca157600080fd5b505af1158015610cb5573d6000803e3d6000fd5b505050506040513d6020811015610ccb57600080fd5b81019080805190602001909291905050506005819055508173ffffffffffffffffffffffffffffffffffffffff1663d3b5dc3b6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610d4657600080fd5b505af1158015610d5a573d6000803e3d6000fd5b505050506040513d6020811015610d7057600080fd5b81019080805190602001909291905050506006819055508173ffffffffffffffffffffffffffffffffffffffff16630fb5a6b46040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610deb57600080fd5b505af1158015610dff573d6000803e3d6000fd5b505050506040513d6020811015610e1557600080fd5b81019080805190602001909291905050506007819055508173ffffffffffffffffffffffffffffffffffffffff166310b0624a6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610e9057600080fd5b505af1158015610ea4573d6000803e3d6000fd5b505050506040513d6020811015610eba57600080fd5b81019080805190602001909291905050506008819055508173ffffffffffffffffffffffffffffffffffffffff1663d8dfeb456040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610f3557600080fd5b505af1158015610f49573d6000803e3d6000fd5b505050506040513d6020811015610f5f57600080fd5b81019080805190602001909291905050506003811115610f7b57fe5b6003811115610f8657fe5b600960006101000a81548160ff02191690836003811115610fa357fe5b02179055508173ffffffffffffffffffffffffffffffffffffffff166348d399e76040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561100c57600080fd5b505af1158015611020573d6000803e3d6000fd5b505050506040513d602081101561103657600080fd5b8101908080519060200190929190505050600960016101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000600c819055506001600d60006101000a81548160ff021916908360038111156110ae57fe5b021790555050505050565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc6002549081150290604051600060405180830381858888f19350505050158015611123573d6000803e3d6000fd5b50565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60035481565b60065481565b600960009054906101000a900460ff1681565b6001600381111561117857fe5b600d60009054906101000a900460ff16600381111561119357fe5b14151561119f57600080fd5b6111a7611243565b6111af6112bb565b6111b76110b9565b6002600d60006101000a81548160ff021916908360038111156111d657fe5b0217905550565b60025481565b60055481565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600082821115151561121c57fe5b818303905092915050565b6000818301905082811015151561123a57fe5b80905092915050565b600061124d6112c4565b90506112b26005546112a461126d6005548561120e90919063ffffffff16565b6112966002546112886003548861139d90919063ffffffff16565b61139d90919063ffffffff16565b6113d590919063ffffffff16565b6113d590919063ffffffff16565b600a8190555050565b42600b81905550565b60008060008060008093506001925060019150600090505b60065481101561139357611335611326826004540a6113188561130a8860055461139d90919063ffffffff16565b6113d590919063ffffffff16565b6113d590919063ffffffff16565b8561122790919063ffffffff16565b935061135e61134f8260075461120e90919063ffffffff16565b8461139d90919063ffffffff16565b925061138661137760018361122790919063ffffffff16565b8361139d90919063ffffffff16565b91508060010190506112dc565b8394505050505090565b6000808314156113b057600090506113cf565b81830290508183828115156113c157fe5b041415156113cb57fe5b8090505b92915050565b600081838115156113e257fe5b049050929150505600a165627a7a72305820550d9043635b9e2e13adc7a4a0252daec558251506f89153dcba3d468c9ec0970029";

    public static final String FUNC_PAYMENTCOUNT = "paymentCount";

    public static final String FUNC_DURATION = "duration";

    public static final String FUNC_PAYMENTPERIOD = "paymentPeriod";

    public static final String FUNC_REPAYMENT = "repayment";

    public static final String FUNC_LOANSTATE = "loanState";

    public static final String FUNC_LATESTPAYMENTTIMESTAMP = "latestPaymentTimestamp";

    public static final String FUNC_COLLATERALADDRESS = "collateralAddress";

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

    public static final String FUNC_GIVER = "giver";

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

    public RemoteCall<String> collateralAddress() {
        final Function function = new Function(FUNC_COLLATERALADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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

    public RemoteCall<String> giver() {
        final Function function = new Function(FUNC_GIVER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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
