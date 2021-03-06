/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.touristicoffice;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Anna Wołoszyn
 */
public class Trip implements Serializable{
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
    public Trip(){
        this.ID=-1;
        this.maxSeats=0;
        this.boughtSeats=0;
        this.country="";
        this.city="";
        this.departureDate="";
        this.arrivalDate="";
        this.price=0;
        this.guide=null;
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

    public void setMaxSeats(int maxSeats) {
        this.maxSeats = maxSeats;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setGuide(TourGuide guide) {
        this.guide = guide;
    }
    
    
    public void incrementBoughtSeats(){
        boughtSeats++;
    }
    
    
}
