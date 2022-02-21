/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.MainFrame;
import Model.Client;
import Model.TourGuide;
import Model.Office;
import Model.CateringInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Class representing the controller of the program
 *
 * @author Anna Wołoszyn
 * @version 3.1
 */
public class Controller {
    
    private MainFrame view;///The view part
    private Office o;///The model part
    boolean wasBooked = false;///True if a trip was booked

    /**
     * Constructor of the controller
     */
    public Controller(Office o) {
        try {
            o.readDataFromFiles();//Reading data of trips, clients and employees from files
        } catch (IOException | ClassNotFoundException e) {
        }
        view = new MainFrame();//Creating the main window
        view.windowCloseListener(new WindowAdapter() {//Listening if user was trying to exit the app
            public void windowClosing(WindowEvent e) {
                try {
                    o.writeDataToFiles();//Saving data of trips, clients and employees to files
                } catch (IOException | ClassNotFoundException ex) {
                }
            }
        });

        //Listening for the action of pressing submit button
        view.submitBtnActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (view.logClientisChecked() || view.logEmployeeisChecked() || view.logManagerisChecked()) {
                    view.showLogInWindow();//Showing the logging window
                    //Listening for the action of pressing OK button to log in
                    view.okLogBtnActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            try{
                            if (view.logClientisChecked()) {//If logging of a client
                                Client c = o.logClientIn(view.getLogin(), view.getPassword());//Attempt to log in the client
                                if (c != null) {//If such logging data exist
                                    view.clearLoggingData();
                                    view.showClientMenu();//Showing the window of options for client

                                    //Waiting for client's choice of action
                                    view.clientOptionsActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            if (e.getActionCommand() == "showBooked") {
                                                if (wasBooked) {
                                                    try {
                                                        c.getBoughtTrips().remove(c.getBoughtTrips().size() - 1);
                                                    } catch (IndexOutOfBoundsException ex) {
                                                    }
                                                    wasBooked = false;
                                                }
                                                view.showTrips(c.getBoughtTrips(), 2);//Showing client's booked trips
                                            }
                                            if (e.getActionCommand() == "showAvailable") {
                                                view.showTrips(o.getTrips(), 2);//Showing available to book trips
                                                //Waiting for selecting a row
                                                view.jTableSelectionListener(view.getTrips(), new ListSelectionListener() {
                                                    public void valueChanged(ListSelectionEvent event) {
                                                        int chosenIndex = view.getTrips().getSelectedRow();//Getting the index of selected row
                                                        view.activateBookTrip();//Activating of JRadioButton "Book trip"
                                                        //Waiting for selecting the button
                                                        view.bookTripActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent e) {
                                                                try {
                                                                    c.bookTrip(o, chosenIndex);//Booking the trip of the chosen index
                                                                    wasBooked = true;//Setting the Booked info for true
                                                                } catch (IndexOutOfBoundsException ex) {
                                                                }
                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                            if (e.getActionCommand() == "tripCountry") {
                                                view.showTrips(o.filterTripsByCountry(view.getTypedCountry()), 2);//Showing trips for the typed country
                                            }
                                        }
                                    });
                                } else {
                                    view.showFailNotification(4);//If logging of the client failed
                                }
                            }
                            if (view.logEmployeeisChecked()) {//If logging of an employee
                                TourGuide tg = o.logTourGuideIn(view.getLogin(), view.getPassword());//Attempt to log in the employee
                                if (tg != null) {//If such logging data exist
                                    view.clearLoggingData();
                                    view.showEmployeeMenu();//Showing the window of employee's options
                                    //Waiting for user's choice
                                    view.showAssignedActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            view.showTrips(o.searchTripsforGuide(tg), 1);//Showing trips assigned to the employee 
                                        }
                                    });
                                } else {
                                    view.showFailNotification(4);//If logging of the employee failed
                                }
                            }
                            if (view.logManagerisChecked()) {//If logging of the manager
                                if (o.logManagerIn(view.getPassword())) {//If logging the manager in was successful
                                    view.clearLoggingData();
                                    view.showManagerMenu();//Showing of the manager's options window
                                    //Waiting for user's choice of buttons
                                    view.managerOptionsActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            if (e.getActionCommand() == "showClients") {
                                                view.showClients(o.getClients());//Showing the registered clients of the office
                                            }
                                            if (e.getActionCommand() == "showTrips") {
                                                view.showTrips(o.getTrips(), 3);//Showing the available trips of the office
                                                //Waiting for selection of a JTable's row
                                                view.jTableSelectionListener(view.getTrips(), new ListSelectionListener() {
                                                    public void valueChanged(ListSelectionEvent event) {
                                                        int chosenIndex = view.getTrips().getSelectedRow();//Getting the selected row's index
                                                        view.activateDeleteTrip();//Activation of "Delete Trip" JRadioButton
                                                        //Waiting for selection of the "Delete Trip"
                                                        view.managerOptionsActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent e) {
                                                                if (e.getActionCommand() == "deleteTrip") {
                                                                    try {
                                                                        o.deleteTrip(chosenIndex);//Deleting the selected trip
                                                                    } catch (IndexOutOfBoundsException ex) {
                                                                    }
                                                                }
                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                            if (e.getActionCommand() == "showGuides") {
                                                view.showGuides(o.getEmployees());//Showing all employed guides of the office
                                            }
                                            if (e.getActionCommand() == "addTrip") {
                                                view.showNewTripDataWindow();//Showing the window for typing new trip's information
                                                //Waiting for clicking the submit button
                                                view.submitTripBtnActionListener(new ActionListener() {
                                                    public void actionPerformed(ActionEvent e) {
                                                        //Checking the correctness of typed/selected data
                                                        if ((view.getCountry().equals("")) || (view.getCity().equals(""))
                                                                || (view.getBegDate().equals("")) || (view.getEndDate().equals(""))
                                                                || (o.findGuideBySurname(view.getGuide()) == null)
                                                                || !(view.allInIsSelected() || view.breakf_dinnerIsSelected() || view.selfCatIsSelected())) {
                                                            view.showFailNotification(5);//If something is wrong showing Fail notification
                                                        } else {//If everything is correct
                                                            try {
                                                                int i = 0;
                                                                TourGuide t = o.findGuideBySurname(view.getGuide());//Getting the typed guide by surname
                                                                //Checking which value of enum CateringInfo is selected
                                                                if (view.allInIsSelected()) {
                                                                    i = 1;
                                                                }
                                                                if (view.breakf_dinnerIsSelected()) {
                                                                    i = 2;
                                                                }
                                                                if (view.selfCatIsSelected()) {
                                                                    i = 3;
                                                                }
                                                                //Adding the trip with all input data
                                                                o.addTrip(view.getCountry(), view.getCity(), view.getPrice(),
                                                                        view.getMaxSeats(), view.getBegDate(), view.getEndDate(),
                                                                        t, CateringInfo.valueOf(i));
                                                            } catch (NumberFormatException ex) {//If number fields were filed with not a number
                                                                view.showFailNotification(5);
                                                            }
                                                            view.showSuccesNotification(4);
                                                        }
                                                    }
                                                });
                                            }
                                        }
                                    });
                                    
                                } else {
                                    view.showFailNotification(4);//If logging in of the manager was unsuccessful
                                }
                            }
                        }catch(NumberFormatException ex){//If login input was not a number
                        view.showFailNotification(4);
                        }
                        }
                    });
                }
                if (view.regClientisChecked() || view.regEmployeeisChecked()) {
                    view.showRegisterWindow();//Showing the register window
                    //Waiting for the action of pressing "Register" button
                    view.registerBtnActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            //Checking if the fields are not empty
                            if (view.getName().equals("") || view.getSurname().equals("") || view.getNewPassword().equals("")) {
                                view.showFailNotification(6);
                            } else {
                                if (view.regClientisChecked()) {//If the register of client
                                    o.addClient(view.getName(), view.getSurname(), view.getNewPassword());//Adding client with input data
                                    view.clearRegisterData();//Clearing the register window 
                                    view.showRegisterSuccess(o.getClients().size() - 1);//Showing success alert
                                }
                                
                                if (view.regEmployeeisChecked()) {//If the register of employee
                                    o.addTourGuide(view.getName(), view.getSurname(), view.getNewPassword());//Adding tour guide with input data
                                    view.clearRegisterData();//Clearing the register window
                                    view.showRegisterSuccess(o.getEmployees().size() - 1);//Showing success alert
                                }
                            }
                            
                        }
                    });
                }
            }
        });
    }
}
