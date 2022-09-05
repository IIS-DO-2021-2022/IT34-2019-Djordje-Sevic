package command;

import java.util.List;

import geometry.Shape;
import mvc.DrawingModel;

public class RemoveShapeCommand implements Command {
	
	private Shape shape;
	private DrawingModel drawingModel;
	private List<Shape> selectedShapes;
	
	public RemoveShapeCommand(Shape shape, DrawingModel drawingModel, List<Shape> selectedShapes) {
		this.shape = shape;
		this.drawingModel = drawingModel;
		this.selectedShapes = selectedShapes;
	}
	@Override
	public void execute() {
		if(shape.isSelected())selectedShapes.remove(shape);
		drawingModel.remove(shape);
		
	}

	@Override
	public void unexecute() {
		drawingModel.add(shape);
		if(shape.isSelected()) selectedShapes.add(shape);
	}

}