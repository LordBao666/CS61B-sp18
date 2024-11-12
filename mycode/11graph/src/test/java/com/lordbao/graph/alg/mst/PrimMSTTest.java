package com.lordbao.graph.alg.mst;

import com.lordbao.graph.weightedEdgeGraph.WeightedEdgeUnDirectedGraph;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Lord_Bao
 * @Date 2024/11/12 12:36
 * @Version 1.0
 */
class PrimMSTTest {

    @Test
    void test1() {
        int size =4;
        WeightedEdgeUnDirectedGraph graph = new WeightedEdgeUnDirectedGraph(size);
        graph.addEdge(0,1,2);
        graph.addEdge(0,2,1);
        graph.addEdge(1,2,1);
        graph.addEdge(2,3,3);
        PrimMST primMST = new PrimMST(graph);
        System.out.println(primMST.getTotalCost());
        primMST.printMST();
    }

    @Test
    void test2() {
        int size =7;
        WeightedEdgeUnDirectedGraph graph = new WeightedEdgeUnDirectedGraph(size);
        graph.addEdge(0,1,2);
        graph.addEdge(0,2,1);
        graph.addEdge(1,2,5);
        graph.addEdge(1,3,11);
        graph.addEdge(1,4,3);
        graph.addEdge(2,4,1);
        graph.addEdge(2,5,15);
        graph.addEdge(3,4,2);
        graph.addEdge(3,6,1);
        graph.addEdge(4,5,4);
        graph.addEdge(4,6,3);
        graph.addEdge(5,6,1);
        PrimMST primMST = new PrimMST(graph);
        System.out.println(primMST.getTotalCost());
        primMST.printMST();
    }
}