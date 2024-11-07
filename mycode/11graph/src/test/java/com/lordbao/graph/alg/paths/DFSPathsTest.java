package com.lordbao.graph.alg.paths;

import com.lordbao.graph.Graph;
import com.lordbao.graph.SimpleGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Lord_Bao
 * @Date 2024/11/7 14:58
 * @Version 1.0
 */
class DFSPathsTest {
    private DFSPaths paths;
    @BeforeEach
    void setUp(){
        Graph graph = new SimpleGraph(9);
        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(1,4);
        graph.addEdge(2,5);
        graph.addEdge(3,4);
        graph.addEdge(4,5);
        graph.addEdge(5,6);
        graph.addEdge(5,8);
        graph.addEdge(6,7);

        paths = new DFSPaths(graph,0);
    }

    @Test
    void hasPathTo() {
        Assertions.assertTrue(paths.hasPathTo(1));
        Assertions.assertTrue(paths.hasPathTo(2));
    }

    @Test
    void pathTo() {
        System.out.println(paths.pathTo(0));
        System.out.println(paths.pathTo(1));
        System.out.println(paths.pathTo(2));
        System.out.println(paths.pathTo(3));
        System.out.println(paths.pathTo(4));
        System.out.println(paths.pathTo(5));
        System.out.println(paths.pathTo(6));
        System.out.println(paths.pathTo(7));
        System.out.println(paths.pathTo(8));

    }
}