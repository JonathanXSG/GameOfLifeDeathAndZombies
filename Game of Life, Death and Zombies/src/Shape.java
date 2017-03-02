import java.awt.Point;
import java.util.ArrayList;

public class Shape {
	
	private static String name;
	private static ArrayList<Point> points;
	private static Point centerPoint;
	
	public Shape(){
		
	}
	
	public static void newShape(String name,ArrayList<Point> points){
		Point bigX = points.get(0), smallX= points.get(0), bigY= points.get(0), smallY= points.get(0);
		for(Point point:points){
			if(point.x > bigX.x)bigX = point;
			if(point.x < smallX.x)smallX = point;
			if(point.x > bigY.x)bigY = point;
			if(point.x < smallY.x)smallY = point;
			points.add(point);
		}
		Point center = null;
		center.x=bigX.x+smallX.x;
		center.y=bigY.y+smallY.y;
		centerPoint = center;
	}
	
	public static ArrayList<Point> star(Point centerPoint){
		int x =centerPoint.x;
		return null;
	}
}
