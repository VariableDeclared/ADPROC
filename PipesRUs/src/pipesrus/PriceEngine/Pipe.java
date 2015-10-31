/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipesrus.PriceEngine;

import java.util.HashSet;

/**
 *
 * @author UP738106
 */
abstract public class Pipe implements IChemicallyResistedPipe
{
    private float _price;
    private enumPipeGrade _ePipeGrade;
    private enumPipeColour _ePipeColour;
    private int _pipeType;
    
    private boolean chemicalResistance;   

    public Pipe()
    {  
    }
    
    public float getPrice()
    {
    return this._price;
    }
    
    public void setPrice(float newPrice)
    {
        this._price = newPrice;
        
    }
    
    public void setGrade(enumPipeGrade newGrade)
    {
        this._ePipeGrade = newGrade;
    }
    
    public enumPipeGrade getGrade()
    {
        return this._ePipeGrade;
    }
    
    public int getType()
    {
        return this._pipeType;
    }
    
    
    public void setType(int newType)
    {
        this._pipeType = newType;
    }
    
    public boolean getChemicalResistance()
    {
        return this.chemicalResistance;
    }
    
    public void setChemicalResistance(boolean chemRes)
    {
        this.chemicalResistance = chemRes;
    }
}
