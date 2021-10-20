/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.touristicoffice;
import java.util.Date;

/**
 *
 * @author User
 */
public class Trip {
    private int ID, maxSeats, boughtSeats;
    private String country,city;
    private String departureDate, arrivalDate;
    private double price;
    TourGuide guide;

    public Trip(int ID, int maxSeats,int boughtSeats, String country, String city, String depDate, String arrDate, double price, TourGuide tg) {
        this.ID = ID;
        this.maxSeats=maxSeats;
        this.boughtSeats=boughtSeats;
        this.country = country;
        this.city = city;
        this.departureDate = depDate;
        this.arrivalDate=arrDate;
        this.price = price;
        this.guide=tg;
    }

    public int getID() {
        return ID;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public double getPrice() {
        return price;
    }

    public TourGuide getGuide() {
        return guide;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public int getBoughtSeats() {
        return boughtSeats;
    }
    
    
    
}
