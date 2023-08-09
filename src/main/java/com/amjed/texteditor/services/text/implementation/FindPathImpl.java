package com.amjed.texteditor.services.text.implementation;

import com.amjed.texteditor.models.dictionary.DictionaryTrie;
import com.amjed.texteditor.models.wordpath.WPTreeNode;
import com.amjed.texteditor.services.text.FindPath;
import com.amjed.texteditor.services.text.NearbyWords;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FindPathImpl implements FindPath {

    /**
     * this method is used to trace the change from one ord to the other one
     * @param word1 is the word to be changed
     * @param word2 is the target of the first word
     * @param dictionaryTrie is the dictionary used to trace the change
     * @return the path used to change word1 to word2
     */
    @Override
    public List<String> findPath(String word1, String word2, DictionaryTrie dictionaryTrie) {
        List<WPTreeNode> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        NearbyWords nearbyWords = new NearbyWordsImpl();
        WPTreeNode root = new WPTreeNode(word1, null);
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
        return Collections.emptyList();
    }
}
