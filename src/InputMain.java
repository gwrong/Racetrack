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
 * InputMain contains the selection elements for which track
 * to solve
 * @author Graham Wright
 * @date 2013
 */
public class InputMain {

	private JPanel leftColumn;
	private JPanel middleColumn;
	private JPanel rightColumn;
	private JPanel buttonColumn;
	private Image image1;
	private Image image2;
	private Image image3;
	private Image image4;
	private Image image5;
	private Image image6;
	private Image image7;
	private JFrame frame;
	private int trackNumber;
	
	/**
	 * The InputMain constructor stores the JFrame originally displayed
	 * @param frame The first JFrame with track selection
	 */
	public InputMain(JFrame frame) {
		
		trackNumber = 1;
		this.frame = frame;
		
		leftColumn = new JPanel() {
			/**
		    * Deals with the drawing of the components for the GUI
		    *
		    * @param g The Graphics object used to draw things to the screen
		    */
		    public void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        g.drawImage(image1, 0, 25, null);
		        g.drawImage(image2, 0, 178, null);
		        g.drawImage(image3, 0, 455, null);
		    }
		};
		leftColumn.setPreferredSize(new Dimension(500, 800));
		
		middleColumn = new JPanel() {
			/**
		    * Deals with the drawing of the components for the GUI
		    *
		    * @param g The Graphics object used to draw things to the screen
		    */
		    public void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        g.drawImage(image7, 0, 25, null);
		        g.drawImage(image4, 0, 510, null);
		    }
		};
		middleColumn.setPreferredSize(new Dimension(550, 800));
		
		rightColumn = new JPanel() {
			/**
		    * Deals with the drawing of the components for the GUI
		    *
		    * @param g The Graphics object used to draw things to the screen
		    */
		    public void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        Graphics2D g2 = (Graphics2D) g;
		        g2.setStroke(new BasicStroke(7));
		        g.drawImage(image5, 0, 25, null);
		        g.drawImage(image6, 0, 458, null);
		    }
		};
		rightColumn.setPreferredSize(new Dimension(500, 800));
		
		buttonColumn = new JPanel();
		
		leftColumn.setLayout(new BoxLayout(leftColumn, BoxLayout.Y_AXIS));
		middleColumn.setLayout(new BoxLayout(middleColumn, BoxLayout.Y_AXIS));
		rightColumn.setLayout(new BoxLayout(rightColumn, BoxLayout.Y_AXIS));
		
		try {                
		      image1 = ImageIO.read(new File("resources/5x5.png"));
		      image2 = ImageIO.read(new File("resources/10x10.png"));
		      image3 = ImageIO.read(new File("resources/rectangle.png"));
		      image4 = ImageIO.read(new File("resources/house.png"));
		      image5 = ImageIO.read(new File("resources/l.png"));
		      image6 = ImageIO.read(new File("resources/pentagon.png"));
		      image7 = ImageIO.read(new File("resources/aaron.png"));
		} catch (IOException ex) {
			           System.out.println("Uh oh");
		}
		
		JButton button = new JButton("Start Solver");
        button.addActionListener(new ButtonListener());
        buttonColumn.add(button);
        
        ButtonGroup trackRadios = new ButtonGroup();
		
        JRadioButton track1 = new JRadioButton("5x5 Track ( < 1 second)");
        track1.addActionListener(new RadioListener(1));
        track1.setSelected(true);

        JRadioButton track2 = new JRadioButton("10x10 Track ( < 1 second)");
        track2.addActionListener(new RadioListener(2));
        
        JRadioButton track3 = new JRadioButton("Rectangle Track (~22 minutes)");
        track3.addActionListener(new RadioListener(3));
        
        JRadioButton track4 = new JRadioButton("House Track ( < 1 second)");
        track4.addActionListener(new RadioListener(4));
        
        JRadioButton track5 = new JRadioButton("L Track ( < 1 second)");
        track5.addActionListener(new RadioListener(5));
        
        JRadioButton track6 = new JRadioButton("Pentagon Track (~10 seconds)");
        track6.addActionListener(new RadioListener(6));
        
        JRadioButton track7 = new JRadioButton("Aaron's Track");
        track7.addActionListener(new RadioListener(7));

        trackRadios.add(track1);
        trackRadios.add(track2);
        trackRadios.add(track3);
        trackRadios.add(track4);
        trackRadios.add(track5);
        trackRadios.add(track6);
        trackRadios.add(track7);

        leftColumn.add(track1);
        leftColumn.add(Box.createRigidArea(new Dimension(0, 130)));
        leftColumn.add(track2);
        leftColumn.add(Box.createRigidArea(new Dimension(0, 251)));
        leftColumn.add(track3);
        middleColumn.add(track7);
        rightColumn.add(track5);
        rightColumn.add(Box.createRigidArea(new Dimension(0, 405)));
        rightColumn.add(track6);
        middleColumn.add(Box.createRigidArea(new Dimension(0, 457)));
        middleColumn.add(track4);
	}

	/**
	 * Adds all JPanels to the JFrame
	 */
    public void addToFrame() {
    	frame.add(leftColumn, BorderLayout.WEST);
    	frame.add(middleColumn, BorderLayout.CENTER);
    	frame.add(rightColumn, BorderLayout.EAST);
    	frame.add(buttonColumn, BorderLayout.SOUTH);
    }

    /**
	 * The main method
	 * @param args The command line arguments
	 */
    public static void main(String[] args) {
    
        JFrame frame = new JFrame("Racetrack Solver");
        InputMain inputMain = new InputMain(frame);
        inputMain.addToFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setBackground(Color.WHITE);
        frame.pack();
    }
    
    private class RadioListener implements ActionListener {
		
		private int id;
		
		public RadioListener(int id) {
			this.id = id;
		}
		
		public void actionPerformed(ActionEvent e) {
			trackNumber = id;
		}
	}
    
    private class ButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			JFrame frame = new JFrame("Solver");
			frame.add(new GraphicsMain(trackNumber));
			frame.setSize(new Dimension(1000,1000));
			frame.setResizable(false);
			frame.setVisible(true);
			
		}
	}
}