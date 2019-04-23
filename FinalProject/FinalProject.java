/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 *
 * @author Finni
 */
public class FinalProject{

    private Database database = new Database();
    private JFrame frame = new JFrame();
    static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
    private JTextField assignmentNameInput;
    private JTextField assignmentUserInput;
    private JTextField assignmentDueDateInput;
    private JTextField assignmentSubjectInput;
    private JTextField assignmentDescriptionInput;
    private JTextField assignmentReminderDateInput;
    private JButton addAssignment;
    private JPanel centerPanel = new JPanel();
    private JPanel bottomPanel;
    private JPanel rightPanel = new JPanel();
    private ArrayList<HWAssignment> list = new ArrayList<>();
    private ArrayList<JPanel> panels = new ArrayList<>();
    private int numAssignments = 0;
    
    
    
    public FinalProject()
    {
        frame.setTitle("Homework Planner");
        frame.setSize(1920, 1080);

        // Specify what happens when the close button is clicked.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //build the middle panel to hold the TicTacToe buttons
        //buildMiddlePanel();

        //build the panel to hold player info
        //buildPlayerPanel();

        //build bottom panel to hold 3 status buttons and status bar
        buildBottomPanel();
        buildRightPanel();
        buildCenterPanel();
        // Add the info to the window
        //add(panel, BorderLayout.CENTER);
        //add(playerPanel, BorderLayout.NORTH); // adds the two player panels
        frame.add(centerPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.EAST);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        

        frame.setVisible(true);   
    }
    
    private void buildBottomPanel()
    {
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        addAssignment = new JButton("Add New Assignment");
        addAssignment.addActionListener(new ButtonListener());
        bottomPanel.add(addAssignment);
    }
    
    private void buildRightPanel()
    {
        
        JLabel label1 = new JLabel("Assignment Name: ");
        JLabel label2 = new JLabel("User: ");
        JLabel label3 = new JLabel("Due Date (MMDDYYYY): ");
        JLabel label4 = new JLabel("Subject: ");
        JLabel label5 = new JLabel("Description (Optional): ");
        JLabel label6 = new JLabel("Reminder Date (Optional, MMDDYYYY): ");
        assignmentNameInput = new JTextField();
        assignmentUserInput = new JTextField();
        assignmentDueDateInput = new JTextField();
        assignmentSubjectInput = new JTextField();
        assignmentDescriptionInput = new JTextField();
        assignmentReminderDateInput = new JTextField();
       
        rightPanel.setBorder(BorderFactory.createTitledBorder("Add New Assignment"));
        rightPanel.setLayout(new GridLayout(6, 2));
        rightPanel.add(label1);
        rightPanel.add(assignmentNameInput);
        rightPanel.add(label2);
        rightPanel.add(assignmentUserInput);
        rightPanel.add(label3);
        rightPanel.add(assignmentDueDateInput);
        rightPanel.add(label4);
        rightPanel.add(assignmentSubjectInput);
        rightPanel.add(label5);
        rightPanel.add(assignmentDescriptionInput);
        rightPanel.add(label6);
        rightPanel.add(assignmentReminderDateInput);
       
    }
    
    private void buildCenterPanel()
    {
        list = database.selectAll();
        centerPanel.removeAll();
        
        
        JLabel name;
        JLabel dueDate;
        JLabel user;
        JLabel subject;
        JLabel description;
        JLabel reminderDate;
        
        for(int i = 0; i < numAssignments; i++)
        {
            name = new JLabel("Name: " + list.get(i).getName());
            dueDate = new JLabel("Due Date: " + Integer.toString(list.get(i).getDate()));
            user = new JLabel("User: " + list.get(i).getUser());
            subject = new JLabel("Subject: " + list.get(i).getSubject());
            description = new JLabel("Description: " + list.get(i).getDescription());
            reminderDate = new JLabel("Reminder Date: " + Integer.toString(list.get(i).getReminderDate()));
            
            centerPanel.add(name);
            centerPanel.add(dueDate);
            centerPanel.add(user);
            centerPanel.add(subject);
            centerPanel.add(description);
            centerPanel.add(reminderDate);
        }
    }
    
    
    private class ButtonListener implements ActionListener
    {
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String actionCommand = e.getActionCommand();
            switch (actionCommand){
                case "Add New Assignment":
                    if(assignmentNameInput.getText().equals("") || assignmentUserInput.getText().equals("") || assignmentDueDateInput.getText().equals("") || assignmentSubjectInput.getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "Non-optional fields must be filled out!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        database.insert(assignmentUserInput.getText(), assignmentNameInput.getText(), assignmentSubjectInput.getText(), Integer.parseInt(assignmentDueDateInput.getText()), Integer.parseInt(assignmentReminderDateInput.getText()), assignmentDescriptionInput.getText());
                        numAssignments++;
                        frame.remove(centerPanel);
                        buildCenterPanel();
                        frame.add(centerPanel);
                    }
                        break;
            }
        }
        
    }
    
    public static void main(String[] args) {
        new FinalProject();
    }
    
}
