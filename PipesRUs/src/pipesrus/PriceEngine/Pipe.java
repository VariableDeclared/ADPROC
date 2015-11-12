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

    protected PipeGrade _ePipeGrade;

    protected PipeColour _colour;

    protected double _priceForPlastic;
    protected PipeType _pipeType;
    
    private boolean chemicalResistance;   

    private final double length, diameter;

    

    /**
     * The constructor for this pipe, initialises with parameter values
     * @param length The length of pipe
     * @param diameter The diameter of the pipe
     * @param grade What pipe grade it is
     * @param chemResist Whether or not the pipe is chemically resisted
     */

    public Pipe(double length, double diameter, PipeGrade grade, boolean chemResist)
    {
        this._ePipeGrade = grade;
        this.chemicalResistance = chemResist;
        this.length = length;
        this.diameter = diameter;
    }
    /**
     * Abstract method to be implemented by all subclasses
     * @return returns the price in pounds as a double
     */
    public abstract double getPrice();

    /**
     * Gets and returns the PipeColour for this pipe
     * @return a PipeColour enum with the value set to the colour number for this pipe
     */
    @Override
    public PipeColour getPipeColour()
    {
        return this._colour;
    }
    /**
     * Gets the PipeGrade enum for this pipe
     * @return returns a PipeGrade enum with the value set to the PipeGrade for this pipe
     */
    public PipeGrade getGrade()
    {
        return this._ePipeGrade;
    }

    /**
     * Gets the PipeType for this pipe
     * @return a PipeType for this pipe
     */
    public PipeType getType()
    {
        return this._pipeType;
    }
    
    
//    public void setType(int newType)
//    {
//        this._pipeType = newType;
//    }
//    
    
    private double metresToInch(double m)
    {
        return m/METERSPERINCH;
    }
    private double inchToMetres(double i)
    {
        return i*METERSPERINCH;
    }
    
    public double getVolume()
    {
        return Math.PI*Math.pow(inchToMetres(this.diameter)/2, 2)*this.length;
    }
    /**
     * Gets and returns the chemical resistance as  a boolean
     * @return a boolean whether this pipe has chemical resistance
     */
    @Override
    public boolean getChemicalResistance()
    {
        return this.chemicalResistance;
    }
    /**
     * Sets whether the pipe is chemically resisted or not
     * @param chemRes a  boolean stating whether the pipe is insulated or not
     */
//    @Override
//    public void setChemicalResistance(boolean chemRes)
//    {
//        this.chemicalResistance = chemRes;
//    }
}
