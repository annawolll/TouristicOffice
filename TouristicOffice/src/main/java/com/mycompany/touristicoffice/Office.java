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

    public Office(double accountBalance, ArrayList<TourGuide> employees, ArrayList<Client> clients, ArrayList<Trip> trips) {
        this.accountBalance = accountBalance;
        this.employees = employees;
        this.clients = clients;
        this.trips=trips;
    }
    
    public void addTrip(String country, String city, double price, int maxSeats, Date begin, Date end, TourGuide tg){
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
    }
    if(choice==2){
    //loging of the employee method
    }
    if(choice==3){
        
    }
    }
    
   public static void main(){
       ArrayList<TourGuide> guides=new ArrayList<TourGuide>();
       ArrayList<Client> clients=new ArrayList<Client>();
       ArrayList<Trip> trips=new ArrayList<Trip>();
       Office touristOffice=new Office(0, guides, clients, trips);
   }
    
}
