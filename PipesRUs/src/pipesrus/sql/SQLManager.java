/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipesrus.sql;

import java.sql.*;
import pipesrus.Models.PipeModel;
import java.util.*;
import java.text.*;
import javax.swing.table.*;

/**
 *
 * @author 732011 <up732011@myport.ac.uk>
 */
public class SQLManager {

    Connection localConn;

    public SQLManager() throws Exception

    {
        Connection c = null;

        //creates or opens a local database
        localConn = DriverManager.getConnection("jdbc:sqlite:.\\db\\pipesrusdb");

    }

    private String appendColumns(String[] columns)
    {
        StringBuilder appendedColumns = new StringBuilder();
        if (columns != null) {

            for (String column : columns) {
                appendedColumns.append(column);
                if(!columns[columns.length-1].equals(column))
                    appendedColumns.append(",");
            }

        } else {
            appendedColumns.append(" * ");
        }

        return appendedColumns.toString();
    }

    private ResultSet selectFrom(String table,
            String[] columns)
            throws SQLException
    {
        StringBuilder sqlStmt = new StringBuilder("SELECT ");
        sqlStmt.append(table);
        sqlStmt.append(appendColumns(columns));

        sqlStmt.append("FROM ");
        sqlStmt.append(table);
        
        return localConn.createStatement().executeQuery(sqlStmt.toString());
    }

    private long selectFrom(String table,
            String[] columns, HashMap<String, Object> columnValuePairs)
            throws SQLException
    {
        StringBuilder sqlStmt = new StringBuilder("SELECT ");
        
        
        sqlStmt.append(appendColumns(columns));
        
        sqlStmt.append(" FROM "); 
        sqlStmt.append(table);
        sqlStmt.append(" WHERE ");
        Iterator<HashMap.Entry<String, Object>> it = columnValuePairs.entrySet().iterator();
        while (it.hasNext()) {
            
            HashMap.Entry<String, Object> pair = it.next();
            sqlStmt.append(pair.getKey());
            sqlStmt.append("='");
            sqlStmt.append(pair.getValue());
            sqlStmt.append("'");
            if(it.hasNext())
                sqlStmt.append(" AND ");
        }
        //System.out.println(sqlStmt.toString());
        Statement stmt = localConn.createStatement();
        ResultSet rs = stmt.executeQuery(sqlStmt.toString());
        
        
        if(!rs.next())
        {
            return 0;
        }
        else
        {
            //Error around here...
            //PEACE OUT
            return rs.getLong(1);
        }

    }

    private long insertInto(String table,
            String[] columns,
            Object[] params)
            throws Exception
    {
        StringBuilder sqlStmt = new StringBuilder("INSERT INTO ");
        sqlStmt.append(table);
        sqlStmt.append(" (");
        for (int i = 0; i < columns.length; i++) {
            if(i != 0)
                sqlStmt.append(",");
            sqlStmt.append(columns[i]);
            
        }
        sqlStmt.append(") VALUES(");
        
        for (int i = 0; i < params.length; i++) {
            if (i != 0) {
                sqlStmt.append(",");
            }
            sqlStmt.append("?");
            
        }
        
        sqlStmt.append(")");
         //System.out.println(sqlStmt.toString());
        PreparedStatement stmt = localConn.prepareStatement(sqlStmt.toString());
        if(stmt == null)
            throw new Exception("Statement is null");
        
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
        
        stmt.execute();
        
        ResultSet rs = stmt.getGeneratedKeys();
        if(rs == null)
            throw new Exception("Result set is null");
        if(rs.next())
        {
            return rs.getLong(1);
        }
        else
            return 0;
        
    }

    private String boolToInt(boolean bool)
    {
        return new Integer(bool ? 1 : 0).toString();
    }

    private long insertPipe(PipeModel pipe) throws Exception
    {
        String[] columns = new String[]{"Grade", "Colour", "Insulated", "Reinforced", "ChemicalResistance", "PipeVolume"};
        HashMap<String, Object> columnValues = new HashMap<>();
        columnValues.put("Grade", pipe.getPipeGrade());
        columnValues.put("Colour", pipe.getPipeColour().toString());
        columnValues.put("Insulated", boolToInt(pipe.getInsulated()));
        columnValues.put("Reinforced", boolToInt(pipe.getReinforced()));
        columnValues.put("ChemicalResistance", boolToInt(pipe.getChemicalResistance()));
        columnValues.put("PipeVolume", pipe.getVolume());
         //System.out.println("Selecting a pipe");
        long pipeID = selectFrom("Pipe", columns, columnValues);
         //System.out.println("Going to see if there is a pipe");
        
        
        if (pipeID == 0)
        {
             //System.out.println("Inserting new pipe");
            pipeID = insertInto("Pipe", columns, new String[]{pipe.getPipeGrade().toString(), pipe.getPipeColour().toString(),
                boolToInt(pipe.getInsulated()), boolToInt(pipe.getReinforced()), boolToInt(pipe.getChemicalResistance()), pipe.getVolume().toString()});

        }
         //System.out.println("Returning: " + pipeID);
        return pipeID;
    }

    public void insertPipeArray(PipeModel[] pipes, Integer customerID) throws Exception
    {
       
        if(pipes.length == 0)
        {
            throw new Exception("No pipes");
        }
        HashMap<String, Object> whereClause = new HashMap<>();
        whereClause.put("CustomerID", customerID);
         //System.out.println("Checking for customer");
        long id = selectFrom("Customer",
                new String[]{"CustomerID"},
                whereClause);
        if (id == 0) {
            throw new Exception("Customer does not exist");
        }
         //System.out.println("Creating Order");
        String sql = "INSERT INTO PipeOrder(CustomerID) VALUES(?)";

        PreparedStatement stmt = localConn.prepareStatement(sql);

        stmt.setInt(1, customerID);

        stmt.execute();
         //System.out.println("Going to insert into orders");
        Long orderID = stmt.getGeneratedKeys().getLong(1);

        ArrayList<Long> pipesOrdered = new ArrayList<>();
        for (PipeModel pipe : pipes) {
             //System.out.println("Iterating");
            Long pipeID = insertPipe(pipe);
            pipesOrdered.add(pipeID);
        }

        String[] colummns = new String[]{"OrderID", "PipeID", "OrderedDate"};
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");
        df.setTimeZone(tz);
        String ISODate = df.format(new java.util.Date());
        for (Long pipeID : pipesOrdered) {
            
            insertInto("ProductOrdered", colummns,
                    new String[]{orderID.toString(),
                        pipeID.toString(),
                        ISODate});
        }

    }
    
    public DefaultTableModel getPipeOrders() throws SQLException
    {
        String sql = "SELECT * FROM PipeOrder p "
                + "JOIN ProductOrdered po ON p.OrderID = po.OrderID "
                + "JOIN Pipe pp ON pp.PipeID = po.PipeID "
                + "JOIN Customer c ON c.CustomerID = p.CustomerID";
        
        ResultSet results = localConn.createStatement().executeQuery(sql);
        ResultSetMetaData metaData = results.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (results.next()) {
            Vector<Object> row = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                row.add(results.getObject(columnIndex));
                //System.out.println(results.getObject(columnIndex));
            }
            data.add(row);
        }
        
        return new DefaultTableModel(data, columnNames);

    }
    
}
