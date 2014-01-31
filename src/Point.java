import java.util.ArrayList;

//Defines a point, x and y coordinates and velocities
public class Point {
	
	//x and y coordinates of point
	private int x;
	private int y;
	
	//x and y components of velocity
	private int vx;
	private int vy;
	
	public Point(int x1, int y2) {
		x = x1;
		y = y2;
	}
	
	public Point(int x1, int y1, int v1, int v2) {
		x = x1;
		y = y1;
		vx = v1;
		vy = v2;
	}
	
	//Returns x velocity of the point
	public int getVx() {
		return vx;
	}
	
	//Returns y velocity of the point
	public int getVy() {
		return vy;
	}
	
	//Returns x coordinate of Point
	public int getX() {
		return x;
	}
	
	//Returns y coordinate of Point
	public int getY() {
		return y;
	}
	
	//Returns the midpoint of this and p
	public Point midpoint(Point p) {
		return new Point((getX() + p.getX()) / 2, (getY() + p.getY()) /2 );
	}
	
	//Subtracts Point p from this Point
	public Point minus(Point p) {
		return new Point (x - p.getX(), y - p.getY());
	}
	
	//Checks to see if x and y coordinates of Points are equal
	public boolean equals(Point p) {
		if(p.getX() == getX() && p.getY() == getY()) return true;
		else return false;
	}
	
	
	//angle of line to point p from point this with respect to the 
	//horizontal axis
	public double angleTowards(Point p) {
		int deltaX = p.getX() - this.getX();
		int deltaY = this.getY() - p.getY();
		double angle = Math.toDegrees(Math.atan2(deltaY, deltaX));
		if (angle < 0) {
            angle = angle + 360;
        }
		return angle;
	}
	
	//Draws a line between this and point and if it doesnt cross any boundaries
	//or passes through the finish line first returns true
	public boolean inTrack(Point p, Boundaries bounds) {
		Line l = new Line(this, p);
		int inTrack = l.inTrack(bounds);
		if (inTrack == 0 || inTrack == 2) {
            return true;
        }
		else return false;
		
	}
	
	//Returns the list of the possible points that can be moved to from this point
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
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
