package main;

import animation.BroadcastingClearanceManager;
import animation.ScreenAnimationCommand;
import animation.ScreenAnimator;
import commands.ApproachCT;
import commands.ApproachCommand;
import commands.CommandList;
import commands.FailCT;
import commands.FailCommand;
import commands.MoveCT;
import commands.MoveCommand;
import commands.PassCT;
import commands.PassCommand;
import commands.Repeat;
import commands.RepeatCT;
import commands.SayCT;
import commands.SayCommand;
import tokens.NewTable;
import tokens.Number;
import tokens.Common;
import tokens.Quote;
import tokens.Start;
import tokens.Word;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import graphics.Avatar;
import graphics.Screen;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@Tags({"Command Interpreter"})
public class Command implements CommandInterface{
	
	static String command;
	int start=0, repeatIndex;
	
	SayCommand sayCommand;
	MoveCommand moveCommand;
	ApproachCommand approachCommand;
	PassCommand passCommand;
	FailCommand failCommand;
	CommandList commandList;
	CommandList forCLP;
	Repeat repeat;
	
	NewTable<Object> table = ScanString.table;

	Screen screen = Assignment11.screen;
	Avatar arthur = Assignment11.screen.getArthur();
	Avatar galahad = Assignment11.screen.getGalahad();
	Avatar guard = Assignment11.screen.getGuard();
	Avatar lancelot = Assignment11.screen.getLancelot();
	Avatar robin = Assignment11.screen.getRobin();
	
	ScreenAnimator arthurAnimator, galahadAnimator, lancelotAnimator, robinAnimator;
	ScreenAnimationCommand arthurCommand, galahadCommand, lancelotCommand, robinCommand;
	BroadcastingClearanceManager manager;
	
	public Command(String newCommand, BroadcastingClearanceManager newManager){
		command = newCommand;
		manager = newManager;
	}

	public String getCommand(){
		return command;
	}

	@Tags({"say parser"})
	SayCommand sayParser(Common[] array, int begin) {
			if(array[begin] instanceof SayCT && array[begin+1] instanceof Quote){
				sayCommand = new SayCommand(screen, String.valueOf(table.getKey().get(start+1)));
				start+=2;
				return sayCommand;
			}
			return null;
		}

	@Tags({"move parser"})
	MoveCommand moveParser(Common[] array, int begin) {
		if(array[begin] instanceof MoveCT && array[begin+1] instanceof Word && array[begin+2] instanceof Number && array[begin+3] instanceof Number){
			if(((String) table.getKey().get(start+1)).contains("arthur")){
				moveCommand = new MoveCommand(arthur,Integer.valueOf((String) table.getKey().get(start+2)),Integer.valueOf((String) table.getKey().get(start+3)));
				start+=4;
				return moveCommand;
			}else if(((String) table.getKey().get(start+1)).contains("galahad")){
				moveCommand = new MoveCommand(galahad,Integer.valueOf((String) table.getKey().get(start+2)),Integer.valueOf((String) table.getKey().get(start+3)));
				start+=4;
				return moveCommand;
			}else if(((String) table.getKey().get(start+1)).contains("guard")){
				moveCommand = new MoveCommand(guard,Integer.valueOf((String) table.getKey().get(start+2)),Integer.valueOf((String) table.getKey().get(start+3)));
				start+=4;
				return moveCommand;
			}else if(((String) table.getKey().get(start+1)).contains("lancelot")){
				moveCommand = new MoveCommand(lancelot,Integer.valueOf((String) table.getKey().get(start+2)),Integer.valueOf((String) table.getKey().get(start+3)));
				start+=4;
				return moveCommand;
			}else if(((String) table.getKey().get(start+1)).contains("robin")){
				moveCommand = new MoveCommand(robin,Integer.valueOf((String) table.getKey().get(start+2)),Integer.valueOf((String) table.getKey().get(start+3)));
				start+=4;
				return moveCommand;
			}
		}
		return null;
	}

	@Tags({"approach parser"})
	ApproachCommand approachParser(Common[] array, int begin) {
			if(array[begin] instanceof ApproachCT && array[begin+1] instanceof Word) {
				if(((String) table.getKey().get(start+1)).contains("arthur")){
					approachCommand = new ApproachCommand(arthur,screen);
					start+=2;
					return approachCommand;
				}else if(((String) table.getKey().get(start+1)).contains("galahad")){
					approachCommand = new ApproachCommand(galahad,screen);
					start+=2;
					return approachCommand;
				}else if(((String) table.getKey().get(start+1)).contains("lancelot")){
					approachCommand = new ApproachCommand(lancelot,screen);
					start+=2;
					return approachCommand;
				}else if(((String) table.getKey().get(start+1)).contains("robin")){
					approachCommand = new ApproachCommand(robin,screen);
					start+=2;
					return approachCommand;
				}
				return null;
				}
			return null;
		}

