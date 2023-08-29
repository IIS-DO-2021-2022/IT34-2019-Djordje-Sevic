 package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends SurfaceShape {
	private Point center;
	private int radius;
	
	public Circle() {
		
	}
	
	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}
	
	public Circle(Point center, int radius, boolean selected) {
		this(center, radius);
		setSelected(selected);
	}
	
	public Circle(Point center, int radius, boolean selected, Color color) {
		this(center, radius, selected);
		this.setColor(color);
	}
	
	public Circle(Point center, int radius, boolean selected, Color color, Color innerColor) {
		this(center, radius, selected, color);
		this.setInnerColor(innerColor);
	}
	
	public double area() {
		return radius * radius * Math.PI;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle pomocni = (Circle) obj;
			return this.center.equals(pomocni.center) && this.radius == pomocni.radius;
		} else return false;
	}
	
	@Override
	public void moveBy(int byX, int byY) {
		this.center.moveBy(byX, byY);
	}

	@Override
	public int compareTo(Object o) {
		if(o instanceof Circle) return this.radius - ((Circle) o).radius;
		return 0;
	}

	@Override
	public boolean contains(int x, int y) {
		return this.center.distance(x, y) <= this.radius;
	}
	
	@Override
	public void fill(Graphics g) {
		g.setColor(this.getInnerColor());
		g.fillOval(this.center.getX()-this.radius+1, this.center.getY()-this.radius+1, this.radius*2-2, this.radius*2-2);		
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.getColor());
		g.drawOval(this.center.getX()-this.radius, this.center.getY()-this.radius, this.radius*2, this.radius*2);
		this.fill(g);
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.center.getX()-3, this.center.getY()-3, 6, 6);
			g.drawRect(this.center.getX()-this.radius-3, this.center.getY()-3, 6, 6);
			g.drawRect(this.center.getX()+this.radius-3, this.center.getY()-3, 6, 6);
			g.drawRect(this.center.getX()-3, this.center.getY()-this.radius-3, 6, 6);
			g.drawRect(this.center.getX()-3, this.center.getY()+this.radius-3, 6, 6);
		}	
	}

	public Point getCenter() {
		return center;
	}
	public void setCenter(Point center) throws Exception {
		if (center.getX() < 0 || center.getY() < 0) throw new Exception();
		this.center = center;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) throws Exception {
		if (radius <= 0) throw new Exception();
		this.radius = radius;
	}
	
	@Override
	public String toString() {
		return "Circle:" + " center: "+ getCenter().getX() + " " + getCenter().getY()+" radius: "+getRadius()+" color: "+getColor().getRGB() +" inner_color: "+getInnerColor().getRGB();
	}

	@Override
	public Circle clone() {
		Circle c = new Circle();
		c.center = this.center.clone();
		c.radius = this.radius;
		c.setColor(this.getColor());
		c.setInnerColor(this.getInnerColor());
		c.setSelected(this.isSelected());
		return c;
	}
}