package Main;

import java.util.Scanner;

public class Assignment2 {

	static String input;
	static String newInput;
	static String response;

	public static void main(String[] args) {
		System.out.println("Enter numbers, words, or anything in quotes to see what each is.");
		System.out.println("When done enter '.' at the beginning of the line to terminate the program.");
		
		Bean bean = new Bean();

		readIn();

		bean.getInput();

		bean.setInput(newInput);

	}

	static String readIn() {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		newInput = scanner.nextLine();
		
//		System.out.println("Are you sure you want to scan? (Y/N)");
//		debug();
//		for (char c : response.toCharArray()) {

//			if (c == 'N') {

//				newInput = "";
//				break;

//			}

//		}
		return newInput;

	}

//	static String debug() {
		
//		@SuppressWarnings("resource")
//		Scanner scanner2 = new Scanner(System.in);
//		response = scanner2.nextLine();

		
//		return response;
//	}

	public static void analyzeAndPrintString(String input) {
		
		Bean bean = new Bean();

		//creating variables which will assist in the analysis of the string.

		int count = 0;
		int previous_space = 0;
		int quote_count_front = 0;
		int quote_count_back = 0;
		boolean quote = false;
		String finalInput = new String();

		//scanning through each character of the screen and determining what to do with it

		for (char c : input.toCharArray()) {

			if (c == '.' && count == 0) {

				System.exit(0);

			}else if (c == ' ') {

				//for new spaces which aren't the exact previous character or the first character

				if (previous_space == count-1 && previous_space != 0) {

					previous_space = Integer.valueOf(count);

				}else if (previous_space != count-1 && quote == false) {

					if (isNumeric(trimString(input.substring(previous_space,count))) == true) {

						finalInput += ("number: " + Integer.valueOf(trimString(input.substring(previous_space,count))) + "\n" + "\n");

					}else if (isWord(trimString(input.substring(previous_space,count))) == true) {
						
						String word = trimString(input.substring(previous_space,count));
						
						if (word.length() > 0) {

							finalInput += ("word: " + trimString(input.substring(previous_space,count)) + "\n" + "\n");
						
						}

					}else {

						previous_space = Integer.valueOf(count);

					}

				}

				previous_space = Integer.valueOf(count);

			}else if (c == '"' && quote == true) {

				quote_count_back = Integer.valueOf(count);
				finalInput += ("quoted string: " + input.substring(quote_count_front+1,quote_count_back) + "\n" + "\n");
				quote = false;

			}else if (c == '"' && quote == false) {

				quote_count_front = Integer.valueOf(count);
				quote = true;

			}else if (c == '+' || c == '-') {

				finalInput += ("sign: " + c + "\n" + "\n");
				previous_space = Integer.valueOf(count);

			}

			count ++;

		}

		// check if the final parts of the string are just random spaces or numeric values

		if (input.substring(previous_space, input.length()).contains(" ") || input.substring(previous_space, input.length()).length() == input.length()) {

			if (isNumeric(trimString(input.substring(previous_space, input.length()))) == true) {

				finalInput += ("number: " + trimString(input.substring(previous_space, input.length())));

			}else if (isWord(trimString(input.substring(previous_space, input.length()))) == true) {
				
				String word = trimString(input.substring(previous_space, input.length()));
				
				if (word.length() > 0) {

					finalInput += ("word: " + trimString(input.substring(previous_space, input.length())));
			
				}

			}

		}
		
		System.out.println(finalInput);
		
//		System.out.println("\n" + "Print another line.");
		
		readIn();
		bean.getInput();
		bean.setInput(newInput);

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
