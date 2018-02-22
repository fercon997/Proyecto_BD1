package Model;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DBConnection {
    Connection connection;
    
    public Connection connectToPostgres() {
        System.out.println("-------- PostgreSQL JDBC Connection Testing ------------");
        
        try {
            Class.forName("org.postgresql.Driver");
        } catch(ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos.");
            System.out.println("La librería de PostgreSQL no pudo ser encontrada.");
            e.printStackTrace();
        }
        
        System.out.println("El driver de PostgreSQL fue encontrada!");
        
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/proyecto", "Ignacio", "");
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos.");
            System.out.println("Conexión fallida");
            e.printStackTrace();
        }
        
        if (connection != null) {
            System.out.println("Conectado a Postgres");
        } else {
            System.out.println("No se pudo conectar a Postgres");
        }
        return connection;
    }
}
