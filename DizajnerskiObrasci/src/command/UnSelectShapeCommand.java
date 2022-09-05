package command;

import java.util.List;

import geometry.Shape;

public class UnSelectShapeCommand implements Command{
	private Shape shape;
	private List<Shape> selectedShapes;
	
	public UnSelectShapeCommand(Shape shape, List<Shape> selectedShapes) {
		this.shape = shape;
		this.selectedShapes = selectedShapes;
	}
	
	@Override
	public void execute() {
		shape.setSelected(false);
		this.selectedShapes.remove(shape);
	}

	@Override
	public void unexecute() {
		shape.setSelected(true);
		this.selectedShapes.add(shape);
	}

}
