
package hw4;

import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.*;


public class HW4 extends JFrame{

	private int WINDOW_WIDTH = 500;
	private int WINDOW_HEIGHT = 500;
	private int foregroundX = 250; // Mouse cursor's X position
	private int midgroundX = 250; // Mouse cursor's Y position
	private int backgroundX = 250;
	private int foregroundY = 250; // Mouse cursor's X position
	private int midgroundY = 250; // Mouse cursor's Y position
	private int backgroundY = 250;
	private int mouseX = 250;
	private int mouseY = 250; // Mouse cursor's X position
	private int clickedX;
	private int clickedY;
	private boolean clicked;
	private boolean parallaxActive;



	public HW4
	{
		
	}
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    private class MyMouseListener implements MouseListener
	{
		public void mousePressed(MouseEvent e)
		{
		}

		public void mouseClicked(MouseEvent e)
		{		
			clicked = true;
			clickedX = e.getX();
			clickedY = e.getY();
		}

		public void mouseReleased(MouseEvent e)
		{
		}

		public void mouseEntered(MouseEvent e)
		{
			parallaxActive = true;
		}

		public void mouseExited(MouseEvent e)
		{
			parallaxActive = false;
		}
	}

   /**
      Private inner class to handle mouse motion events.
   */
   
	private class MyMouseMotionListener implements MouseMotionListener
	{
		public void mouseDragged(MouseEvent e)
		{
		}

		public void mouseMoved(MouseEvent e)
		{	    
		}
	}
   
	private class MyActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
		}     
	}
}
