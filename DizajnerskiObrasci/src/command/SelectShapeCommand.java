package command;

import java.util.List;

import geometry.Shape;
import mvc.DrawingModel;

public class SelectShapeCommand implements Command {
	private Shape shape;
	private List<Shape> selectedShapes;
	
	public SelectShapeCommand(Shape shape, List<Shape> selectedShapes) {
		this.shape = shape;
		this.selectedShapes = selectedShapes;
	}
	@Override
	public void execute() {
		this.shape.setSelected(true);
		this.selectedShapes.add(shape);
	}

	@Override
	public void unexecute() {
		this.shape.setSelected(false);
		this.selectedShapes.remove(shape);
	}
}
