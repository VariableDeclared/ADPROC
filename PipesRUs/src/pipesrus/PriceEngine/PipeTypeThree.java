/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipesrus.PriceEngine;

/**
 *
 * @author UP738106
 */
public class PipeTypeThree extends PipeTypeTwo
{
    public PipeTypeThree(double length, double diameter, PipeGrade grade, boolean chemResist)
    {
        super(length, diameter,grade, chemResist);
        this._colour = PipeColour.TWO_COLOURS;
        this._pipeType = 3;
    }

    @Override
    public double getPrice()
    {
        double price = super.getPrice();
        //colour
        
        price *= 1.17;
        return price;
        
    }
}
