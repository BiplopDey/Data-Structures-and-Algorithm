package com.datastructuresandalgorithm.datastructuresandalgorithm.datastructures.onedimensional;

import java.util.NoSuchElementException;

public class StackArrayImpl implements Stack {
    private int top;
    private int[] v;

    StackArrayImpl(int size) {
        top = -1;
        v = new int[size];
    }

    public void push(int data) {
        if(v.length-1 >= top)
            duplicateArraySize();

        v[++top] = data;
    }

    public int pop() {
        if (isEmpty())
            throw new NoSuchElementException();

        int data = v[top--];
        return data;
    }

    @Override
    public int peek(){
        return v[top];
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    private void duplicateArraySize(){
        int[] u = new int[v.length*2];
        for (int i = 0; i < v.length; i++)
            u[i] = v[i];
        v=u;
    }
}

