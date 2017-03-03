
import javafx.geometry.Point2D; 
import java.util.ArrayList;

public class Shape {
	
	private String name;
	private ArrayList<Point2D> initialPoints = new ArrayList<>();
	private ArrayList<Point2D> relativePoints = new ArrayList<>();
	private Point2D initialCenterPoint;
	private Point2D centerPoint;
	
	public Shape(){
		Point2D p1 = new Point2D(2.0,2.0);
		Point2D p2 = new Point2D(-2.0,-2.0);
		this.initialPoints.add(p1);
		SetCenterPoint();
		
	}
	
	public void SetCenterPoint(){
		Point2D bigX = this.initialPoints.get(0), smallX= this.initialPoints.get(0), bigY= this.initialPoints.get(0), smallY= this.initialPoints.get(0);
		for(Point2D point:this.initialPoints){
			if(point.getX() > bigX.getX())bigX = point;
			if(point.getX() < smallX.getX())smallX = point;
			if(point.getY() > bigY.getY())bigY = point;
			if(point.getY() < smallY.getY())smallY = point;
		}
		Point2D mp1,mp2;
		mp1 = bigX.midpoint(smallX);
		mp2 = bigY.midpoint(smallY);
		Point2D midPoint = mp1.midpoint(mp2);
		System.out.println(mp1 + "midpoint");
		initialCenterPoint = new Point2D(Math.floor(midPoint.getX()), Math.floor(midPoint.getY()));
		
	}
	
	public ArrayList<Point2D> setRetalivePoints(){
		for(Point2D point:this.initialPoints){
			relativePoints.add(point.subtract(initialCenterPoint));
			
			System.out.println(point +""+  initialCenterPoint);
		}
		return relativePoints;
	}
	
	public ArrayList<Point2D> star(Point2D centerPoint){
		return relativePoints;
	}
	
	
	
	
	
}
