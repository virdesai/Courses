package main;

import tokens.Table;
import util.misc.ThreadSupport;
import graphics.Screen;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

public class Assignment7{
	
	static Screen screen = new Screen();
	static Command command = new Command();
	static ScannerBean scannerBean = new ScannerBean();

	static OEFrame scannerFrame = ObjectEditor.edit(scannerBean);
	static OEFrame screenFrame = ObjectEditor.edit(screen);
	static OEFrame commandFrame = ObjectEditor.edit(command);

	public static void main(String[] args) {

		Object one = true,two = false,three = true,four = false,five = true;

		command.setCommand("move Arthur 20 30");

		ThreadSupport.sleep(3000);
		commandFrame.refresh();
		scannerFrame.refresh();
		screenFrame.refresh();

		screen.Approach(command.arthur);

		ThreadSupport.sleep(2000);
		commandFrame.refresh();
		scannerFrame.refresh();
		screenFrame.refresh();

		command.setCommand("say \"This is a test of the say command!\"");

		ThreadSupport.sleep(2000);
		commandFrame.refresh();
		scannerFrame.refresh();
		screenFrame.refresh();
		
		command.setCommand("say \"test 2!\"");
		
		ThreadSupport.sleep(2000);
		commandFrame.refresh();
		scannerFrame.refresh();
		screenFrame.refresh();
		
		Table table = new Table();
		table.put("six", one);
		table.put("seven", two);
		table.put("eight", three);
		table.put("nine", four);
		table.put("ten", five);
		
		System.out.println("Table 1");
		System.out.println(table.key);
		System.out.println(table.value);
		
		table.put("eight", two);
		System.out.println("Table 2");
		System.out.println(table.key);
		System.out.println(table.value);
		
		System.out.println("Table 3");
		table.put("eleven", three);
		System.out.println(table.key);
		System.out.println(table.value);
		System.out.println("Test failure");
		System.out.println(table.get("tacos"));

	}

}