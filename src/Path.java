import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Defines a path which is a list of points
 * 
 * @author Graham Wright
 * @date 2013
 */
public class Path {

	private ArrayList<Point> arr;
	
	/**
	 * Path constructor
	 */
	public Path() {
		arr = new ArrayList<Point>();
	}
	
	/**
	 * Path constructor takes in starting point
	 * 
	 * @param p The starting point
	 */
	public Path(Point p) {
		arr = new ArrayList<Point>();
		arr.add(p);
	}
	
	/**
	 * Path constructor takes in a list of points
	 * 
	 * @param p The list of points
	 */
	public Path(ArrayList<Point> p) {
		arr = p;
	}
	
	/**
	 * Add a point to the path
	 * 
	 * @param pt The point to be added
	 */
	public void add(Point pt) {
		arr.add(pt);
	}
	
	/**
	 * Generates possible points that the last point in the path can move to
	 * given a boundaries object
	 * 
	 * @param bound The boundaries of the racetrack
	 * @return The possible points that can be moved to
	 */
	public ArrayList<Point> generatePossiblePoints(Boundaries bound) {
		return arr.get(arr.size() - 1).generatePossiblePoints(bound, this);
	}
	
	/**
	 * Returns the list of points in the path
	 * 
	 * @return path list
	 */
	public ArrayList<Point> getPoints() {
		return arr;
	}
	
	/**
	 * Returns the specified point in the path
	 * 
	 * @param i The index of the point
	 * @return the Point at index i
	 */
	public Point get(int i) {
		if (i >= arr.size() || i < 0) {
            return null;
        }
		return arr.get(i);
	}
	
	//Experimental checking max velocities reaching finish
	public boolean finishUnreachable2(Line finishLine, int checkingSize) {
		if (finishUnreachable(finishLine.getStart(), checkingSize) && finishUnreachable(finishLine.getEnd(), checkingSize) && finishUnreachable(finishLine.getStart().midpoint(finishLine.getEnd()), checkingSize)) {
			return true;
		}
		return false;
	}
	
	//Seeing if with max velocities the path can reach the finish
	//if true, the path cannot reach the finish in the remaining turns
	public boolean finishUnreachable(Point start, int checkingSize) {
		
		Point last = arr.get(arr.size() - 1);
		int Vx = Math.abs(last.getVx()) + 1;
		int Vy = Math.abs(last.getVy()) + 1;
		int turnsLeft = checkingSize - arr.size();
		
		int maxVx = Math.abs(last.getVx());
		int maxVy = Math.abs(last.getVy());
		
		Line test = new Line(last, start);
		double magnitude = test.getMagnitude();
		
		for (int i = 0; i < turnsLeft; i++) {
			maxVx = maxVx + Vx;
			maxVy = maxVy + Vy;
			Vx++;
			Vy++;
		}
		
		double maxMagnitude = Math.sqrt(Math.pow(maxVx, 2) + Math.pow(maxVy, 2));
		if(maxMagnitude < magnitude) return true;
		
		return false;
	}
	
	/**
	 * Sees if the last line intersects the rest of the path
	 * 
	 * @return whether or not the path crosses itself
	 */
	public boolean crossesItself() {
		if(arr.size() < 4) return false;
		Line temp = new Line();
		Line last = new Line(arr.get(arr.size() - 2), arr.get(arr.size() - 1));
		for (int i = 0; i < arr.size() - 3; i++) {
			temp = new Line(arr.get(i), arr.get(i + 1));
			if (last.crosses(temp)) {
                return true;
            }
		}
		return false;
	}
	
	/**
	 * Sees if the path has the point p in it
	 * 
	 * @param p The point being checked
	 * @return Whether or not the point is in it
	 */
	public boolean has(Point p) {
		if (this.size() > 1) {
			for (int i = 2; i < arr.size(); i++) {
				if (arr.get(i).equals(p)) {
                    return true;
                }
			}
		}
		return false;
	}
	
	/**
	 * Sees if the path has the point p in it
	 * 
	 * @param p The point being checked
	 * @return Whether or not the point is in it
	 */
	public boolean contains(Point p) {
		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i).equals(p)) {
                return true;
            }
		}
		return false;
	}
	
	/**
	 * Gets the size of the path
	 * 
	 * @return size
	 */
	public int size() {
		return arr.size();
	}
	
	/**
	 * Gets the length of the path
	 * 
	 * @return the length
	 */
	public int getLength() {
		return arr.size();
	}
	
	/**
	 * Draws each line in the path
	 * 
	 * @param g The Graphics object
	 */
	public void draw(Graphics2D g) {
		Line l = new Line();
		for (int i = 0; i < arr.size() - 1; i++) {
			l = new Line(arr.get(i), arr.get(i+1));
			l.draw(g);
		}
	}
	
	/**
	 * Removes the last point in the path and returns the point
	 * 
	 * @return the Last point in the path
	 */
	public Point removeLast() {
		Point p = arr.get(arr.size() - 1);
		arr.remove(arr.size() - 1);
		return p;
		
	}
	
	/**
	 * Decides if the path is complete (if the last 2 points form a line that
	 * crosses the finish line before any boundaries)
	 * 
	 * @param bound The boundaries of the racetrack
	 * @return whether or not the path is complete
	 */
	public boolean isComplete(Boundaries bound) {
		if (arr.size() < 2) {
            return false;
        }
		Line l = new Line(arr.get(arr.size() - 2), arr.get(arr.size() - 1));
		if (l.inTrack(bound) == 2) {
            return true;
        }
		return false;	
	}
	
	
	//Visual representation of the path [ (x1, y1) (x2, y2) ... ]
	@Override
	public String toString() {
		String s = "\n\n[";
		for (int i = 0; i < arr.size(); i++) {
			s = s + " " + ((Point) arr.get(i)).toString();		
		}
		s = s + " ]";
		return s;
	}
}
