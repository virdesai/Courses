package commands;

import util.annotations.Tags;
import graphics.Screen;

@Tags({"fail command"})
public class FailCommand implements Runnable{

	Screen screen;
	
	public FailCommand(Screen newScreen){
		screen = newScreen;
	}

	public void run() {
		screen.Failed();
	}
}
