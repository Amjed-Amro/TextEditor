package com.amjed.texteditor.services.text;

public interface CaesarCipher {
    String encrypt(int key, String input);

    String encrypt(Integer key1, Integer key2, String input);

    String decrypt(Integer key, String input);

    String decrypt(Integer key1, Integer key2, String input);

    String crackCaesarCipher(String encrypted);
}
