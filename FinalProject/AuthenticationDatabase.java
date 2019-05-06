/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Finni
 */
public class AuthenticationDatabase {
    public AuthenticationDatabase()
    {
        createTable();
    }
    
    
    public static void createTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sqlite/db/UserAuthentication.db";
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS userAuthentication (\n"
                + "	user text,\n"
                + "	password text NOT NULL,\n"
                + "     CONSTRAINT key PRIMARY KEY(user, password)\n "
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sqlite/db/UserAuthentication.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void newUser(String username, String password) {
        String sql = "INSERT INTO UserAuthentication (user, password) VALUES(?,?)";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public String getData(String username) {
        String sql = "SELECT user, password FROM UserAuthentication";
        String data = "";
        String tempUser = "";
       // passwordChecker check = new passwordChecker();

        try (Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                if(rs.getString("user").equals(username))
                    data = rs.getString("password");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data; 
    }
}
