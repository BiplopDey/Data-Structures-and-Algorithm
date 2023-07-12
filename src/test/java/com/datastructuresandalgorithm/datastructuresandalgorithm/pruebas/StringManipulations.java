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

        int[] frequencies = new int[256];
        str1 = toLowerCaseAndRemoveSpace(str1);
        str2 = toLowerCaseAndRemoveSpace(str2);

        for(var ch : str1.toCharArray())
           frequencies[ch]++;

        for(var ch : str2.toCharArray())
            frequencies[ch]--;

        for(var f : frequencies)
            if(f != 0)
                return false;

        return true;
    }

    private static String toLowerCaseAndRemoveSpace(String str1) {
        return str1.toLowerCase().replaceAll("\\s+", "");
    }
}
