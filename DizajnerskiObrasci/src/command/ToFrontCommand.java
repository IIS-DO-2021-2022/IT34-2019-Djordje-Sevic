package command;

import java.util.Collections;

import mvc.DrawingModel;

public class ToFrontCommand implements Command{
	private int index;
	private DrawingModel drawingModel;
	
	public ToFrontCommand(DrawingModel drawingModel, int index) {
		this.drawingModel = drawingModel;
		this.index = index;
	}
	
	@Override
	public void execute() {
		swap();	
	}

	@Override
	public void unexecute() {
		swap();
	}
	
	private void swap() {
		Collections.swap(drawingModel.getShapes(), index, index + 1);
	}

}
