package com.datastructuresandalgorithm.datastructuresandalgorithm.datastructures.onedimensional;

public class QueueArrayImpl implements Queue {
    private int F, R;//front and rear, inclusives indexes.
    private int count=0;
    private int[] v;
    private int size;

    public QueueArrayImpl(int size) {
        this.size = size;
        F = 0;
        R = -1;
        v = new int[size];
    }

    @Override
    public void enqueue(int data) {
        if (isFull())
            throw new IllegalStateException();

        R=(R+1)%size;
        v[R] = data;
        count++;
    }

    @Override
    public int dequeue() {
        if (isEmpty())
            throw new IllegalStateException();

        int value = v[F];
        F=(F+1)%size;
        count--;
        return value;
    }

    @Override
    public boolean isEmpty(){
        return count == 0;
    }

    public boolean isFull(){
        return count == size;
    }
}

