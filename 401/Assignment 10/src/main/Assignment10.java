package main;

import java.awt.Component;
import java.awt.GridLayout;
import java.beans.PropertyChangeListener;
import javax.swing.JFrame;
import javax.swing.JTextField;

import commands.CommandController;
import commands.CommandObserver;
import commands.CommandView;

import util.misc.ThreadSupport;
import graphics.Screen;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

public class Assignment10 {
	
	static Screen screen = new Screen();
	static Command command = new Command();
	static ScannerBean scannerBean = new ScannerBean();
	static ConsoleSceneView csv = new ConsoleSceneView(screen);
	static OEFrame screenFrame = ObjectEditor.edit(screen);

	static JFrame frame = new JFrame("Command");
	static JTextField input = new JTextField();

	public static void main(String[] args) {
		
		composeFrame();
		composeMVC();
		
		PropertyChangeListener view = new View(screen);
		
		JFrame frame2 = new JFrame("View");
		
		frame2.add((Component) view);
		frame2.setSize(1350,700);
		frame2.setVisible(true);

		command.aSyncArthur();
		command.aSyncGalahad();
		command.aSyncLancelot();
		command.aSyncRobin();
		ThreadSupport.sleep(3500);

		command.setCommand("approach galahad");
		ThreadSupport.sleep(1000);
		command.setCommand("say \"guard test\"");
		ThreadSupport.sleep(1000);
		command.setCommand("say \"knight test\"");
		ThreadSupport.sleep(1000);
		command.screen.Passed();
		ThreadSupport.sleep(1000);
		command.setCommand("approach arthur");
		ThreadSupport.sleep(1000);
		command.screen.Say("say test");
		ThreadSupport.sleep(1000);
		command.screen.Say("say test 2");
		ThreadSupport.sleep(1000);
		command.screen.Failed();
		ThreadSupport.sleep(1000);
		command.screen.Say("say test 3");
		ThreadSupport.sleep(1000);

	}

	public static void composeFrame() {
		frame.setLayout(new GridLayout(1,1));
		frame.add(input);
		frame.setSize(300,100);
		frame.setVisible(true);
	}

	public static void composeMVC() {
		CommandObserver commandObserver = new CommandObserver();
		new CommandController(command, input);
		PropertyChangeListener commandView = new CommandView(input);
		commandObserver.addPropertyChangeListener(commandView);
	}
}