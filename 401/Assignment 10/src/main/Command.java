package main;

import animation.ScreenAnimationCommand;
import animation.ScreenAnimator;
import commands.ApproachCT;
import commands.MoveCT;
import commands.MoveCommand;
import commands.SayCT;
import commands.SayCommand;
import tokens.Number;
import tokens.Common;
import tokens.Quote;
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
	
	SayCommand sayCommand;
	MoveCommand moveCommand;
	
	Table table = ScanString.table;

	Screen screen = Assignment10.screen;
	Avatar arthur = Assignment10.screen.getArthur();
	Avatar galahad = Assignment10.screen.getGalahad();
	Avatar guard = Assignment10.screen.getGuard();
	Avatar lancelot = Assignment10.screen.getLancelot();
	Avatar robin = Assignment10.screen.getRobin();
	
	ScreenAnimator arthurAnimator, galahadAnimator, lancelotAnimator, robinAnimator;
	ScreenAnimationCommand arthurCommand, galahadCommand, lancelotCommand, robinCommand;

	public String getCommand(){
		return command;
	}

	@Tags({"say parser"})
	SayCommand sayParser(Common[] array) {
			if(array[0] instanceof SayCT && array[1] instanceof Quote){
				sayCommand = new SayCommand(screen, String.valueOf(table.getKey().get(1)));
				return sayCommand;
			}
			return null;
	}
	
	void sayProcess() {
		sayCommand.run();
	}

	@Tags({"move parser"})
	MoveCommand moveParser(Common[] array) {
		if(array[0] instanceof MoveCT && array[1] instanceof Word && array[2] instanceof Number && array[3] instanceof Number){
			if(table.getKey().get(1).contains("arthur")){
				moveCommand = new MoveCommand(arthur,Integer.valueOf(table.getKey().get(2)),Integer.valueOf(table.getKey().get(3)));
				return moveCommand;
			}else if(table.getKey().get(1).contains("galahad")){
				moveCommand = new MoveCommand(galahad,Integer.valueOf(table.getKey().get(2)),Integer.valueOf(table.getKey().get(3)));
				return moveCommand;
			}else if(table.getKey().get(1).contains("guard")){
				moveCommand = new MoveCommand(guard,Integer.valueOf(table.getKey().get(2)),Integer.valueOf(table.getKey().get(3)));
				return moveCommand;
			}else if(table.getKey().get(1).contains("lancelot")){
				moveCommand = new MoveCommand(lancelot,Integer.valueOf(table.getKey().get(2)),Integer.valueOf(table.getKey().get(3)));
				return moveCommand;
			}else if(table.getKey().get(1).contains("robin")){
				moveCommand = new MoveCommand(robin,Integer.valueOf(table.getKey().get(2)),Integer.valueOf(table.getKey().get(3)));
				return moveCommand;
			}
		}
		return null;
	}

	void moveProcess() {
		moveCommand.run();
	}

	public void setCommand(String newCommand){
		Assignment10.scannerBean.setInput(newCommand);
		Common[] common = Assignment10.scannerBean.getCommon();
		if (sayParser(common) != null) {
			sayProcess();
		}else if(moveParser(common) != null){
			moveProcess();
		}else if(common[0] instanceof ApproachCT && common[1] instanceof Word){
				if(table.getKey().get(1).contains("arthur")){
					screen.Approach(arthur);
				}else if(table.getKey().get(1).contains("galahad")){
					screen.Approach(galahad);
				}else if(table.getKey().get(1).contains("lancelot")){
					screen.Approach(lancelot);
				}else if(table.getKey().get(1).contains("robin")){
					screen.Approach(robin);
				}
			}
		}

	@Tags({"asynchronous Arthur"})
	void aSyncArthur() {
		arthurAnimator = new ScreenAnimator(arthur, 100,100);
		arthurCommand = new ScreenAnimationCommand(arthurAnimator);
		Thread arthurThread = new Thread(arthurCommand, "arthur");
		arthurThread.start();
	}
	@Tags({"asynchronous Galahad"})
	void aSyncGalahad() {
		galahadAnimator = new ScreenAnimator(galahad, 100,100);
		galahadCommand = new ScreenAnimationCommand(galahadAnimator);
		Thread galahadThread = new Thread(galahadCommand, "galahad");
		galahadThread.start();
	}
	@Tags({"asynchronous Lancelot"})
	void aSyncLancelot() {
		lancelotAnimator = new ScreenAnimator(lancelot, 100,100);
		lancelotCommand = new ScreenAnimationCommand(lancelotAnimator);
		Thread lancelotThread = new Thread(lancelotCommand, "lancelot");
		lancelotThread.start();
	}
	@Tags({"asynchronous Robin"})
	void aSyncRobin() {
		robinAnimator = new ScreenAnimator(robin, 100,100);
		robinCommand = new ScreenAnimationCommand(robinAnimator);
		Thread robinThread = new Thread(robinCommand, "robin");
		robinThread.start();
	}
}
