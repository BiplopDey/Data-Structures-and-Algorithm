package com.datastructuresandalgorithm.datastructuresandalgorithm.datastructures.twodimensional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Trie {
    private Node root = new Node(' ');

    public void insert(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            if (!node.hasChild(ch))
                node.addChild(ch);

            node = node.getChild(ch);
        }
        node.makeEndOfString();
    }

    public boolean contains(String word) {
        if (word == null)
            return false;

        Node node = root;
        for (char ch : word.toCharArray()) {
            if (!node.hasChild(ch))
                return false;

            node = node.getChild(ch);
        }
        return node.isEndOfString();
    }

    public void delete(String str) {
        if (str == null)
            return;
        delete(root, str, 0);
    }

    private void delete(Node root, String str, int index) {
        if (index == str.length()) {
            root.dontMakeEndOfString();
            return;
        }

        char ch = str.charAt(index);
        if (!root.hasChild(ch))
            return;

        Node child = root.getChild(ch);
        delete(child, str, index + 1);
        if (!child.hasChildren() && !child.isEndOfString())
            root.deleteChild(ch);
    }

    private Node findLastNodeOf(String prefix) {
        Node current = root;
        for (char ch : prefix.toCharArray()) {
            if (!current.hasChild(ch))
                return null;
            current = current.getChild(ch);
        }
        return current;
    }

    public List<String> startsWith(String prefix) {
        Node lastNode = findLastNodeOf(prefix);
        if (lastNode == null || prefix == null)
            return null;
        List<String> list = new ArrayList<>();
        startsWith(lastNode, prefix, list);
        return list;
    }

    private void startsWith(Node root, String word, List<String> list) {
        if (root.isEndOfString())
            list.add(word);

        for (Node node : root.getChildren())
            startsWith(node, word + node.getValue(), list);

    }

    public void traverse() {
        traverse(root);
    }

    private void traverse(Node root) {
        for (Node node : root.getChildren())
            traverse(node);
    }
}

class Node {
    private char value;
    private HashMap<Character, Node> children = new HashMap<>();
    private boolean isEndOfString;

    public Node(char value) {
        this.value = value;
        isEndOfString = false;
    }

    public boolean isLeaf() {
        return children.size() == 0;
    }

    public boolean hasChildren() {
        return children.size() > 0;
    }

    public boolean isEndOfString() {
        return isEndOfString;
    }

    public void deleteChild(char value) {
        children.remove(value);
    }

    public char getValue() {
        return value;
    }

    public void makeEndOfString() {
        isEndOfString = true;
    }

    public void dontMakeEndOfString() {
        isEndOfString = true;
    }
    public boolean hasChild(char value) {
        return children.containsKey(value);
    }

    public void addChild(char value) {
        children.put(value, new Node(value));
    }

    public Node getChild(char value) {
        return children.get(value);
    }

    public List<Node> getChildren(){
        return new ArrayList<Node>(children.values());
    }
}