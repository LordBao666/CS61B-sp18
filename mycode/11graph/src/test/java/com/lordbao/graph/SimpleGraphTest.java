package com.lordbao.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Lord_Bao
 * @Date 2024/11/7 12:37
 * @Version 1.0
 */

class SimpleGraphTest {

    @Test
    void testGraph() {
        SimpleGraph graph = new SimpleGraph(9);
        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(1,4);
        graph.addEdge(2,5);
        graph.addEdge(3,4);
        graph.addEdge(4,5);
        graph.addEdge(5,6);
        graph.addEdge(5,8);
        graph.addEdge(6,7);

        //添加重复边，查看是否影响简单图
        graph.addEdge(5,8);
        graph.addEdge(6,7);

        Assertions.assertEquals(9,graph.V());
        Assertions.assertEquals(9,graph.E());
        Assertions.assertEquals(Arrays.asList(2,4,6,8),new ArrayList<Integer>((Collection<? extends Integer>) graph.adj(5)));


        Graph.print(graph);
    }



}