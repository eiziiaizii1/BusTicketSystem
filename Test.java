import java.util.Iterator;

import BusTicketSystem.*;

public class Test {
	// PASSENGER CREATION
	static Passenger m = new Passenger("Ahmet", "Kaya", Gender.Male);
	static Passenger f = new Passenger("Ayse", "Sungur", Gender.Female);
	static Passenger nullPass1 = null;
	static Passenger nullPass2 = new Passenger(null, null, null);


	public static void main(String[] args) {

		// Passenger TESTS
		System.out.println("\nPASSENGER toString TESTS "
				+ "\n-----------------------------------------------------------------------");
		System.out.println(m);
		System.out.println(f);
		System.out.println(nullPass1);
		System.out.println(nullPass2);

		System.out.println();

		System.out.println(
				"\nPASSENGER TESTS " + "\n-----------------------------------------------------------------------");

		System.out.println("m == f :" + m.equals(f));
		System.out.println("m == m :" + m.equals(m));
		System.out.println(
				"Ahmet Kaya, Male == Ahmet Kaya, Male :" + m.equals(new Passenger("Ahmet", "Kaya", Gender.Male)));

		System.out.println();

		// BUS CREATION
		Bus busA = new BusA("06 ABC 123", 14, 8);
		System.out.println("BusA created: \n" + busA);
		Bus busB = new BusB("07 DEF 456", 12, 6);
		System.out.println("BusB created: \n" + busB);
		Bus busC = new BusC("08 GHI 789", 10, 5);
		System.out.println("BusC created: \n" + busC);

		
		// TESTS FOR BUS-A
		System.out.println("\nSINGLE PASSENGER SELL SEAT METHOD TESTS (BUS-A)"
				+ "\n-----------------------------------------------------------------------");
		sellSeatTest(busA);

		
		busA.makeMultipleSeatsFree(1, busA.NUMBER_OF_SEATS);

		System.out.println("\nPASSENGER ARRAY SELL SEAT METHOD TESTS (BUS-A)"
				+ "\n-----------------------------------------------------------------------");
		sellSeatArrayTest(busA);

		
		System.out.println("\nMAKE SEAT FREE METHODS TESTS (BUS-A)"
				+ "\n-----------------------------------------------------------------------");
		makeSeatFreeTest(busA);

		// TESTS FOR BUS-B
		/////////////////////////////////////////////////////////////
		System.out.println("\nSINGLE PASSENGER SELL SEAT METHOD TESTS (BUS-B)"
				+ "\n-----------------------------------------------------------------------");
		sellSeatTest(busB);

		busB.makeMultipleSeatsFree(1, busB.NUMBER_OF_SEATS);

		System.out.println("\nPASSENGER ARRAY SELL SEAT METHOD TESTS (BUS-B)"
				+ "\n-----------------------------------------------------------------------");
		sellSeatArrayTest(busB);

		System.out.println("\nMAKE SEAT FREE METHODS TESTS (BUS-B)"
				+ "\n-----------------------------------------------------------------------");
		makeSeatFreeTest(busB);

		// TESTS FOR BUS-C
		/////////////////////////////////////////////////////////////
		System.out.println("\nSINGLE PASSENGER SELL SEAT METHOD TESTS (BUS-C)"
				+ "\n-----------------------------------------------------------------------");
		sellSeatTest(busC);

		busC.makeMultipleSeatsFree(1, busC.NUMBER_OF_SEATS);

		System.out.println("\nPASSENGER ARRAY SELL SEAT METHOD TESTS (BUS-C)"
				+ "\n-----------------------------------------------------------------------");
		sellSeatArrayTest(busC);

		System.out.println("\nMAKE SEAT FREE METHODS TESTS (BUS-C)"
				+ "\n-----------------------------------------------------------------------");
		makeSeatFreeTest(busC);
		 
	}

