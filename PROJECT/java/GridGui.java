import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*Created by Bartosz Kosakowski
* 02/02/2018 (dd/mm/yyyy)
* Draws the grid used to display Conway's Game of Life
*/
@SuppressWarnings("serial")
public class GridGui extends JPanel {

	private static JButton startButton = new JButton("Start");
	private static StartGame startAction = new StartGame();
	private static JButton endButton = new JButton("End");
	private static EndGame endAction = new EndGame();

	private static JFrame myFrame = new JFrame();
	
	private static Grid g1;
	
	// adds the buttons to the frame and positions them
	private static void setUpButtons() {
		startButton.addActionListener(startAction);
		startButton.setBounds(275, 600, 100, 30);
		endButton.addActionListener(endAction);
		endButton.setBounds(400, 600, 100, 30);
		myFrame.add(startButton);
		myFrame.add(endButton);
	}// end of setUpButtons
	
	public static void drawGrid(Cell[][] cells, int rows, int columns) {
		// myFrame.setLayout(new FlowLayout());
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(750, 750);
		myFrame.setVisible(true);
		myFrame.setTitle("Conway's Game of Life");

		// we add the buttons in after configuring our window
		setUpButtons();

		// we add the grid to the window after setting up the buttons
		g1 = new Grid(cells, rows, columns);
		myFrame.add(g1);
	}// end of drawGrid
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


//testing stuff with drawing JPanel shapes
@SuppressWarnings("serial")
class Grid extends JPanel {
	private int rows;
	private int columns;
	private Cell[][] cells;
	
	public Grid(Cell[][] cells, int rows, int columns){
		this.cells = cells;
		this.rows = rows;
		this.columns = columns;
	}
	
	public void paintComponent(Graphics g) {
		for (int i = 0; i < rows; i++){
			for (int j = 0; j < columns; j++){
				g.setColor(this.cells[i][j].getColor());
				g.fillRect(i*5, j*5, 5, 5);
				System.out.println("cell location: ("+ i + ", " + j + ")\n" + "cell colour: " + cells[i][j].getColor());
			}
		}
	}
}// end of Grid
