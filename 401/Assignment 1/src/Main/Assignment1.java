package Main;

import java.util.Scanner;

public class Assignment1 {

	public static void main(String[] args){
		System.out.println("Enter numbers then hit the enter key.");
		System.out.println("When done enter '.' at the beginning of the line to terminate the program.");

		readIn();

	}

	private static void readIn() {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();

		analyzeAndPrintString(input);

	}

	private static void analyzeAndPrintString(String arg1) {

		//creating variables which will assist in the analysis of the string.

		int count = 0;
		int sum = 0;
		int product = 0;
		int previous_space = 0;

		//scanning through each character of the screen and determining what to do with it

		for (char c : arg1.toCharArray()) {

			if (c == '.' && count == 0) {

				System.exit(0);

			}else if (Character.isDigit(c) == false) {

				//for new spaces which aren't the exact previous character or the first character

				if (previous_space == count-1 && previous_space != 0) {

					previous_space = Integer.valueOf(count);

				}else if (sum == 0) {

					if (isNumeric(trimString(arg1.substring(previous_space,count))) == false) {

						previous_space = count;

					}else {

						//for the first numeric values in the string the values need to be added to 'product' so we don't multiply by 0 continuously.

					sum = Integer.parseInt(trimString(arg1.substring(previous_space,count)));
					product = Integer.parseInt(trimString(arg1.substring(previous_space,count)));

					}

				}else {

					if (product == 0) {

						product += Integer.parseInt(trimString(arg1.substring(previous_space,count)));

					}else{

					product = product * Integer.parseInt(trimString(arg1.substring(previous_space+1, count)));

					}

					sum += Integer.parseInt(trimString(arg1.substring(previous_space+1, count)));

				}

				previous_space = Integer.valueOf(count);

			}

			count ++;

		}

		// check if the final parts of the string are just random spaces or numeric values
		if (arg1.substring(previous_space, arg1.length()).contains(" ")) {

			if (isNumeric(trimString(arg1.substring(previous_space, arg1.length()))) == true) {

				sum += Integer.parseInt(trimString(arg1.substring(previous_space, arg1.length())));
				product = product * Integer.parseInt(trimString(arg1.substring(previous_space, arg1.length())));
				System.out.println("Numbers: " + arg1 + " Sum: " + sum + " Product: " + product);

			}else{

			System.out.println("Numbers: " + arg1 + " Sum: " + sum + " Product: " + product);

			}

		}else {

			sum += Integer.parseInt(trimString(arg1.substring(previous_space, arg1.length())));
			product = product * Integer.parseInt(trimString(arg1.substring(previous_space, arg1.length())));
			System.out.println("Numbers: " + arg1 + " Sum: " + sum + " Product: " + product);

		}

		readIn();

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


	// below method ignores the amount of spaces and removes it
	public static String trimString(String string) {

		String trimmed = string.replace(" ","");

		return trimmed;

	}

}