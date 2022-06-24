package com.datastructuresandalgorithm.datastructuresandalgorithm.datastructures.twodimensional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {
    @Test
    void test(){
        Trie t = new Trie();
        t.insert("word");

        System.out.println(t.contains("word"));
        System.out.println(t.contains("wor"));
        t.insert("wor");
        System.out.println(t.contains("wor"));
        System.out.println("test traversal------");
        t.traverse();

        System.out.println("test delete------");
        t = new Trie();
        t.insert("word");
        t.insert("wor");
        t.delete("words");

        System.out.println(t.contains("word"));
    }
}