/**
* @Authors: Finnian Allen and Graham Laird
* Assignment: HW4
* Due: 3/25 @ 11:59 PM
*/

package hw4;

import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.*;


public class HW4 extends JFrame{

	private int WINDOW_WIDTH = 517; // width of the window
	private int WINDOW_HEIGHT = 500; // height of the window
	private int foregroundX; // foreground's X position
	private int midgroundX; // midground's x position
	private int backgroundX; // background's x position
	private int foregroundY; // foreground's y position
	private int midgroundY; // Mouse cursor's Y position
	private int backgroundY; // background's Y position.
    private int sunX; // sun x position
	private int sunY; // sun y position
    private int xDifference; // for calculating difference in x needed for parallax
    private int yDifference; // for calculating difference in y needed for parallax
	private int mouseX = 250; // stores the mouse's x position
	private int mouseY = 250; // Mouse cursor's y position
    private int birdX = -10; // holds the birds position (initialized to the left of the screen out of sight)
	private int clickedX; // stores the clicked x location
	private int clickedY; // stores the clicked y location
	private boolean clicked = true; // used to check if a click was performed
	private boolean parallaxActive; // tells us if parallax is currently in use or inactive


	/** Constructor for the class
	 *@pre None.
	 *@post HW4 GUI (JFrame) has been created
	 */ 
	public HW4()
	{
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT); // set the size of the window
		DrawPanel dp = new DrawPanel();  // setup the drawpanel
		add(dp);// add the draw panel
		// Add a mouse listener
		addMouseListener(new MyMouseListener()); // add the mouse listener
   
		// Add a mouse motion listener
		addMouseMotionListener(new MyMouseMotionListener()); // add the motion listener
		
