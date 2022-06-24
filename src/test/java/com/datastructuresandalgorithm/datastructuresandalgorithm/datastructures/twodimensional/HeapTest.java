package com.datastructuresandalgorithm.datastructuresandalgorithm.datastructures.twodimensional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapTest {
    @Test
    void test(){
        System.out.println("TestInsert");
        Heap heap = init();
        System.out.println(heap.items);

        System.out.println("Test Order");
        heap = init();
        while (!heap.isEmpty())
            System.out.println(heap.remove());

        System.out.println("Test-------remove");
        heap = init();
        heap.remove();
        System.out.println(heap.items);

        System.out.println("Test-------getKthLargest");
        System.out.println(getKthLargest(
                new int[] { 5, 3, 8, 4, 1, 2 }, 2));
    }


    static Heap init() {
        Heap heap = new Heap();
        heap.insert(10);
        heap.insert(5);
        heap.insert(17);
        heap.insert(4);
        heap.insert(22);
        return heap;
    }

     int getKthLargest(int[] array, int k) {
        Heap heap = new Heap();
        for (int i : array)
            heap.insert(i);

        for (int i = 1; i <= k - 1; i++)
            heap.remove();

        return heap.remove();
    }

     void heapify(int[] array){
        for (int i = 0; i < array.length; i++) {
            heapify(array, i);
        }
    }

     void heapify(int[] array, int index){
        int leftChildIndex = index*2+1;
        int rightChildIndex = index*2+2;
        int largerIndex = index;

        if(leftChildIndex < array.length && array[leftChildIndex]> array[index])
            largerIndex = leftChildIndex;

        if(leftChildIndex < array.length && array[rightChildIndex]> array[index])
            largerIndex = rightChildIndex;

        swap(array, index, largerIndex);
        heapify(array, largerIndex);
    }

     void swap(int[] array, int first, int secod){
        int fistValue = array[first];
        array[first] = array[secod];
        array[secod] = fistValue;
    }
}