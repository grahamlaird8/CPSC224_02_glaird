/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
 
public class LoginForm extends JFrame{
    
 private AuthenticationDatabase database = new AuthenticationDatabase();
 private JLabel title, name, pass;
 private JTextField usernameInput;
 private JButton btn1;
 private JButton btn2;
 private JPasswordField passInput;
 LoginForm() {
  JFrame frame = new JFrame("Login Form");
  title = new JLabel("Login Form");
  title.setFont(new Font("Helvetica", Font.BOLD, 20));
 
  name = new JLabel("Username");
  pass = new JLabel("Password");
  usernameInput = new JTextField();
  passInput = new JPasswordField();
  btn1 = new JButton("Login");
  btn2 = new JButton("Register");
 
  title.setBounds(80, 30, 400, 30);
  name.setBounds(80, 70, 200, 30);
  pass.setBounds(80, 110, 200, 30);
  usernameInput.setBounds(300, 70, 200, 30);
  passInput.setBounds(300, 110, 200, 30);
  btn1.setBounds(200, 160, 100, 30);
  btn2.setBounds(200, 200, 100, 30);
  
  btn1.addActionListener(new ButtonListener());
  btn2.addActionListener(new ButtonListener());
  
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
  frame.add(title);
  frame.add(name);
  frame.add(usernameInput);
  frame.add(pass);
  frame.add(passInput);
  frame.add(btn1);
  frame.add(btn2);
 
  frame.setSize(600, 400);
  frame.setLayout(null);
  frame.setVisible(true);
 }
 private class ButtonListener implements ActionListener
 {
 public void actionPerformed(ActionEvent e)
 {
     passwordChecker check = new passwordChecker();
     if(e.getSource() == btn1)
     {
        String username = usernameInput.getText();
        String password = passInput.getText();
        if(check.getPass() == password)
        {
            System.out.println("cool");
        }
        else
        {
            System.out.println("woops");  
        }
     } else if(e.getSource() == btn2)
     {
        String username = usernameInput.getText();
        String password = passInput.getText();
        database.newUser(username, password);
        
     }
  }
 }

 public static void main(String[] args) {
  new LoginForm();
 }
}