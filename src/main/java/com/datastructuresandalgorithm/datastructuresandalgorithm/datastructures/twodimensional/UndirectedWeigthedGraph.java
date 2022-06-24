package com.datastructuresandalgorithm.datastructuresandalgorithm.datastructures.twodimensional;

import java.util.*;

public class UndirectedWeigthedGraph{
    private class Node {
        private String label;
        private Set<Edge> edges = new HashSet<>();
        private Set<Node> children = new HashSet<>();

        public Node(String label) {
            this.label = label;
        }

        public void addEdge(Node to, int weight) {
            children.add(to);
            edges.add(new Edge(this, to, weight));
        }

        public boolean hasEdge(Edge edge) {
            return edges.contains(edge);
        }

        public List<Edge> getEdges() {
            return new ArrayList<Edge>(edges);
        }

        public List<Node> getChildren() {
            return new ArrayList<Node>(children);
        }

        @Override
        public String toString() {
            return label;
        }
    }

    private class Edge {
        private Node from, to;
        private int weight;

        public Edge(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return from + "->" + to + ":" + weight;
        }
    }

    Map<String, Node> nodes = new HashMap<>();

    private class NodeEntry {
        private Node node;
        private int priority;

        public NodeEntry(Node node, int priority) {
            this.node = node;
            this.priority = priority;
        }
    }

    public UndirectedWeigthedGraph getMinimumSpanningTree(){
        UndirectedWeigthedGraph tree = new UndirectedWeigthedGraph();
        PriorityQueue<Edge> edges =  new PriorityQueue<>(
                Comparator.comparingInt(e -> e.weight)
        );
        Node startNode = nodes.values().iterator().next();
        edges.addAll(startNode.getEdges());
        tree.addNode(startNode.label);

        while(tree.nodes.size() < nodes.size()){
            Edge minEdge = edges.remove();
            Node nextNode = minEdge.to;

            if(tree.containsNode(nextNode.label))
                continue;

            tree.addNode(nextNode.label);
            tree.addEdge(minEdge.from.label,
                    nextNode.label,
                    minEdge.weight);

            for(Edge edge: nextNode.getEdges())
                if(!tree.containsNode(edge.to.label))
                    edges.add(edge);
        }

        return tree;
    }

    public boolean containsNode(String label){
        return nodes.containsKey(label);
    }

    public int getShortestDistance(String from, String to){
        Node fromNode = nodes.get(from);
        Map<Node, Integer> distances = new HashMap<>();
        for(Node node : nodes.values())
            distances.put(node, Integer.MAX_VALUE);

        distances.replace(fromNode, 0);

        Set<Node> visited = new HashSet<>();

        PriorityQueue<NodeEntry> queue =  new PriorityQueue<>(
                Comparator.comparingInt(ne -> ne.priority)
        );
        queue.add(new NodeEntry(fromNode, 0));

        while(!queue.isEmpty()){
            Node current = queue.remove().node;
            visited.add(current);

            for(Edge edge: current.getEdges()){
                if(visited.contains(edge.to))
                    continue;

                int newDistance = distances.get(current) + edge.weight;
                if( newDistance < distances.get(edge.to)){
                    distances.replace(edge.to, newDistance);
                    queue.add(new NodeEntry(edge.to, newDistance));
                }
            }
        }

        return distances.get(nodes.get(to));
    }

    public List<Node> getShortestPath(String from, String to){
        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);

        Map<Node, Integer> distances = new HashMap<>();
        for(Node node : nodes.values())
            distances.put(node, Integer.MAX_VALUE);
        distances.replace(fromNode, 0);

        Map<Node, Node> previousNodes = new HashMap<>();

        Set<Node> visited = new HashSet<>();

        PriorityQueue<NodeEntry> queue =  new PriorityQueue<>(
                Comparator.comparingInt(ne -> ne.priority)
        );
        queue.add(new NodeEntry(fromNode, 0));
        while(!queue.isEmpty()){
            Node current = queue.remove().node;
            visited.add(current);

            for(Edge edge: current.getEdges()){
                if(visited.contains(edge.to))
                    continue;

                int newDistance = distances.get(current) + edge.weight;
                if( newDistance < distances.get(edge.to)){
                    distances.replace(edge.to, newDistance);
                    previousNodes.put(edge.to, current);
                    queue.add(new NodeEntry(edge.to, newDistance));
                }

            }
        }

        Stack<Node> stack = new Stack<>();
        Node previous = toNode;
        stack.add(previous);
        while(previous!=null){
            previous = previousNodes.get(previous);
            stack.add(previous);
        }

        List<Node> path = new ArrayList<>();
        while(!stack.isEmpty()){
            path.add(stack.pop());
        }

        return path;
    }

    public boolean hasCycle() {
        Set<Node> visited = new HashSet<>();

        for (var node : nodes.values())
            if (!visited.contains(node) && hasCycle(node, null, visited))
                return true;

        return false;
    }

    private boolean hasCycle(Node node, Node parent, Set<Node> visited) {
        visited.add(node);

        for (Node child : node.getChildren()) {
            if (child == parent)
                continue;

            if (visited.contains(child) || hasCycle(child, node, visited))
                return true;
        }

        return false;
    }

    public void addNode(String label) {
        nodes.putIfAbsent(label, new Node(label));
    }

    public void addEdge(String from, String to, int weight) {
        Node fromNode = nodes.get(from), toNode = nodes.get(to);

        if (fromNode == null || toNode == null)
            throw new IllegalArgumentException();

        Edge FromEdge = new Edge(fromNode, toNode, weight);
        if (fromNode.hasEdge(FromEdge))
            return;

        fromNode.addEdge(toNode, weight);
        toNode.addEdge(fromNode, weight);
    }

    public void print() {
        for (Node node : nodes.values()) {
            List<Edge> targets = node.getEdges();
            if (!targets.isEmpty())
                System.out.println(node + " is connected to " + targets);
        }
    }

}