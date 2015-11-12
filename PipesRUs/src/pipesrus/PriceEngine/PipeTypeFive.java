/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipesrus.PriceEngine;

/**
 *
 * @author UP738106
 * 
 */
public class PipeTypeFive extends PipeTypeFour implements IOuterReinforcedPipe
{
    private final boolean outerReinforced = true;
        
    public PipeTypeFive(double length, double diameter, PipeGrade grade, boolean chemResist)
    {
        super(length, diameter, grade, chemResist);
        this._pipeType = 5;
    }
    
    @Override
    public boolean getOuterReinforcedBool()
    {
        return this.outerReinforced;
    }
    
    @Override
    public double getPrice()
    {
        double price = super.getPrice();
        
        //outer reinforcement
        price *= 1.15;
        
        return price;
        
        
        
    }
}
