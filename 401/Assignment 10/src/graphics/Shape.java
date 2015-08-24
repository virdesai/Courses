package graphics;

import util.annotations.Tags;

@Tags({"Bounded Shape"})
public class Shape extends Location implements ShapeInterface{

	protected int width,height;
	
	public Shape(int newX, int newY) {
		super(newX, newY);
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
}
