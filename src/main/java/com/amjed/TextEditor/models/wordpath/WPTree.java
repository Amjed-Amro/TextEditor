package com.amjed.TextEditor.models.wordpath;

import com.amjed.TextEditor.models.dictionary.DictionaryTrie;
import com.amjed.TextEditor.services.internal.DictionaryLoader;
import com.amjed.TextEditor.services.internal.NearbyWords;
import com.amjed.TextEditor.services.internal.implementation.DictionaryLoaderImpl;
import com.amjed.TextEditor.services.internal.implementation.NearbyWordsImpl;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class WPTree {
    // this is the root node of the WPTree
    private WPTreeNode root;
    // used to search for nearby Words
    private NearbyWords nearbyWords;

    private DictionaryTrie dictionaryTrie;


    public WPTree() {
        this.root = null;
        DictionaryLoader dictionaryLoader = new DictionaryLoaderImpl();
        dictionaryTrie = dictionaryLoader.loadDictionary("data/dict.txt");

        this.nearbyWords = new NearbyWordsImpl();
    }

    public List<String> findPath(String word1, String word2) {
        List<WPTreeNode> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();

        root = new WPTreeNode(word1, null);
        visited.add(word1);
        queue.add(root);
        while (queue.size() > 0) {
            WPTreeNode currNode = queue.get(0);
            queue.remove(0);
            List<String> neighbor = nearbyWords.distanceOne(currNode.getWord(), true, dictionaryTrie);
            for (String n : neighbor) {
                if (!visited.contains(n)) {
                    visited.add(n);
                    queue.add(currNode.addChild(n));

                }
                if (currNode.getWord().equals(word2)) {
                    return currNode.buildPathToRoot();
                }
            }
        }
        return null;
    }

    // Method to print a list of WPTreeNodes (useful for debugging)
    private String printQueue(List<WPTreeNode> list) {
        String ret = "[ ";

        for (WPTreeNode w : list) {
            ret += w.getWord() + ", ";
        }
        ret += "]";
        return ret;
    }

}
