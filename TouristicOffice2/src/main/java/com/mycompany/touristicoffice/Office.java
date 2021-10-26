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
    
    public void addClient(String name, String surname, String password){
    int id=0;
    if(clients.size()!=0){
    id=clients.size()+1;
    }
    Client newClient=new Client(id,name,surname,password,null);
    clients.add(newClient);
    }
    
    public void addTourGuide(String name, String surname,String password){
    int id=0;
    if(employees.size()!=0){
        id=employees.size()+1;
    }
    TourGuide newGuide=new TourGuide(id,name, surname,password);
    employees.add(newGuide);
    }
    
    public void deleteTrip(int id){
    trips.remove(id);
    }
    
    public void printGuides(){
        int counter=1;
        for(TourGuide tg:employees){
            System.out.println(counter+". "+tg.getID()+" "+tg.getName()+" "+tg.getSurname()+"\n");
        }
    }
    
    public void editTripDetails(int Index){
   System.out.println("Which detail would you like to edit?\n");
   System.out.println("1.MaxSeats. 2.Country. 3.City. 4.Arrival Date. 5.Departure Date. 6.Price. 7.Guide.");
    Scanner scanner=new Scanner(System.in);
    int choice=scanner.nextInt();
    System.out.println("Type new value of the field or choose a guide number if you answered '7'");
    switch(choice){
    case(1):
        trips.get(Index).setMaxSeats(scanner.nextInt());
        break;
     case(2):
         trips.get(Index).setCountry(scanner.nextLine());
         break;
     case(3):
         trips.get(Index).setCity(scanner.nextLine());
         break; 
     case(4):
         trips.get(Index).setArrivalDate(scanner.nextLine());
         break;
     case(5):
         trips.get(Index).setDepartureDate(scanner.nextLine());
         break;
     case(6):
         trips.get(Index).setPrice(scanner.nextDouble());
         break;
     case(7):
         printGuides();
         trips.get(Index).setGuide(employees.get(scanner.nextInt()-1));
    }
    }
    
    public Trip sellTrip(int Index){
        Trip temp=trips.get(Index);
        //Checking if there are some free seats to book
    if(temp.getMaxSeats()-temp.getBoughtSeats()>0){
    trips.get(Index).incrementBoughtSeats();
    temp.incrementBoughtSeats();
    return temp;
    }
    return null;
    }
    
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
        int counter=1;
    for(Trip trip:trips){
       StringBuilder sb=new StringBuilder();
       sb.append(counter);
       sb.append(". ");
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
    counter++;
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
    System.out.println("3.Register as a Client.\n4.Register as an employee.\n5.Log in as the Manager.");
    int choice;
    Scanner scanner=new Scanner(System.in);
    choice=scanner.nextInt();
    if(choice==1 ||choice==2 ||choice==5){
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
        TourGuide loggedTG=logTourGuideIn(id,password);
            if(loggedTG!=null){
    
    }
    }
    if(choice==5){
        boolean ManagerAccess=logManagerIn(password);//Id unnecessary here
    }
    }
    else if(choice==3 ||choice==4){
        System.out.println("Type your name, surname, new password:\n");
        String name=scanner.nextLine();
        String surname=scanner.nextLine();
        String password=scanner.nextLine();
        if(choice==4){
            addTourGuide(name, surname,password);
            System.out.println("Succesfully created account\nYour id is: "+employees.size());
        }
        if(choice==3){
            addClient(name,surname,password);
            System.out.println("Succesfully created account\nYour id is:"+clients.size());
        }
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
