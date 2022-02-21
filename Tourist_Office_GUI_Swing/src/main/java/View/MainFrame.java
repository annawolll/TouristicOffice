package View;

import Model.Client;
import Model.ClientTableModel;
import Model.GuideTableModel;
import Model.TourGuide;
import javax.swing.*;
import java.awt.event.*;
import Model.Trip;
import Model.TripTableModel;
import java.util.ArrayList;
import javax.swing.event.ListSelectionListener;

/**Class representing main window of GUI
 *
 * @author Anna Wołoszyn
 * @version 3.1
 */
public class MainFrame {
    //Buttons present in main window and in logging/register windows:
    private JButton submitBtn;
    private JButton okLogBtn;
    private JButton registerBtn;
    private JRadioButton logClient;
    private JRadioButton logEmployee;
    private JRadioButton logManager;
    private JRadioButton regClient;
    private JRadioButton regEmployee;
    //Fields for logging parameters:
    private JTextField login;
    private JPasswordField password;
    //Fields for register parameters:
    private JTextField name;
    private JTextField surname;
    private JTextField newPassword;
    //Client's options listed in JRadiobuttons:
    private JRadioButton showBooked;
    private JRadioButton showTrips;
    private JRadioButton bookTrip;
    private JRadioButton tripCountry;
    //Guide's options listed in JRadioButtons
    private JRadioButton showAssigned;
    //Manager's options listed in JRadioButtons
    private JRadioButton showClients;
    private JRadioButton showGuides;
    private JRadioButton addTrip;
    private JRadioButton deleteTrip;
    //Windows for users:
    private JFrame mainMenu;
    private JFrame employeeMenu;
    private JFrame clientMenu;
    private JFrame managerMenu;
    private JFrame register;
    private JFrame logging;
    private JFrame newTripData;
    //Additional fields
    private JTextField typeCountry;
    private JTextField country;
    private JTextField city;
    private JTextField price;
    private JTextField maxSeats;
    private JTextField beg;
    private JTextField end;
    private JTextField tg;

    private JRadioButton allInclusive;
    private JRadioButton breakf_dinner;
    private JRadioButton selfCat;
    private JButton submitNewTrip;
    private JTable trips;
    
    /**Method returning true or false depending on the selection of the button
     */
    public boolean allInIsSelected() {
        return allInclusive.isSelected();
    }
    /**Method returning true or false depending on the selection of the button
     */
    public boolean breakf_dinnerIsSelected() {
        return breakf_dinner.isSelected();
    }
    /**Method returning true or false depending on the selection of the button
     */
    public boolean selfCatIsSelected() {
        return selfCat.isSelected();
    }
    /**Method returning true or false depending on the selection of the button
     */
    public boolean logClientisChecked() {
        return logClient.isSelected();
    }
    /**Method returning true or false depending on the selection of the button
     */
    public boolean logEmployeeisChecked() {
        return logEmployee.isSelected();
    }
    /**Method returning true or false depending on the selection of the button
     */
    public boolean logManagerisChecked() {
        return logManager.isSelected();
    }
    /**Method returning true or false depending on the selection of the button
     */
    public boolean regClientisChecked() {
        return regClient.isSelected();
    }
    /**Method returning true or false depending on the selection of the button
     */
    public boolean regEmployeeisChecked() {
        return regEmployee.isSelected();
    }
    /**Method returning typed login
     */
    public int getLogin() {
        return Integer.parseInt(login.getText());
    }
    /**Method returning typed password
     */
    public String getPassword() {
        return password.getText();
    }
    /**Method returning typed name
     */
    public String getName() {
        return name.getText();
    }
    /**Method returning typed surname
     */
    public String getSurname() {
        return surname.getText();
    }
    /**Method returning typed new password
     */
    public String getNewPassword() {
        return newPassword.getText();
    }
    /**Method returning typed country
     */
    public String getTypedCountry() {
        return typeCountry.getText();
    }
    /**Method returning typed country
     */
    public String getCountry() {
        return country.getText();
    }
    /**Method returning typed city
     */
    public String getCity() {
        return city.getText();
    }
    /**Method returning typed price
     */
    public double getPrice() {
        return Double.parseDouble(price.getText());
    }
    /**Method returning typed maximum num of seats
     */
    public int getMaxSeats() {
        return Integer.parseInt(maxSeats.getText());
    }
    /**Method returning typed beggining date
     */
    public String getBegDate() {
        return beg.getText();
    }
    /**Method returning typed end date
     */
    public String getEndDate() {
        return end.getText();
    }
    /**Method returning typed guide
     */
    public String getGuide() {
        return tg.getText();
    }
    /**Method returning JTable of trips
     */
    public JTable getTrips() {
        return trips;
    }
    /**Activating of the ,,Delete Trip" button
     */
    public void activateDeleteTrip() {
        deleteTrip.setEnabled(true);
    }
    /**Activating of the ,,Book Trip" button
     */
    public void activateBookTrip() {
        bookTrip.setEnabled(true);
    }

