/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipesrus.Models;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pipesrus.PriceEngine.PipeGrade;

/**
 *
 * @author UP732011
 */
public class PipeModelTest {
    
    public PipeModelTest() {
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
     * Test of getPipeGrade method, of class PipeModel.
     */
    @Test
    public void testGetPipeGrade() {
        System.out.println("getPipeGrade");
        PipeModel instance = new PipeModel();
        PipeGrade expResult = null;
        PipeGrade result = instance.getPipeGrade();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInsulated method, of class PipeModel.
     */
    @Test
    public void testGetInsulated() {
        System.out.println("getInsulated");
        PipeModel instance = new PipeModel();
        Boolean expResult = null;
        Boolean result = instance.getInsulated();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReinforced method, of class PipeModel.
     */
    @Test
    public void testGetReinforced() {
        System.out.println("getReinforced");
        PipeModel instance = new PipeModel();
        Boolean expResult = null;
        Boolean result = instance.getReinforced();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChemicalResistance method, of class PipeModel.
     */
    @Test
    public void testGetChemicalResistance() {
        System.out.println("getChemicalResistance");
        PipeModel instance = new PipeModel();
        Boolean expResult = null;
        Boolean result = instance.getChemicalResistance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
