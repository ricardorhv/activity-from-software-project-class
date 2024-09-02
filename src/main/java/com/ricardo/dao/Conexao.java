package com.ricardo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexao {
    private static final String user = "postgres";
    private static final String password = "root";
    private static final String url = "jdbc:postgresql://localhost:5432/project1_aps"; 
    
    public static Connection connection;
    
    public static Connection getConnection() {
        try {
            if (connection == null) {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, user, password);
            }
            
            return connection;
            
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Error: " + error.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (ClassNotFoundException error) {
            JOptionPane.showMessageDialog(null, "Error: " + error.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
