/*Created by Bartosz Kosakowski
* 02/02/2018 (dd/mm/yyyy)
* Draws the grid used to display Conway's Game of Life
*/
import javax.swing.JFrame;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class GridGui extends JFrame{
	private static GridGui mygrid = new GridGui();
	
	private static JButton startButton = new JButton("Start");
	private static StartGame startAction = new StartGame();
	private static JButton endButton = new JButton("End");
	private static EndGame endAction = new EndGame();
	
	private static void setUpButtons(){
		startButton.addActionListener(startAction);
		mygrid.add(startButton);
		endButton.addActionListener(endAction);
		mygrid.add(endButton);
	}
	
	public static void drawGrid(){
		mygrid.setLayout(new FlowLayout());
		mygrid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mygrid.setSize(300,300);
		mygrid.setVisible(true);
		mygrid.setTitle("Conway's Game of Life");
		
		setUpButtons();
	}
}//end of GridGui

class StartGame implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		ConwaysGame.startGame();
	}
}//end of StartGame

class EndGame implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		ConwaysGame.endGame();
		System.out.println("DONE");
	}
}//end of EndGame