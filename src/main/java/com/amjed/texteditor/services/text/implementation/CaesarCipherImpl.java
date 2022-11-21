package com.amjed.texteditor.services.text.implementation;

import com.amjed.texteditor.constants.Constants;
import com.amjed.texteditor.services.text.CaesarCipher;
import org.springframework.stereotype.Service;

@Service
public class CaesarCipherImpl implements CaesarCipher {

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

    @Override
    public String decrypt(Integer key, String input) {
        return (encrypt(26 - key, input));
    }

    @Override
    public String decrypt(Integer key1, Integer key2, String input) {
        return (encrypt(26 - key1, 26 - key2, input));
    }

    @Override
    public String crackCaesarCipher(String encrypted) {
        return decrypt(getKey(encrypted), encrypted);
    }


    private String shiftAlphabet(Integer key, Boolean isUpper) {
        if (isUpper) {
            return Constants.ALPHABET.UPPER.substring(key) + Constants.ALPHABET.UPPER.substring(0, key);
        }
        return Constants.ALPHABET.LOWER.substring(key) + Constants.ALPHABET.LOWER.substring(0, key);
    }

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

    private int maxIndex(int[] vals) {
        int maxDex = 0;
        for (int k = 0; k < vals.length; k++) {
            if (vals[k] > vals[maxDex]) {
                maxDex = k;
            }
        }
        return maxDex;
    }

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
