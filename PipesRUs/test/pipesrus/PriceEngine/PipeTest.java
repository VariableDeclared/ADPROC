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
public class PipeTest {
    
    public PipeTest()
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
     * Test of getPrice method, of class Pipe.
     */
    @Test
    public void testGetPrice()
    {
        System.out.println("getPrice");
        Pipe instance = new PipeTypeOne(1,39, PipeGrade.THREE, false);
        Double expResult = getVol(39,1) * 0.35;
        Double result = instance.getPrice();
        assertEquals(expResult, result);

    }

    /**
     * Test of getPipeColour method, of class Pipe.
     */
    @Test
    public void testGetPipeColour()
    {
        System.out.println("getPipeColour");
        Pipe instance = new PipeTypeOne(1,39, PipeGrade.THREE, false);
        PipeColour expResult = PipeColour.NO_COLOUR;
        PipeColour result = instance.getPipeColour();
        assertEquals(expResult, result);

    }

    /**
     * Test of getGrade method, of class Pipe.
     */
    @Test
    public void testGetGrade()
    {
        System.out.println("getGrade");
        Pipe instance = new PipeImpl();
        PipeGrade expResult = PipeGrade.ONE;
        PipeGrade result = instance.getGrade();
        assertEquals(expResult, result);

    }

    /**
     * Test of getType method, of class Pipe.
     */
    @Test
    public void testGetType()
    {
        System.out.println("getType");
        Pipe instance = new PipeTypeOne(1,39, PipeGrade.THREE, false);
        PipeType expResult = PipeType.ONE;
        PipeType result = instance.getType();
        assertEquals(expResult, result);

    }
    private double getVol(double diameter, double length)
    {
        return Math.PI*Math.pow(diameter*0.9/2, 2)*length/0.0254;
    }
    /**
     * Test of getVolume method, of class Pipe.
     */
    @Test
    public void testGetVolume()
    {
        System.out.println("getVolume test");
        Pipe instance = new PipeTypeOne(1,39, PipeGrade.THREE, false);
        double expResult = getVol(39,1);
        double result = instance.getVolume();
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of getChemicalResistance method, of class Pipe.
     */
    @Test
    public void testGetChemicalResistance()
    {
        System.out.println("getChemicalResistance");
        Pipe instance = new PipeImpl();
        boolean expResult = false;
        boolean result = instance.getChemicalResistance();
        assertEquals(expResult, result);

    }

    public class PipeImpl extends Pipe {

        public PipeImpl()
        {
            super(0.0, 0.0, PipeGrade.ONE, false);
        }

        public Double getPrice()
        {
            return null;
        }
    }
    
}
