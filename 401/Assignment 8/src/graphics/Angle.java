package graphics;

import util.annotations.IsAtomicShape;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@IsAtomicShape(false)
@Tags({"Angle"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public class Angle extends Location implements AngleInterface {
	Line leftLine;
	Line rightLine;

	public Angle(int newX, int newY, double newAngle, double newRadius) {
		super(newX, newY);
		leftLine = new Line(x, y, newAngle+Math.PI/2, newRadius);
		rightLine = new Line(x, y, newAngle, newRadius);
	}

	public Line getRightLine() {
		return rightLine;
	}
	public void setRightLine(int newX, int newY, double newAngle, double newRadius) {
		rightLine.setX(newX);
		rightLine.setY(newY);
		rightLine.angle=newAngle;
		rightLine.radius=newRadius;
	}
	public Line getLeftLine() {
		return leftLine;
	}
	public void setLeftLine(int newX, int newY, double newAngle, double newRadius) {
		double leftAngle = newAngle+Math.PI/2;
		leftLine.setX(newX);
		leftLine.setY(newY);
		leftLine.angle=leftAngle;
		leftLine.radius=newRadius;
	}
}
