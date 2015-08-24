package graphics;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.OVAL_PATTERN)
public class Circle extends Shape implements CircleInterface{
	
	public Circle(int X, int Y, int Width, int Height){
		super(X, Y);
		width = Width;
		height = Height;
	}
}
