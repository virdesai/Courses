package graphics;

import util.annotations.IsAtomicShape;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@IsAtomicShape(false)
@Tags({"Angle"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public class Angle implements AngleInterface{
	Line leftLine;
	Line rightLine;
	Point point;
	
	public Angle(Point joint, PolarPoint polar) {
		setPoint(joint);
		setLeftLine(polar);
		setRightLine(polar);
	}
	
	public Line getLeftLine() {
		return leftLine;
	}
	public void setLeftLine(PolarPoint mainValues) {
		double leftAngle = mainValues.getAngle()+Math.PI/2;
		double leftRadius = mainValues.getRadius();
		PolarPoint newPolar = new PolarPoint(leftAngle,leftRadius);
		leftLine = new Line(point, newPolar);
	}
	public Line getRightLine() {
		return rightLine;
	}
	public void setRightLine(PolarPoint mainValues) {
		rightLine = new Line(point, mainValues);
	}
	public Point getPoint(){
		return point;
	}
	public void setPoint(Point newPoint) {
		point = newPoint;
	}

}
