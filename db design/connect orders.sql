SELECT * FROM PipeOrder p 
JOIN ProductOrdered po ON p.OrderID = po.OrderID 
JOIN Pipe pp ON pp.PipeID = po.PipeID 
JOIN Customer c ON c.CustomerID = p.OrderID