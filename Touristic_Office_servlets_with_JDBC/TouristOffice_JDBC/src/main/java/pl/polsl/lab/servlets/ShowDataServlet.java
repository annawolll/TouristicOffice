/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.lab.servlets;

import Model.Client;
import Model.Office;
import Model.TourGuide;
import Model.Trip;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet handling display of option chosen by the user
 *
 * @author Anna Wołoszyn
 * @version 5.0
 */
@WebServlet(name = "ShowDataServlet", urlPatterns = {"/ShowDataServlet"})
public class ShowDataServlet extends HttpServlet {

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
        Office o = (Office) session.getAttribute("office");
        Connection con = (Connection) session.getAttribute("Connection");
        Statement st = null;
        ResultSet rs = null;

        String user = (String) session.getAttribute("user");//Getting the user saved in session
        String action = request.getParameter("option");//Getting the choice of action
        ArrayList<Trip> tr = new ArrayList<Trip>();

        if ((user.equals("client") && action.equals("showTrips") || (user.equals("manager") && action.equals("showTrips")))) {
            //If available trips in the office are to be displayed either to client or to manager
            tr = o.getTrips();
            try {
                st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                rs = st.executeQuery("SELECT * FROM TRIPS");
            } catch (SQLException se) {
                System.err.println(se.getMessage());
            }
        }
        if (user.equals("client") && action.equals("showBooked")) {
            //If trips booked by the client are to be displayed
            Client c = (Client) session.getAttribute("client");
            tr = c.getBoughtTrips();
            try {
                st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                rs = st.executeQuery("SELECT * FROM TRIPS JOIN BOOKED_TRIPS ON "
                        + "TRIPS.ID=BOOKED_TRIPS.ID_TRIP AND BOOKED_TRIPS.ID_CLIENT=" + String.valueOf(c.getID()));
            } catch (SQLException se) {
                System.err.println(se.getMessage());
            }
        }
        if (user.equals("client") && action.equals("showCountry")) {
            //If trips available for a chosen country are to be displayed
            Client c = (Client) session.getAttribute("client");
            String country = request.getParameter("country");
            tr = o.filterTripsByCountry(country);
            try {
                st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                rs = st.executeQuery("SELECT * FROM TRIPS WHERE country='" + country + "'");
            } catch (SQLException se) {
                System.err.println(se.getMessage());
            }
        }
        if (user.equals("employee") && action.equals("showAssigned")) {
            //If trips assigned to a guide are to be displayed
            TourGuide tg = (TourGuide) session.getAttribute("employee");
            tr = o.searchTripsforGuide(tg);
            try {
                st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                rs = st.executeQuery("SELECT * FROM TRIPS, ASSIGNED_TRIPS "
                        + "WHERE TRIPS.ID=ASSIGNED_TRIPS.ID_TRIP AND ASSIGNED_TRIPS.ID_TOURGUIDE=" + String.valueOf(tg.getID()));
            } catch (SQLException se) {
                System.err.println(se.getMessage());
            }
        }

        if (action.equals("showTrips") || action.equals("showBooked")
                || action.equals("showCountry") || action.equals("showAssigned")) {
            //If any list of trips is to be displayed
            try {
                if (rs.isBeforeFirst()) {
                    out.println("<h3>Showing trips:</h3>");
                    out.println("   ID &emsp; COUNTRY &emsp; CITY &emsp;&emsp; BEG.DATE &emsp;&emsp; END DATE &emsp;&emsp; PRICE &emsp;&emsp; GUIDE <br><br>");
                    int i = 1;

                    while (rs.next()) {
                        out.println(i + "&emsp;" +rs.getInt("id")+"&emsp;"+ rs.getString("country") + "&emsp; - &emsp;");
                        out.println(rs.getString("city") + "&emsp; - &emsp;");
                        java.sql.Date depSql = rs.getDate("departure");
                        java.sql.Date arrSql = rs.getDate("arrival");
                        out.println(String.valueOf(depSql) + "&emsp; - &emsp;");
                        out.println(String.valueOf(arrSql) + "&emsp; - &emsp;");
                        out.println(rs.getInt("price") + "&emsp; - &emsp;");
                        TourGuide tg = o.findGuideByID(rs.getInt("tourguideid"));
                        out.println(tg.getSurname());
                        out.println("<br>");
                        i++;
                    }
                    //}

                    if (user.equals("manager") && action.equals("showTrips")) {
                        //Possibility of deleting one of the trips for manager
                        out.println("<br><br>Do you want to delete one of the trips?<br>If so, type its id and submit.<br>");
                        out.println("<form action=ModifyTripsServlet> <input type=\"text\" name=\"choice\"><br>");
                        out.println("<input type=\"submit\" value=\"Delete trip\" /> </form><br>"
                                + "<form action=LoggingRegisteringServlet><button type=\"submit\" name=\"person\" value=\"returnManager\">Return to my options</button></form>"
                        );
                    }
                    if (user.equals("client") && action.equals("showTrips")) {
                        //Possibility of booking one of the displayed trips for client
                        out.println("<br><br>Do you want to book one of the trips?<br>If so, type its id and submit.<br>");
                        out.println("<form action=ModifyTripsServlet><input type=\"text\" name=\"choice\"><br>");
                        out.println("<input type=\"submit\" value=\"Book trip\" /></form>");

                    }
                } else //If the trips list was empty
                {
                    out.println("No trips found.");
                }
                //'Return to options' buttons
                if (user.equals("client")) {
                    out.println("<br><form action=LoggingRegisteringServlet><button type=\"submit\" name=\"person\" value=\"returnClient\">Return to my options</button></form>");
                }
                if (user.equals("employee")) {
                    out.println("<br><form action=LoggingRegisteringServlet><button type=\"submit\" name=\"person\" value=\"returnEmployee\">Return to my options</button></form>");
                }
            } catch (SQLException se) {
                System.err.println(se.getMessage());
            }
        }

