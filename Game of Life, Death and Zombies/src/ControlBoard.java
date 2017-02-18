import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlBoard extends JPanel{
	JButton resetButton = new JButton("Reset");
	JButton playButton = new JButton("Play");
	JButton refreshButton = new JButton("Reset");
	JButton back = new JButton("Back");
	
	public ControlBoard(){
		
		resetButton.addActionListener(reset);
		playButton.addActionListener(play);
		add(resetButton);
		add(back);
		add(playButton);
		add(refreshButton);
		
	}
	
	ActionListener reset = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			GameBoard.resetBoard();
			Main.repaintGameBOard();
		}
	};
	ActionListener play = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			GameBoard.setNeighbors();
			GameBoard.checkNeighbors();
			Main.repaintGameBOard();
		}
	};
	ActionListener refresh = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			Main.repaintGameBOard();
		}
	};
	
	@Override
	public Dimension getPreferredSize() {
		  return new Dimension(200, 500);
	}
	@Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }  
}
