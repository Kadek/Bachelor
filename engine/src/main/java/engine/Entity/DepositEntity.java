package engine.Entity;

import engine.Deposit;
import java.io.IOException;
import java.math.BigInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;

public class DepositEntity extends BlockchainCommunicator {
    
    private static final Logger log = LoggerFactory.getLogger(DepositEntity.class);
    
    private final Web3j web3j;
    private final Credentials credentials;
    
    public DepositEntity(final String privateKey) throws IOException{
        super(privateKey);
        web3j = connectToDefaultNetwork();
        credentials = loadCredentials();
    }
    
    public String createDeposit(
            final String taker,
            final String depositValue
    ) throws Exception {
        log.info("Deploying deposit smart contract");
        Deposit deposit = Deposit.deploy(
            web3j, credentials,
            ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT,
            new BigInteger(depositValue),
            getPublicAddress(credentials),
            taker
        ).send();
        log.info("Contract deployed successfully");
        String depositAddress = deposit.getDepositAddress().send();
        log.info("Balance of a deposit on address {} is {}",
                depositAddress,
                web3j.ethGetBalance(depositAddress, DefaultBlockParameterName.LATEST).send().getBalance().toString());
        return depositAddress;
    }
}
