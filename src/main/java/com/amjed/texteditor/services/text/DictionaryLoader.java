package com.amjed.texteditor.services.text;

import com.amjed.texteditor.models.dictionary.DictionaryTrie;


public interface DictionaryLoader {
    void loadDictionary(DictionaryTrie dictionaryTrie, String filename);

    DictionaryTrie loadDictionary(String filename);

    void loadDictionary(DictionaryTrie dictionaryTrie, String filename, int nWords);

    boolean addWord(DictionaryTrie dictionaryTrie, String word);


}
