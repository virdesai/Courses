package main;

import util.misc.ThreadSupport;
import graphics.Screen;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

public class Assignment5{
	
	static String input;
	static String newInput;

	public static void main(String[] args) {

		Screen screen = new Screen();
		OEFrame oeFrame = ObjectEditor.edit(screen);
		oeFrame.refresh();
		screen.getArthurLegAngle().getLeftLine().rotation(2);
		ThreadSupport.sleep(2000);
		oeFrame.refresh();
		screen.moveAll(50, 75);
		ThreadSupport.sleep(2000);
		oeFrame.refresh();
	}

}