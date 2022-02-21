/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.lab.servlets;

import Model.CateringInfo;
import Model.Office;
import Model.TourGuide;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet handling adding trip to the office with data from request
 *
 * @author Anna Wołoszyn
 * @version 5.0
 */
@WebServlet(name = "AddedTripServlet", urlPatterns = {"/AddedTripServlet"})
public class AddedTripServlet extends HttpServlet {

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

        if (o != null) {
            //Getting all necessary data from request
            String country = request.getParameter("country");
            String city = request.getParameter("city");
            try {
                int price = Integer.parseInt(request.getParameter("price"));
                int maxSeats = Integer.parseInt(request.getParameter("maxSeats"));
                String begin = request.getParameter("begin");
                String end = request.getParameter("end");
                String guide = request.getParameter("guide");
                String radio = request.getParameter("catering");

                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

                //try {
                java.util.Date utilDate = format.parse(begin);
                java.sql.Date begDate = new java.sql.Date(utilDate.getTime());
                utilDate = format.parse(end);
                java.sql.Date endDate = new java.sql.Date(utilDate.getTime());

                if (country.equals("") || city.equals("") || begin.equals("") || end.equals("")
                        || guide.equals("")) {
                    //If any field is empty
                    out.println("All fields must be filled!");
                    out.println("<br><form action=ShowDataServlet><button type=\"submit\" name=\"option\" value=\"addTrip\">Return</button></form>");
                } else {
                    //Checking if guide with given surname exists in the system
                    TourGuide tg = o.findGuideBySurname(guide);
                    if (tg != null) {
                        //Adding trip to the office's trip list
                        //After checking all wrong input possibilities
                        o.addTrip(country, city, price, maxSeats, 0, begin, end, tg, CateringInfo.valueOf(radio));
                        try {
                            Connection con = (Connection) session.getAttribute("Connection");
                            //Statement st=con.createStatement();
                            int id = o.getTrips().size() - 1;
                            PreparedStatement ps = con.prepareStatement("INSERT INTO Trips VALUES(?,?,?,?,?,?,?,?,?,?)");
                            ps.setInt(1, id);
                            ps.setInt(2, maxSeats);
                            ps.setInt(3, 0);
                            ps.setString(4, country);
                            ps.setString(5, city);
                            ps.setDate(6, begDate);
                            ps.setDate(7, endDate);
                            ps.setDouble(8, price);
                            ps.setInt(9, tg.getID());
                            ps.setString(10, radio);
                            ps.executeUpdate();
                            ps.close();
                        } catch (SQLException se) {
                            System.err.println(se.getMessage());
                        }
                        out.println("Successfully added trip.");
                        out.println("<br><form action=LoggingRegisteringServlet><button type=\"submit\" name=\"person\" value=\"returnManager\">Return to my options</button></form>");
                        //} catch (ParseException e) {
                        //out.println("Wrong format of date. Type yyyy/mm/dd");
                        //}
                    } else {
                        out.println("No such guide in the system!");
                        out.println("<br><form action=ShowDataServlet><button type=\"submit\" name=\"option\" value=\"addTrip\">Return</button></form>");
                    }
                }
            } catch (NumberFormatException | NullPointerException | ParseException ex) {
                //If price or maxseats were empty or not a number
                //Or no radiobutton was chosen
                out.println("Missing fields, wrong format of input data or catering option not chosen."
                        + "<br>Check if date is YYYY/MM/DD (separated by slash)");
                out.println("<br><form action=ShowDataServlet><button type=\"submit\" name=\"option\" value=\"addTrip\">Return</button></form>");
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
