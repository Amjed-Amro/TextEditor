package com.amjed.texteditor.services.text;

import com.amjed.texteditor.models.dictionary.DictionaryTrie;

import java.util.List;

public interface NearbyWords {

    public List<String> suggestions(String word, int numSuggestions, DictionaryTrie dictionaryTrie);

    public List<String> distanceOne(String word, boolean wordsOnly, DictionaryTrie dictionaryTrie);

}
