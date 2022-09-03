package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends SurfaceShape{
	private Point upperLeftPoint;
	private int width, heigth;
	
	public Rectangle() {
		
	}
	
	public Rectangle(Point upperLeftPoint, int width, int heigth) {
		this.upperLeftPoint = upperLeftPoint;
		this.width = width;
		this.heigth = heigth;
	}
	
	public Rectangle(Point upperLeftPoint, int width, int heigth, boolean selected) {
		this(upperLeftPoint, width, heigth);
		setSelected(selected);
	}
	
	public Rectangle(Point upperLeftPoint, int width, int heigth, boolean selected, Color color) {
		this(upperLeftPoint,width,heigth,selected);
		this.setColor(color);
	}
	
	public Rectangle(Point upperLeftPoint, int width, int heigth, boolean selected, Color color, Color innerColor) {
		this(upperLeftPoint,width,heigth,selected,color);
		this.setInnerColor(innerColor);
	}
	
	@Override
	public void moveBy(int byX, int byY) {
		this.upperLeftPoint.moveBy(byX, byY);		
	}

	@Override
	public int compareTo(Object o) {
		if(o instanceof Rectangle) return this.area() - ((Rectangle) o).area();
		return 0;
	}


	@Override
	public void fill(Graphics g) {
		g.setColor(this.getInnerColor());
		g.fillRect(this.upperLeftPoint.getX()+1, this.upperLeftPoint.getY()+1, this.width-1, this.heigth-1);	
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.drawRect(this.upperLeftPoint.getX(), this.upperLeftPoint.getY(), this.width, this.heigth);
		this.fill(g);
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.upperLeftPoint.getX()-3, this.upperLeftPoint.getY()-3, 6, 6);
			g.drawRect(this.upperLeftPoint.getX()+this.width-3, this.upperLeftPoint.getY()-3, 6, 6);
			g.drawRect(this.upperLeftPoint.getX()-3, this.upperLeftPoint.getY()+this.heigth-3, 6, 6);
			g.drawRect(this.upperLeftPoint.getX()+this.width-3, this.upperLeftPoint.getY()+this.heigth-3, 6, 6);
		}		
	}
	
	@Override
	public boolean contains(int x, int y) {
		return this.upperLeftPoint.getX() <= x && this.upperLeftPoint.getY() <= y && x <= this.upperLeftPoint.getX() + width && y <= this.upperLeftPoint.getY() + heigth;
	}
	
	public int area() {
		return width * heigth;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle pomocni = (Rectangle) obj;
			return this.upperLeftPoint.equals(pomocni.upperLeftPoint) && this.heigth == pomocni.heigth && this.width == pomocni.width;
		} else return false;
	}
	
	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}
	public void setUpperLeftPoint(Point upperLeftPoint) throws Exception {
		if(upperLeftPoint.getX() < 0 || upperLeftPoint.getY() < 0) throw new Exception();
		this.upperLeftPoint = upperLeftPoint;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) throws Exception {
		if(width <= 0) throw new Exception();
		this.width = width;
	}
	public int getHeigth() {
		return heigth;
	}
	public void setHeigth(int heigth) throws Exception {
		if(heigth <= 0) throw new Exception();
		this.heigth = heigth;
	}
	
	@Override
	public String toString() {
		return "Upper Left Point=" + upperLeftPoint + ", width=" + width + ", height=" + heigth;
	}			
}