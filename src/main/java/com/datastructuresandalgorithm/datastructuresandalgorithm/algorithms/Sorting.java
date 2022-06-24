package com.datastructuresandalgorithm.datastructuresandalgorithm.algorithms;

import java.util.*;

public class Sorting {


    static void bucketSort(int[] array, int numberOfBuckets) {
        List<List<Integer>> buckets = new ArrayList<>();

        for (int i = 0; i < numberOfBuckets; i++)
            buckets.add(new ArrayList<>());

        for (int c : array)
            buckets.get(c / numberOfBuckets).add(c);

        int c = 0;
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
            for (int i : bucket)
                array[c++] = i;
        }
    }

    static void countSort(int[] v, int upperBound) {
        int[] array = new int[upperBound + 1];
        for (int i = 0; i < v.length; i++) {
            array[v[i]]++;
        }

        int count = 0;
        for (int j = 0; j<array.length;j++)
            for (int i = 0; i < array[j]; i++)
                v[count++] = j;

    }

    static void quickSort(int[] v){
        quickSort(v, 0, v.length-1);
    }

    static void quickSort(int[] v, int start, int indexPivot) {
        if (start >= indexPivot)
            return;

        int newIndexPivot = partitionSort(v, start, indexPivot);
        quickSort(v, start, newIndexPivot - 1);
        quickSort(v, newIndexPivot + 1, indexPivot);
    }

    static int partitionSort(int[] v, int start, int p) {
        int b = start-1;
        int pivot = v[p];
        for (int i = start; i < p ; i++)
            if(v[i]<pivot)
                swap(v, ++b, i);

        swap(v, ++b, p);
        return b;
    }

    static void swap(int[] v, int i, int j) {
        int temp = v[j];
        v[j] = v[i];
        v[i] = temp;
    }

    static void insertionSort(int[] v) {
        for (int i = 1; i < v.length; i++) {
            insert(v, i);
        }
    }

    static void insert(int[] v, int startingIndex) {
        int n = v[startingIndex];
        int i = startingIndex-1;
        while(i>=0 && v[i] > n){
            v[i+1] = v[i];
            i--;
        }
        v[i+1] = n;
    }

    static void selectionSort(int[] v) {
        int indexOfMin;
        for (int i = 0; i < v.length - 1; i++) {
            indexOfMin = findIndexOfMin(v, i);
            swap(v, i, indexOfMin);
        }
    }

    static int findIndexOfMin(int[] v, int startingIndex) {
        int min = v[startingIndex];
        int index = startingIndex;
        for (int i = startingIndex + 1; i < v.length; i++) {
            if (min > v[i]) {
                min = v[i];
                index = i;
            }
        }
        return index;
    }

    static void bubbleSort(int[] v) {
        boolean swapped = false;
        for (int j = v.length - 1; j > 0; j--) {
            for (int i = 0; i < j; i++)
                if (v[i] > v[i + 1]) {
                    swap(v, i, i + 1);
                    swapped=true;
                }

            if(!swapped) return;
        }
    }


    static int[] mergeShort(int[] v) {
        int n = v.length;
        if (n == 1)
            return v;

        int[] left = mergeShort(Arrays.copyOfRange(v, 0, n / 2));
        int[] right = mergeShort(Arrays.copyOfRange(v, n / 2, n));

        return merge(left, right);
    }

    static int[] merge(int[] left, int[] right) {
        int n = left.length + right.length;
        int[] v = new int[n];
        int headRight = 0;
        int headLeft = 0;
        int tailV = 0;

        while (headLeft < left.length && headRight < right.length)
            if (left[headLeft] <= right[headRight])
                v[tailV++] = left[headLeft++];
            else
                v[tailV++] = right[headRight++];

        while (headLeft < left.length)
            v[tailV++] = left[headLeft++];
        while (headRight < right.length)
            v[tailV++] = right[headRight++];

        return v;
    }
}
