package com.datastructuresandalgorithm.datastructuresandalgorithm.pruebas;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FizzBuzz {
    @Test
    void test() {
        System.out.println(1%3);
        System.out.println(fizzBuzz(3));
    }

    public List<String> fizzBuzz(int n) {
      //  List<String> list = new ArrayList<>();
      //  for(int i=1; i <= n; i++)
      //      list.add(process(i));

        return IntStream.range(1,n+1).mapToObj(i->process(i)).collect(Collectors.toList());
    }

    String process(int n){
        String result = "";
        if(n%3==0)
            result = "Fizz";

        if(n%5==0)
            result += "Buzz";

        return result.isEmpty() ? String.valueOf(n) : result;
    }
}
