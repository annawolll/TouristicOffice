package Model;

import Model.Office;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Class with tests of the Office's methods
 *
 * @author Anna Wołoszyn
 * @version 5.1
 */
public class OfficeTest {

    private Office o;
    private ArrayList<TourGuide> guides;
    private ArrayList<Trip> trips;
    private ArrayList<Client> clients;
    private Manager m;

    /**
     * Method initializing necessary fields before each test
     */
    @BeforeEach
    public void setUp() {
        guides = new ArrayList<TourGuide>();
        TourGuide tg = new TourGuide(0, "a", "b", "c");
        guides.add(tg);//List of guides is no longer a null, has one object
        trips = new ArrayList<Trip>();
        Trip t = new Trip(0, 2, 0, "a", "b", "c", "d", 2.5, tg, CateringInfo.SELF_CATERING);
        trips.add(t);//List of trips is no longer a null, has one object
        clients = new ArrayList<Client>();
        Client c = new Client(0, "a", "b", "c", trips);
        clients.add(c);//List of clients is no longer a null, has one object

        m = new Manager("password");//Manager initialized with a password here
        o = new Office(250.4, guides, clients, trips, m);//Initializing the office with created fields
    }

    /**
     * Test of getEmployees method, of class Office.
     */
    @Test
    public void testGetEmployees() {
        ArrayList<TourGuide> result = o.getEmployees();
        assertEquals(guides, result);
    }

    /**
     * Test of getClients method, of class Office.
     */
    @Test
    public void testGetClients() {
        ArrayList<Client> result = o.getClients();
        assertEquals(clients, result);
    }

    /**
     * Test of getTrips method, of class Office.
     */
    @Test
    public void testGetTrips() {
        ArrayList<Trip> result = o.getTrips();
        assertEquals(trips, result);
    }

    /**
     * Test of getManager method, of class Office.
     */
    @Test
    public void testGetManager() {
        assertEquals(m, o.getManager());
    }

    /**
     * Test of writeTripsToFile and readTripsFromFile methods, of class Office.
     */
    @Test
    public void testWriteTripsToAndReadTripsFromFile() throws IOException, ClassNotFoundException {
        //Testing both reading and writing methods of Trips data
        //And checking if the final result is consistent
        o.writeTripsToFile("newTrips.bin");//New file as a parameter
        Office o2 = new Office(0, null, null, null, null);//Creating an empty object
        o2.readTripsFromFile("newTrips.bin");//Reading from the same file
        assertEquals(o.getTrips().get(0), o2.getTrips().get(0));
    }

    /**
     * Test of writeGuidesToFile and readGuidesFromFile methods, of class
     * Office.
     */
    @Test
    public void testWriteGuidesToAndReadGuidesFromFile() throws IOException, ClassNotFoundException {
        //Testing both reading and writing methods of Guides data
        //And checking if the final result is consistent
        o.writeGuidesToFile("newGuides.bin");//New file as a parameter
        Office o2 = new Office(0, null, null, null, null);//Creating an empty object
        o2.readGuidesFromFile("newGuides.bin");//Reading from the same file
        assertEquals(o.getEmployees().get(0), o2.getEmployees().get(0));
    }

    /**
     * Test of writeClientsToFile and readClientsFromFile methods, of class
     * Office.
     */
    @Test
    public void testWriteClientsToAndReadClientsFromFile() throws IOException, ClassNotFoundException {
        //Testing both reading and writing methods of Clients data
        //And checking if the final result is consistent
        o.writeClientsToFile("newClients.bin");//New file as a parameter
        Office o2 = new Office(0, null, null, null, null);//Creating an empty object
        o2.readClientsFromFile("newClients.bin");//Reading from the same file
        assertEquals(o.getClients().get(0), o2.getClients().get(0));
    }

    //Methods: readDataFromFiles() and writeDataToFiles() won't be tested
    //as they only call other, more specific methods, which are tested above
    /**
     * Test of getGuideById method, of class Office.
     */
    @Test
    public void testGetGuideById() {
        //Uses ArrayList's method get(index) to check the working of tested fuction
        try {
            //Option which shouldn't throw an exception
            assertEquals(guides.get(0), o.getGuideById(0));
        } catch (IndexOutOfBoundsException e) {
            fail("Guide with existing id has not been found, but should have.");
        }
        try {
            //Option which should throw an exception
            assertEquals(guides.get(2), o.getGuideById(2));
            fail("Exception not thrown when it should be.");
        } catch (IndexOutOfBoundsException e) {
        }
    }

    /**
     * Test of addTrip method, of class Office.
     */
    @ParameterizedTest
    @ValueSource(strings = {"Poland", "Germany"})
    public void testAddTrip(String country) {
        TourGuide tg = new TourGuide(0, "a", "b", "c");//Creating a new guide object
        Trip expected = new Trip(1, 11, 0, country, "bb", "cc", "dd", 4.5, tg, CateringInfo.ALL_INCLUSIVE);//Creating a new Trip object
        o.addTrip(country, "bb", 4.5, 11,0, "cc", "dd", tg, CateringInfo.ALL_INCLUSIVE);//Calling the tested method
        assertEquals(expected, o.getTrips().get(o.getTrips().size() - 1));
    }

    /**
     * Test of addClient method, of class Office.
     */
    @ParameterizedTest
    @ValueSource(strings = {"Natalia", "Ewa"})
    public void testAddClient(String name) {
        ArrayList<Trip> newTrips = new ArrayList<Trip>();//Creating a new, empty trips list
        Client expected = new Client(1, name, "bb", "cc", newTrips);//Creting a new Client object
        o.addClient(name, "bb", "cc");//Calling the tested method
        assertEquals(expected, o.getClients().get(o.getClients().size() - 1));
    }

    /**
     * Test of addTourGuide method, of class Office.
     */
    @ParameterizedTest
    @ValueSource(strings = {"Jan", "Maria"})
    public void testAddTourGuide(String name) {
        TourGuide expected = new TourGuide(1, name, "Nowak", "haslo");//Creating a new TourGuide object
        o.addTourGuide(name, "Nowak", "haslo");//Calling the tested method
        assertEquals(expected, o.getGuideById(1));
    }

    /**
     * Test of deleteTrip method, of class Office.
     */
    @Test
    public void testDeleteTrip() {
        int beforeSize = o.getTrips().size();//Loading current size of list of trips
        o.deleteTrip(0);//Calling the tested method
        assertEquals(beforeSize - 1, o.getTrips().size());//Checking if the size was decremented

    }

    /**
     * Test of sellTrip method, of class Office.
     */
    @Test
    public void testSellTrip() {
        Trip temp = trips.get(0);//Getting the trip available to sell
        temp.incrementBoughtSeats();//Simulating selling of the temp trip
        assertEquals(temp, o.sellTrip(0));//Checking if returned by tested method object is as expected
    }

}