	@Tags({"pass parser"})
	PassCommand passParser(Common[] array) {
			if(((String) table.getKey().get(start)).contains("passed")){
				passCommand = new PassCommand(screen);
				start++;
				return passCommand;
			}
			return null;
		}

	@Tags({"fail parser"})
	FailCommand failParser(Common[] array) {
			if(((String) table.getKey().get(start)).contains("failed")){
				failCommand = new FailCommand(screen);
				start++;
				return failCommand;
			}
			return null;
		}

	@Tags({"command parser"})
	Runnable commandParser(Common[] array, int begin, CommandList newCommandList){
			if(array[begin] instanceof MoveCT){
				moveParser(array, begin);
				newCommandList.add(moveCommand);
				return moveCommand;
			}else if(array[begin] instanceof SayCT){
				sayParser(array, begin);
				newCommandList.add(sayCommand);
				return sayCommand;
			}else if(array[begin] instanceof Start){
				start++;
				newCommandList.add(commandListParser(array, start));
				return newCommandList;
			}else if(array[begin] instanceof RepeatCT){
				repeatParser(array, begin);
				newCommandList.add(repeat);
				return repeat;
			}else if(array[begin] instanceof ApproachCT){
				approachParser(array, begin);
				newCommandList.add(approachCommand);
				return approachCommand;
			}else if(array[begin] instanceof PassCT){
				passParser(array);
				newCommandList.add(passCommand);
				return passCommand;
			}else if(array[begin] instanceof FailCT){
				failParser(array);
				newCommandList.add(failCommand);
				return failCommand;
			}else {
			begin++;
			start++;
			}
			return null;
		}

	@Tags({"command list parser"})
	CommandList commandListParser(Common[] array, int begin) {
		if(array[begin] instanceof MoveCT) {
			commandParser(array,begin, forCLP);
			begin+=4;
			commandListParser(array,begin);
		}else if(array[begin] instanceof SayCT){
			commandParser(array,begin, forCLP);
			begin+=2;
			commandListParser(array,begin);
		}else if(array[begin] instanceof ApproachCT){
			commandParser(array,begin, forCLP);
			begin+=2;
			commandListParser(array,begin);
		}else if(array[begin] instanceof PassCT){
			commandParser(array,begin, forCLP);
			begin++;
			commandListParser(array,begin);
		}else if(array[begin] instanceof FailCT){
			commandParser(array,begin, forCLP);
			begin++;
			commandListParser(array,begin);
		}
		return forCLP;
	}

	void commandListProcess(){
		commandList.run();
	}
	
	@Tags({"repeat parser"})
	Repeat repeatParser(Common[] array, int begin) {
			if(array[begin+2] instanceof RepeatCT){
				start+=2;
				repeat = new Repeat(Integer.valueOf((String) table.getKey().get(start-1)),repeatParser(array, start));
			}else {
				start+=2;
				CommandList newCommandList = new CommandList();
				Runnable forRepeat = commandParser(array,start, newCommandList);
				repeat = new Repeat(Integer.valueOf((String) table.getKey().get(start-1)),forRepeat);
			}
			return repeat;
	}

	public void setCommand(String newCommand){
		command = newCommand;
		commandList = new CommandList();
		forCLP = new CommandList();
		Assignment11.scannerBean.setInput(command);
		Common[] common = Assignment11.scannerBean.getCommon();
		start=0;
		commandParser(common, start, commandList);
		commandListProcess();
		}
	@Tags({"start animation"})
	void startAnimation(){
		manager.proceedAll();
	}

	@Tags({"asynchronous Arthur"})
	void aSyncArthur() {
		arthurAnimator = new ScreenAnimator(arthur, 100,100);
		arthurCommand = new ScreenAnimationCommand(arthurAnimator);
		Thread arthurThread = new Thread(arthurCommand, "arthur");
		arthurThread.start();
	}
	@Tags({"waiting arthur"})
	synchronized void waitingArthur(){
		arthurAnimator = new ScreenAnimator(arthur, 100,100, manager);
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
	@Tags({"waiting galahad"})
	synchronized void waitingGalahad(){
		galahadAnimator = new ScreenAnimator(galahad, 100,100, manager);
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
	@Tags({"waiting lancelot"})
	synchronized void waitingLancelot(){
		lancelotAnimator = new ScreenAnimator(lancelot, 100,100, manager);
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
	@Tags({"waiting robin"})
	synchronized void waitingRobin(){
		robinAnimator = new ScreenAnimator(robin, 100,100, manager);
		robinCommand = new ScreenAnimationCommand(robinAnimator);
		Thread robinThread = new Thread(robinCommand, "robin");
		robinThread.start();
	}
}