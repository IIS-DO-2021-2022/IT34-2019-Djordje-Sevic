package command;

import java.util.List;

import geometry.Shape;
import mvc.DrawingModel;

public class AddShapeCommand implements Command {
	
	private Shape shape;
	private DrawingModel drawingModel;
	//private List<Shape> selectedShapes;
	
	public AddShapeCommand(Shape shape, DrawingModel drawingModel) {
		this.shape = shape;
		this.drawingModel = drawingModel;
	}
	@Override
	public void execute() {		
		drawingModel.add(shape);
	}

	@Override
	public void unexecute() {
		drawingModel.remove(shape);
	}

}
