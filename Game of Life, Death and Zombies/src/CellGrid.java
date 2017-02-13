import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class CellGrid extends JComponent{
	
	int x;
	int y;
	int height = 10;
	int width = 10;
	ArrayList<Cell> cells = new ArrayList<Cell>();
	
	public CellGrid(int xPos, int yPos) {
		this.x=xPos;
		this.x=yPos;
	}
	
	@Override
	public Dimension getPreferredSize() {
		  return new Dimension(width, height);
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
//		for(int i=0;i<=2;i++){
//			for(int j=0;j<=2;i++){
//				cells.add(new Cell(j,i,"alive"));
//			}
//		}
		cells.add(new Cell(5,5,"alive"));
		cells.get(0).draw(g);

	}
	
	
}