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
        localConn = DriverManager.getConnection("jdbc:sqlite:.\\db\\local.db");

    }

    private String appendColumns(String[] columns)
    {
        StringBuilder appendedColumns = new StringBuilder();
        if (columns != null) {
            appendedColumns.append(" (");
            for (String column : columns) {
                appendedColumns.append(column);
            }
            appendedColumns.append(") ");
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

    private ResultSet selectFrom(String table,
            String[] columns, HashMap<String, Object> columnValuePairs)
            throws SQLException
    {
        StringBuilder sqlStmt = new StringBuilder("SELECT ");
        sqlStmt.append(table);

        sqlStmt.append(appendColumns(columns));

        sqlStmt.append(") WHERE(");
        Iterator<HashMap.Entry<String, Object>> it = columnValuePairs.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry<String, Object> pair = it.next();
            sqlStmt.append(pair.getKey());
            sqlStmt.append("='");
            sqlStmt.append(pair.getValue());
            sqlStmt.append("'");

        }
        return localConn.createStatement().executeQuery(sqlStmt.toString());

    }

    private ResultSet insertInto(String table,
            String[] columns,
            Object[] params)
            throws Exception
    {
        StringBuilder sqlStmt = new StringBuilder("INSERT INTO ");
        sqlStmt.append(table);
        sqlStmt.append(" (");
        for (String column : columns) {
            sqlStmt.append(column);
        }
        sqlStmt.append(") VALUES(");

        for (Object param : params) {
            sqlStmt.append("?");
            if (!params[params.length].equals(param)) {
                sqlStmt.append(",");
            }
        }

        sqlStmt.append(")");

        PreparedStatement stmt = localConn.prepareStatement(sqlStmt.toString());

        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }

        stmt.execute();

        return stmt.getResultSet();
    }

    private String boolToInt(boolean bool)
    {
        return new Integer(bool ? 1 : 0).toString();
    }

    private long insertPipe(PipeModel pipe) throws Exception
    {
        String[] columns = new String[]{"Grade", "Colour", "Insulated", "Reinforced", "ChemicalResistance"};
        HashMap<String, Object> columnValues = new HashMap<>();
        columnValues.put("Grade", pipe.getPipeGrade());
        columnValues.put("Colour", pipe.getPipeColour().toString());
        columnValues.put("Insulated", boolToInt(pipe.getInsulated()));
        columnValues.put("Reinforced", boolToInt(pipe.getReinforced()));
        columnValues.put("ChemicalResistance", boolToInt(pipe.getChemicalResistance()));
        ResultSet alreadyAPipe = selectFrom("Pipe", columns, columnValues);
        PreparedStatement stmt;

        long pipeID;
        if (alreadyAPipe == null) {
            pipeID = insertInto("Pipe", columns, new String[]{pipe.getPipeGrade().toString(), pipe.getPipeColour().toString(),
                boolToInt(pipe.getInsulated()), boolToInt(pipe.getReinforced()), boolToInt(pipe.getChemicalResistance())}).getLong(1);
        } else {
            pipeID = alreadyAPipe.getLong(1);
        }

        return pipeID;
    }

    public void insertPipeArray(PipeModel[] pipes, Integer customerID) throws Exception
    {
        HashMap<String, Object> whereClause = new HashMap<>();
        whereClause.put("CustomerID", customerID);
        ResultSet checkForCustomer = selectFrom("Customer",
                new String[]{"CustomerID"},
                whereClause);
        if (checkForCustomer == null) {
            throw new Exception("Customer does not exist");
        }

        String sql = "INSERT INTO PipeOrder(CustomerID) VALUES(?)";

        PreparedStatement stmt = localConn.prepareStatement(sql);

        stmt.setInt(1, customerID);

        stmt.execute();

        Long orderID = stmt.getGeneratedKeys().getLong(1);

        ArrayList<Long> pipesOrdered = new ArrayList<>();
        for (PipeModel pipe : pipes) {
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
}
