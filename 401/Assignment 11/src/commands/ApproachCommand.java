package commands;

import util.annotations.Tags;
import graphics.Avatar;
import graphics.Screen;

@Tags({"approach command"})
public class ApproachCommand implements Runnable{

	Avatar avatar;
	Screen screen;

	public ApproachCommand(Avatar newAvatar, Screen newScreen) {
		avatar = newAvatar;
		screen = newScreen;
	}

	public void run() {
		screen.Approach(avatar);
	}
}