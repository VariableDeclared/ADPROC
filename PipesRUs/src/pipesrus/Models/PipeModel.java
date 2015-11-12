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
        
        double radius = diameter / 2;
        this.volume = Math.PI * (radius * radius) * length;
    }
    public PipeColour getPipeColour()
    {
        return this.pipeColour;
    }
    
    public double getPrice()
    {
        double price = 0;
        double additionalCostPercentage = 1;    //1 = 100% ie no extra
        
        if(this.reinforced)
        {
            additionalCostPercentage += 0.15;   //extra 15%
        }
        
        if(this.insulated)
        {
            additionalCostPercentage += 0.14;  //extra 14%
        } 
        
        if(this.chemicalResistance)
        {
            additionalCostPercentage += 0.12;   //extra 12%
        }
        
        if(this.pipeColour == PipeColour.ONE_COLOUR)
        {
            additionalCostPercentage += 0.12;
        }
        
         if(this.pipeColour == PipeColour.TWO_COLOURS)
        {
            additionalCostPercentage += 0.17;
        }
        
         switch(this.pipeGrade)
         {
             case ONE:
                 price = 30 * this.getVoloume();
                 break;
                 
            case TWO:
                 price = 32 * this.getVoloume();
                 break;
            
            case THREE:
                 price = 35 * this.getVoloume();
                 break;
            
            case FOUR:
                 price = 40 * this.getVoloume();
                 break;
            
            case FIVE:
                 price = 46 * this.getVoloume();
                 break;
              
            default:
                break;
         }
         double total = price * additionalCostPercentage;
         
        System.out.println("Price:" + total);
        return total / 100; //put into £
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
    
    public double getVoloume()
    {
        double radius = this.diameter /2;
        
        return Math.PI * (radius * radius) * this.length;
    }
}
