package graphics;

import java.beans.PropertyChangeEvent;

import util.annotations.Tags;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.LINE_PATTERN)
@Tags({"Rotating Line"})
public class Line extends Shape implements LineInterface {

double radius, angle;

	public Line (int newX, int newY , double newAngle, double newRadius) {
		super(newX, newY);
		setAngle(newAngle);
		setRadius(newRadius);
		}

	@Override
	public int getWidth(){
		width = (int)(radius*Math.cos(angle));
		return width;
	}
	@Override
	public int getHeight(){
		height = (int)(radius*Math.sin(angle));
		return height;
	}
    public double getAngle() {
    	return angle;
    	}
    public void setAngle(double newAngle) {
    	double oldAngle = angle;
    	angle += newAngle;
    	propertyListener.notifyAllListeners(new PropertyChangeEvent(this, "angle", oldAngle, angle));
    }

    @Tags({"rotate"})
    public void rotation(int units) {
    	setAngle(units*(Math.PI/32));
    }
    public double getRadius() {
    	return radius;
    	}
    public void setRadius(double newRadius) {
    	double oldRadius = radius;
    	radius = newRadius;
    	propertyListener.notifyAllListeners(new PropertyChangeEvent(this, "radius", oldRadius, radius));
    }
}