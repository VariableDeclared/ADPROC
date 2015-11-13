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
    /**
     * The constructor for this pipe, initialises with parameter values
     * @param length The length of pipe
     * @param diameter The diameter of the pipe
     * @param grade What pipe grade it is
     * @param chemResist Whether or not the pipe is chemically resisted
     */
    
    public PipeTypeOne(double length, double diameter, PipeGrade grade, boolean chemResist)
    {
        super(length, diameter, grade, chemResist);
        this._colour = PipeColour.NO_COLOUR;
        this._pipeType = PipeType.ONE;
    }
    /**
     * Gets the colour of pipe
     * @return returns the PipeColour type with the PipeColour for this pipe
     */
    @Override
    public PipeColour getPipeColour()
    {
        return this._colour;
    }

    /**
     * Gets the value in pounds for this pipe
     * @return a double stating the value as pounds
     */
    @Override
    public Double getPrice()
    {
        this._priceForPlastic = this.getVolume() * this._ePipeGrade.getPrice();
        double price = this._priceForPlastic;
        if(this.getChemicalResistance())
        {
           price = this._priceForPlastic * 1.12;
        }
        System.out.println("Hello: + " +price);
        return price;

    }
//    public void setPipeColour(PipeColour pipeColour)
//    {
//         this._ePipeColour = pipeColour;
//    }
}
