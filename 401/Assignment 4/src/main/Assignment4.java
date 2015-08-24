package main;

import util.misc.ThreadSupport;
import bus.uigen.ObjectEditor;
import bus.uigen.OEFrame;

public class Assignment4 {
	
	static String input;
	static String newInput;

	public static void main(String[] args) {
		//System.out.println("Enter numbers, words, or anything in quotes to see what each is.");
		//System.out.println("When done enter '.' at the beginning of the line to terminate the program.");

		ScannerBean scannerBean = new ScannerBean();

		OEFrame oeFrame = ObjectEditor.edit(scannerBean);

		scannerBean.setInput("MoVe 050 { saY \"hi!\" } ");
		oeFrame.refresh();
		ThreadSupport.sleep(3000);
		scannerBean.setInput("RotateLeftArm 5 rotateLeftArm ");
		oeFrame.refresh();
		ThreadSupport.sleep(3000);

		//System.out.println(Arrays.toString(scannerBean.getCommon()));
		oeFrame.refresh();
		
		Line line = new Line();
		OEFrame oeFrame2 = ObjectEditor.edit(line);

		line.setLocation(line.getWidth(),line.getHeight());
		line.rotation(0);
		oeFrame.refresh();
		ThreadSupport.sleep(2000);
		line.rotation(35);
		oeFrame2.refresh();
		ThreadSupport.sleep(2000);
		line.rotation(5);
		oeFrame2.refresh();
		ThreadSupport.sleep(2000);
		line.rotation(5);
		oeFrame2.refresh();
		ThreadSupport.sleep(2000);
		line.rotation(5);
		oeFrame2.refresh();

	}

	/*static String readIn() {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		newInput = scanner.nextLine();

		return newInput;

	}*/

}
