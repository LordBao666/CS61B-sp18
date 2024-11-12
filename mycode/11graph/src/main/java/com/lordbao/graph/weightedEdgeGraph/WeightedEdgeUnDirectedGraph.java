package com.lordbao.graph.weightedEdgeGraph;


/**
 * @Author Lord_Bao
 * @Date 2024/11/11 11:19
 * @Version 1.0
 * 带权边无向图
 */
public class WeightedEdgeUnDirectedGraph extends WeightedEdgeDirectedGraph {
    public WeightedEdgeUnDirectedGraph(int size) {
        super(size);
    }

    @Override
    public void addEdge(int v, int w, int weight) {
        super.addEdge(v, w, weight);
        super.addEdge(w,v,weight);
    }

    @Override
    public int E() {
        return super.E()>>1;
    }
}
