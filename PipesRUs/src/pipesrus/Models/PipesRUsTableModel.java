/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipesrus.Models;
import javax.swing.table.DefaultTableModel;
import java.util.*;
/**
 *
 * @author 732011
 */
///Experimental. Bugged with index problem
public class PipesRUsTableModel extends DefaultTableModel  {
    public PipesRUsTableModel(Object[][] data, Object[] columnNames)
    {
        super(data, columnNames);
    }


    

    @Override
    public boolean isCellEditable(int row, int col)
    {
        return false;
    }

}
