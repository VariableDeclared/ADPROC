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
    private boolean insulated = true;
    
    public PipeTypeFour(float length, float diameter, PipeGrade grade, boolean chemResist)
    {
        super(length, diameter,grade, chemResist);
    }
    
    public boolean getInsulatedBool()
    {
        return this.insulated;
    }
    
    public void setInsulatedBool(boolean reinforced)
    {
        this.insulated = reinforced;
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