        if (user.equals("manager") && action.equals("showClients")) {
            //If the display of clients list
            try {
                st = con.createStatement();
                rs = st.executeQuery("SELECT * FROM clients");

                out.println("<h3>Registered clients:</h3>");
                out.println("   ID &emsp; NAME &emsp;&emsp; SURNAME <br>");
                int i = 1;
                while (rs.next()) {
                    out.println(i + "&emsp;" + rs.getInt("ID") + "&emsp; - &emsp;");
                    out.println(rs.getString("name") + "&emsp; - &emsp;");
                    out.println(rs.getString("surname"));
                    out.println("<br>");
                    i++;
                }
            } catch (SQLException se) {
                System.err.println(se.getMessage());
            }
            //Return buton for manager
            out.println("<br><form action=LoggingRegisteringServlet><button type=\"submit\" name=\"person\" value=\"returnManager\">Return to my options</button></form>");
        }
        if (user.equals("manager") && action.equals("showGuides")) {
            //If the display of employees list
            try {
                st = con.createStatement();
                rs = st.executeQuery("SELECT * FROM employees");
                out.println("<h3>Registered employees:</h3>");
                out.println("ID &emsp; NAME &emsp;&emsp; SURNAME <br>");
                int i = 1;
                while (rs.next()) {
                    out.println(i + "&emsp;" + rs.getInt("ID") + "&emsp; - &emsp;");
                    out.println(rs.getString("name") + "&emsp; - &emsp;");
                    out.println(rs.getString("surname"));
                    out.println("<br>");
                    i++;
                }
            } catch (SQLException se) {
                System.err.println(se.getMessage());
            }
            //Return button for manager
            out.println("<br><form action=LoggingRegisteringServlet><button type=\"submit\" name=\"person\" value=\"returnManager\">Return to my options</button></form>");
        }
        if (user.equals("manager") && action.equals("addTrip")) {
            //If adding trip was chosen by the manager
            out.println("Add new Trip's all necessary data.");
            //Necessary fields for input of new trips's data
            out.println("<form action=\"AddedTripServlet\">\n"
                    + "  <label for=\"country\">Country: </label>\n"
                    + "  <input type=\"text\" name=\"country\"><br>\n"
                    + "  <label for=\"city\">City: </label>\n"
                    + "  <input type=\"text\" name=\"city\"><br> "
                    + "  <label for=\"price\">Price: </label>\n"
                    + "  <input type=\"text\" name=\"price\"><br> "
                    + "  <label for=\"maxSeats\">Max num of seats: </label>\n"
                    + "  <input type=\"text\" name=\"maxSeats\"><br> "
                    + "  <label for=\"begin\">Departure Date: </label>\n"
                    + "  <input type=\"text\" name=\"begin\"><br> "
                    + "  <label for=\"end\">Arrival Date: </label>\n"
                    + "  <input type=\"text\" name=\"end\"><br> "
                    + "  <label for=\"guide\">Guide's name: </label>\n"
                    + "  <input type=\"text\" name=\"guide\"><br> "
                    + "  <div> <input type=\"radio\" id=\"allin\" name=\"catering\" value=\"ALL_INCLUSIVE\">"
                    + "  <label for=\"allin\">ALL-INCLUSIVE</label> </div>"
                    + "  <div> <input type=\"radio\" id=\"breakf\" name=\"catering\" value=\"BREAKFAST_DINNER\">"
                    + "  <label for=\"breakf\">BREAKFAST & DINNER</label> </div>"
                    + "  <div> <input type=\"radio\" id=\"self\" name=\"catering\" value=\"SELF_CATERING\">"
                    + "  <label for=\"self\">SELF CATERING</label> </div>"
                    + "  <input type=\"submit\" value=\"Add trip\" />\n"
                    + "  </form>");
            out.println("<br><form action=LoggingRegisteringServlet><button type=\"submit\" name=\"person\" value=\"returnManager\">Return to my options</button></form>");
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
