package com.amjed.TextEditor.services.internal;

import com.amjed.TextEditor.models.dictionary.DictionaryTrie;

import java.util.List;

public interface PredictCompletion {
    public List<String> predictCompletions(String prefix, int numCompletions, DictionaryTrie dictionaryTrie);

    public Boolean isCorrect(String word, DictionaryTrie dictionaryTrie);
}
