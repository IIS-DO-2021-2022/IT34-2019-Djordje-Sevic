package strategy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import geometry.Shape;
import mvc.DrawingFrame;

public class SaveLog implements Saving{
	
	private DrawingFrame drawingFrame;
	
	public SaveLog(DrawingFrame drawingFrame) {
		this.drawingFrame = drawingFrame;
	}
	
	@Override
	public void save(File file) {
		try {
			FileWriter fw = new FileWriter(file);
			fw.write(drawingFrame.getTxtAreaLogger().getText());
			fw.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
		
	}

}
