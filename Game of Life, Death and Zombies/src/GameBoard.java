import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

import javafx.geometry.Point2D;

public class GameBoard extends JPanel{
	private static final long serialVersionUID = -6141196392003841438L;
	
	static int hGap=1;
	static int vGap=1;
	static int columns=50;
	static int rows=50;
	private Boolean createRandom;
	private Boolean shapeToggle;
	private String selectedShape = "star";
	static GridLayout cellGrid = new GridLayout(rows,columns,hGap,vGap);
	static Cell[][] cellArray = new Cell[200][200];
	private String guideStatusOverride;
	
	private MouseAdapter mouseClick = new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
			if(shapeToggle){
				drawShape(e);
				//insertShape(e);
			}
			else{
				cellSelection(e);
			}
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			cellSelection(e);
		}
		@Override
		public void mouseExited(MouseEvent e) {
			Cell overCell = (Cell) e.getComponent();
			if(overCell.getStatus() == "guide"){
				overCell.setStatus(guideStatusOverride);
				overCell.repaint();
		    }
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			if(SwingUtilities.isLeftMouseButton(e)){
				cellSelection(e);
			}
			else{
				Cell overCell = (Cell) e.getComponent();
				guideStatusOverride=overCell.getStatus();
				overCell.setStatus("guide");
				overCell.repaint();
			}
		}
	};
	
	public void drawShape(MouseEvent e){
		Cell overCell = (Cell) e.getComponent();
		Shape shape = new Shape();
		Point2D point = new Point2D(overCell.getXVal(), overCell.getYVal());
		
		ArrayList<Point2D> relativePoints = shape.setRetalivePoints();
		for(Point2D point1:relativePoints){
			System.out.println(point);
			int x = (int) point.getX();
			int y = (int) point.getY();
			cellArray[x][y].setStatus("zombie");
			cellArray[x][y].repaint();
;		}
	}
	
	public GameBoard() {
		applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setLayout(cellGrid);
		setBackground(Color.DARK_GRAY);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		createRandom=true;
		shapeToggle=false;
		Random random = new Random();
		
        for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				if(createRandom && random.nextInt(10)<2){
					cellArray[j][i] = new Cell(j,i,random.nextBoolean());
				}
				else{
					cellArray[j][i] = new Cell(j,i,"dead");
				}
				cellArray[j][i].addMouseListener(mouseClick);
				cellArray[j][i].addMouseMotionListener(mouseClick);
				add(cellArray[j][i]);
			}
		}
        
    }
	
	private void cellSelection(MouseEvent e){
		Cell selectedCell = (Cell) e.getComponent();
    	if(e.isControlDown() || SwingUtilities.isRightMouseButton(e)){
    		if(selectedCell.getStatus() != "zombie"){
    			selectedCell.setStatus("zombie");
    		}
    	}
    	else if(SwingUtilities.isLeftMouseButton(e)){
    		if(selectedCell.getStatus()=="dead" || selectedCell.getStatus()=="guide"){
    			selectedCell.setStatus("alive");
    		}
    		else{
    			selectedCell.setStatus("dead");
    		}
    	}
    	setAdyacentNeighborValues(selectedCell.getXVal(),selectedCell.getYVal());
    	selectedCell.repaint();
	}
	
	public void resetBoard(){
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				cellArray[i][j].setStatus("dead");
			}
		}
	}
	public void runBoard(){
		setAllNeighborValues();
		checkNeighbors();
		repaint();
		setAllNeighborValues();
		Main.generationCount++;
		Main.repaintGameboardSettings();
	}
	public void setAllNeighborValues(){
		for(int x = 0;x < columns;x++) {
			for (int y = 0; y < rows; y++) {
				calcNeighbors(x,y);
			}
		}
	}
	public void setAdyacentNeighborValues(int x, int y){
		int smallX = x - 1;
		if(smallX < 0) smallX = columns - 1;
		int smallY = y - 1;
		if(smallY < 0) smallY = rows - 1;
		int bigX = (x + 1) % columns;
		int bigY = (y + 1) % columns;
		calcNeighbors(smallX,smallY);
		calcNeighbors(smallX,y);
		calcNeighbors(smallX,bigY);
		calcNeighbors(x,smallY);
		calcNeighbors(x,y);
		calcNeighbors(x,bigY);
		calcNeighbors(bigX,smallY);
		calcNeighbors(bigX,y);
		calcNeighbors(bigX,bigY);
	}
	public void calcNeighbors(int x, int y){
		int smallX = x - 1;
		if(smallX < 0) smallX = columns - 1;
		int smallY = y - 1;
		if(smallY < 0) smallY = rows - 1;
		int bigX = (x + 1) % columns;
		int bigY = (y + 1) % columns;
		int neighbors = 0;
		if(cellArray[smallX] [smallY].isAlive()) neighbors++;
		if(cellArray[smallX] [y].isAlive()) neighbors++;
		if(cellArray[smallX] [bigY].isAlive()) neighbors++;
		if(cellArray[x] [smallY].isAlive()) neighbors++;
		if(cellArray[x] [bigY].isAlive()) neighbors++;
		if(cellArray[bigX] [smallY].isAlive()) neighbors++;
		if(cellArray[bigX] [y].isAlive()) neighbors++;
		if(cellArray[bigX] [bigY].isAlive()) neighbors++;
		cellArray[x][y].setNeighbors(neighbors);
		cellArray[x][y].setToolTipText("X: "+x+" , Y: "+y+" , N: "+cellArray[x][y].getNeighbors());
	}
	static public void checkNeighbors(){
		for(int i=0;i<columns;i++){
			for(int j=0;j<rows;j++){
				if(cellArray[i][j].getNeighbors()<2 || cellArray[i][j].getNeighbors()>3){
					cellArray[i][j].setStatus("dead");
				}
				else if(cellArray[i][j].getNeighbors()==2 && cellArray[i][j].getStatus()=="alive"){
					cellArray[i][j].setStatus("alive");
				}
				if(cellArray[i][j].getNeighbors()==3){
					cellArray[i][j].setStatus("alive");
				}
			}
		}
	}
	
	public void insertTestShape(){
		int middleX = columns/2;
		int middleY = rows/2;
		star(middleX, middleY-3);
		star(middleX, middleY+3);
		star(middleX-3, middleY);
		star(middleX+3, middleY);
		setAllNeighborValues();
	}
	public void insertShape(MouseEvent e){
		Cell selectedCell = (Cell) e.getComponent();
		int x = selectedCell.getXVal();
		int y = selectedCell.getYVal();
		switch (selectedShape) {
		case "star":
			star(x,y);
			break;
		case "block":
			block(x,y);
			break;
		case "beehive":
			beehive(x,y);
			break;
		case "loaf":
			loaf(x,y);
			break;
		case "boat":
			boat(x,y);
			break;
		case "tub":
			tub(x,y);
			break;
		case "blinker":
			blinker(x,y);
			break;
		case "toad":
			toad(x,y);
			break;
		case "beacon":
			beacon(x,y);
			break;
		default:
			break;
		}
		setAllNeighborValues();
		repaint();
	}
	
	private void star(int x, int y){
		paintCell(x, y-1, "alive");
		paintCell(x, y, "alive");
		paintCell(x, y+1, "alive");
		paintCell(x-1, y, "alive");
		paintCell(x+1, y, "alive");
	}
	private void block(int x, int y){
		paintCell(x, y, "alive");
		paintCell(x, y+1, "alive");
		paintCell(x+1, y, "alive");
		paintCell(x+1, y+1, "alive");
	}
	private void beehive(int x, int y){
		paintCell(x, y-1, "alive");
		paintCell(x+1, y-1, "alive");
		paintCell(x-1, y, "alive");
		paintCell(x+2, y, "alive");
		paintCell(x, y+1, "alive");
		paintCell(x+1, y+1, "alive");
	}
	private void loaf(int x, int y){
		paintCell(x, y-1, "alive");
		paintCell(x+1, y-1, "alive");
		paintCell(x-1, y, "alive");
		paintCell(x+2, y, "alive");
		paintCell(x, y+1, "alive");
		paintCell(x+2, y+1, "alive");
		paintCell(x+1, y+2, "alive");
	}
	private void boat(int x, int y){
		cellArray[x-1][y-1].setStatus("alive");
		cellArray[x][y-1].setStatus("alive");
		cellArray[x][y+1].setStatus("alive");
		cellArray[x-1][y].setStatus("alive");
		cellArray[x+1][y].setStatus("alive");
	}
	private void tub(int x, int y){
		cellArray[x][y-1].setStatus("alive");
		cellArray[x][y+1].setStatus("alive");
		cellArray[x-1][y].setStatus("alive");
		cellArray[x+1][y].setStatus("alive");
	}
	private void blinker(int x, int y){
		cellArray[x-1][y].setStatus("alive");
		cellArray[x][y].setStatus("alive");
		cellArray[x+1][y].setStatus("alive");
	}
	private void toad(int x, int y){
		cellArray[x-1][y].setStatus("alive");
		cellArray[x][y].setStatus("alive");
		cellArray[x+1][y].setStatus("alive");
		cellArray[x][y-1].setStatus("alive");
		cellArray[x+1][y-1].setStatus("alive");
		cellArray[x+2][y-1].setStatus("alive");
	}
	private void beacon(int x, int y){
		cellArray[x-1][y-1].setStatus("alive");
		cellArray[x][y-1].setStatus("alive");
		cellArray[x-1][y].setStatus("alive");
		cellArray[x][y].setStatus("alive");
		cellArray[x+1][y+1].setStatus("alive");
		cellArray[x+2][y+1].setStatus("alive");
		cellArray[x+1][y+2].setStatus("alive");
		cellArray[x+2][y+2].setStatus("alive");
	}
	private void paintCell(int x, int y,String status){
		cellArray[x][y].setStatus(status);
	}
	
	public void setShapeToggle(Boolean state){
		this.shapeToggle=state;
	}
	
	public void setSelectedShape(String shape){
		selectedShape = shape;
	}
    
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
    }


	
}
