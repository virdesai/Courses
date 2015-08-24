package commands;

import util.annotations.Tags;
import graphics.Screen;

@Tags({"pass command"})
public class PassCommand implements Runnable{

	Screen screen;
	
	public PassCommand(Screen newScreen) {
		screen = newScreen;
	}

	public void run() {
		screen.Passed();
	}
}
