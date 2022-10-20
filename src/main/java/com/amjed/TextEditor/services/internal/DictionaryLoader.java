package com.amjed.TextEditor.services.internal;

import com.amjed.TextEditor.models.dictionary.DictionaryTrie;


public interface DictionaryLoader {
    public void loadDictionary(DictionaryTrie dictionaryTrie, String filename);

    public DictionaryTrie loadDictionary(String filename);

    public void loadDictionary(DictionaryTrie dictionaryTrie, String filename, int nWords);

    public boolean addWord(DictionaryTrie dictionaryTrie, String word);


}
