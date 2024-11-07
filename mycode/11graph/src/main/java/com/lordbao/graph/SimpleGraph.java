package com.lordbao.graph;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Author Lord_Bao
 * @Date 2024/11/7 12:23
 * @Version 1.0
 */
public class SimpleGraph implements Graph {

    private  final int size;
    /**邻接列表*/
    private final  HashSet<Integer>[] list;
    @SuppressWarnings("unchecked")
    public SimpleGraph(int size) {
        this.size = size;
        list = (HashSet<Integer>[]) new HashSet[size];
        for(int i=0;i<size;i++){
            list[i] = new HashSet<>();
        }
    }

    @Override
    public void addEdge(int v, int w) {
        list[v].add(w);
        list[w].add(v);
    }

    @Override
    public Iterable<Integer> adj(int v) {
        return list[v];
    }

    @Override
    public int V() {
        return size;
    }

    @Override
    public int E() {
        int edges =0;
        for(HashSet<Integer> set : list){
            edges+=set.size();
        }
        return (edges>>1);
    }
}
