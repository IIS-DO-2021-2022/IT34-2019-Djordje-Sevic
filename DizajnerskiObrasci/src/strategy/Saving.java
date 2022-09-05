package strategy;

import java.io.File;
import java.util.List;

import geometry.Shape;

public interface Saving {
	void save(List<Shape> shapes, File file);
}
