/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.touristicoffice;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class TourGuide extends Person{
    //private int ID;
    //String name, surname, password;

    public TourGuide(int ID, String name, String surname, String password) {
       super(ID, name, surname, password);
    }
    
    
    
    public void printAssignedTrips(Office of){
    ArrayList<Trip> foundTrips=of.searchTripsforGuide(this);
    }
    
}
