package graphics;

import util.annotations.Explanation;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.POINT_PATTERN)
@Explanation("Polar Location in Java coordinate System.")
public interface PolarPointInterface {
	public double getAngle(); 
	public double getRadius(); 
	public void setAngle(double newAngle);
	public void setRadius(double newRadius);

}
