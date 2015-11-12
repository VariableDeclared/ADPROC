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
abstract public class Pipe implements IChemicallyResistedPipe, IColouredPipe 
{
    private static final double METERSPERINCH  =  0.0254;
    
    //private float _price;

    protected PipeGrade _ePipeGrade;

    protected PipeColour _colour;

    protected int _pipeType;
    
    private boolean chemicalResistance;   

    private final float length, diameter;
    public Pipe(float length, float diameter, PipeGrade grade, boolean chemResist)
    {
        this._ePipeGrade = grade;
        this.chemicalResistance = chemResist;
        this.length = length;
        this.diameter = diameter;
    }
    
    public abstract double getPrice();
//    {
//    return this._price;
//    }
//    
//    public void setPrice(float newPrice)
//    {
//        this._price = newPrice;
//        
//    }
    
//    public void setGrade(PipeGrade newGrade)
//    {
//        this._ePipeGrade = newGrade;
//    }
    
    @Override
    public PipeColour getPipeColour()
    {
        return this._colour;
    }
    public PipeGrade getGrade()
    {
        return this._ePipeGrade;
    }
//    
    public int getType()
    {
        return this._pipeType;
    }
    
    
//    public void setType(int newType)
//    {
//        this._pipeType = newType;
//    }
//    
    
    private double meterToInch(float m)
    {
        return m/METERSPERINCH;
    }
    
    public double getVolume()
    {
        return (Math.PI*Math.pow(this.diameter/2, 2))*this.length;
    }
    
    @Override
    public boolean getChemicalResistance()
    {
        return this.chemicalResistance;
    }
    @Override
    public void setChemicalResistance(boolean chemRes)
    {
        this.chemicalResistance = chemRes;
    }
}
