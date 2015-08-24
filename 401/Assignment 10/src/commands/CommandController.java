package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import main.Command;

public class CommandController implements ActionListener{

	JTextField input;
	Command command;

	public CommandController(Command newCommand, JTextField newInput) {
		input = newInput;
		command = newCommand;
		input.addActionListener(this);
	}
	public void actionPerformed(ActionEvent event) {
		JTextField source = (JTextField) event.getSource();
		String text = source.getText();
		command.setCommand(text);
	}
}
