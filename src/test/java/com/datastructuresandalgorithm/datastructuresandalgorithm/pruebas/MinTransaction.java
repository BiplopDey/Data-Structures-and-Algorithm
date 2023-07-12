package com.datastructuresandalgorithm.datastructuresandalgorithm.pruebas;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MinTransaction {
    class Balance {
        private final Map<String, Double> balance = new HashMap<>();
        public Balance(){
        }

        public void addTransaction(String payer, List<String> participants, Double amount) {
            Double eachShare = amount / participants.size();

            addIfNewParticipant(participants);

            participants.forEach(p->{
                balance.put(p, balance.get(p)+eachShare);
            });
            balance.put(payer, balance.get(payer)-amount);
        }

        public Double balanceOf(String name) {
            if(!balance.containsKey(name))
                throw new IllegalArgumentException();
            return balance.get(name);
        }

        private void addIfNewParticipant(List<String> participants) {
            participants.forEach(p->{
                if(!balance.containsKey(p)) balance.put(p, (double) 0);
            });
        }

        public List<Transaction> getTransactions() {
            List<Transaction> transactions = new ArrayList<>();
            Map<String, Double> map = new HashMap<>(balance);
            //[-350,..,20]
            //[-5,...,20]
            while (!areAllBalanceZero(map)){
                String maxPerson = getMax(map);
                String minPerson = getMin(map);
                Double min = map.get(minPerson), max = map.get(maxPerson);
                if(abs(min)>=max){
                    map.put(maxPerson, (double) 0);
                    map.put(minPerson, min+max);
                    transactions.add(new Transaction(maxPerson, minPerson, max));
                }
                if(abs(min)<max){
                    map.put(maxPerson, min+max);
                    map.put(minPerson, (double) 0);
                    transactions.add(new Transaction(maxPerson, minPerson, abs(min)));
                }
            }
            
            return transactions;
        }

        private boolean areAllBalanceZero(Map<String, Double> map) {
            for(Map.Entry<String, Double> e : map.entrySet()){
                if(abs(e.getValue()) > Double.MIN_VALUE)
                    return false;
            }
            return true;
        }

        private String getMin(Map<String, Double> map) {
            return map.entrySet().stream()
                    .min(Comparator.comparing(Map.Entry::getValue))
                    .get()
                    .getKey();
        }

        private String getMax(Map<String, Double> map) {
            return map.entrySet().stream()
                    .max(Comparator.comparing(Map.Entry::getValue))
                    .get()
                    .getKey();
        }
    }

    class Transaction{
        final String from;
        final String to;
        final Double amount;

        public Transaction(String from, String to, Double amount){
            this.from = from;
            this.to = to;
            this.amount = amount;
        }

        @Override
        public String toString(){
            return "From "+from+" To " + to + " Amount "+ amount;
        }
    }

    @Test
    public void testGetBalanceOf(){
        Balance balance = new Balance();
        // ana -> buy to every one 400
        // jon -> buy 100 to bob and mar
        balance.addTransaction("Ana", List.of("Ana","Jon","Bob","Mar"), Double.valueOf(400));
        balance.addTransaction("Jon", List.of("Bob","Mar"), Double.valueOf(100));

        assertEquals(Double.valueOf(0), balance.balanceOf("Jon"));
        assertEquals(Double.valueOf(150), balance.balanceOf("Bob"));
        assertEquals(Double.valueOf(150), balance.balanceOf("Mar"));
        assertEquals(Double.valueOf(-300), balance.balanceOf("Ana"));
        assertThrows(IllegalArgumentException.class, ()->balance.balanceOf("Dave"));
    }

    @Test
    public void testMinimumTransaction(){
        Balance balance = new Balance();

        balance.addTransaction("Ana", List.of("Ana","Jon","Bob","Mar"), Double.valueOf(400));
        balance.addTransaction("Jon", List.of("Bob","Mar"), Double.valueOf(150));

     //   assertEquals(2,balance.getTransactions().size());
        balance.getTransactions().forEach(p->System.out.println(p));
    }

}
