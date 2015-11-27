INSERT INTO Customer(Name, AccNo) VALUES("Peter", "0000");

INSERT INTO Pipe(Grade, Colour, Insulated, Reinforced, ChemicalResistance) VALUES(1, 1, "NO_COLOUR", 0, 0);

INSERT INTO PipeOrder(CustomerID) VALUES(1);

INSERT INTO ProductOrdered(OrderID, PipeID, OrderedDate) VALUES(1, 1, DATETIME());

