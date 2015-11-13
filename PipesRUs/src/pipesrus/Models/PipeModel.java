/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipesrus.Models;
import pipesrus.PriceEngine.*;
import java.math.*;

     
/**
 *
 * @author UP732011
 */
public class PipeModel extends Model {
    PipeGrade pipeGrade;
    PipeColour pipeColour;
    boolean insulated, reinforced, chemicalResistance;
    double length, diameter;
    double price;
    double volume;
    Pipe _pipe;
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
     * @param length The length of the pipe in meters
     * @param diameter the diameter of the pipe
     */
    public PipeModel(boolean insulated, boolean reinforced,
            boolean chemicalResistance,
                        PipeGrade grade, 
                        PipeColour colour,
                        double length,
                        double diameter)
    {
        this.insulated = insulated;
        this.reinforced = reinforced;
        this.chemicalResistance = chemicalResistance;
        this.pipeGrade = grade;
        this.pipeColour = colour;
        this.length = length;
        this.diameter = diameter;
//        
//        double radius = diameter / 2;
//        this.volume = Math.PI * (radius * radius) * length;
    }
    public PipeColour getPipeColour()
    {
        return this.pipeColour;
    }
    public void setValue(double val)
    {
        this.price = val;
    }
    public double getValue()
    {
        return this.price;
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
    public double getLength()
    {
        return this.length;
    }
    public double getDiameter()
    {
        return this.diameter;
    }
    
//    public double getVoloume()
//    {
//        double radius = this.diameter /2;
//        
//        return Math.PI * (radius * radius) * this.length;
//    }
}
