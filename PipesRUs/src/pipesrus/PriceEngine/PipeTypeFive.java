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
    
    /**
     * The constructor for this pipe, initialises with parameter values
     * @param length The length of pipe
     * @param diameter The diameter of the pipe
     * @param grade What pipe grade it is
     * @param chemResist Whether or not the pipe is chemically resisted
     */
    public PipeTypeFive(double length, double diameter, PipeGrade grade, boolean chemResist)
    {
        super(length, diameter, grade, chemResist);
        this._pipeType = PipeType.FIVE;
    }
    /**
     * Returns whether the pipe is reinforced
     * @return a bool stating whether the pipe is reinforced or not
     */
    @Override
    public boolean getOuterReinforcedBool()
    {
        return this.outerReinforced;
    }
    /**
     * Gets the value in pounds for this pipe
     * @return a double stating the value as pounds
     */
    @Override
    public double getPrice()
    {
        double price = super.getPrice();
        
        //outer reinforcement
        price *= 1.15;
        
        return price;
        
        
        
    }
}
