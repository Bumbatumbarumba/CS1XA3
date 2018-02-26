import java.awt.Color;
import javax.swing.JPanel;

/*Created by Bartosz Kosakowski
* 01/02/2018 (dd/mm/yyyy)
* Coway's Game of Life in Java
*/
public class ConwaysGame {
	private static int rows = 20;
	private static int columns = 20;
	private static Cell[][] grid = new Cell[rows][columns];
	private static boolean runGame = false;

	// change this to be a double between 0 and 1 to determine how many live
	// members there will be off the bat when populate() is ran
	public static double livingLikelihood = 0.05;

	public static void main(String[] args) {
		populate();
		GridGui.drawGrid(grid, rows, columns);
		runGame();
	}

	public static void runGame() {
		// while runGame is false, it will put this thread to sleep
		// so that it only starts once the user starts the game
		while (!runGame) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// exception caught!
			}
		}

		// game starts when runGame is true, duh
		while (runGame) {
			// iterates through the grid and
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					if (ruleCheck(i, j)) {
						grid[i][j].makeAlive();
					} else {
						grid[i][j].makeDead();
					}
				}
			}
		}
	}// end of runGame

	// randomly populates the grid with live cells
	public static void populate() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				grid[i][j] = new Cell();
				if (Math.random() < livingLikelihood) {
					grid[i][j].makeAlive();
				}
			}
		}
	}// end of populate

	// used to count the number of live cells at any given instance
	// not useful outside of just checking stuff
	public static int countLive() {
		int numLiveCells = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (grid[i][j].isLive()) {
					numLiveCells++;
				}
			}
		}
		return numLiveCells;
	}// end of countLive

	// used to check if the current space should be alive or dead, based on the
	// rules on the wiki page of conway's game; they are listed below
	public static boolean ruleCheck(int i, int j) {
		// used to add up the number of live neighbours
		int neighbourCounter = 0;
		// used to return if the current cell will be alive or not
		boolean willSurvive = false;

		/*
		 * this first block counts up the number of alive neighbours it checks
		 * all neighbours surrounding the cell at (i,j) the beauty of using
		 * modulo is that it can simulate an area that is circular, which makes
		 * checking spaces VERY easy since for i == 0 and j == 0, if we check
		 * (i-1)%100 we will actually check the space at i == 99 j == 0
		 */
		if (grid[Math.floorMod((i - 1), rows)][Math.floorMod((j - 1), columns)].isLive())
			neighbourCounter++;
		if (grid[Math.floorMod(i, rows)][Math.floorMod((j - 1), columns)].isLive())
			neighbourCounter++;
		if (grid[Math.floorMod((i + 1), rows)][Math.floorMod((j - 1), columns)].isLive())
			neighbourCounter++;
		if (grid[Math.floorMod((i - 1), rows)][Math.floorMod(j, columns)].isLive())
			neighbourCounter++;
		if (grid[Math.floorMod((i + 1), rows)][Math.floorMod(j, columns)].isLive())
			neighbourCounter++;
		if (grid[Math.floorMod((i - 1), rows)][Math.floorMod((j + 1), columns)].isLive())
			neighbourCounter++;
		if (grid[Math.floorMod(i, rows)][Math.floorMod((j + 1), columns)].isLive())
			neighbourCounter++;
		if (grid[Math.floorMod((i + 1), rows)][Math.floorMod((j + 1), columns)].isLive())
			neighbourCounter++;

		// any cell with fewer than two live neighbours dies, as
		// if caused by underpopulation
		if (neighbourCounter < 2) {
			willSurvive = false;
		}
		// any live cell with more than three live neighbours dies,
		// as if by overpopulation
		else if (neighbourCounter > 3) {
			willSurvive = false;
		}
		// any dead cell with exactly three live neighbours becomes
		// a live cell, as if by reproduction
		else if (neighbourCounter == 3) {
			willSurvive = true;
		}
		// any live cell with two or three live neighbours lives on
		// to the next generation
		else {
			willSurvive = true;
		}

		return willSurvive;
	}

	// changes runGame to be true
	public static void startGame() {
		runGame = true;
	}

	// changes runGame to be false
	public static void endGame() {
		runGame = false;
	}
}// end of ConwaysGame

@SuppressWarnings("serial")
// represents a cell, with vars for position, colour and
// whether it is alive or not
class Cell extends JPanel{
	private Color cellColour = Color.WHITE;
	private boolean islive = false;

	public boolean isLive() {
		return this.islive;
	}

	public void makeDead() {
		this.islive = false;
		this.cellColour = Color.WHITE;
	}

	public void makeAlive() {
		this.islive = true;
		this.cellColour = Color.BLACK;
	}

	public Color getColor() {
		return this.cellColour;
	}
}// end of Cell