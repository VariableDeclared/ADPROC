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
 * @author UP732011
 */
public class PipeTest {
    
    public PipeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getPrice method, of class Pipe.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        Pipe instance = new PipeImpl();
        float expResult = 0.0F;
        float result = instance.getPrice();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPrice method, of class Pipe.
     */
    @Test
    public void testSetPrice() {
        System.out.println("setPrice");
        float newPrice = 0.0F;
        Pipe instance = new PipeImpl();
        instance.setPrice(newPrice);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGrade method, of class Pipe.
     */
    @Test
    public void testSetGrade() {
        System.out.println("setGrade");
        PipeGrade newGrade = null;
        Pipe instance = new PipeImpl();
        instance.setGrade(newGrade);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGrade method, of class Pipe.
     */
    @Test
    public void testGetGrade() {
        System.out.println("getGrade");
        Pipe instance = new PipeImpl();
        PipeGrade expResult = null;
        PipeGrade result = instance.getGrade();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getType method, of class Pipe.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Pipe instance = new PipeImpl();
        int expResult = 0;
        int result = instance.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setType method, of class Pipe.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        int newType = 0;
        Pipe instance = new PipeImpl();
        instance.setType(newType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class PipeImpl extends Pipe {
        public void setChemicalResistance(boolean bool)
        {
            
        }
    }
    
}
