package com.amjed.texteditor.services.text.implementation;

import com.amjed.texteditor.constants.Constants;
import com.amjed.texteditor.models.dictionary.DictionaryTrie;
import com.amjed.texteditor.services.text.VigenereCipher;

public class VigenereCipherImpl implements VigenereCipher {
    @Override
    public String encrypt(String input, String key) {
        int[] keys = vigKeys(key);
        return enc(keys, input);
    }

    @Override
    public String decrypt(String input, String key) {
        int[] keys = vigKeys(key);
        for (int i = 0; i < keys.length; i++) {
            keys[i] = 26 - keys[i];
        }
        return enc(keys, input);
    }
    @Override
    public String breakVigenere(String encrypted, DictionaryTrie dictionary) {
        int max = 0;
        int decryptKeyIndex = 1;

        StringBuilder allAlphabetMessage = new StringBuilder();
        for (int i = 0; i < encrypted.length(); i++) {
            if (encrypted.charAt(i) >= 'A' && encrypted.charAt(i) <= 'z') {
                allAlphabetMessage.append(encrypted.charAt(i));
            }
        }
        for (int i = 1; i < 100; i++) {
            int[] testKey = tryKeyLength(allAlphabetMessage.toString(), i);
            String testDecrypted = enc(testKey, encrypted);
            int testMax = countWords(testDecrypted, dictionary);
            if (testMax > max) {
                decryptKeyIndex = i;
                max = testMax;
            }
        }
        int[] decryptKey = tryKeyLength(allAlphabetMessage.toString(), decryptKeyIndex);
        return enc(decryptKey, encrypted);
    }

    private String enc(int[] keys, String input) {
        String[] shiftedAlphabet = new String[keys.length];
        for (int j = 0; j < shiftedAlphabet.length; j++) {
            shiftedAlphabet[j] = shiftAlphabet(keys[j]);
        }
        int j = 0;
        StringBuilder answer = new StringBuilder(input);
        for (int i = 0; i < answer.length(); ) {

            if (answer.charAt(i) >= 'A' && answer.charAt(i) <= 'z') {
                answer.setCharAt(i, transformLetter(answer.charAt(i), shiftedAlphabet[j]));
                j++;
            }
            if (j >= keys.length) {
                j = 0;
            }
            i++;
        }
        return answer.toString();
    }

    private int[] vigKeys(String key) {
        key = key.toLowerCase();
        int[] keys = new int[key.length()];

        for (int i = 0; i < key.length(); i++) {
            int thisKey =  key.charAt(i) - Constants.ALPHABET.LOWER.charAt(i);
            if (thisKey >= 0) {
                keys[i] = thisKey;
            } else {
                keys[i] = 26 + thisKey;
            }

        }
        return keys;
    }
    private char transformLetter(Character ch, String shiftedAlphabet) {
        if (ch.isUpperCase(ch)) {
            int alphaIndex = Constants.ALPHABET.UPPER.indexOf(ch);
            return shiftedAlphabet.toUpperCase().charAt(alphaIndex);
        }
        if (ch.isLowerCase(ch)) {
            int alphaIndex = Constants.ALPHABET.LOWER.indexOf(ch);
            return shiftedAlphabet.toLowerCase().charAt(alphaIndex);
        }
        return ch;
    }

    private String shiftAlphabet(Integer key) {
        return Constants.ALPHABET.LOWER.substring(key) + Constants.ALPHABET.LOWER.substring(0, key);
    }

    public int[] tryKeyLength(String encrypted, int klength) {
        int[] key = new int[klength];
        for (int i = 0; i < klength; i++) {
            String split = sliceString(encrypted, i, klength);
            int keyI = getKey(split);
            key[i] = (keyI);
        }
        return key;
    }

    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder result = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            result.append( message.charAt(i));


        }
        return result.toString();
    }

    public int countWords(String message, DictionaryTrie dictionary) {
        int count = 0;
        String[] split = message.split("\\W+");
        for (String word : split) {
            if (dictionary.isWord(word.toLowerCase())) {
                count += 1;
            }
        }
        return count;
    }

    public int getKey(String encrypted) {
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int mostCommonPos = 'e' - 'a';
        int dkey = maxDex - mostCommonPos;
        if (dkey < 0) {
            dkey = 26 - (mostCommonPos - maxDex);
        }
        return 26 - dkey;
    }

    public int[] countLetters(String message) {
        int[] counts = new int[26];
        for (int k = 0; k < message.length(); k++) {
            int dex = Constants.ALPHABET.LOWER.indexOf(Character.toLowerCase(message.charAt(k)));
            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }

    public int maxIndex(int[] values) {
        int maxDex = 0;
        for (int k = 0; k < values.length; k++) {
            if (values[k] > values[maxDex]) {
                maxDex = k;
            }
        }
        return maxDex;
    }


}
