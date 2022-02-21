package Model;

import java.io.Serializable;

/**
 * Represents a simple person
 *
 * @author Anna Wołoszyn
 * @version 3.1
 */
public class Person implements Serializable {

    private int ID;//Person's logging id
    private String name, surname, password;//Name, surname, and password of the person

    /**
     * Creates a new person with given values
     */
    public Person(int ID, String name, String surname, String password) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }

    /**
     * Returns the person's ID
     */
    public int getID() {
        return ID;
    }

    /**
     * Returns the person's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the person's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the person's surname
     */
    public String getSurname() {
        return surname;
    }

}