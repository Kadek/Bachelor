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
    private static final String BINARY = "6080604052336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000600960016101000a81548160ff0219169083600181111561006357fe5b021790555061268c806100776000396000f30060806040526004361061016a576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063015888d6146101b75780630e4d6c71146101e75780630fb5a6b41461021457806310b0624a1461023f578063176f832e1461026a57806325af34cd1461029757806335bc868a146102d057806338cc4831146103275780633edc35191461037e57806350e6b86e146103ab57806351f48936146103d6578063572d66aa146104195780636da7edfc1461044657806374cbe57e146104895780637e9a1232146104b65780637f568f18146104e65780638475c028146105135780639a17f96a1461054c578063d148e36d14610579578063d1d20056146105a4578063d3b5dc3b146105fb578063d8dfeb4514610626578063f1653f6e1461065f578063f21658671461068a578063f385cecb146106d7578063f51e181a14610702578063f6be71d11461072d578063fb775b461461075a575b600060025414151561017b57600080fd5b60028081111561018757fe5b600960029054906101000a900460ff1660028111156101a257fe5b1415156101ae57600080fd5b34600281905550005b3480156101c357600080fd5b506101e5600480360381019080803560ff1690602001909291905050506107b1565b005b3480156101f357600080fd5b506102126004803603810190808035906020019092919050505061080b565b005b34801561022057600080fd5b50610229610826565b6040518082815260200191505060405180910390f35b34801561024b57600080fd5b5061025461082c565b6040518082815260200191505060405180910390f35b34801561027657600080fd5b5061029560048036038101908080359060200190929190505050610832565b005b3480156102a357600080fd5b506102ac61084d565b604051808260018111156102bc57fe5b60ff16815260200191505060405180910390f35b3480156102dc57600080fd5b506102e5610860565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561033357600080fd5b5061033c610886565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561038a57600080fd5b506103a96004803603810190808035906020019092919050505061088e565b005b3480156103b757600080fd5b506103c06108a9565b6040518082815260200191505060405180910390f35b3480156103e257600080fd5b50610417600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506108af565b005b34801561042557600080fd5b5061044460048036038101908080359060200190929190505050611010565b005b34801561045257600080fd5b50610487600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061102b565b005b34801561049557600080fd5b506104b4600480360381019080803590602001909291905050506112bf565b005b3480156104c257600080fd5b506104e4600480360381019080803560ff169060200190929190505050611515565b005b3480156104f257600080fd5b506105116004803603810190808035906020019092919050505061156f565b005b34801561051f57600080fd5b506105286115be565b6040518082600281111561053857fe5b60ff16815260200191505060405180910390f35b34801561055857600080fd5b50610577600480360381019080803590602001909291905050506115d1565b005b34801561058557600080fd5b5061058e6115ec565b6040518082815260200191505060405180910390f35b3480156105b057600080fd5b506105b96115f2565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561060757600080fd5b50610610611618565b6040518082815260200191505060405180910390f35b34801561063257600080fd5b5061063b61161e565b6040518082600381111561064b57fe5b60ff16815260200191505060405180910390f35b34801561066b57600080fd5b50610674611631565b6040518082815260200191505060405180910390f35b34801561069657600080fd5b506106d5600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050611637565b005b3480156106e357600080fd5b506106ec61173f565b6040518082815260200191505060405180910390f35b34801561070e57600080fd5b50610717611745565b6040518082815260200191505060405180910390f35b34801561073957600080fd5b506107586004803603810190808035906020019092919050505061174b565b005b34801561076657600080fd5b5061076f611766565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b600060028111156107be57fe5b600960029054906101000a900460ff1660028111156107d957fe5b1415156107e557600080fd5b80600960026101000a81548160ff0219169083600281111561080357fe5b021790555050565b600060085414151561081c57600080fd5b8060088190555050565b60075481565b60085481565b600060065414151561084357600080fd5b8060068190555050565b600960019054906101000a900460ff1681565b600b60009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600030905090565b600060055414151561089f57600080fd5b8060058190555050565b60045481565b6000806002808111156108be57fe5b600960029054906101000a900460ff1660028111156108d957fe5b1415156108e557600080fd5b8291506003548273ffffffffffffffffffffffffffffffffffffffff1663d148e36d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561094f57600080fd5b505af1158015610963573d6000803e3d6000fd5b505050506040513d602081101561097957600080fd5b81019080805190602001909291905050501015151561099757600080fd5b61099f6117a4565b604051809103906000f0801580156109bb573d6000803e3d6000fd5b5090506002548273ffffffffffffffffffffffffffffffffffffffff1663f385cecb6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610a2557600080fd5b505af1158015610a39573d6000803e3d6000fd5b505050506040513d6020811015610a4f57600080fd5b8101908080519060200190929190505050101515610b7a578073ffffffffffffffffffffffffffffffffffffffff166108fc6002549081150290604051600060405180830381858888f19350505050158015610aaf573d6000803e3d6000fd5b508173ffffffffffffffffffffffffffffffffffffffff1663f2165867306002546040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b158015610b5557600080fd5b505af1158015610b69573d6000803e3d6000fd5b505050506000600281905550610e67565b8073ffffffffffffffffffffffffffffffffffffffff166108fc8373ffffffffffffffffffffffffffffffffffffffff1663f385cecb6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610bf857600080fd5b505af1158015610c0c573d6000803e3d6000fd5b505050506040513d6020811015610c2257600080fd5b81019080805190602001909291905050509081150290604051600060405180830381858888f19350505050158015610c5e573d6000803e3d6000fd5b508173ffffffffffffffffffffffffffffffffffffffff1663f385cecb6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610cc357600080fd5b505af1158015610cd7573d6000803e3d6000fd5b505050506040513d6020811015610ced57600080fd5b81019080805190602001909291905050506002600082825403925050819055508173ffffffffffffffffffffffffffffffffffffffff1663f2165867308473ffffffffffffffffffffffffffffffffffffffff1663f385cecb6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610d8e57600080fd5b505af1158015610da2573d6000803e3d6000fd5b505050506040513d6020811015610db857600080fd5b81019080805190602001909291905050506040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b158015610e4e57600080fd5b505af1158015610e62573d6000803e3d6000fd5b505050505b8073ffffffffffffffffffffffffffffffffffffffff1663682c782684306040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050600060405180830381600087803b158015610f3657600080fd5b505af1158015610f4a573d6000803e3d6000fd5b505050508073ffffffffffffffffffffffffffffffffffffffff1663dfab6ef96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401600060405180830381600087803b158015610fb257600080fd5b505af1158015610fc6573d6000803e3d6000fd5b5050505080600b60006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505050565b600060045414151561102157600080fd5b8060048190555050565b600080600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614151561107357600080fd5b6001600281111561108057fe5b600960029054906101000a900460ff16600281111561109b57fe5b14806110cb57506002808111156110ae57fe5b600960029054906101000a900460ff1660028111156110c957fe5b145b15156110d657600080fd5b819050600160028111156110e657fe5b600960029054906101000a900460ff16600281111561110157fe5b14156111bf578073ffffffffffffffffffffffffffffffffffffffff16631ec0987f306040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050600060405180830381600087803b1580156111a257600080fd5b505af11580156111b6573d6000803e3d6000fd5b50505050611273565b8073ffffffffffffffffffffffffffffffffffffffff1663cb7c85fc306040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050600060405180830381600087803b15801561125a57600080fd5b505af115801561126e573d6000803e3d6000fd5b505050505b81600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555042600a819055505050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561131c57600080fd5b6000600181111561132957fe5b600960019054906101000a900460ff16600181111561134457fe5b14151561135057600080fd5b6001600960016101000a81548160ff0219169083600181111561136f57fe5b0217905550600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050600160028111156113a657fe5b600960029054906101000a900460ff1660028111156113c157fe5b1415611453578073ffffffffffffffffffffffffffffffffffffffff166361cc8558836040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b15801561143657600080fd5b505af115801561144a573d6000803e3d6000fd5b505050506114db565b8073ffffffffffffffffffffffffffffffffffffffff16638bebfca9836040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b1580156114c257600080fd5b505af11580156114d6573d6000803e3d6000fd5b505050505b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b6000600381111561152257fe5b600960009054906101000a900460ff16600381111561153d57fe5b14151561154957600080fd5b80600960006101000a81548160ff0219169083600381111561156757fe5b021790555050565b600060025414151561158057600080fd5b6001600281111561158d57fe5b600960029054906101000a900460ff1660028111156115a857fe5b1415156115b457600080fd5b8060028190555050565b600960029054906101000a900460ff1681565b60006003541415156115e257600080fd5b8060038190555050565b60035481565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60065481565b600960009054906101000a900460ff1681565b600a5481565b60006001600281111561164657fe5b600960029054906101000a900460ff16600281111561166157fe5b14151561166d57600080fd5b8290508073ffffffffffffffffffffffffffffffffffffffff1663d148e36d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156116d457600080fd5b505af11580156116e8573d6000803e3d6000fd5b505050506040513d60208110156116fe57600080fd5b81019080805190602001909291905050506003541015151561171f57600080fd5b6117348260025461178b90919063ffffffff16565b600281905550505050565b60025481565b60055481565b600060075414151561175c57600080fd5b8060078190555050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600082821115151561179957fe5b818303905092915050565b604051610eac806117b5833901905600608060405234801561001057600080fd5b50610e8c806100206000396000f3006080604052600436106100e6576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630937e68a146100e85780630fb5a6b41461011357806310b0624a1461013e57806317cd802d14610169578063350baf781461019457806350e6b86e146101bf57806358b6c5ad146101ea578063682c7826146101f45780636cee6d7714610257578063782b360214610261578063d148e36d146102b8578063d3b5dc3b146102e3578063d8dfeb451461030e578063dfab6ef914610347578063f385cecb1461035e578063f51e181a14610389575b005b3480156100f457600080fd5b506100fd6103b4565b6040518082815260200191505060405180910390f35b34801561011f57600080fd5b506101286103ba565b6040518082815260200191505060405180910390f35b34801561014a57600080fd5b506101536103c0565b6040518082815260200191505060405180910390f35b34801561017557600080fd5b5061017e6103c6565b6040518082815260200191505060405180910390f35b3480156101a057600080fd5b506101a96103cc565b6040518082815260200191505060405180910390f35b3480156101cb57600080fd5b506101d46103d2565b6040518082815260200191505060405180910390f35b6101f26103d8565b005b34801561020057600080fd5b50610255600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610504565b005b61025f610bab565b005b34801561026d57600080fd5b50610276610c18565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156102c457600080fd5b506102cd610c3e565b6040518082815260200191505060405180910390f35b3480156102ef57600080fd5b506102f8610c44565b6040518082815260200191505060405180910390f35b34801561031a57600080fd5b50610323610c4a565b6040518082600381111561033357fe5b60ff16815260200191505060405180910390f35b34801561035357600080fd5b5061035c610c5d565b005b34801561036a57600080fd5b50610373610c77565b6040518082815260200191505060405180910390f35b34801561039557600080fd5b5061039e610c7d565b6040518082815260200191505060405180910390f35b600c5481565b60075481565b60085481565b600a5481565b600b5481565b60045481565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561043357600080fd5b60085461044b600b5442610c8390919063ffffffff16565b11151561045757600080fd5b600754600c5410151561046957600080fd5b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc600a549081150290604051600060405180830381858888f193505050501580156104d2573d6000803e3d6000fd5b506104ea600854600b54610c9c90919063ffffffff16565b600b81905550600c60008154809291906001019190505550565b6000808391508290508073ffffffffffffffffffffffffffffffffffffffff1663fb775b466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561057157600080fd5b505af1158015610585573d6000803e3d6000fd5b505050506040513d602081101561059b57600080fd5b81019080805190602001909291905050506000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff1663fb775b466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561064f57600080fd5b505af1158015610663573d6000803e3d6000fd5b505050506040513d602081101561067957600080fd5b8101908080519060200190929190505050600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055503073ffffffffffffffffffffffffffffffffffffffff16316002819055508073ffffffffffffffffffffffffffffffffffffffff1663d148e36d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561074c57600080fd5b505af1158015610760573d6000803e3d6000fd5b505050506040513d602081101561077657600080fd5b81019080805190602001909291905050506003819055508173ffffffffffffffffffffffffffffffffffffffff166350e6b86e6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156107f157600080fd5b505af1158015610805573d6000803e3d6000fd5b505050506040513d602081101561081b57600080fd5b81019080805190602001909291905050506004819055508173ffffffffffffffffffffffffffffffffffffffff1663f51e181a6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561089657600080fd5b505af11580156108aa573d6000803e3d6000fd5b505050506040513d60208110156108c057600080fd5b81019080805190602001909291905050506005819055508173ffffffffffffffffffffffffffffffffffffffff1663d3b5dc3b6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561093b57600080fd5b505af115801561094f573d6000803e3d6000fd5b505050506040513d602081101561096557600080fd5b81019080805190602001909291905050506006819055508173ffffffffffffffffffffffffffffffffffffffff16630fb5a6b46040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156109e057600080fd5b505af11580156109f4573d6000803e3d6000fd5b505050506040513d6020811015610a0a57600080fd5b81019080805190602001909291905050506007819055508173ffffffffffffffffffffffffffffffffffffffff166310b0624a6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610a8557600080fd5b505af1158015610a99573d6000803e3d6000fd5b505050506040513d6020811015610aaf57600080fd5b81019080805190602001909291905050506008819055508173ffffffffffffffffffffffffffffffffffffffff1663d8dfeb456040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610b2a57600080fd5b505af1158015610b3e573d6000803e3d6000fd5b505050506040513d6020811015610b5457600080fd5b81019080805190602001909291905050506003811115610b7057fe5b6003811115610b7b57fe5b600960006101000a81548160ff02191690836003811115610b9857fe5b02179055506000600c8190555050505050565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc6002549081150290604051600060405180830381858888f19350505050158015610c15573d6000803e3d6000fd5b50565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60035481565b60065481565b600960009054906101000a900460ff1681565b610c65610cb8565b610c6d610d30565b610c75610bab565b565b60025481565b60055481565b6000828211151515610c9157fe5b818303905092915050565b60008183019050828110151515610caf57fe5b80905092915050565b6000610cc2610d39565b9050610d27600554610d19610ce260055485610c8390919063ffffffff16565b610d0b600254610cfd60035488610e1290919063ffffffff16565b610e1290919063ffffffff16565b610e4a90919063ffffffff16565b610e4a90919063ffffffff16565b600a8190555050565b42600b81905550565b60008060008060008093506001925060019150600090505b600654811015610e0857610daa610d9b826004540a610d8d85610d7f88600554610e1290919063ffffffff16565b610e4a90919063ffffffff16565b610e4a90919063ffffffff16565b85610c9c90919063ffffffff16565b9350610dd3610dc482600754610c8390919063ffffffff16565b84610e1290919063ffffffff16565b9250610dfb610dec600183610c9c90919063ffffffff16565b83610e1290919063ffffffff16565b9150806001019050610d51565b8394505050505090565b600080831415610e255760009050610e44565b8183029050818382811515610e3657fe5b04141515610e4057fe5b8090505b92915050565b60008183811515610e5757fe5b049050929150505600a165627a7a72305820148a8f51b254e0f2b16e62f20493852bbd34c8768a4afb57f0c13d86cbcf94730029a165627a7a723058200e4a782f1482d4b2fcf1554852c8656f1e5bec29b25fdd8819ada18f4df23f6e0029";

    public static final String FUNC_SETSIDE = "setSide";

    public static final String FUNC_SETPAYMENTPERIOD = "setPaymentPeriod";

    public static final String FUNC_DURATION = "duration";

    public static final String FUNC_PAYMENTPERIOD = "paymentPeriod";

    public static final String FUNC_SETPRECISION = "setPrecision";

    public static final String FUNC_LOANSTATE = "loanState";

    public static final String FUNC_LOANADDRESS = "loanAddress";

    public static final String FUNC_GETADDRESS = "getAddress";

    public static final String FUNC_SETSCALE = "setScale";

    public static final String FUNC_INTERESTRECIPROCAL = "interestReciprocal";

    public static final String FUNC_CREATELOAN = "createLoan";

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

    public static final String FUNC_ADJUSTBASIS = "adjustBasis";

    public static final String FUNC_BASIS = "basis";

    public static final String FUNC_SCALE = "scale";

    public static final String FUNC_SETDURATION = "setDuration";

    public static final String FUNC_GIVER = "giver";

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

    public RemoteCall<String> loanAddress() {
        final Function function = new Function(FUNC_LOANADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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

    public RemoteCall<TransactionReceipt> createLoan(String askAddress) {
        final Function function = new Function(
                FUNC_CREATELOAN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(askAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    public RemoteCall<TransactionReceipt> adjustBasis(String bidAddress, BigInteger adjustment) {
        final Function function = new Function(
                FUNC_ADJUSTBASIS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(bidAddress), 
                new org.web3j.abi.datatypes.generated.Uint256(adjustment)), 
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

    public RemoteCall<TransactionReceipt> setDuration(BigInteger _duration) {
        final Function function = new Function(
                FUNC_SETDURATION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_duration)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> giver() {
        final Function function = new Function(FUNC_GIVER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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
