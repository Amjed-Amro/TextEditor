package com.amjed.TextEditor.services.internal;

import com.amjed.TextEditor.models.dictionary.DictionaryTrie;

import java.util.List;

public interface FindPath {
    public List<String> findPath(String word1, String word2, DictionaryTrie dictionaryTrie);
}
