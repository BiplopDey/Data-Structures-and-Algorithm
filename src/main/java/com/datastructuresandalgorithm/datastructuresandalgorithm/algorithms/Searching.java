package com.datastructuresandalgorithm.datastructuresandalgorithm.algorithms;

public class Searching {

    public static int jumpSearch(int[] v, int value) {
        int size = (int) Math.sqrt(v.length);

        int bound = 0;
        while (bound < v.length && value > v[bound])
            bound += size;

        int left = bound -size;
        int right = Math.min(bound, v.length - 1);
        return linearSearch(v, left, right, value);
    }

    public static int linearSearch(int[] array, int left, int right, int target) {
        for (int i = left; i <= right; i++)
            if (array[i] == target)
                return i;
        return -1;
    }

    public static int exponentialSearch(int[] v, int value) {
        int prevBound = 0;
        int bound = 0;
        while (bound < v.length && value > v[bound])
            bound *= 2;

        int left = bound / 2;
        int right = Math.min(bound, v.length - 1);
        return binarySearchRec(v, bound);
    }


    static int binarySearchRec(int[] v, int value){
        return binarySearchRec(v, value, 0, v.length - 1);
    }
    static int binarySearchRec(int[] v, int value, int start, int end) {
        if (start > end)
            return -1;

        int mid = (start + end) / 2;
        if (value == v[mid])
            return mid;

        if (value < v[mid])
            return binarySearchRec(v, value, start, mid - 1);

        return binarySearchRec(v, value, mid + 1, end);
    }

    static int binarySearchIter(int[] v, int value) {
        int start = 0, end = v.length - 1, mid;
        while (start <= end) {
            mid = (start + end) / 2;
            if (value == v[mid])
                return mid;
            else if (value < v[mid])
                end = mid - 1;
            else
                start = mid + 1;
        }

        return -1;
    }

    static int ternarySearchRec(int[] v, int value){
        return ternarySearchRec(v, value, 0, v.length - 1);
    }
    static int ternarySearchRec(int[] v, int value, int start, int end) {
        if (start > end)
            return -1;

        int partitionSize= (start-end)/3;
        int mid1 = start + partitionSize;
        int mid2 = end - partitionSize;

        if (value == v[mid1])
            return mid1;
        if (value == v[mid2])
            return mid2;

        if (value < v[mid1])
            return binarySearchRec(v, value, start, mid1 - 1);
        if ( v[mid1]<value && value<v[mid2] )
            return binarySearchRec(v, value, mid1+1, mid2 - 1);
        return binarySearchRec(v, value, mid2 + 1, end);
    }

}
