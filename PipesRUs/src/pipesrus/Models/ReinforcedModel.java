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
public class ReinforcedModel {

    private boolean reinforced;
    public ReinforcedModel()
    {
        reinforced = false;
    }
    public ReinforcedModel(boolean value)
    {
        reinforced = value;
    }
    public boolean getReinforced()
    {
        return this.reinforced;
    }

}
