package com.amjed.texteditor.services.text;

import com.amjed.texteditor.models.dictionary.DictionaryTrie;

public interface VigenereCipher {
    String encrypt(String input, String key);

    String decrypt(String input, String key);

    String breakVigenere(String encrypted, DictionaryTrie dictionary);
}
