/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipesrus;

import java.awt.event.ActionEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pipesrus.Models.PipeModel;

/**
 *
 * @author pete
 */
public class PipesRUsGUITest {
    
    public PipesRUsGUITest() {
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
     * Test of actionPerformed method, of class PipesRUsGUI.
     */
    @Test
    public void testActionPerformed() {
        System.out.println("actionPerformed");
        ActionEvent evnt = null;
        PipesRUsGUI instance = new PipesRUsGUI();
        instance.actionPerformed(evnt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tryUpdateModel method, of class PipesRUsGUI.
     */
    @Test
    public void testTryUpdateModel() {
        System.out.println("tryUpdateModel");
        PipeModel pipe = null;
        PipesRUsGUI instance = new PipesRUsGUI();
        instance.tryUpdateModel(pipe);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
