package com.aqryuz.footballTicketDemo.model;

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

import io.reactivex.Flowable;

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
    private static final String BINARY = "608060405234801561001057600080fd5b50604051602080610eec83398101604052516000805475010000000000000000000000000000000000000000003360a060020a60ff02199092167401000000000000000000000000000000000000000060ff86160217600160a060020a0319169190911760a860020a60ff021916179055610092640100000000610098810204565b50610127565b6000806100a3610110565b600092505b60038360ff16101561010b57505060408051808201825260ff83811680835267016345785d8a0000600195860180841682026020808701918252600094855260029052959092208451815460ff19169416939093178355935191909401556100a8565b505050565b604080518082019091526000808252602082015290565b610db6806101366000396000f3006080604052600436106100a35763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416631b55e3ec81146100a85780632b62bafe146101625780632e882e62146101865780633ccfd60b1461019b57806341c0e1b5146101b0578063565697d3146101c5578063a07f3a56146101ec578063d5cef13314610221578063dbd0e1b614610236578063df284c0b14610267575b600080fd5b3480156100b457600080fd5b506100c9600160a060020a0360043516610282565b60405180806020018560ff1660ff1681526020018460ff1660ff168152602001838152602001828103825286818151815260200191508051906020019080838360005b8381101561012457818101518382015260200161010c565b50505050905090810190601f1680156101515780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b61018460ff6004803582169160248035909116916044359182019101356103c5565b005b34801561019257600080fd5b50610184610753565b3480156101a757600080fd5b506101846107cb565b3480156101bc57600080fd5b506101846108b2565b3480156101d157600080fd5b506101da610925565b60408051918252519081900360200190f35b3480156101f857600080fd5b5061020d600160a060020a0360043516610947565b604080519115158252519081900360200190f35b34801561022d57600080fd5b50610184610969565b34801561024257600080fd5b5061024b610bb6565b60408051600160a060020a039092168252519081900360200190f35b34801561027357600080fd5b506101da60ff60043516610bc5565b60606000806000610291610bdd565b600160a060020a0386166000908152600160208181526040928390208351815460029481161561010002600019011693909304601f8101839004909202830160e090810190945260c08301828152929390928492909184919084018282801561033b5780601f106103105761010080835404028352916020019161033b565b820191906000526020600020905b81548152906001019060200180831161031e57829003601f168201915b5050509183525050600182015460ff8082161515602080850191909152610100909204811660408085019190915280518082018252600286015490921682526003850154928201929092526060808401919091526004840154608084015260059093015460a092830152835192840151519084015193909101519199909850919650945092505050565b6103cd610c1f565b6000805474010000000000000000000000000000000000000000900460ff168110610442576040805160e560020a62461bcd02815260206004820152600860248201527f536f6c64206f7574000000000000000000000000000000000000000000000000604482015290519081900360640190fd5b6000547501000000000000000000000000000000000000000000900460ff1615156104b7576040805160e560020a62461bcd02815260206004820152601960248201527f546865206576656e74206973206e6f74206f70656e6e65642100000000000000604482015290519081900360640190fd5b600054600160a060020a0316331415610540576040805160e560020a62461bcd02815260206004820152602c60248201527f596f75206172652073656c6c65722c2063616e6e6f742065786563757465207460448201527f6869732066756e6374696f6e0000000000000000000000000000000000000000606482015290519081900360840190fd5b505060ff80851660009081526002602090815260408083208151808301835281548616815260019182015481850152338552928190529220918201549092161561058957600080fd5b610594818585610c36565b506001818101805460208581015160ff8a8116808302600489015588516002808a01805460ff19908116939095169290921790915560038901939093559316851761ff001916610100938402179093554260058601553360009081529084905260409020845485949193610618938593879390811615909102600019011604610cb4565b5060018281018054918301805460ff938416151560ff199182161780835592546101009081900485160261ff00199093169290921790556002808501549084018054909216908316179055600380840154908301556004808401549083015560059283015492909101919091556000805474010000000000000000000000000000000000000000808204841689900384160274ff00000000000000000000000000000000000000001990911617815560208401516040513393891691909102340380156108fc0292909190818181858888f19350505050158015610700573d6000803e3d6000fd5b506020808301516040805133815260ff89169381019390935282810191909152517f64a6cdb9816f7d15a3e26b2fcd226b68a8b47e548cd046d211ad5b5d85ae9f2b9181900360600190a1505050505050565b60008061075e610c1f565b600092505b60038360ff1610156107c657505060408051808201825260ff83811680835267016345785d8a0000600195860180841682026020808701918252600094855260029052959092208451815460ff1916941693909317835593519190940155610763565b505050565b600054600160a060020a0316331461082d576040805160e560020a62461bcd02815260206004820152601260248201527f596f7520617265206e6f742073656c6c65720000000000000000000000000000604482015290519081900360640190fd5b60008054604051600160a060020a0390911691303180156108fc02929091818181858888f19350505050158015610868573d6000803e3d6000fd5b5060005460408051600160a060020a0390921682523031602083015280517f62727d0d8a19740a5ca86d0390916b86992ef5ba0e739743052f9467d60b62e59281900390910190a1565b6000805475ff00000000000000000000000000000000000000000019811690915560408051600160a060020a0390921682523031602083015280517f62727d0d8a19740a5ca86d0390916b86992ef5ba0e739743052f9467d60b62e59281900390910190a1600054600160a060020a0316ff5b60005474010000000000000000000000000000000000000000900460ff165b90565b600160a060020a03166000908152600160208190526040909120015460ff1690565b610971610bdd565b336000908152600160208190526040909120015460ff1615156109de576040805160e560020a62461bcd02815260206004820152601660248201527f596f7520617265206e6f74206120617474656e64656500000000000000000000604482015290519081900360640190fd5b336000908152600160208181526040928390208351815460029481161561010002600019011693909304601f8101839004909202830160e090810190945260c083018281529293909284929091849190840182828015610a7f5780601f10610a5457610100808354040283529160200191610a7f565b820191906000526020600020905b815481529060010190602001808311610a6257829003601f168201915b5050509183525050600182015460ff8082161515602080850191909152610100909204811660408085019190915280518082018252600286015490921682526003850154928201929092526060830152600483015460808084019190915260059093015460a090920191909152908201519051919250339181156108fc0291906000818181858888f19350505050158015610b1e573d6000803e3d6000fd5b506040808201516000805460ff7401000000000000000000000000000000000000000080830482169094011690920274ff000000000000000000000000000000000000000019909216919091178155338152600160205290812090610b838282610d29565b5060018101805461ffff1916905560028101805460ff191690556000600382018190556004820181905560059091015550565b600054600160a060020a031690565b60ff1660009081526002602052604090206001015490565b60e06040519081016040528060608152602001600015158152602001600060ff168152602001610c0b610c1f565b815260200160008152602001600081525090565b604080518082019091526000808252602082015290565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610c775782800160ff19823516178555610ca4565b82800160010185558215610ca4579182015b82811115610ca4578235825591602001919060010190610c89565b50610cb0929150610d70565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610ced5780548555610ca4565b82800160010185558215610ca457600052602060002091601f016020900482015b82811115610ca4578254825591600101919060010190610d0e565b50805460018160011615610100020316600290046000825580601f10610d4f5750610d6d565b601f016020900490600052602060002090810190610d6d9190610d70565b50565b61094491905b80821115610cb05760008155600101610d765600a165627a7a72305820600066bad4a4b7941a54cf05c1e85c815fcf0a5835f3a1eeb6b9f77e427671860029";

    public static final String FUNC_CHECKCUSTOMERHISTORY = "checkCustomerHistory";

    public static final String FUNC_BUYFISRTTIME = "buyFisrtTime";

    public static final String FUNC_CREATETICKETS = "createTickets";

    public static final String FUNC_WITHDRAW = "withdraw";

    public static final String FUNC_KILL = "kill";

    public static final String FUNC_GETTOTALTICKET = "gettotalTicket";

    public static final String FUNC_ISATTENDED = "isAttended";

    public static final String FUNC_REQUESTREFUND = "requestRefund";

    public static final String FUNC_GETSELLER = "getSeller";

    public static final String FUNC_GETTICKETINFO = "getTicketInfo";

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

    public RemoteCall<Tuple4<String, BigInteger, BigInteger, BigInteger>> checkCustomerHistory(String addr) {
        final Function function = new Function(FUNC_CHECKCUSTOMERHISTORY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(addr)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple4<String, BigInteger, BigInteger, BigInteger>>(
                new Callable<Tuple4<String, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple4<String, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<String, BigInteger, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue());
                    }
                });
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

    public RemoteCall<TransactionReceipt> createTickets() {
        final Function function = new Function(
                FUNC_CREATETICKETS, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    public RemoteCall<BigInteger> getTicketInfo(BigInteger _id) {
        final Function function = new Function(FUNC_GETTICKETINFO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(_id)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
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

    public static RemoteCall<TicketContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger _totalTicket) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(_totalTicket)));
        return deployRemoteCall(TicketContract.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<TicketContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger _totalTicket) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(_totalTicket)));
        return deployRemoteCall(TicketContract.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TicketContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger _totalTicket) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(_totalTicket)));
        return deployRemoteCall(TicketContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<TicketContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger _totalTicket) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(_totalTicket)));
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
