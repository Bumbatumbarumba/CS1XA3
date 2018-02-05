/*Created by Bartosz Kosakowski
* 01/02/2018 (dd/mm/yyyy)
* Coway's Game of Life in Java
*/
public class ConwaysGame {
	private static int rows = 100;
	private static int columns = 100;
	private static Cell[][] grid = new Cell[rows][columns];
	private static boolean runGame = false;

	// change this to be a double between 0 and 1 to determine how many live
	// members there will be off the bat when populate() is ran
	public static double livingLikelihood = 0.05;

	public static void main(String[] args) {
		populate();
		GridGui.drawGrid();
		runGame();
	}

	public static void runGame() {
		// while runGame is false, it will put this thread to sleep
		// so that it only starts once the user starts the game
		while (!runGame) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				//exception caught!
			}
		}
		// game starts when runGame is true, duh
		while (runGame) {
			// put stuff here to make the game run
			for (int i = 0; i < 20; i++) {
				System.out.println("test");
			}
		}
	}

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
		int neighbourCounter = 0;
		boolean willSurvive = false;
		if (i > 0) {
			if (j > 0) {
			}
		}

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

// represents a cell, with vars for position and whether it is alive or not
// I will prolly remove the position vars
class Cell {
	private int x = 0;
	private int y = 0;
	private boolean islive = false;

	public boolean isLive() {
		return this.islive;
	}

	public void makeDead() {
		this.islive = false;
	}

	public void makeAlive() {
		this.islive = true;
	}
}//end of Cell