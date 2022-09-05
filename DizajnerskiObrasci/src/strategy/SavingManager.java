package strategy;

import java.io.File;
import java.util.List;

import geometry.Shape;

public class SavingManager implements Saving{
	private Saving saving;
	
	public SavingManager(Saving saving) {
		this.saving = saving;
	}
	@Override
	public void save(List<Shape> shapes, File file) {
		saving.save(shapes, file);		
	}

}
