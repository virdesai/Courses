package commands;

import util.annotations.Tags;

@Tags({"Repeat"})
public class Repeat implements Runnable{

	int times;
	Runnable command;

	public Repeat(int newInt, Runnable newCommand){
		times = newInt;
		command = newCommand;
	}

	public void run() {
		for (int i=0; i<times; i++){
			command.run();
		}
	}
}
