package graphics;

import java.beans.PropertyChangeListener;

import util.annotations.Tags;

@Tags({"Bounded Shape"})
public interface ShapeInterface extends util.models.PropertyListenerRegisterer{
	
	public int getX(); 
	public int getY(); 
	public void setX(int newX);
	public void setY(int newY);
	public void addPropertyChangeListener(PropertyChangeListener listener);
	public int getWidth();
	public int getHeight();

}