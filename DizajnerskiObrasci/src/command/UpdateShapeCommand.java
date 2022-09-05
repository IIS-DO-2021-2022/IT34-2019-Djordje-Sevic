package command;

import geometry.Shape;
import mvc.DrawingModel;

public class UpdateShapeCommand implements Command{
	private Shape oldState;
	private Shape newState;
	private Shape original;
	private DrawingModel drawingModel;
	private int index;
	
	public UpdateShapeCommand(Shape oldState, Shape newState, DrawingModel drawingModel, int index) {
		this.oldState = oldState;
		this.newState = newState;
		this.drawingModel = drawingModel;
		this.index = index;
	}

	@Override
	public void execute() {
		original = oldState.clone();
		oldState = newState.clone();
		drawingModel.getShapes().set(index, oldState);
	}

	@Override
	public void unexecute() {
		oldState = original.clone();
		drawingModel.getShapes().set(index, oldState);
	}
}
