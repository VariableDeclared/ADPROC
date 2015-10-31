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
 * @author pete
 */
public class IChemicallyResistedPipeTest {
    
    public IChemicallyResistedPipeTest() {
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
     * Test of getChemicalResistance method, of class IChemicallyResistedPipe.
     */
    @Test
    public void testGetChemicalResistance() {
        System.out.println("getChemicalResistance");
        IChemicallyResistedPipe instance = new IChemicallyResistedPipeImpl();
        boolean expResult = false;
        boolean result = instance.getChemicalResistance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setChemicalResistance method, of class IChemicallyResistedPipe.
     */
    @Test
    public void testSetChemicalResistance() {
        System.out.println("setChemicalResistance");
        boolean resistance = false;
        IChemicallyResistedPipe instance = new IChemicallyResistedPipeImpl();
        boolean expResult = false;
        boolean result = instance.setChemicalResistance(resistance);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class IChemicallyResistedPipeImpl implements IChemicallyResistedPipe {

        public boolean getChemicalResistance() {
            return false;
        }

        public boolean setChemicalResistance(boolean resistance) {
            return false;
        }
    }
    
}
