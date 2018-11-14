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
    private static final String BINARY = "608060405234801561001057600080fd5b50604051610c17380380610c1783398101604052805160208083015160028390556000805460a060020a60ff0219600160a060020a03199091163317167401000000000000000000000000000000000000000017905590920180519192909161007f9160019190840190610087565b505050610122565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106100c857805160ff19168380011785556100f5565b828001600101855582156100f5579182015b828111156100f55782518255916020019190600101906100da565b50610101929150610105565b5090565b61011f91905b80821115610101576000815560010161010b565b90565b610ae6806101316000396000f3006080604052600436106100985763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416631b55e3ec811461009d5780632b62bafe146101b05780633ccfd60b146101d457806341c0e1b5146101e9578063565697d3146101fe578063a07f3a5614610225578063b2926b2a1461025a578063c11d77cb146102e4578063dbd0e1b6146102fe575b600080fd5b3480156100a957600080fd5b506100be600160a060020a036004351661032f565b6040805160ff80851692820192909252908216606082015260808082528551908201528451819060208083019160a084019189019080838360005b838110156101115781810151838201526020016100f9565b50505050905090810190601f16801561013e5780820380516001836020036101000a031916815260200191505b50838103825286518152865160209182019188019080838360005b83811015610171578181015183820152602001610159565b50505050905090810190601f16801561019e5780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390f35b6101d260ff6004803582169160248035909116916044359182019101356104c8565b005b3480156101e057600080fd5b506101d2610725565b3480156101f557600080fd5b506101d261080c565b34801561020a57600080fd5b5061021361087e565b60408051918252519081900360200190f35b34801561023157600080fd5b50610246600160a060020a0360043516610885565b604080519115158252519081900360200190f35b34801561026657600080fd5b5061026f6108a3565b6040805160208082528351818301528351919283929083019185019080838360005b838110156102a9578181015183820152602001610291565b50505050905090810190601f1680156102d65780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156102f057600080fd5b506101d26004351515610938565b34801561030a57600080fd5b50610313610978565b60408051600160a060020a039092168252519081900360200190f35b60608060008061033d610987565b600160a060020a0386166000908152600360209081526040918290208251608081018452815460ff80821615158352610100808304821684870152620100009092041682860152600180840180548751600293821615909402600019011691909104601f8101869004860283018601909652858252919492936060860193919291908301828280156104105780601f106103e557610100808354040283529160200191610410565b820191906000526020600020905b8154815290600101906020018083116103f357829003601f168201915b5050509190925250505060408082015160608301516001805484516020601f600260001961010086881615020190941693909304928301819004810282018101909652818152929a5092965093945092918301828280156104b25780601f10610487576101008083540402835291602001916104b2565b820191906000526020600020905b81548152906001019060200180831161049557829003601f168201915b5050505050935080602001519250509193509193565b600080600254111515610525576040805160e560020a62461bcd02815260206004820152600860248201527f536f6c64206f7574000000000000000000000000000000000000000000000000604482015290519081900360640190fd5b60005474010000000000000000000000000000000000000000900460ff161515610599576040805160e560020a62461bcd02815260206004820152601960248201527f546865206576656e74206973206e6f74206f70656e6e65642100000000000000604482015290519081900360640190fd5b600054600160a060020a0316331415610622576040805160e560020a62461bcd02815260206004820152602c60248201527f596f75206172652073656c6c65722c2063616e6e6f742065786563757465207460448201527f6869732066756e6374696f6e0000000000000000000000000000000000000000606482015290519081900360840190fd5b50336000908152600360205260409020805460ff161561064157600080fd5b600360ff8516111561065257600080fd5b805460ff19166001908117825561066c90820184846109ad565b50805462ff0000199081166201000060ff87811682029290921761ff00199081166101008a8516810291909117808755336000908152600360205260409020805460ff19169186161515919091178082558754931692829004851682029290921780835586549516948390049093169091029290921782556001808401805485949361070d9385810193926002918116159092026000190190911604610a2b565b50506002805460ff9096169095039094555050505050565b600054600160a060020a03163314610787576040805160e560020a62461bcd02815260206004820152601260248201527f596f7520617265206e6f742073656c6c65720000000000000000000000000000604482015290519081900360640190fd5b60008054604051600160a060020a0390911691303180156108fc02929091818181858888f193505050501580156107c2573d6000803e3d6000fd5b5060005460408051600160a060020a0390921682523031602083015280517f62727d0d8a19740a5ca86d0390916b86992ef5ba0e739743052f9467d60b62e59281900390910190a1565b6000805474ff000000000000000000000000000000000000000019811690915560408051600160a060020a0390921682523031602083015280517f62727d0d8a19740a5ca86d0390916b86992ef5ba0e739743052f9467d60b62e59281900390910190a1600054600160a060020a0316ff5b6002545b90565b600160a060020a031660009081526003602052604090205460ff1690565b60018054604080516020601f6002600019610100878916150201909516949094049384018190048102820181019092528281526060939092909183018282801561092e5780601f106109035761010080835404028352916020019161092e565b820191906000526020600020905b81548152906001019060200180831161091157829003601f168201915b5050505050905090565b60008054911515740100000000000000000000000000000000000000000274ff000000000000000000000000000000000000000019909216919091179055565b600054600160a060020a031690565b604080516080810182526000808252602082018190529181019190915260608082015290565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106109ee5782800160ff19823516178555610a1b565b82800160010185558215610a1b579182015b82811115610a1b578235825591602001919060010190610a00565b50610a27929150610aa0565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610a645780548555610a1b565b82800160010185558215610a1b57600052602060002091601f016020900482015b82811115610a1b578254825591600101919060010190610a85565b61088291905b80821115610a275760008155600101610aa65600a165627a7a723058200befd7d68411fe0e11a8f33cbad62effb20844f6556d8d41b64f6bebdd55d9b70029";

    public static final String FUNC_CHECKCUSTOMERHISTORY = "checkCustomerHistory";

    public static final String FUNC_BUYFISRTTIME = "buyFisrtTime";

    public static final String FUNC_WITHDRAW = "withdraw";

    public static final String FUNC_KILL = "kill";

    public static final String FUNC_GETTOTALTICKET = "gettotalTicket";

    public static final String FUNC_ISATTENDED = "isAttended";

    public static final String FUNC_GETEVENTHASH = "geteventHash";

    public static final String FUNC_UPDATEEVENTSTATUS = "updateEventStatus";

    public static final String FUNC_GETSELLER = "getSeller";

    public static final Event BUY_EVENT = new Event("Buy", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event WITHDRAWSUCCESSFULLY_EVENT = new Event("withdrawSuccessfully", 
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

    public RemoteCall<Tuple4<String, String, BigInteger, BigInteger>> checkCustomerHistory(String addr) {
        final Function function = new Function(FUNC_CHECKCUSTOMERHISTORY, 
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

    public RemoteCall<TransactionReceipt> buyFisrtTime(BigInteger _ticketId, BigInteger _amount, String _customerIdNumber, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_BUYFISRTTIME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(_ticketId), 
                new org.web3j.abi.datatypes.generated.Uint8(_amount), 
                new org.web3j.abi.datatypes.Utf8String(_customerIdNumber)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> withdraw() {
        final Function function = new Function(
                FUNC_WITHDRAW, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> kill() {
        final Function function = new Function(
                FUNC_KILL, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> gettotalTicket() {
        final Function function = new Function(FUNC_GETTOTALTICKET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Boolean> isAttended(String customerAddress) {
        final Function function = new Function(FUNC_ISATTENDED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(customerAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<String> geteventHash() {
        final Function function = new Function(FUNC_GETEVENTHASH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> updateEventStatus(Boolean b) {
        final Function function = new Function(
                FUNC_UPDATEEVENTSTATUS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Bool(b)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> getSeller() {
        final Function function = new Function(FUNC_GETSELLER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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

    public static RemoteCall<TicketContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger _totalTicket, String _eventHash) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_totalTicket), 
                new org.web3j.abi.datatypes.Utf8String(_eventHash)));
        return deployRemoteCall(TicketContract.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<TicketContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger _totalTicket, String _eventHash) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_totalTicket), 
                new org.web3j.abi.datatypes.Utf8String(_eventHash)));
        return deployRemoteCall(TicketContract.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TicketContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger _totalTicket, String _eventHash) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_totalTicket), 
                new org.web3j.abi.datatypes.Utf8String(_eventHash)));
        return deployRemoteCall(TicketContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TicketContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger _totalTicket, String _eventHash) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_totalTicket), 
                new org.web3j.abi.datatypes.Utf8String(_eventHash)));
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
