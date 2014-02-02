import java.util.ArrayList;

/**
 * Defines a point, x and y coordinates and velocities
 * 
 * @author Graham Wright
 * @date 2013
 */
public class Point {
	
	//x and y coordinates of point
	private int x;
	private int y;
	
	//x and y components of velocity
	private int vx;
	private int vy;
	
	/**
	 * Point constructor with x and y coords
	 * 
	 * @param x1 The x coordinate
	 * @param y1 The y coordinate
	 */
	public Point(int x1, int y2) {
		x = x1;
		y = y2;
	}
	
	/**
	 * Point constructor with x and y coords and velocity
	 * 
	 * @param x1 The x coordinate
	 * @param y1 The y coordinate
	 * @param v1 The velocity x component
	 * @param v1 The velocity y component
	 */
	public Point(int x1, int y1, int v1, int v2) {
		x = x1;
		y = y1;
		vx = v1;
		vy = v2;
	}
	
	/**
	 * Gets the x component of velocity
	 * 
	 * @return The x component of velocity
	 */
	public int getVx() {
		return vx;
	}
	
	/**
	 * Gets the y component of velocity
	 * 
	 * @return The y component of velocity
	 */
	public int getVy() {
		return vy;
	}
	
	/**
	 * Gets the x coordinate
	 * 
	 * @return The x coordinate
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gets theyx coordinate
	 * 
	 * @return The y coordinate
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Gets the midpoint of this and point p
	 * 
	 * @param p Other point
	 * @return Point midpoint
	 */
	public Point midpoint(Point p) {
		return new Point((getX() + p.getX()) / 2, (getY() + p.getY()) /2 );
	}
	
	/**
	 * Subtracts Point p from this Point
	 * 
	 * @param p The point to be subtracted
	 * @return Point The subtraction result
	 */
	public Point minus(Point p) {
		return new Point (x - p.getX(), y - p.getY());
	}
	
	/**
	 * Checks to see if x and y coordinates of Points are equal
	 * SHOULD BE TYPE OBJECT PARAMETER BUT THIS IS OLD CODE 
	 * 
	 * @param p The other point
	 * @return result of being equal
	 */
	public boolean equals(Point p) {
		if(p.getX() == getX() && p.getY() == getY()) return true;
		else return false;
	}
	
	/**
	 * angle of line to point p from point this with respect to the 
	 * horizontal axis
	 * 
	 * @param p The other point
	 * @return double The angle
	 */
	public double angleTowards(Point p) {
		int deltaX = p.getX() - this.getX();
		int deltaY = this.getY() - p.getY();
		double angle = Math.toDegrees(Math.atan2(deltaY, deltaX));
		if (angle < 0) {
            angle = angle + 360;
        }
		return angle;
	}

	/**
	 * Draws a line between this and point and if it doesn't cross any boundaries
	 * or passes through the finish line first returns true
	 * 
	 * @param p The other point
	 * @param bounds The bounds of the racetrack
	 * @return Whether or not it is valid
	 */
	public boolean inTrack(Point p, Boundaries bounds) {
		Line l = new Line(this, p);
		int inTrack = l.inTrack(bounds);
		if (inTrack == 0 || inTrack == 2) {
            return true;
        }
		else return false;
		
	}
	
	/**
	 * Returns the list of the possible points that can be moved to from this point
	 * 
	 * @param bounds The bounds of the racetrack
	 * @param path The path
	 * @return Possible points to move to
	 */
	public ArrayList<Point> generatePossiblePoints(Boundaries bounds, Path path) {
		
		ArrayList<Point> points = new ArrayList<Point>();
		
		Point p1 = new Point(x + vx + 25, y + vy, vx + 25, vy);
		if (this.inTrack(p1, bounds)) points.add(p1);
		Point p2 = new Point(x + vx + 25, y + vy - 25, vx + 25, vy - 25);
		if (this.inTrack(p2, bounds)) points.add(p2);
		Point p3 = new Point(x + vx, y + vy - 25, vx, vy - 25);
		if (this.inTrack(p3, bounds)) points.add(p3);
		Point p4 = new Point(x + vx - 25, y + vy - 25, vx - 25, vy - 25);
		if (this.inTrack(p4, bounds)) points.add(p4);
		Point p5 = new Point(x + vx - 25, y + vy, vx - 25, vy);
		if (this.inTrack(p5, bounds)) points.add(p5);
		Point p6 = new Point(x + vx - 25, y + vy + 25, vx - 25, vy + 25);
		if (this.inTrack(p6, bounds)) points.add(p6);
		Point p7 = new Point(x + vx, y + vy + 25, vx, vy + 25);
		if (this.inTrack(p7, bounds)) points.add(p7);
		Point p8 = new Point(x + vx + 25, y + vy + 25, vx + 25, vy + 25);
		if (this.inTrack(p8, bounds)) points.add(p8);
		if (!(vx == 0 && vy == 0)) {
			Point p9 = new Point(x + vx, y + vy, vx, vy);
			if(this.inTrack(p9, bounds)) points.add(p9);
		}
		
		for (int i = 0; i < points.size(); i++) {
			//System.out.println(points.get(i).toString());
			if (path.has(points.get(i))) {
				points.remove(i);
				i--;
			}
		}
		return points;
	}
	
	//Visual representation of a Point (x, y)
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
