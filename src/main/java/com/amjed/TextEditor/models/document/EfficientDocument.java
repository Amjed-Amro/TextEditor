package com.amjed.TextEditor.models.document;

import lombok.Data;

import java.util.HashMap;


@Data
public class EfficientDocument {
    private String text;
    private int numWords;
    private int numSentences;
    private int numSyllables;
    private int numberOfCharacters;
    private double FleschScore;
    private String longestWord;
    private int longestWordLength;
    private int numOfUniqueWords;

    private HashMap<String, Integer> wordsFrequency;


    public EfficientDocument() {
        this.text = text;
        this.numWords = 0;
        this.numSentences = 0;
        this.numSyllables = 0;
        this.numberOfCharacters = 0;
        FleschScore = 0.0;
        this.numOfUniqueWords = 0;
        this.longestWord = "";
        this.longestWordLength = 0;
        this.wordsFrequency = new HashMap<String, Integer>();

    }
}
