package BusTicketSystem;

public class BusB extends Bus {

    private BusB() {
        super();
    }

    public BusB(String plate, int numberOfRows, int rowOfDoor) {
        super(plate, numberOfRows, 4, rowOfDoor, 2);
        this.busType = "Type B";
    }

    @Override
    public int getNumberOfFreeSeats() {
        int numberOfFreeSeats = 0;
        //Check each seat to see if it is empty, ignoring null seats
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < SEATS_PER_ROW; j++) {
                if (seats[i][j] != null && !seats[i][j].hasTicket()) {
                    numberOfFreeSeats++;
                }
            }
        }
        return numberOfFreeSeats;
    }

    @Override
    public int getNumberOfRows() {
        return NUMBER_OF_ROWS;
    }

    private void sellSeat(int seatRow, int seatColumn, Passenger pas) {
        if (pas == null) {
            System.out.println("Cannot sell a ticket to NULL passenger!");
            return;
        }
        System.out.println("Ticket sold successfully. Seat number is: " + (seats[seatRow][seatColumn].seatNumber));
        seats[seatRow][seatColumn].setTicket(new Ticket(pas));
    }

    @Override
    public void sellSeat(double rowMultiplier, Passenger pas) {
        if (pas == null) {
            System.out.println("Cannot sell a ticket to NULL passenger!");
            return;
        }
        if (rowMultiplier < 0 || rowMultiplier > 1) {
            System.out.println("Invalid row number entered.");
            return;
        }
        //calculates the row using rowMultiplier
        int row = ((rowMultiplier == 0) ? 0 : ((int) Math.ceil(rowMultiplier * this.NUMBER_OF_ROWS) - 1));
        int column = getSeatColumn(row, pas);
        //tries to sell within the given row
        if (column >= 0) {
            sellSeat(row, column, pas);
            return;
        }
        //if not sold at the inital row we check up and down using two pointers
        int L = row - 1;
        int R = row + 1;
        //we keep trying to seat the passenger until both the
        //left and right pointers are out of bounds
        //which means there are no more rows to check
        while (L >= 0 || R < NUMBER_OF_ROWS) {
            if (L >= 0) {
                column = getSeatColumn(L, pas);
                if (column >= 0) {
                    sellSeat(L, column, pas);
                    return;
                } else {
                    L--;
                }
            }
            if (R < NUMBER_OF_ROWS) {
                column = getSeatColumn(R, pas);
                if (column >= 0) {
                    sellSeat(R, column, pas);
                    return;
                } else {
                    R++;
                }
            }
        }

    }

    private int getSeatColumn(int row, Passenger pas) {
        if (pas == null) {
            System.out.println("Cannot sell a ticket to NULL passenger!");
            return -1;
        }

        if (row < 0 || row >= NUMBER_OF_ROWS) {
            //Return -2 if the row is out of bounds
            return -2;
        }

        //the row's length may change if there is a door 
        int rowLength = ((row == this.ROW_OF_DOOR - 1) ? SEATS_PER_ROW - (SEATS_PER_ROW - SEATS_ON_LEFT_SIDE) : SEATS_PER_ROW);

        //Searches for a valid column in the row until it reaches the end of the row
        for (int c = 0; c < rowLength; c++) {
            if (canSitAt(row, c, pas)) {
                return c;
            }
        }
        //Return -1 if a valid column was not found
        return -1;
    }

    @Override
    public void sellMultipleSeats(double rowMultiplier, Passenger[] pas) {
        if (rowMultiplier < 0 || rowMultiplier > 1) {
            System.out.println("Invalid row number entered.");
            return;
        }
        int row = ((rowMultiplier == 0) ? 0 : ((int) Math.ceil(rowMultiplier * this.NUMBER_OF_ROWS) - 1));
        //we try to sell seats within the initial row
        int pasIndex = sellRow(0, row, pas);

        //if there are any remaining passengers we check
        //above and below the initial row and try to sell
        //seats until there are no more passengers to seat
        //or when both L and R are out of bounds
        //When L and R are out of bounds there are no more
        //rows left to check
        int L = row - 1;
        int R = row + 1;
        while (pasIndex >= 0 && pasIndex < pas.length) {

            if (L >= 0) {
                pasIndex = sellRow(pasIndex, L, pas);
                L--;
            }
            if (R < NUMBER_OF_ROWS) {
                pasIndex = sellRow(pasIndex, R, pas);
                R++;
            }
            if (L < 0 && R >= NUMBER_OF_ROWS) {
                break;
            }

        }

    }

    private int sellRow(int pasIndex, int row, Passenger[] pas) {
        if (pas.length < 1 || pasIndex >= pas.length || pasIndex < 0) {
            return -1;
            //returns -1 if pas is not within range
        }
        int rowLength = ((row == this.ROW_OF_DOOR - 1) ? SEATS_PER_ROW - (SEATS_PER_ROW - SEATS_ON_LEFT_SIDE) : SEATS_PER_ROW);
        for (int column = 0; column < rowLength; column++) {
            //looks for a valid spot to start filling
            if (canSitAt(row, column, pas[pasIndex])) {
                sellSeat(row, column, pas[pasIndex++]);
                //fills every empty seat after the first successful seating until it reaches the end
                //or if it encounters another passenger / runs out of passengers to seat
                for (int subColumn = column + 1; subColumn < rowLength; subColumn++) {

                    if (pasIndex >= pas.length) {
                        break;
                    }
                    if (!seats[row][subColumn].hasTicket() && checkNextSeat(row, subColumn, pas[pasIndex])) {
                        sellSeat(row, subColumn, pas[pasIndex++]);
                    } else {
                        //System.out.println("failed to seat passenger at " + seats[row][subColumn]);
                        break;
                    }

                }
                break;
            }
        }
        //returns -2 if we completed sitting all passengers
        //returns current pasIndex if we have not
        return (pasIndex >= pas.length ? -2 : pasIndex);
    }

    private boolean checkNextSeat(int row, int column, Passenger pas) {
        column++;
        if (column == SEATS_PER_ROW || seats[row][column] == null) {
            //returns true if the next seat is at the end of the row 
            //or if the next seat is a door (null)
            return true;
        }
        if (column < SEATS_ON_LEFT_SIDE) { //if the seat is on the left side of the row
            if (SEATS_ON_LEFT_SIDE == 1) {
                return true;
                //the seat has no neighbors, return true as anyone can sit here
            }
            if (column >= 0 && column < SEATS_ON_LEFT_SIDE - 1) {
                return Seat.hasSameGender(pas, seats[row][column]);
                //check if the passenger to the right of the desired seat has the same gender
            }
            if (column == SEATS_ON_LEFT_SIDE - 1) {
                return true;
                //true as there is nobody to compare to on the right
            }
        } else {
            if (SEATS_PER_ROW - SEATS_ON_LEFT_SIDE == 1) {
                return true;
                //the seat has no neighbors, return true as anyone can sit here
            }
            if (column >= SEATS_ON_LEFT_SIDE && column < SEATS_PER_ROW - 1) {
                return Seat.hasSameGender(pas, seats[row][column]);
                //Check genders of the passenger to the right of the desired seat
            }

            if (column == SEATS_PER_ROW - 1) {
                return true;
                //true as there is nobody to compare to on the right
            }
        }
        return false;
        //fallback case that returns false
    }

    private boolean canSitAt(int seatRow, int seatColumn, Passenger pas) {
        if (seats[seatRow][seatColumn].hasTicket()) {
            return false;
            //false, passengers cannot sit at empty seats
        }
        if (seatColumn < SEATS_ON_LEFT_SIDE) { //if the seat is on the left side of the row
            if (SEATS_ON_LEFT_SIDE == 1) {
                return true;
                //the seat has no neighbors, return true as anyone can sit here
            }
            if (seatColumn > 0 && seatColumn < SEATS_ON_LEFT_SIDE - 1) {
                return Seat.hasSameGender(pas, seats[seatRow][seatColumn + 1])
                        && Seat.hasSameGender(pas, seats[seatRow][seatColumn - 1]);
                //Check genders of the passenger to the right and left of the desired seat
            }
            if (seatColumn == 0) {
                return Seat.hasSameGender(pas, seats[seatRow][seatColumn + 1]);
                //check if the passenger to the right of the desired seat has the same gender
            }
            if (seatColumn == SEATS_ON_LEFT_SIDE - 1) {
                return Seat.hasSameGender(pas, seats[seatRow][seatColumn - 1]);
                //Check if the passenger to the left of the desired seat has the same gender
                //as the right side belongs to the right row
            }
        } else {
            if (SEATS_PER_ROW - SEATS_ON_LEFT_SIDE == 1) {
                return true;
                //the seat has no neighbors, return true as anyone can sit here
            }
            if (seatColumn > SEATS_ON_LEFT_SIDE && seatColumn < SEATS_PER_ROW - 1) {
                return Seat.hasSameGender(pas, seats[seatRow][seatColumn + 1])
                        && Seat.hasSameGender(pas, seats[seatRow][seatColumn - 1]);
                //Check genders of the passenger to the right and left of the desired seat
            }
            if (seatColumn == SEATS_ON_LEFT_SIDE) {
                return Seat.hasSameGender(pas, seats[seatRow][seatColumn + 1]);
                //check if the passenger to the right of the desired seat has the same gender
                //as the left side belongs to the left row
            }
            if (seatColumn == SEATS_PER_ROW - 1) {
                return Seat.hasSameGender(pas, seats[seatRow][seatColumn - 1]);
                //Check if the passenger to the left of the desired seat has the same gender
            }
        }
        return false;
        //fallback case that returns false
    }

    @Override
    public void makeSeatFree(int seatNumber) {
        //checks if the seatNumber received is valid
        if (seatNumber <= NUMBER_OF_SEATS && seatNumber > 0) {
            if (seatNumber > (ROW_OF_DOOR - 1) * SEATS_PER_ROW + SEATS_ON_LEFT_SIDE) {
                //while converting into a row and column index add the number of seats skipped
                //if the seatNumber entered is after the door
                seatNumber += (SEATS_PER_ROW - SEATS_ON_LEFT_SIDE);
            }
            //converted seatNumber into row and column indexes
            int seatRow = (seatNumber - 1) / SEATS_PER_ROW;
            int seatColumn = (seatNumber - 1) % SEATS_PER_ROW;
            seats[seatRow][seatColumn].setTicket(null);

        } else {
            System.out.println("Invalid seat number entered");
        }
    }

    @Override
    public void makeMultipleSeatsFree(int seatNumberStart, int seatNumberEnd) {
        //emptys each seat including the starting and ending seat
        if (seatNumberStart > 0 && seatNumberStart < NUMBER_OF_SEATS
                && seatNumberEnd > 1 && seatNumberEnd <= NUMBER_OF_SEATS) {
            for (int i = seatNumberStart; i <= seatNumberEnd; i++) {
                makeSeatFree(i);
            }
        } else {
            System.out.println("Invalid seat number entered!");
        }
    }

    @Override
    public String toString() {
        String layout = "";

        for (int i = seats[0].length - 1; i >= 0; i--) { // column
            for (int j = 0; j < seats.length; j++) { // row
                if (seats[j][i] == null) {
                    layout += "    "; // emptyness representing the door
                    continue;
                }
                if (!seats[j][i].hasTicket()) {
                    //Empty seats show the seatNumber
                    layout += "[" + seats[j][i].seatNumber + "]";
                } else {
                    layout += "[" + ((seats[j][i].getGenderOfSitter() == Gender.Male) ? "M" : "F") + "]";
                    //Shows the gender of the person at the seat
                }

            }
            layout += "\n";
            if (i == SEATS_ON_LEFT_SIDE) {
                layout += "\n";
                // adds an empty row to represent the corridor of the bus
            }
        }
        return layout;
    }

}
