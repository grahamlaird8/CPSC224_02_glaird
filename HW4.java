/**
* @Authors: Finnian Allen and Graham Liard
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
	private int backgroundY; // background's Y position
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


	/**
	* This function is our constructor for the program.
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
    
    /**
      Private inner class to handle mouse events such as clicks.
   */
	
    private class MyMouseListener implements MouseListener
	{
		/**
        Meathod to check if the mouse was pressed.
	    */
		public void mousePressed(MouseEvent e)
		{
		}
		
		/**
        Meathod to check if the user has clicked the mouse.
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

		public void mouseReleased(MouseEvent e)
		{
		}

		public void mouseEntered(MouseEvent e)
		{
			parallaxActive = true; // activate the parallax
		}

		public void mouseExited(MouseEvent e)
		{
			parallaxActive = false; // deactivate parallax
		}
	}

   /**
      Private inner class to handle mouse motion events.
   */
   
	private class MyMouseMotionListener implements MouseMotionListener
	{
		public void mouseDragged(MouseEvent e)
		{
			JOptionPane.showMessageDialog(null, "Mouse Dragged");
		}

		public void mouseMoved(MouseEvent e)
		{	    
			mouseX = e.getX(); // get the x value of the mouse
			mouseY = e.getY(); // get the y value of the mouse
		}
	}
	
	/**
      Private inner class to handle actions performed.
    */
   
	class DrawPanel extends JPanel implements ActionListener
	{
		private int delay = 10; // set the timer delay for this class
		protected Timer timer;

		public DrawPanel()
		{
			timer = new Timer(delay, this); //create the timer with delay
			timer.start();		// start the timer
		}

		public void actionPerformed(ActionEvent e)
		// will run when the timer fires
		{
			if(clicked && parallaxActive) // if the parallax is active and the mouse is clicked
			{
				repaint(); // repaint the image
			}
		}

		/**
        Meathod to build the scene and implament parallax.
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
    
	    /**
        Meathod to build birds on the screen with apropriate parallax.
	    */
        public void buildBirds(Graphics g)
        {
            g.setColor(Color.gray);
            g.fillPolygon(new int[] {(birdX + backgroundX - 60), (birdX + backgroundX - 50), (birdX + backgroundX - 60)}, new int[] {(170 + backgroundY - 30), (165 + backgroundY - 30), (160 + backgroundY - 30)}, 3);
            g.fillPolygon(new int[] {(birdX + backgroundX - 40), (birdX + backgroundX - 30), (birdX + backgroundX - 40)}, new int[] {(170 + backgroundY - 20), (165 + backgroundY - 20), (160 + backgroundY - 20)}, 3);
            g.fillPolygon(new int[] {(birdX + backgroundX - 20), (birdX + backgroundX - 10), (birdX + backgroundX - 20)}, new int[] {(170 + backgroundY - 10), (165 + backgroundY - 10), (160 + backgroundY - 10)}, 3);
            g.fillPolygon(new int[] {(birdX + backgroundX), (birdX + backgroundX + 10), (birdX + backgroundX)}, new int[] {(170 + backgroundY), (165 + backgroundY), (160 + backgroundY)}, 3);
        }
		
		/**
        Meathod to build mountains on the screen with apropriate parallax.
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
		
		/**
        Meathod to build mountains on the screen with apropriate parallax.
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
	
		/**
        Meathod to build the sky and sun on the screen with apropriate parallax.
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
	
		/**
        Meathod to calculatye the parallax for wach possible x and y value and store them in private variables.
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
        
		/**
        Meathod to either incrament the bird's x position or decerease it based on if the birds have
		safely left the screens view or not.
	    */
        public int calculateBirdPosition()
        {
            if(birdX > 600) // if the birds are well off the screen
                birdX = -10; // reset the birds to 10 pixels left of the screen
            else
                birdX++; // incrament the birds
            return birdX;
        }

}

	/**
    Main Meathod.
	*/
	public static void main(String[] args) 
	{
        new HW4();
    }
}