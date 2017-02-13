
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;

public class GameBoard extends JPanel{
	ArrayList<Cell> cells = new ArrayList<Cell>();
	int xPadding=5;
	int yPadding=5;

	public GameBoard() {
		setBackground(Color.DARK_GRAY);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				cells.add(new Cell(j*25+xPadding,i*25+yPadding,"alive"));
			}
		}
        cells.get(2).setStatus("dead");
    }

    @Override
	public Dimension getPreferredSize() {
		  return new Dimension(500, 500);
	}
	@Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Cell i:cells){
	    	   i.draw(g);
	    }
    }  
}
