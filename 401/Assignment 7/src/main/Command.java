package main;

import tokens.Number;
import tokens.Common;
import tokens.MoveCT;
import tokens.Quote;
import tokens.SayCT;
import tokens.Table;
import tokens.Word;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import graphics.Avatar;
import graphics.Screen;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@Tags({"Command Interpreter"})
public class Command {
	
	static String command;
	
	Table table = ScanString.table;

	Screen screen = Assignment7.screen;
	Avatar arthur = Assignment7.screen.getArthur();
	Avatar galahad = Assignment7.screen.getGalahad();
	Avatar guard = Assignment7.screen.getGuard();
	Avatar lancelot = Assignment7.screen.getLancelot();
	Avatar robin = Assignment7.screen.getRobin();

	public String getCommand(){
		return command;
	}
	
	public void setCommand(String newCommand){
		Assignment7.scannerBean.setInput(newCommand);
		Common[] common = Assignment7.scannerBean.getCommon();
		int size = common.length;
		for(int i=0; i<size;){
			if(common[i] instanceof SayCT && common[i+1] instanceof Quote){
				screen.Say(String.valueOf(table.getKey().get(i+1)));
				i+=2;
			}else if(common[i] instanceof MoveCT && common[i+1] instanceof Word && common[i+2] instanceof Number && common[i+3] instanceof Number){
				if(table.getKey().get(i+1).contains("arthur")){
					arthur.move(Integer.valueOf(table.getKey().get(i+2)), Integer.valueOf(table.getKey().get(i+3)));
					i+=4;
				}else if(table.getKey().get(i+1).contains("galahad")){
					galahad.move(Integer.valueOf(table.getKey().get(i+2)), Integer.valueOf(table.getKey().get(i+3)));
					i+=4;
				}else if(table.getKey().get(i+1).contains("guard")){
					guard.move(Integer.valueOf(table.getKey().get(i+2)), Integer.valueOf(table.getKey().get(i+3)));
					i+=4;
				}else if(table.getKey().get(i+1).contains("lancelot")){
					lancelot.move(Integer.valueOf(table.getKey().get(i+2)), Integer.valueOf(table.getKey().get(i+3)));
					i+=4;
				}else if(table.getKey().get(i+1).contains("robin")){
					robin.move(Integer.valueOf(table.getKey().get(i+2)), Integer.valueOf(table.getKey().get(i+3)));
					i+=4;
				}
			}
		}
	}

}
