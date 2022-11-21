package com.amjed.texteditor.services.text;

import com.amjed.texteditor.models.dictionary.DictionaryTrie;


public interface DictionaryLoader {
    public void loadDictionary(DictionaryTrie dictionaryTrie, String filename);

    public DictionaryTrie loadDictionary(String filename);

    public void loadDictionary(DictionaryTrie dictionaryTrie, String filename, int nWords);

    public boolean addWord(DictionaryTrie dictionaryTrie, String word);


}
