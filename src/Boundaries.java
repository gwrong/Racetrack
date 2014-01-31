import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

//Defines the boundaries of the racetrack
public class Boundaries {

	private Graphics2D g;
	private ArrayList<Line> arr;
    
	public Boundaries(Graphics2D gr){
		g = gr;
		arr = new ArrayList<Line>();
	
	}
	
	//Returns the ArrayList of Lines that define the racetrack boundaries
	public ArrayList<Line> getBoundaries() {
		return arr;
	}
	
	//Returns the finish line 
	public Line getFinishLine() {
		return arr.get(arr.size() - 1);
	}
	
	//Returns the safety line that prevents finishing at the start of the track
	public Line getSafety() {
		return arr.get(arr.size() - 2);
	}
	
	//Defines the boundaries of the racetrack and draws them on the grid. 
	//Last line should be the finish line
	public void createBoundaries(){
		
		/*
		
		//A 5x5 box with a smaller 1x1 box inside
		Line l1 = new Line(0,0,125,0);
		Line l2 = new Line(125,0,125,125);
		Line l3 = new Line(125,125,0,125);
		Line l4 = new Line(0,0,0,125);
		Line l5 = new Line(50,50,75,50);
		Line l6 = new Line(75,50,75,75);
		Line l7 = new Line(75,75,50,75);
		Line l8 = new Line(50,50,50,75);
		//Boundary so that racecar does not go straight across finish line
		Line l9 = new Line(60, 0, 60, 50);
		//Finish Line
		Line l10 = new Line(63, 0, 63, 50);
		arr.add(l1);
		arr.add(l2);
		arr.add(l3);
		arr.add(l4);
		arr.add(l5);
		arr.add(l6);
		arr.add(l7);
		arr.add(l8);
		arr.add(l9);
		arr.add(l10);
		for(int i = 0; i < arr.size(); i++) {
			arr.get(i).draw(g);
		}
		
		*/
		
		
		
		/*
		
		//A 10x10 box with a smaller 2x2 box inside
		Line l1 = new Line(0,0,250,0);
		Line l2 = new Line(250,0,250,250);
		Line l3 = new Line(250,250,0,250);
		Line l4 = new Line(0,0,0,250);
		Line l5 = new Line(100,100,150,100);
		Line l6 = new Line(150,100,150,150);
		Line l7 = new Line(150,150,100,150);
		Line l8 = new Line(100,100,100,150);
		//Boundary so that racecar does not go straight across finish line
		Line l9 = new Line(126, 0, 126, 100);
		//Finish Line
		Line l10 = new Line(128, 0, 128, 100);
		arr.add(l1);
		arr.add(l2);
		arr.add(l3);
		arr.add(l4);
		arr.add(l5);
		arr.add(l6);
		arr.add(l7);
		arr.add(l8);
		arr.add(l9);
		arr.add(l10);
		for(int i = 0; i < arr.size(); i++) {
			arr.get(i).draw(g);
		}
		
		*/
		

		///*
		
		//Rectangular Loop, 9x1 rectangle in the middle
        //With finishUnreachable
        //15 turns, 6971 count
        //Time with finishUnreachable: 290338
        //Without
        //15 turns, 17200 count
        //Time: 1352761
		Line l1 = new Line(0,0,425,0);
		Line l2 = new Line(425,0,425,225);
		Line l3 = new Line(425,225,0,225);
		Line l4 = new Line(0,0,0,225);
		Line l5 = new Line(100,100,325,100);
		Line l6 = new Line(325,100,325,125);
		Line l7 = new Line(325,125,100,125);
		Line l8 = new Line(100,125,100,100);
		//Boundary so that racecar does not go straight across finish line
		Line l9 = new Line(226, 0, 226, 100);
		//Finish Line
		Line l10 = new Line(228, 0, 228, 100);
		arr.add(l1);
		arr.add(l2);
		arr.add(l3);
		arr.add(l4);
		arr.add(l5);
		arr.add(l6);
		arr.add(l7);
		arr.add(l8);
		arr.add(l9);
		arr.add(l10);
		for(int i = 0; i < arr.size(); i++) {
			arr.get(i).draw(g);
		}
		
		//*/
	
		
		/*
		
		//L Shaped Track 4 wide
		Line l1 = new Line(0,-1,100,-1);
		Line l2 = new Line(100,0,100,300);
		Line l3 = new Line(100,300,400,300);
		Line l4 = new Line(400,400,0,400);
		Line l5 = new Line(0,400,0,0);
		//Finish Line
		Line l6 = new Line(400,300,400,400);
	
		
		arr.add(l1);
		arr.add(l2);
		arr.add(l3);
		arr.add(l4);
		arr.add(l5);
		arr.add(l6);
		
		
		for(int i = 0; i < arr.size(); i++) {
			arr.get(i).draw(g);
		}
		
		
		*/
		
		
		/*
		
		//Weird Shaped pentagon
		Line l1 = new Line(0,50,275,0);
		Line l2 = new Line(275,0,250,150);
		Line l3 = new Line(250,150,150,300);
		Line l4 = new Line(150,300,0,300);
		Line l5 = new Line(0,300,0,50);
		Line l6 = new Line(75,100,150,75);
		Line l7 = new Line(100,175,175,125);
		Line l8 = new Line(175,125,200,100);
		Line l9 = new Line(100,175,100,225);
		Line l10 = new Line(100,225,50,225);
		//safety line
		Line l11 = new Line(0,174,102,174);
		//finish line
		Line l12 = new Line(0,172,100,172);

	
		
		arr.add(l1);
		arr.add(l2);
		arr.add(l3);
		arr.add(l4);
		arr.add(l5);
		arr.add(l6);
		arr.add(l7);
		arr.add(l8);
		arr.add(l9);
		arr.add(l10);
		arr.add(l11);
		arr.add(l12);
		
		
		for(int i = 0; i < arr.size(); i++) {
			arr.get(i).draw(g);
		}
		
		*/
		
		
		
		/*
		
		//Circle with house
		Line l1 = new Line(0,125,150,25);
		Line l2 = new Line(150,25,275,50);
		Line l3 = new Line(275,50,300,125);
		Line l4 = new Line(300,125,350,50);
		Line l5 = new Line(350,50,400,0);
		Line l6 = new Line(400,0,450,75);
		
		Line l7 = new Line(450,175,300,175);
		Line l8 = new Line(300,175,275,250);
		Line l9 = new Line(275,250,150,275);
		Line l10 = new Line(150,275,0,175);
		Line l11 = new Line(-1,175,-1,125);
		Line l12 = new Line(75,150,175,100);
		Line l13 = new Line(175,100,200,150);
		Line l14 = new Line(200,150,175,200);
		Line l15 = new Line(175,200,75,150);
		Line l16 = new Line(375,75,375,125);
		Line l17 = new Line(375,150,375,175);
		//Finish line
		Line l18 = new Line(450,75,450,175);
		
		
		
		arr.add(l1);
		arr.add(l2);
		arr.add(l3);
		arr.add(l4);
		arr.add(l5);
		arr.add(l6);
		arr.add(l7);
		arr.add(l8);
		arr.add(l9);
		arr.add(l10);
		arr.add(l11);
		arr.add(l12);
		arr.add(l13);
		arr.add(l14);
		arr.add(l15);
		arr.add(l16);
		arr.add(l17);
		arr.add(l18);
		
		for(int i = 0; i < arr.size(); i++) {
			arr.get(i).draw(g);
		}
		
		*/
		
		
		
		
		/*
		
		//Aaron's Track
		Line l1 = new Line(0,0,500,0);
		Line l2 = new Line(500,0,500,450);
		Line l3 = new Line(500,450,250,450);
		Line l4 = new Line(250,450,250,325);
		Line l5 = new Line(250,325,100,325);
		Line l6 = new Line(100,325,0,225);
		Line l7 = new Line(0,225,0,0);
		Line l8 = new Line(100,75,425,75);
		Line l9 = new Line(425,75,425,325);
		Line l10 = new Line(425,325,375,325);
		Line l11 = new Line(375,325,250,200);
		Line l12 = new Line(250,200,100,200);
		Line l13 = new Line(100,200,100,75);
		Line l14 = new Line(0,188,100,188);
		Line l15 = new Line(0,187,100,187);

		arr.add(l1);
		arr.add(l2);
		arr.add(l3);
		arr.add(l4);
		arr.add(l5);
		arr.add(l6);
		arr.add(l7);
		arr.add(l8);
		arr.add(l9);
		arr.add(l10);
		arr.add(l11);
		arr.add(l12);
		arr.add(l13);
		arr.add(l14);
		arr.add(l15);

		for(int i = 0; i < arr.size(); i++) {
			arr.get(i).draw(g);
		}
		
		*/
		
	}
	
}
