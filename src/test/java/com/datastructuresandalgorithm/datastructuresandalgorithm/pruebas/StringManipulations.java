package com.datastructuresandalgorithm.datastructuresandalgorithm.pruebas;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringManipulations {

    @Test
    public void testIsAnagram() {
        assertTrue(isAnagram("Listen", "Silent"));
        assertFalse(isAnagram("Hello world", "Rowed loll"));
        assertTrue(isAnagram("Funeral", "Real fun"));
        assertFalse(isAnagram("This is longer", "Short"));
    }
    boolean isAnagram(String str1, String str2){
        if(str1 == null || str2 == null)
            return false;
        Map<Character, Integer> frequencies = new HashMap<>();
        str1 = toLowerCaseAndRemoveSpace(str1);
        str2 = toLowerCaseAndRemoveSpace(str2);

        for(var ch : str1.toCharArray())
            frequencies.put(ch,frequencies.getOrDefault(ch,0)+1);


        for(var ch : str2.toCharArray()){
            if(!frequencies.containsKey(ch))
                return false;
            frequencies.put(ch,frequencies.get(ch)-1);
        }

        for(var entry : frequencies.entrySet())
            if(entry.getValue() != 0)
                return false;

        return true;
    }

    private static String toLowerCaseAndRemoveSpace(String str1) {
        return str1.toLowerCase().replaceAll("\\s+", "");
    }
}
