import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;

/*Created by Bartosz Kosakowski
* 01/02/2018 (dd/mm/yyyy)
* Coway's Game of Life in Java
*/
public class ConwaysGame {
	private static int rows = 100;
	private static int columns = 100;
	private static Cell[][] grid = new Cell[rows][columns];
	private static boolean runGame = false;
	private static Integer[] randRows = new Integer[rows];
	private static Integer[] randCols = new Integer[columns];
	private static int populationSeedType = 0;
	private static boolean makingPopChoice = true;

	// change this to be a double between 0 and 1 to determine how many live
	// the odds of a cell starting off as alive or dead - default is 0.05
	public static double livingLikelihood = 0.99;

	public static void main(String[] args) throws InterruptedException {
		setUpRNG();

		// comment and uncomment each of the following to use
		// different seeds:
		// populate();
		// populateSquare();
		randSquarePop();
		// doNotPop();

		GridGui.drawUI(grid, rows, columns);
		runGame();
	}// end of main

	public static void runGame() throws InterruptedException {
		while (true) {
			// while runGame is false, it will put this thread to sleep
			// so that it only starts once the user starts the game
			while (!runGame) {
				Thread.sleep(200);
			}
			// game starts when runGame is true, duh
			while (runGame) {
				// slows down the simulation speed
				Thread.sleep(100);

				// iterates through the grid randomly (does not pick duplicate
				// locations) rather than linearly
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < columns; j++) {
						if (ruleCheck(randRows[i], randCols[j])) {
							grid[randRows[i]][randCols[j]].makeAlive();
						} else {
							grid[randRows[i]][randCols[j]].makeDead();
						}
					}
				}

				// checks each space linearly; uncomment this code and comment
				// out the above code to change how it runs
				/*
				 * for (int i = 0; i < rows; i++) { for (int j = 0; j < columns;
				 * j++) { if (ruleCheck(i, j)) { grid[i][j].makeAlive(); } else
				 * { grid[i][j].makeDead(); } } }
				 */
				GridGui.updateGrid();
			}
		}
	}// end of runGame

	// sets up randRows and randCols to be a randomly ordered list of
	// ints between 0 and rows, and 0 and columns, respectively
	public static void setUpRNG() {
		for (int i = 0; i < rows; i++) {
			randRows[i] = i;
		}
		for (int j = 0; j < columns; j++) {
			randCols[j] = j;
		}

		Collections.shuffle(Arrays.asList(randRows));
		Collections.shuffle(Arrays.asList(randCols));
	}// end of setUpRNG

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

	// populates the grid with a square live cells placed in the middle
	public static void populateSquare() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				grid[i][j] = new Cell();
			}
		}
		int p1 = rows / 2;
		int p2 = columns / 2;

		grid[p1][p2].makeAlive();
		grid[p1 + 1][p2 + 1].makeAlive();
		grid[p1 + 1][p2].makeAlive();
		grid[p1][p2 + 1].makeAlive();
	}// end of populateSquare

	// populates the grid with random squares of cells at half of the
	// specified likelihood
	public static void randSquarePop() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				grid[i][j] = new Cell();
			}
		}
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (Math.random() < livingLikelihood / 2) {
					grid[i][j].makeAlive();
					grid[Math.floorMod((i + 1), rows)][Math.floorMod(j + 1, columns)].makeAlive();
					grid[Math.floorMod((i + 1), rows)][Math.floorMod(j, columns)].makeAlive();
					grid[Math.floorMod(i, rows)][Math.floorMod((j + 1), columns)].makeAlive();
				}
			}
		}
	}// end of randSquarePop

	// creates an area of dead cells; this was for testing
	public static void doNotPop() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				grid[i][j] = new Cell();
			}
		}
	}// end of doNotPop

	// used to count the number of live cells at any given instance
	// not useful outside of testing
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
		// if caused by underpopulation (if alive)
		if (neighbourCounter < 2) {
			if (grid[i][j].isLive())
				willSurvive = false;
		}
		// any live cell with more than three live neighbours dies,
		// as if by overpopulation (if alive)
		else if (neighbourCounter > 3) {
			if (grid[i][j].isLive())
				willSurvive = false;
		}
		// any dead cell with exactly three live neighbours becomes
		// a live cell, as if by reproduction (if dead)
		else if (neighbourCounter == 3) {
			if (!grid[i][j].isLive())
				willSurvive = true;
		}
		// any live cell with two or three live neighbours lives on
		// to the next generation (if alive)
		else {
			if (grid[i][j].isLive())
				willSurvive = true;
		}

		return willSurvive;
	}// end of ruleCheck

	// changes runGame to be true
	public static void startGame() {
		runGame = true;
	}// end of startGame

	// changes runGame to be false
	public static void endGame() {
		runGame = false;
	}// end of endGame

	public static void setPopType(int val) {
		populationSeedType = val;
	}// end of setPopType

	public static void decisionMade(boolean choice) {
		makingPopChoice = choice;
	}// end of setPopType
}// end of ConwaysGame

// represents a cell, with vars for colour and whether it is alive or not
class Cell {
	private Color cellColour = Color.WHITE;
	private boolean islive = false;

	// these are all pretty intuitive
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