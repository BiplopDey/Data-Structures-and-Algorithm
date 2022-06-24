package com.datastructuresandalgorithm.datastructuresandalgorithm.datastructures.onedimensional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackArrayImpTest {

    @Test
    void test(){
        Stack stack = new StackArrayImpl(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push(8);
        stack.push(9);
        stack.push(10);
        assertEquals(10, stack.peek());
        assertEquals(10, stack.pop());
        assertEquals(9, stack.pop());
        assertEquals(8, stack.pop());
        assertEquals(7, stack.pop());
        assertEquals(6, stack.pop());
        assertEquals(5, stack.pop());
        assertEquals(4, stack.pop());
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        assertTrue(stack.isEmpty());
    }

}