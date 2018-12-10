pragma solidity ^0.4.25;

contract TicketContract{
    address private seller;
    uint8 totalTicket;
    bool eventAvailable;
    
    struct Ticket{
        uint8 id;
        uint price;
    }
    
    struct Customer{
        string id;
        bool isAttended;
        uint8 ticketsBought;
        Ticket ticket;
        uint fund;
        uint timestamp;
    }
    
    mapping(address => Customer)  customerMap; 
    mapping(uint8 => Ticket)  ticketMap;
    
    constructor (uint8 _totalTicket) public {
        totalTicket = _totalTicket;
        seller = msg.sender;
        eventAvailable = true;
        createTickets();
    }
    
    function createTickets() public{
        for(uint8 i = 0; i < 3; i++){
            uint price = 0.1 ether;
            Ticket memory ticket = Ticket(i, price * (i + 1));
            ticketMap[i] = ticket;
        }
    }
    
    
//Get Set part:
//GET
    function getSeller() public view returns(address) {
        return seller;
    }
    
     function gettotalTicket() public view returns(uint) {
        return totalTicket;
    }
    
    function isAttended(address customerAddress) public view returns(bool){
        return customerMap[customerAddress].isAttended;
    }
    
    function getTicketInfo(uint8 _id) public view returns(uint){
        return ticketMap[_id].price; 
    }
    
     function checkCustomerHistory(address addr) public view returns(string, uint8, uint8, uint) {
        Customer memory c = customerMap[addr];
        return(c.id, c.ticket.id, c.ticketsBought, c.timestamp);
    }
    /*
    function getCustomerHistory(address _customer) public returns(uint[] ticketId){
        for (uint8 i = 0; i < customerMap.length; i++) {
            Ticket storage ticket = customerMap[_customer][i];
            ticketId[i] = ticket.id;
        }
    }*/
    
//Checking part:
    //Check if msg.sender is seller or not
    modifier OnlySeller () {
        require(msg.sender == seller,"You are not seller");
        _;
    }
    modifier NotSeller () {
        require(msg.sender != seller,"You are seller, cannot execute this function");
        _;
    }
    
    modifier OnlyCustomer(){
        require(customerMap[msg.sender].isAttended,"You are not a attendee");
        _;
    }
    
    //Check if there are available ticket
    modifier WhenTicketAvailable() {
        require(totalTicket > 0, "Sold out");
        _;
    }

    //Check if the contract is closed    
    modifier WhenEventAvailable(){
        require(eventAvailable, "The event is not openned!");
        _;
    }
    
    modifier ValidAddress(){
        require(msg.sender != 0x0);
        _;
    }
    
    event Buy(address _customer, uint8 _numsTicket, uint _ticketId);
    event withdrawSuccessfully(address _to, uint _amount);


    function buyFisrtTime (uint8 _ticketId, uint8 _amount, string _customerId) external payable WhenTicketAvailable WhenEventAvailable NotSeller{
        Ticket memory ticket = ticketMap[_ticketId];
        Customer storage newCustomer = customerMap[msg.sender];
        require(!newCustomer.isAttended);
        //update customerMap
        
        //customerMap[msg.sender].tickets.push(ticket);
        newCustomer.id = _customerId;
        newCustomer.isAttended = true;
        newCustomer.fund = ticket.price * _amount;
        newCustomer.ticket = ticket;
        newCustomer.ticketsBought = _amount;
        newCustomer.timestamp = now;
        
        customerMap[msg.sender] = newCustomer;
        
        //update global variable
        totalTicket-= _amount;
        
        //Refund 
        msg.sender.transfer(msg.value - ticket.price * _amount);
    
        emit Buy(msg.sender, _amount, ticket.price);
    }
    
    
    
    function requestRefund () external OnlyCustomer{
        Customer memory c = customerMap[msg.sender];
        msg.sender.transfer(c.fund);
        totalTicket+= c.ticketsBought;
        delete customerMap[msg.sender];
    }
    
    
    function withdraw() public OnlySeller{
        seller.transfer(address(this).balance);
        emit withdrawSuccessfully(seller, address(this).balance);
    }
    
    //Withdraw V2.0 :v
    function kill() public{
        eventAvailable = false;
        emit withdrawSuccessfully(seller, address(this).balance);
        selfdestruct(seller);
    }
}
