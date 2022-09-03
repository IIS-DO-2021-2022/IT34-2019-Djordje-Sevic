package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape {
	private int x, y;

	public Point() {
		
	}	

	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}	

	public Point(int x, int y, boolean selected) {
		this(x,y);
		this.setSelected(selected);
	}
	
	public Point(int x, int y, boolean selected, Color color) {
		this(x,y,selected);
		this.setColor(color);
	}
	
	@Override
	public int compareTo(Object o) {
		if ( o instanceof Point){
			Point coordinateStartPoint = new Point(0,0);
			return (int)(this.distance(coordinateStartPoint.getX(), coordinateStartPoint.getY()) - ((Point)o).distance(coordinateStartPoint.getX(), coordinateStartPoint.getY()));
		}
		return 0;
	}

	@Override
	public void moveBy(int byX, int byY) {
		this.x += byX;
		this.y += byY;		
	}

	@Override
	public boolean contains(int x, int y) {
		return this.distance(x, y) <= 3;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.drawLine(this.x-2, this.y, this.x+2, this.y);
		g.drawLine(this.x, this.y-2, this.x, this.y+2);
		
		if(isSelected()){
			g.setColor(Color.BLUE);
			g.drawRect(this.x-3, this.y-3, 6, 6);
		}
	}

	public double distance(int x2, int y2){
		return Math.sqrt(Math.pow(this.x - x2, 2) + Math.pow(this.y - y2, 2));
	}
	
	public boolean equals(Object o){
		if(o instanceof Point){
			Point pomocna = (Point) o;
			return this.x == pomocna.x && this.y == pomocna.y;
		} else return false;
	}
	public int getX() {
		return x;
	}

	public void setX(int x) throws Exception {
		if(x < 0) throw new Exception();
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) throws Exception {
		if(y < 0) throw new Exception();
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}