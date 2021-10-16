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
public class Client extends Person{
   // private int ID;
   // private String name, surname, password;
   private ArrayList<Trip> boughtTrips;

    public Client(int ID, String name, String surname, String password, ArrayList<Trip> boughtTrips) {
        super(ID, name, surname, password);
        this.boughtTrips = boughtTrips;
    }
   
   
    
}
