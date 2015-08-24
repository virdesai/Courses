package main;

import util.annotations.Tags;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.LINE_PATTERN)
@Tags({"Rotating Line"})
public class Line implements LineProperty {
	
int x, y;
double radius=75, angle;
int width, height;
int rightX, rightY;
    
	/*public void ALine (int initX, int initY, int initWidth, int initHeight) {
	    x = initX; 
	    y = initY;
	    width = initWidth;
	    height = initHeight;
	    }*/

    public int getX() {
    	x = Integer.valueOf(rightX) - (int)(radius*Math.cos(angle));
    	return x;
    	}
    public void setX(int newX) {
    	x = newX;
    	}
    public int getY() {
    	y = Integer.valueOf(rightY) - (int)(radius*Math.sin(angle));
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
    	setY(Integer.valueOf(rightY) - (int)(radius*Math.sin(angle)));
    	setX(Integer.valueOf(rightX) - (int)(radius*Math.cos(angle)));
    }
    
    public void setLocation(int xLocation, int yLocation){
    	rightX = (Integer.valueOf(xLocation)+(int)(radius*Math.cos(angle)));
    	rightY = (Integer.valueOf(yLocation)+(int)(radius*Math.sin(angle)));
    }

}
