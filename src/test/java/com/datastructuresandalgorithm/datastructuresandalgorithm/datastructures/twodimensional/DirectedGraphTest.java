package com.datastructuresandalgorithm.datastructuresandalgorithm.datastructures.twodimensional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectedGraphTest {
    @Test
    void test(){
        System.out.println("\n Test cycle detection");
        DirectedGraph graph = new DirectedGraph();
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "A");
        graph.addEdge("D", "A");
        System.out.println("Should be true: " + graph.hasCycle());
        graph.removeEdge("C", "A");
        graph.addEdge("A", "C");
        System.out.println("Should be false: " + graph.hasCycle());
    }
}