package Model;

import Model.Trip;
import Model.TourGuide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**Class of Trip's tests
 *
 * @author Anna Wołoszyn
 * @version 3.1
 */
public class TripTest {
    
    private Trip t;
    private TourGuide tg;
    
    /**
     * Method initializing necessary fields before each test
     */
    @BeforeEach
    public void setUp() {
        tg=new TourGuide(0,"e","f","g");//Assigning an example guide to tg
        t=new Trip(0,1,2,"a","b","c","d",2.5,tg, CateringInfo.ALL_INCLUSIVE);  //Assigning an example trip to t
    }
    
    /**
     * Test of incrementBoughtSeats method, of class Trip.
     */
    @Test
    public void testIncrementBoughtSeats() {
        t.incrementBoughtSeats();//Calling the tested method
        assertEquals(3,t.getBoughtSeats());//Checking if the number of bought seats is bigger
    }
}
