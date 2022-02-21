package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents the office's client
 *
 * @author Anna Wołoszyn
 * @version 3.1
 */
public class Client extends Person implements Serializable {

    /**
     * Represents a list of booked trips by the client
     */
    private ArrayList<Trip> boughtTrips;

    /**
     * Creates a new Client with given values
     */
    public Client(int ID, String name, String surname, String password, ArrayList<Trip> boughtTrips) {
        super(ID, name, surname, password);
        this.boughtTrips = boughtTrips;
    }

    /**
     * Returns the array of trips that were booked by the client
     */
    public ArrayList<Trip> getBoughtTrips() {
        return boughtTrips;
    }
    
    /**
     * Represents booking a trip by the client
     * @param of Tourist Office in which transaction is being done
     * @param tripIndex Index of the trip to be booked
     */
    public boolean bookTrip(Office of, int tripIndex) {
        Trip bought = new Trip();//Creating a new trip object to which booked trip will be written
        bought = of.sellTrip(tripIndex);//"bought" is now modified by method sellTrip
        if (bought == null) {//Booking the trip wasn't succesful
            return false;
        } else {
            //Adding the correctly modified trip object to clients list of bought trips
            getBoughtTrips().add(bought);
            return true;
        }
    }
    
    /**
     * Returns true if all fields of object are identical to those of object
     * "other"
     *
     * @param other Compared object
     */
    @Override
    public boolean equals(Object other) {//This overriden method is used by testing methods
        if (!(other instanceof Client)) {//If the other object isn't of type "Client"
            return false;
        } else {
            Client c2 = (Client) other;//Casting the given object to a "Client"
            //Comparing all fields
            if (this.getID() == c2.getID() && this.getName().equals(c2.getName())
                    && this.getSurname().equals(c2.getSurname()) && this.getPassword().equals(c2.getPassword())
                    && this.getBoughtTrips().equals(c2.getBoughtTrips())) {
                return true;
            }
            return false;//If any field didn't match
        }
    }
}