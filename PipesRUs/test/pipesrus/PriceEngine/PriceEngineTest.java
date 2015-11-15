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
import pipesrus.Models.PipeModel;

/**
 *
 * @author 732011 <up732011@myport.ac.uk>
 */
public class PriceEngineTest {
    
    public PriceEngineTest()
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
     * Test of getPipeForModel method, of class PriceEngine.
     */
    @Test
    public void testGetPipeForModel() throws Exception
    {
        System.out.println("getPipeForModel");
        PipeModel model = new PipeModel(false, false, true, PipeGrade.THREE, PipeColour.TWO_COLOURS
        , 1, 39);
        PriceEngine instance = new PriceEngine();
        Pipe expResult = new PipeTypeThree(1,39, PipeGrade.THREE, true);
        Pipe result = instance.getPipeForModel(model);
        assertSame(expResult.getClass(), expResult.getClass());

    }
    
}
