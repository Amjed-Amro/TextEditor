package com.amjed.TextEditor.services.internal.implementation;

import com.amjed.TextEditor.constants.Constants;
import com.amjed.TextEditor.models.dictionary.DictionaryTrie;
import com.amjed.TextEditor.models.wordpath.WPTreeNode;
import com.amjed.TextEditor.services.internal.DictionaryLoader;
import com.amjed.TextEditor.services.internal.FindPath;
import com.amjed.TextEditor.services.internal.NearbyWords;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

@Service
public class FindPathImpl implements FindPath {
    public List<String> findPath(String word1, String word2, DictionaryTrie dictionaryTrie) {

        List<WPTreeNode> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();

        NearbyWords nearbyWords = new NearbyWordsImpl();
        WPTreeNode root = new WPTreeNode(word1, null);

        DictionaryLoader dictionaryLoader = new DictionaryLoaderImpl();
        DictionaryTrie dictionaryTrie1 = dictionaryLoader.loadDictionary(Constants.DICTIONARY_PATH);
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
}
