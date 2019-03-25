package hw4;

import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.*;


public class HW4 extends JFrame{

	private int WINDOW_WIDTH = 517;
	private int WINDOW_HEIGHT = 500;
	private int foregroundX; // Mouse cursor's X position
	private int midgroundX; // Mouse cursor's Y position
	private int backgroundX;
	private int foregroundY; // Mouse cursor's X position
	private int midgroundY; // Mouse cursor's Y position
	private int backgroundY;
    private int sunX;
	private int sunY;
    private int xDifference;
    private int yDifference;
	private int mouseX = 250;
	private int mouseY = 250; // Mouse cursor's X position
    private int birdX = 20;
	private int clickedX;
	private int clickedY;
	private boolean clicked = true;
	private boolean parallaxActive;



	public HW4()
	{
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		DrawPanel dp = new DrawPanel(); 
		add(dp);
		// Add a mouse listener
		addMouseListener(new MyMouseListener());
   
		// Add a mouse motion listener
		addMouseMotionListener(new MyMouseMotionListener());
		
		setVisible(true);
	}
    
    
    private class MyMouseListener implements MouseListener
	{
		public void mousePressed(MouseEvent e)
		{
		}

		public void mouseClicked(MouseEvent e)
		{		
			if(!clicked)
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
			JOptionPane.showMessageDialog(null, "Mouse Dragged");
		}

		public void mouseMoved(MouseEvent e)
		{	    
			mouseX = e.getX();
			mouseY = e.getY();
		}
	}
   
	class DrawPanel extends JPanel implements ActionListener
{
    private int delay = 10;
    protected Timer timer;

    public DrawPanel()
    {
        timer = new Timer(delay, this);
        timer.start();		// start the timer
    }

    public void actionPerformed(ActionEvent e)
    // will run when the timer fires
    {
        if(clicked && parallaxActive)
		{
			repaint();
		}
    }

    // draw rectangles and arcs
    public void paintComponent(Graphics g)
    {
        calculateParallax();
        calculateBirdPosition();
        super.paintComponent(g); // call superclass's paintComponent
        buildSunAndSky(g);
        buildBirds(g);
        buildMountains(g);
		buildGrassAndTrees(g);
        
    }
    
        public void buildBirds(Graphics g)
        {
            g.setColor(Color.gray);
            g.fillPolygon(new int[] {(birdX + backgroundX - 60), (birdX + backgroundX - 50), (birdX + backgroundX - 60)}, new int[] {(170 + backgroundY - 30), (165 + backgroundY - 30), (160 + backgroundY - 30)}, 3);
            g.fillPolygon(new int[] {(birdX + backgroundX - 40), (birdX + backgroundX - 30), (birdX + backgroundX - 40)}, new int[] {(170 + backgroundY - 20), (165 + backgroundY - 20), (160 + backgroundY - 20)}, 3);
            g.fillPolygon(new int[] {(birdX + backgroundX - 20), (birdX + backgroundX - 10), (birdX + backgroundX - 20)}, new int[] {(170 + backgroundY - 10), (165 + backgroundY - 10), (160 + backgroundY - 10)}, 3);
            g.fillPolygon(new int[] {(birdX + backgroundX), (birdX + backgroundX + 10), (birdX + backgroundX)}, new int[] {(170 + backgroundY), (165 + backgroundY), (160 + backgroundY)}, 3);
        }
	
	public void buildMountains(Graphics g)
	{
            g.setColor(new Color(148,0,211)); // mountain purple
            g.fillPolygon(new int[] {(125 + backgroundX), (275 + backgroundX), (400 + backgroundX)}, new int[] {(600 + backgroundY), (150 + backgroundY), (600 + backgroundY)}, 3); // mountain 1
            g.setColor(new Color(199,234,70)); // mountain green
            g.fillPolygon(new int[] {(-100 + midgroundX), (150 + midgroundX), (400 + midgroundX)}, new int[] {(600 + midgroundY), (200 + midgroundY), (600 + midgroundY)}, 3); // mountain 2
            g.setColor(new Color(204,85,0)); // mountain orange
            g.fillPolygon(new int[] {(50 + midgroundX), (400 + midgroundX), (600 + midgroundX)}, new int[] {(600 + midgroundY), (200 + midgroundY), (600 + midgroundY)}, 3); // mountain 3
        }
	
	public void buildGrassAndTrees(Graphics g)
	{
            g.setColor(new Color(126, 200, 80)); // grass green
            g.fillRect(0, (400 + foregroundY), 600, 100); // grass/ground
		
            g.setColor(new Color(126, 44, 31)); // wood brown
            g.fillRect((450 + foregroundX), (360 + foregroundY), 20, 50); // tree trunk
            g.setColor(Color.black); // wood brown
            g.drawRect((450 + foregroundX), (360 + foregroundY), 20, 50); // tree trunk

            g.setColor(new Color(103, 146, 103)); // leaf green
            g.fillOval((435 + foregroundX), (300 + foregroundY), 50, 75); // tree top
            g.setColor(Color.black); // leaf green
            g.drawOval((435 + foregroundX), (300 + foregroundY), 50, 75); // tree top
		
            g.setColor(new Color(126, 44, 31)); // wood brown
            g.fillRect((250 + foregroundX), (360 + foregroundY), 20, 50); // tree trunk
            g.setColor(Color.black); // wood brown
            g.drawRect((250 + foregroundX), (360 + foregroundY), 20, 50); // tree trunk

            g.setColor(new Color(103, 146, 103)); // leaf green
            g.fillOval((235 + foregroundX), (300 + foregroundY), 50, 75); // tree top
            g.setColor(Color.black); // leaf green
            g.drawOval((235 + foregroundX), (300 + foregroundY), 50, 75); // tree top
	}
	
	public void buildSunAndSky(Graphics g)
	{
            g.setColor(new Color(149, 200, 216)); // sky blue
            g.fillRect(0, 0, 500, 500); // sky
        
            g.setColor(Color.yellow); // sun yellow
            g.fillOval((375 + sunX), (50 + sunY), 100, 100); // sun
            g.setColor(Color.black); // sun yellow
            g.drawOval((375 + sunX), (50 + sunY), 100, 100); // sun
	}
	
	public void calculateParallax()
	{
            xDifference = mouseX - (WINDOW_WIDTH / 2);
            yDifference = mouseY - (WINDOW_HEIGHT / 2);
            foregroundX = xDifference / 5;
            midgroundX = xDifference / 12;
            backgroundX = xDifference / 30;
            foregroundY = yDifference / 5;
            midgroundY = yDifference / 12;
            backgroundY = yDifference / 30;
            sunX = xDifference / 60;
            sunY = yDifference / 60;
	}
        
        public int calculateBirdPosition()
        {
            if(birdX > 600)
                birdX = -10;
            else
                birdX++;
            return birdX;
        }

}


public static void main(String[] args) {
        new HW4();
    }
}