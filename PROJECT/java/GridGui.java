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
	private static JButton endButton = new JButton("Pause");
	private static EndGame endAction = new EndGame();
	private static JButton randPop = new JButton("Randomly Populate");
	private static JButton squarePop = new JButton("Centered Square Population");
	private static JButton randSquare = new JButton("Random Square Populations");
	private static JButton noPop = new JButton("Empty Grid");
	private static JFrame myFrame = new JFrame();
	private static Grid g1;

	// adds the buttons to the frame and positions them
	private static void setUpButtons() {
		startButton.addActionListener(startAction);
		startButton.setBounds(275, 600, 100, 30);

		endButton.addActionListener(endAction);
		endButton.setBounds(275, 600, 100, 30); // 400, 600, 100, 30
		endButton.setVisible(false);
		
		randPop.addActionListener(new RandomPop());
		randPop.setBounds(560, 200, 150, 30);
		
		squarePop.addActionListener(new SquarePop());
		squarePop.setBounds(560, 250, 150, 30);
		
		randSquare.addActionListener(new RandSquarePop());
		randSquare.setBounds(560, 300, 150, 30);
		
		noPop.addActionListener(new NoPop());
		noPop.setBounds(560, 350, 150, 30);
		
		/*startButton.addActionListener(startAction);
		startButton.setBounds(275, 600, 100, 30);*/
		
		myFrame.add(startButton);
		myFrame.add(endButton);
		myFrame.add(randPop);
		myFrame.add(squarePop);
		myFrame.add(randSquare);
		myFrame.add(noPop);
	}// end of setUpButtons

	public static void drawUI(Cell[][] cells, int rows, int columns) {
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(750, 750);
		myFrame.setVisible(true);
		myFrame.setTitle("Conway's Game of Life");
		g1 = new Grid(cells, rows, columns);

		// we add the buttons in after configuring our window
		setUpButtons();

		// we add the grid to the window after setting up the buttons
		myFrame.add(g1);
	}// end of drawUI

	// updates the grid based on current cell statuses
	public static void updateGrid() {
		myFrame.repaint();
	}// end of updateGrid
	
	public static void startVisibility(boolean isvis){
		startButton.setVisible(isvis);
		endButton.setVisible(!isvis);
	}
	
	public static void controlVisibility(boolean setVis){
		randPop.setVisible(setVis);
		squarePop.setVisible(setVis);
		randSquare.setVisible(setVis);
		noPop.setVisible(setVis);
		
		randPop.setEnabled(setVis);
		squarePop.setEnabled(setVis);
		randSquare.setEnabled(setVis);
		noPop.setEnabled(setVis);
	}
}// end of GridGui

// The following actionlistener classes allow for each button to
// perform a particular function
class StartGame implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		GridGui.startVisibility(false);
		GridGui.controlVisibility(false);
		System.out.println("START");
		ConwaysGame.startGame();
	}
}// end of StartGame

class EndGame implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		GridGui.startVisibility(true);
		GridGui.controlVisibility(true);
		System.out.println("DONE");
		ConwaysGame.endGame();
	}
}// end of EndGame

class RandomPop implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		ConwaysGame.setPopType(0);
		ConwaysGame.decisionMade(false);
		GridGui.updateGrid();
	}
}// end of RandomPop

class SquarePop implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		ConwaysGame.setPopType(1);
		ConwaysGame.decisionMade(false);
	}
}// end of SquarePop

class RandSquarePop implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		ConwaysGame.setPopType(2);
		ConwaysGame.decisionMade(false);
	}
}// end of RandSquarePop

class NoPop implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		ConwaysGame.setPopType(3);
		ConwaysGame.decisionMade(false);
	}
}// end of NoPop

class ResetGame implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		ConwaysGame.endGame();
	}
}// end of NoPop

// used to paint the grid shown on the frame
@SuppressWarnings("serial")
class Grid extends JPanel {
	private int rows;
	private int columns;
	private Cell[][] cells;

	public Grid(Cell[][] cells, int rows, int columns) {
		this.cells = cells;
		this.rows = rows;
		this.columns = columns;
	}

	public void paintComponent(Graphics g) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				g.setColor(this.cells[i][j].getColor());
				g.fillRect(i * 5 + 50, j * 5 + 50, 5, 5);
			}
		}
	}
}// end of Grid