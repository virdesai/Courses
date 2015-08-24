package main;

import java.util.Scanner;

public class Assignment3 {

	static String input;
	static String newInput;

	public static void main(String[] args) {
		System.out.println("Enter numbers, words, or anything in quotes to see what each is.");
		System.out.println("When done enter '.' at the beginning of the line to terminate the program.");

		ScannerBean scannerBean = new ScannerBean();

		readIn();

		scannerBean.getInput();

		scannerBean.setInput(newInput);

	}

	static String readIn() {

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		newInput = scanner.nextLine();

		return newInput;

	}

}