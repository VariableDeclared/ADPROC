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
    private boolean outerReinforced = true;
        
    public PipeTypeFive(PipeColour colour, boolean chemResist)
    {
        super(PipeColour.TWO_COLOURS, chemResist);
    }
    
    public void setOuterReinforcedBool(boolean reinforcedBool)
    {
        this.outerReinforced = reinforcedBool;
    }
    
    public boolean getOuterReinforcedBool()
    {
        return this.outerReinforced;
    }
    
}