    public MainFrame() {
        mainMenu = new JFrame("Touristic Office");
        
        mainMenu.setSize(350, 350);//Setting the size and location of main frame
        mainMenu.setLocation(300, 250);
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Making sure the frame closes when x is clicked
        mainMenu.setDefaultLookAndFeelDecorated(true);//Setting the look and feel
        JLabel label1 = new JLabel("Welcome to our Touristic Office!");//Entry label
        label1.setBounds(30, 10, 200, 30);
        //Adding the JRadioButtons of main menu:
        logClient = new JRadioButton("Log in as a client.");//Creating a new JRadioButton
        logClient.setActionCommand("logClient");//Setting the action command for the buttton
        logClient.setBounds(30, 50, 200, 20);//Setting the coordinates and size of the button
        mainMenu.add(logClient);//Adding the button to the frame
        logEmployee = new JRadioButton("Log in as an employee.");//All steps analogically as first button
        logEmployee.setActionCommand("logEmployee");
        logEmployee.setBounds(30, 80, 200, 20);
        mainMenu.add(logEmployee);
        logManager = new JRadioButton("Log in as the manager.");//All steps analogically as first button
        logManager.setActionCommand("logManager");
        logManager.setBounds(30, 110, 200, 20);
        mainMenu.add(logManager);
        regClient = new JRadioButton("Register as a Client.");//All steps analogically as first button
        regClient.setActionCommand("regClient");
        regClient.setBounds(30, 140, 200, 20);
        mainMenu.add(regClient);
        regEmployee = new JRadioButton("Register as an employee.");//All steps analogically as first button
        regEmployee.setActionCommand("regEmployee");
        regEmployee.setBounds(30, 170, 200, 20);
        mainMenu.add(regEmployee);

        ButtonGroup group = new ButtonGroup();//Grouping hte buttons so that only one can be chosen at once
        group.add(logClient);
        group.add(logEmployee);
        group.add(logManager);
        group.add(regClient);
        group.add(regEmployee);

        submitBtn = new JButton("Submit");//Creating hte submit button
        submitBtn.setBounds(50, 230, 100, 40);
        mainMenu.add(submitBtn);
        //add elements to the frame
        mainMenu.add(label1);
        mainMenu.setLayout(null);
        mainMenu.setVisible(true);//Making the frame appear on screen
    }

