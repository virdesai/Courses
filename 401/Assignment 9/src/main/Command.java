package main;

import tokens.ApproachCT;
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

	Screen screen = Assignment9.screen;
	Avatar arthur = Assignment9.screen.getArthur();
	Avatar galahad = Assignment9.screen.getGalahad();
	Avatar guard = Assignment9.screen.getGuard();
	Avatar lancelot = Assignment9.screen.getLancelot();
	Avatar robin = Assignment9.screen.getRobin();

	public String getCommand(){
		return command;
	}

	public void setCommand(String newCommand){
		Assignment9.scannerBean.setInput(newCommand);
		Common[] common = Assignment9.scannerBean.getCommon();
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
			}else if(common[i] instanceof ApproachCT && common[i+1] instanceof Word){
				if(table.getKey().get(i+1).contains("arthur")){
					screen.Approach(arthur);
					i+=2;
				}else if(table.getKey().get(i+1).contains("galahad")){
					screen.Approach(galahad);
					i+=2;
				}else if(table.getKey().get(i+1).contains("lancelot")){
					screen.Approach(lancelot);
					i+=2;
				}else if(table.getKey().get(i+1).contains("robin")){
					screen.Approach(robin);
					i+=2;
				}
			}
		}
	}
}
