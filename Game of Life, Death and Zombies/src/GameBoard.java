
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;

public class GameBoard extends JPanel{
	ArrayList<Cell> cells = new ArrayList<Cell>();
	int hGap=1;
	int vGap=1;
	int cellsCount;
	
	public GameBoard() {
		applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		setLayout(new GridLayout(0,10,hGap,vGap));
		setBackground(Color.DARK_GRAY);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
        for(int i=0;i<10;i++){
			for(int j=0;j<20;j++){
				cells.add(new Cell(j,i,"alive"));
			}
		}
        for(int  i=0;i<cells.size();i++){
        	add(cells.get(i));
        }
        
    }
	
    @Override
	public Dimension getPreferredSize() {
		  return new Dimension(500, 500);
	}
	@Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }  
}
