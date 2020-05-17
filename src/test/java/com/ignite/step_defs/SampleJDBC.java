package com.ignite.step_defs;


import com.ignite.pojos.Student;
import org.junit.Test;

import java.sql.*;

public class SampleJDBC {
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    String url = "192.168.1.15\\COOLSQLIGNITE";
    String user = "ignite";
    String password = "Eh7ruY3!p@82";  //  aK%82Fnx!3d

    String applicationNo = "5902002580";


    @Test
    public void test(){
        // Create a variable for the connection string.
        String connectionUrl = "jdbc:sqlserver://"+url+";databaseName=CoolSIS_101;user="+user+";password="+password;

        try {
            connection = DriverManager.getConnection(connectionUrl);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

            // String sql = "SELECT * FROM [stdApplications] WHERE applicationNo =" +applicationNo;

            resultSet = statement.executeQuery("SELECT * FROM [stdApplications] WHERE applicationNo = "+applicationNo+";");

            // 1nci sonuc
            System.out.println("===== 1st CALL =====");
            System.out.println("FIRST ROW: " + resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void compareSQL(ResultSet result) throws SQLException {

        Student myStudent = new Student();
        String studentName = resultSet.getString("firstName");
        int hesapNo = resultSet.getInt("hesapNO");





    }


    public String compareWithSQL(){
        Student myStudent = new Student();
        return "SELECT COUNT(*)  as count FROM [stdApplications] WHERE applicationNo = '"+applicationNo+"" +
                "' and lastName = '"+ myStudent.lastName+
                "' and firstName = '"+ myStudent.firstName;

    }

}
