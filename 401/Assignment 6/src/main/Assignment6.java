package main;

import util.misc.ThreadSupport;
import graphics.Screen;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

public class Assignment6{

	public static void main(String[] args) {

		Screen screen = new Screen();
		OEFrame oeFrame = ObjectEditor.edit(screen);
		screen.Approach(screen.getGalahad());
		ThreadSupport.sleep(1000);
		oeFrame.refresh();
		screen.Say("guard test");
		ThreadSupport.sleep(1000);
		oeFrame.refresh();
		screen.Say("knight test");
		ThreadSupport.sleep(1000);
		oeFrame.refresh();
		screen.Passed();
		ThreadSupport.sleep(1000);
		oeFrame.refresh();
		screen.Approach(screen.getArthur());
		ThreadSupport.sleep(1000);
		oeFrame.refresh();
		screen.Say("guard test 2");
		ThreadSupport.sleep(1000);
		oeFrame.refresh();
		screen.Say("knight test 2");
		ThreadSupport.sleep(1000);
		oeFrame.refresh();
		screen.Failed();
		ThreadSupport.sleep(1000);
		oeFrame.refresh();
		screen.Approach(screen.getRobin());
		ThreadSupport.sleep(1000);
		oeFrame.refresh();
		screen.Approach(screen.getLancelot());
		ThreadSupport.sleep(1000);
		oeFrame.refresh();
		screen.Say("guard test 3");
		ThreadSupport.sleep(1000);
		oeFrame.refresh();
		screen.Failed();
		ThreadSupport.sleep(1000);
		oeFrame.refresh();
		screen.Say("finished!");
		ThreadSupport.sleep(1000);
		oeFrame.refresh();
		
		ScannerBean scannerBean = new ScannerBean();

		OEFrame oeFrame2 = ObjectEditor.edit(scannerBean);

		scannerBean.setInput("MoVe 050 { saY \"hi!\" } ApproaCh");
		oeFrame2.refresh();
		ThreadSupport.sleep(3000);
		scannerBean.setInput("RotateLeftArm 5 rotateLeftArm ");
		oeFrame2.refresh();
		ThreadSupport.sleep(3000);


	}

}