package com.lordbao.graph.weightedEdgeGraph;


/**
 * @Author Lord_Bao
 * @Date 2024/11/11 10:59
 * @Version 1.0
 *
 * 节点下标从0开始
 */
public interface WeightedEdgeGraph {
    /**添加边 v-w，weight是v-w的权值,并赋予权值*/
    public void addEdge(int v,int w,int weight);
    /**返回与节点v相连的所有边*/
    public Iterable<WeightedEdge> adj(int v);
    /**返回节点个数*/
    public int V();
    /**返回节点边数*/
    public int E();
}
