/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.lab.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 * Servlet responsible for logging or registering of users
 *
 * @author Anna Wołoszyn
 * @version 5.0
 */
@WebServlet(name = "LoggingRegisteringServlet", urlPatterns = {"/LoggingRegisteringServlet"})
public class LoggingRegisteringServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);

        if (session.getAttribute("office") == null) {//Checking if there was initialization of the office
            //Creating empty arraylists of office's data
            ArrayList<TourGuide> employees = new ArrayList<>();
            ArrayList<Client> clients = new ArrayList<>();
            ArrayList<Trip> trips = new ArrayList<>();
            Manager manager = new Manager("manager");
            //Initializing office object with created lists and manager object
            Office o = new Office(0, employees, clients, trips, manager);
            session.setAttribute("office", o);

            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
            } catch (ClassNotFoundException cnfe) {
                System.err.println(cnfe.getMessage());
            }

            try {
                String url = "jdbc:derby://localhost:1527/Lab5";
                Connection conn = DriverManager.getConnection(url, "Lab5", "Lab5");
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM CLIENTS");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String surname = rs.getString("surname");
                    String passw = rs.getString("password");
                    o.addClient(name, surname, passw);
                }
                rs.close();

                Statement statement2 = conn.createStatement();
                ResultSet rs2 = statement2.executeQuery("SELECT * FROM EMPLOYEES");
                while (rs2.next()) {
                    int id = rs2.getInt("id");
                    String name = rs2.getString("name");
                    String surname = rs2.getString("surname");
                    String passw = rs2.getString("password");
                    o.addTourGuide(name, surname, passw);
                }
                rs.close();
                Statement statement3 = conn.createStatement();
                ResultSet rs3 = statement3.executeQuery("SELECT * FROM TRIPS");
                while (rs3.next()) {
                    int id = rs3.getInt("id");
                    int maxSeats = rs3.getInt("maxseats");
                    int boughtSeats = rs3.getInt("boughtseats");
                    String country = rs3.getString("country");
                    String city = rs3.getString("city");
                    String dep = rs3.getString("departure");
                    String arr = rs3.getString("arrival");
                    double price = rs3.getDouble("price");
                    int guideID = rs3.getInt("tourguideid");
                    CateringInfo ci = CateringInfo.valueOf(rs3.getString("catering"));
                    TourGuide t = o.findGuideByID(guideID);
                    o.addTrip(country, city, price, maxSeats, boughtSeats, dep, arr, t, CateringInfo.ALL_INCLUSIVE);
                }
                rs.close();
                Statement statement4 = conn.createStatement();
                ResultSet rs4 = statement4.executeQuery("SELECT * FROM MANAGER");
                while (rs4.next()) {
                    String passw = rs4.getString("password");
                    Manager m = new Manager(passw);
                }
                rs.close();
                session.setAttribute("Connection", conn);

            } catch (SQLException e) {
                throw new Error("Problem", e);
            }
        }

        Office tmp = (Office) session.getAttribute("office");//Getting the office object saved to session

        if (request.getParameter("person") != null) {//If one radiobutton is selected (desired state)

            Client loggedC = (Client) session.getAttribute("client");
            boolean correctClient = false;
            int visitCounter = 1;
            if (request.getParameter("person").equals("logclient")) {//If the logging of client
                try {
                    int login = Integer.parseInt(request.getParameter("login"));
                    String password = request.getParameter("password");
                    //Attempt to log the client in
                    loggedC = tmp.logClientIn(login, password);
                    if (loggedC != null) {//If logging was successful
                        correctClient = true;
                        session.setAttribute("user", "client");//Setting the user for client
                        Cookie[] cookies = request.getCookies();//Getting cookies

                        if (cookies != null) {
                            for (Cookie cookie : cookies) {
                                if (cookie.getName().equals("visitCounter")) {//Getting the cookie storing number of visits
                                    visitCounter = Integer.parseInt(cookie.getValue());
                                    break;
                                }
                            }
                        }

                    } else {//If logging failed
                        out.println("Wrong login or password.");
                        out.println("<form action=\"LogOutServlet\" id=\"logOut\">\n"
                                + "            <input type=\"submit\" value=\"Return\" />\n"
                                + "        </form>");
                    }
                } catch (NumberFormatException ex) {
                    //If login wasn't a number
                    out.println("Wrong input data");
                    out.println("<form action=\"LogOutServlet\" id=\"logOut\">\n"
                            + "            <input type=\"submit\" value=\"Return\" />\n"
                            + "        </form>");
                }
            }
            if ((request.getParameter("person").equals("logclient") && correctClient)
                    || request.getParameter("person").equals("returnClient")) {
                //If correct logging of client occured or client is returning to their main menu

                Cookie[] cookies = request.getCookies();//Getting cookies
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("visitCounter")) {//Getting the cookie storing number of visits
                            visitCounter = Integer.parseInt(cookie.getValue());
                            break;
                        }
                    }
                }
                if (request.getParameter("person").equals("returnClient")) {
                    visitCounter--;
                }
                //Menu displayed to the user
                out.println("Welcome, " + loggedC.getName() + "!<br>This is your " + visitCounter + " visit."
                        + "<br><br>What would you like to do?<br><br>");
                out.println("<form action=\"ShowDataServlet\" id=\"showBooked\">\n"
                        + "<button type=\"submit\" name=\"option\" value=\"showBooked\">Show my booked trips.</button>"
                        + "        </form>");
                out.println("<form action=\"ShowDataServlet\" id=\"showAvailable\">\n"
                        + "<button type=\"submit\" name=\"option\" value=\"showTrips\">Show trips available to book.</button>"
                        + "        </form>");
                out.println("<form action=\"ShowDataServlet\" id=\"showCountry\">\n"
                        + "<button type=\"submit\" name=\"option\" value=\"showCountry\">Show trips for selected country.</button>"
                        + "<input type=\"text\" name=\"country\"/>\n"
                        + "        </form>");
                out.println("<form action=\"LogOutServlet\" id=\"logOut\">\n"
                        + "            <input type=\"submit\" value=\"Log out.\" />\n"
                        + "        </form>");

                session.setAttribute("client", loggedC);
                visitCounter++;//Incrementing counter of visits
                Cookie cookie = new Cookie("visitCounter", Integer.toString(visitCounter));
                response.addCookie(cookie);//Adding cookie to the response

            }

            TourGuide logged = (TourGuide) session.getAttribute("employee");
            boolean correctEmployee = false;
            if (request.getParameter("person").equals("logemployee")) {//If logging of an employee
                try {
                    int login = Integer.parseInt(request.getParameter("login"));
                    String password = request.getParameter("password");
                    //Attempt to log the employee in
                    logged = tmp.logTourGuideIn(login, password);
                    if (logged != null) {//Iflogging was successful
                        session.setAttribute("user", "employee");//Setting the user for employee
                        correctEmployee = true;
                    } else {
                        out.println("Wrong password or login.");
                        out.println("<form action=\"LogOutServlet\" id=\"logOut\">\n"
                                + "            <input type=\"submit\" value=\"Return\" />\n"
                                + "        </form>");
                    }
                } catch (NumberFormatException ex) {
                    //If login input was not a number
                    out.println("Wrong input data");
                    out.println("<form action=\"LogOutServlet\" id=\"logOut\">\n"
                            + "            <input type=\"submit\" value=\"Return\" />\n"
                            + "        </form>");
                }
            }

            if ((request.getParameter("person").equals("logemployee") && correctEmployee)
                    || request.getParameter("person").equals("returnEmployee")) {
                //If correct logging of employee occured or employee is returning to their options
                //Displaying options for empoyee
                out.println("Welcome, " + logged.getName() + "!<br><br>What would you like to do?<br><br>");
                out.println("<form action=\"ShowDataServlet\" id=\"showAssigned\">\n"
                        + "<button type=\"submit\" name=\"option\" value=\"showAssigned\">Show my assigned trips.</button>"
                        + "        </form>");
                out.println("<form action=\"LogOutServlet\" id=\"logOut\">\n"
                        + "            <input type=\"submit\" value=\"Log out.\" />\n"
                        + "        </form>");
                session.setAttribute("employee", logged);

            }

            boolean correctManager = false;
            if (request.getParameter("person").equals("logmanager")) {//If logging of the manager
                String password = request.getParameter("password");
                if (!password.equals("")) {
                    if (tmp.logManagerIn(password)) {
                        session.setAttribute("user", "manager");//Setting the user for manager
                        correctManager = true;
                    }
                } else {
                    out.println("Password is empty!");
                }
            }

            if ((request.getParameter("person").equals("logmanager") && correctManager)
                    || request.getParameter("person").equals("returnManager")) {
                //If correct logging of manager occured or manager is returning to their options
                //Display of options
                out.println("Successfully logged the manager.<br><br>What would you like to do?<br>");
                out.println("<form action=\"ShowDataServlet\" id=\"showClients\">\n"
                        + "<button type=\"submit\" name=\"option\" value=\"showClients\">Show clients registered in the office.</button>\n"
                        + "        </form>");
                out.println("<form action=\"ShowDataServlet\" id=\"showGuides\">\n"
                        + "<button type=\"submit\" name=\"option\" value=\"showGuides\">Show guides employed in the office.</button>\n"
                        + "        </form>");
                out.println("<form action=\"ShowDataServlet\" id=\"showAvailable\">\n"
                        + "<button type=\"submit\" name=\"option\" value=\"showTrips\">Show office's available trips.</button>\n"
                        + "        </form>");
                out.println("<form action=\"ShowDataServlet\" id=\"addTrip\">\n"
                        + "<button type=\"submit\" name=\"option\" value=\"addTrip\">Add a trip.</button>\n"
                        + "        </form>");
                out.println("<form action=\"LogOutServlet\" id=\"logOut\">\n"
                        + "            <input type=\"submit\" value=\"Log out.\" />\n"
                        + "        </form>");
            }

            if (request.getParameter("person").equals("regclient")) {//If registering of a client
                String name = request.getParameter("fname");
                String surname = request.getParameter("lname");
                String nPassword = request.getParameter("newPassword");
                if (name.equals("") || surname.equals("") || nPassword.equals("")) {
                    //Checking if any field is empty
                    out.println("All fields must be filled!");
                    out.println("<form action=\"LogOutServlet\" id=\"logOut\">\n"
                            + "            <input type=\"submit\" value=\"Return\" />\n"
                            + "        </form>");
                } else {
                    //Adding the client to the list
                    tmp.addClient(name, surname, nPassword);
                    int id = tmp.getClients().size() - 1;
                    try {
                        Connection con = (Connection) session.getAttribute("Connection");
                        Statement st = con.createStatement();
                        st.executeUpdate("INSERT INTO CLIENTS VALUES (" + String.valueOf(id) + ",'" + name + "','" + surname + "','" + nPassword + "')");
                    } catch (SQLException se) {
                        System.err.println(se.getMessage());
                    }
                    out.println("Successfully registered client.");
                    out.println("<form action=\"LogOutServlet\" id=\"logOut\">\n"
                            + "            <input type=\"submit\" value=\"Return\" />\n"
                            + "        </form>");
                }
            }
            if (request.getParameter("person").equals("regemployee")) {
                String name = request.getParameter("fname");
                String surname = request.getParameter("lname");
                String nPassword = request.getParameter("newPassword");
                if (name.equals("") || surname.equals("") || nPassword.equals("")) {
                    //Checking if any field is empty
                    out.println("All fields must be filled!");
                    out.println("<form action=\"LogOutServlet\" id=\"logOut\">\n"
                            + "            <input type=\"submit\" value=\"Return\" />\n"
                            + "        </form>");
                } else {
                    //Adding employee to the list
                    tmp.addTourGuide(name, surname, nPassword);
                    int id = tmp.getEmployees().size() - 1;
                    try {
                        Connection con = (Connection) session.getAttribute("Connection");
                        Statement st = con.createStatement();
                        st.executeUpdate("INSERT INTO EMPLOYEES VALUES (" + String.valueOf(id) + ",'" + name + "','" + surname + "','" + nPassword + "')");
                    } catch (SQLException se) {
                        System.err.println(se.getMessage());
                    }
                    out.println("Successfully registered employee.");
                    out.println("<form action=\"LogOutServlet\" id=\"logOut\">\n"
                            + "            <input type=\"submit\" value=\"Return\" />\n"
                            + "        </form>");
                }
            }
        } else {//If no radiobutton was clicked
            out.println("No option was chosen");
            out.println("<form action=\"LogOutServlet\" id=\"logOut\">\n"
                    + "            <input type=\"submit\" value=\"Return\" />\n"
                    + "        </form>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
