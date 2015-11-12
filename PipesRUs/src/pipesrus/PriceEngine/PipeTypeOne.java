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
public class PipeTypeOne extends Pipe
{
    
    public PipeTypeOne(double length, double diameter, PipeGrade grade, boolean chemResist)
    {
        super(length, diameter, grade, chemResist);
        this._colour = PipeColour.NO_COLOUR;
        this._pipeType = 1;
    }
    @Override
    public PipeColour getPipeColour()
    {
        return this._colour;
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
