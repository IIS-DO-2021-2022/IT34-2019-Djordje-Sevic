package strategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import geometry.Shape;
import mvc.DrawingModel;

public class SaveDraw implements Saving{
	
	private DrawingModel drawingModel;
	
	public SaveDraw(DrawingModel drawingModel) {
		this.drawingModel = drawingModel;
	}
	@Override
	public void save(File file) {	
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream(file);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(drawingModel.getShapes());
	         out.close();
	         fileOut.close();
	         //System.out.printf("Serialized data is saved in /tmp/employee.ser");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}

}
