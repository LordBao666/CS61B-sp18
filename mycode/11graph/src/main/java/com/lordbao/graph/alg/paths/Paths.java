package com.lordbao.graph.alg.paths;


import com.lordbao.graph.Graph;

import java.util.Arrays;

/**
 * @Author Lord_Bao
 * @Date 2024/11/7 14:36
 * @Version 1.0
 */
public abstract class Paths {
    /**
     * 起始节点
     */
    protected int s;
    /**
     * 节点是否标记
     */
    protected boolean[] marked;

    /**
     * 指向某节点的起始节点，比如edgeTo[2]=1，表示存在边1-2
     */
    protected int[] edgeTo;


    protected Graph g;

    public Paths(Graph g, int s) {
        this.g = g;
        this.s = s;
        int vertexSize = g.V();
        marked = new boolean[vertexSize];
        edgeTo = new int[vertexSize];
        //初始值设置为-1，表示它到s不可达。注意s到自己也是不可达
        Arrays.fill(edgeTo, -1);
    }

    /**
     * 起始节点s到v是否可达
     */
    public abstract boolean hasPathTo(int v);

    /**
     * 返回起始节点到v的一条路径。如果不存在，返回null
     */
    public abstract Iterable<Integer> pathTo(int v);
}
