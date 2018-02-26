import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
/*Created by Bartosz Kosakowski
* 02/02/2018 (dd/mm/yyyy)
* Draws the grid used to display Conway's Game of Life
*/
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GridGui extends JPanel {
	
	private static GridGui gridgui = new GridGui();

	private static JButton startButton = new JButton("Start");
	private static StartGame startAction = new StartGame();
	private static JButton endButton = new JButton("End");
	private static EndGame endAction = new EndGame();

	private static JFrame myFrame = new JFrame();

	private static Grid[][] myGrid;
	// private static Grid g = new Grid();

	// adds the buttons to the frame and positions them
	private static void setUpButtons() {
		startButton.addActionListener(startAction);
		startButton.setBounds(275, 600, 100, 30);
		endButton.addActionListener(endAction);
		endButton.setBounds(400, 600, 100, 30);
		myFrame.add(startButton);
		myFrame.add(endButton);
	}// end of setUpButtons
	
	//instantiates the 
	private static void setUpGrid(int rows, int columns) {
		myGrid = new Grid[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				myGrid[i][j] = new Grid(i, j);
				myFrame.add(myGrid[i][j]);
			}
		}
	}// end of setUpGrid

	public static void drawGrid(int rows, int columns) {

		// myFrame.setLayout(new FlowLayout());
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(750, 750);
		myFrame.setVisible(true);
		myFrame.setTitle("Conway's Game of Life");

		// we add the buttons in after configuring our window
		setUpButtons();

		// we add the grid to the window after setting up the buttons
		//setUpGrid(rows, columns);
	}// end of drawGrid

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}// end of paintComponent
}// end of GridGui

class StartGame implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		ConwaysGame.startGame();
	}
}// end of StartGame

class EndGame implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		ConwaysGame.endGame();
		System.out.println("DONE");
	}
}// end of EndGame

@SuppressWarnings("serial")
class Grid extends JPanel {
	private int i;
	private int j;
	
	public Grid(int i, int j){
		this.i = i;
		this.j = j;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRect(20, 20, 5, 5);
	}
}// end of Grid
