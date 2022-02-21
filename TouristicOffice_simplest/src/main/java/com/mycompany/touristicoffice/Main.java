/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.touristicoffice;

import java.util.ArrayList;

import java.io.IOException;

/**
 *
 * @author User
 */
public class Main {
    public static void main(String[] args){
       ArrayList<TourGuide> guides=new ArrayList<TourGuide>();
       ArrayList<Client> clients=new ArrayList<Client>();
       ArrayList<Trip> trips=new ArrayList<Trip>();
       Manager manager=new Manager("manager");
       Office touristOffice=new Office(0, guides, clients, trips, manager);
       try{
          touristOffice.readDataFromFiles();
       }
        catch(IOException ex){
           System.out.println("IOException caught while reading data.");
       }
       catch(ClassNotFoundException ex){
           System.out.println("ClassNotFoundException caught while reading data");
       }
       
       touristOffice.userMenu();
       
       try{
           touristOffice.writeDataToFiles();
       }
       catch(IOException ex){
           System.out.println("IOException caught while writing data.");
       }
       catch(ClassNotFoundException ex){
           System.out.println("ClassNotFoundException caught while writing data");
       }
       
    }
    
}
