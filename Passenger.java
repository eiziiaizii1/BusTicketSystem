package BusTicketSystem;

public class Passenger {

    final Gender GENDER;
    final String FIRST_NAME;
    final String LAST_NAME;

    private Passenger() {
        this.FIRST_NAME = null;
        this.LAST_NAME = null;
        this.GENDER = null;
    }

    public Passenger(String firstName, String lastName, Gender gender) {
        //Default name is ??? if null was entered as a name
        this.FIRST_NAME = (firstName != null ? firstName : "???");
        this.LAST_NAME = (lastName != null ? lastName : "???");
        this.GENDER = (gender != null ? gender : Gender.Male);
        //Default gender is male if null was given as a gender
    }

    protected Gender getGender() {
        return this.GENDER;
    }

    private String getFullName() {
        return FIRST_NAME == null ? null : this.FIRST_NAME + " " + LAST_NAME == null ? null : this.LAST_NAME;
    }

    @Override
    public String toString() {
        return getFullName() + ", " + getGender();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Passenger p) {
            return this.FIRST_NAME.equalsIgnoreCase(p.FIRST_NAME) && this.LAST_NAME.equalsIgnoreCase(p.LAST_NAME)
                    && this.GENDER == p.GENDER;
        } else {
            return false;
        }
    }
}
