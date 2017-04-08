import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Main {
	
	static JFrame mainFrame = new JFrame("A Game of Life, Death and Zombies");
	static JPanel mainPanel = new JPanel(new BorderLayout());	    
	static GameBoard gameBoard = new GameBoard();
	static ControlBoard controlBoard = new ControlBoard();
	static JMenuBar menuBar = new JMenuBar();
	static Timer timer;
	public static final int initialTimerSpeed = 250;
	public static int generationCount=0;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainFrame.setResizable(false);
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

				//mainFrame.setJMenuBar(menuBar);
				mainPanel.add(gameBoard,BorderLayout.WEST);
				mainPanel.add(controlBoard,BorderLayout.EAST);
				mainFrame.getContentPane().add(mainPanel);

				mainFrame.pack();
				mainFrame.setVisible(true);
				
				timer = new Timer(initialTimerSpeed, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						runGameBoard();
					}
				});
			}
		});
	}
	static void resetGameBoard(){
		timer.stop();
		gameBoard.resetBoard();
		generationCount=0;
	}
	static void repaintGameBoard(){
		timer.stop();
		gameBoard.repaint();
	}
	static void runGameBoard(){
		gameBoard.runBoard();
	}
	static void startGameBoard(){
		timer.start();
	}
	static void stopGameBoard(){
		timer.stop();
	}
	static void setTimerSpeed(int speed){
		timer.setDelay(speed);
	}
	static void setSelectedShape(String shape){
		gameBoard.setSelectedShape(shape);
	}
	public static void repaintGameboardSettings(){
		controlBoard.rePaintGameboardSettings();
	}
}
