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
 * @v0.2 - % cost for each Enum
 */
public enum PipeColour 
{
    NO_COLOUR(0),
    ONE_COLOUR(1.12),
    TWO_COLOURS(1.17);
    
    
    private double value;
    private  PipeColour(double price)
    {
     this.value = price; 
    }
    
    private double getValue()
    {
        return this.value;
    }
}
