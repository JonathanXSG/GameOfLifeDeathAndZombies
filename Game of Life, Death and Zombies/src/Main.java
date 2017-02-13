import java.awt.LayoutManager;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Main {

	public static void main(String[] args) {
		JFrame MainFrame = new JFrame("A Game of Life, Death and Zombies");
		MainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		MainFrame.setLocationRelativeTo(null);
		MainFrame.setSize(500, 500);
		JPanel mainPanel = new JPanel();
		
	    //mainPanel.add(new JLabel("Chat:", SwingConstants.LEFT));
	    
	    GameBoard gameBoard = new GameBoard();
	    mainPanel.add(gameBoard);
		MainFrame.add(mainPanel);
		
		//MainFrame.pack();
		MainFrame.setVisible(true);

		
	}

}
