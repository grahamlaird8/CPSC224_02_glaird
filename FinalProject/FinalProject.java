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
import javax.swing.event.*;

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
    private final JLabel title = new JLabel("Assignment Tracker");
    private JPanel titleP = new JPanel();
    private JPanel bottomPanel;
    private JPanel rightPanel = new JPanel();
    private ArrayList<HWAssignment> list = new ArrayList<>();
    private ArrayList<HWAssignment> listForUser = new ArrayList<>();
    private int totAssignments = database.numItems();
    private int numAssignments;
    Color customDarkGrey = new Color(43,53,68);
    Color customDark = new Color(37,42,51);
    Color customLightGrey = new Color(109,121,140);
    Color spray = new Color(129, 207, 224);
    private final String[] months = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    private final String[] days = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    private final String[] years = new String[]{"2019", "2020", "2021", "2022", "2023", "2024", "2025"};
    private String dueMonth;
    private String dueDay;
    private String dueYear;
    private String reminderDueMonth;
    private String reminderDueDay;
    private String reminderDueYear;
    public String username;
    private JList assignmentList;
    private Popup p;
    private LoginForm login;
    private int index;
    JComboBox monthMenu = new JComboBox(months);
    JComboBox daysMenu = new JComboBox(days);
    JComboBox yearMenu = new JComboBox(years);
    JComboBox reminderMonthMenu = new JComboBox(months);
    JComboBox reminderDaysMenu = new JComboBox(days);
    JComboBox reminderYearMenu = new JComboBox(years);
    
    
    public FinalProject(String user)
    {
        username = user;
        frame.setTitle("Homework Planner");
        frame.setSize(1920, 1080);

        // Specify what happens when the close button is clicked.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

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
        addAssignment.setBackground(spray);
        removeAssignment = new JButton("Delete Assignment");
        removeAssignment.setBackground(spray);
        addAssignment.addActionListener(new ButtonListener());
        removeAssignment.addActionListener(new ButtonListener());
        bottomPanel.add(addAssignment);
        bottomPanel.add(removeAssignment);
    }
    
    private void buildRightPanel()
    {
        
        JLabel label1 = new JLabel("Assignment Name: ");
        JLabel label3 = new JLabel("Due Date (MMDDYYYY): ");
        JLabel label4 = new JLabel("Course: ");
        JLabel label5 = new JLabel("Description (Optional): ");
        JLabel label6 = new JLabel("Reminder Date (Optional, MMDDYYYY): ");
        label1.setForeground(Color.WHITE);
        label3.setForeground(Color.WHITE);
        label4.setForeground(Color.WHITE);
        label5.setForeground(Color.WHITE);
        label6.setForeground(Color.WHITE);
        assignmentNameInput = new JTextField();
        assignmentSubjectInput = new JTextField();
        assignmentDescriptionInput = new JTextField();
        JPanel datePanel = new JPanel();
        datePanel.setBackground(Color.DARK_GRAY);
        JPanel reminderDatePanel = new JPanel();
        reminderDatePanel.setBackground(Color.DARK_GRAY);
        
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
        
        rightPanel.setBorder(BorderFactory.createTitledBorder("Add New Assingment"));
        rightPanel.setBackground(Color.DARK_GRAY);
        rightPanel.setLayout(new GridLayout(6, 2));
        rightPanel.add(label1);
        rightPanel.add(assignmentNameInput);
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
        panel.setBackground(Color.DARK_GRAY);
        String sName;
        String sCourse;
        String sTotal;
        String sDueDate;
        String[] all;
        numAssignments = 0;
        
        for(int i = 0; i < totAssignments; i++)
        {
            if(list.get(i).getUser().equals(username))
            {
                listForUser.add(numAssignments, list.get(i));
                numAssignments++;
            }
            
        }
        
        
        all = new String[numAssignments];
        for(int i = 0; i < numAssignments; i++)
        {
            panel = new JPanel();
            panel.setBackground(customLightGrey);
            sName = "                    Assignment: " + listForUser.get(i).getName();
            sDueDate = "Due Date: " + listForUser.get(i).getDate();
            sCourse = "      Course: " + listForUser.get(i).getSubject();
            sTotal = sDueDate + sName + sCourse;
            all[i] = sTotal;
        }
        
        assignmentList = new JList(all);
        assignmentList.setBackground(Color.DARK_GRAY);
        assignmentList.setForeground(Color.white);
        assignmentList.setPreferredSize(new Dimension(600, 800));
        assignmentList.setFont(new Font("Helvetica",Font.BOLD,20));
        assignmentList.addListSelectionListener(new assignmentListListener());
        JScrollPane sp = new JScrollPane(assignmentList);
        sp.setPreferredSize(new Dimension(800, 800));
        centerPanel.add(sp);
    }
    
    private class assignmentListListener implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if(index != assignmentList.getSelectedIndex())
            {
                index = assignmentList.getSelectedIndex();
                //JOptionPane.showMessageDialog(null, index);

                JFrame f = new JFrame("Selected Assignment");
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(6,1));
                JLabel name = new JLabel("Assignment: " + listForUser.get(index).getName());
                JLabel dueDate = new JLabel("Due Date: " + listForUser.get(index).getDate());
                JLabel subject = new JLabel("Course: " + listForUser.get(index).getSubject());
                JLabel description = new JLabel("Description: " + listForUser.get(index).getDescription());
                JLabel reminderDate = new JLabel("Reminder Date: " + listForUser.get(index).getReminderDate());
                name.setFont(new Font("Helvetica", Font.BOLD,20));
                name.setForeground(Color.white);
                dueDate.setFont(new Font("Helvetica", Font.BOLD,20));
                dueDate.setForeground(Color.white);
                subject.setFont(new Font("Helvetica", Font.BOLD,20));
                subject.setForeground(Color.white);
                description.setFont(new Font("Helvetica", Font.BOLD,20));
                description.setForeground(Color.white);
                reminderDate.setFont(new Font("Helvetica", Font.BOLD,20));
                reminderDate.setForeground(Color.white);
                panel.add(name);
                panel.add(dueDate);
                panel.add(subject);
                panel.add(description);
                panel.add(reminderDate);
                f.setSize(400,400);
                PopupFactory pf = new PopupFactory();
                panel.setBackground(customDarkGrey);
                panel.setForeground(Color.white);
                p = pf.getPopup(f, panel, 600, 600);
                f.add(panel);
                f.show();
            }
        }
        
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
                    if(assignmentNameInput.getText().equals("") || username.equals("") || assignmentSubjectInput.getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "Non-optional fields must be filled out!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        database.insert(username, assignmentNameInput.getText(), assignmentSubjectInput.getText(), dueMonth + "/" + dueDay + "/" + dueYear, reminderDueMonth + "/" + reminderDueDay + "/" + reminderDueYear, assignmentDescriptionInput.getText());
                        totAssignments = database.numItems();
                        frame.remove(centerPanel);
                        buildCenterPanel();
                        frame.add(centerPanel, BorderLayout.CENTER);
                        frame.setVisible(true);
                    }
                        break;
                case "Delete Assignment":
                    if(assignmentNameInput.getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "Non-optional fields must be filled out!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        database.delete(assignmentNameInput.getText(), username);
                        totAssignments = database.numItems(); 
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
        new LoginForm();
    }
    
}