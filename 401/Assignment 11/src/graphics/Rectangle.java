package graphics;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.RECTANGLE_PATTERN)
public class Rectangle extends Shape implements RectangleInterface {

	public Rectangle(int X, int Y, int Width, int Height) {
		super(X, Y);
		//x = X;
		//y = Y;
		width = Width;
		height = Height;
	}
}
