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
public class PipeGradeTest {
    
    public PipeGradeTest() {
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
     * Test of valueOf method, of class PipeGrade.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "ONE";
        PipeGrade expResult = PipeGrade.ONE;
        PipeGrade result = PipeGrade.valueOf(name);
        assertEquals(expResult, result);

    }

    /**
     * Test of getPrice method, of class PipeGrade.
     */
    @Test
    public void testGetPrice()
    {
        System.out.println("getPrice");
        PipeGrade instance = PipeGrade.ONE;
        double expResult = 0.3;
        double result = instance.getPrice();
        assertEquals(expResult, result, 0.0);

    }
    
}
