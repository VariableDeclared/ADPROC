
CREATE TABLE "ProductOrdered" (
	`ProductOrderedID`	INTEGER NOT NULL,
	`OrderID`	INTEGER NOT NULL,
	`PipeID`	INTEGER NOT NULL,
	`OrderedDate` DATE NOT NULL,
	FOREIGN KEY(OrderID) REFERENCES PipeOrder(OrderID),
	FOREIGN KEY(PipeID) REFERENCES Pipe(PipeID),
	PRIMARY KEY(ProductOrderedID)
);
INSERT INTO `ProductOrdered` (ProductOrderedID,OrderID,PipeID,OrderedDate) VALUES (1,1,1,'2015-11-27 18:09:30');
CREATE TABLE `PipeOrder` (
	`OrderID`	INTEGER NOT NULL,
	`CustomerID`	INTEGER NOT NULL,
	FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID),
	PRIMARY KEY(OrderID)
);
INSERT INTO `PipeOrder` (OrderID,CustomerID) VALUES (1,1);
INSERT INTO `PipeOrder` (OrderID,CustomerID) VALUES (2,1);
INSERT INTO `PipeOrder` (OrderID,CustomerID) VALUES (3,1);
INSERT INTO `PipeOrder` (OrderID,CustomerID) VALUES (4,1);
CREATE TABLE "Pipe" (
	`PipeID`	INTEGER NOT NULL,
	`Grade`	INTEGER NOT NULL,
	`Colour`	TEXT NOT NULL,
	`Insulated`	INTEGER NOT NULL,
	`Reinforced`	INTEGER NOT NULL,
	`ChemicalResistance`	INTEGER NOT NULL,
	`PipeVolume`	REAL,
	PRIMARY KEY(PipeID)
);
INSERT INTO `Pipe` (PipeID,Grade,Colour,Insulated,Reinforced,ChemicalResistance,PipeVolume) VALUES (1,1,'1','NO_COLOUR',0,0,NULL);
CREATE TABLE "Customer" (
	`CustomerID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`Name`	TEXT NOT NULL,
	`AccNo`	TEXT NOT NULL
);
INSERT INTO `Customer` (CustomerID,Name,AccNo) VALUES (1,'Peter','0000');

