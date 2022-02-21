/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**Class representing display of trip list in JTable
 *
 * @author Anna Wołoszyn
 * @version 3.1
 */
public class TripTableModel extends AbstractTableModel {
    	
        protected final String[] COLUMN_NAMES={"Country","City","Departure Date","Arrival Date","Price","Guide's name","Catering"};
        private List<Trip> trips;
       
        /**Constructor of the class 
         */
        public TripTableModel(List<Trip> l){
            this.trips=new ArrayList<>(l);
        }
        /**Getting the row number of rows
         */
        @Override
        public int getRowCount() {
        return trips.size();
        }
        /**Getting the row number of columns
         */
        @Override
        public int getColumnCount() {
        return 7;
        }
        /**Getting the name of given column
         */
        @Override
        public String getColumnName(int c) {
        return COLUMN_NAMES[c];
        }
        /**Getting the value at given row and column
         */
        @Override
        public Object getValueAt(int row,int col) {
           Trip t = trips.get(row);
        Object value = null;
        switch (col) {
            case 0:
                value = t.getCountry();
                break;
            case 1:
                value = t.getCity();
                break;
            case 2:
                value = t.getDepartureDate();
                break;
            case 3:
                value = t.getArrivalDate();
                break;
            case 4:
                value=t.getPrice();
                break;
            case 5:
                value=t.getGuide().getSurname();
                break;
            case 6:
                value=t.getCatering().name();
                break;
        }
        return value;
        }
    }
