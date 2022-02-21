package Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the touristic office
 *
 * @author Anna Wołoszyn
 * @version 5.1
 */
public class Office {

    private double accountBalance;//Cash position of the Office
    private ArrayList<TourGuide> employees;//All of the tour guides employed in the Office
    private ArrayList<Client> clients;//All of the clients registered in the Office
    private ArrayList<Trip> trips;//All of the trips offered to book by the Office
    private Manager manager;//Manager of the office

    /**
     * Creates a new office with given values
     */
    public Office(double accountBalance, ArrayList<TourGuide> employees, ArrayList<Client> clients, ArrayList<Trip> trips, Manager m) {
        this.accountBalance = accountBalance;
        this.employees = employees;
        this.clients = clients;
        this.trips = trips;
        this.manager = m;
    }

    /**
     * Returns the array of office's employees
     */
    public ArrayList<TourGuide> getEmployees() {
        return employees;
    }

    /**
     * Returns the array of office's clients
     */
    public ArrayList<Client> getClients() {
        return clients;
    }

    /**
     * Returns the array of office's available trips
     */
    public ArrayList<Trip> getTrips() {
        return trips;
    }

    /**
     * Returns the office's manager
     */
    public Manager getManager() {
        return manager;
    }

    /**
     * Writes list of trips to file
     */
    public void writeTripsToFile(String fileName) throws IOException, ClassNotFoundException {
        try ( ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(trips);//Serialising the whole list of trips
        }
    }

    /**
     * Writes list of guides to file
     */
    public void writeGuidesToFile(String fileName) throws IOException, ClassNotFoundException {
        try ( ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(employees);//Serialising the whole list of employees
        }
    }

    /**
     * Writes list of clients to file
     */
    public void writeClientsToFile(String fileName) throws IOException, ClassNotFoundException {
        try ( ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(clients);//Serialising the whole list of clients
        }
    }

    /**
     * Reads list of trips from file
     */
    public void readTripsFromFile(String fileName) throws IOException, ClassNotFoundException {
        try ( ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            trips = (ArrayList<Trip>) inputStream.readObject();//Deserialising the whole list of trips
        }
    }

    /**
     * Reads list of guides from file
     */
    public void readGuidesFromFile(String fileName) throws IOException, ClassNotFoundException {
        try ( ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            employees = (ArrayList<TourGuide>) inputStream.readObject();//Deserialising the whole list of employees
        }
    }

    /**
     * Reads list of clients from file
     */
    public void readClientsFromFile(String fileName) throws IOException, ClassNotFoundException {
        try ( ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            clients = (ArrayList<Client>) inputStream.readObject();//Deserialising the whole list of clients
        }
    }

    /**
     * Manages reading all data from files
     */
    public void readDataFromFiles(String trips, String guides,String clients) throws IOException, ClassNotFoundException {
        //This method calls all the functions serialising objects lists of the Office
        readTripsFromFile("/WEB-INF/trips.bin");
        readGuidesFromFile("/WEB-INF/guides.bin");
        readClientsFromFile("/WEB-INF/clients.bin");
    }

    /**
     * Manages writing all data to files
     */
    public void writeDataToFiles(String path) throws IOException, ClassNotFoundException {
        //This method calls all the functions deserialising objects lists of the Office
        writeTripsToFile("trips.bin");
        writeGuidesToFile("guides.bin");
        writeClientsToFile("clients.bin");
    }

    /**
     * Gets guide by his id
     */
    public TourGuide getGuideById(int id) {//Returns a guide that is stored in the guides list under the value of id
        try {
            return employees.get(id);
        } catch (IndexOutOfBoundsException e) {//If there isn't such index in guides list
            System.out.println("No guide under such id");
        }
        return null;
    }

    /**
     * Adds a trip to the office list of trips
     */
    public void addTrip(String country, String city, double price, int maxSeats,int boughtS, String begin, String end, TourGuide tg, CateringInfo cI) {
        int id = 0;
        if (trips.size() != 0) {
            id = trips.size();
        }
        
        //Creating a new Trip object with data from parameters of the method
        Trip newTrip = new Trip(id, maxSeats, boughtS, country, city, begin, end, price, tg, cI);
        trips.add(newTrip);//Adding the prepared trip to the list
    }
    

