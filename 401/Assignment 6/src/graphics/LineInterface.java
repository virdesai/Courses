package graphics;

public interface LineInterface {
	
	public int getX();
	public void setX(int newX);
	public int getY();
	public void setY(int newY);
	public void setPoint(Point newLocation);
	public void setPolarPoint(PolarPoint newPoint);
	public void setRadius(double newRadius);
	public double getRadius();
	public void rotation(int units);
	public double getAngle();
	public void setAngle(double newAngle);
    public int getWidth();
    public int getHeight();

}
