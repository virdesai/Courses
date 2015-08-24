package commands;

import graphics.Screen;

public class SayCommand implements Runnable{
	
	String say;
	Screen screen;

	public SayCommand(Screen newScreen, String string) {
		screen = newScreen;
		say = string;
	}

	public void run() {
		screen.Say(say);
	}
}