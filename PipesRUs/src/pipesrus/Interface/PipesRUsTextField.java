/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipesrus.Interface;
import javax.swing.JTextField;
/**
 *
 * @author UP732011
 */
public class PipesRUsTextField extends JTextField{
    
    private String ID;
    public PipesRUsTextField(String value)
    {
        super(value);
        this.ID = value;
    }
    
    public String getID()
    {
        return this.ID;
    }
    public void setID(String value)
    {
        this.ID = value;
    }
}