    /**
     * Adds a client to the office list of clients
     */
    public void addClient(String name, String surname, String password) {
        int id = 0;
        if (clients.size() != 0) {
            id = clients.size();
        }
        ArrayList<Trip> boughtTrips = new ArrayList<Trip>();//Preparing an empty list of trips to tie to the added client
        //Creating a new Client object with data from parameters of the method
        Client newClient = new Client(id, name, surname, password, boughtTrips);
        clients.add(newClient);//Adding the prepared client to the list
    }

    /**
     * Adds a guide to the office list of guides
     */
    public void addTourGuide(String name, String surname, String password) {
        int id = 0;
        if (employees.size() != 0) {
            id = employees.size();
        }
        //Creating a new tour guide object with data from parameters of the method
        TourGuide newGuide = new TourGuide(id, name, surname, password);
        employees.add(newGuide);//Adding the prepared tour guide to the list
    }

    /**
     * Deletes a trip from list
     */
    public void deleteTrip(int id) {
        trips.remove(id);//Removing the trip stored in "trips" under the index of id value
    }

    /**
     * Represents selling the trip to a client
     */
    public Trip sellTrip(int Index) {
        Trip temp = trips.get(Index);//Getting the trip stored in "trips" under the Index 
        if (temp != null) {
            //Checking if there are some free seats to book
            if ((temp.getMaxSeats() - temp.getBoughtSeats()) > 0) {
                trips.get(Index).incrementBoughtSeats();//Incrementing the number of bought seats in the trip
                temp.incrementBoughtSeats();
                return temp;//Returning the trip that has just been bought
            }
        }
        return null;//Returning null if the trip was not found or was equal to null 
    }

    public ArrayList<Trip> filterTripsByCountry(String country) {
        //Using Java Streams to filter the list of trips
        List<Trip> filtered = trips.stream().filter(t -> t.getCountry().equals(country))//Lambda function filtering by the country field
                .collect(Collectors.toList());
        ArrayList tmp=new ArrayList<Trip>(filtered);
        return tmp;//Returning the list of matched trips
    }
/**
     * Represents logging the client into the app
     */
    public Client logClientIn(int id, String password) {
        for (Client client : clients) {//Searching through the whole clients list of the Office
            if (client.getID() == id) {//Checking if there is an object where id is the same as parameter "id"
                if (client.getPassword().equals(password)) {//Checking the same for password
                    return client;//If such object has been found it is returned
                }
            }
        }
        return null;//If such object hs not been found null is returned
    }

    /**
     * Represents logging the guide into the app
     */
    public TourGuide logTourGuideIn(int id, String password) {
        for (TourGuide guide : employees) {//Searching through the whole guides list of the Office
            if (guide.getID() == id) {//Checking if there is an object where id is the same as parameter "id"
                if (guide.getPassword().equals(password)) {//Checking the same for password
                    return guide;//If such object has been found it is returned
                }
            }
        }
        return null;//If such object has not been found null is returned
    }

    /**
     * Represents logging the manager into the app
     */
    public boolean logManagerIn(String password) {
        //Checking if the Office manager's password is the same as parameter "password"
        if (manager.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
    /**
     * Returns a list of all the trips assigned to a given guide
     */
    public ArrayList<Trip> searchTripsforGuide(TourGuide tg) {
        //Creating a new object where found trips will be added
        ArrayList<Trip> matchedTrips = new ArrayList<Trip>();
        for (Trip trip : trips) {//Searching through the whole office's trips list
            if (trip.getGuide() == tg) {//If given guide is assigned to the trip
                matchedTrips.add(trip);//This trip is added to matchedTrips
            }
        }
        return matchedTrips;
    }
    public TourGuide findGuideBySurname(String surn){
    for(TourGuide t:employees){
    if(t.getSurname().equals(surn)) return t;
    }
    return null;
    }
    public TourGuide findGuideByID(int id ){
    for(TourGuide t:employees){
    if(t.getID()==id) return t;
    }
    return null;
    }
    
}
