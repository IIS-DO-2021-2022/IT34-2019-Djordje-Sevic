package mvc;

import javax.swing.JPanel;

import geometry.Shape;

import java.awt.Graphics;
import java.util.Iterator;

public class DrawingView extends JPanel {

	private DrawingModel drawingModel = new DrawingModel();

	public void setDrawingModel(DrawingModel drawingModel) {
		this.drawingModel = drawingModel;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> it = drawingModel.getShapes().iterator();
		while(it.hasNext()) {
			it.next().draw(g);
		}
	}
}
