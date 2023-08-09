package com.amjed.texteditor.services.text.implementation;

import com.amjed.texteditor.models.dictionary.DictionaryTrie;
import com.amjed.texteditor.services.text.NearbyWords;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

@Service
public class NearbyWordsImpl implements NearbyWords {

    private DictionaryTrie dictionaryTrie;


    /**
     * Return the list of Strings that are one modification away
     * from the input string.
     * @param word      The original String
     * @param wordsOnly controls whether to return only words or any String
     * @return list of Strings which are nearby the original string
     */
    @Override
    public List<String> distanceOne(String word, boolean wordsOnly, DictionaryTrie dictionaryTrie) {
        this.dictionaryTrie = dictionaryTrie;
        List<String> retList = new ArrayList<>();
        insertions(word, retList, wordsOnly);
        substitution(word, retList, wordsOnly);
        deletions(word, retList, wordsOnly);
        return retList;
    }
    /**
     * Add to the currentList Strings that are one character deletion away
     * from the input string.
     * @param word           The misspelled word
     * @param numSuggestions is the maximum number of suggestions to return
     * @return the list of spelling suggestions
     */
    @Override
    public List<String> suggestions(String word, int numSuggestions, DictionaryTrie dictionaryTrie) {
        this.dictionaryTrie = dictionaryTrie;
        List<String> queue = new LinkedList<>();     // String to explore
        HashSet<String> visited = new HashSet<>();   // to avoid exploring the same
        List<String> retList = new LinkedList<>();   // words to return
        queue.add(word);
        visited.add(word);
        while (queue.isEmpty() && retList.size() < numSuggestions) {
            String currWord = queue.get(0);
            queue.remove(0);
            List<String> neighbor = distanceOne(currWord, true, dictionaryTrie);
            for (String n : neighbor) {
                if (!visited.contains(n)) {
                    queue.add(n);
                }
            }
            if (!currWord.equals(word) && dictionaryTrie.isWord(currWord)) {
                retList.add(currWord);
            }
        }
        return retList;
    }
    /**
     * Add to the currentList Strings that are one character insertion away
     * from the input string.
     * @param word        The original String
     * @param currentList is the list of words to append modified words
     * @param wordsOnly   controls whether to return only words or any String
     * @return
     */
    private void substitution(String word, List<String> currentList, boolean wordsOnly) {
        for (int index = 0; index < word.length(); index++) {
            for (int charCode = 'a'; charCode <= 'z'; charCode++) {
                StringBuilder stringBuilder = new StringBuilder(word);
                stringBuilder.setCharAt(index, (char) charCode);
                // if the item isn't in the list, isn't the original string, and
                // (if wordsOnly is true) is a real word, add to the list
                if (!currentList.contains(stringBuilder.toString()) &&
                        (!wordsOnly || dictionaryTrie.isWord(stringBuilder.toString())) &&
                        !word.equals(stringBuilder.toString())) {
                    currentList.add(stringBuilder.toString());
                }
            }
        }
    }
    /**
     * Add to the currentList Strings that are one character deletion away
     * from the input string.
     *
     * @param word        The original String
     * @param currentList is the list of words to append modified words
     * @param wordsOnly   controls whether to return only words or any String
     * @return
     */
    private void deletions(String word, List<String> currentList, boolean wordsOnly) {
        for (int index = 0; index < word.length(); index++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(word.substring(0, index));
            stringBuilder.append(word.substring(index + 1));

            if (!currentList.contains(stringBuilder.toString()) &&
                    (!wordsOnly || dictionaryTrie.isWord(stringBuilder.toString())) &&
                    !word.equals(stringBuilder.toString())) {
                currentList.add(stringBuilder.toString());
            }
        }
    }

    /**
     *
     * @param s
     * @param currentList
     * @param wordsOnly
     */
    private void insertions(String s, List<String> currentList, boolean wordsOnly) {
        for (int index = 0; index < s.length() + 1; index++) {
            for (int charCode = 'a'; charCode <= 'z'; charCode++) {
                StringBuilder stringBuilder = new StringBuilder();
                if (index == s.length() + 1) {

                    stringBuilder.append(s);
                    stringBuilder.append((char) charCode);
                    if (!currentList.contains(stringBuilder.toString()) &&
                            (!wordsOnly || dictionaryTrie.isWord(stringBuilder.toString())) &&
                            !s.equals(stringBuilder.toString())) {
                        currentList.add(stringBuilder.toString());
                    }
                } else {
                    stringBuilder.append(s.substring(0, index));
                    stringBuilder.append((char) charCode);
                    stringBuilder.append(s.substring(index));
                    // if the item isn't in the list, isn't the original string, and
                    // (if wordsOnly is true) is a real word, add to the list
                    if (!currentList.contains(stringBuilder.toString()) &&
                            (!wordsOnly || dictionaryTrie.isWord(stringBuilder.toString())) &&
                            !s.equals(stringBuilder.toString())) {
                        currentList.add(stringBuilder.toString());
                    }
                }
            }
        }
    }

}

