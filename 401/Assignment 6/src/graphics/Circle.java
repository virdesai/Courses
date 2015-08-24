package graphics;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.OVAL_PATTERN)
public class Circle implements CircleInterface{
	
	int x, y, width, height;
	
	public Circle(int X, int Y, int Width, int Height){
		x = X;
		y = Y;
		width = Width;
		height = Height;
	}
	
	public int getX(){return x;}
	public void setX(int newX){
		x = newX;
	}

	public int getY(){return y;}
	public void setY(int newY){
		y = newY;
	}

	public int getWidth(){return width;}
	public void setWidth(int newWidth){
		width = newWidth;
	}

	public int getHeight(){return height;}
	public void setHeight(int newHeight){
		height = newHeight;
	}

}
