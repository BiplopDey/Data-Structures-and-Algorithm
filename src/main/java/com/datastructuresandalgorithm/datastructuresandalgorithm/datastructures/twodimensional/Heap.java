package com.datastructuresandalgorithm.datastructuresandalgorithm.datastructures.twodimensional;

import java.util.ArrayList;
import java.util.List;

public class Heap {
    List<Integer> items = new ArrayList<>();// make it private after testing
    public boolean isEmpty(){
        return items.isEmpty();
    }
    public int remove() {
        int root = items.get(0);
        items.set(0, items.get(items.size() - 1));
        items.remove(items.size() - 1);
        bubleDown();

        return root;
    }

    public void bubleDown() {
        int indexParent = 0;
        while (indexParent < items.size() && !isValidParent(indexParent)) {
            int largerChildIndex = largerChildIndex(indexParent);
            swap(largerChildIndex, indexParent);
            indexParent = largerChildIndex;
        }
    }

    private int largerChildIndex(int index) {
        if (!hasLeftChild(index))
            return index;
        if (!hasRightChild(index))
            return leftChildIndex(index);

        return leftChild(index) > rightChild(index) ? leftChildIndex(index) : rightChildIndex(index);
    }

    private boolean isValidParent(int index) {
        if (!hasLeftChild(index))
            return true;

        int value = items.get(index);
        if (!hasRightChild(index))
            return leftChildIndex(index) <= value;

        return leftChild(index) <= value && rightChild(index) <= value;
    }

    private boolean hasLeftChild(int index) {
        return leftChildIndex(index) < items.size();
    }

    private boolean hasRightChild(int index) {
        return rightChildIndex(index) < items.size();
    }

    private int leftChild(int index) {
        return items.get(leftChildIndex(index));
    }

    private int rightChild(int index) {
        return items.get(rightChildIndex(index));
    }

    private int leftChildIndex(int indexParent) {
        return indexParent * 2 + 1;
    }

    private int rightChildIndex(int indexParent) {
        return indexParent * 2 + 2;
    }

    public void insert(int value) {
        items.add(value);

        bubleUp();
    }

    private void bubleUp() {
        int indexSon = items.size() - 1;
        while (!smallerThanParent(indexSon) && indexSon > 0) {
            swap(indexSon, parentIndex(indexSon));
            indexSon = parentIndex(indexSon);
        }
    }

    private void swap(int first, int second) {
        int value = items.get(first);

        items.set(first, items.get(second));
        items.set(second, value);
    }

    private int parentIndex(int indexSon) {
        return (indexSon - 1) / 2;
    }

    private boolean smallerThanParent(int indexSon) {
        return items.get(indexSon) <= items.get(parentIndex(indexSon));
    }
}