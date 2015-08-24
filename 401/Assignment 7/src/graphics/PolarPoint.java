package graphics;

import util.annotations.Explanation;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@Explanation("Uses Polar representation.")
public class PolarPoint {

	protected double angle, radius;

	public PolarPoint(double newAngle, double newRadius) {
		angle = newAngle;
		radius = newRadius;
	}

	public double getAngle() {
		return angle;
		}
	public double getRadius() {
		return radius;
	}
	public void setAngle(double newAngle) {
		angle = newAngle;
	}
	public void setRadius(double newRadius) {
		radius = newRadius;
	}

}
