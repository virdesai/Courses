package graphics;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.annotations.Tags;

@Tags({"Locatable"})
public abstract class Location implements LocationInterface{

	PropertyListenerInterface propertyListener = new PropertyListenerClass();
	protected int x,y;

	public Location(int newX, int newY){
		setX(newX);
		setY(newY);
	}

	public int getX() {
		return x;
		}
	public int getY() {
		return y;
	}
	public void setX(int newX) {
		int oldX = Integer.valueOf(x);
		x = newX;
		propertyListener.notifyAllListeners(new PropertyChangeEvent(this, "x", oldX, newX));
		}
	public void setY(int newY) {
		int oldY = Integer.valueOf(y);
		y = newY;
		propertyListener.notifyAllListeners(new PropertyChangeEvent(this, "y", oldY, newY));
		}

	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyListener.addElement(listener);
	}

}
