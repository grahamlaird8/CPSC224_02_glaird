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
    private JLabel title = new JLabel("Assignment Tracker");
    private JPanel titleP = new JPanel();
    private JPanel bottomPanel;
    private JPanel rightPanel = new JPanel();
    private ArrayList<HWAssignment> list = new ArrayList<>();
    private ArrayList<JPanel> panels = new ArrayList<>();
    private int numAssignments = database.numItems();
    private ArrayList<JButton> assignments = new ArrayList<>(numAssignments);
    Color customDarkGrey = new Color(43,53,68);
    Color customDark = new Color(37,42,51);
    Color customLightGrey = new Color(109,121,140);
    
    
    
    public FinalProject()
    {
        
        JOptionPane.showMessageDialog(null, numAssignments);
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
        titleP.setBackground(customLightGrey);
        title.setForeground(Color.white);
        titleP.setBorder(BorderFactory.createEtchedBorder());
        title.setFont(new Font("Helvetica",Font.BOLD,40));
        titleP.add(title);
        frame.add(titleP,BorderLayout.NORTH);
        buildBottomPanel();
        buildRightPanel();
        centerPanel.setBackground(Color.black);
        buildCenterPanel();
        // Add the info to the window
        //add(panel, BorderLayout.CENTER);
        //add(playerPanel, BorderLayout.NORTH); // adds the two player panels
        frame.add(centerPanel, BorderLayout.CENTER);
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
        centerPanel.setBackground(customDarkGrey);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 6));
        //centerPanel.setLayout(new GridLayout(numAssignments, 1));
        String sName;
        String sUser;
        String sTotal;
        String sDueDate;
        String[] all = new String[numAssignments];
        
        
        
        JLabel name;
        JLabel dueDate;
        JLabel user;
        JLabel subject;
        JLabel description;
        JLabel reminderDate;
        
        
        for(int i = 0; i < numAssignments; i++)
        {
            panel = new JPanel();
            panel.setBackground(customLightGrey);
            sName = "                    Assignment: " + list.get(i).getName();
            sDueDate = "Due Date: " + Integer.toString(list.get(i).getDate());
            sUser = "      User: " + list.get(i).getUser();
            sTotal = sDueDate + sName + sUser;
            all[i] = sTotal;
            
            //subject = new JLabel("Subject: " + list.get(i).getSubject());
            //description = new JLabel("Description: " + list.get(i).getDescription());
            //reminderDate = new JLabel("Reminder Date: " + Integer.toString(list.get(i).getReminderDate()));
            //name.setForeground(Color.white);
            
            
            //panel.add(name);
            //panel.add(dueDate);
            //panel.add(user);
            //panel.add(subject);
            //panel.add(description);
            //panel.add(reminderDate);
            //panel.setBorder(BorderFactory.createEtchedBorder(customDark, Color.white));
            //centerPanel.add(panel);
        }
        
        JList list = new JList(all);
        list.setBackground(customDarkGrey);
        list.setForeground(Color.white);
        list.setPreferredSize(new Dimension(600, 800));
        list.setFont(new Font("Helvetica",Font.BOLD,20));
        JScrollPane sp = new JScrollPane(list);
        sp.setPreferredSize(new Dimension(800, 800));
        centerPanel.add(sp);
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
                        frame.add(centerPanel, BorderLayout.CENTER);
                        frame.setVisible(true);
                    }
                        break;
            }
        }
        
    }
    
    public static void main(String[] args) {
        new FinalProject();
    }
    
}