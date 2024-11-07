package com.lordbao.graph;


/**
 * @Author Lord_Bao
 * @Date 2024/11/7 12:18
 * @Version 1.0
 *
 * 所有的节点标号都是从0开始
 */
public interface Graph {
    /**添加边 v-w*/
    public void addEdge(int v,int w);
    /**返回与节点v相连的节点编号*/
    public Iterable<Integer> adj(int v);
    /**返回节点个数*/
    public int V();
    /**返回节点边数*/
    public int E();
    public static void print(Graph graph){
        for(int i=0;i<graph.V();i++){
            for(int j: graph.adj(i)){
                System.out.println(i+" - " +j);
            }
        }
    }
}
