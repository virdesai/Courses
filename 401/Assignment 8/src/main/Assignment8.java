package main;

import util.misc.ThreadSupport;
import graphics.Screen;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

public class Assignment8 {
	
	static Screen screen = new Screen();
	static Command command = new Command();
	static ScannerBean scannerBean = new ScannerBean();
	static ConsoleSceneView csv = new ConsoleSceneView(screen);
	static OEFrame screenFrame = ObjectEditor.edit(screen);

	public static void main(String[] args) {

		ThreadSupport.sleep(2000);
		command.setCommand("move ArTHur 20 30");
		ThreadSupport.sleep(2000);
		command.screen.Approach(screen.arthur);
		ThreadSupport.sleep(2000);
		command.setCommand("say \"This is a test of the say command!\"");
		ThreadSupport.sleep(2000);
		command.setCommand("say \"test 2!\"");
		ThreadSupport.sleep(2000);
		command.screen.getArthur().getArms().getLeftLine().rotation(5);
		ThreadSupport.sleep(1000);
		command.screen.getArthur().getArms().getRightLine().rotation(5);
		ThreadSupport.sleep(1000);
		command.screen.getArthur().getLegs().getLeftLine().rotation(5);
		ThreadSupport.sleep(1000);
		command.screen.getArthur().getLegs().getRightLine().rotation(5);
		ThreadSupport.sleep(1000);
		command.screen.getArthur().getArms().getLeftLine().rotation(-5);
		ThreadSupport.sleep(1000);
		command.screen.getArthur().getArms().getRightLine().rotation(-5);
		ThreadSupport.sleep(1000);
		command.screen.getArthur().getLegs().getLeftLine().rotation(-5);
		ThreadSupport.sleep(1000);
		command.screen.getArthur().getLegs().getRightLine().rotation(-5);
		ThreadSupport.sleep(1000);
	}
}