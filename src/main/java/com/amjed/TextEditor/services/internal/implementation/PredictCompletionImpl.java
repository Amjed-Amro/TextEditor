package com.amjed.TextEditor.services.internal.implementation;

import com.amjed.TextEditor.models.dictionary.DictionaryTrie;
import com.amjed.TextEditor.models.dictionary.TrieNode;
import com.amjed.TextEditor.services.internal.PredictCompletion;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class PredictCompletionImpl implements PredictCompletion {
    /**
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions
     * of the prefix string. All legal completions must be valid words in the
     * dictionary. If the prefix itself is a valid word, it is included
     * in the list of returned words.
     * <p>
     * The list of completions must contain
     * all of the shortest completions, but when there are ties, it may break
     * them in any order. For example, if there the prefix string is "ste" and
     * only the words "step", "stem", "stew", "steer" and "steep" are in the
     * dictionary, when the user asks for 4 completions, the list must include
     * "step", "stem" and "stew", but may include either the word
     * "steer" or "steep".
     * <p>
     * If this string prefix is not in the trie, it returns an empty list.
     *
     * @param prefix         The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */
    public List<String> predictCompletions(String prefix, int numCompletions, DictionaryTrie dictionaryTrie) {
        prefix = prefix.toLowerCase();
        List<String> completions = new ArrayList<String>();

        if ((prefix.length() > 0) && dictionaryTrie.getRoot().getChild(prefix.charAt(0)) == null) {
            return completions;
        }
        LinkedList<TrieNode> queue = new LinkedList<TrieNode>();
        TrieNode curr = dictionaryTrie.getRoot();
        for (char i : prefix.toCharArray()) {
            if (curr.getChild(i) != null) {
                curr = curr.getChild(i);
            }
        }
        queue.add(curr);
        while (queue.size() > 0 && completions.size() < numCompletions) {
            curr = queue.get(0);
            queue.remove(0);
            if (curr != null && curr.endsWord()) {
                completions.add(curr.getText());
            }
            for (char i : curr.getValidNextCharacters()) {
                if (curr.getChild(i) != null) {
                    queue.add(curr.getChild(i));
                }
            }
        }
        return completions;
    }

    public Boolean isCorrect(String word, DictionaryTrie dictionaryTrie) {
        return dictionaryTrie.isWord(word);
    }
}
