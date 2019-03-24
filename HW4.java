import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.Timer;

public class HW4
{
    // execute application
    public static void main( String args[] )
    {
        JFrame frame = new JFrame( "Forrest Parallax" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        DrawPanel panel = new DrawPanel();
        frame.add( panel );
        frame.setSize( 500, 500 ); // set frame size
        frame.setVisible( true ); // display frame
    } // end main
}


// class DrawPanel

class DrawPanel extends JPanel implements ActionListener
{
    private int delay = 10;
    protected Timer timer;

    private int x = 0;		// x position
    private int y = 0;		// y position
    private int radius = 15;	// ball radius

    private int dx = 2;		// increment amount (x coord)
    private int dy = 2;		// increment amount (y coord)

    public DrawPanel()
    {
        timer = new Timer(delay, this);
        timer.start();		// start the timer
    }

    public void actionPerformed(ActionEvent e)
    // will run when the timer fires
    {
        repaint();
    }

    // draw rectangles and arcs
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g ); // call superclass's paintComponent


        // check for boundaries
        //if (x < radius)			dx = Math.abs(dx);
        //if (x > getWidth() - radius)	dx = -Math.abs(dx);
        //if (y < radius)			dy = Math.abs(dy);
        //if (y > getHeight() - radius)	dy = -Math.abs(dy);

        // adjust ball position
        //x += dx;
        //y += dy;
        //g.fillOval(x - radius, y - radius, radius*2, radius*2);
        g.setColor(new Color(149, 200, 216)); // sky blue
        g.fillRect(0, 0, 500, 500); // sky

        g.setColor(new Color(126, 200, 80)); // grass green
        g.fillRect(0, 400, 500, 100); // grass/ground
    }

}