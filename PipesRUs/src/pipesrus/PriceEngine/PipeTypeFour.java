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

    /**
     * The constructor for this pipe, initialises with parameter values
     * @param length The length of pipe
     * @param diameter The diameter of the pipe
     * @param grade What pipe grade it is
     * @param chemResist Whether or not the pipe is chemically resisted
     */

    public PipeTypeFour(double length, double diameter, PipeGrade grade, boolean chemResist)
    {
        super(length, diameter,grade, chemResist);
        this._pipeType = PipeType.FOUR;
    }
    /**
     * Get whether this type of pipe has insulation
     * @return a bool stating true for insulation and false otherwise
     */
    @Override
    public boolean getInsulatedBool()
    {
        return this.insulated;
    }
    
    /**
     * Gets and returns a the price of the pipe for the type, length and diameter of pipe given
     * @return returns a double with the value of the pipe
     */
    @Override
    public double getPrice()
    {
        double price = super.getPrice();
        
        
        //insulation
        price *= 1.14;
        return price;
    }
    
    
}
