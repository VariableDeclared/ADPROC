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
    public PipeTypeThree(float length, float diameter, PipeGrade grade, boolean chemResist)
    {
        super(length, diameter,grade, chemResist);
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
