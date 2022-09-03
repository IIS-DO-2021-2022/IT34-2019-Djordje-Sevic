package mvc;

import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JOptionPane;

import dialogs.DlgCircle;
import dialogs.DlgDonut;
import dialogs.DlgHexagon;
import dialogs.DlgLine;
import dialogs.DlgPoint;
import dialogs.DlgRectangle;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import geometry.SurfaceShape;
import adapter.HexagonAdapter;

import java.awt.Color;

public class DrawingController {

	private DrawingModel drawingModel;
	private DrawingFrame drawingFrame;
	
	private Iterator<Shape> it;
	private Shape selectedShape;
	private int selectedIndex;
	private Point startPoint;
	
	public DrawingController(DrawingModel drawingModel, DrawingFrame drawingFrame) {
		this.drawingModel = drawingModel;
		this.drawingFrame = drawingFrame;
	}
	
	public void mouseClicked(MouseEvent e) {
		Shape newShape = null;		
		if(drawingFrame.getTglbtnSelect().isSelected()) {
			selectedShape = null;
			it = drawingModel.getShapes().iterator();
			int i = -1;
			while(it.hasNext()) {
				Shape shape = it.next();
				shape.setSelected(false);
				i++;
				if(shape.contains(e.getX(), e.getY())) {
					selectedShape = shape;
					selectedIndex = i;
				}
			}
			if(selectedShape != null) selectedShape.setSelected(true);			
		} else if (drawingFrame.getTglbtnPoint().isSelected()) {
			newShape = new Point(e.getX(), e.getY(), false, drawingFrame.getColorPalete().getColor());
		} else if (drawingFrame.getTglbtnLine().isSelected()) {
			if(startPoint == null) startPoint = new Point(e.getX(), e.getY(), false, drawingFrame.getColorPalete().getColor());
			else {
				newShape = new Line(startPoint, new Point(e.getX(), e.getY()), false, drawingFrame.getColorPalete().getColor());
				startPoint = null;
			}
		} else if (drawingFrame.getTglbtnRectangle().isSelected()) {
			DlgRectangle r = new DlgRectangle();
			r.getTxtXCoordinate().setText(String.valueOf(e.getX()));
			r.getTxtYCoordinate().setText(String.valueOf(e.getY()));
			r.getTxtXCoordinate().setEnabled(false);
			r.getTxtYCoordinate().setEnabled(false);
			r.setInnerColor(Color.WHITE);
			r.getBtnColor().setVisible(false);
			r.setVisible(true);
			if(!r.isOk()) return;
			newShape = new Rectangle(new Point(e.getX(),e.getY()), -1, -1, false, drawingFrame.getColorPalete().getColor(), r.getInnerColor());
			try {				
				((Rectangle)newShape).setWidth(Integer.parseInt(r.getTxtWidth().getText()));
				((Rectangle)newShape).setHeigth(Integer.parseInt(r.getTxtHeigth().getText()));
			} catch (Exception ex) {
				newShape = null;
				JOptionPane.showMessageDialog(drawingFrame, "Wrong input data", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (drawingFrame.getTglbtnHexagon().isSelected()) {
			DlgHexagon h = new DlgHexagon();
			h.getTxtX().setText(String.valueOf(e.getX()));
			h.getTxtY().setText(String.valueOf(e.getY()));
			h.getTxtX().setEnabled(false);
			h.getTxtY().setEnabled(false);
			h.setInnerColor(Color.WHITE);
			h.getBtnColor().setVisible(false);
			h.setVisible(true);
			if(!h.isOk()) return;
			newShape = new HexagonAdapter(new Point(e.getX(), e.getY()), -1, false, drawingFrame.getColorPalete().getColor(), h.getInnerColor());
			try {
				((HexagonAdapter)newShape).setRadius(Integer.parseInt(h.getTxtR().getText()));
			} catch (Exception ex) {
				newShape = null;
				JOptionPane.showMessageDialog(drawingFrame, "Wrong input data", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (drawingFrame.getTglbtnCircle().isSelected()) {
			DlgCircle c = new DlgCircle();
			c.getTxtX1().setText(String.valueOf(e.getX()));
			c.getTxtY1().setText(String.valueOf(e.getY()));
			c.getTxtX1().setEnabled(false);
			c.getTxtY1().setEnabled(false);
			c.setInnerColor(Color.WHITE);
			c.getBtnColor().setVisible(false);
			c.setVisible(true);
			if(!c.isOk()) return;
			newShape = new Circle(new Point(e.getX(),e.getY()), -1, false, drawingFrame.getColorPalete().getColor(), c.getInnerColor());
			try {
				((Circle)newShape).setRadius(Integer.parseInt(c.getTxtRadius().getText()));
			} catch (Exception ex) {
				newShape = null;
				JOptionPane.showMessageDialog(drawingFrame, "Wrong input data", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (drawingFrame.getTglbtnDonut().isSelected()) {
			DlgDonut d = new DlgDonut();
			d.getTxtX1().setText(String.valueOf(e.getX()));
			d.getTxtY1().setText(String.valueOf(e.getY()));
			d.getTxtX1().setEnabled(false);
			d.getTxtY1().setEnabled(false);
			d.setInnerColor(Color.WHITE);
			d.getBtnColor().setVisible(false);
			d.setVisible(true);
			if(!d.isOk()) return;
			newShape = new Donut(new Point(e.getX(),e.getY()), -1, -1, false, drawingFrame.getColorPalete().getColor(), d.getInnerColor());
			try {
				((Donut)newShape).setRadius(Integer.parseInt(d.getTxtOuterRadius().getText()));
				((Donut)newShape).setInnerRadius(Integer.parseInt(d.getTxtInnerRadius().getText()));
			} catch (Exception ex) {
				newShape = null;
				JOptionPane.showMessageDialog(drawingFrame, "Wrong input data", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (drawingFrame.getTglbtnFill().isSelected()) {
			SurfaceShape fillShape = null;
			it = drawingModel.getShapes().iterator();
			while(it.hasNext()) {
				Shape shape = it.next();
				if(shape.contains(e.getX(), e.getY()) && shape instanceof SurfaceShape) fillShape = (SurfaceShape) shape;
			}
			if(fillShape != null) fillShape.setInnerColor(drawingFrame.getColorPalete().getColor());
		}
		if (newShape != null) drawingModel.add(newShape);
		drawingFrame.repaint();
	}
	
	public void modify() {		
		if(selectedShape == null) {
			JOptionPane.showMessageDialog(drawingFrame, "You didn't select shape!", "Warrning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if(selectedShape instanceof Point) {
			DlgPoint p = new DlgPoint();
			p.getTxtX1().setText(String.valueOf(((Point) selectedShape).getX()));
			p.getTxtY1().setText(String.valueOf(((Point) selectedShape).getY()));
			p.setColor(selectedShape.getColor());
			p.setVisible(true);
			if(!p.isOk()) return;
			selectedShape = new Point(-1,-1, true, p.getColor());
			try {
				((Point)selectedShape).setX(Integer.parseInt(p.getTxtX1().getText()));
				((Point)selectedShape).setY(Integer.parseInt(p.getTxtY1().getText()));
				drawingModel.getShapes().set(selectedIndex, selectedShape);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(drawingFrame, "Wrong input data", "Error", JOptionPane.ERROR_MESSAGE);
			}					
		} else if (selectedShape instanceof Line){
			DlgLine l = new DlgLine();
			l.getTxtX1().setText(String.valueOf(((Line) selectedShape).getStartPoint().getX()));
			l.getTxtY1().setText(String.valueOf(((Line) selectedShape).getStartPoint().getY()));
			l.getTxtX2().setText(String.valueOf(((Line) selectedShape).getEndPoint().getX()));
			l.getTxtY2().setText(String.valueOf(((Line) selectedShape).getEndPoint().getY()));
			l.setColor(selectedShape.getColor());
			l.setVisible(true);
			if(!l.isOk()) return;
			selectedShape = new Line(null, null, true, l.getColor());			
			try {
				((Line)selectedShape).setStartPoint(new Point(Integer.parseInt(l.getTxtX1().getText()), Integer.parseInt(l.getTxtY1().getText())));
				((Line)selectedShape).setEndPoint(new Point(Integer.parseInt(l.getTxtX2().getText()), Integer.parseInt(l.getTxtY2().getText())));
				drawingModel.getShapes().set(selectedIndex, selectedShape);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(drawingFrame, "Wrong input data", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if(selectedShape instanceof HexagonAdapter) {
			DlgHexagon h = new DlgHexagon();
			h.getTxtX().setText(String.valueOf(((HexagonAdapter)selectedShape).getCenter().getX()));
			h.getTxtY().setText(String.valueOf(((HexagonAdapter)selectedShape).getCenter().getY()));
			h.getTxtR().setText(String.valueOf(((HexagonAdapter)selectedShape).getRadius()));
			h.setColor(((HexagonAdapter)selectedShape).getColor());
			h.setInnerColor(((HexagonAdapter)selectedShape).getInnerColor());
			h.setVisible(true);
			if(!h.isOk()) return;
			selectedShape = new HexagonAdapter(null,-1, true, h.getColor(), h.getInnerColor());
			try {
				((HexagonAdapter)selectedShape).setCenter(new Point(Integer.parseInt(h.getTxtX().getText()),Integer.parseInt(h.getTxtY().getText())));
				((HexagonAdapter)selectedShape).setRadius(Integer.parseInt(h.getTxtR().getText()));
				drawingModel.getShapes().set(selectedIndex, selectedShape);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(drawingFrame, "Wrong input data", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if(selectedShape instanceof Rectangle) {
			DlgRectangle r = new DlgRectangle();
			r.getTxtXCoordinate().setText(String.valueOf(((Rectangle) selectedShape).getUpperLeftPoint().getX()));
			r.getTxtYCoordinate().setText(String.valueOf(((Rectangle) selectedShape).getUpperLeftPoint().getY()));
			r.getTxtWidth().setText(String.valueOf(((Rectangle) selectedShape).getWidth()));
			r.getTxtHeigth().setText(String.valueOf(((Rectangle) selectedShape).getHeigth()));
			r.setColor(((Rectangle) selectedShape).getColor());
			r.setInnerColor(((Rectangle) selectedShape).getInnerColor());
			r.setVisible(true);
			if(!r.isOk()) return;
			selectedShape = new Rectangle(null, -1, -1, true, r.getColor(), r.getInnerColor());
			try {
				((Rectangle)selectedShape).setUpperLeftPoint(new Point(Integer.parseInt(r.getTxtXCoordinate().getText()), Integer.parseInt(r.getTxtYCoordinate().getText())));
				((Rectangle)selectedShape).setWidth(Integer.parseInt(r.getTxtWidth().getText()));
				((Rectangle)selectedShape).setHeigth(Integer.parseInt(r.getTxtHeigth().getText()));
				drawingModel.getShapes().set(selectedIndex, selectedShape);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(drawingFrame, "Wrong input data", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if(selectedShape instanceof Donut) {
			DlgDonut d = new DlgDonut();
			d.getTxtX1().setText(String.valueOf(((Donut) selectedShape).getCenter().getX()));
			d.getTxtY1().setText(String.valueOf(((Donut) selectedShape).getCenter().getY()));
			d.getTxtInnerRadius().setText(String.valueOf(((Donut) selectedShape).getInnerRadius()));
			d.getTxtOuterRadius().setText(String.valueOf(((Donut) selectedShape).getRadius()));
			d.setColor(((Donut) selectedShape).getColor());
			d.setInnerColor(((Donut) selectedShape).getInnerColor());
			d.setVisible(true);
			if(!d.isOk()) return;
			selectedShape = new Donut(null, -1, -1, true, d.getColor(), d.getInnerColor());
			try {
				((Donut)selectedShape).setCenter(new Point(Integer.parseInt(d.getTxtX1().getText()), Integer.parseInt(d.getTxtY1().getText())));
				((Donut)selectedShape).setRadius(Integer.parseInt(d.getTxtOuterRadius().getText()));
				((Donut)selectedShape).setInnerRadius(Integer.parseInt(d.getTxtInnerRadius().getText()));
				drawingModel.getShapes().set(selectedIndex, selectedShape);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(drawingFrame, "Wrong input data", "Error", JOptionPane.ERROR_MESSAGE);
			}				
		} else if(selectedShape instanceof Circle) {
			DlgCircle c = new DlgCircle();
			c.getTxtX1().setText(String.valueOf(((Circle) selectedShape).getCenter().getX()));
			c.getTxtY1().setText(String.valueOf(((Circle) selectedShape).getCenter().getY()));
			c.getTxtRadius().setText(String.valueOf(((Circle) selectedShape).getRadius()));
			c.setColor(((Circle) selectedShape).getColor());
			c.setInnerColor(((Circle) selectedShape).getInnerColor());
			c.setVisible(true);
			if(!c.isOk()) return;
			selectedShape = new Circle(null, -1, true, c.getColor(), c.getInnerColor());
			try {
				((Circle)selectedShape).setCenter(new Point(Integer.parseInt(c.getTxtX1().getText()), Integer.parseInt(c.getTxtY1().getText())));
				((Circle)selectedShape).setRadius(Integer.parseInt(c.getTxtRadius().getText()));
				drawingModel.getShapes().set(selectedIndex, selectedShape);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(drawingFrame, "Wrong input data", "Error", JOptionPane.ERROR_MESSAGE);
			}					
		}
		drawingFrame.repaint();
	}
	
	public void delete() {
		if(selectedShape == null) {
			JOptionPane.showMessageDialog(drawingFrame, "You didn't select shape!", "Warrning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(JOptionPane.showConfirmDialog(drawingFrame, "Are you sure?") != 0) return;
		drawingModel.getShapes().remove(selectedShape);
		selectedShape = null;
		drawingFrame.repaint();
	}

}
