package com.amjed.texteditor.services.text.implementation;

import com.amjed.texteditor.constants.Constants;
import com.amjed.texteditor.services.text.CaesarCipher;
import org.springframework.stereotype.Service;

@Service
public class CaesarCipherImpl implements CaesarCipher {
    /**
     * this method is used to encrypt messages using Caesar Cipher with one key
     * @param key is a number that represents the shift in alphabets.
     * @param input is the message to be encrypted
     * @return the encrypted message
     */
    @Override
    public String encrypt(int key, String input) {
        String shiftedAlphabet = shiftAlphabet(key, Boolean.TRUE);
        String lowerShiftedAlphabet = shiftedAlphabet.toLowerCase();

        StringBuilder stringBuilder = new StringBuilder(input);
        for (Integer i = 0; i < stringBuilder.length(); i++) {
            Character ch = stringBuilder.charAt(i);
            if (ch.isUpperCase(ch)) {
                Integer alphaIndex = Constants.ALPHABET.UPPER.indexOf(ch);
                stringBuilder.setCharAt(i, shiftedAlphabet.charAt(alphaIndex));
            }
            if (ch.isLowerCase(ch)) {
                Integer alphaIndex = Constants.ALPHABET.LOWER.indexOf(ch);
                stringBuilder.setCharAt(i, lowerShiftedAlphabet.charAt(alphaIndex));
            }
        }
        return stringBuilder.toString();
    }
    /**
     * this method is used to encrypt messages using Caesar Cipher with two keys
     * @param key1 is a number that represents the shift in odd alphabets
     * @param key2 is a number that represents the shift in  even alphabets
     * @param input is the message to be encrypted
     * @return the encrypted message
     */
    @Override
    public String encrypt(Integer key1, Integer key2, String input) {
        String shiftedAlphabet1 = shiftAlphabet(key1, Boolean.TRUE);
        String shiftedAlphabet2 = shiftAlphabet(key2, Boolean.TRUE);
        String lowerShiftedAlphabet1 = shiftedAlphabet1.toLowerCase();
        String lowerShiftedAlphabet2 = shiftedAlphabet2.toLowerCase();

        StringBuilder stringBuilder = new StringBuilder(input);
        for (int i = 0; i < stringBuilder.length(); i++) {
            Character ch = stringBuilder.charAt(i);
            if (i % 2 == 0) {
                if (ch.isUpperCase(ch)) {
                    int alphaIndex = Constants.ALPHABET.UPPER.indexOf(ch);
                    stringBuilder.setCharAt(i, shiftedAlphabet1.charAt(alphaIndex));
                }
                if (ch.isLowerCase(ch)) {
                    int alphaIndex = Constants.ALPHABET.LOWER.indexOf(ch);
                    stringBuilder.setCharAt(i, lowerShiftedAlphabet1.charAt(alphaIndex));
                }
            } else {
                if (ch.isUpperCase(ch)) {
                    int alphaIndex = Constants.ALPHABET.UPPER.indexOf(ch);
                    stringBuilder.setCharAt(i, shiftedAlphabet2.charAt(alphaIndex));
                }
                if (ch.isLowerCase(ch)) {
                    int alphaIndex = Constants.ALPHABET.LOWER.indexOf(ch);
                    stringBuilder.setCharAt(i, lowerShiftedAlphabet2.charAt(alphaIndex));
                }
            }
        }
        return stringBuilder.toString();
    }
    /**
     * this method is used to decrypt messages using Caesar Cipher with one key
     * @param key is a number that represents the shift in alphabets
     * @param input is the message to be decrypted
     * @return the decrypted message
     */
    @Override
    public String decrypt(Integer key, String input) {
        return (encrypt(26 - key, input));
    }
    /**
     * this method is used to decrypt messages using Caesar Cipher with two keys
     * @param key1 is a number that represents the shift in odd alphabets
     * @param key2 is a number that represents the shift in  even alphabets
     * @param input is the message to be decrypted
     * @return the decrypted message
     */
    @Override
    public String decrypt(Integer key1, Integer key2, String input) {
        return (encrypt(26 - key1, 26 - key2, input));
    }
    /**
     * this method is used to crack a message encrypted using Caesar Cipher based on the most common letter in english 'e'
     * @param encrypted is the encrypted message to be cracked
     * @return the cracked message
     */
    @Override
    public String crackCaesarCipher(String encrypted) {
        return decrypt(getKey(encrypted), encrypted);
    }

    /**
     * private helping method used to shift alphabets by the key
     * @param key is a number that represents the shift in alphabets
     * @param isUpper a boolean represents if the letters is upper case or lower
     * @return the shifted alphabets
     */
    private String shiftAlphabet(Integer key, Boolean isUpper) {
        if (isUpper) {
            return Constants.ALPHABET.UPPER.substring(key) + Constants.ALPHABET.UPPER.substring(0, key);
        }
        return Constants.ALPHABET.LOWER.substring(key) + Constants.ALPHABET.LOWER.substring(0, key);
    }


    /**
     * private helping method used to count each letter in
     * a message, this method used to crack a message
     * @param message is the message to count letters
     * @return number of letters
     */
    private int[] countLetters(String message) {
        int[] counts = new int[26];
        for (int k = 0; k < message.length(); k++) {
            int dex = Constants.ALPHABET.LOWER.indexOf(Character.toLowerCase(message.charAt(k)));
            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }

    /**
     * private helping method used to find the index of the most common
     * letter in alphabets, this method used to crack a message
     * @param vals is an array contains index of a letter and number of appearance in a message
     * @return the index of the most common letter
     */
    private int maxIndex(int[] vals) {
        int maxDex = 0;
        for (int k = 0; k < vals.length; k++) {
            if (vals[k] > vals[maxDex]) {
                maxDex = k;
            }
        }
        return maxDex;
    }

    /**
     * private helping method used to find the key to crack encrypted message
     * @param encrypted is the encrypted message
     * @return the key to crack the message
     */
    private int getKey(String encrypted) {
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int mostCommonPos = 'e' - 'a';
        int dkey = maxDex - mostCommonPos;
        if (maxDex < mostCommonPos) {
            dkey = 26 - (mostCommonPos - maxDex);
        }
        return dkey;
    }
}
