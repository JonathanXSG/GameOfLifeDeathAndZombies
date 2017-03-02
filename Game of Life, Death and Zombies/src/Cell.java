import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

public class Cell extends JComponent{

	private static final long serialVersionUID = 198019049042373243L;
	private String status;
	private int neighbors;
	private int x;
	private int y;
	private int height = 10;
	private int width = 10;
	private Boolean state = false;
	
	public Cell(int x, int y, String status){
		this.x=x;
		this.y=y;
		this.status=status;
		
//		this.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//            	mouseClick(e);
//            }});
		setToolTipText("X: "+this.getXVal()+" , Y: "+this.getYVal());
	}
	public Cell(int x, int y, Boolean alive){
		this.x=x;
		this.y=y;
		this.setState(state);
		if(this.getState()){
			this.status="alive";
		}
		setToolTipText("X: "+this.getXVal()+" , Y: "+this.getYVal());
	}
	
//	private void mouseClick(MouseEvent e){
//		this.setState(!this.getState());
//    	if(e.isControlDown() || SwingUtilities.isRightMouseButton(e)){
//    		if(this.status != "zombie"){
//    			setStatus("zombie");
//    		}
//    		else if(this.status=="zombie"){
//    			if(this.getState()==true){
//        			setStatus("alive");
//        		}
//        		else{
//        			setStatus("dead");
//        		}
//    		}
//    	}
//    	else if(SwingUtilities.isLeftMouseButton(e)){
//    		if(getState()==true){
//    			setStatus("alive");
//    		}
//    		else{
//    			setStatus("dead");
//    		}
//    	}
//    	Main.updateNeightbors(this.getX(),this.getY());
//		repaint();
//	}
	
	public Boolean isAlive(){
		return this.getState();
	}
	public int getNeighbors(){
		return this.neighbors;
	}
	public void setNeighbors(int neighbors){
		this.neighbors=neighbors;
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
		else{
			g2.setColor(Color.RED);
		}
		g2.fillRect(0, 0, width, height);;
	}
	
	@Override
	public Dimension getPreferredSize() {
		  return new Dimension(width, height);
	}
	@Override
	public void paintComponent(Graphics g){
		this.draw(g);
		super.paintComponent(g);
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