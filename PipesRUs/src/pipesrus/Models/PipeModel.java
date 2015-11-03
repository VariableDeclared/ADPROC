/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipesrus.Models;
import pipesrus.PriceEngine.*;
/**
 *
 * @author UP732011
 */
public class PipeModel extends Model {
    PipeGrade pipeGrade;
    PipeColour pipeColour;
    boolean insulated, reinforced, chemicalResistance;
    /**
     * Shouldn't be called by the system, only used so that the system can see methods
     */
    public PipeModel()
    {
        
    }
    /**
     * 
     * @param insulated The insulation - boolean
     * @param reinforced Reinforced - boolean
     * @param chemicalResistance Chemical Resistance - boolean
     * @param grade Chemical Resistance - enum
     * @param colour Chemical Resistance - enum
     */
    public PipeModel(boolean insulated, boolean reinforced, boolean chemicalResistance,
                        PipeGrade grade, PipeColour colour)
    {
        this.insulated = insulated;
        this.reinforced = reinforced;
        this.chemicalResistance = chemicalResistance;
        this.pipeGrade = grade;
        this.pipeColour = colour;
    }
    public PipeColour getPipeColour()
    {
        return this.pipeColour;
    }
    public PipeGrade getPipeGrade()
    {
        return this.pipeGrade;
    }
    public Boolean getInsulated()
    {
        return this.insulated;
    }
    public Boolean getReinforced()
    {
        return this.reinforced;
    }
    public Boolean getChemicalResistance()
    {
        return this.chemicalResistance;
    }
    
}
