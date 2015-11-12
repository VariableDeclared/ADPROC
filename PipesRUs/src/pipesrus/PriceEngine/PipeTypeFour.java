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
public class PipeTypeFour extends PipeTypeThree implements IInsulatedPipe
{
    private final boolean insulated = true;
    
    public PipeTypeFour(double length, double diameter, PipeGrade grade, boolean chemResist)
    {
        super(length, diameter,grade, chemResist);
        this._pipeType = 4;
    }
    @Override
    public boolean getInsulatedBool()
    {
        return this.insulated;
    }
    
    @Override
    public double getPrice()
    {
        double price = super.getPrice();
        
        
        //insulation
        price *= 1.14;
        return price;
    }
    
    
}
