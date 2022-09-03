package mvc;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import geometry.*;
import javax.swing.border.LineBorder;

import dialogs.DlgCircle;
import dialogs.DlgDonut;
import dialogs.DlgRectangle;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlDrawing extends JPanel {

	private ArrayList<Shape> listOfShapes = new ArrayList<Shape>();
	private Shape selectedShape;
	private Point startPoint;
	private FrmDrawing frame;
	private Iterator<Shape> it;
	private int selectedIndex;
	
	public PnlDrawing(FrmDrawing frame) {
		this.frame = frame;
		setBackground(Color.WHITE);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				press(e);
			}
		});		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (Shape sh : listOfShapes) {
			sh.draw(g);
		}
	}

	public void press(MouseEvent e) {
		Shape newShape = null;		
		if(frame.getTglbtnSelect().isSelected()) {
			selectedShape = null;
			it = listOfShapes.iterator();
			int i = -1;
			while(it.hasNext()) {
				Shape shape = it.next();
				shape.setSelected(false);
				i++;
				if(shape.contains(e.getX(), e.getY())) {
					selectedShape = shape;
					selectedIndex= i;
				}
			}
			if(selectedShape != null) selectedShape.setSelected(true);			
		} else if (frame.getTglbtnPoint().isSelected()) {
			newShape = new Point(e.getX(), e.getY(), false, frame.getColorPalete().getColor());
		} else if (frame.getTglbtnLine().isSelected()) {
			if(startPoint == null) startPoint = new Point(e.getX(), e.getY(), false, frame.getColorPalete().getColor());
			else {
				newShape = new Line(startPoint, new Point(e.getX(), e.getY()), false, frame.getColorPalete().getColor());
				startPoint = null;
			}
		} else if (frame.getTglbtnRectangle().isSelected()) {
			DlgRectangle r = new DlgRectangle();
			r.getTxtXCoordinate().setText(String.valueOf(e.getX()));
			r.getTxtYCoordinate().setText(String.valueOf(e.getY()));
			r.getTxtXCoordinate().setEnabled(false);
			r.getTxtYCoordinate().setEnabled(false);
			r.setInnerColor(Color.WHITE);
			r.getBtnColor().setVisible(false);
			r.setVisible(true);
			if(!r.isOk()) return;
			newShape = new Rectangle(new Point(e.getX(),e.getY()), -1, -1, false, frame.getColorPalete().getColor(), r.getInnerColor());
			try {				
				((Rectangle)newShape).setWidth(Integer.parseInt(r.getTxtWidth().getText()));
				((Rectangle)newShape).setHeigth(Integer.parseInt(r.getTxtHeigth().getText()));
			} catch (Exception ex) {
				newShape = null;
				JOptionPane.showMessageDialog(frame, "Wrong input data", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (frame.getTglbtnCircle().isSelected()) {
			DlgCircle c = new DlgCircle();
			c.getTxtX1().setText(String.valueOf(e.getX()));
			c.getTxtY1().setText(String.valueOf(e.getY()));
			c.getTxtX1().setEnabled(false);
			c.getTxtY1().setEnabled(false);
			c.setInnerColor(Color.WHITE);
			c.getBtnColor().setVisible(false);
			c.setVisible(true);
			if(!c.isOk()) return;
			newShape = new Circle(new Point(e.getX(),e.getY()), -1, false, frame.getColorPalete().getColor(), c.getInnerColor());
			try {
				((Circle)newShape).setRadius(Integer.parseInt(c.getTxtRadius().getText()));
			} catch (Exception ex) {
				newShape = null;
				JOptionPane.showMessageDialog(frame, "Wrong input data", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (frame.getTglbtnDonut().isSelected()) {
			DlgDonut d = new DlgDonut();
			d.getTxtX1().setText(String.valueOf(e.getX()));
			d.getTxtY1().setText(String.valueOf(e.getY()));
			d.getTxtX1().setEnabled(false);
			d.getTxtY1().setEnabled(false);
			d.setInnerColor(Color.WHITE);
			d.getBtnColor().setVisible(false);
			d.setVisible(true);
			if(!d.isOk()) return;
			newShape = new Donut(new Point(e.getX(),e.getY()), -1, -1, false, frame.getColorPalete().getColor(), d.getInnerColor());
			try {
				((Donut)newShape).setRadius(Integer.parseInt(d.getTxtOuterRadius().getText()));
				((Donut)newShape).setInnerRadius(Integer.parseInt(d.getTxtInnerRadius().getText()));
			} catch (Exception ex) {
				newShape = null;
				JOptionPane.showMessageDialog(frame, "Wrong input data", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (frame.getTglbtnFill().isSelected()) {
			SurfaceShape fillShape = null;
			it = listOfShapes.iterator();
			while(it.hasNext()) {
				Shape shape = it.next();
				if(shape.contains(e.getX(), e.getY()) && shape instanceof SurfaceShape) fillShape = (SurfaceShape) shape;
			}
			if(fillShape != null) fillShape.setInnerColor(frame.getColorPalete().getColor());
		}
		if (newShape != null) listOfShapes.add(newShape);
		repaint();
	}

	public Shape getSelectedShape() {
		return selectedShape;
	}
	
	void setSelectedShape(Shape selectedShape) {
		this.selectedShape = selectedShape;
	}
	
	public ArrayList<Shape> getListOfShapes() {
		return listOfShapes;
	}

	public int getSelectedIndex() {
		return selectedIndex;
	}
}
