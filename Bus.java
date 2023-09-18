package BusTicketSystem;

public abstract class Bus {

    protected String busType;
    final String plate;
    protected final Seat[][] seats;
    final int NUMBER_OF_ROWS;
    final int ROW_OF_DOOR;
    public final int SEATS_PER_ROW;
    final int SEATS_ON_LEFT_SIDE;
    public final int NUMBER_OF_SEATS;

//This constructor will be unused
    protected Bus() {
        seats = null;
        SEATS_ON_LEFT_SIDE = -1;
        SEATS_PER_ROW = -1;
        plate = null;
        busType = null;
        ROW_OF_DOOR = -1;
        NUMBER_OF_ROWS = -1;
        NUMBER_OF_SEATS = -1;
    }

    protected Bus(String plate, int NUMBER_OF_ROWS,  int SEATS_PER_ROW , int ROW_OF_DOOR , int SEATS_ON_LEFT_SIDE) {
        this.plate = plate;
        this.SEATS_PER_ROW = SEATS_PER_ROW;
        this.SEATS_ON_LEFT_SIDE = SEATS_ON_LEFT_SIDE;        
        if (NUMBER_OF_ROWS < 1) {
            System.out.println("An invalid number was entered for the number of rows, a default value of 12 will be assigned");
            this.NUMBER_OF_ROWS = 12;
        } else {
            this.NUMBER_OF_ROWS = NUMBER_OF_ROWS;
        }

        if (ROW_OF_DOOR < 1 || ROW_OF_DOOR > NUMBER_OF_ROWS) {
            System.out.println("An invalid value for the row number of the door was entered");
            if (NUMBER_OF_ROWS == 1) {
                this.ROW_OF_DOOR = NUMBER_OF_ROWS;
            } else {
                this.ROW_OF_DOOR = NUMBER_OF_ROWS / 2;
            }

        } else {
            this.ROW_OF_DOOR = ROW_OF_DOOR;
        }
        this.seats = new Seat[this.NUMBER_OF_ROWS][this.SEATS_PER_ROW];
        this.NUMBER_OF_SEATS = this.NUMBER_OF_ROWS * this.SEATS_PER_ROW - (this.SEATS_PER_ROW - this.SEATS_ON_LEFT_SIDE);
        createBusLayout();
        
    }
        private final void createBusLayout() {
        int seatNumber = 1;
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (i == ROW_OF_DOOR - 1 && j == SEATS_ON_LEFT_SIDE) {
                    //When we reach the area where the door will be, we will skip to the next row
                    break;
                } else {
                    //Creates an empty seat with the current seat number
                    seats[i][j] = new Seat(seatNumber++, null);
                }
            }
        }

    }

    public String getBusInfo() {
        return "Bus Type: " + busType + "\n" + "Bus Plate: " + plate;
    }

    public abstract int getNumberOfFreeSeats();
    
    public abstract int getNumberOfRows();
    
    public abstract void sellSeat(double row, Passenger pas);

    public abstract void sellMultipleSeats(double rowMultiplier, Passenger[] pas);

    public abstract void makeSeatFree(int seatNumber);
    
    public abstract void makeMultipleSeatsFree(int seatNumberStart,int seatNumberEnd);
    
}
