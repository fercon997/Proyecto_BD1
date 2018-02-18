/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Ignacio
 */
public class DBConnection {
    
    public void connectToPostgres() throws SQLException {
        System.out.println("-------- PostgreSQL JDBC Connection Testing ------------");
        
        try {
            Class.forName("org.postgresql.Driver");
        } catch(ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver not found. Include it in your Libraries path.");
            e.printStackTrace();
            return;
        }
        
        System.out.println("PostgreSQL JDBC Driver Registered!");
        
        Connection connection = null;
        
        try {
            connection = DriverManager.getConnection(
                            "jdbc:postgresql://127.0.0.1:5432/test", "Ignacio", "");
        } catch(SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
            return;
        }
        
        if (connection != null) {
            System.out.println("Connected to PostgreSQL");
        } else {
            System.out.println("Could not connect to PostgreSQL");
        }
        
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM estudiante");
        
        while (rs.next()) {
            System.out.println("Columna 1");
            System.out.println(rs.getString(1));
        }
        rs.close();
        st.close();
    }
}
