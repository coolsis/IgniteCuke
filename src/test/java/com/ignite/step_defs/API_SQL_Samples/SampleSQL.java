package com.ignite.step_defs.API_SQL_Samples;


import org.junit.Test;

import java.sql.*;

public class SampleSQL {

    Connection connection;
    Statement statement;
    ResultSet resultSet;

    public static void main(String[] args) {
        String url = "192.168.1.15\\COOLSQLIGNITE";
        String user = "ignite";
        String password = "Eh7ruY3!p@82";

        // Create a variable for the connection string.
        String connectionUrl = "jdbc:sqlserver://"+url+";databaseName=CoolSIS_101;user="+user+";password="+password;

        try (
            Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement() )
        {
            String SQL = "SELECT  * FROM [stdApplications]";
            ResultSet rs = stmt.executeQuery(SQL);
            System.out.println(rs);
            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                System.out.println(rs.getString("firstName") + " " + rs.getString("lastName"));
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() throws SQLException {
        String url = "192.168.1.15\\COOLSQLIGNITE";
        String user = "ignite";
        String password = "Eh7ruY3!p@82";
        String connectionUrl = "jdbc:sqlserver://"+url+";databaseName=CoolSIS_101;user="+user+";password="+password;

        String applicationNo = "5902002580";

        connection = DriverManager.getConnection(connectionUrl);
        //We need to create a statement

        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        //Now we gonna run a query, so for that we need to create a result set
        ResultSet resultSet = statement.executeQuery("SELECT * FROM [stdApplications] WHERE applicationNo = "+applicationNo+";");

        //go to first row
        System.out.println("\n\n========-:| FIRST ROW |:-========");
        resultSet.first();
        System.out.println(resultSet.getRow()+" :: "+ resultSet.getString("lastName"));

        //go to afterLast
        System.out.println("\n\n========-:| afterLast |:-========");
        //resultSet.afterLast();
        System.out.println(resultSet.getString("firstName"));

        String query = "SELECT COUNT(*) as count \n" +
                "FROM [stdApplications]\n" +
                "WHERE applicationNo = '5902002579' and  lastName='ayaz'";
        //to run query
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        //we are running query (command)
        resultSet = statement.executeQuery(query);
        boolean exists = false;
        try {
            //to jump yo yje first record
            resultSet.next();
            //we are checking if we have more then 0
            exists = resultSet.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("\n\nApplication " + (exists?"Found":"NOT FOUND"));
    }
}
