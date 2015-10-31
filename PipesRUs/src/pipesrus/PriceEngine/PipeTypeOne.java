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
    private enumPipeColour _ePipeColour;
    
    public PipeTypeOne(enumPipeColour colour, boolean chemResist)
    {
        this._ePipeColour = colour;
        this.setChemicalResistance(chemResist);
    }
    
    public enumPipeColour getPipeColour()
    {
        return this._ePipeColour;
    }
    
    public void setPipeColour(enumPipeColour pipeColour)
    {
         this._ePipeColour = pipeColour;
    }
}
