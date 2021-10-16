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
    private Date departureDate, arrivalDate;
    private double price;
    TourGuide guide;

    public Trip(int ID, int maxSeats,int boughtSeats, String country, String city, Date depDate, Date arrDate, double price, TourGuide tg) {
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
    
    
    
}
