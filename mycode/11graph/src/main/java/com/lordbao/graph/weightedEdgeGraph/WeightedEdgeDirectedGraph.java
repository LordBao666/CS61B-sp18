package com.lordbao.graph.weightedEdgeGraph;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Author Lord_Bao
 * @Date 2024/11/11 11:03
 * @Version 1.0
 *
 * 带权边有向图
 */
public class WeightedEdgeDirectedGraph implements WeightedEdgeGraph {
    private  final int size;
    /**邻接列表*/
    private final  HashSet<WeightedEdge>[] list;


    @SuppressWarnings("unchecked")
    public WeightedEdgeDirectedGraph(int size) {
        this.size = size;
        list = (HashSet<WeightedEdge>[])new HashSet[size];
        for(int i=0;i<size;i++){
            list[i] = new HashSet<>();
        }
    }

    @Override
    public void addEdge(int v, int w, int weight) {
        list[v].add(new WeightedEdge(w, weight));
    }

    @Override
    public Iterable<WeightedEdge> adj(int v) {
        return list[v];
    }

    @Override
    public int V() {
        return size;
    }

    @Override
    public int E() {
        int count = 0;
        for(int i=0;i<size;i++){
            count+=list[i].size();
        }
        return count;
    }

    public void print(){
        for (int i=0;i<size;i++){
            for(WeightedEdge edge : list[i]){
                System.out.println(i+" - " + edge.to+" : "+edge.weight);
            }
        }
    }
}
