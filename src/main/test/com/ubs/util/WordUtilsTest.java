package com.ubs.util;

import static com.ubs.util.WordUtils.getDistinctWordCount;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.ubs.exceptions.InvalidInputException;

public class WordUtilsTest {

	@Test(expected = InvalidInputException.class)
	public void callGetDistinctWordsWithInputNullGivesException() {
		String sentence = null;
		getDistinctWordCount(sentence);
	}

	@Test(expected = InvalidInputException.class)
	public void callGetDistinctWordsWithInputNothingGivesException() {
		String sentence = "";
		getDistinctWordCount(sentence);
	}

	@Test(expected = InvalidInputException.class)
	public void callGetDistinctWordsWithOnlySpacesGivesException() {
		String sentence = "   ";
		getDistinctWordCount(sentence);
	}

	@Test
	public void callGetDistinctWordsWith2DistinctWords() {
		@SuppressWarnings("unchecked")
		Map<String, Integer> distinctWordCountMapSpy = spy(HashMap.class);
		when(distinctWordCountMapSpy.size()).thenReturn(2);
		distinctWordCountMapSpy.put("two", 1);

		final String sentence = "two words";
		assertEquals(distinctWordCountMapSpy.size(), getDistinctWordCount(sentence).size());
		assertEquals(distinctWordCountMapSpy.get("two"), getDistinctWordCount(sentence).get("two"));
	}

	@Test
	public void getDistinctWordsSpaceNotConsidered() {
		@SuppressWarnings("unchecked")
		Map<String, Integer> distinctWordCountMapSpy = spy(HashMap.class);
		when(distinctWordCountMapSpy.size()).thenReturn(2);
		distinctWordCountMapSpy.put("two", 1);
		assertEquals(distinctWordCountMapSpy.size(), getDistinctWordCount("$two words").size());

		assertEquals(null, getDistinctWordCount("two words").get(" "));
	}

	@Test
	public void callGetDistinctWordsWithAllDistinctWordsAndComma() {
		@SuppressWarnings("unchecked")
		Map<String, Integer> distinctWordCountMapSpy = spy(HashMap.class);
		when(distinctWordCountMapSpy.size()).thenReturn(6);

		final String sentence = "Pinki,this is not a school";
		assertEquals(distinctWordCountMapSpy.size(), getDistinctWordCount(sentence).size());
		assertEquals(null, getDistinctWordCount(sentence).get(","));
	}

	@Test
	public void callGetDistinctWordsWithAllDistinctWordsAndCommaWithSpaces() {
		@SuppressWarnings("unchecked")
		Map<String, Integer> distinctWordCountMapSpy = spy(HashMap.class);
		when(distinctWordCountMapSpy.size()).thenReturn(5);

		final String sentence = "Space before , and after comma";
		assertEquals(distinctWordCountMapSpy.size(), getDistinctWordCount(sentence).size());
		assertEquals(null, getDistinctWordCount(sentence).get(","));
	}

	@Test
	public void callGetDistinctWordsAllDistinctWordsAndNegliblePuncuationAndQuestionMark() {
		@SuppressWarnings("unchecked")
		Map<String, Integer> distinctWordCountMapSpy = spy(HashMap.class);
		when(distinctWordCountMapSpy.size()).thenReturn(7);
		distinctWordCountMapSpy.put("good", 2);

		final String sentence = "Good Morning!! My good boy. How are you?";
		assertEquals(distinctWordCountMapSpy.size(), getDistinctWordCount(sentence).size());
		assertEquals(null, getDistinctWordCount(sentence).get("!!"));
		assertEquals(null, getDistinctWordCount(sentence).get("?"));
		assertEquals(distinctWordCountMapSpy.get("good"), getDistinctWordCount(sentence).get("good"));
	}

	@Test
	public void callGetDistinctWordsOneWordAndNeglibleDots() {
		@SuppressWarnings("unchecked")
		Map<String, Integer> distinctWordCountMapSpy = spy(HashMap.class);
		when(distinctWordCountMapSpy.size()).thenReturn(1);

		final String sentence = "helloooo..... ";
		assertEquals(distinctWordCountMapSpy.size(), getDistinctWordCount(sentence).size());
		assertEquals(new Integer(1), getDistinctWordCount(sentence).get("helloooo"));
	}

