package command;

import java.util.Collections;

import mvc.DrawingModel;

public class BringToBackCommand implements Command{
	private DrawingModel drawingModel;
	private int index;
	
	public BringToBackCommand(DrawingModel drawingModel, int index) {
		this.drawingModel = drawingModel;
		this.index = index;
	}

	@Override
	public void execute() {
		for(int i = index; i > 0; i--) {
			Collections.swap(drawingModel.getShapes(), i, i - 1);
		}
	}

	@Override
	public void unexecute() {
		for(int i = 0; i < index; i++) {
			Collections.swap(drawingModel.getShapes(), i, i + 1);
		}
		
	}
}
