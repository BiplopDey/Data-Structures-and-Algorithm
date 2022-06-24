package com.datastructuresandalgorithm.datastructuresandalgorithm.datastructures.twodimensional;

import java.util.*;

public class AVLTree {
    private class Node {
        int value;
        Node left, right;
        int height = 0;

        Node(int value) {
            this.value = value;
        }
    }

    private Node root;

    public void insert(int value) {
        root = insert(root, value);
    }

    private Node insert(Node root, int value) {
        if (root == null)
            return new Node(value);

        if (value < root.value)
            root.left = insert(root.left, value);
        else
            root.right = insert(root.right, value);

        setHeight(root);

        return rotate(root);
    }

    private Node rotate(Node root) {
        if (isBalanced(root))
            return root;

        if (isLeftHeavy(root)) {
            if (balanceFactor(root.left) < 0)
                root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balanceFactor(root.left) > 0)
            root.right = rightRotate(root.right);

        return leftRotate(root);
    }

    private Node leftRotate(Node root) {
        Node newRoot = root.right;
        root.right = newRoot.left;
        newRoot.left = root;

        setHeight(root);
        setHeight(newRoot);

        return newRoot;
    }

    private Node rightRotate(Node root) {
        Node newRoot = root.left;
        root.left = newRoot.right;
        newRoot.right = root;

        setHeight(root);
        setHeight(newRoot);

        return newRoot;
    }

    private void setHeight(Node root) {
        root.height = Math.max(height(root.left), height(root.right)) + 1;
    }

    private boolean isBalanced(Node node) {
        return -1 >= balanceFactor(node) || balanceFactor(node) <= 1;
    }

    private boolean isLeftHeavy(Node node) {
        return balanceFactor(node) > 1;
    }

    private boolean isRightHeavy(Node node) {
        return balanceFactor(node) < -1;
    }

    private int balanceFactor(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private int height(Node node) {
        return node == null ? -1 : node.height;
    }
}