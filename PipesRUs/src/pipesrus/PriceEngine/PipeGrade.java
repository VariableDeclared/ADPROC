/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipesrus.PriceEngine;

import java.util.Set;

/**
 * 
 * 
 * @author UP738106
 * 
 * @version 0.3 - price/inch^3 for each Enum
 */
public enum PipeGrade 
{
    ONE(0.3),
    TWO(0.32),
    THREE(0.35),    
    FOUR(0.40),
    FIVE(0.46);
    
    private double price;
    
    private PipeGrade(double newPrice)
    {
        this.price = newPrice;
    }
    
    public double getPrice()
    {
        return this.price;
    }

}
//public PipeGrade grade;
//{
//
//    GRADE_ONE,
//    GRADE_TWO,
//    GRADE_THREE,    
//    GRADE_FOUR,
//    GRADE_FIVE;
//
//}