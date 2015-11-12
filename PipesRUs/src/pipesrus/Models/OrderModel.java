/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipesrus.Models;
import pipesrus.PriceEngine.*;
import java.util.LinkedList;
/**
 *
 * @author UP732011
 */
public class OrderModel 
{
    private final LinkedList<Pipe> pipeList;
    
    private final double totalCost;
    
    public OrderModel(LinkedList<Pipe> pipes)
    {
        this.pipeList = pipes;
       
        double price = 1;
        for(int i = 0; i < this.pipeList.size(); i++)
        {
            price += pipes.get(i).getPrice();
        }
        
        this.totalCost = price; 
   }
    public LinkedList<Pipe> getPipes()
    {
        return this.pipeList;
    }
    public double getTotalPipe()
    {
        return this.pipeList.size();
    }
    public double getTotalCost()
    {
        return this.totalCost;
    }
    
}
