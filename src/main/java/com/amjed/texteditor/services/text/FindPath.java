package com.amjed.texteditor.services.text;

import com.amjed.texteditor.models.dictionary.DictionaryTrie;

import java.util.List;

public interface FindPath {
    public List<String> findPath(String word1, String word2, DictionaryTrie dictionaryTrie);
}
