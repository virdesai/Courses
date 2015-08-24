package graphics;

public interface LineInterface extends ShapeInterface{
	
	public int getWidth();
	public int getHeight();
	public void setRadius(double newRadius);
	public double getRadius();
	public void rotation(int units);
	public double getAngle();
	public void setAngle(double newAngle);

}
