package com.datastructuresandalgorithm.datastructuresandalgorithm.datastructures.onedimensional;

public class PriorityQueue{
    private int[] v = new int[5];
    private int size = 0;

    public void add(int n) {
        //check if is full
        // [1,3,5,7,0,0,0]
        int i;
        for (i = size - 1; i >= 0; i--) {
            if (v[i] <= n)
                break;
            v[i+1] = v[i];
        }
        v[i+1]=n;
        size++;
    }


    public int remove(){
        if(isEmpty())
            throw new IllegalStateException();
        return v[size--];
    }

    public boolean isEmpty(){
        return size == 0;
    }

}