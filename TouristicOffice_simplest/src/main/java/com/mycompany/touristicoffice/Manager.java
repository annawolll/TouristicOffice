/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.touristicoffice;

import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author Anna Wołoszyn
 */
public class Manager implements Serializable{
    private String password;

    public Manager(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    
    public void ManagerOptionsMenu(Office of){
        int choice=0;
        do{
        System.out.println("What would you like to do?\n1.Print trips.\n2.Add a trip.\n"
           + "3.Edit trip's info.\n4.Delete a trip.\n5.Print guides.\n6.Print clients.\n7.Escape.");
        Scanner scanner=new Scanner(System.in);
        choice=scanner.nextInt();
        if(choice==1){
        of.printAvailableTrips();
        }
        if(choice==2){
        System.out.println("Enter: max num of seats, country, city, departure date, arrival date, price and guide's id");
        int maxS, guideId;
        String Country,City, depDate, arrDate;
        double price;
        Scanner sc=new Scanner(System.in);
        maxS=sc.nextInt();
        sc.nextLine();
        Country=sc.nextLine();
        City=sc.nextLine();
        depDate=sc.nextLine();
        arrDate=sc.nextLine();
        price=sc.nextDouble();
        sc.nextLine();
        guideId=sc.nextInt();
        TourGuide tg=of.getGuideById(guideId);
        of.addTrip(Country,City,price,maxS,depDate,arrDate,tg);
        }
   if(choice==3){
       System.out.println("Which trip to edit? Type number from the list:");
       of.editTripDetails(scanner.nextInt()-1);
   }
   if(choice==4){
       System.out.println("Which trip to delete? Type number from the list:");
       of.deleteTrip(scanner.nextInt()-1);
   }
   if(choice==5){
        of.printGuides();
   }
   if(choice==6){
       of.printClients();
   }
   }while(choice!=7);
 }
}
