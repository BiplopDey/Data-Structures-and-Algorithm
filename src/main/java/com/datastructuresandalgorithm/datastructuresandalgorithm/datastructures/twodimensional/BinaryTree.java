package com.datastructuresandalgorithm.datastructuresandalgorithm.datastructures.twodimensional;


public class BinaryTree {
    public class Node {//make private after testing
        int value;
        Node left, right;

        Node(int value) {
            this.value = value;
        }
    }

    public Node root;//make private after testing

    public void insert(int value){
        insertRec(value);
        //insertIt(value);
    }

    private void insertRec(int value) {
        if (root == null) {
            root = new Node(value);
            return;
        }
        insertRec(root, value);
    }

    private void insertRec(Node root, int value) {
        if (value < root.value) {
            if (root.left == null) {
                root.left = new Node(value);
                return;
            }
            insertRec(root.left, value);
            return;
        }

        if (root.right == null) {
            root.right = new Node(value);
            return;
        }
        insertRec(root.right, value);
    }

    private void insertIt(int value) {
        Node node = new Node(value);
        if (root == null) {
            root = node;
            return;
        }

        Node current = root;
        while (current != null) {// it can be while true
            if (value < current.value) {
                if (current.left == null) {
                    current.left = node;
                    return;
                }
                current = current.left;
                continue;
            }
            if (current.right == null) {
                current.right = node;
                return;
            }
            current = current.right;
        }
    }

    public boolean find(int value) {
        Node current = root;
        while (current != null) {
            if (current.value == value)
                return true;

            if (value < current.value) {
                current = current.left;
                continue;
            }
            current = current.right;
        }

        return false;
    }

    public void traverseLevelOrder(){
        for(int i=0; i<= height();i++)
            getNodesAtDistance(i);
    }

    public void getNodesAtDistance(int level){
        getNodesAtDistance(root, level);
        System.out.println();
    }

    private void getNodesAtDistance(Node root, int distance){
        if(root==null) return;

        if(distance==0){
            System.out.print(root.value+" ");
            return;
        }

        distance--;
        getNodesAtDistance(root.left,distance);
        getNodesAtDistance(root.right,distance);
    }

    public boolean isBinarySearchTree() {
        return isBinarySearchTree(root, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBinarySearchTree(Node root, int lowerBound,  int upperBound) {
        if (root == null)
            return true;

        if(root.value <= lowerBound || root.value >= upperBound )
            return false;

        return isBinarySearchTree(root.left, lowerBound, root.value) && isBinarySearchTree(root.right, root.value, upperBound);
    }

    public boolean equals(BinaryTree tree) {
        if (tree == null)
            return root == null;// teacher didn't noticed it

        return equals(root, tree.root);
    }

    private boolean equals(Node n1, Node n2) {
        if (n1 == null || n2 == null) // if one is null, the other have to be null
            return n1 == null && n2 == null;

        if (isLeaf(n1))// if one is leaf and the other also is a leaf and with same value, then
            return isLeaf(n2) ? n1.value == n2.value : false;

        return n1.value == n2.value && equals(n1.left, n2.left) && equals(n1.right, n2.right);
    }

    public int min() {// only for binary search tree, O(log n)
        if (root == null)
            throw new IllegalStateException();

        Node current = root;
        while (current.left != null)
            current = current.left;

        return current.value;
    }

    public int minForGeneralTrees() {
        if(root == null) throw new IllegalStateException();
        return minForGeneralTrees(root);
    }

    private int minForGeneralTrees(Node root) {// O(n)
        if (root == null)
            return Integer.MAX_VALUE;// this condition is important that the teacher forgot
        if (isLeaf(root))
            return root.value;
        return Math.min(Math.min(minForGeneralTrees(root.left), minForGeneralTrees(root.right)), root.value);
    }

    private boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

    public int height() {
        return height(root);
    }

    private int height(Node root) {
        if (root == null)
            return -1;
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    public void traverseInOrder() {
        traverseInOrder(root);
    }

    private void traverseInOrder(Node root) {// left, root, right
        if (root == null)
            return;

        traverseInOrder(root.left);
        System.out.print(root.value + " ");
        traverseInOrder(root.right);
    }

    public void traversePreOrder() {//
        traversePreOrder(root);
    }

    private void traversePreOrder(Node root) {// root, left, right
        if (root == null)
            return;

        System.out.print(root.value + " ");
        traversePreOrder(root.left);
        traversePreOrder(root.right);
    }

    public void traversePostOrder() {
        traversePostOrder(root);
    }

    private void traversePostOrder(Node root) {// left, right,root
        if (root == null)
            return;

        traversePostOrder(root.left);
        traversePostOrder(root.right);
        System.out.print(root.value + " ");
    }

}