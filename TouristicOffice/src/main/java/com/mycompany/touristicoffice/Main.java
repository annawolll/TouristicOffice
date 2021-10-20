/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.touristicoffice;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author User
 */
public class Main {
    public static void main(String[] args){
         ArrayList<TourGuide> guides=new ArrayList<TourGuide>();
         
         //TourGuide jk=new TourGuide(1, "Jan", "Kowalski", "jankow");
         //TourGuide pn=new TourGuide(2,"Piotr", "Nowak", "piotnow");
         //guides.add(jk);
         //guides.add(pn);
       ArrayList<Client> clients=new ArrayList<Client>();
       
       //Client df=new Client(1,"Daria", "Falkowska", "darfalk", null);
       ArrayList<Trip> trips=new ArrayList<Trip>();
       //Trip first=new Trip(0, 20,0, "Poland", "Warsaw","2021,10,21","2021,10,30",350, jk);
       //trips.add(first);
       Manager manager=new Manager("manager");
       Office touristOffice=new Office(0, guides, clients, trips, manager);
       
      
       touristOffice.userMenu();
       
    }
    
}
