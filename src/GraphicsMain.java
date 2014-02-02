import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Where the solver is displayed
 * 
 * @author Graham Wright
 * @date 2013
 */
public class GraphicsMain extends JPanel {

	private int trackNumber;
	
	/**
	 * GraphicsMain constructor takes in the
	 * track number being solved
	 */
	public GraphicsMain(int track) {
		trackNumber = track;
	}
	
	/**
    * Deals with the drawing of the components for the GUI
    *
    * @param g The Graphics object used to draw things to the screen
    */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

        Boundaries bound = displayBoundaries(g2d, trackNumber);
        Point p = new Point(0, 0);
        g2d.setPaint(Color.BLUE);
        
        if (trackNumber == 1) {
        	//5x5
            p = new Point(50, 25);
        } else if (trackNumber == 2) {
        	//10x10
            p = new Point(125, 50);
        } else if (trackNumber == 3) {
        	 //Rectangular Loop
            p = new Point(225, 50);
        } else if (trackNumber == 4) {
        	//Circle with a house
            p = new Point(0, 150);
        } else if (trackNumber == 5) {
        	//LShape 4 Wide
            p = new Point(50, 0);
        } else if (trackNumber == 6) {
        	//Weird Pentagon
            p = new Point(75, 200);
        } else if (trackNumber == 7) {
        	//Aarons track
            p = new Point(50, 200);
        }
    	runTheSolver(p, bound, g2d);
	}
	
	private Boundaries displayBoundaries(Graphics2D g2d, int trackNumber) {
    	//Sets up the coordinate grid
        g2d.setPaint(Color.BLACK);
        for (int i = 0; i < 2000; i += 25) {
        	g2d.drawLine(i, 0, i, 2000);
        	g2d.drawLine(0, i, 2000, i);
        }
        
        //Draws boundaries on the grid
        g2d.setPaint(Color.RED);
        g2d.setStroke(new BasicStroke(3));
        Boundaries bound = new Boundaries(g2d);
        bound.createBoundaries(trackNumber);
        return bound;
    }
	
	private void runTheSolver(Point p, Boundaries bound, Graphics2D g2d) {
    	Solver solve = new Solver();
        //Sets the width of the paint brush
        g2d.setStroke(new BasicStroke(3));
        solve.generateFastestPaths(p, bound, g2d);
        g2d.setPaint(Color.BLACK);
        Double elipse = new Ellipse2D.Double(p.getX() - 5, p.getY() - 5, 10, 10);
        g2d.fill(elipse);
    }
}
