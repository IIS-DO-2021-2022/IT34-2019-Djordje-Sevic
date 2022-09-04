package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape {
	private Point startPoint, endPoint;
		
	public Line() {
		
	}
	
	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	public Line(Point startPoint, Point endPoint, boolean selected) {
		this(startPoint, endPoint);
		this.setSelected(selected);
	}
	
	public Line(Point startPoint, Point endPoint, boolean selected, Color color) {
		this(startPoint, endPoint, selected);
		this.setColor(color);
	}
	
	@Override
	public int compareTo(Object o) {
		if (o instanceof Line) return (int) (this.length() - ((Line) o).length());
		return 0;
	}

	@Override
	public void moveBy(int byX, int byY) {
		this.startPoint.moveBy(byX, byY);
		this.endPoint.moveBy(byX, byY);
	}

	@Override
	public boolean contains(int x, int y) {
		return ((this.startPoint.distance(x, y) + this.endPoint.distance(x, y)) - length() <= 0.05);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.drawLine(this.startPoint.getX(), this.startPoint.getY(), this.endPoint.getX(), this.endPoint.getY());
		
		if(isSelected()){
			g.setColor(Color.BLUE);
			g.drawRect(this.startPoint.getX()-3, this.startPoint.getY()-3, 6, 6);
			g.drawRect(this.endPoint.getX()-3, this.endPoint.getY()-3, 6, 6);
			g.drawRect(this.middleOfLine().getX()-3, this.middleOfLine().getY()-3, 6, 6);
		}
	}

	public double length() {
		return startPoint.distance(endPoint.getX(), endPoint.getY());
	}
	
	public Point middleOfLine() {
		return new Point((this.startPoint.getX() + this.endPoint.getX()) / 2, (this.startPoint.getY() + this.endPoint.getY()) / 2);
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line pomocna = (Line) obj;
			return this.startPoint.equals(pomocna.startPoint) && this.endPoint.equals(pomocna.endPoint);
		} else return false;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) throws Exception {
		if(startPoint.getX() < 0 || startPoint.getY() < 0) throw new Exception();
		this.startPoint = startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) throws Exception {
		if(endPoint.getX() < 0 || endPoint.getY() < 0) throw new Exception();
		this.endPoint = endPoint;
	}
	
	@Override
	public String toString() {
		return startPoint + "-->" + endPoint;
	}

	@Override
	public Line clone() {
		Line line = new Line();
		line.startPoint = this.startPoint.clone();
		line.endPoint = this.endPoint.clone();
		line.setColor(this.getColor());
		line.setSelected(this.isSelected());
		return line;
	}
}