	public static void sellSeatTest(Bus bus) {
		double totalRow = bus.getNumberOfRows();

		System.out.println("\nsellSeat Method tested in increasing order"
				+ "\nvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");

		System.out.println("\n" + bus);
		System.out.println("ROW 1:");
		bus.sellSeat(0.0, nullPass1); // FIXED java.lang.NullPointerException
		bus.sellSeat(0.0, f);
		bus.sellSeat(0.0, f);
		bus.sellSeat(0.0, f);
		System.out.println("\n" + bus);

		System.out.println("ROW 2:");
		bus.sellSeat(2 / totalRow, m);
		bus.sellSeat(2 / totalRow, f);
		bus.sellSeat(2 / totalRow, f);
		bus.sellSeat(2 / totalRow, f);
		System.out.println("\n" + bus);

		System.out.println("ROW 3:");
		bus.sellSeat(3 / totalRow, m);
		bus.sellSeat(3 / totalRow, m);
		bus.sellSeat(3 / totalRow, f);
		bus.sellSeat(3 / totalRow, f);
		System.out.println("\n" + bus);

		System.out.println("ROW 4:");
		bus.sellSeat(4 / totalRow, m);
		bus.sellSeat(4 / totalRow, m);
		bus.sellSeat(4 / totalRow, m);
		bus.sellSeat(4 / totalRow, f);
		System.out.println("\n" + bus);

		System.out.println("ROW 5:");
		bus.sellSeat(5 / totalRow, f);
		bus.sellSeat(5 / totalRow, m);
		bus.sellSeat(5 / totalRow, m);
		bus.sellSeat(5 / totalRow, m);
		System.out.println("\n" + bus);

		System.out.println("ROW 6:");
		bus.sellSeat(6 / totalRow, f);
		bus.sellSeat(6 / totalRow, f);
		bus.sellSeat(6 / totalRow, m);
		bus.sellSeat(6 / totalRow, m);
		System.out.println("\n" + bus);

		System.out.println("ROW 7:");
		bus.sellSeat(7 / totalRow, f);
		bus.sellSeat(7 / totalRow, m);
		bus.sellSeat(7 / totalRow, f);
		bus.sellSeat(7 / totalRow, m);
		System.out.println("\n" + bus);

		System.out.println("ROW 10:");
		bus.sellSeat(10 / totalRow, f);
		bus.sellSeat(10 / totalRow, m);
		bus.sellSeat(10 / totalRow, m);
		bus.sellSeat(10 / totalRow, m);
		System.out.println("\n" + bus);

		System.out.println("LAST ROW:");
		bus.sellSeat(1.0, f);
		bus.sellSeat(1.0, m);
		bus.sellSeat(1.0, f);
		bus.sellSeat(1.0, m);
		System.out.println("\n" + bus);

		System.out.println("INVALID ROW INPUTS:");
		bus.sellSeat(-1.4, m);
		bus.sellSeat(2.6, f);
		bus.sellSeat(-232.5, m);
		bus.sellSeat(143.0, f);
		System.out.println("\n" + bus);

		bus.makeMultipleSeatsFree(1, bus.NUMBER_OF_SEATS);

		System.out.println("\nsellSeat Method Tested Randomly"
				+ "\nvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");

		System.out.println("ROW 5:");
		bus.sellSeat(5 / totalRow, m);
		bus.sellSeat(5 / totalRow, f);
		bus.sellSeat(5 / totalRow, m);
		bus.sellSeat(5 / totalRow, m);
		System.out.println("\n" + bus);

		System.out.println("ROW 5 again:");
		bus.sellSeat(5 / totalRow, m);
		bus.sellSeat(5 / totalRow, f);
		bus.sellSeat(5 / totalRow, m);
		bus.sellSeat(5 / totalRow, m);
		System.out.println("\n" + bus);

		System.out.println("ROW 6:");
		bus.sellSeat(6 / totalRow, f);
		bus.sellSeat(6 / totalRow, f);
		bus.sellSeat(6 / totalRow, m);
		bus.sellSeat(6 / totalRow, m);
		System.out.println("\n" + bus);

		System.out.println("ROW 6 again:");
		bus.sellSeat(6 / totalRow, f);
		bus.sellSeat(6 / totalRow, f);
		bus.sellSeat(6 / totalRow, m);
		bus.sellSeat(6 / totalRow, m);
		System.out.println("\n" + bus);

		System.out.println("ROW 4:");
		bus.sellSeat(4 / totalRow, m);
		bus.sellSeat(4 / totalRow, m);
		bus.sellSeat(4 / totalRow, m);
		bus.sellSeat(4 / totalRow, f);
		System.out.println("\n" + bus);

		System.out.println("LAST ROW:");
		bus.sellSeat(1.0, f);
		bus.sellSeat(1.0, m);
		bus.sellSeat(1.0, f);
		bus.sellSeat(1.0, m);
		System.out.println("\n" + bus);

		System.out.println("ROW 10:");
		bus.sellSeat(10 / totalRow, f);
		bus.sellSeat(10 / totalRow, m);
		bus.sellSeat(10 / totalRow, m);
		bus.sellSeat(10 / totalRow, m);
		System.out.println("\n" + bus);

		System.out.println("ROW 9:");
		bus.sellSeat(9 / totalRow, f);
		bus.sellSeat(9 / totalRow, m);
		bus.sellSeat(9 / totalRow, m);
		bus.sellSeat(9 / totalRow, m);
		System.out.println("\n" + bus);
		
		bus.makeMultipleSeatsFree(1, bus.NUMBER_OF_SEATS);
		
		System.out.println("\nsellSeat Method Tested For Special Conditions"
				+ "\nvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
		
		for (int i = 0; i < bus.NUMBER_OF_SEATS; i++) {
			bus.sellSeat(1.0, m);
		}
		System.out.println("\n" + bus);
		
		System.out.println("\nTRYING TO SELL A TICKET FOR A BUS WITHOUT AVAILABLE SEATS:");
		bus.sellSeat(1/totalRow, f);
		System.out.println("\n" + bus);
		bus.sellSeat(1/totalRow, m);
		System.out.println("\n" + bus);
		
		bus.makeMultipleSeatsFree(1, bus.NUMBER_OF_SEATS);	
		for (int i = 0; i < bus.SEATS_PER_ROW; i++) {
			bus.sellSeat(2/totalRow, f);
		}
		System.out.println("\nSELLING A TICKET TO THE FULL ROW");
		bus.sellSeat(2/totalRow, m);
		System.out.println("\n" + bus);
		bus.sellSeat(2/totalRow, f);
		System.out.println("\n" + bus);
		bus.sellSeat(2/totalRow, m);
		System.out.println("\n" + bus);
		bus.sellSeat(2/totalRow, m);
		
		
		bus.makeMultipleSeatsFree(1, bus.NUMBER_OF_SEATS);
		for (int i = 0; i < bus.SEATS_PER_ROW; i++) {
			bus.sellSeat(2/totalRow, f);
		}
		for (int i = 0; i < bus.SEATS_PER_ROW; i++) {
			bus.sellSeat(4/totalRow, f);
		}
		
		System.out.println("\nSELLING A TICKET TO EMPTY ROW SORROUNDED BY FULL ROWS:");
		System.out.println("\n" + bus);	
		bus.sellSeat(3/totalRow, f);
		System.out.println("\n" + bus);	
		bus.sellSeat(3/totalRow, f);
		System.out.println("\n" + bus);
		bus.sellSeat(3/totalRow, m);
		System.out.println("\n" + bus);
		
		
		bus.makeMultipleSeatsFree(1, bus.NUMBER_OF_SEATS);
		for (int i = 0; i < bus.SEATS_PER_ROW; i++) {
			bus.sellSeat(2/totalRow, f);
		}
		for (int i = 0; i < bus.SEATS_PER_ROW; i++) {
			bus.sellSeat(3/totalRow, f);
		}
		System.out.println("\nSELLİNG A TICKET TO FULL ROW LEFT OF THE CHOSEN ROW IS FULL:");
		System.out.println("\n" + bus);
		bus.sellSeat(3/totalRow, f);
		System.out.println("\n" + bus);	
		bus.sellSeat(3/totalRow, f);
		System.out.println("\n" + bus);
		bus.sellSeat(3/totalRow, m);
		System.out.println("\n" + bus);
		bus.sellSeat(3/totalRow, f);
		System.out.println("\n" + bus);	
		
		bus.makeMultipleSeatsFree(1, bus.NUMBER_OF_SEATS);
		for (int i = 0; i < bus.SEATS_PER_ROW; i++) {
			bus.sellSeat(4/totalRow, f);
		}
		for (int i = 0; i < bus.SEATS_PER_ROW; i++) {
			bus.sellSeat(3/totalRow, f);
		}
		System.out.println("\nSELLING A TICKET To FULL ROW RIGHT OF THE CHOSEN ROW IS FULL:");
		System.out.println("\n" + bus);
		bus.sellSeat(3/totalRow, f);
		System.out.println("\n" + bus);	
		bus.sellSeat(3/totalRow, f);
		System.out.println("\n" + bus);
		bus.sellSeat(3/totalRow, m);
		System.out.println("\n" + bus);
		bus.sellSeat(3/totalRow, f);
		System.out.println("\n" + bus);	
	}

	static Passenger[] pasArr_0 = { };
	static Passenger[] pasArr0 = { nullPass1, nullPass1, nullPass1 };
	static Passenger[] pasArr1 = { m };
	static Passenger[] pasArr1f = { f };
	static Passenger[] pasArr2 = { f, m };
	static Passenger[] pasArr3 = { m, m, f };
	static Passenger[] pasArr4 = { m, f, f };
	static Passenger[] pasArr5 = { f, m, f };
	static Passenger[] pasArr6 = { f, f, m };

	static Passenger[] pasArr7 = { m, m, m, m };
	static Passenger[] pasArr8 = { f, f, f, f };
	static Passenger[] pasArr9 = { m, m, f, f, m, m };
	static Passenger[] pasArr10 = { m, f, f, f };
	static Passenger[] pasArr11 = { f, m, m, f };
	static Passenger[] pasArr12 = { m, f, f, m };
	static Passenger[] pasArr13 = { m, f, m, f };
	
	public static void sellSeatArrayTest(Bus bus) {
		double totalRow = bus.getNumberOfRows();
		System.out.println("\n" + bus);
		
		System.out.println("\nSELLING TICKET TO NULL PASSENGER ARRAY:");
		bus.sellMultipleSeats(0.0,pasArr0);
		System.out.println("\n" + bus); // FIXED NullPointerException in toString Method

		System.out.println("\nSELLING TICKET TO LAST ROW MANY TIMES:");
		bus.sellMultipleSeats(1.0,pasArr2); //{ f, m }
		System.out.println("\n" + bus);

		bus.sellMultipleSeats(1.0,pasArr2); //{ f, m }
		System.out.println("\n" + bus);
		
		bus.sellMultipleSeats(1.0,pasArr3); //{ m, m, f }
		System.out.println("\n" + bus);

		System.out.println("\nSELLING TICKET TO FIRST ROW MANY TIMES:");
		bus.sellMultipleSeats(0.0,pasArr1); //{ m }
		System.out.println("\n" + bus);

		bus.sellMultipleSeats(0.0,pasArr2); //{ f, m }
		System.out.println("\n" + bus);
		
		bus.sellMultipleSeats(0.0,pasArr3); //{ m, m, f }
		System.out.println("\n" + bus);
		
		System.out.println("\nSELLING TICKET TO EMPTY PASSENGER ARRAY:");
		bus.sellMultipleSeats(6/totalRow,pasArr_0); // { }
		System.out.println("\n" + bus);
		
		System.out.println("\nSELLING TICKET WITHOUT ANY SPECIAL CONDITION:");
		
		bus.sellMultipleSeats(2/totalRow,pasArr8); //{ f, f, f, f }
		System.out.println("\n" + bus);
		
		bus.sellMultipleSeats(2/totalRow,pasArr9);//{ m, m, f, f, m, m }
		System.out.println("\n" + bus);

		bus.sellMultipleSeats(5/totalRow,pasArr1); //{ m }
		System.out.println("\n" + bus);
		
		bus.sellMultipleSeats(6/totalRow,pasArr5);// { f, m, f }
		System.out.println("\n" + bus);
		
		bus.sellMultipleSeats(11/totalRow,pasArr11); //{ f, m, m, f }
		System.out.println("\n" + bus);
		
		bus.sellMultipleSeats(10/totalRow,pasArr13); //{ m, f, m, f }
		System.out.println("\n" + bus);
		
		bus.sellMultipleSeats(10/totalRow,pasArr7); //{ m, m, m, m }
		System.out.println("\n" + bus);
		
		bus.sellMultipleSeats(9/totalRow,pasArr1); //{ m }
		System.out.println("\n" + bus);
		
		bus.sellMultipleSeats(9/totalRow,pasArr1f); //{ f }
		System.out.println("\n" + bus);
		
		bus.makeMultipleSeatsFree(1, bus.NUMBER_OF_SEATS);
		System.out.println("\n" + bus);
		for (int i = 0; i < bus.NUMBER_OF_SEATS-1; i++) {
			bus.sellSeat(0.0, m);
		}
		System.out.println("\nMETHOD TESING WHEN THERE IS ONLY 1 FREE SEAT, BUT ARRAY HAS MORE PASSENGERS");
		System.out.println("\n" + bus);
		bus.sellMultipleSeats(9/totalRow,pasArr7); //{m, m, m, m }
		System.out.println("\n" + bus);
	}

	public static void makeSeatFreeTest(Bus bus) {
		bus.makeMultipleSeatsFree(1, bus.NUMBER_OF_SEATS);
		for (int i = 0; i < bus.NUMBER_OF_SEATS; i++) {
			bus.sellSeat(0.0, m);
		}
		System.out.println("\n" + bus);
		System.out.println("Before calling to method: Free seat number is " + bus.getNumberOfFreeSeats());
		System.out.println("Makes seat(1) seat free: ");
		bus.makeSeatFree(1);
		System.out.println("After calling to method: Free seat number is " + bus.getNumberOfFreeSeats());
		System.out.println("\n" + bus);

		System.out.println("Before calling to method: Free seat number is " + bus.getNumberOfFreeSeats());
		System.out.println("Makes seat(3) seat free: ");
		bus.makeSeatFree(3);
		System.out.println("After calling to method: Free seat number is " + bus.getNumberOfFreeSeats());
		System.out.println("\n" + bus);

		System.out.println("Before calling to method: Free seat number is " + bus.getNumberOfFreeSeats());
		System.out.println("Makes seat(21) seat free: ");
		bus.makeSeatFree(21);
		System.out.println("After calling to method: Free seat number is " + bus.getNumberOfFreeSeats());
		System.out.println("\n" + bus);

		System.out.println("Before calling to method: Free seat number is " + bus.getNumberOfFreeSeats());
		System.out.println("Trying invalid input for a single seat:");
		bus.makeSeatFree(456456);
		System.out.println("After calling to method: Free seat number is " + bus.getNumberOfFreeSeats());
		System.out.println("\n" + bus);

		System.out.println("Before calling to method: Free seat number is " + bus.getNumberOfFreeSeats());
		System.out.println("Makes seats 6(included) to 10(included) free:");
		bus.makeMultipleSeatsFree(6, 10);
		System.out.println("After calling to method: Free seat number is " + bus.getNumberOfFreeSeats());
		System.out.println("\n" + bus);

		System.out.println("Before calling to method: Free seat number is " + bus.getNumberOfFreeSeats());
		System.out.println("Makes seats 22(included) to 25(included) free:");
		bus.makeMultipleSeatsFree(22, 25);
		System.out.println("After calling to method: Free seat number is " + bus.getNumberOfFreeSeats());
		System.out.println("\n" + bus);

		System.out.println("Before calling to method: Free seat number is " + bus.getNumberOfFreeSeats());
		System.out.println("Trying invalid input1 for multiple seats:");
		bus.makeMultipleSeatsFree(-4, 10);
		System.out.println("After calling to method: Free seat number is " + bus.getNumberOfFreeSeats());
		System.out.println("\n" + bus);

		System.out.println("Before calling to method: Free seat number is " + bus.getNumberOfFreeSeats());
		System.out.println("Trying invalid input2 for multiple seats:");
		bus.makeMultipleSeatsFree(4, 3422);
		System.out.println("After calling to method: Free seat number is " + bus.getNumberOfFreeSeats());
		System.out.println("\n" + bus);

		System.out.println("Before calling to method: Free seat number is " + bus.getNumberOfFreeSeats());
		System.out.println("Trying illogical input for multiple seats:");
		bus.makeMultipleSeatsFree(15, 9);
		System.out.println("After calling to method: Free seat number is " + bus.getNumberOfFreeSeats());
		System.out.println("\n" + bus);
	}
}
