import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Shape;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicsMain extends JPanel {

	//Visual paint method
    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        
        //Sets up the coordinate grid
        g2d.setPaint(Color.BLACK);
        for (int i = 0; i < 2000; i += 25) {
        	g2d.drawLine(i, 0, i, 2000);
        	g2d.drawLine(0, i, 2000, i);
        }
        
        //Draws boundaries on the grid
        g2d.setPaint(Color.RED);
        g2d.setStroke(new BasicStroke(10));
        Boundaries bound = new Boundaries(g2d);
        bound.createBoundaries();
        
        
        g2d.setPaint(Color.BLUE);
        
        //5x5
        //Point p = new Point(50, 25);
        
        //10x10
        //Point p = new Point(125, 50);
        
        //Rectangular Loop
        Point p = new Point(225, 50);
        
        //LShape 4 Wide
        //Point p = new Point(50, 0);
        
        //Weird Pentagon
        //Point p = new Point(75, 200);
        
        //Circle with a house
        //Point p = new Point(0, 150);
        
        //Aarons track
        //Point p = new Point(50, 200);
        
        Solver solve = new Solver();
        //Sets the width of the paint brush
        g2d.setStroke(new BasicStroke(3));
        solve.generateFastestPaths(p, bound, g2d);
        g2d.setPaint(Color.BLACK);
        Double elipse = new Ellipse2D.Double(p.getX() - 5, p.getY() - 5, 10, 10);
        g2d.fill(elipse);
    }

    //Main method runs the program
    public static void main(String[] args) {
    
        JFrame frame = new JFrame("Racetrack Solver");
        frame.add(new GraphicsMain());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setBackground(Color.WHITE); 
    }
}