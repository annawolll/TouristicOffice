/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.lab.servlets;

import Model.Client;
import Model.Office;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet handling changes in lists:deleting trip and booking trip
 *
 * @author Anna Wołoszyn
 * @version 5.0
 */
@WebServlet(name = "ModifyTripsServlet", urlPatterns = {"/ModifyTripsServlet"})
public class ModifyTripsServlet extends HttpServlet {

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
        String user = (String) session.getAttribute("user");//Getting the user saved currently in session
        if (user == "manager") {
            try {
                int num = Integer.parseInt(request.getParameter("choice"));
                if (num < o.getTrips().size()) {
                    o.deleteTrip(num);//Deleting the chosen trip
                    try {
                        Connection con = (Connection) session.getAttribute("Connection");
                        Statement st = con.createStatement();
                        st.executeUpdate("DELETE FROM Trips WHERE ID=" + String.valueOf(num));
                    } catch (SQLException se) {
                        System.err.println(se.getMessage());
                    }
                    out.println("Successfully deleted trip.");
                    out.println("<br><form action=LoggingRegisteringServlet><button type=\"submit\" name=\"person\" value=\"returnManager\">Return to my options</button></form>");
                } else {
                    out.println("Wrong index of the trip!");
                    out.println("<br><form action=ShowDataServlet><button type=\"submit\" name=\"option\" value=\"showTrips\">Return</button></form>");
                }
            } catch (NumberFormatException ex) {//If choice of number of trip wasn't a number
                out.println("Choice must be a number.");
                out.println("<br><form action=ShowDataServlet><button type=\"submit\" name=\"option\" value=\"showTrips\">Return</button></form>");
            }
        }
        if (user == "client") {
            Client logged = (Client) session.getAttribute("client");
            if (logged != null) {
                try {
                    int num = Integer.parseInt(request.getParameter("choice"));
                    if (num < o.getTrips().size()) {
                        logged.bookTrip(o, num);//Booking the trip by the logged client
                        try {
                            Connection con = (Connection) session.getAttribute("Connection");
                            Statement st = con.createStatement();
                            st.executeUpdate("INSERT INTO booked_trips VALUES (" + String.valueOf(logged.getID()) + "," + String.valueOf(num) + ")");
                            st.executeUpdate("UPDATE Trips SET boughtseats=boughtseats+1 WHERE ID=" + String.valueOf(num));
                        } catch (SQLException se) {
                            System.err.println(se.getMessage());
                        }
                        out.println("Successfully booked trip.");
                        out.println("<br><form action=LoggingRegisteringServlet><button type=\"submit\" name=\"person\" value=\"returnClient\">Return to my options</button></form>");
                    } else {
                        out.println("Wrong index of the trip!");
                        out.println("<br><form action=ShowDataServlet><button type=\"submit\" name=\"option\" value=\"showTrips\">Return</button></form>");
                    }
                } catch (NumberFormatException ex) {//If choice of number of trip wasn't a number
                    out.println("Choice must be a number.");
                    out.println("<br><form action=ShowDataServlet><button type=\"submit\" name=\"option\" value=\"showTrips\">Return</button></form>");
                }
            }
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
