package commands;

import graphics.PropertyListenerClass;
import graphics.PropertyListenerInterface;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.models.PropertyListenerRegisterer;

public class CommandObserver implements PropertyListenerRegisterer {

	PropertyListenerInterface propertyListener = new PropertyListenerClass();
	protected String command;
	
	public String getCommand() {
		return command;
	}
	
	public void setCommand(String newCommand) {
		String oldCommand = String.valueOf(getCommand());
		command = newCommand;
		propertyListener.notifyAllListeners(new PropertyChangeEvent(this, "command", oldCommand, newCommand));
	}

	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyListener.addElement(listener);
	}
}