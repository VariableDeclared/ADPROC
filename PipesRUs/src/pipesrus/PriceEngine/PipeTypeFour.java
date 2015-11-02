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
    private boolean insulated;
    
    public PipeTypeFour(PipeColour colour, boolean chemResist)
    {
        super(colour, chemResist);  
       
    }
    
    public boolean getInsulatedBool()
    {
        return this.insulated;
    }
    
    public void setInsulatedBool(boolean reinforced)
    {
        this.insulated = reinforced;
    }
    
    
}
