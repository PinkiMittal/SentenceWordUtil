package com.ubs.util;

import java.util.HashMap;
import java.util.Map;

import com.ubs.exceptions.InvalidInputException;

/**
 * Gives you methods to perform over sentence. Example method -
 * getDistinctWordCount - Gives a map of words, with their number of appearance
 * in the sentence.
 * 
 * @author Pinki Bhinder Mittal
 *
 */
public class WordUtils {

	private WordUtils() {
		super();
	}

	private static final String EMPTY_STR = "";
	private static final String SPACE_REGX = "\\s+";
	private static final String NOT_WORD_REGX = "[^A-Za-z0-9_']+";
	private static final String NEW_LINE = "[\\n]";

	/**
	 * Returns a map of words, with their number of appearance in the sentence.
	 * 
	 * @param sentence
	 *            the input sentence.
	 * @return wordCountMap the map containing the word and it's count.
	 */
	public static Map<String, Integer> getDistinctWordCount(String sentence) {

		// Validate sentence before processing.
		validateSentence(sentence);

		// Remove all new lines and replace with space.
		// Or you could have thrown exception saying -
		// only one sentence is allowed not paragraph.
		sentence = sentence.replaceAll(NEW_LINE, " ");

		// Remove all non-words in line and replace with space.
		sentence = sentence.replaceAll(NOT_WORD_REGX, " ");

		Map<String, Integer> wordCountMap = new HashMap<String, Integer>();
		// Loop through all the words in the sentence.
		for (String word : sentence.split(SPACE_REGX)) {
			word = word.toLowerCase();
			if (!wordCountMap.containsKey(word))
				// first time this word has occurred.
				wordCountMap.put(word, 1);
			else
				wordCountMap.put(word, wordCountMap.get(word) + 1);
		}

		// Check if empty key
		if (wordCountMap.containsKey(EMPTY_STR)) {
			wordCountMap.remove(EMPTY_STR);
		}

		return wordCountMap;
	}

	/**
	 * Validate the given sentence. if it is not null or empty or only spaces.
	 * 
	 * @param sentence
	 */
	private static void validateSentence(String sentence) {
		if (null != sentence && !sentence.trim().isEmpty()) {
			return;
		}
		throw new InvalidInputException("Please pass some sentence");
	}

}
