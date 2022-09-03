package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Donut extends Circle{
	private int innerRadius;
	
	public Donut() {
		
	}
	
	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		this.setSelected(selected);
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color) {
		this(center, radius, innerRadius,selected);
		this.setColor(color);
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color, Color innerColor) {
		this(center, radius, innerRadius,selected,color);
		this.setInnerColor(innerColor);
	}
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof Donut) return (int) (this.area() - ((Donut) o).area());
		return 0;
	}

	@Override
	public boolean contains(int x, int y) {
		return super.contains(x, y) && getCenter().distance(x, y) > innerRadius;
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(this.getInnerColor());
		super.fill(g);
		g.setColor(Color.WHITE);
		g.fillOval(this.getCenter().getX()-this.innerRadius, this.getCenter().getY()-this.innerRadius, this.innerRadius*2, this.innerRadius*2);
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(getColor());
		g.drawOval(this.getCenter().getX()-this.innerRadius, this.getCenter().getY()-this.innerRadius, this.innerRadius*2, this.innerRadius*2);
	}

	public double area() {
		return super.area() - innerRadius*innerRadius*Math.PI;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut pomocni = (Donut) obj;
			return this.innerRadius == pomocni.innerRadius && getCenter().equals(pomocni.getCenter()) && getRadius() == pomocni.getRadius();
		} else return false;
	}
	
	public int getInnerRadius() {
		return innerRadius;
	}
	public void setInnerRadius(int innerRadius) throws Exception {
		if (innerRadius <= 0 || innerRadius > this.getRadius()) throw new Exception();
		this.innerRadius = innerRadius;
	}
	
	public String toString() {
		return super.toString() + ", inner radius=" + innerRadius;
	}
}