	@Test
	public void callGetDistinctWordsAllDistinctWordsAndNeglibleSingleQuote() {
		@SuppressWarnings("unchecked")
		Map<String, Integer> distinctWordCountMapSpy = spy(HashMap.class);
		when(distinctWordCountMapSpy.size()).thenReturn(8);

		final String sentence = "Hey I'd like to play with this ball.";
		assertEquals(distinctWordCountMapSpy.size(), getDistinctWordCount(sentence).size());
		assertEquals(null, getDistinctWordCount(sentence).get("'"));
	}

	@Test
	public void callGetDistinctWordsNeglibleDoubleQuotesAndMutlipleOccurences() {
		@SuppressWarnings("unchecked")
		Map<String, Integer> distinctWordCountMapSpy = spy(HashMap.class);
		when(distinctWordCountMapSpy.size()).thenReturn(8);
		distinctWordCountMapSpy.put("said", 2);
		distinctWordCountMapSpy.put("dog", 2);
		distinctWordCountMapSpy.put("bow", 3);
		distinctWordCountMapSpy.put("and", 1);

		final String sentence = "In dog cat conversation, dog said \"bow bow bow\" and cat said \"meow meow\"";
		assertEquals(distinctWordCountMapSpy.size(), getDistinctWordCount(sentence).size());
		assertEquals(null, getDistinctWordCount(sentence).get("\""));
		assertEquals(distinctWordCountMapSpy.get("said"), getDistinctWordCount(sentence).get("said"));
		assertEquals(distinctWordCountMapSpy.get("dog"), getDistinctWordCount(sentence).get("dog"));
		assertEquals(distinctWordCountMapSpy.get("bow"), getDistinctWordCount(sentence).get("bow"));
		assertEquals(distinctWordCountMapSpy.get("and"), getDistinctWordCount(sentence).get("and"));
	}

	@Test
	public void callGetDistinctWordsIgnoreNumbersAsWord() {
		@SuppressWarnings("unchecked")
		Map<String, Integer> distinctWordCountMapSpy = spy(HashMap.class);
		when(distinctWordCountMapSpy.size()).thenReturn(4);
		distinctWordCountMapSpy.put("pens", 1);

		final String sentence = "I have 10 pens.";
		assertEquals(distinctWordCountMapSpy.size(), getDistinctWordCount(sentence).size());
		assertEquals(new Integer(1), getDistinctWordCount(sentence).get("10"));
		assertEquals(distinctWordCountMapSpy.get("pens"), getDistinctWordCount(sentence).get("pens"));
	}

	@Test
	public void callGetDistinctWordsIgnoreLowerAndUpperCaseOfWord() {
		@SuppressWarnings("unchecked")
		Map<String, Integer> distinctWordCountMapSpy = spy(HashMap.class);
		when(distinctWordCountMapSpy.size()).thenReturn(9);
		distinctWordCountMapSpy.put("i", 3);
		distinctWordCountMapSpy.put("am", 2);

		final String sentence = "I am showing lower and i am showing upper case example of I.";
		assertEquals(distinctWordCountMapSpy.size(), getDistinctWordCount(sentence).size());
		assertEquals(distinctWordCountMapSpy.get("i"), getDistinctWordCount(sentence).get("i"));
		assertEquals(distinctWordCountMapSpy.get("am"), getDistinctWordCount(sentence).get("am"));
	}

	@Test
	public void callGetDistinctWordsTestOneCompleteSentence() {
		@SuppressWarnings("unchecked")
		Map<String, Integer> distinctWordCountMapSpy = spy(HashMap.class);
		when(distinctWordCountMapSpy.size()).thenReturn(6);
		distinctWordCountMapSpy.put("this", 2);
		distinctWordCountMapSpy.put("is", 2);
		distinctWordCountMapSpy.put("a", 1);
		distinctWordCountMapSpy.put("statement", 1);
		distinctWordCountMapSpy.put("and", 1);
		distinctWordCountMapSpy.put("so", 1);

		final String sentence = "This is a statement, and so is this.";
		assertEquals(distinctWordCountMapSpy.size(), getDistinctWordCount(sentence).size());
		assertEquals(distinctWordCountMapSpy.get("this"), getDistinctWordCount(sentence).get("this"));
		assertEquals(distinctWordCountMapSpy.get("is"), getDistinctWordCount(sentence).get("is"));
		assertEquals(distinctWordCountMapSpy.get("a"), getDistinctWordCount(sentence).get("a"));
		assertEquals(distinctWordCountMapSpy.get("statement"), getDistinctWordCount(sentence).get("statement"));
		assertEquals(distinctWordCountMapSpy.get("and"), getDistinctWordCount(sentence).get("and"));
		assertEquals(distinctWordCountMapSpy.get("so"), getDistinctWordCount(sentence).get("so"));

	}

}
