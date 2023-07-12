package com.datastructuresandalgorithm.datastructuresandalgorithm.pruebas;

import java.util.*;
import java.util.stream.Collectors;

public class Prueba {

    public int longestConsecutive(int[] nums) {
        if(nums == null) throw new IllegalArgumentException();
        if(nums.length == 0) return 0;

        int longestSeq=1;
        Set<Integer> set = mapIntArrayToSet(nums);

        for(int num : set)
            if(isStartOfSequence(set, num))
                longestSeq = Math.max(longestSeq, getSeqLength(set, num));

        return longestSeq;
    }

    int getSeqLength(Set<Integer> set, int num) {
        int seqLength = 0;
        int nextNum = num;
        while(set.contains(nextNum)){
            nextNum++;
            seqLength++;
        }
        return seqLength;
    }

    boolean isStartOfSequence(Set<Integer> set, int num) {
        return !set.contains(num - 1);
    }

    Set<Integer> mapIntArrayToSet(int[] nums){
        if(nums==null)
            throw new IllegalArgumentException();
        return Arrays.stream(nums).boxed().collect(Collectors.toSet());
    }
}
