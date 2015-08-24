package main;

import commands.ApproachCT;
import commands.CallCT;
import commands.DefineCT;
import commands.MoveCT;
import commands.ProceedAllCT;
import commands.RedoCT;
import commands.RepeatCT;
import commands.RotateLeftArmCT;
import commands.RotateRightArmCT;
import commands.SayCT;
import commands.SleepCT;
import commands.ThreadCT;
import commands.UndoCT;
import commands.WaitCT;

import tokens.Common;
import tokens.End;
import tokens.Minus;
import tokens.Number;
import tokens.Plus;
import tokens.Quote;
import tokens.Start;
import tokens.Table;
import tokens.Word;

public class ScanString {

	public static String sendInput;
	static Common[] common;
	static Table table = new Table();

public void analyzeString(String input, Common[] big) {

		//creating variables which will assist in the analysis of the string.

		int count = 0;
		int previous_space = 0;
		int quote_count_front = 0;
		int quote_count_back = 0;
		boolean quote_occur = false;
		int arraySize = 0;
		table.key.clear();
		table.value.clear();

		//scanning through each character of the screen and determining what to do with it

		for (char c : input.toCharArray()) {

			if (c == '.' && count == 0) {

				System.out.println("Period Reached");

			}else if (c == ' ' || input.charAt(previous_space) == '-' || input.charAt(previous_space) == '+') {

				//for new spaces which aren't the exact previous character or the first character

				if (previous_space == count-1 && previous_space != 0) {

					previous_space = Integer.valueOf(count);

				}else if (previous_space != count-1 && quote_occur == false) {

					if (isNumeric(trimString(input.substring(previous_space,count))) == true) {

						Number number = new Number();
						String mainInput = trimString(input.substring(previous_space,count));
						sendInput = String.valueOf(mainInput);
						number.setInput(mainInput);
						big[arraySize] = number;
						table.put(String.valueOf(number.getNumber()),number);
						arraySize ++;

					}else if (isWord(trimString(input.substring(previous_space,count))) == true) {

						String possibleWord = trimString(input.substring(previous_space,count));

						if (possibleWord.length() > 0) {

							String mainInput = trimString(input.substring(previous_space,count));
							sendInput = String.valueOf(mainInput);

							if (mainInput.toLowerCase().equals("move") == true) {

								MoveCT move = new MoveCT();
								move.setInput(mainInput);
								big[arraySize] = move;
								table.put(move.getWord(),move);
								arraySize ++;

							}else if (mainInput.toLowerCase().equals("say") == true) {

								SayCT say = new SayCT();
								say.setInput(mainInput);
								big[arraySize] = say;
								table.put(say.getWord(),say);
								arraySize ++;

							}else if (mainInput.toLowerCase().equals("rotateleftarm") == true) {

								RotateLeftArmCT rotateLeftArm = new RotateLeftArmCT();
								rotateLeftArm.setInput(mainInput);
								big[arraySize] = rotateLeftArm;
								table.put(rotateLeftArm.getWord(),rotateLeftArm);
								arraySize ++;

							}else if (mainInput.toLowerCase().equals("rotaterightarm") == true) {

								RotateRightArmCT rotateRightArm = new RotateRightArmCT();
								rotateRightArm.setInput(mainInput);
								big[arraySize] = rotateRightArm;
								table.put(rotateRightArm.getWord(),rotateRightArm);
								arraySize ++;

							}else if (mainInput.toLowerCase().equals("repeat") == true) {

								RepeatCT repeat = new RepeatCT();
								repeat.setInput(mainInput);
								big[arraySize] = repeat;
								table.put(repeat.getWord(),repeat);
								arraySize ++;

							}else if (mainInput.toLowerCase().equals("define") == true) {

								DefineCT define = new DefineCT();
								define.setInput(mainInput);
								big[arraySize] = define;
								table.put(define.getWord(),define);
								arraySize ++;

							}else if (mainInput.toLowerCase().equals("call") == true) {

								CallCT call = new CallCT();
								call.setInput(mainInput);
								big[arraySize] = call;
								table.put(call.getWord(),call);
								arraySize ++;

							}else if (mainInput.toLowerCase().equals("thread") == true) {

								ThreadCT thread = new ThreadCT();
								thread.setInput(mainInput);
								big[arraySize] = thread;
								table.put(thread.getWord(),thread);
								arraySize ++;

							}else if (mainInput.toLowerCase().equals("wait") == true) {

								WaitCT wait = new WaitCT();
								wait.setInput(mainInput);
								big[arraySize] = wait;
								table.put(wait.getWord(),wait);
								arraySize ++;

							}else if (mainInput.toLowerCase().equals("proceedall") == true) {

								ProceedAllCT proceedAll = new ProceedAllCT();
								proceedAll.setInput(mainInput);
								big[arraySize] = proceedAll;
								table.put(proceedAll.getWord(),proceedAll);
								arraySize ++;

							}else if (mainInput.toLowerCase().equals("sleep") == true) {

								SleepCT sleep = new SleepCT();
								sleep.setInput(mainInput);
								big[arraySize] = sleep;
								table.put(sleep.getWord(),sleep);
								arraySize ++;

							}else if (mainInput.toLowerCase().equals("undo") == true) {

								UndoCT undo = new UndoCT();
								undo.setInput(mainInput);
								big[arraySize] = undo;
								table.put(undo.getWord(),undo);
								arraySize ++;

							}else if (mainInput.toLowerCase().equals("redo") == true) {

								RedoCT redo = new RedoCT();
								redo.setInput(mainInput);
								big[arraySize] = redo;
								table.put(redo.getWord(),redo);
								arraySize ++;

							}else if (mainInput.toLowerCase().equals("approach") == true) {

								ApproachCT approach = new ApproachCT();
								approach.setInput(mainInput);
								big[arraySize] = approach;
								table.put(approach.getWord(),approach);
								arraySize ++;

							}else {

								Word word = new Word();
								word.setInput(mainInput);
								big[arraySize] = word;
								table.put(word.getWord(),word);
								arraySize ++;

							}

						}

					}

				}else if (quote_occur == false && previous_space == count){

					previous_space = Integer.valueOf(count);

				}

				previous_space = Integer.valueOf(count);

			}else if (c == '"' && quote_occur == true) {

				Quote quote = new Quote();
				quote_count_back = Integer.valueOf(count);
				String mainInput = input.substring(quote_count_front+1,quote_count_back);
				sendInput = String.valueOf(mainInput);
				quote.setInput(mainInput);
				big[arraySize] = quote;
				table.put(quote.getInput(),quote);
				arraySize ++;
				quote_occur = false;

			}else if (c == '"' && quote_occur == false) {

				quote_count_front = Integer.valueOf(count);
				quote_occur = true;

			}else if (c == '+') {

				Plus plus = new Plus();
				String mainInput = String.valueOf(c);
				sendInput = String.valueOf(mainInput);
				plus.setInput(mainInput);
				big[arraySize] = plus;
				table.put(plus.getInput(),plus);
				arraySize ++;
				previous_space = Integer.valueOf(count);

			}else if (c == '-') {

				Minus minus = new Minus();
				String mainInput = String.valueOf(c);
				sendInput = String.valueOf(mainInput);
				minus.setInput(mainInput);
				big[arraySize] = minus;
				table.put(minus.getInput(),minus);
				arraySize ++;
				previous_space = Integer.valueOf(count);

			}else if (c == '{') {

				Start start = new Start();
				String mainInput = String.valueOf(c);
				sendInput = String.valueOf(mainInput);
				start.setInput(mainInput);
				big[arraySize] = start;
				table.put(start.getInput(),start);
				arraySize ++;
				previous_space = Integer.valueOf(count);

			}else if (c == '}') {

				End end = new End();
				String mainInput = String.valueOf(c);
				sendInput = String.valueOf(mainInput);
				end.setInput(mainInput);
				big[arraySize] = end;
				table.put(end.getInput(),end);
				arraySize ++;
				previous_space = Integer.valueOf(count);

			}

			count ++;

		}

		// check if the final parts of the string are just random spaces or numeric values

		if (input.substring(previous_space, input.length()).contains(" ") || input.substring(previous_space, input.length()).length() == input.length()) {

			if (isNumeric(trimString(input.substring(previous_space, input.length()))) == true) {

				Number number = new Number();
				String mainInput = trimString(input.substring(previous_space, input.length()));
				sendInput = String.valueOf(mainInput);
				number.setInput(mainInput);
				big[arraySize] = number;
				table.put(String.valueOf(number.getNumber()),number);
				arraySize ++;

			}else if (isWord(trimString(input.substring(previous_space,count))) == true) {

				String possibleWord = trimString(input.substring(previous_space,count));

				if (possibleWord.length() > 0) {

					String mainInput = trimString(input.substring(previous_space,count));
					sendInput = String.valueOf(mainInput);

					if (mainInput.toLowerCase().equals("move") == true) {

						MoveCT move = new MoveCT();
						move.setInput(mainInput);
						big[arraySize] = move;
						table.put(move.getWord(),move);
						arraySize ++;

					}else if (mainInput.toLowerCase().equals("say") == true) {

						SayCT say = new SayCT();
						say.setInput(mainInput);
						big[arraySize] = say;
						table.put(say.getWord(),say);
						arraySize ++;

					}else if (mainInput.toLowerCase().equals("rotateleftarm") == true) {

						RotateLeftArmCT rotateLeftArm = new RotateLeftArmCT();
						rotateLeftArm.setInput(mainInput);
						big[arraySize] = rotateLeftArm;
						table.put(rotateLeftArm.getWord(),rotateLeftArm);
						arraySize ++;

					}else if (mainInput.toLowerCase().equals("rotaterightarm") == true) {

						RotateRightArmCT rotateRightArm = new RotateRightArmCT();
						rotateRightArm.setInput(mainInput);
						big[arraySize] = rotateRightArm;
						table.put(rotateRightArm.getWord(),rotateRightArm);
						arraySize ++;

					}else if (mainInput.toLowerCase().equals("repeat") == true) {

						RepeatCT repeat = new RepeatCT();
						repeat.setInput(mainInput);
						big[arraySize] = repeat;
						table.put(repeat.getWord(),repeat);
						arraySize ++;

					}else if (mainInput.toLowerCase().equals("define") == true) {

						DefineCT define = new DefineCT();
						define.setInput(mainInput);
						big[arraySize] = define;
						table.put(define.getWord(),define);
						arraySize ++;

					}else if (mainInput.toLowerCase().equals("call") == true) {

						CallCT call = new CallCT();
						call.setInput(mainInput);
						big[arraySize] = call;
						table.put(call.getWord(),call);
						arraySize ++;

					}else if (mainInput.toLowerCase().equals("thread") == true) {

						ThreadCT thread = new ThreadCT();
						thread.setInput(mainInput);
						big[arraySize] = thread;
						table.put(thread.getWord(),thread);
						arraySize ++;

					}else if (mainInput.toLowerCase().equals("wait") == true) {

						WaitCT wait = new WaitCT();
						wait.setInput(mainInput);
						big[arraySize] = wait;
						table.put(wait.getWord(),wait);
						arraySize ++;

					}else if (mainInput.toLowerCase().equals("proceedall") == true) {

						ProceedAllCT proceedAll = new ProceedAllCT();
						proceedAll.setInput(mainInput);
						big[arraySize] = proceedAll;
						table.put(proceedAll.getWord(),proceedAll);
						arraySize ++;

					}else if (mainInput.toLowerCase().equals("sleep") == true) {

						SleepCT sleep = new SleepCT();
						sleep.setInput(mainInput);
						big[arraySize] = sleep;
						table.put(sleep.getWord(),sleep);
						arraySize ++;

					}else if (mainInput.toLowerCase().equals("undo") == true) {

						UndoCT undo = new UndoCT();
						undo.setInput(mainInput);
						big[arraySize] = undo;
						table.put(undo.getWord(),undo);
						arraySize ++;

					}else if (mainInput.toLowerCase().equals("redo") == true) {

						RedoCT redo = new RedoCT();
						redo.setInput(mainInput);
						big[arraySize] = redo;
						table.put(redo.getWord(),redo);
						arraySize ++;

					}else if (mainInput.toLowerCase().equals("approach") == true) {

						ApproachCT approach = new ApproachCT();
						approach.setInput(mainInput);
						big[arraySize] = approach;
						table.put(approach.getWord(),approach);
						arraySize ++;

					}else {

						Word word = new Word();
						word.setInput(mainInput);
						big[arraySize] = word;
						table.put(word.getWord(),word);
						arraySize ++;

					}

				}

			}

		}else if (quote_occur == false) {

		}

		common = new Common[arraySize];
		for(int i=0; i<arraySize; i ++) {

			common[i] = big[i];

		}

	}

	// confirms if the string is numeric or has non numeric values

	public static boolean isNumeric(String string) {

		try {

			@SuppressWarnings("unused")
			int value = Integer.parseInt(string);

		}catch (NumberFormatException nfe){

			return false;

		}

		return true;

	}
	
	public static boolean isWord(String string) {
		
		int number = 1;

		for (char c : string.toCharArray()) {

			if (Character.isLetter(c) == true) {

				number = number * 1;

			}else {

				number = number * 0;

			}

		}

		if (number == 1) {

			return true;

		}else {

			return false;

		}

	}

	// below method ignores the amount of spaces and removes it
	
	public static String trimString(String string) {

		String trimmed = string.replace(" ","");

		return trimmed;

	}

}
