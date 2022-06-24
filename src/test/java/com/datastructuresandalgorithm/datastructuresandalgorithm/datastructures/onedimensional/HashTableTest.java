package com.datastructuresandalgorithm.datastructuresandalgorithm.datastructures.onedimensional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    @Test
    void test(){
        HashTable table = new HashTable();
        table.put(1,"hola");
        table.put(2,"mundo");
        table.put(30,"hello");
        table.put(10,"world");

        assertEquals("hola", table.get(1));
        assertEquals("mundo", table.get(2));
        assertEquals("hello", table.get(30));
        assertEquals("world", table.get(10));


        table.put(1,"hello");
        assertEquals("hello", table.get(1));

        table.remove(10);
        assertNull(table.get(10));
    }
}