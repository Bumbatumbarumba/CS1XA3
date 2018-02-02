/*Created by Bartosz Kosakowski
* 02/02/2018 (dd/mm/yyyy)
* Draws the grid used to display Conway's Game of Life
*/
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class GridGui extends JFrame{
	private static GridGui mygrid = new GridGui();
	
	public static void drawGrid(){
		mygrid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mygrid.setSize(200,200);
		mygrid.setVisible(true);
		mygrid.setTitle("Conway's Game of Life");
	}
}