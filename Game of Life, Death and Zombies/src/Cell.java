import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

public class Cell extends JComponent{

	private static final long serialVersionUID = 198019049042373243L;
	private String status;
	private String guideStatusOverride = "dead";
	private int neighbors;
	private int zombieNeighbors;
	private int x;
	private int y;
	private int size = 10;
	private Boolean state = false;
	
	public Cell(int x, int y, String status, int size){
		this.x=x;
		this.y=y;
		this.status=status;
		this.size=size;
		setToolTipText("X: "+this.getXVal()+" , Y: "+this.getYVal());
	}
	public Cell(int x, int y, Boolean alive, int size){
		this.x=x;
		this.y=y;
		this.state=alive;
		this.size = size;
		if(this.getState()){
			this.status="alive";
		}
		else{
			this.status="dead";
		}
		setToolTipText("X: "+this.getXVal()+" , Y: "+this.getYVal());
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if(status=="alive"){
			g2.setColor(Color.BLUE);
		}
		else if (status == "zombie"){
			g2.setColor(Color.GREEN);
		}
		else if(status == "dead"){
			g2.setColor(Color.BLACK);
		}
		else if(status == "guide"){
			g2.setColor(Color.GRAY);
		}
		else{
			g2.setColor(Color.RED);
		}
		g2.fillRect(0, 0, size, size);;
		setToolTipText("X: "+this.getXVal()+" , Y: "+this.getYVal() + " , N: " + this.getNeighbors() + "  "+ this.status);
	}
	
	@Override
	public Dimension getPreferredSize() {
		  return new Dimension(size, size);
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.draw(g);
	}
	public Boolean isAlive(){
		return this.getState();
	}
	public Boolean isZombie(){
		if(this.status.equals("zombie")){
			return true;
		}
		return false;
	}
	public int getNeighbors(){
		return this.neighbors;
	}
	public void setNeighbors(int neighbors){
		this.neighbors=neighbors;
	}
	public int getZombieNeighbors(){
		return this.zombieNeighbors;
	}
	public void setZombieNeighbors(int zombieNeighbors){
		this.zombieNeighbors=zombieNeighbors;
	}
	public void setStatus(String status){
		this.status=status;
		if(status=="alive"){
			this.setState(true);
		}
		else{
			this.setState(false);
		}
	}
	public String getStatus(){
		return this.status;
	}
	public String getGuideStatusOverride() {
		return guideStatusOverride;
	}
	public void setGuideStatusOverride(String guideStatusOverride) {
		this.guideStatusOverride = guideStatusOverride;
	}
	public Boolean getState() {
		return state;
	}
	public void setState(Boolean state) {
		this.state = state;
	}
	public int getXVal() {
		return x;
	}
	public int getYVal() {
		return y;
	}
	
}