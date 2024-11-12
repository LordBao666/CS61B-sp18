package com.lordbao.graph;

import com.lordbao.graph.weightedEdgeGraph.WeightedEdgeDirectedGraph;
import com.lordbao.graph.weightedEdgeGraph.WeightedEdgeUnDirectedGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @Author Lord_Bao
 * @Date 2024/11/11 11:21
 * @Version 1.0
 */
public class WeightedEdgeGraphTest {

    @Test
    public void unDiretecGraph(){
        int size =4;
        WeightedEdgeDirectedGraph graph = new WeightedEdgeDirectedGraph(size);
        graph.addEdge(0,1,5);
        graph.addEdge(0,2,1);
        graph.addEdge(1,0,3);
        graph.addEdge(1,3,2);
        graph.addEdge(2,1,1);
        graph.addEdge(2,3,5);

        Assertions.assertSame(4,graph.V());
        Assertions.assertSame(6,graph.E());
        System.out.println(graph.adj(2));
        graph.print();
    }

    @Test
    public void DirectedGraph(){
        int size =4;
        WeightedEdgeUnDirectedGraph graph = new WeightedEdgeUnDirectedGraph(size);
        graph.addEdge(0,1,5);
        graph.addEdge(0,2,1);
        graph.addEdge(1,3,2);
        graph.addEdge(1,2,1);
        graph.addEdge(2,3,5);

        Assertions.assertSame(4,graph.V());
        Assertions.assertSame(5,graph.E());
        System.out.println(graph.adj(2));
        graph.print();
    }
}