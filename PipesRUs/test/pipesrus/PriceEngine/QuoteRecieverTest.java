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
import pipesrus.Models.OrderModel;

/**
 *
 * @author 732011 <up732011@myport.ac.uk>
 */
public class QuoteRecieverTest {
    
    public QuoteRecieverTest()
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



    public class QuoteRecieverImpl implements QuoteReciever {

        public void acceptOrderModel(OrderModel model)
        {
        }

        public void showError(String errorTitle, Exception ex)
        {
        }
    }
    
}
