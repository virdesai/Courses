package graphics;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PropertyListenerClass implements PropertyListenerInterface{

	public final int MAX_SIZE = 50;
	PropertyChangeListener[] contents = new PropertyChangeListener[MAX_SIZE];
	int size = 0;
	
	public int size() {
		return size;
	}
	public PropertyChangeListener elementAt(int index) {
		return contents[index];
	}
	public boolean isFull() {
		return size == MAX_SIZE;
	}
	public void addElement(PropertyChangeListener l) {
		if (isFull()) {
			System.out.println("Adding to full history");
		}else{
			contents[size]=l;
			size++;
		}
	}
	public void notifyAllListeners(PropertyChangeEvent event) {
		for (int index = 0; index < size(); index++) {
			elementAt(index).propertyChange(event);
			}
	}
}
