package Model;

import java.io.Serializable;

/**
 * Represents an employee being a tour guide
 *
 * @author Anna Wołoszyn
 * @version 5.1
 */
public class TourGuide extends Person implements Serializable {

    /**
     * Creates a new Tour Guide with given values
     */
    public TourGuide(int ID, String name, String surname, String password) {
        super(ID, name, surname, password);
    }

    /**
     * Returns true if all fields of object are identical to those of object
     * "other"
     *
     * @param other Compared object
     */
    @Override
    public boolean equals(Object other) {//This overriden method is used by testing methods
        if (!(other instanceof TourGuide)) {//Checking if the other object is of type "TourGuide"
            return false;
        } else {
            TourGuide t2 = (TourGuide) other;//Casting the other object to a TourGuide object
            //Comparing all fields
            if (this.getID() == t2.getID() && this.getName().equals(t2.getName())
                    && this.getSurname().equals(t2.getSurname()) && this.getPassword().equals(t2.getPassword())) {
                return true;
            }
            return false;//If any field didn't match
        }
    }
}