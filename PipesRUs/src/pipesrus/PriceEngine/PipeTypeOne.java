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
public class PipeTypeOne extends Pipe implements IColouredPipe 
{
    private PipeColour _ePipeColour;
    
    public PipeTypeOne(PipeColour colour, boolean chemResist)
    {
        this._ePipeColour = colour;
        this.setChemicalResistance(chemResist);
    }
    
    public PipeColour getPipeColour()
    {
        return this._ePipeColour;
    }
    
    public void setPipeColour(PipeColour pipeColour)
    {
         this._ePipeColour = pipeColour;
    }
}
