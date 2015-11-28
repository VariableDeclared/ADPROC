SELECT * FROM PipeOrder p 
JOIN Customer c ON c.CustomerID = p.CustomerID
JOIN ProductOrdered po ON p.OrderID = po.OrderID 
JOIN Pipe pp ON pp.PipeID = po.PipeID 
