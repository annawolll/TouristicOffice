/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**Class representing display of guide list in JTable
 *
 * @author Anna Wołoszyn
 * @version 3.1
 */
public class GuideTableModel extends AbstractTableModel {
    	
        protected final String[] COLUMN_NAMES={"ID","Name","Surname"};
        private List<TourGuide> guides;
       /**Constructor of the class
        */
        public GuideTableModel(List<TourGuide> l){
            this.guides=new ArrayList<>(l);
        }
        /**Getting the num of rows
         */
        @Override
        public int getRowCount() {
        return guides.size();
        }
        /**Getting the num of columns
         */
        @Override
        public int getColumnCount() {
        return 3;
        }
        /**Getting the column name
         */
        @Override
        public String getColumnName(int c) {
        return COLUMN_NAMES[c];
    }    
        /**Getting the value at given row and column
         */
        @Override
        public Object getValueAt(int row,int col) {
           TourGuide t = guides.get(row);
        Object value = null;
        switch (col) {
            case 0:
                value = t.getID();
                break;
            case 1:
                value = t.getName();
                break;
            case 2:
                value = t.getSurname();
                break;
        }
        return value;
        }
    }