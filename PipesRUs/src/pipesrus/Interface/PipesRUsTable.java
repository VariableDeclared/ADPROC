/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipesrus.Interface;
import javax.swing.JTable;
import javax.swing.table.*;
/**
 *
 * @author 732011
 */
public class PipesRUsTable extends JTable {
    public PipesRUsTable(AbstractTableModel model)
    {
        super(model);
    }
    public PipesRUsTable(Object [][] table, Object[] colNames)
    {
        super(table, colNames);
    }
    @Override
    public boolean isCellEditable(int row, int col)
    {
        return false;
    }
}
