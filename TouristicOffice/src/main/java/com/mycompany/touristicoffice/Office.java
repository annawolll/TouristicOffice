/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.touristicoffice;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

/**
 *
 * @author User
 */
public class Office {
    private double accountBalance;
    ArrayList<TourGuide> employees;
    ArrayList<Client> clients;
    ArrayList<Trip> trips;
    Manager manager;

    public Office(double accountBalance, ArrayList<TourGuide> employees, ArrayList<Client> clients, ArrayList<Trip> trips, Manager m) {
        this.accountBalance = accountBalance;
        this.employees = employees;
        this.clients = clients;
        this.trips=trips;
        this.manager=m;
    }
    
    public void addTrip(String country, String city, double price, int maxSeats, String begin, String end, TourGuide tg){
       int id=0;
        if(trips.size()!=0){
        id=trips.size()+1;
        }
        Trip newTrip= new Trip(id,maxSeats,0, country,city, begin,end, price, tg);
        trips.add(newTrip);
    }
    public void deleteTrip(int id){
    trips.remove(id);
    }
    
    public void editTripDetails(){}
    
    public Client logClientIn(int id, String password){
        for(Client client:clients){
            if(client.getID()==id){
            if(client.getPassword()==password)
                return client;
            }
        }
        return null;
    }
    
    public void printAvailableTrips(){
    for(Trip trip:trips){
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
       sb.append(trip.getMaxSeats()-trip.getBoughtSeats());
       String line=sb.toString();
   System.out.println(line);
   sb.setLength(0);//Check this function
   }
    }
    
    public ArrayList<Trip> searchTripsforGuide(TourGuide tg){
        ArrayList<Trip> matchedTrips=new ArrayList<Trip>();
    for(Trip trip:trips){
     if(trip.getGuide()==tg){
     matchedTrips.add(trip);
     }
    }
    return matchedTrips;
    }
    
    public TourGuide logTourGuideIn(int id, String password){
        for(TourGuide guide:employees){
            if(guide.getID()==id){
            if(guide.getPassword()==password)
                return guide;
            }
        }
        return null;
    }
    
    public boolean logManagerIn(String password){
        if(manager.getPassword()==password) return true;
        return false;
    }
    
    public void userMenu(){
    System.out.println("Welcome to our Touristic Office!\n1.Log in as a Client.\n2.Log in as an employee.");
    System.out.println("3.Log in as the Manager.");
    int choice;
    Scanner scanner=new Scanner(System.in);
    choice=scanner.nextInt();
    System.out.println("Enter your ID and password:");
    int id=scanner.nextInt();
    String password=scanner.nextLine();
    if(choice==1){
    //logging of the client method
    Client loggedClient=logClientIn(id, password);
    if(loggedClient!=null){
    
    }
    }
    if(choice==2){
    //logging of the employee method
    }
    if(choice==3){
        
    }
    }
    
   //public static void main(){
       /*
       ArrayList<TourGuide> guides=new ArrayList<TourGuide>();
       ArrayList<Client> clients=new ArrayList<Client>();
       ArrayList<Trip> trips=new ArrayList<Trip>();
       Manager manager=new Manager("");
       Office touristOffice=new Office(0, guides, clients, trips, manager);
*/
   
    
}
