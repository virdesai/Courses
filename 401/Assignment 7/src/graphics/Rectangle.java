package graphics;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.RECTANGLE_PATTERN)
public class Rectangle extends Shape implements RectangleInterface {

	//int x, y, width, height;

	public Rectangle(int X, int Y, int Width, int Height) {

		x = X;
		y = Y;
		width = Width;
		height = Height;
	}

	public void setWidth(int newWidth) {
		width = newWidth;
	}

	public void setHeight(int newHeight) {
		height = newHeight;
	}

}
