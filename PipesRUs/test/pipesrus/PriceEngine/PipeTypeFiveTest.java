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
public class PipeTypeFiveTest {
    
    public PipeTypeFiveTest()
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
     * Test of getOuterReinforcedBool method, of class PipeTypeFive.
     */
    @Test
    public void testGetOuterReinforcedBool()
    {
        System.out.println("getOuterReinforcedBool");
        PipeTypeFive instance = new PipeTypeFive(1,39, PipeGrade.FOUR, false);
        boolean expResult = true;
        boolean result = instance.getOuterReinforcedBool();
        assertEquals(expResult, result);

    }
    private double getVol(double diameter, double length)
    {
        return Math.PI*Math.pow(diameter*0.9/2, 2)*(length/0.0254);
    }
    /**
     * Test of getPrice method, of class PipeTypeFive.
     */
    @Test
    public void testGetPrice()
    {
        System.out.println("getPrice");
        PipeTypeFive instance =  new PipeTypeFive(1,39, PipeGrade.FOUR, false);
        Double expResult = 0.40 * getVol(39,1);
        double temp = expResult;
        //Insulation
        expResult += temp * 1.14;
        //reinforcement
        expResult += temp * 1.15;
        //Colour
        expResult += temp * 1.17;
        Double result = instance.getPrice();
        assertEquals(expResult, result);

    }
    
}
