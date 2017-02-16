import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import javafx.scene.input.MouseButton;

public class Cell extends JComponent{
	private String status;
	private int neighbors;
	int x;
	int y;
	int height = 20;
	int width = 20;
	Boolean state = false;
	
	public Cell(int x, int y, String status){
		this.x=x;
		this.y=y;
		this.status=status;
		
		this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	mouseClick(e);
            }});
	}
	
	public void mouseClick(MouseEvent e){
		this.state= !state;
    	if(e.isControlDown() || SwingUtilities.isRightMouseButton(e)){
    		if(this.status != "zombie"){
    			setStatus("zombie");
    		}
    		else if(this.status=="zombie"){
    			if(this.state==true){
        			setStatus("alive");
        		}
        		else{
        			setStatus("daed");
        		}
    		}
    	}
    	else if(SwingUtilities.isLeftMouseButton(e)){
			System.out.println("hello");
    		if(state==true){
    			setStatus("alive");
    		}
    		else{
    			setStatus("daed");
    		}
    	}
        repaint();
	}
	
	public void setStatus(String status){
		this.status=status;
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
	
	
}