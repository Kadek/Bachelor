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
    private static final String BINARY = "6080604052336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000600960016101000a81548160ff0219169083600181111561006357fe5b0217905550612d01806100776000396000f300608060405260043610610175576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063015888d6146101c25780630e4d6c71146101f25780630fb5a6b41461021f57806310b0624a1461024a578063176f832e1461027557806325af34cd146102a257806335bc868a146102db57806338cc4831146103325780633edc35191461038957806348d399e7146103b657806350e6b86e1461040d57806351f4893614610438578063572d66aa1461047b5780636da7edfc146104a857806374cbe57e146104eb5780637f568f18146105185780638475c028146105455780639a17f96a1461057e578063d148e36d146105ab578063d1d20056146105d6578063d3b5dc3b1461062d578063d493af7514610658578063d8dfeb45146106a8578063f1653f6e146106e1578063f21658671461070c578063f385cecb14610759578063f51e181a14610784578063f6be71d1146107af578063fb775b46146107dc575b600060025414151561018657600080fd5b60028081111561019257fe5b600960029054906101000a900460ff1660028111156101ad57fe5b1415156101b957600080fd5b34600281905550005b3480156101ce57600080fd5b506101f0600480360381019080803560ff169060200190929190505050610833565b005b3480156101fe57600080fd5b5061021d6004803603810190808035906020019092919050505061088d565b005b34801561022b57600080fd5b506102346108a8565b6040518082815260200191505060405180910390f35b34801561025657600080fd5b5061025f6108ae565b6040518082815260200191505060405180910390f35b34801561028157600080fd5b506102a0600480360381019080803590602001909291905050506108b4565b005b3480156102ae57600080fd5b506102b76108cf565b604051808260018111156102c757fe5b60ff16815260200191505060405180910390f35b3480156102e757600080fd5b506102f06108e2565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561033e57600080fd5b50610347610908565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561039557600080fd5b506103b460048036038101908080359060200190929190505050610910565b005b3480156103c257600080fd5b506103cb61092b565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561041957600080fd5b50610422610951565b6040518082815260200191505060405180910390f35b34801561044457600080fd5b50610479600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610957565b005b34801561048757600080fd5b506104a6600480360381019080803590602001909291905050506110b8565b005b3480156104b457600080fd5b506104e9600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506110d3565b005b3480156104f757600080fd5b5061051660048036038101908080359060200190929190505050611367565b005b34801561052457600080fd5b50610543600480360381019080803590602001909291905050506115bd565b005b34801561055157600080fd5b5061055a61160c565b6040518082600281111561056a57fe5b60ff16815260200191505060405180910390f35b34801561058a57600080fd5b506105a96004803603810190808035906020019092919050505061161f565b005b3480156105b757600080fd5b506105c061163a565b6040518082815260200191505060405180910390f35b3480156105e257600080fd5b506105eb611640565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561063957600080fd5b50610642611666565b6040518082815260200191505060405180910390f35b34801561066457600080fd5b506106a6600480360381019080803560ff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061166c565b005b3480156106b457600080fd5b506106bd611708565b604051808260038111156106cd57fe5b60ff16815260200191505060405180910390f35b3480156106ed57600080fd5b506106f661171b565b6040518082815260200191505060405180910390f35b34801561071857600080fd5b50610757600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050611721565b005b34801561076557600080fd5b5061076e611829565b6040518082815260200191505060405180910390f35b34801561079057600080fd5b5061079961182f565b6040518082815260200191505060405180910390f35b3480156107bb57600080fd5b506107da60048036038101908080359060200190929190505050611835565b005b3480156107e857600080fd5b506107f1611850565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b6000600281111561084057fe5b600960029054906101000a900460ff16600281111561085b57fe5b14151561086757600080fd5b80600960026101000a81548160ff0219169083600281111561088557fe5b021790555050565b600060085414151561089e57600080fd5b8060088190555050565b60075481565b60085481565b60006006541415156108c557600080fd5b8060068190555050565b600960019054906101000a900460ff1681565b600c60009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600030905090565b600060055414151561092157600080fd5b8060058190555050565b600b60009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60045481565b60008060028081111561096657fe5b600960029054906101000a900460ff16600281111561098157fe5b14151561098d57600080fd5b8291506003548273ffffffffffffffffffffffffffffffffffffffff1663d148e36d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156109f757600080fd5b505af1158015610a0b573d6000803e3d6000fd5b505050506040513d6020811015610a2157600080fd5b810190808051906020019092919050505010151515610a3f57600080fd5b610a4761188e565b604051809103906000f080158015610a63573d6000803e3d6000fd5b5090506002548273ffffffffffffffffffffffffffffffffffffffff1663f385cecb6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610acd57600080fd5b505af1158015610ae1573d6000803e3d6000fd5b505050506040513d6020811015610af757600080fd5b8101908080519060200190929190505050101515610c22578073ffffffffffffffffffffffffffffffffffffffff166108fc6002549081150290604051600060405180830381858888f19350505050158015610b57573d6000803e3d6000fd5b508173ffffffffffffffffffffffffffffffffffffffff1663f2165867306002546040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b158015610bfd57600080fd5b505af1158015610c11573d6000803e3d6000fd5b505050506000600281905550610f0f565b8073ffffffffffffffffffffffffffffffffffffffff166108fc8373ffffffffffffffffffffffffffffffffffffffff1663f385cecb6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610ca057600080fd5b505af1158015610cb4573d6000803e3d6000fd5b505050506040513d6020811015610cca57600080fd5b81019080805190602001909291905050509081150290604051600060405180830381858888f19350505050158015610d06573d6000803e3d6000fd5b508173ffffffffffffffffffffffffffffffffffffffff1663f385cecb6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610d6b57600080fd5b505af1158015610d7f573d6000803e3d6000fd5b505050506040513d6020811015610d9557600080fd5b81019080805190602001909291905050506002600082825403925050819055508173ffffffffffffffffffffffffffffffffffffffff1663f2165867308473ffffffffffffffffffffffffffffffffffffffff1663f385cecb6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610e3657600080fd5b505af1158015610e4a573d6000803e3d6000fd5b505050506040513d6020811015610e6057600080fd5b81019080805190602001909291905050506040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050600060405180830381600087803b158015610ef657600080fd5b505af1158015610f0a573d6000803e3d6000fd5b505050505b8073ffffffffffffffffffffffffffffffffffffffff1663682c782684306040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050600060405180830381600087803b158015610fde57600080fd5b505af1158015610ff2573d6000803e3d6000fd5b505050508073ffffffffffffffffffffffffffffffffffffffff1663dfab6ef96040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401600060405180830381600087803b15801561105a57600080fd5b505af115801561106e573d6000803e3d6000fd5b5050505080600c60006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505050565b60006004541415156110c957600080fd5b8060048190555050565b600080600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614151561111b57600080fd5b6001600281111561112857fe5b600960029054906101000a900460ff16600281111561114357fe5b1480611173575060028081111561115657fe5b600960029054906101000a900460ff16600281111561117157fe5b145b151561117e57600080fd5b8190506001600281111561118e57fe5b600960029054906101000a900460ff1660028111156111a957fe5b1415611267578073ffffffffffffffffffffffffffffffffffffffff16631ec0987f306040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050600060405180830381600087803b15801561124a57600080fd5b505af115801561125e573d6000803e3d6000fd5b5050505061131b565b8073ffffffffffffffffffffffffffffffffffffffff1663cb7c85fc306040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050600060405180830381600087803b15801561130257600080fd5b505af1158015611316573d6000803e3d6000fd5b505050505b81600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555042600a819055505050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156113c457600080fd5b600060018111156113d157fe5b600960019054906101000a900460ff1660018111156113ec57fe5b1415156113f857600080fd5b6001600960016101000a81548160ff0219169083600181111561141757fe5b0217905550600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690506001600281111561144e57fe5b600960029054906101000a900460ff16600281111561146957fe5b14156114fb578073ffffffffffffffffffffffffffffffffffffffff166361cc8558836040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b1580156114de57600080fd5b505af11580156114f2573d6000803e3d6000fd5b50505050611583565b8073ffffffffffffffffffffffffffffffffffffffff16638bebfca9836040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b15801561156a57600080fd5b505af115801561157e573d6000803e3d6000fd5b505050505b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b60006002541415156115ce57600080fd5b600160028111156115db57fe5b600960029054906101000a900460ff1660028111156115f657fe5b14151561160257600080fd5b8060028190555050565b600960029054906101000a900460ff1681565b600060035414151561163057600080fd5b8060038190555050565b60035481565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60065481565b6000600381111561167957fe5b600960009054906101000a900460ff16600381111561169457fe5b1415156116a057600080fd5b81600960006101000a81548160ff021916908360038111156116be57fe5b021790555080600b60006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050565b600960009054906101000a900460ff1681565b600a5481565b60006001600281111561173057fe5b600960029054906101000a900460ff16600281111561174b57fe5b14151561175757600080fd5b8290508073ffffffffffffffffffffffffffffffffffffffff1663d148e36d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b1580156117be57600080fd5b505af11580156117d2573d6000803e3d6000fd5b505050506040513d60208110156117e857600080fd5b81019080805190602001909291905050506003541015151561180957600080fd5b61181e8260025461187590919063ffffffff16565b600281905550505050565b60025481565b60055481565b600060075414151561184657600080fd5b8060078190555050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600082821115151561188357fe5b818303905092915050565b6040516114378061189f833901905600608060405234801561001057600080fd5b50611417806100206000396000f300608060405260043610610107576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630937e68a146101095780630fb5a6b41461013457806310b0624a1461015f57806317cd802d1461018a57806325af34cd146101b5578063350baf78146101ee57806348d399e71461021957806350e6b86e1461027057806358b6c5ad1461029b578063682c7826146102a55780636cee6d7714610308578063782b360214610312578063d148e36d14610369578063d3b5dc3b14610394578063d8dfeb45146103bf578063dfab6ef9146103f8578063f385cecb1461040f578063f51e181a1461043a578063fb775b4614610465575b005b34801561011557600080fd5b5061011e6104bc565b6040518082815260200191505060405180910390f35b34801561014057600080fd5b506101496104c2565b6040518082815260200191505060405180910390f35b34801561016b57600080fd5b506101746104c8565b6040518082815260200191505060405180910390f35b34801561019657600080fd5b5061019f6104ce565b6040518082815260200191505060405180910390f35b3480156101c157600080fd5b506101ca6104d4565b604051808260038111156101da57fe5b60ff16815260200191505060405180910390f35b3480156101fa57600080fd5b506102036104e7565b6040518082815260200191505060405180910390f35b34801561022557600080fd5b5061022e6104ed565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561027c57600080fd5b50610285610513565b6040518082815260200191505060405180910390f35b6102a3610519565b005b3480156102b157600080fd5b50610306600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506108db565b005b6103106110b9565b005b34801561031e57600080fd5b50610327611126565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561037557600080fd5b5061037e61114c565b6040518082815260200191505060405180910390f35b3480156103a057600080fd5b506103a9611152565b6040518082815260200191505060405180910390f35b3480156103cb57600080fd5b506103d4611158565b604051808260038111156103e457fe5b60ff16815260200191505060405180910390f35b34801561040457600080fd5b5061040d61116b565b005b34801561041b57600080fd5b506104246111dd565b6040518082815260200191505060405180910390f35b34801561044657600080fd5b5061044f6111e3565b6040518082815260200191505060405180910390f35b34801561047157600080fd5b5061047a6111e9565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b600c5481565b60075481565b60085481565b600a5481565b600d60009054906101000a900460ff1681565b600b5481565b600960019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60045481565b6000806002600381111561052957fe5b600d60009054906101000a900460ff16600381111561054457fe5b14151561055057600080fd5b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156105ab57600080fd5b6008546105c3600b544261120e90919063ffffffff16565b1115156105cf57600080fd5b600754600c541015156105e157600080fd5b600a543073ffffffffffffffffffffffffffffffffffffffff1631101561083d5760038081111561060e57fe5b600960009054906101000a900460ff16600381111561062957fe5b141561070c57600960019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1691508173ffffffffffffffffffffffffffffffffffffffff166351cff8d9306040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050600060405180830381600087803b1580156106ef57600080fd5b505af1158015610703573d6000803e3d6000fd5b50505050610814565b6002600381111561071957fe5b600960009054906101000a900460ff16600381111561073457fe5b141561081357600960019054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690508073ffffffffffffffffffffffffffffffffffffffff166351cff8d9306040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050600060405180830381600087803b1580156107fa57600080fd5b505af115801561080e573d6000803e3d6000fd5b505050505b5b6003600d60006101000a81548160ff0219169083600381111561083357fe5b02179055506108d7565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc600a549081150290604051600060405180830381858888f193505050501580156108a6573d6000803e3d6000fd5b506108be600854600b5461122790919063ffffffff16565b600b81905550600c600081548092919060010191905055505b5050565b600080600060038111156108eb57fe5b600d60009054906101000a900460ff16600381111561090657fe5b14151561091257600080fd5b8391508290508073ffffffffffffffffffffffffffffffffffffffff1663fb775b466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561097c57600080fd5b505af1158015610990573d6000803e3d6000fd5b505050506040513d60208110156109a657600080fd5b81019080805190602001909291905050506000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff1663fb775b466040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610a5a57600080fd5b505af1158015610a6e573d6000803e3d6000fd5b505050506040513d6020811015610a8457600080fd5b8101908080519060200190929190505050600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055503073ffffffffffffffffffffffffffffffffffffffff16316002819055508073ffffffffffffffffffffffffffffffffffffffff1663d148e36d6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610b5757600080fd5b505af1158015610b6b573d6000803e3d6000fd5b505050506040513d6020811015610b8157600080fd5b81019080805190602001909291905050506003819055508173ffffffffffffffffffffffffffffffffffffffff166350e6b86e6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610bfc57600080fd5b505af1158015610c10573d6000803e3d6000fd5b505050506040513d6020811015610c2657600080fd5b81019080805190602001909291905050506004819055508173ffffffffffffffffffffffffffffffffffffffff1663f51e181a6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610ca157600080fd5b505af1158015610cb5573d6000803e3d6000fd5b505050506040513d6020811015610ccb57600080fd5b81019080805190602001909291905050506005819055508173ffffffffffffffffffffffffffffffffffffffff1663d3b5dc3b6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610d4657600080fd5b505af1158015610d5a573d6000803e3d6000fd5b505050506040513d6020811015610d7057600080fd5b81019080805190602001909291905050506006819055508173ffffffffffffffffffffffffffffffffffffffff16630fb5a6b46040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610deb57600080fd5b505af1158015610dff573d6000803e3d6000fd5b505050506040513d6020811015610e1557600080fd5b81019080805190602001909291905050506007819055508173ffffffffffffffffffffffffffffffffffffffff166310b0624a6040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610e9057600080fd5b505af1158015610ea4573d6000803e3d6000fd5b505050506040513d6020811015610eba57600080fd5b81019080805190602001909291905050506008819055508173ffffffffffffffffffffffffffffffffffffffff1663d8dfeb456040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610f3557600080fd5b505af1158015610f49573d6000803e3d6000fd5b505050506040513d6020811015610f5f57600080fd5b81019080805190602001909291905050506003811115610f7b57fe5b6003811115610f8657fe5b600960006101000a81548160ff02191690836003811115610fa357fe5b02179055508173ffffffffffffffffffffffffffffffffffffffff166348d399e76040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561100c57600080fd5b505af1158015611020573d6000803e3d6000fd5b505050506040513d602081101561103657600080fd5b8101908080519060200190929190505050600960016101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000600c819055506001600d60006101000a81548160ff021916908360038111156110ae57fe5b021790555050505050565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc6002549081150290604051600060405180830381858888f19350505050158015611123573d6000803e3d6000fd5b50565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60035481565b60065481565b600960009054906101000a900460ff1681565b6001600381111561117857fe5b600d60009054906101000a900460ff16600381111561119357fe5b14151561119f57600080fd5b6111a7611243565b6111af6112bb565b6111b76110b9565b6002600d60006101000a81548160ff021916908360038111156111d657fe5b0217905550565b60025481565b60055481565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600082821115151561121c57fe5b818303905092915050565b6000818301905082811015151561123a57fe5b80905092915050565b600061124d6112c4565b90506112b26005546112a461126d6005548561120e90919063ffffffff16565b6112966002546112886003548861139d90919063ffffffff16565b61139d90919063ffffffff16565b6113d590919063ffffffff16565b6113d590919063ffffffff16565b600a8190555050565b42600b81905550565b60008060008060008093506001925060019150600090505b60065481101561139357611335611326826004540a6113188561130a8860055461139d90919063ffffffff16565b6113d590919063ffffffff16565b6113d590919063ffffffff16565b8561122790919063ffffffff16565b935061135e61134f8260075461120e90919063ffffffff16565b8461139d90919063ffffffff16565b925061138661137760018361122790919063ffffffff16565b8361139d90919063ffffffff16565b91508060010190506112dc565b8394505050505090565b6000808314156113b057600090506113cf565b81830290508183828115156113c157fe5b041415156113cb57fe5b8090505b92915050565b600081838115156113e257fe5b049050929150505600a165627a7a72305820fbef7628f20e1252584ccd8ee714d312487d08559c3f66347ec2cb8b47db3cd30029a165627a7a72305820c9aaadbcf4e3b393d5c62c5a77d58f6c2937c912d767998c43625f4361b78ddb0029";

    public static final String FUNC_SETSIDE = "setSide";

    public static final String FUNC_SETPAYMENTPERIOD = "setPaymentPeriod";

    public static final String FUNC_DURATION = "duration";

    public static final String FUNC_PAYMENTPERIOD = "paymentPeriod";

    public static final String FUNC_SETPRECISION = "setPrecision";

    public static final String FUNC_LOANSTATE = "loanState";

    public static final String FUNC_LOANADDRESS = "loanAddress";

    public static final String FUNC_GETADDRESS = "getAddress";

    public static final String FUNC_SETSCALE = "setScale";

    public static final String FUNC_COLLATERALADDRESS = "collateralAddress";

    public static final String FUNC_INTERESTRECIPROCAL = "interestReciprocal";

    public static final String FUNC_CREATELOAN = "createLoan";

    public static final String FUNC_SETINTERESTRECIPROCAL = "setInterestReciprocal";

    public static final String FUNC_INFORMLEDGER = "informLedger";

    public static final String FUNC_CANCELLOANOFFER = "cancelLoanOffer";

    public static final String FUNC_SETBASIS = "setBasis";

    public static final String FUNC_SIDE = "side";

    public static final String FUNC_SETINTERESTSCALED = "setInterestScaled";

    public static final String FUNC_INTERESTSCALED = "interestScaled";

    public static final String FUNC_LEDGERADDRESS = "ledgerAddress";

    public static final String FUNC_PRECISION = "precision";

    public static final String FUNC_SETCOLLATERAL = "setCollateral";

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

    public RemoteCall<TransactionReceipt> setCollateral(BigInteger _collateral, String _collateralAddress) {
        final Function function = new Function(
                FUNC_SETCOLLATERAL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(_collateral), 
                new org.web3j.abi.datatypes.Address(_collateralAddress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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
