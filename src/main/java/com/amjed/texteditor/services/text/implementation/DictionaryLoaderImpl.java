package com.amjed.texteditor.services.text.implementation;

import com.amjed.texteditor.constants.Constants;
import com.amjed.texteditor.models.dictionary.DictionaryTrie;
import com.amjed.texteditor.models.dictionary.TrieNode;
import com.amjed.texteditor.services.text.DictionaryLoader;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

@Log4j2
@Service
public class DictionaryLoaderImpl implements DictionaryLoader {

    /**
     * this method is to add words to dictionary from file
     * @param dictionaryTrie is the dictionary to add words to
     * @param filename is the file
     */
    @Override
    public void loadDictionary(DictionaryTrie dictionaryTrie, String filename) {
        BufferedReader reader = null;
        try {
            String nextWord;

            reader = new BufferedReader(new FileReader(filename));
            while ( (nextWord = reader.readLine()) != null) {
                addWord(dictionaryTrie, nextWord);
            }
        } catch (IOException e) {
            log.error(Constants.ERROR_LOADING_FILE + filename);
        }

    }

    /**
     * this method is to load a dictionary from a file
     * @param filename is the name of the file to load the dictionary from
     * @return loaded dictionary as DictionaryTrie
     */

    @Override
    public DictionaryTrie loadDictionary(String filename) {
        DictionaryTrie dictionaryTrie = new DictionaryTrie();
        BufferedReader reader = null;
        try {
            String nextWord;
            reader = new BufferedReader(new FileReader(filename));
            //TODO: you can use here Objects.isNull()

            while (!Objects.isNull (nextWord = reader.readLine())) {
                addWord(dictionaryTrie, nextWord);
            }
        } catch (IOException e) {
            log.error(Constants.ERROR_LOADING_FILE + filename);

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
                log.error("loadDicitonary Warning: End of dictionary file reached.  ");
                log.error(nWords + " requested, but only " + numLoaded + " words loaded.");

            }
        } catch (IOException e) {
            log.error(Constants.ERROR_LOADING_FILE + filename);
        }

    }
    /**
     * Insert a word into the trie.
     * This method adds a word by creating and linking the necessary trie nodes
     * into the trie.     *
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
