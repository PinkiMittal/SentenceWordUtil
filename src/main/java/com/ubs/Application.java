package com.ubs;

import static com.ubs.util.WordUtils.getDistinctWordCount;

import java.util.Scanner;

/**
 * This is a main class to run the application
 * 
 * @author Pinki Mittal
 *
 */
public class Application {

	public static void main(String[] args) {
		System.out.println("Hi Author! Please write a sentence and press enter:");
		Scanner scanner = new Scanner(System.in);
		String sentence = scanner.nextLine();
		scanner.close();
		System.out.println(getDistinctWordCount(sentence));
	}

}
