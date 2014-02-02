import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Defines a line (2 points)
 * 
 * @author Graham Wright
 * @date 2013
 */
public class Line {

	private Point p1;
	private Point p2;
	private Point vector;
	private double magnitude;
    
    public Line() {
    
    }
	
    /**
     * The Line constructor taking in a starting point and end point
     * Vector is (deltax, deltay)
	 * Magnitude is the magnitude of the vector
     * 
     * @param p1 The starting point
     * @param p2 The ending point
     */
	public Line(Point pt1, Point pt2) {
		p1 = pt1;
		p2 = pt2;	
		vector = new Point(pt2.getX() - pt1.getX(), pt2.getY() - pt1.getY());
		magnitude = Math.sqrt(Math.pow(vector.getX(), 2) + Math.pow(vector.getY(), 2));
	}
	
	/**
     * The Line constructor taking in all coordinates
     * 
     * @param x1 The starting x coordinate
     * @param y1 The starting y coordinate
     * @param x2 The ending x coordinate
     * @param y2 The ending y coordinate
     */
	public Line(int x1, int y1, int x2, int y2){
        this(new Point(x1,y1), new Point(x2,y2));
	}
	
	/**
     * Gets the starting point
     * 
     * @return The Starting point
     */
	public Point getStart() {
		return p1;
	}

	/**
     * Gest the line magnitude
     * 
     * @return The magnitude of the line
     */
	public double getMagnitude() {
		return magnitude;
	}
	
	/**
     * Returns the angle of the line to the positive horizontal
     * 
     * @return the angle
     */
	public double getAngle() {
		return p1.angleTowards(p2);
	}
	
	/**
     * Returns the end point
     * 
     * @return end point
     */
	public Point getEnd() {
		return p2;
	}
	
	/**
     * Returns the vector of the line
     * 
     * @return the vector
     */
	public Point getVector() {
		return vector;
	}
	
	//Checks for two lines being equal
	/**
     * Checks equality
     * SHOULD BE OBJECT TYPE TO OVERRIDE BUT OLD CODE
     * 
     * @param l The other line
     * @return result of equality
     */
	public boolean equals(Line l) {
		if (l.getStart().equals(getStart()) && l.getEnd().equals(getEnd())) return true;
		else return false;
	}
	
	/**
     * Decides if this line crosses line l
     * 
     * @param l The other line
     * @return Whether or not they cross
     */
	public boolean crosses(Line l) {
		
		Point p = p1;
		Point r = vector;
		Point q = l.getStart();
		Point s = l.getVector();
		
		//Lines are colinear
		if (Solver.cross(r, s) == 0 && Solver.cross(q.minus(p), r) == 0) {
			return true;
		}
		
		//lines are parallel
		else if (Solver.cross(r, s) == 0) {
			return false;
		}
		
		else {
			double t = (Solver.cross(q.minus(p), s)) / Solver.cross(r, s);
			double u = (Solver.cross(q.minus(p), r)) / Solver.cross(r, s);
			
			//Lines do intersect
			if (t >= 0 && t <= 1 && u >= 0 && u <= 1) {		
				return true;	
			}
		}
		
		return false;
	}
	
	/**
     * Checks for this line intersecting boundaries
	 * 0 - in track   1 - intersects boundary   2 - crosses finish line
     * 
     * @param bound The racetrack boundaries
     * @return The appropriate value
     */
	public int inTrack(Boundaries bound) {
		
		ArrayList<Line> arr = bound.getBoundaries();
		int check = 0;
		for(int i = 0; i < arr.size(); i++) {
			
			Point p = p1;
			Point r = vector;
			Point q = arr.get(i).getStart();
			Point s = arr.get(i).getVector();
			
			//Lines are colinear
			if (Solver.cross(r, s) == 0 && Solver.cross(q.minus(p), r) == 0) {
				if (check == 0) {
                    check = 1;
                }
			}
			
			//lines are parallel
			else if (Solver.cross(r, s) == 0) {
				
			}
			
			//find t and u values
			else {
				double t = (Solver.cross(q.minus(p), s)) / Solver.cross(r, s);
				double u = (Solver.cross(q.minus(p), r)) / Solver.cross(r, s);
				
				//Lines do intersect
				if (t >= 0 && t <= 1 && u >= 0 && u <= 1) {		
					if(check == 0) check = 1;
					
					//checking finish line intersection from pt to finish line line
					if (i == arr.size() - 1) {
						Point intersection = new Point((int) (p1.getX() + t * r.getX() + .5),(int) (p1.getY() + t * r.getY() + .5));
						Line finishLine = new Line(p1, intersection);
						
						if (!(finishLine.intersectsBoundaries(bound))) {
							return 2;						
						}
					}				
				}
				
				//Lines don't intersect
				else ;
			}	
		}	
		return check;
	}
	
	/**
     * Sees if line intersects boundaries NOT INCLUDING FINISH LINE
     * 
     * @param bound The racetrack boundaries
     * @return Whether or not this intersects with the boundaries
     * 		   not including the finish
     */
	public boolean intersectsBoundaries(Boundaries bound) {
		ArrayList<Line> arr = bound.getBoundaries();
		for(int i = 0; i < arr.size() - 1; i++) {
			
			Point p = p1;
			Point r = vector;
			Point q = arr.get(i).getStart();
			Point s = arr.get(i).getVector();
		
			//colinear
			if(Solver.cross(r, s) == 0 && Solver.cross(q.minus(p), r) == 0) {
				return true;
						
			}
			
			//parallel
			else if(Solver.cross(r, s) == 0) {
	
			}
			
			else {
				double t = (Solver.cross(q.minus(p), s)) / Solver.cross(r, s);
				double u = (Solver.cross(q.minus(p), r)) / Solver.cross(r, s);
				
				if (t >= 0.0 && t <= 1.0 && u >= 0.0 && u <= 1.0) {		
					return true;
				}
				else ;
			}
		}
		
		return false;
	}
	
	/**
     * Draws the line
     * 
     * @param g The graphics object
     */
	public void draw(Graphics2D g) {
		g.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
	}
	
	//Returns visual representation (x1, y1) to (x2, y2)
	@Override
	public String toString() {
		return p1.toString() + " to " + p2.toString();
	}
}
