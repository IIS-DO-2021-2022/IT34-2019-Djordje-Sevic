package application;

import mvc.DrawingController;
import mvc.DrawingFrame;
import mvc.DrawingModel;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DrawingModel drawModel = new DrawingModel();
		DrawingFrame drawFrame = new DrawingFrame();
		drawFrame.getDrawingView().setDrawingModel(drawModel);
		DrawingController drawController = new DrawingController(drawModel, drawFrame);
		drawFrame.setDrawingController(drawController);
		drawController.addPropertyChangeListener(drawFrame);
	}

}
