package com.datastructuresandalgorithm.datastructuresandalgorithm.datastructures.twodimensional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UndirectedWeigthedGraphTest {

    @Test
    void test() {
        System.out.println("\n Test cycle detection");
        UndirectedWeigthedGraph graph = new UndirectedWeigthedGraph();
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "C", 1);
        System.out.println("Should be false: " + graph.hasCycle());

        graph.addEdge("A", "C", 1);
        System.out.println("Should be true: " + graph.hasCycle());
    }
}