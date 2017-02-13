import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class CellGrid extends JComponent{
	private String status;
	private int neighbors;
	int x;
	int y;
	int height = 500;
	int width = 500;
	ArrayList<Cell> cells = new ArrayList<Cell>();
	
	public CellGrid() {
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				cells.add(new Cell(j,i,"alive"));
			}
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		  return new Dimension(width, height);
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		for(Cell i:cells){
	    	   i.draw(g);
	       }

	}
	
	
}