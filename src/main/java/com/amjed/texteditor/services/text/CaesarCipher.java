package com.amjed.texteditor.services.text;

public interface CaesarCipher {
    public String encrypt(int key, String input);

    public String encrypt(Integer key1, Integer key2, String input);

    public String decrypt(Integer key, String input);

    public String decrypt(Integer key1, Integer key2, String input);

    public String crackCaesarCipher(String encrypted);
}
