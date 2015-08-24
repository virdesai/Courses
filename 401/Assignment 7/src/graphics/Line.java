package graphics;

import util.annotations.Tags;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.LINE_PATTERN)
@Tags({"Rotating Line"})
public class Line extends Location implements LineInterface {
	
int height, width;
double radius, angle;
Point point;
PolarPoint polarPoint;

	public Line (Point initPoint, PolarPoint polarCord) {
		x = initPoint.getX();
		y = initPoint.getY();
		angle = polarCord.getAngle();
		radius = polarCord.getRadius();

		}
	
	public int getWidth(){
		width = (int)(radius*Math.cos(angle));
		return width;
	}
	
	public int getHeight(){
		height = (int)(radius*Math.sin(angle));
		return height;
	}
    public double getAngle() {
    	return angle;
    	}
    public void setAngle(double newAngle) {
    	angle += newAngle;
    	height = (int)(radius*Math.sin(angle));
    	width = (int)(radius*Math.cos(angle));
    }

    @Tags({"rotate"})
    public void rotation(int units) {
    	setAngle(units*(Math.PI/32));
    }
    public double getRadius() {
    	return radius;
    	}
    public void setRadius(double newRadius) {
    	radius = newRadius;
    	setY(Integer.valueOf(point.getY()) - (int)(radius*Math.sin(angle)));
    	setX(Integer.valueOf(point.getX()) - (int)(radius*Math.cos(angle)));
    	polarPoint = new PolarPoint(getAngle(),radius);
    	width = (int)(radius*Math.cos(angle));
    	height = (int)(radius*Math.sin(angle));
    }

    public void setPoint(Point newPoint){
    	point = newPoint;
    }
    
    public void setPolarPoint(PolarPoint newPoint){
    	polarPoint = newPoint;
    	setAngle(polarPoint.getAngle());
    	setRadius(polarPoint.getRadius());
    }

}