package graphics;

import java.beans.PropertyChangeListener;
import util.annotations.Tags;

@Tags({"Locatable"})
public interface LocationInterface extends util.models.PropertyListenerRegisterer{
	public int getX(); 
	public int getY(); 
	public void setX(int newX);
	public void setY(int newY);
	public void addPropertyChangeListener(PropertyChangeListener listener);

}
