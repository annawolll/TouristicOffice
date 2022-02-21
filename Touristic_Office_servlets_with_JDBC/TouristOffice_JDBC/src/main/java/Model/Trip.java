package Model;

import java.io.Serializable;

/**
 * Represents a trip
 *
 * @author Anna Wołoszyn
 * @version 5.1
 */
public class Trip implements Serializable {

    private int ID, maxSeats, boughtSeats;//ID of the trip, maximum amount of seats to book, and how many seats are already booked
    private String country, city;//Destination country and city
    private String departureDate, arrivalDate;//Date of the beggining and end
    private double price;//Price in PLN
    private CateringInfo catering;//Type of the catering
    /**
     * Guide assigned to the trip
     */
    TourGuide guide;

    /**
     * Creates a new trip with given values
     */
    public Trip(int ID, int maxSeats, int boughtSeats, String country, String city, String depDate, String arrDate, double price, TourGuide tg, CateringInfo cI) {
        this.ID = ID;
        this.maxSeats = maxSeats;
        this.boughtSeats = boughtSeats;
        this.country = country;
        this.city = city;
        this.departureDate = depDate;
        this.arrivalDate = arrDate;
        this.price = price;
        this.guide = tg;
        this.catering = cI;
    }

    /**
     * Creates a new trip with default values
     */
    public Trip() {
        this.ID = -1;
        this.maxSeats = 0;
        this.boughtSeats = 0;
        this.country = "";
        this.city = "";
        this.departureDate = "";
        this.arrivalDate = "";
        this.price = 0;
        this.guide = null;
        this.catering = CateringInfo.SELF_CATERING;//Default value set to self-catering
    }

    /**
     * Gets the trip's id
     */
    public int getID() {
        return ID;
    }

    /**
     * Gets the trip's destination country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Gets the trip's destination city
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets the trip's departure date
     */
    public String getDepartureDate() {
        return departureDate;
    }

    /**
     * Gets the trip's arrival date
     */
    public String getArrivalDate() {
        return arrivalDate;
    }

    /**
     * Gets the trip's price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the trip's guide
     */
    public TourGuide getGuide() {
        return guide;
    }

    /**
     * Gets the trip's maximum amount of seats
     */
    public int getMaxSeats() {
        return maxSeats;
    }

    /**
     * Gets the trip's number of bought seats
     */
    public int getBoughtSeats() {
        return boughtSeats;
    }

    /**
     * Gets the trip's number of bought seats
     */
    public CateringInfo getCatering() {
        return catering;
    }

    /**
     * Sets the trip's maximum amount of seats
     */
    public void setMaxSeats(int maxSeats) {
        this.maxSeats = maxSeats;
    }

    /**
     * Sets the trip's destination country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Sets the trip's destination city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Sets the trip's departure date
     */
    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * Sets the trip's arrival date
     */
    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**
     * Sets the trip's price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the guide assigned to the trip
     */
    public void setGuide(TourGuide guide) {
        this.guide = guide;
    }

    /**
     * Sets the guide assigned to the trip
     */
    public void setCatering(CateringInfo cI) {
        this.catering = cI;
    }

    /**
     * Increments the trip's bought seats field
     */
    public void incrementBoughtSeats() {
        boughtSeats++;
    }

    /**
     * Returns true if all fields of object are identical to those of object
     * "other"
     *
     * @param other Compared object
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Trip)) {//Checking if the other object is of type "Trip"
            return false;
        } else {
            Trip t2 = (Trip) other;//Casting the other object to a "Trip"
            //Comparing all fields
            if (this.ID == t2.getID() && this.maxSeats == t2.getMaxSeats()
                    && this.boughtSeats == t2.getBoughtSeats() && this.country.equals(t2.getCountry())
                    && this.city.equals(t2.getCity()) && this.departureDate.equals(t2.getDepartureDate())
                    && this.arrivalDate.equals(t2.getArrivalDate()) && this.price == t2.getPrice()
                    && this.guide.equals(t2.getGuide()) && this.catering == t2.getCatering()) {
                return true;
            }
            return false;//If any field didn't match
        }
    }
}