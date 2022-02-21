package Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Enum class, storing possible values for trip's catering types
 *
 * @author Anna Wołoszyn
 * @version 5.1
 */
public enum CateringInfo implements Serializable {
    ALL_INCLUSIVE(1), BREAKFAST_DINNER(2), SELF_CATERING(3);

    /**
     * Id of the option
     */
    private int id;
    /**
     * Map storing option and its id
     */
    private static Map map = new HashMap<>();

    /**
     * Constructor of the class
     */
    CateringInfo(int id) {
        this.id = id;
    }

    static {
        //Putting the values into the map
        for (CateringInfo cI : CateringInfo.values()) {
            map.put(cI.id, cI);
        }
    }

    /**
     * Getting the value connected to given id
     */
    public static CateringInfo valueOf(int id) {
        return (CateringInfo) map.get(id);
    }

    /**
     * Getting the id method
     */
    public int getId() {
        return id;
    }
}