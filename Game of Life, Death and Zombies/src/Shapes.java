import java.util.ArrayList;

import javafx.geometry.Point2D;

public class Shapes {

	private ArrayList<Shape> shapeArrayList = new ArrayList<>();
	private ArrayList<Point2D> points = new ArrayList<>();
	public Shapes(){
		setDefaultShapes();
	}
	
	private void setDefaultShapes(){
		addDot();
		addStar();
		addBlock();
		addBeehive();
		addLoaf();
		addBoat();
		addTub();
		addBlinker();
		addToad();
		addBeacon();
	}

	
	@SuppressWarnings("unchecked")
	public void addShape(String name, ArrayList<Point2D> points){
		shapeArrayList.add(new Shape(name, (ArrayList<Point2D>) points.clone()));
	}
	
	public ArrayList<Point2D> getShape(String shape, Point2D clickedPoint){
		switch (shape) {
		case "dot":
			return shapeArrayList.get(0).setRetalivePoints(clickedPoint);
		case "star":
			return shapeArrayList.get(1).setRetalivePoints(clickedPoint);
		case "block":
			return shapeArrayList.get(2).setRetalivePoints(clickedPoint);
		case "beehive":
			return shapeArrayList.get(3).setRetalivePoints(clickedPoint);
		case "loaf":
			return shapeArrayList.get(4).setRetalivePoints(clickedPoint);
		case "boat":
			return shapeArrayList.get(5).setRetalivePoints(clickedPoint);
		case "tub":
			return shapeArrayList.get(6).setRetalivePoints(clickedPoint);
		case "blinker":
			return shapeArrayList.get(7).setRetalivePoints(clickedPoint);
		case "toad":
			return shapeArrayList.get(8).setRetalivePoints(clickedPoint);
		case "beacon":
			return shapeArrayList.get(9).setRetalivePoints(clickedPoint);
		default:
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	private void addDot(){
		points.clear();
		points.add(new Point2D(0,0));
		shapeArrayList.add(new Shape("block", (ArrayList<Point2D>) points.clone()));
	}
	@SuppressWarnings("unchecked")
	private void addStar(){
		points.clear();
		points.add(new Point2D(1,0));
		points.add(new Point2D(0,1));
		points.add(new Point2D(1,1));
		points.add(new Point2D(2,1));
		points.add(new Point2D(1,2));
		shapeArrayList.add(new Shape("star", (ArrayList<Point2D>) points.clone()));
	}
	@SuppressWarnings("unchecked")
	private void addBlock(){
		points.clear();
		points.add(new Point2D(0,0));
		points.add(new Point2D(0,1));
		points.add(new Point2D(1,0));
		points.add(new Point2D(1,1));
		shapeArrayList.add(new Shape("block", (ArrayList<Point2D>) points.clone()));
	}
	@SuppressWarnings("unchecked")
	private void addBeehive(){
		points.clear();
		points.add(new Point2D(1,0));
		points.add(new Point2D(2,0));
		points.add(new Point2D(0,1));
		points.add(new Point2D(3,1));
		points.add(new Point2D(1,2));
		points.add(new Point2D(2,2));
		shapeArrayList.add(new Shape("star", (ArrayList<Point2D>) points.clone()));
	}
	@SuppressWarnings("unchecked")
	private void addLoaf(){
		points.clear();
		points.add(new Point2D(1,0));
		points.add(new Point2D(2,0));
		points.add(new Point2D(0,1));
		points.add(new Point2D(3,1));
		points.add(new Point2D(1,2));
		points.add(new Point2D(3,2));
		points.add(new Point2D(2,3));
		shapeArrayList.add(new Shape("star", (ArrayList<Point2D>) points.clone()));
	}
	@SuppressWarnings("unchecked")
	private void addBoat(){
		points.clear();
		points.add(new Point2D(0,0));
		points.add(new Point2D(1,0));
		points.add(new Point2D(0,1));
		points.add(new Point2D(2,1));
		points.add(new Point2D(1,2));
		shapeArrayList.add(new Shape("star", (ArrayList<Point2D>) points.clone()));
	}
	@SuppressWarnings("unchecked")
	private void addTub(){
		points.clear();
		points.add(new Point2D(1,0));
		points.add(new Point2D(0,1));
		points.add(new Point2D(2,1));
		points.add(new Point2D(1,2));
		shapeArrayList.add(new Shape("star", (ArrayList<Point2D>) points.clone()));
	}
	@SuppressWarnings("unchecked")
	private void addBlinker(){
		points.clear();
		points.add(new Point2D(1,0));
		points.add(new Point2D(2,0));
		points.add(new Point2D(3,0));
		shapeArrayList.add(new Shape("star", (ArrayList<Point2D>) points.clone()));
	}
	@SuppressWarnings("unchecked")
	private void addToad(){
		points.clear();
		points.add(new Point2D(2,0));
		points.add(new Point2D(3,0));
		points.add(new Point2D(4,0));
		points.add(new Point2D(1,1));
		points.add(new Point2D(2,1));
		points.add(new Point2D(3,1));
		shapeArrayList.add(new Shape("star", (ArrayList<Point2D>) points.clone()));
	}
	@SuppressWarnings("unchecked")
	private void addBeacon(){
		points.clear();
		points.add(new Point2D(0,0));
		points.add(new Point2D(0,1));
		points.add(new Point2D(1,0));
		points.add(new Point2D(1,1));
		points.add(new Point2D(3,3));
		points.add(new Point2D(4,3));
		points.add(new Point2D(3,4));
		points.add(new Point2D(4,4));
		shapeArrayList.add(new Shape("block", (ArrayList<Point2D>) points.clone()));
	}
	
}
