/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipesrus.PriceEngine;
import pipesrus.Models.*;
/**
 *
 * @author UP732011 <UP732011@myport.ac.uk>
 */
public interface QuoteReciever {
    public void acceptOrderModel(OrderModel model);
    public void showError(String errorTitle, Exception ex);
}
