package commands;

import java.util.ArrayList;

import util.annotations.Tags;

@Tags({"command list"})
public class CommandList implements Runnable{

	ArrayList<Runnable> list = new ArrayList<Runnable>();
	
	@Tags({"add"})
	public void add(Runnable command) {
		list.add(command);
	}

	public void run() {
		for(int i=0; i<list.size();){
			list.get(i).run();
			i++;
		}
	}

}