    /**
     * The listener will be the event when the button is pressed
     */
    public void submitBtnActionListener(ActionListener aL) {
        // Since the listener will be set only when it is
        // initialized in the controller, the button here
        // will do nothing when this method is not called    
        submitBtn.addActionListener(aL);
    }
     /**The listener will be the event when the button is pressed
     */
    public void okLogBtnActionListener(ActionListener aL) {
        okLogBtn.addActionListener(aL);
    }
    /**The listener will be the event when the button is pressed
     */
    public void registerBtnActionListener(ActionListener aL) {
        registerBtn.addActionListener(aL);
    }
    /**The listener will be the event when the button is pressed
     */
    public void submitTripBtnActionListener(ActionListener aL) {
        submitNewTrip.addActionListener(aL);
    }
    /**The listener will be the event when the button is pressed
     */
    public void showAssignedActionListener(ActionListener aL) {
        showAssigned.addActionListener(aL);
    }
    /**The listener will be the event when the button is pressed
     */
    public void clientOptionsActionListener(ActionListener aL) {
        showBooked.addActionListener(aL);
        showTrips.addActionListener(aL);
        tripCountry.addActionListener(aL);
    }
    /**The listener will be the event when the button is pressed
     */
    public void bookTripActionListener(ActionListener aL) {
        bookTrip.addActionListener(aL);
    }
    /**The listener will be the event when one of the buttons is pressed
     */
    public void managerOptionsActionListener(ActionListener aL) {
        showTrips.addActionListener(aL);
        showClients.addActionListener(aL);
        showGuides.addActionListener(aL);
        addTrip.addActionListener(aL);
        deleteTrip.addActionListener(aL);
    }
    /**The listener will be the event when a row of JTable is clicked
     */
    public void jTableSelectionListener(JTable tab, ListSelectionListener lS) {
        trips.getSelectionModel().addListSelectionListener(lS);
    }
    /**The listener will be the event when there is an attempt to close the window
     */
    public void windowCloseListener(WindowAdapter wa) {
        mainMenu.addWindowListener(wa);
    }
/**Showing the window wwhere user can log in
 */
    public void showLogInWindow() {
        logging = new JFrame("Log in");
        logging.setSize(300, 200);
        logging.setLocation(200, 150);
        logging.setDefaultLookAndFeelDecorated(true);
        JLabel label2 = new JLabel("Login:");//Adding label to the frame
        label2.setBounds(10, 20, 100, 30);//Setting size and place of label
        logging.add(label2);
        JLabel label3 = new JLabel("Password:");//Adding label to the frame
        label3.setBounds(10, 60, 100, 30);//Setting size and place of label
        logging.add(label3);
        login = new JTextField();
        login.setBounds(120, 20, 150, 30);//Setting size and place of field
        logging.add(login);
        password = new JPasswordField();
        password.setBounds(120, 60, 150, 30);//Setting size and place of field
        logging.add(password);
        okLogBtn = new JButton("OK");//Adding button to the frame
        okLogBtn.setBounds(120, 120, 60, 30);//Setting size and place of button
        logging.add(okLogBtn);
        logging.setLayout(null);
        logging.setVisible(true);//Making the frame appear on screen

    }
/**Showing the window wwhere user can register
 */
    public void showRegisterWindow() {
        register = new JFrame("Register");
        register.setSize(300, 300);
        register.setLocation(200, 150);
        register.setDefaultLookAndFeelDecorated(true);
        JLabel label2 = new JLabel("Your name:");//Adding label to the frame
        label2.setBounds(10, 20, 100, 30);
        register.add(label2);
        JLabel label3 = new JLabel("Your surname:");//Adding label to the frame
        label3.setBounds(10, 60, 100, 30);
        register.add(label3);
        JLabel label4 = new JLabel("New password:");//Adding label to the frame
        label4.setBounds(10, 100, 100, 30);
        register.add(label4);
        name = new JTextField();//Adding field to the frame
        name.setBounds(120, 20, 150, 30);
        register.add(name);
        surname = new JTextField();//Adding field to the frame
        surname.setBounds(120, 60, 150, 30);
        register.add(surname);
        newPassword = new JTextField();//Adding field to the frame
        newPassword.setBounds(120, 100, 150, 30);
        register.add(newPassword);
        registerBtn = new JButton("OK");//Adding button to the frame
        registerBtn.setBounds(120, 150, 60, 30);
        register.add(registerBtn);//Adding button to the frame
        register.setLayout(null);
        register.setVisible(true);//Making the frame appear on screen
    }
/**Clearing al the fields of register window
 */
    public void clearRegisterData() {
        name.setText("");
        surname.setText("");
        newPassword.setText("");
    }
/**Clearing al the fields of logging window
 */
    public void clearLoggingData() {
        login.setText("");
        password.setText("");
    }
    /**Showing the window of client's options
 */
    public void showClientMenu() {
        clientMenu = new JFrame("Client's Menu");
        clientMenu.setSize(750, 500);
        clientMenu.setLocation(200, 150);
        clientMenu.setDefaultLookAndFeelDecorated(true);
        showBooked = new JRadioButton("Show booked trips");
        showBooked.setActionCommand("showBooked");
        showBooked.setBounds(10, 20, 170, 30);
        clientMenu.add(showBooked);
        showTrips = new JRadioButton("Show available trips");
        showTrips.setActionCommand("showAvailable");
        showTrips.setBounds(10, 60, 170, 30);
        clientMenu.add(showTrips);
        bookTrip = new JRadioButton("Book a trip");
        bookTrip.setEnabled(false);
        bookTrip.setActionCommand("bookTrip");
        bookTrip.setBounds(10, 100, 170, 30);
        clientMenu.add(bookTrip);
        typeCountry = new JTextField();
        typeCountry.setBounds(20, 170, 130, 30);
        clientMenu.add(typeCountry);
        tripCountry = new JRadioButton("Show trips for typed country");
        tripCountry.setActionCommand("tripCountry");
        tripCountry.setBounds(10, 210, 200, 30);
        clientMenu.add(tripCountry);
        ButtonGroup group = new ButtonGroup();//Grouping hte buttons so that only one can be chosen at once
        group.add(showBooked);
        group.add(showTrips);
        group.add(bookTrip);
        group.add(tripCountry);

        clientMenu.setLayout(null);
        clientMenu.setVisible(true);//Making the frame appear on screen
    }
     /**Showing a success notification window 
      */
    public void showRegisterSuccess(int login) {
        JOptionPane.showMessageDialog(register, "Successfully registered.\nYour login is: " + login);
    }
    /**Showing a success notification window 
      */
    public void showSuccesNotification(int windowCode) {
        switch (windowCode) {
            case 2:
                JOptionPane.showMessageDialog(clientMenu, "Operation was succesful.");
                break;
            case 3:
                JOptionPane.showMessageDialog(managerMenu, "Operation was succesful.");
                break;
            case 4:
                JOptionPane.showMessageDialog(newTripData, "Added successfully.");
        }
    }
    /**Showing a failure notification window for different frames
      */
    public void showFailNotification(int windowCode) {
        switch (windowCode) {
            case 3:
                JOptionPane.showMessageDialog(managerMenu, "Operation was unsuccesful.");
                break;
            case 4:
                JOptionPane.showMessageDialog(logging, "Incorrect login or password.");
                break;
            case 5:
                JOptionPane.showMessageDialog(newTripData, "Operation unsuccesful, check your input.");
                break;
            case 6:
                JOptionPane.showMessageDialog(register, "Operation unsuccesful, check your input.");
                break;
        }
    }
/**Showing a window with employee's options
 */
    public void showEmployeeMenu() {
        employeeMenu = new JFrame("Employee's Menu");
        employeeMenu.setSize(750, 300);
        employeeMenu.setLocation(200, 150);
        employeeMenu.setDefaultLookAndFeelDecorated(true);
        showAssigned = new JRadioButton("Print assigned trips");
        showAssigned.setActionCommand("showAssigned");
        showAssigned.setBounds(10, 20, 170, 30);
        employeeMenu.add(showAssigned);
        employeeMenu.setLayout(null);
        employeeMenu.setVisible(true);//Making the frame appear on screen
    }
    /**Showing a list of trips in chosen window
     */
    public void showTrips(ArrayList<Trip> trs, int userCode) {
        if ((trs != null) && (trs.size() != 0)) {//Checking if there's anything to print
            trips = new JTable(new TripTableModel(trs));//Creating a JTable from an own Model of AbstractTable
            trips.setRowSelectionAllowed(true);//Alowing selection of rows

            JScrollPane jsp = new JScrollPane(trips);//Creating a JScrollPane from table
            JPanel panel = new JPanel();//Creating a JPane
            panel.add(jsp);//Adding JScrollPane to JPane
            panel.setBounds(150, 20, 600, 200);

            if (userCode == 1) {//1 is the code for employee
                employeeMenu.add(panel);
                employeeMenu.setVisible(true);//Making the frame appear on screen
            }
            if (userCode == 2) {
                clientMenu.add(panel);
                clientMenu.setVisible(true);//Making the frame appear on screen
            }
            if (userCode == 3) {
                managerMenu.add(panel);
                managerMenu.setVisible(true);//Making the frame appear on screen
            }
        } else {//If the trips' list is empty
            JLabel lab = new JLabel("Trips' list is empty.");//Creating a JLabel
            lab.setBounds(200, 20, 200, 30);
            switch (userCode) {//Deciding where to put the label
                case 3:
                    managerMenu.add(lab);
                    managerMenu.setVisible(true);//Making the frame appear on screen
                    break;
                case 2:
                    clientMenu.add(lab);
                    clientMenu.setVisible(true);//Making the frame appear on screen
                    break;
                case 1:
                    employeeMenu.add(lab);
                    employeeMenu.setVisible(true);//Making the frame appear on screen
                    break;
            }
        }
    }
    /**Showing a list of clients in manager window
     */
    public void showClients(ArrayList<Client> c) {
        if (c != null) {//Checking if there's anything to show
            JTable clients = new JTable(new ClientTableModel(c));//Creating a JTable from an own Model of AbstractTable
            JScrollPane jsp = new JScrollPane(clients);//Creating a JScrollPane from table
            JPanel panel = new JPanel();//Creating a JPanel
            panel.add(jsp);//Adding the JScrollPane to JPanel
            panel.setBounds(150, 20, 600, 200);
            managerMenu.add(panel);
            managerMenu.setVisible(true);//Making the frame appear on screen
        } else {//If the list is empty
            JLabel lab = new JLabel("Clients' list is empty.");
            lab.setBounds(200, 20, 200, 30);
            managerMenu.add(lab);
            managerMenu.setVisible(true);//Making the frame appear on screen
        }
    }
    /**Showing a list of guides in manager window
     */
    public void showGuides(ArrayList<TourGuide> tgs) {
        if (tgs != null) {//Checking if there's anything to show
            JTable guides = new JTable(new GuideTableModel(tgs));//Creating a JTable from an own Model of AbstractTable
            JScrollPane jsp = new JScrollPane(guides);//Creating a JScrollPane from table
            JPanel panel = new JPanel();//Creating a JPanel
            panel.add(jsp);//Adding the JScrollPane to JPanel
            panel.setBounds(150, 20, 600, 200);
            managerMenu.add(panel);
            managerMenu.setVisible(true);//Making the frame appear on screen
        } else {//If the list is empty
            JLabel lab = new JLabel("Clients' list is empty.");
            lab.setBounds(200, 20, 200, 30);
            managerMenu.add(lab);
            managerMenu.setVisible(true);//Making the frame appear on screen
        }
    }
/**Showing the window with manager's options
 */
    public void showManagerMenu() {
        managerMenu = new JFrame("Manager's Menu");
        managerMenu.setSize(750, 300);//Setting the size and location of frame
        managerMenu.setLocation(200, 150);
        managerMenu.setDefaultLookAndFeelDecorated(true);
        showTrips = new JRadioButton("Print available trips");//Adding a JRadioButton
        showTrips.setActionCommand("showTrips");
        showTrips.setBounds(10, 20, 170, 30);
        managerMenu.add(showTrips);
        addTrip = new JRadioButton("Add a trip");//Adding a JRadioButton
        addTrip.setActionCommand("addTrip");
        addTrip.setBounds(10, 60, 170, 30);
        managerMenu.add(addTrip);
        deleteTrip = new JRadioButton("Delete a trip");//Adding a JRadioButton
        deleteTrip.setEnabled(false);
        deleteTrip.setActionCommand("deleteTrip");
        deleteTrip.setBounds(10, 100, 170, 30);
        managerMenu.add(deleteTrip);
        showGuides = new JRadioButton("Show employed guides");//Adding a JRadioButton
        showGuides.setActionCommand("showGuides");
        showGuides.setBounds(10, 140, 170, 30);
        managerMenu.add(showGuides);
        showClients = new JRadioButton("Show registered clients");//Adding a JRadioButton
        showClients.setActionCommand("showClients");
        showClients.setBounds(10, 180, 170, 30);
        managerMenu.add(showClients);
        ButtonGroup group = new ButtonGroup();//Grouping hte buttons so that only one can be chosen at once
        group.add(showGuides);
        group.add(showTrips);
        group.add(addTrip);
        group.add(deleteTrip);
        group.add(showClients);
        managerMenu.setLayout(null);
        managerMenu.setVisible(true);//Making the frame appear on screen
    }
    /**Showing a window for manager to type the new trip's data
    */
    public void showNewTripDataWindow() {
        newTripData = new JFrame("New Trip's data");
        newTripData.setSize(500, 500);
        newTripData.setLocation(200, 150);
        newTripData.setDefaultLookAndFeelDecorated(true);
        JLabel c = new JLabel("Country:");//Adding a JRadioButton
        c.setBounds(10, 10, 100, 30);
        newTripData.add(c);
        country = new JTextField();//Adding a JRadioButton
        country.setBounds(140, 10, 150, 30);
        newTripData.add(country);
        JLabel ct = new JLabel("City:");//Adding a JRadioButton
        ct.setBounds(10, 50, 100, 30);
        newTripData.add(ct);
        city = new JTextField();//Adding a JRadioButton
        city.setBounds(140, 50, 150, 30);
        newTripData.add(city);
        JLabel p = new JLabel("Price:");//Adding a JRadioButton
        p.setBounds(10, 90, 100, 30);
        newTripData.add(p);
        price = new JTextField();//Adding a JRadioButton
        price.setBounds(140, 90, 150, 30);
        newTripData.add(price);
        JLabel ms = new JLabel("Max num of seats:");//Adding a JRadioButton
        ms.setBounds(10, 130, 200, 30);
        newTripData.add(ms);
        maxSeats = new JTextField();//Adding a JRadioButton
        maxSeats.setBounds(140, 130, 150, 30);
        newTripData.add(maxSeats);
        JLabel b = new JLabel("Begin date:");//Adding a JRadioButton
        b.setBounds(10, 170, 100, 30);
        newTripData.add(b);
        beg = new JTextField();//Adding a JRadioButton
        beg.setBounds(140, 170, 150, 30);
        newTripData.add(beg);
        JLabel e = new JLabel("End date:");//Adding a JRadioButton
        e.setBounds(10, 210, 100, 30);
        newTripData.add(e);
        end = new JTextField();//Adding a JRadioButton
        end.setBounds(140, 210, 150, 30);
        newTripData.add(end);
        JLabel t = new JLabel("Tour Guide:");//Adding a JRadioButton
        t.setBounds(10, 250, 100, 30);
        newTripData.add(t);
        tg = new JTextField();//Adding a JRadioButton
        tg.setBounds(140, 250, 150, 30);
        newTripData.add(tg);
        JLabel ci = new JLabel("Catering:");//Adding a JRadioButton
        ci.setBounds(10, 290, 100, 30);
        newTripData.add(ci);
        allInclusive = new JRadioButton("ALL_INCLUSIVE");//Adding a JRadioButton
        allInclusive.setBounds(140, 290, 200, 30);
        breakf_dinner = new JRadioButton("BREAKFAST_DINNER");//Adding a JRadioButton
        breakf_dinner.setBounds(140, 330, 200, 30);
        selfCat = new JRadioButton("SELF_CATERING");//Adding a JRadioButton
        selfCat.setBounds(140, 370, 200, 30);
        ButtonGroup bg = new ButtonGroup();//Grouping hte buttons so that only one can be chosen at once
        bg.add(allInclusive);
        bg.add(breakf_dinner);
        bg.add(selfCat);
        newTripData.add(allInclusive);
        newTripData.add(breakf_dinner);
        newTripData.add(selfCat);
        submitNewTrip = new JButton("Submit");//Adding a button
        submitNewTrip.setBounds(150, 410, 200, 30);
        newTripData.add(submitNewTrip);
        newTripData.setLayout(null);
        newTripData.setVisible(true);//Making the frame appear on screen
    }
}
