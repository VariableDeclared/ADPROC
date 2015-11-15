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
public class PipeTypeOneTest {
    
    public PipeTypeOneTest()
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
    private double getVol(double diameter, double length)
    {
        return Math.PI*Math.pow(diameter*0.9/2, 2)*length/0.0254;
    }
    /**
     * Test of getPipeColour method, of class PipeTypeOne.
     */
    @Test
    public void testGetPipeColour()
    {
        System.out.println("getPipeColour");
        PipeTypeOne instance = new PipeTypeOne(1,39, PipeGrade.THREE, false);
        PipeColour expResult = PipeColour.NO_COLOUR;
        PipeColour result = instance.getPipeColour();
        assertEquals(expResult, result);

    }

    /**
     * Test of getPrice method, of class PipeTypeOne.
     */
    @Test
    public void testGetPrice()
    {
        System.out.println("getPrice");
        PipeTypeOne instance = new PipeTypeOne(1,39, PipeGrade.THREE, false);
        Double expResult = PipeGrade.THREE.getPrice() * getVol(39,1);
        Double result = instance.getPrice();
        assertEquals(expResult, result);

    }
    
}
