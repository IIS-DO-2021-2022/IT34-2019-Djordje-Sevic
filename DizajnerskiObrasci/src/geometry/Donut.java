package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;

public class Donut extends Circle{
	private int innerRadius;
	private Area circle;
	
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
        ((Graphics2D)g).fill(circle);
	}

	@Override
	public void draw(Graphics g) {
		Ellipse2D.Double ellipse1 = new Ellipse2D.Double(
				this.getCenter().getX()-this.getRadius(),this.getCenter().getY()-this.getRadius(),this.getRadius()*2,this.getRadius()*2); 
        Ellipse2D.Double ellipse2 = new Ellipse2D.Double(
        		this.getCenter().getX()-this.innerRadius,this.getCenter().getY()-this.innerRadius,this.innerRadius*2,this.innerRadius*2);
        circle = new Area(ellipse1);
        circle.subtract(new Area(ellipse2));
        g.setColor(this.getColor());
        ((Graphics2D)g).draw(circle);
        fill(g);       
        
        if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.getCenter().getX()-3, this.getCenter().getY()-3, 6, 6);
			g.drawRect(this.getCenter().getX()-this.getRadius()-3, this.getCenter().getY()-3, 6, 6);
			g.drawRect(this.getCenter().getX()+this.getRadius()-3, this.getCenter().getY()-3, 6, 6);
			g.drawRect(this.getCenter().getX()-3, this.getCenter().getY()-this.getRadius()-3, 6, 6);
			g.drawRect(this.getCenter().getX()-3, this.getCenter().getY()+this.getRadius()-3, 6, 6);
		}
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