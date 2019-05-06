/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

/**
 *
 * @author Finni
 */
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Database {

    public Database()
    {
        createTable();
    }
    
    
    public static void createTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sqlite/db/AssignmentManager.db";
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS AssignmentManager (\n"
                + "	user text NOT NULL,\n"
                + "	name text NOT NULL,\n"
                + "     class text NOT NULL,\n"
                + "     duedate integer NOT NULL,\n"
                + "     reminderdate integer,\n"
                + "	description text,\n"
                + "     CONSTRAINT key PRIMARY KEY(user, name, class)\n "
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
        String url = "jdbc:sqlite:C://sqlite/db/AssignmentManager.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insert(String userName, String assignmentName, String courseName, String dueDate, String reminderDate, String description) {
        String sql = "INSERT INTO AssignmentManager (user, name, class, duedate, reminderdate, description) VALUES(?,?,?,?,?,?)";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userName);
            pstmt.setString(2, assignmentName);
            pstmt.setString(3, courseName);
            pstmt.setString(4, dueDate);
            pstmt.setString(5, reminderDate);
            pstmt.setString(6, description);
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public int numItems()
    {
        int num = 0;
        String sql = "SELECT COUNT(*) FROM AssignmentManager";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
             while(rs.next())
             {
                 num = rs.getInt(1);
             }
            
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return num;
    }
    
    public ArrayList<HWAssignment> selectAll(){
        String sql = "SELECT user, name, class, duedate, reminderdate, description FROM AssignmentManager";
        int i = 0;
        ArrayList<HWAssignment> list = new ArrayList<>();
        HWAssignment assignment = new HWAssignment();
        
        try (Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                assignment = new HWAssignment();
                assignment.setUser(rs.getString("user"));
                assignment.setName(rs.getString("name"));
                assignment.setSubject(rs.getString("class"));
                assignment.setDueDate(rs.getString("duedate"));
                assignment.setReminderDate(rs.getString("reminderdate"));
                assignment.setDescription(rs.getString("description"));
                
                
                list.add(i, assignment);
                
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    public void delete(String assignmentName){
        String sql = "DELETE FROM AssignmentManager WHERE name = ?";
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
            pstmt.setString(1, assignmentName);
            // execute the delete statement
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}