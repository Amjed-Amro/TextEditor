package com.amjed.TextEditor.services.internal.implementation;

import com.amjed.TextEditor.constants.Constants;
import com.amjed.TextEditor.models.document.EfficientDocument;
import com.amjed.TextEditor.services.internal.DocumentProcess;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class DocumentProcessImpl implements DocumentProcess {

    public EfficientDocument processText(String text) {
        EfficientDocument efficientDocument = new EfficientDocument();
        efficientDocument.setText(text);
        efficientDocument.setNumberOfCharacters(text.length());
        efficientDocument = processText(efficientDocument);
        efficientDocument.setNumOfUniqueWords(efficientDocument.getWordsFrequency().size());

        return efficientDocument;
    }

    private double getFleschScore(EfficientDocument efficientDocument) {
        double wordNumber = efficientDocument.getNumWords();
        double sentNumber = efficientDocument.getNumSentences();
        double syllNumber = efficientDocument.getNumSyllables();
        double part1 = 206.835;
        double part2 = 1.015 * (wordNumber / sentNumber);
        double part3 = 84.6 * (syllNumber / wordNumber);
        return (part1 - part2 - part3);
    }

    /**
     * Passes through the text one time to count the number of words, syllables
     * and sentences, and set the member variables appropriately.
     * Words, sentences and syllables are defined as described below.
     */
    private EfficientDocument processText(EfficientDocument efficientDocument1) {
        EfficientDocument efficientDocument = efficientDocument1;
        List<String> tokens = getTokens("[!?.]+|[a-zA-Z]+", efficientDocument.getText());
        for (int i = 0; i < tokens.size(); i++) {
            if (isWord(tokens.get(i))) {
                efficientDocument.setNumWords(efficientDocument.getNumWords() + 1);
                efficientDocument.setNumSyllables(efficientDocument.getNumSyllables() + countSyllables(tokens.get(i)));
                efficientDocument = wordProcess(efficientDocument, tokens.get(i));
            }
            if (tokens.get(i).contains(".") || tokens.get(i).contains("!") || tokens.get(i).contains("?")) {
                efficientDocument.setNumSentences(efficientDocument.getNumSentences() + 1);
            }
            if (i == tokens.size() - 1 && isWord(tokens.get(i))) {
                efficientDocument.setNumSentences(efficientDocument.getNumSentences() + 1);
            }
        }
        efficientDocument.setFleschScore(getFleschScore(efficientDocument));
        return efficientDocument;
    }

    private EfficientDocument wordProcess(EfficientDocument efficientDocument, String word) {

        if (efficientDocument.getWordsFrequency().containsKey(word)) {
            efficientDocument.getWordsFrequency().put(word, efficientDocument.getWordsFrequency().get(word) + 1);
        } else {
            efficientDocument.getWordsFrequency().put(word, 1);
        }
        if (word.length() > efficientDocument.getLongestWordLength()) {
            efficientDocument.setLongestWord(word);
            efficientDocument.setLongestWordLength(word.length());
        }
        return efficientDocument;
    }

    private boolean isWord(String tok) {
        return !(tok.indexOf("!") >= 0 || tok.indexOf(".") >= 0 || tok.indexOf("?") >= 0);
    }


    /**
     * Returns the tokens that match the regex pattern from the document
     * text string.
     *
     * @param pattern A regular expression string specifying the
     *                token pattern desired
     * @return A List of tokens from the document text that match the regex
     * pattern
     */
    private List<String> getTokens(String pattern, String text) {
        ArrayList<String> tokens = new ArrayList<>();
        Pattern tokSplitter = Pattern.compile(pattern);
        Matcher m = tokSplitter.matcher(text);

        while (m.find()) {
            tokens.add(m.group());
        }

        return tokens;
    }

    /**
     * This is a helper function that returns the number of syllables
     * in a word.
     *
     * @param word The word to count the syllables in
     * @return The number of syllables in the given word, according to
     * this rule: Each contiguous sequence of one or more vowels is a syllable,
     * with the following exception: a lone "e" at the end of a word
     * is not considered a syllable unless the word has no other syllables.
     * You should consider y a vowel.
     */
    private int countSyllables(String word) {
        int count = 0;
        Boolean preVowel = Boolean.FALSE;
        for (int i = 0; i < word.length(); i++) {
            if (Constants.ALPHABET.VOWELS.contains(word.charAt(i)) && !preVowel) {
                if (!(i == word.length() - 1 && word.charAt(i) == 'e')) {
                    count++;
                    preVowel = Boolean.TRUE;
                }
            }
            if (!Constants.ALPHABET.VOWELS.contains(word.charAt(i))) {
                preVowel = Boolean.FALSE;
            }
            if (i == word.length() - 1 && word.charAt(i) == 'e' && count == 0) {
                count++;
            }
        }
        return count;
    }
}
