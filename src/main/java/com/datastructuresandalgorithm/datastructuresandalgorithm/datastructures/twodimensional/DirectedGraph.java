package com.datastructuresandalgorithm.datastructuresandalgorithm.datastructures.twodimensional;

import java.util.*;

public class DirectedGraph{
    private class Node {
        private String label;

        public Node(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    Map<String, Node> nodes = new HashMap<>();
    Map<Node, List<Node>> adjacencyList = new HashMap<>();

    public boolean hasCycle() {
        Set<Node> all = new HashSet<>();
        all.addAll(nodes.values());

        Set<Node> visiting = new HashSet<>();
        Set<Node> visited = new HashSet<>();

        while (!all.isEmpty()) {
            var current = all.iterator().next();
            if (hasCycle(current, all, visiting, visited))
                return true;
        }

        return false;
    }

    private boolean hasCycle(Node node, Set<Node> all,
                             Set<Node> visiting, Set<Node> visited) {
        all.remove(node);
        visiting.add(node);

        for (var neighbour : adjacencyList.get(node)) {
            if (visited.contains(neighbour))
                continue;

            if (visiting.contains(neighbour))
                return true;

            if (hasCycle(neighbour, all, visiting, visited))
                return true;
        }

        visiting.remove(node);
        visited.add(node);

        return false;
    }


    public boolean hasCycleEficient() {
        Set<Node> visiting = new HashSet<Node>();
        boolean b = true;

        for (Node node : nodes.values())
            if (!visiting.contains(node))
                b &= hasCycleEficient(node, visiting);

        return b;
    }

    private boolean hasCycleEficient(Node node, Set<Node> visiting) {
        if (visiting.contains(node))
            return true;
        visiting.add(node);

        if (adjacencyList.get(node).isEmpty())
            return false;

        boolean b = true;
        for (Node neighbours : adjacencyList.get(node))
            b &= hasCycleEficient(neighbours, visiting);

        return b;
    }

    public List<String> topologicalSort() {
        Set<Node> visited = new HashSet<>();
        Stack<Node> stack = new Stack<>();
        for (Node node : nodes.values())
            topologicalSort(node, visited, stack);

        List<String> sorted = new ArrayList<>();
        while (!stack.isEmpty())
            sorted.add(stack.pop().label);

        return sorted;
    }

    private void topologicalSort(Node node, Set<Node> visited, Stack<Node> stack) {
        if (visited.contains(node))
            return;

        visited.add(node);

        for (Node neighbour : adjacencyList.get(node))
            topologicalSort(neighbour, visited, stack);

        stack.push(node);
    }

    public void breathFirst(String label) {
        Node node = nodes.get(label);
        if (node == null)
            throw new IllegalArgumentException();

        Queue<Node> queue = new ArrayDeque<>();
        Set<Node> visited = new HashSet<>();

        queue.add(node);
        while (!queue.isEmpty()) {
            Node current = queue.remove();
            if (visited.contains(current))
                continue;

            System.out.print(current + " ");
            visited.add(current);
            for (Node neighbour : adjacencyList.get(current))
                if (!visited.contains(neighbour))
                    queue.add(neighbour);
        }
    }

    public void depthFirst(String label) {
        Set<Node> visited = new HashSet<>();
        Node node = nodes.get(label);
        if (node == null)
            throw new IllegalArgumentException();

        System.out.println();
        depthFirstIt(node);
    }

    private void depthFirstIt(Node node) {
        Stack<Node> stack = new Stack<>();
        Set<Node> visited = new HashSet<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node current = stack.pop();
            if (visited.contains(current))
                continue;

            System.out.print(current + " ");
            visited.add(current);
            for (Node neighbour : adjacencyList.get(current))
                if (!visited.contains(neighbour))
                    stack.add(neighbour);
        }
    }

    private void depthFirstRec(Node node, Set<Node> visited) {
        visited.add(node);
        System.out.print(node + " ");

        for (Node n : adjacencyList.get(node))
            if (!visited.contains(n))
                depthFirstRec(n, visited);
    }

    public void addNode(String label) {
        Node node = new Node(label);
        nodes.putIfAbsent(label, node);
        adjacencyList.putIfAbsent(node, new LinkedList<>());
    }

    public void addEdge(String from, String to) {
        Node fromNode = nodes.get(from), toNode = nodes.get(to);

        if (fromNode == null || toNode == null)
            throw new IllegalArgumentException();

        List<Node> fromNodeList = adjacencyList.get(fromNode);
        if (fromNodeList.contains(toNode))
            return;
        fromNodeList.add(toNode);
    }

    public void print() {
        for (Node node : adjacencyList.keySet()) {
            List<Node> targets = adjacencyList.get(node);
            if (!targets.isEmpty())
                System.out.println(node + " is connected to " + targets);
        }
    }

    public void removeNode(String label) {
        Node node = nodes.get(label);
        if (node == null)
            return;

        for (Node n : adjacencyList.keySet())
            adjacencyList.get(n).remove(n);

        adjacencyList.remove(node);
        nodes.remove(label);
    }

    public void removeEdge(String from, String to) {
        Node fromNode = nodes.get(from), toNode = nodes.get(to);
        if (fromNode == null || toNode == null)
            return;

        adjacencyList.get(fromNode).remove(toNode);
    }
}