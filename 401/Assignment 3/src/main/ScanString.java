package main;

import java.util.Scanner;

public class ScanString {

	static String newInput;
	static String mainInput;

	static String readIn() {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		newInput = scanner.nextLine();

		return newInput;

	}

public static void analyzeAndPrintString(String input) {

	Number number = new Number();
	Word word = new Word();
	Quote quote = new Quote();
	Start start = new Start();
	End end = new End();
	Plus plus = new Plus();
	Minus minus = new Minus();

		//creating variables which will assist in the analysis of the string.

		int count = 0;
		int previous_space = 0;
		int quote_count_front = 0;
		int quote_count_back = 0;
		boolean quote_occur = false;

		//scanning through each character of the screen and determining what to do with it

		for (char c : input.toCharArray()) {

			if (c == '.' && count == 0) {

				System.exit(0);

			}else if (c == ' ' || input.charAt(previous_space) == '-' || input.charAt(previous_space) == '+') {

				//for new spaces which aren't the exact previous character or the first character

				if (previous_space == count-1 && previous_space != 0) {

					previous_space = Integer.valueOf(count);

				}else if (previous_space != count-1 && quote_occur == false) {

					if (isNumeric(trimString(input.substring(previous_space,count))) == true) {

						mainInput = trimString(input.substring(previous_space,count));
						number.setInput(mainInput);
						System.out.println(number);
						System.out.println(number.getInput());
						System.out.println(number.getNumber());

					}else if (isWord(trimString(input.substring(previous_space,count))) == true) {

						String possibleWord = trimString(input.substring(previous_space,count));

						if (possibleWord.length() > 0) {

							mainInput = trimString(input.substring(previous_space,count));
							word.setInput(mainInput);
							System.out.println(word);
							System.out.println(word.getInput());
							System.out.println(word.getWord());

						}

					}

				}else if (quote_occur == false && previous_space == count){

					System.out.println("Invalid input: " + input.substring(previous_space,count));
					previous_space = Integer.valueOf(count);

				}

				previous_space = Integer.valueOf(count);

			}else if (c == '"' && quote_occur == true) {

				quote_count_back = Integer.valueOf(count);
				mainInput = input.substring(quote_count_front+1,quote_count_back);
				quote.setInput(mainInput);
				System.out.println(quote);
				System.out.println(quote.getInput());
				quote_occur = false;

			}else if (c == '"' && quote_occur == false) {

				quote_count_front = Integer.valueOf(count);
				quote_occur = true;

			}else if (c == '+') {

				mainInput = String.valueOf(c);
				plus.setInput(mainInput);
				System.out.println(plus);
				System.out.println(plus.getInput());
				previous_space = Integer.valueOf(count);

			}else if (c == '-') {

				mainInput = String.valueOf(c);
				minus.setInput(mainInput);
				System.out.println(minus);
				System.out.println(minus.getInput());
				previous_space = Integer.valueOf(count);

			}else if (c == '{') {

				mainInput = String.valueOf(c);
				start.setInput(mainInput);
				System.out.println(start);
				System.out.println(start.getInput());
				previous_space = Integer.valueOf(count);

			}else if (c == '}') {

				mainInput = String.valueOf(c);
				end.setInput(mainInput);
				System.out.println(end);
				System.out.println(end.getInput());
				previous_space = Integer.valueOf(count);

			}

			count ++;

		}

		// check if the final parts of the string are just random spaces or numeric values

		if (input.substring(previous_space, input.length()).contains(" ") || input.substring(previous_space, input.length()).length() == input.length()) {

			if (isNumeric(trimString(input.substring(previous_space, input.length()))) == true) {

				mainInput = trimString(input.substring(previous_space, input.length()));
				number.setInput(mainInput);
				System.out.println(number);
				System.out.println(number.getInput());
				System.out.println(number.getNumber());

			}else if (isWord(trimString(input.substring(previous_space, input.length()))) == true) {

				String possibleWord = trimString(input.substring(previous_space, input.length()));

				if (possibleWord.length() > 0) {

					mainInput = trimString(input.substring(previous_space, input.length()));
					word.setInput(mainInput);
					System.out.println(word);
					System.out.println(word.getInput());
					System.out.println(word.getWord());

				}

			}

		}else if (quote_occur == false) {
			
			System.out.println("Invalid: " + input.substring(previous_space, input.length()));

		}

		ScannerBean scannerBean = new ScannerBean();

		readIn();

		scannerBean.getInput();

		scannerBean.setInput(newInput);

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
