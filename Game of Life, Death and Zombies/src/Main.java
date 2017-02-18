import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Main {

	static JFrame MainFrame = new JFrame("A Game of Life, Death and Zombies");
	static JPanel mainPanel = new JPanel();	    
    static GameBoard gameBoard = new GameBoard();
    static ControlBoard controlBoard = new ControlBoard();
    static JMenuBar menuBar = new JMenuBar();
    
	public static void main(String[] args) {
		
		MainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		MainFrame.setLocationRelativeTo(null);
		
		
	    JMenu file = new JMenu("File");
	    file.setMnemonic(KeyEvent.VK_A);
	    JMenu edit = new JMenu("Edit");
	    edit.setMnemonic(KeyEvent.VK_A);
	    JMenu help = new JMenu("Help");
	    help.setMnemonic(KeyEvent.VK_A);
	    JMenuItem menuItem;
	    
	    menuBar.add(file);
	    menuBar.add(edit);
	    menuBar.add(help);
	    
	    menuItem = new JMenuItem("A text-only menu item",
	    		KeyEvent.VK_T);
	    menuItem.setAccelerator(KeyStroke.getKeyStroke(
	    		KeyEvent.VK_1, ActionEvent.ALT_MASK));
	    menuItem.getAccessibleContext();
	    file.add(menuItem);
	    
	    MainFrame.setJMenuBar(menuBar);
	    mainPanel.add(gameBoard);
	    mainPanel.add(controlBoard);
		MainFrame.getContentPane().add(mainPanel);
		
		MainFrame.pack();
		MainFrame.setVisible(true);
		
	}
	
	static void repaintGameBOard(){
		gameBoard.updateUI();
	}

}
