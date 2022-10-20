package com.amjed.TextEditor.services.internal;

import com.amjed.TextEditor.models.dictionary.DictionaryTrie;

public interface VigenereCipher {
    public String encrypt(String input, String key);

    public String decrypt(String input, String key);

    public String breakVigenere(String encrypted, DictionaryTrie dictionary);
}
