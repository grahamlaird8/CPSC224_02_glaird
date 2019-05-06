/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

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
    private JButton removeAssignment;
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
    private String[] months = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    private String[] days = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    private String[] years = new String[]{"2019", "2020", "2021", "2022", "2023", "2024", "2025"};
    private String dueMonth;
    private String dueDay;
    private String dueYear;
    private String reminderDueMonth;
    private String reminderDueDay;
    private String reminderDueYear;
    JComboBox monthMenu = new JComboBox(months);
    JComboBox daysMenu = new JComboBox(days);
    JComboBox yearMenu = new JComboBox(years);
    JComboBox reminderMonthMenu = new JComboBox(months);
    JComboBox reminderDaysMenu = new JComboBox(days);
    JComboBox reminderYearMenu = new JComboBox(years);
    
    
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
        bottomPanel.setLayout(new GridLayout(2, 1));
        addAssignment = new JButton("Add New Assignment");
        removeAssignment = new JButton("Delete Assignment");
        addAssignment.addActionListener(new ButtonListener());
        removeAssignment.addActionListener(new ButtonListener());
        bottomPanel.add(addAssignment);
        bottomPanel.add(removeAssignment);
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
        JPanel datePanel = new JPanel();
        JPanel reminderDatePanel = new JPanel();
        
        datePanel.add(monthMenu);
        datePanel.add(daysMenu);
        datePanel.add(yearMenu);
        monthMenu.addActionListener(new comboListenr());
        daysMenu.addActionListener(new comboListenr());
        yearMenu.addActionListener(new comboListenr());
        
        
        reminderDatePanel.add(reminderMonthMenu);
        reminderDatePanel.add(reminderDaysMenu);
        reminderDatePanel.add(reminderYearMenu);
        reminderMonthMenu.addActionListener(new comboListenr());
        reminderDaysMenu.addActionListener(new comboListenr());
        reminderYearMenu.addActionListener(new comboListenr());
       
        rightPanel.setBorder(BorderFactory.createTitledBorder("Add New Assignment"));
        rightPanel.setLayout(new GridLayout(6, 2));
        rightPanel.add(label1);
        rightPanel.add(assignmentNameInput);
        rightPanel.add(label2);
        rightPanel.add(assignmentUserInput);
        rightPanel.add(label3);
        rightPanel.add(datePanel);
        rightPanel.add(label4);
        rightPanel.add(assignmentSubjectInput);
        rightPanel.add(label5);
        rightPanel.add(assignmentDescriptionInput);
        rightPanel.add(label6);
        rightPanel.add(reminderDatePanel);
       
    }
    
    private void buildCenterPanel()
    {
        list = database.selectAll();
        Collections.sort(list, new CustomComparator());         //sort the list by due date, newest first
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
            sDueDate = "Due Date: " + list.get(i).getDate();
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
    
    private class comboListenr implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            dueMonth = monthMenu.getSelectedItem().toString();
            dueDay = daysMenu.getSelectedItem().toString();
            dueYear = yearMenu.getSelectedItem().toString();
            
            reminderDueMonth = reminderMonthMenu.getSelectedItem().toString();
            reminderDueDay = reminderDaysMenu.getSelectedItem().toString();
            reminderDueYear = reminderYearMenu.getSelectedItem().toString();
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
                    if(assignmentNameInput.getText().equals("") || assignmentUserInput.getText().equals("") || assignmentSubjectInput.getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "Non-optional fields must be filled out!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        database.insert(assignmentUserInput.getText(), assignmentNameInput.getText(), assignmentSubjectInput.getText(), dueMonth + "/" + dueDay + "/" + dueYear, reminderDueMonth + "/" + reminderDueDay + "/" + reminderDueYear, assignmentDescriptionInput.getText());
                        numAssignments++;
                        frame.remove(centerPanel);
                        buildCenterPanel();
                        frame.add(centerPanel, BorderLayout.CENTER);
                        frame.setVisible(true);
                    }
                        break;
                case "Delete Assignent":
                    if(assignmentNameInput.getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "Non-optional fields must be filled out!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        database.delete(assignmentUserInput.getText());
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
    
    public class CustomComparator implements Comparator<HWAssignment> {
    @Override
    public int compare(HWAssignment o1, HWAssignment o2) {
        String date1 = o1.getDate();
        String date2 = o2.getDate();
        date1 = date1.substring(4, 7) + date1.substring(0,3);
        date2 = date2.substring(4, 7) + date2.substring(0,3);
        return date1.compareTo(date2);
        }
    }
    
    public static void main(String[] args) {
        new FinalProject();
    }
    
}