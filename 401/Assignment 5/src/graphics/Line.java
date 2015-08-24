package graphics;

import util.annotations.Tags;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.LINE_PATTERN)
@Tags({"Rotating Line"})
public class Line implements LineInterface {
	
int x, y;
double radius, angle;
int width, height;
Point point;
PolarPoint polarPoint;

	public Line (Point initPoint, PolarPoint polarCord) {
		x = initPoint.getX();
		y = initPoint.getY();
		angle = polarCord.getAngle();
		radius = polarCord.getRadius();

		}

    public int getX() {
    	return x;
    	}
    public void setX(int newX) {
    	x = newX;
    	}
    public int getY() {
    	return y;
    	}
    public void setY(int newY) {
    	y = newY;
    	}
    public int getWidth() {
    	width = (int)(radius*Math.cos(angle));
    	return width;
    	}
    public int getHeight() {
    	height = (int)(radius*Math.sin(angle));
    	return height;
    	}
    public double getAngle() {
    	return angle;
    	}
    public void setAngle(double newAngle) {
    	angle += newAngle;
    	setY((int)(radius*Math.sin(angle)));
    	setX((int)(radius*Math.cos(angle)));
    	polarPoint = new PolarPoint(angle,getRadius());
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
    }

    public void setPoint(Point newPoint){
    	point = newPoint;
    }

    public Point getPoint() {
    	return point;
    }
    
    public void setPolarPoint(PolarPoint newPoint){
    	polarPoint = newPoint;
    	setAngle(polarPoint.getAngle());
    	setRadius(polarPoint.getRadius());
    }
    
    public PolarPoint getPolarPoint() {
    	return polarPoint;
    }

}