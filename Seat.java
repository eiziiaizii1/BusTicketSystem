package BusTicketSystem;

class Seat {

    private Ticket seatTicket;
    protected final int seatNumber;

    private Seat() {
        seatNumber = -1;
    }
    //Only variants of the Bus class can create seats in this package
    protected Seat(int seatNumber, Ticket ticket) {
        this.seatNumber = seatNumber;
        this.seatTicket = ticket;
    }

    protected Gender getGenderOfSitter() {
        if(seatTicket.ticketOwner==null) {
        	return null;
        }
    	return this.seatTicket.ticketOwner.getGender();
    }

    protected boolean isEmpty() {
        return (this.seatTicket == null);
    }

    protected void setTicket(Ticket ticket) {
        this.seatTicket = ticket;
    }
    protected boolean hasTicket(){
        return this.seatTicket != null;
    }

    protected void removeTicket() {
        this.seatTicket = null;
    }
    
    protected Passenger getOwner() {
    	return seatTicket.ticketOwner;
    }
    

    protected static boolean hasSameGender(Passenger buyerPas, Seat seatToCompare) {
        if (buyerPas != null) {
            if (seatToCompare.seatTicket == null) {
                //Empty seats will be assumed to have the same gender as the passenger
                //in order to allow the passenger to sit 
                return true;
            } else {
                //If a seat is not empty we will compare their genders
                return buyerPas.getGender() == seatToCompare.getGenderOfSitter();
            }
        } else {
            //null passengers are not valid, therefore return false
            return false;
        }
    }

    @Override
    public String toString() {
        return "Reserved by: " + (this.seatTicket == null ? "Nobody" : seatTicket.ticketOwner.toString())
                + "\nSeat Number: " + seatNumber;
    }
}
