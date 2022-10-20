package com.amjed.TextEditor.services.internal.implementation;

import com.amjed.TextEditor.constants.Constants;
import com.amjed.TextEditor.models.dictionary.DictionaryTrie;
import com.amjed.TextEditor.models.dictionary.TrieNode;
import com.amjed.TextEditor.services.internal.DictionaryLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class DictionaryLoaderImpl implements DictionaryLoader {
    @Override
    public void loadDictionary(DictionaryTrie dictionaryTrie, String filename) {
        BufferedReader reader = null;
        try {
            String nextWord;
            reader = new BufferedReader(new FileReader(filename));
            while ((nextWord = reader.readLine()) != null) {
                addWord(dictionaryTrie, nextWord);
            }
        } catch (IOException e) {
            System.err.println(Constants.ERROR_LOADING_FILE + filename);
            e.printStackTrace();
        }

    }

    @Override
    public DictionaryTrie loadDictionary(String filename) {
        DictionaryTrie dictionaryTrie = new DictionaryTrie();
        BufferedReader reader = null;
        try {
            String nextWord;
            reader = new BufferedReader(new FileReader(filename));
            while ((nextWord = reader.readLine()) != null) {
                addWord(dictionaryTrie, nextWord);
            }
        } catch (IOException e) {
            System.err.println(Constants.ERROR_LOADING_FILE + filename);
            e.printStackTrace();
        }
        return dictionaryTrie;
    }


    /**
     * Load the first N words from the dictionary file into the dictionary
     *
     * @param dictionaryTrie The dictionary to load
     * @param filename       The file containing the words to load.  Each word must be on a separate line.
     * @param nWords         The number of words to load.  It will load the first nWords words
     */
    @Override
    public void loadDictionary(DictionaryTrie dictionaryTrie, String filename, int nWords) {
        BufferedReader reader = null;
        try {
            String nextWord;
            reader = new BufferedReader(new FileReader(filename));
            int numLoaded = 0;
            while ((nextWord = reader.readLine()) != null && numLoaded < nWords) {
                addWord(dictionaryTrie, nextWord);
                numLoaded++;
            }
            if (numLoaded < nWords) {
                System.out.print("loadDicitonary Warning: End of dictionary file reached.  ");
                System.out.println(nWords + " requested, but only " + numLoaded + " words loaded.");
            }
        } catch (IOException e) {
            System.err.println(Constants.ERROR_LOADING_FILE + filename);
            e.printStackTrace();
        }

    }


    /**
     * Insert a word into the trie.
     * This method adds a word by creating and linking the necessary trie nodes
     * into the trie.
     *
     * @return true if the word was successfully added or false if it already exists
     * in the dictionary.
     */
    @Override
    public boolean addWord(DictionaryTrie dictionaryTrie, String word) {
        word = word.toLowerCase();
        TrieNode currNode = dictionaryTrie.getRoot();
        int count = 0;

        for (char i : word.toCharArray()) {
            currNode.insert(i);
            currNode = currNode.getChild(i);
            count++;
            if (count == (word.length())) {
                if (currNode.endsWord()) {
                    return false;
                }
                currNode.setEndsWord(true);
                dictionaryTrie.setSize(dictionaryTrie.getSize() + 1);
            }
        }
        return true;
    }


}