		setVisible(true);
	}
    
    /** private inner class that handles mouse events
	 *@pre None.
	 *@post when a mouse event occurs, it is handled appropriately
	 */ 
    private class MyMouseListener implements MouseListener
	{
		/** Method to handle when the mouse is pressed. Here to not throw exception.
		 *@pre None.
		 *@post None
		 *@param MouseEvent e
		 */ 
		public void mousePressed(MouseEvent e)
		{
		}
		
		/** Method to handle when the mouse is clicked, toggles a boolean value
		 *@pre None.
		 *@post When the mouse is clicked, the boolean value is toggled
		 *@param MouseEvent e
		 */ 
		public void mouseClicked(MouseEvent e)
		{		
			if(!clicked) // if the mouse hasn't been clicked
            {
				clicked = true;
            }
            else
            {
                clicked = false;
            }
		}
		
		/** Method to handle when the mouse is released. Here to not throw exception.
		 *@pre None.
		 *@post None
		 *@param MouseEvent e
		 */ 
		public void mouseReleased(MouseEvent e)
		{
		}
		
		/** Method to handle when the mouse is on the screen, enables the parallax
		 *@pre None.
		 *@post When the mouse is on the screen, set the parallax boolean true
		 *@param MouseEvent e
		 */ 
		public void mouseEntered(MouseEvent e)
		{
			parallaxActive = true; // activate the parallax
		}

		/** Method to handle when the mouse is off the screen, disables the parallax
		 *@pre None.
		 *@post When the mouse is off the screen, set the parallax boolean false
		 *@param MouseEvent e
		 */ 
		public void mouseExited(MouseEvent e)
		{
			parallaxActive = false; // deactivate parallax
		}
	}

	/** private inner class that handles mouse motion events
	 *@pre None.
	 *@post when a mouse motion event occurs, it is handled appropriately
	 */ 
	private class MyMouseMotionListener implements MouseMotionListener
	{
		/** Method to handle when the mouse is dragged by displaying a message letting the user know
		 *@pre None.
		 *@post When the mouse is dragged, display dialog saying that the mouse was dragged
		 *@param MouseEvent e
		 */ 
		public void mouseDragged(MouseEvent e)
		{
			JOptionPane.showMessageDialog(null, "Mouse Dragged");
		}

		/** Method to handle when the mouse is dragged by updating the current location of the mouse
		 *@pre: none
		 *@post When the mouse is moved, update the X and Y coordinates of the mouse
		 *@param MouseEvent e
		 */ 
		public void mouseMoved(MouseEvent e)
		{	    
			mouseX = e.getX(); // get the x value of the mouse
			mouseY = e.getY(); // get the y value of the mouse
		}
	}
	
	/** private inner class that handles drawing (graphics) and the timer action listener
	 *@pre None.
	 *@post graphics are updated when apropriate
	 */ 
	class DrawPanel extends JPanel implements ActionListener
	{
		private int delay = 1; // set the timer delay for this class
		protected Timer timer;

		/** Constructor for the class
		 *@pre None.
		 *@post timer object has been created and started
		 */ 
		public DrawPanel()
		{
			timer = new Timer(delay, this); //create the timer with delay
			timer.start();		// start the timer
		}

		/** Method to handle when the timer fires
		 *@pre: none
		 *@post repaints the graphics when the timer fires
		 *@param ActionEvent e
		 */ 
		public void actionPerformed(ActionEvent e)
		// will run when the timer fires
		{
			if(clicked && parallaxActive) // if the parallax is active and the mouse is clicked
			{
				repaint(); // repaint the image
			}
		}

		/** Method to paint the screen and implement the parallax
		 *@pre: none
		 *@post screen has been painted in relation to where the mouse is/last was on the screen
		 *@param Graphics g
		 */ 
		public void paintComponent(Graphics g)
		{
        calculateParallax(); // call to calculate parallax
        calculateBirdPosition(); // call to the birds location
        super.paintComponent(g); // call superclass's paintComponent
        buildSunAndSky(g); // call to create the backdrop
        buildBirds(g); // call to create the birds
        buildMountains(g); // call to create the mountains
		buildGrassAndTrees(g); // call to create the foreground
		}
    
	    /** Method to paint the birds
		 *@pre: none
		 *@post birds have been painted in relation to where the mouse is/last was on the screen
		 *@param Graphics g
		 */ 
        public void buildBirds(Graphics g)
        {
            g.setColor(Color.gray);
            g.fillPolygon(new int[] {(birdX + backgroundX - 60), (birdX + backgroundX - 50), (birdX + backgroundX - 60)}, new int[] {(170 + backgroundY - 30), (165 + backgroundY - 30), (160 + backgroundY - 30)}, 3);
            g.fillPolygon(new int[] {(birdX + backgroundX - 40), (birdX + backgroundX - 30), (birdX + backgroundX - 40)}, new int[] {(170 + backgroundY - 20), (165 + backgroundY - 20), (160 + backgroundY - 20)}, 3);
            g.fillPolygon(new int[] {(birdX + backgroundX - 20), (birdX + backgroundX - 10), (birdX + backgroundX - 20)}, new int[] {(170 + backgroundY - 10), (165 + backgroundY - 10), (160 + backgroundY - 10)}, 3);
            g.fillPolygon(new int[] {(birdX + backgroundX), (birdX + backgroundX + 10), (birdX + backgroundX)}, new int[] {(170 + backgroundY), (165 + backgroundY), (160 + backgroundY)}, 3);
        }
		
		/** Method to paint the Mountains
		 *@pre: none
		 *@post mountains have been painted in relation to where the mouse is/last was on the screen
		 *@param Graphics g
		 */ 
		public void buildMountains(Graphics g)
		{
            g.setColor(new Color(148,0,211)); // mountain purple
            g.fillPolygon(new int[] {(125 + backgroundX), (275 + backgroundX), (400 + backgroundX)}, new int[] {(600 + backgroundY), (150 + backgroundY), (600 + backgroundY)}, 3); // mountain 1
            g.setColor(new Color(199,234,70)); // mountain green
            g.fillPolygon(new int[] {(-100 + midgroundX), (150 + midgroundX), (400 + midgroundX)}, new int[] {(600 + midgroundY), (200 + midgroundY), (600 + midgroundY)}, 3); // mountain 2
            g.setColor(new Color(204,85,0)); // mountain orange
            g.fillPolygon(new int[] {(50 + midgroundX), (400 + midgroundX), (600 + midgroundX)}, new int[] {(600 + midgroundY), (200 + midgroundY), (600 + midgroundY)}, 3); // mountain 3
        }
		
		/** Method to paint the Grass and Trees
		 *@pre: none
		 *@post trees have been painted in relation to where the mouse is/last was on the screen
		 *@param Graphics g
		 */ 
		public void buildGrassAndTrees(Graphics g)
		{
            g.setColor(new Color(126, 200, 80)); // grass green
            g.fillRect(0, (400 + foregroundY), 600, 100); // grass/ground
		
            g.setColor(new Color(126, 44, 31)); // wood brown
            g.fillRect((450 + foregroundX), (360 + foregroundY), 20, 50); // tree trunk1
            g.setColor(Color.black); // wood brown
            g.drawRect((450 + foregroundX), (360 + foregroundY), 20, 50); // tree trunk1 outline

            g.setColor(new Color(103, 146, 103)); // leaf green
            g.fillOval((435 + foregroundX), (300 + foregroundY), 50, 75); // tree top1
            g.setColor(Color.black); // leaf green
            g.drawOval((435 + foregroundX), (300 + foregroundY), 50, 75); // tree top1 outline
		
            g.setColor(new Color(126, 44, 31)); // wood brown
            g.fillRect((250 + foregroundX), (360 + foregroundY), 20, 50); // tree trunk2
            g.setColor(Color.black); // wood brown
            g.drawRect((250 + foregroundX), (360 + foregroundY), 20, 50); // tree trunk2 outline

            g.setColor(new Color(103, 146, 103)); // leaf green
            g.fillOval((235 + foregroundX), (300 + foregroundY), 50, 75); // tree top2
            g.setColor(Color.black); // leaf green
            g.drawOval((235 + foregroundX), (300 + foregroundY), 50, 75); // tree top2 outline
		}
	
		/** Method to paint the sun and sky
		 *@pre: none
		 *@post sun has been painted in relation to where the mouse is/last was on the screen
		 *@param Graphics g
		 */ 
		public void buildSunAndSky(Graphics g)
		{
            g.setColor(new Color(149, 200, 216)); // sky blue
            g.fillRect(0, 0, 500, 500); // sky
        
            g.setColor(Color.yellow); // sun yellow
            g.fillOval((375 + sunX), (50 + sunY), 100, 100); // sun
            g.setColor(Color.black); // sun yellow
            g.drawOval((375 + sunX), (50 + sunY), 100, 100); // sun outline
		}
	
		/** Method to calculate the parallax movement for each section of the scene
		 *@pre: none
		 *@post variables that hold the parallax movement for each section has been calculated based on the mouse position
		 *@param Graphics g
		 */ 
		public void calculateParallax()
		{
            xDifference = mouseX - (WINDOW_WIDTH / 2);
            yDifference = mouseY - (WINDOW_HEIGHT / 2);
            foregroundX = xDifference / 5; // sets the foreground x
            midgroundX = xDifference / 12; // sets the midground x
            backgroundX = xDifference / 30; // sets the backdropground x
            foregroundY = yDifference / 5; // sets the foreground y
            midgroundY = yDifference / 12; // sets the midkground y
            backgroundY = yDifference / 30;// sets the background y
            sunX = xDifference / 60; // sets the sun's x
            sunY = yDifference / 60; // sets the sun's y
		}
        
		/** Method to calculate the position of the bird
		 *@pre: none
		 *@post birds position has been reset if off the screen
		 *@param Graphics g
		 */ 
        public int calculateBirdPosition()
        {
            if(birdX > 600) // if the birds are well off the screen
                birdX = -10; // reset the birds to 10 pixels left of the screen
            else
                birdX++; // increment the birds
            return birdX;
        }

}

	/** Main method
	 *@pre: none
	 *@post GUI object has been created
	 *@param String[] args
	 */ 
	public static void main(String[] args) 
	{
        new HW4();
    }
}