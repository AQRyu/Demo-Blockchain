package com.aqryuz.footballTicketDemo.model;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.0.0-alpha-1.
 */
public class TicketContract extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50604051610eea380380610eea833981016040528051602080830151600383905560008054600160a060020a031916331790556004805460ff191660011790559092018051919290916100699160029190840190610071565b50505061010c565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106100b257805160ff19168380011785556100df565b828001600101855582156100df579182015b828111156100df5782518255916020019190600101906100c4565b506100eb9291506100ef565b5090565b61010991905b808211156100eb57600081556001016100f5565b90565b610dcf8061011b6000396000f3006080604052600436106100ae5763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166308551a5381146100b35780631ccfd3f4146100e45780632b62bafe1461016e578063565697d3146101925780637150d8ae146101b9578063a07f3a56146101ce578063c11d77cb14610203578063cc7c247a1461021d578063d5cef13314610232578063dbd0e1b614610247578063f6f1314f1461025c575b600080fd5b3480156100bf57600080fd5b506100c861036f565b60408051600160a060020a039092168252519081900360200190f35b3480156100f057600080fd5b506100f961037e565b6040805160208082528351818301528351919283929083019185019080838360005b8381101561013357818101518382015260200161011b565b50505050905090810190601f1680156101605780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b61019060ff600480358216916024803590911691604435918201910135610412565b005b34801561019e57600080fd5b506101a76106d0565b60408051918252519081900360200190f35b3480156101c557600080fd5b506100c86106d6565b3480156101da57600080fd5b506101ef600160a060020a03600435166106e5565b604080519115158252519081900360200190f35b34801561020f57600080fd5b506101906004351515610703565b34801561022957600080fd5b50610190610778565b34801561023e57600080fd5b50610190610869565b34801561025357600080fd5b506100c8610a6c565b34801561026857600080fd5b5061027d600160a060020a0360043516610a7b565b6040805160ff80851692820192909252908216606082015260808082528551908201528451819060208083019160a084019189019080838360005b838110156102d05781810151838201526020016102b8565b50505050905090810190601f1680156102fd5780820380516001836020036101000a031916815260200191505b50838103825286518152865160209182019188019080838360005b83811015610330578181015183820152602001610318565b50505050905090810190601f16801561035d5780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390f35b600054600160a060020a031681565b60028054604080516020601f60001961010060018716150201909416859004938401819004810282018101909252828152606093909290918301828280156104075780601f106103dc57610100808354040283529160200191610407565b820191906000526020600020905b8154815290600101906020018083116103ea57829003601f168201915b505050505090505b90565b60008060035411151561046f576040805160e560020a62461bcd02815260206004820152600860248201527f536f6c64206f7574000000000000000000000000000000000000000000000000604482015290519081900360640190fd5b60045460ff1615156104cb576040805160e560020a62461bcd02815260206004820152601960248201527f546865206576656e74206973206e6f74206f70656e6e65642100000000000000604482015290519081900360640190fd5b600054600160a060020a031633141561052e576040805160e560020a62461bcd02815260206004820152601560248201527f53656c6c65722063616e6e6f7420646f20746869730000000000000000000000604482015290519081900360640190fd5b506001805473ffffffffffffffffffffffffffffffffffffffff1916331790819055600160a060020a03166000908152600560205260409020805460ff161561057657600080fd5b60038460ff161115801561058f57506003548460ff1611155b151561059a57600080fd5b805460ff191660011781556105b3600282018484610c17565b50805460ff86811661010090810261ff00198389166201000090810262ff000019968716178216929092178087553460018089019182558054600160a060020a031660009081526005602052604090208054938816151560ff1990941693909317808455895487900488168702941693909317808355885485900490961690930294909516939093178455548383015560028085018054869594610667948685019491831615026000190190911604610c95565b50506003805460ff8088169182900390925560015460408051600160a060020a039092168252602082019290925291881682820152517f64a6cdb9816f7d15a3e26b2fcd226b68a8b47e548cd046d211ad5b5d85ae9f2b92509081900360600190a15050505050565b60035490565b600154600160a060020a031681565b600160a060020a031660009081526005602052604090205460ff1690565b600054600160a060020a03163314610765576040805160e560020a62461bcd02815260206004820152601760248201527f4f6e6c792073656c6c65722063616e20646f2074686973000000000000000000604482015290519081900360640190fd5b6004805460ff1916911515919091179055565b600054600160a060020a031633146107da576040805160e560020a62461bcd02815260206004820152601760248201527f4f6e6c792073656c6c65722063616e20646f2074686973000000000000000000604482015290519081900360640190fd5b6004805460ff1916905560008054604051600160a060020a0390911691303180156108fc02929091818181858888f1935050505015801561081f573d6000803e3d6000fd5b5060005460408051600160a060020a0390921682523031602083015280517f9e7601cbe0a416d212307faaff448ec2c033cfc55a2ae13f01cc1f8aca27643a9281900390910190a1565b610871610d0a565b3360009081526005602052604090205460ff1615156108da576040805160e560020a62461bcd02815260206004820152601960248201527f4f6e6c7920637573746f6d65722063616e20646f207468697300000000000000604482015290519081900360640190fd5b600180543373ffffffffffffffffffffffffffffffffffffffff1990911617808255600160a060020a0316600090815260056020908152604091829020825160a081018452815460ff80821615158352610100808304821684870152620100009092041682860152828601546060830152600280840180548751988116159093026000190190921604601f81018590048502870185019095528486529094919360808601938301828280156109d05780601f106109a5576101008083540402835291602001916109d0565b820191906000526020600020905b8154815290600101906020018083116109b357829003601f168201915b5050509190925250506001546060830151604051939450600160a060020a039091169281156108fc0292506000818181858888f19350505050158015610a1a573d6000803e3d6000fd5b506040818101516003805460ff909216909101905560018054600160a060020a031660009081526005602052918220805462ffffff1916815590810182905590610a676002830182610d42565b505050565b600054600160a060020a031690565b606080600080610a89610d0a565b600160a060020a038616600090815260056020908152604091829020825160a081018452815460ff808216151583526101008083048216848701526201000090920416828601526001808401546060840152600280850180548851938116159094026000190190931604601f810186900486028201860190965285815291949293608086019390830182828015610b615780601f10610b3657610100808354040283529160200191610b61565b820191906000526020600020905b815481529060010190602001808311610b4457829003601f168201915b5050509190925250505060408082015160808301516002805484516020601f60001961010060018616150201909316849004928301819004810282018101909652818152929a509296509394509291830182828015610c015780601f10610bd657610100808354040283529160200191610c01565b820191906000526020600020905b815481529060010190602001808311610be457829003601f168201915b5050505050935080602001519250509193509193565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610c585782800160ff19823516178555610c85565b82800160010185558215610c85579182015b82811115610c85578235825591602001919060010190610c6a565b50610c91929150610d89565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610cce5780548555610c85565b82800160010185558215610c8557600052602060002091601f016020900482015b82811115610c85578254825591600101919060010190610cef565b60a060405190810160405280600015158152602001600060ff168152602001600060ff16815260200160008152602001606081525090565b50805460018160011615610100020316600290046000825580601f10610d685750610d86565b601f016020900490600052602060002090810190610d869190610d89565b50565b61040f91905b80821115610c915760008155600101610d8f5600a165627a7a7230582095b21b4df2fabc85eae87ad425d4a43a2398d97f9931a0e51c44b52fc4180e560029";

    public static final String FUNC_SELLER = "seller";

    public static final String FUNC_GETEVENTID = "geteventId";

    public static final String FUNC_BUYFISRTTIME = "buyFisrtTime";

    public static final String FUNC_GETTOTALTICKET = "gettotalTicket";

    public static final String FUNC_BUYER = "buyer";

    public static final String FUNC_ISATTENDED = "isAttended";

    public static final String FUNC_UPDATEEVENTSTATUS = "updateEventStatus";

    public static final String FUNC_KILLEVENT = "killEvent";

    public static final String FUNC_REQUESTREFUND = "requestRefund";

    public static final String FUNC_GETSELLER = "getSeller";

    public static final String FUNC_CUSTOMERDETAIL = "customerDetail";

    public static final Event BUY_EVENT = new Event("Buy", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event WITHDRAWSUCCESSFULLY_EVENT = new Event("WithdrawSuccessfully", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected TicketContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TicketContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TicketContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TicketContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<String> seller() {
        final Function function = new Function(FUNC_SELLER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> geteventId() {
        final Function function = new Function(FUNC_GETEVENTID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> buyFisrtTime(BigInteger _ticketId, BigInteger _amount, String _customerId, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_BUYFISRTTIME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(_ticketId), 
                new org.web3j.abi.datatypes.generated.Uint8(_amount), 
                new org.web3j.abi.datatypes.Utf8String(_customerId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<BigInteger> gettotalTicket() {
        final Function function = new Function(FUNC_GETTOTALTICKET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> buyer() {
        final Function function = new Function(FUNC_BUYER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Boolean> isAttended(String customerAddress) {
        final Function function = new Function(FUNC_ISATTENDED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(customerAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> updateEventStatus(Boolean b) {
        final Function function = new Function(
                FUNC_UPDATEEVENTSTATUS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Bool(b)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> killEvent() {
        final Function function = new Function(
                FUNC_KILLEVENT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> requestRefund() {
        final Function function = new Function(
                FUNC_REQUESTREFUND, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> getSeller() {
        final Function function = new Function(FUNC_GETSELLER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Tuple4<String, String, BigInteger, BigInteger>> customerDetail(String addr) {
        final Function function = new Function(FUNC_CUSTOMERDETAIL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(addr)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint8>() {}));
        return new RemoteCall<Tuple4<String, String, BigInteger, BigInteger>>(
                new Callable<Tuple4<String, String, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple4<String, String, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<String, String, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    public List<BuyEventResponse> getBuyEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(BUY_EVENT, transactionReceipt);
        ArrayList<BuyEventResponse> responses = new ArrayList<BuyEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            BuyEventResponse typedResponse = new BuyEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._customer = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._numsTicket = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._ticketId = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<BuyEventResponse> buyEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, BuyEventResponse>() {
            @Override
            public BuyEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(BUY_EVENT, log);
                BuyEventResponse typedResponse = new BuyEventResponse();
                typedResponse.log = log;
                typedResponse._customer = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._numsTicket = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._ticketId = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<BuyEventResponse> buyEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BUY_EVENT));
        return buyEventFlowable(filter);
    }

    public List<WithdrawSuccessfullyEventResponse> getWithdrawSuccessfullyEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(WITHDRAWSUCCESSFULLY_EVENT, transactionReceipt);
        ArrayList<WithdrawSuccessfullyEventResponse> responses = new ArrayList<WithdrawSuccessfullyEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            WithdrawSuccessfullyEventResponse typedResponse = new WithdrawSuccessfullyEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._to = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<WithdrawSuccessfullyEventResponse> withdrawSuccessfullyEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, WithdrawSuccessfullyEventResponse>() {
            @Override
            public WithdrawSuccessfullyEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(WITHDRAWSUCCESSFULLY_EVENT, log);
                WithdrawSuccessfullyEventResponse typedResponse = new WithdrawSuccessfullyEventResponse();
                typedResponse.log = log;
                typedResponse._to = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<WithdrawSuccessfullyEventResponse> withdrawSuccessfullyEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(WITHDRAWSUCCESSFULLY_EVENT));
        return withdrawSuccessfullyEventFlowable(filter);
    }

    @Deprecated
    public static TicketContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TicketContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TicketContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TicketContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TicketContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TicketContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TicketContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TicketContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TicketContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger _totalTicket, String _eventId) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_totalTicket), 
                new org.web3j.abi.datatypes.Utf8String(_eventId)));
        return deployRemoteCall(TicketContract.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<TicketContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger _totalTicket, String _eventId) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_totalTicket), 
                new org.web3j.abi.datatypes.Utf8String(_eventId)));
        return deployRemoteCall(TicketContract.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TicketContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger _totalTicket, String _eventId) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_totalTicket), 
                new org.web3j.abi.datatypes.Utf8String(_eventId)));
        return deployRemoteCall(TicketContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TicketContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger _totalTicket, String _eventId) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_totalTicket), 
                new org.web3j.abi.datatypes.Utf8String(_eventId)));
        return deployRemoteCall(TicketContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class BuyEventResponse {
        public Log log;

        public String _customer;

        public BigInteger _numsTicket;

        public BigInteger _ticketId;
    }

    public static class WithdrawSuccessfullyEventResponse {
        public Log log;

        public String _to;

        public BigInteger _amount;
    }
}
