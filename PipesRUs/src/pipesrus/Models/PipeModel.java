/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipesrus.Models;
import pipesrus.PriceEngine.*;
/**
 *
 * @author Pete
 */
public class PipeModel {
    PipeGrade pipeGrade;
    boolean insulated, reinforced, chemicalResistance;
    public PipeModel(boolean insulated, boolean reinforced, boolean chemicalResistance,
                        PipeGrade grade)
    {
        this.insulated = insulated;
        this.reinforced = reinforced;
        this.chemicalResistance = chemicalResistance;
        this.pipeGrade = grade;
    }
    public PipeGrade getPipeGrade()
    {
        return this.pipeGrade;
    }
    public boolean getInsulated()
    {
        return this.insulated;
    }
    public boolean getReinforced()
    {
        return this.reinforced;
    }
    public boolean getChemicalResistance()
    {
        return this.chemicalResistance;
    }
    
}
