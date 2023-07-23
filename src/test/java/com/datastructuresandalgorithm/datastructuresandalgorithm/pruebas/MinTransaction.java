package com.datastructuresandalgorithm.datastructuresandalgorithm.pruebas;

import org.junit.jupiter.api.Test;

import java.util.*;

import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MinTransaction {
    class Balance {
        private final Map<String, Double> balance = new HashMap<>();

        public Balance() {
        }

        public void addTransaction(String payer, List<String> participants, Double amount) {
            var eachShare = amount / participants.size();

            addIfNewParticipant(participants);

            participants.forEach(p -> balance.put(p, balance.get(p) + eachShare));
            balance.put(payer, balance.get(payer) - amount);
        }

        public Double balanceOf(String name) {
            if (!balance.containsKey(name))
                throw new IllegalArgumentException();
            return balance.get(name);
        }

        private void addIfNewParticipant(List<String> participants) {
            participants.stream().filter(p -> !balance.containsKey(p)).forEach(p -> balance.put(p, 0.0));
        }

        public List<Transaction> getTransactions() {
            var transactions = new ArrayList<Transaction>();
            var map = new HashMap<>(balance);
            //[-350,..,20]
            //[-5,..,20]
            while (!areAllBalanceZero(map)) {
                var max = getMax(map);
                var min = getMin(map);
                var transfer = Math.min(abs(min.getValue()), max.getValue());
                map.put(max.getKey(), max.getValue() - transfer);
                map.put(min.getKey(), min.getValue() + transfer);
                transactions.add(new Transaction(max.getKey(), min.getKey(), transfer));
            }

            return transactions;
        }

        private boolean areAllBalanceZero(Map<String, Double> map) {
            for (Map.Entry<String, Double> e : map.entrySet()) {
                if (abs(e.getValue()) > Double.MIN_VALUE)
                    return false;
            }
            return true;
        }

        private Map.Entry<String, Double> getMin(Map<String, Double> map) {
            return Collections.min(map.entrySet(), (a,b)->  a.getValue().compareTo(b.getValue()));
        }

        private Map.Entry<String, Double> getMax(Map<String, Double> map) {
            return Collections.max(map.entrySet(), (a,b)->  a.getValue().compareTo(b.getValue()));
        }
    }

    record Transaction(String from, String to, Double amount) {

        @Override
        public String toString() {
            return String.format("From %s to %s amount %s ", from, to, amount);
        }
    }

    @Test
    public void testGetBalanceOf() {
        Balance balance = new Balance();
        // ana -> buy to every one 400
        // jon -> buy 100 to bob and mar
        balance.addTransaction("Ana", List.of("Ana", "Jon", "Bob", "Mar"), 400.0);
        balance.addTransaction("Jon", List.of("Bob", "Mar"), 100.0);

        assertEquals(0.0, balance.balanceOf("Jon"));
        assertEquals(150.0, balance.balanceOf("Bob"));
        assertEquals(150.0, balance.balanceOf("Mar"));
        assertEquals(-300.0, balance.balanceOf("Ana"));
        assertThrows(IllegalArgumentException.class, () -> balance.balanceOf("Dave"));
    }

    @Test
    public void testMinimumTransaction() {
        Balance balance = new Balance();

        balance.addTransaction("Ana", List.of("Ana", "Jon", "Bob", "Mar"), 400.0);
        balance.addTransaction("Jon", List.of("Bob", "Mar"), 150.0);

        //   assertEquals(2,balance.getTransactions().size());
        balance.getTransactions().forEach(System.out::println);
    }

}
