/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipesrus.Models;

/**
 *
 * @author UP732011 <UP732011@myport.ac.uk>
 */
public class InsulatedModel {
    private boolean insulated;
    public InsulatedModel()
    {
        insulated = false;
    }
    public InsulatedModel(boolean value)
    {
        insulated = value;
    }
    public boolean getInsualted()
    {
        return this.insulated;
    }
}
