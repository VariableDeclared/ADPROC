/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipesrus.Models;
import javax.swing.table.AbstractTableModel;
import java.util.*;
/**
 *
 * @author 732011
 */
///Experimental. Bugged with index problem
public class PipesRUsTableModel extends AbstractTableModel  {
    private ArrayList<String[]> table = new ArrayList<>(25);
    @Override
    public Object getValueAt(int row, int column)
    {
        return this.table.get(row)[column];
    }
    public int getColumnCount()
    {
        return this.table.get(0).length;
    }
    public int getRowCount()
    {
        return this.table.size();
    }
    @Override
    public boolean isCellEditable(int row, int col)
    {
        return false;
    }
    @Override
    public void setValueAt(Object val, int rowIndex, int columnIndex)
    {
    
        if(this.table.size() <= rowIndex)
        {
            this.table.add(new String[3]);
        }
        
        String [] tabRow = (String[])this.table.get(rowIndex);
        tabRow[columnIndex] = (String) val;
        this.table.set(rowIndex, tabRow);
    }
}
