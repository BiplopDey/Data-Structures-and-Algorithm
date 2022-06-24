package com.datastructuresandalgorithm.datastructuresandalgorithm.datastructures.twodimensional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinaryTreeTest {
    BinaryTree tree;

    @BeforeEach
    void setUp() {
       tree = createATree();
    }

    @Test
    void find() {
        System.out.println(this.tree.find(7));
        System.out.println(this.tree.find(9));
        System.out.println(this.tree.find(-5));
        System.out.println(this.tree.find(30));
    }
    @Test
    void traverse(){
        this.tree.traversePreOrder();
        System.out.println();
        this.tree.traverseInOrder();
        System.out.println();
        this.tree.traversePostOrder();
    }
    @Test
    void height(){
        System.out.println(this.tree.height());
    }

    void min() {
        this.tree.insert(-1);
        System.out.println(this.tree.min());

    }

    void equals() {
        System.out.println(this.tree.equals(this.tree));
        BinaryTree tree1 = createATree();
        tree1.insert(-1);
        System.out.println(this.tree.equals(tree1));
        tree1.insert(11);
        System.out.println(this.tree.equals(tree1));

        tree1.insert(21);
        System.out.println(this.tree.equals(tree1));
    }


    void testIsBinarySearchTree() {
        System.out.println(this.tree.isBinarySearchTree());
    }

    void testDistanceNode() {
        this.tree = createATree();
        this.tree.getNodesAtDistance(0);
        this.tree.getNodesAtDistance(1);
        this.tree.getNodesAtDistance(2);

    }

    void test_is_traverse_level_order() {
        this.tree = createATree();
        this.tree.traverseLevelOrder();
    }

    public static BinaryTree createATree(){
        BinaryTree t1 = new BinaryTree();
        t1.insert(7);
        t1.insert(4);
        t1.insert(9);
        t1.insert(1);
        t1.insert(6);
        t1.insert(8);
        t1.insert(10);
        return t1;
    }
}