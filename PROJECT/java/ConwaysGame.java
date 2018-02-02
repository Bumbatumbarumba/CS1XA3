/*Created by Bartosz Kosakowski
* 01/02/2018 (dd/mm/yyyy)
* Coway's Game of Life in Java
*/
public class ConwaysGame{
	public static int rows = 100;
	public static int columns = 100;
	public static Cell[][] grid = new Cell[rows][columns];

	//change this to be a double between 0 and 1 to determine how many live
	//members there will be off the bat when populate() is ran
	public static double livingLikelihood = 0.05;

	public static void main (String [] args){
		populate();
		System.out.println(countLive());
		GridGui.drawGrid();
	}

	//randomly populates the grid with live cells
	public static void populate(){
		for (int i = 0; i < rows; i++){
			for (int j = 0; j < columns; j++){
				grid[i][j] = new Cell();
				if (Math.random() < livingLikelihood){
					grid[i][j].makeAlive();
				}
			}
		}
	}//end of populate

	//used to count the number of live cells
	public static int countLive(){
		int numLiveCells = 0;
		for (int i = 0; i < rows; i++){
			for (int j = 0; j < columns; j++){
				if (grid[i][j].isLive()){
					numLiveCells++;
				}
			}
		}
		return numLiveCells;
	}//end of countLive
}

//represents a cell, with vars for position and whether it is alive or not
//I will prolly remove the position vars
class Cell{
	private int x = 0;
	private int y = 0;
	private boolean islive = false;

	public boolean isLive(){
		return this.islive;
	}

	public void makeDead(){
		this.islive = false;
	}

	public void makeAlive(){
		this.islive = true;
	}
}