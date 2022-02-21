package Model;

import java.io.Serializable;

/**
 * Represents the office's manager
 *
 * @author Anna Wołoszyn
 * @version 3.1
 */
public class Manager implements Serializable {

    private String password;

    /**
     * Creates a new manager with given password
     */
    public Manager(String password) {
        this.password = password;
    }

    /**
     * Returns manager's password
     */
    public String getPassword() {
        return password;
    }
}