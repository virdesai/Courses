package graphics;

import util.annotations.Tags;

@Tags({"Bounded Shape"})
public class Shape extends Location implements ShapeInterface{

	int width,height;
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}

}
