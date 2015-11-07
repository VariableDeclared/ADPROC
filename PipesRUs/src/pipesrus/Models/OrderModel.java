/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipesrus.Models;
import pipesrus.PriceEngine.*;
/**
 *
 * @author UP732011
 */
public class OrderModel {
    private final Pipe pipe;
    private final double totalCost;
    private final double totalPipe;
    public OrderModel(Pipe pipe, double totalCost, double totalPipe)
    {
        this.pipe = pipe;
        this.totalCost = totalCost;
        this.totalPipe = totalPipe;
    }
    public Pipe getPipe()
    {
        return this.pipe;
    }
    public double getTotalPipe()
    {
        return this.totalPipe;
    }
    public double getTotalCost()
    {
        return this.totalCost;
    }
    
}