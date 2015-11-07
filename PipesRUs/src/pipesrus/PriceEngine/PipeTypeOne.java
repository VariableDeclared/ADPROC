/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipesrus.PriceEngine;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author UP738106
 */
public class PipeTypeOne extends Pipe implements IColouredPipe 
{
   
    public PipeTypeOne(float length, float diameter, PipeGrade grade, boolean chemResist)
    {
        super(length, diameter, grade, chemResist);
    }
    
    public PipeColour getPipeColour()
    {
        return PipeColour.NO_COLOUR;
    }

    public double getPrice()
    {
        double price = this.getVolume() * this._ePipeGrade.getPrice();
        System.out.println(price);
        if(this.getChemicalResistance())
        {
            price *= 1.12;
        }
        return price;

    }
//    public void setPipeColour(PipeColour pipeColour)
//    {
//         this._ePipeColour = pipeColour;
//    }
}
