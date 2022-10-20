package com.amjed.TextEditor.models.dictionary;

import lombok.Data;

@Data
public class DictionaryTrie {
    private TrieNode root;
    private int size;

    public DictionaryTrie() {
        root = new TrieNode();
    }


    public void printTree() {
        printNode(root);
    }

    public void printNode(TrieNode curr) {
        if (curr == null)
            return;

        System.out.println(curr.getText());

        TrieNode next = null;
        for (Character c : curr.getValidNextCharacters()) {
            next = curr.getChild(c);
            printNode(next);
        }
    }

    public boolean isWord(String s) {
        if (s.equals("")) {
            return false;
        }
        s = s.toLowerCase();
        TrieNode next = root;
        for (char i : s.toCharArray()) {
            if (next.getChild(i) == null) {
                return false;
            }
            next = next.getChild(i);
        }
        if (next.endsWord() && next.getText().equals(s)) {
            return true;
        }
        return false;
    }


}
