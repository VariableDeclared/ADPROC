
CREATE TABLE "ProductOrdered" (
	`ProductOrderedID`	INTEGER NOT NULL,
	`OrderID`	INTEGER NOT NULL,
	`PipeID`	INTEGER NOT NULL,
	`OrderedDate` DATE NOT NULL,
	FOREIGN KEY(OrderID) REFERENCES PipeOrder(OrderID),
	FOREIGN KEY(PipeID) REFERENCES Pipe(PipeID),
	PRIMARY KEY(ProductOrderedID)
);
INSERT INTO `ProductOrdered` VALUES (1,1,1,'2015-11-27 18:09:30');
INSERT INTO `ProductOrdered` VALUES (2,45,0,'2015-11-28T10:07+0000');
INSERT INTO `ProductOrdered` VALUES (3,46,0,'2015-11-28T10:08+0000');
INSERT INTO `ProductOrdered` VALUES (4,47,11,'2015-11-28T10:11+0000');
CREATE TABLE `PipeOrder` (
	`OrderID`	INTEGER NOT NULL,
	`CustomerID`	INTEGER NOT NULL,
	FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID),
	PRIMARY KEY(OrderID)
);

