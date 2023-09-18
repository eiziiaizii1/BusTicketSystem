package BusTicketSystem;

class Ticket {

    protected final Passenger ticketOwner;
    protected final double ticketPrice = 99.0;

    private Ticket() {
        this.ticketOwner = null;
    }

    //We want tickets to only be created from selling a seat
    protected Ticket(Passenger ticketOwner) {
        this.ticketOwner = ticketOwner;
    }

    @Override
    public String toString() {
        return ticketOwner.toString() + "\nTicket price is " + ticketPrice + "TL";
    }
}
