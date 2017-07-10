import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import javafx.geometry.Point2D;

public class GameBoard extends JPanel{
	private static final long serialVersionUID = -6141196392003841438L;
	
	static final int ZOMBIE_CHANCE = 5;
	static int hGap=1;
	static int vGap=1;
	static int columns=150;
	static int rows=100;
	private boolean drawTestShape = false;
	private boolean createRandom = true;
	private static GridLayout cellGrid = new GridLayout(rows,columns,hGap,vGap);
	
	static private Random random = new Random();
	private String selectedShape = "dot";
	private String statusSelected;
	private static Cell[][] cellArray = new Cell[columns][rows];
	private Shapes shape = new Shapes();
	private ArrayList<Point2D> relativePoints = new ArrayList<Point2D>();
	
	
	public GameBoard() {
		applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setLayout(cellGrid);
		setBackground(Color.DARK_GRAY);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				if(createRandom && random.nextInt(10)<2){
					cellArray[j][i] = new Cell(j,i,random.nextBoolean(),1000/rows);
				}
				else{
					cellArray[j][i] = new Cell(j,i,"dead",1000/rows);
				}
				cellArray[j][i].addMouseListener(mouseClick);
				cellArray[j][i].addMouseMotionListener(mouseClick);
				add(cellArray[j][i]);
			}
		}
		if(drawTestShape){
			resetBoard();
			insertTestShape();
		}
	}
	
	public void resetBoard(){
		for(int i=0;i<columns;i++){
			for(int j=0;j<rows;j++){
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
		int bigY = (y + 1) % rows;
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
		int bigY = (y + 1) % rows;
		int neighbors = 0;
		int zombieNeighbors = 0;
		if(cellArray[smallX] [smallY].isAlive()) neighbors++;
		else if(cellArray[smallX] [smallY].isZombie()) zombieNeighbors++;
		if(cellArray[smallX] [y].isAlive()) neighbors++;
		else if(cellArray[smallX] [y].isZombie()) zombieNeighbors++;
		if(cellArray[smallX] [bigY].isAlive()) neighbors++;
		else if(cellArray[smallX] [bigY].isZombie()) zombieNeighbors++;
		if(cellArray[x] [smallY].isAlive()) neighbors++;
		else if(cellArray[x] [smallY].isZombie()) zombieNeighbors++;
		if(cellArray[x] [bigY].isAlive()) neighbors++;
		else if(cellArray[x] [bigY].isZombie()) zombieNeighbors++;
		if(cellArray[bigX] [smallY].isAlive()) neighbors++;
		else if(cellArray[bigX] [smallY].isZombie()) zombieNeighbors++;
		if(cellArray[bigX] [y].isAlive()) neighbors++;
		else if(cellArray[bigX] [y].isZombie()) zombieNeighbors++;
		if(cellArray[bigX] [bigY].isAlive()) neighbors++;
		else if(cellArray[bigX] [bigY].isZombie()) zombieNeighbors++;
		cellArray[x][y].setNeighbors(neighbors);
		cellArray[x][y].setZombieNeighbors(zombieNeighbors);
		cellArray[x][y].setToolTipText("X: "+x+" , Y: "+y+" , N: "+cellArray[x][y].getNeighbors());
	}
	static public void checkNeighbors(){
		for(int i=0;i<columns;i++){
			for(int j=0;j<rows;j++){
				if(cellArray[i][j].getStatus().equals("alive")){
					if(cellArray[i][j].getNeighbors()<2 || cellArray[i][j].getNeighbors()>3){
						if(random.nextInt(100)<ZOMBIE_CHANCE)  cellArray[i][j].setStatus("zombie");
						else  cellArray[i][j].setStatus("dead");
					}
					else if(cellArray[i][j].getNeighbors()==2 && cellArray[i][j].getStatus()=="alive"){
						cellArray[i][j].setStatus("alive");
					}
					if(cellArray[i][j].getZombieNeighbors() - cellArray[i][j].getNeighbors() >=2){
						cellArray[i][j].setStatus("zombie");
					}
				}
				else if(cellArray[i][j].getStatus().equals("zombie")){
					if(cellArray[i][j].getNeighbors() - cellArray[i][j].getZombieNeighbors() >=3 ){
						cellArray[i][j].setStatus("dead");
					}
				}
				else{
					if(cellArray[i][j].getZombieNeighbors()>=4 && cellArray[i][j].getZombieNeighbors()<7){
						cellArray[i][j].setStatus("zombie");
					}
					else if(cellArray[i][j].getNeighbors()==3){
						cellArray[i][j].setStatus("alive");
					}
				}
			}
		}
	}
	
	public void insertTestShape(){
		relativePoints.clear();
		int middleX = columns/2;
		int middleY = rows/2;
		relativePoints =shape.getShape("star", new Point2D(middleX, middleY-3));
		relativePoints =shape.getShape("star", new Point2D(middleX, middleY+3));
		relativePoints =shape.getShape("star", new Point2D(middleX-3, middleY));
		relativePoints =shape.getShape("star", new Point2D(middleX+3, middleY));
		for(Point2D point1:relativePoints){
			int x = (int) point1.getX();
			int y = (int) point1.getY();
			cellArray[x][y].setStatus("alive");
		}
		setAllNeighborValues();
	}
	
	public void drawShape(MouseEvent e, String status){
		Cell overCell = (Cell) e.getComponent();
		Point2D clickedPoint = new Point2D(overCell.getXVal(), overCell.getYVal());
		relativePoints.clear();
		relativePoints =shape.getShape(selectedShape, clickedPoint);
		for(Point2D point1:relativePoints){
			int x = (int) point1.getX();
			int y = (int) point1.getY();
			if(status == "guide"){
				cellArray[x][y].setGuideStatusOverride(cellArray[x][y].getStatus());
				cellArray[x][y].setStatus(status);
			}
			else if(status == "override"){
				cellArray[x][y].setStatus(cellArray[x][y].getGuideStatusOverride());
			}
			else{
				cellArray[x][y].setStatus(status);
				cellArray[x][y].setGuideStatusOverride(cellArray[x][y].getStatus());
			}
			cellArray[x][y].repaint();
			setAdyacentNeighborValues(x,y);
		}
	}
	
	public void setSelectedShape(String shape){
		selectedShape = shape;
	}
    
	private MouseAdapter mouseClick = new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
			if(e.isControlDown() || SwingUtilities.isRightMouseButton(e)){
				drawShape(e,"zombie");
				statusSelected = "zombie";
			}
			else if(e.isAltDown()){
				drawShape(e,"dead");
				statusSelected = "dead";
			}
			else{
				drawShape(e,"alive");
				statusSelected = "alive";
			}
		}
		@Override
		public void mouseExited(MouseEvent e) {
			Cell overCell = (Cell) e.getComponent();
			if(overCell.getStatus() == "guide"){
				drawShape(e,"override");
		    }
			else{
				try {
					drawShape(e,"override");
				} catch (Exception e2) {
					System.out.println(e2 +" "+ ((Cell) e.getComponent()).getYVal());
				}
				
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			if(SwingUtilities.isLeftMouseButton(e)){
				drawShape(e, statusSelected);
			}
			else{
				Cell overCell = (Cell) e.getComponent();
				if(overCell.getStatus() != "guide"){
					drawShape(e, "guide");
				}
			}	
		}
	};
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
    }

	
}
