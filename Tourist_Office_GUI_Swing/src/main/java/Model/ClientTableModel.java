/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**Class representing display of client list
 *
 * @author Anna Wołoszyn
 * @version 3.1
 */
public class ClientTableModel extends AbstractTableModel {
    	
        protected final String[] COLUMN_NAMES={"ID","Name","Surname","Num of booked trips"};
        private List<Client> clients;
       
        /**Constructor of the class
         */
        public ClientTableModel(List<Client> l){
            this.clients=new ArrayList<>(l);
        }
        /**Getting the num of row 
         */
        @Override
        public int getRowCount() {
        return clients.size();
        }
        /**Getting the num of columns 
         */
        @Override
        public int getColumnCount() {
        return 4;
        }
        /**Getting the name of column 
         */
        @Override
        public String getColumnName(int c) {
        return COLUMN_NAMES[c];
    }    
        /**Getting the value at given row and column
         */
        @Override
        public Object getValueAt(int row,int col) {
           Client c = clients.get(row);
        Object value = null;
        switch (col) {
            case 0:
                value = c.getID();
                break;
            case 1:
                value = c.getName();
                break;
            case 2:
                value = c.getSurname();
                break;
            case 3:
                value = c.getBoughtTrips().size();
                break;
        }
        return value;
        }
    }