package commands;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JTextField;

public class CommandView implements PropertyChangeListener {

	JTextField input;

	public CommandView(JTextField newInput) {
		input = newInput;
	}

	public void propertyChange(PropertyChangeEvent evt) {
		input.setText(evt.getNewValue().toString());
	}
}
