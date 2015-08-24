package graphics;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public interface PropertyListenerInterface {
	
	public int size();
	public PropertyChangeListener elementAt(int index);
	public boolean isFull();
	public void addElement(PropertyChangeListener l);
	public void notifyAllListeners(PropertyChangeEvent event);

}
