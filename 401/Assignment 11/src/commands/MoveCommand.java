package commands;

import graphics.Avatar;

public class MoveCommand implements Runnable{

	Avatar avatar;
	int x, y;

	public MoveCommand(Avatar newAvatar, int newX, int newY) {
		avatar = newAvatar;
		x = newX;
		y = newY;
	}

	public void run() {
		avatar.move(x,y);
	}
}