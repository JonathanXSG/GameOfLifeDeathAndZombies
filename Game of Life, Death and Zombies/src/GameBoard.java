
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
	static ArrayList<Cell> cells = new ArrayList<Cell>();
	int hGap=1;
	int vGap=1;
	int cellsCount;
	
	public GameBoard() {
		applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setLayout(new GridLayout(0,20,hGap,vGap));
		setBackground(Color.DARK_GRAY);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
        for(int i=0;i<20;i++){
			for(int j=0;j<20;j++){
				cells.add(new Cell(j,i,"dead"));
				
			}
		}
        for(Cell cell1:cells){
        	add(cell1);
        	System.out.println(cell1.getXVal() +" " + cell1.getYVal());
        }
    }
	
	static public void resetBoard(){
		for(Cell cellIteration:cells){
			cellIteration.setStatus("dead");
		}
	}
	
	static void runBoard(){
		setNeighbors();
		checkNeighbors();
	}
	
	public static void setNeighbors(){
		for(Cell cell1:cells){
			int n=0;
			for(Cell cell2:cells){
				if(cell2.getStatus()=="alive" && cell1!=cell2){
					if(cell1.getYVal()+1 >= cell2.getYVal() && cell1.getYVal()-1 <= cell2.getYVal() ){
					int upBound=cell1.getXVal()-1;
					int lowBound=cell1.getXVal()+1;
						if(lowBound >= cell2.getXVal() && upBound <= cell2.getXVal() ){
							n++;
						}
					}
				}
			}
			cell1.setNeighbors(n);
		}
	}
	
	static public void checkNeighbors(){
		for(Cell cell:cells){
			if(cell.getStatus()=="alive"){
				if(cell.getNeighbors()<2){
					cell.setStatus("dead");
				}
				else if(cell.getNeighbors()==2 || cell.getNeighbors()==3){
					cell.setStatus("alive");
				}
				else if(cell.getNeighbors()>3){
					cell.setStatus("dead");
				}
			}
			else if(cell.getStatus()=="dead"){
				if(cell.getNeighbors()==3){
					cell.setStatus("alive");
				}
			}
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
