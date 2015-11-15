/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipesrus.PriceEngine;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 732011 <up732011@myport.ac.uk>
 */
public class PipeTypeFourTest {
    
    public PipeTypeFourTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of getInsulatedBool method, of class PipeTypeFour.
     */
    @Test
    public void testGetInsulatedBool()
    {
        System.out.println("getInsulatedBool");
        PipeTypeFour instance = new PipeTypeFour(1,39, PipeGrade.THREE, false);;
        boolean expResult = true;
        boolean result = instance.getInsulatedBool();
        assertEquals(expResult, result);
    }
    private double getVol(double diameter, double length)
    {
        return Math.PI*Math.pow(diameter*0.9/2, 2)*(length/0.0254);
    }
    /**
     * Test of getPrice method, of class PipeTypeFour.
     */
    @Test
    public void testGetPrice()
    {
        System.out.println("getPrice");
        PipeTypeFour instance =  new PipeTypeFour(1,39, PipeGrade.THREE, false);
        
        
        Double expResult = 0.35 * getVol(39,1);
        double temp = expResult;
        //Colour
        expResult += temp * 1.17;
        //Insulation
        expResult += temp * 1.14;
        System.out.println("Expecting: " + expResult);
        Double result = instance.getPrice();
        assertEquals(expResult, result);

    }
    
}
