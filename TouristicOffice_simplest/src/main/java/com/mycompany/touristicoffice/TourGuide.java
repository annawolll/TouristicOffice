/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.touristicoffice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Anna Wołoszyn
 */
public class TourGuide extends Person implements Serializable{
    
    public TourGuide(int ID, String name, String surname, String password) {
       super(ID, name, surname, password);
    }
    
  
    public void printAssignedTrips(Office of){
        ArrayList<Trip> foundTrips=of.searchTripsforGuide(this);
        for(Trip t:foundTrips){
            System.out.println(t.getCountry()+" "+t.getCity()+" "+t.getDepartureDate()+" "+t.getArrivalDate());
        }
    }
    
    public void GuideOptionsMenu(Office of){
        int choice=0;
        do{
        System.out.println("What would you like to do?\n1.Print my assigned trips.\n2.Escape.");
        Scanner scanner=new Scanner(System.in);
        choice=scanner.nextInt();
        if(choice==1){
            System.out.println("Your trips:");
        printAssignedTrips(of);
        }
        }while(choice!=2);
    }
}
