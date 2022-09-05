package command;

import java.util.Collections;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToFrontCommand implements Command{
	private DrawingModel drawingModel;
	private int index;
	
	public BringToFrontCommand(DrawingModel drawingModel, int index) {
		this.drawingModel = drawingModel;
		this.index = index;
	}

	@Override
	public void execute() {
		for(int i = index; i < drawingModel.getShapes().size() - 1; i++) {
			Collections.swap(drawingModel.getShapes(), i, i + 1);
		}
	}

	@Override
	public void unexecute() {
		for(int i = drawingModel.getShapes().size() - 1; i > index; i--) {
			Collections.swap(drawingModel.getShapes(), i, i - 1);
		}
		
	}
}
