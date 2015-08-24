package graphics;

import util.annotations.Explanation;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.POINT_PATTERN)
@Explanation("Uses Cartesian representation.")
public class Point implements PointInterface{
	protected int x, y;
	public Point(int theX, int theY) {
		x = theX;
		y = theY;
	}

	public int getX() {
		return x;
		}
	public int getY() {
		return y;
	}
	public void setX(int newX) {
		x = newX;
		}
	public void setY(int newY) {
		y = newY;
		}

}
