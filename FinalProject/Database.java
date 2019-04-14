/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;


import java.sql.*;
/**
 *
 * @author graha
 */
public class Database {

    public static void createTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://Program Files/AssignmentManager.db";
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS AssignmentManager (\n"
                + "	user text PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "     class text NOT NULL,\n"
                + "     duedate integer NOT NULL,\n"
                + "     reminderdate integer,\n"
                + "	description text,\n"
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
        String url = "jdbc:sqlite:C://Program Files/AssignmentManager.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    public void insert(String userName, String assignmentName, String courseName, int dueDate, int reminderDate, String description) {
        String sql = "INSERT INTO AssignmentManager(user, name, class, duedate, reminderdate, description) VALUES(?,?,?,?,?,?)";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userName);
            pstmt.setString(2, assignmentName);
            pstmt.setString(3, courseName);
            pstmt.setInt(4, dueDate);
            pstmt.setInt(5, reminderDate);
            pstmt.setString(6, description);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void selectAll(HWAssignment assignment[]){
        String sql = "SELECT user, name, class, duedate, reminderdate, description FROM AssignmentManager";
        int i = 0;
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                assignment[i].setUser(rs.getString("user"));
                assignment[i].setName(rs.getString("name"));
                assignment[i].setClass(rs.getString("class"));
                assignment[i].setDueDate(rs.getInt("duedate"));
                assignment[i].setReminderDate(rs.getInt("reminderdate"));
                assignment[i].setUser(rs.getString("description"));
                
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public static void main(String[] args) {
    }
    
}

