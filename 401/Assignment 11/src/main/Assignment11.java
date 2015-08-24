package main;

import java.awt.Component;
import java.beans.PropertyChangeListener;
import javax.swing.JFrame;
import graphics.Screen;
import animation.BroadcastingClearanceManager;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

public class Assignment11 {
	
	static Screen screen = new Screen();
	static BroadcastingClearanceManager manager = new BroadcastingClearanceManager();
	static Command command = new Command("",manager);
	static ScannerBean scannerBean = new ScannerBean();
	static ConsoleSceneView csv = new ConsoleSceneView(screen);
	static OEFrame bcmFrame = ObjectEditor.edit(manager);
	static OEFrame commandFrame = ObjectEditor.edit(command);

	public static void main(String[] args) {

		PropertyChangeListener view = new View(screen);

		JFrame frame2 = new JFrame("View");

		frame2.add((Component) view);
		frame2.setSize(1350,700);
		frame2.setVisible(true);

		manager.waitForProceed();

		command.aSyncArthur();
		command.aSyncRobin();
		command.aSyncArthur();

		manager.waitForProceed();

		command.waitingArthur();
		command.waitingGalahad();
		command.waitingLancelot();
		command.waitingRobin();

//The following commands work. To see them better comment them out and try them yourself using the command interpreter object.

		manager.waitForProceed();
		command.setCommand("move arthur 5 10");
		manager.waitForProceed();
		command.setCommand("say \"Quest?\"");
		manager.waitForProceed();
		command.setCommand("approach arthur");
		manager.waitForProceed();
		command.setCommand("passed");
		manager.waitForProceed();
		command.setCommand("failed");
		manager.waitForProceed();
		command.setCommand("{ move Galahad 5 10 say \"Name?\" }");
		manager.waitForProceed();
		command.setCommand("repeat 5 move robin 3 4");
		manager.waitForProceed();
		command.setCommand("repeat 3 { move lancelot 3 4 move galahad 3 4 }");
		manager.waitForProceed();
		command.setCommand("repeat 3 repeat 4 { move lancelot 2 3 move robin 3 2 }");

	}
}