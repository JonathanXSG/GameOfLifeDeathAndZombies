import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class CellObj {
	private String status;
	private int neighbors;
	int x;
	int y;
	int height = 20;
	int width = 20;

	public CellObj(int x, int y, String status){
		this.x=x*25+10;
		this.y=y*25;
		this.status=status;
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
		else{
			g2.setColor(Color.BLACK);
		}
		g2.setColor(Color.BLUE);
		g2.fillRect(x, y, width, height);;


	}

}
