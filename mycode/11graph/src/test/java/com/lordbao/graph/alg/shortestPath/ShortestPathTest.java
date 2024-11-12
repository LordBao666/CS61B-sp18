package com.lordbao.graph.alg.shortestPath;

import com.lordbao.graph.weightedEdgeGraph.WeightedEdgeDirectedGraph;
import com.lordbao.graph.weightedEdgeGraph.WeightedEdgeGraph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Lord_Bao
 * @Date 2024/11/11 12:57
 * @Version 1.0
 */
class ShortestPathTest {

    @Test
    void testShortestPath() {
        int size =7;
        WeightedEdgeGraph graph = new WeightedEdgeDirectedGraph(size);
        graph.addEdge(0,1,2);
        graph.addEdge(0,2,1);
        graph.addEdge(1,2,5);
        graph.addEdge(1,3,11);
        graph.addEdge(1,4,3);
        graph.addEdge(2,5,15);
        graph.addEdge(3,4,2);
        graph.addEdge(4,2,1);
        graph.addEdge(4,5,4);
        graph.addEdge(4,6,5);
        graph.addEdge(6,3,1);
        ShortestPath shortestPath = new ShortestPath(graph, 0);

        System.out.println(shortestPath.getShortestDistanceTo(2));
        System.out.println(shortestPath.getShortestPathTo(2));

        System.out.println(shortestPath.getShortestDistanceTo(5));
        System.out.println(shortestPath.getShortestPathTo(5));

        System.out.println(shortestPath.getShortestDistanceTo(3));
        System.out.println(shortestPath.getShortestPathTo(3));
    }


}