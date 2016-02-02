package tbrachet.my.texts.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {

	/**
	 * This method use the Flesch Kincaid Grade level (FKRA) where the formula:
	 * 0.39*(total words / total sentences)+11.8*(total syllables / total words) - 15.59
	 *  gives a score representative of the grade level required to read the text.
	 *  For our purpose, we will consider grade level above 12 scoring a 10 
	 * @param textToScore The text to evaluate
	 * @return The score
	 */
	public static String calculateScore(String textToScore) {

		int nbSentences = 0;
		int nbWords = 0;
		int nbSyllables = 0;
		String result = "";

		if (textToScore != null) {
			String[] sentences = textToScore.split("\\.");
			String[] words;

			for (int i = 0; i < sentences.length; i++) {
				words = sentences[i].split("\\s+");
				nbSentences++;
				for (int j = 0; j < words.length; j++) {
					nbWords++;
					nbSyllables += countSyllables(words[j]);
				}
			}

			if (nbSentences > 0 && nbWords > 0 && nbSyllables > 0) {
				double fkra = 0.39 * (nbWords / nbSentences) + 11.8 * (nbSyllables / nbWords) - 15.59;
				long round = Math.round(fkra);
				if(round<1) {
					round = 1;
				}
				else if (round>10) {
					round = 10;
				} 
				result = String.valueOf(round);
			}
		}

		return result;
	}

	/**
	 * Method counting the number of syllables
	 * 
	 * @param word The word tested for its syllables
	 * @return the number of syllables in the word
	 */
	private static int countSyllables(String word) {
		int count = 0;
		word = word.toLowerCase();

		if (word.length()>0 && word.charAt(word.length() - 1) == 'e') {
			if (silente(word)) {
				String newword = word.substring(0, word.length() - 1);
				count = count + countit(newword);
			} else {
				count++;
			}
		} else {
			count = count + countit(word);
		}
		return count;
	}

	private static int countit(String word) {
		int count = 0;
		Pattern splitter = Pattern.compile("[^aeiouy]*[aeiouy]+");
		Matcher m = splitter.matcher(word);

		while (m.find()) {
			count++;
		}
		return count;
	}

	private static boolean silente(String word) {
		word = word.substring(0, word.length() - 1);

		Pattern yup = Pattern.compile("[aeiouy]");
		Matcher m = yup.matcher(word);

		if (m.find()) {
			return true;
		} else
			return false;
	}

}
