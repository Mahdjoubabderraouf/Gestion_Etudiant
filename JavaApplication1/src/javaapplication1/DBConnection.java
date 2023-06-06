package javaapplication1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection{
        private Connection newConnection;
        private final String url = "jdbc:oracle:thin:@localhost:1521:XE";
        private String username ;
        private String password ;

    public DBConnection(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Connection getNewConnection() {
        return newConnection;
    }

    public void setNewConnection(Connection newConnection) {
        this.newConnection = newConnection;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DBConnection() {
        
        this.username = "BDDAdmin";
        this.password = "TPAdmin";
        
    }
        
    public  void connexion() {
        // Database connection parameters
        try {
            // Register the Oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Establish the connection
            newConnection = DriverManager.getConnection(url, username, password);
            // Connection successful, do your database operations here

            
        } catch (ClassNotFoundException | SQLException e) {
        }
    }
    public  void deconnexion() throws SQLException{ 
        // Close the connection
           newConnection.close();
    }
    
}
