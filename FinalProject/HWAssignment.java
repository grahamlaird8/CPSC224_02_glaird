/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

/**
 *
 * @author Finnian Allen and Graham Laird
 */
public class HWAssignment {
     // setters getters whatever else
    private int dueDate;
    private int reminderDate;
    private String subject;
    private String user;
    private String name;
    private String description;

    //constructor
    public HWAssignment (int dueDate, int reminderDate, String subject, String user, String name, String description) {
        this.dueDate = dueDate;
        this.reminderDate = reminderDate;
        this.subject = subject;
        this.user = user;
        this.name = name;
        this.description = description;
    }
    
    public HWAssignment()
    {
    }
    
    // setters
    public void setDueDate(int dueDate) // assigns name
    {
        this.dueDate = dueDate;
    }
    
   
    public void setReminderDate(int reminderDate) // assigns date
    {
        this.reminderDate = reminderDate;
    }
    
    public void setSubject(String subject) // assigns a class name
    {
        this.subject = subject;
    }
    
    public void setUser(String user) // assigns user
    {
        this.user = user;
    }
    
    public void setName(String name) // assigns name
    {
        this.name = name;
    }
    
    public void setDescription(String description) // assigns name
    {
        this.description = description;
    }
    
    
    
    
    
    // getters
    
    public int getDate() {
        return this.dueDate;
    }
    
    public int getReminderDate() {
        return this.reminderDate;
    }
    
    public String getSubject()
    {
        return this.subject;
    }
    
    public String getUser()
    {
        return this.user;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public String getDescription() // assigns name
    {
        return this.description;
    }
}
