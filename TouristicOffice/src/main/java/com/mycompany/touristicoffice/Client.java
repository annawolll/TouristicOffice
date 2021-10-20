/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.touristicoffice;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.util.Scanner;

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
   
   public void printTrips(){
   for(Trip trip:boughtTrips){
       StringBuilder sb=new StringBuilder();
       sb.append(trip.getCountry());
       sb.append("  ");
       sb.append(trip.getCity());
       sb.append("  ");
       sb.append(trip.getDepartureDate());
       sb.append("  ");
       sb.append(trip.getArrivalDate());
       sb.append("  ");
       sb.append(trip.getPrice());
       sb.append("  ");
       String line=sb.toString();
   System.out.println(line);
   sb.setLength(0);//Check this function
   }
   
   }
    
   public void ClientOptionsMenu(Office of){
   System.out.println("What would you like to do?\n1.Show booked trips.\n2.Show available trips.\n3.Book a trip.");
    Scanner scanner=new Scanner(System.in);
   int choice=scanner.nextInt();
   if(choice==1){
   System.out.println("Booked trips info:\nCountry  City    Departure Date  Arrival Date    Price\n");
   printTrips();
   }
   if(choice==2){
   System.out.println("Available trips to buy:\nCountry City    Departure Date Arrival Date Price   Number of Seats");
   of.printAvailableTrips();
   }
   if(choice==3){
   
   }
   }
}
