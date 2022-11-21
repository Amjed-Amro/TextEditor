package com.amjed.texteditor.services.text;

import com.amjed.texteditor.models.dictionary.DictionaryTrie;

public interface VigenereCipher {
    public String encrypt(String input, String key);

    public String decrypt(String input, String key);

    public String breakVigenere(String encrypted, DictionaryTrie dictionary);
}
