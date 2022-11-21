package com.amjed.texteditor.models.wordpath;

import java.util.LinkedList;
import java.util.List;

public class WPTreeNode {

    private String word;
    private List<WPTreeNode> children;
    private WPTreeNode parent;

    /**
     * Construct a node with the word w and the parent p
     * (pass a null parent to construct the root)
     *
     * @param word   The new node's word
     * @param parent The new node's parent
     */
    public WPTreeNode(String word, WPTreeNode parent) {
        this.word = word;
        this.parent = parent;
        this.children = new LinkedList<WPTreeNode>();
    }

    /**
     * Add a child of a node containing the String s
     * precondition: The word is not already a child of this node
     *
     * @param s The child node's word
     * @return The new WPTreeNode
     */
    public WPTreeNode addChild(String s) {
        WPTreeNode child = new WPTreeNode(s, this);
        this.children.add(child);
        return child;
    }

    /**
     * Get the list of children of the calling object
     * (pass a null parent to construct the root)
     *
     * @return List of WPTreeNode children
     */
    public List<WPTreeNode> getChildren() {
        return this.children;
    }

    /**
     * Allows you to build a path from the root node to
     * the calling object
     *
     * @return The list of strings starting at the root and
     * ending at the calling object
     */
    public List<String> buildPathToRoot() {
        WPTreeNode curr = this;
        List<String> path = new LinkedList<String>();
        while (curr != null) {
            path.add(0, curr.getWord());
            curr = curr.parent;
        }
        return path;
    }

    /**
     * Get the word for the calling object
     *
     * @return Getter for calling object's word
     */
    public String getWord() {
        return this.word;
    }

    /**
     * toString method
     *
     * @return The string representation of a WPTreeNode
     */
    public String toString() {
        String ret = "Word: " + word + ", parent = ";
        if (this.parent == null) {
            ret += "null.\n";
        } else {
            ret += this.parent.getWord() + "\n";
        }
        ret += "[ ";
        for (WPTreeNode curr : children) {
            ret += curr.getWord() + ", ";
        }
        ret += (" ]\n");
        return ret;
    }

}
