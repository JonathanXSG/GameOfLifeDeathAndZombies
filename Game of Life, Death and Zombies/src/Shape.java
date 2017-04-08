import javafx.geometry.Point2D;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Shape {
	
	private String name;
	private ArrayList<Point2D> centerRelativePoints = new ArrayList<>();
	private ArrayList<Point2D> clickRelativePoints = new ArrayList<>();
	private Point2D shapeCenterPoint;
	
	public Shape(String name, ArrayList<Point2D> initialPoints){
		this.name = name;
		this.centerRelativePoints = initialPoints;
		setCenterPoint();
	}

	private void setCenterPoint(){
		Point2D bigX = this.centerRelativePoints.get(0), smallX= this.centerRelativePoints.get(0), bigY= this.centerRelativePoints.get(0), smallY= this.centerRelativePoints.get(0);
		for(Point2D point:this.centerRelativePoints){
			if(point.getX() > bigX.getX())bigX = point;
			if(point.getX() < smallX.getX())smallX = point;
			if(point.getY() > bigY.getY())bigY = point;
			if(point.getY() < smallY.getY())smallY = point;
		}
		Point2D mp1,mp2;
		mp1 = bigX.midpoint(smallX);
		mp2 = bigY.midpoint(smallY);
		Point2D midPoint = mp1.midpoint(mp2);
		DecimalFormat df = new DecimalFormat("#");
		shapeCenterPoint = new Point2D(Double.valueOf(df.format(Math.floor(midPoint.getX()))), Double.valueOf(df.format(Math.floor(midPoint.getY()))));
	}
	
	public ArrayList<Point2D> setRetalivePoints(Point2D clickedPoint){
		Point2D upperBound = new Point2D(74,74);
		Point2D lowerBound = new Point2D(0,0);
		for(Point2D point:this.centerRelativePoints){
			Point2D newPoint = point.subtract(this.shapeCenterPoint).add(clickedPoint);
			if(isBigger(newPoint,upperBound) || isSmaller(newPoint, lowerBound)){
				if(newPoint.getX()<0){
					newPoint = new Point2D(GameBoard.columns+newPoint.getX(), newPoint.getY());
				}
				if(newPoint.getY()<0){
					newPoint = new Point2D(newPoint.getX(), GameBoard.rows+newPoint.getY());
				}
				if(newPoint.getX()>74){
					newPoint = new Point2D(newPoint.getX()%GameBoard.columns, newPoint.getY());
				}
				if(newPoint.getY()>74){
					newPoint = new Point2D(newPoint.getX(), newPoint.getY()%GameBoard.rows);
				}
			}
			this.clickRelativePoints.add(newPoint);
		}
		return this.clickRelativePoints;
	}
	
	public String getName(){
		return this.name;
	}
	private boolean isBigger(Point2D p1, Point2D p2){
		if(p1.getX() > p2.getX() || p1.getY() > p2.getY()){
			return true;
		}
		else{
			return false;
		}
	}
	private boolean isSmaller(Point2D p1, Point2D p2){
		if(p1.getX() < p2.getX() || p1.getY() < p2.getY()){
			return true;
		}
		else{
			return false;
		}
	}

	
}
