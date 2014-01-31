import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.List;
import java.util.ArrayList;

//Contains the actual algorithm for finding the paths
public class Solver {
	
	//Takes the 2d cross product of p1 and p2  p1x*p2y - p1y*p1x
	public static double cross(Point p1, Point p2) {
		return (p1.getX() * p2.getY() - (p1.getY() * p2.getX()));
		
	}
	
	//Given a starting point, boundaries, and a graphics2d object, paints
	//The fastest possible paths through the track
	public void generateFastestPaths(Point p, Boundaries bound, Graphics2D g) {
        long startTime = System.currentTimeMillis();
		
		int size = 1;
		int count = 0;
		
		//As long as there are no fastest paths found, continue to increment
		//The size of the path you are looking for
		while (count == 0) {
			//System.out.println("Loop " + size);
			g.setPaint(Color.CYAN);
			Path current = new Path(p);
			ArrayList<Point> possPoints = new ArrayList<Point>();
			Point last = p;
			boolean skip = false;
		
			//When current.size() == 0, it has run through all possible combinations
			while (current.size() > 0) {
				
				skip = false;		
				
				//Looks to see if the path has crossed the finish line already
				if (current.isComplete(bound)) {
					
					//Restricting the amount painting to reduce clutter; draws a complete path
					//with a random color
					
					if (count < 7000) {
						g.setPaint(new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)));
						current.draw(g);
					}
					
					count++;
					last = current.removeLast();
				}
						
				//Before the end, seeing if max velocity can reach finish
				else if (current.size() < size - 2){
					
					//Turn on if Aaron's Track
					//if(current.finishUnreachable(new Point(50,175), size)) {
					//	//System.out.println(current.toString());
					//	last = current.removeLast();
					//}	
					
					//Turn on if Rectangle
					//if(current.finishUnreachable(new Point(225,50), size)) {
					//	//System.out.println(current.toString());
					//	last = current.removeLast();
					//}	
					
					
					//if(current.finishUnreachable2(bound.getFinishLine(), size)) {
					//	//System.out.println(current.toString());
					//	last = current.removeLast();
					//}	
				}
				
				//Stops a path once it hits a certain size
				else if (current.size() > size) {
					last = current.removeLast();
				}
				
				//Checks if last line crosses any other line in the path
				else if (current.crossesItself()) {
					last = current.removeLast();
				}
				
				if (current.size() == 0) break;
				possPoints = current.generatePossiblePoints(bound);
				
				//The point that the path will move towards
				Point next = null;
				
				//There are possible points to move to
				if (!possPoints.isEmpty()) {	
					//Choosing the next point to move to, also making sure that the new point was not just used. If it moved to the ith point,
					//then it will move to the (i+1)th point. If there is no new branches to begin, then nothing happens
					for (int i = 0; i < possPoints.size(); i++) {
						
						//The point just traveled to is in the possible points to move towards
						if (last.equals(possPoints.get(i))) {
							
							//If there is another branch after the branch that was just traveled through
							if (i + 1 < possPoints.size()) {
								
								//If the path has not already used this point
									next = possPoints.get(i + 1);
									i = possPoints.size();
							}
							
							//If the branch that was just traveled through is the last branch
							else {
								last = current.removeLast();
								skip = true;
								i = possPoints.size();
							}
						}
					}
					
					//It hasn't traveled through any of the points yet
					if (next == null && skip == false) {
                        next = possPoints.get(0);
                    }
				}
				
				//There aren't possible points to move to
				else {
					last = current.removeLast();
				}
				
				//if there are points to move to
				if (next != null) {
					current.add(next);
					last = next;
				}
			}
			size++;
		}
		
		//Print results
		System.out.println("number of turns: " + (size - 1));
		System.out.println("count: " + count);
        System.out.println("Time: " + (System.currentTimeMillis() - startTime));
	}
}
