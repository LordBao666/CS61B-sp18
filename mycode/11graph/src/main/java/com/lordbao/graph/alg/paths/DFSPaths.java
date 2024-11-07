package com.lordbao.graph.alg.paths;


import com.lordbao.graph.Graph;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author Lord_Bao
 * @Date 2024/11/7 14:45
 * @Version 1.0
 */

public class DFSPaths extends Paths{
    public DFSPaths(Graph g, int s) {
        super(g, s);
        dfs(s);
    }

    /**
     * 对节点v进行深度优先搜索
     */
    private void dfs(int v){
        marked[v] = true;
        for(int neighbor : g.adj(v)){
            if(!marked[neighbor]){
                edgeTo[neighbor]=v;
                dfs(neighbor);
            }
        }
    }

    @Override
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    @Override
    public Iterable<Integer> pathTo(int v) {
        if(!hasPathTo(v)){
            return null;
        }
        if(v==s){
            return List.of(s);
        }

        List<Integer> list = new LinkedList<>();
        list.addFirst(v);
        while ( edgeTo[v]>0){
            v=edgeTo[v];
            list.addFirst(v);
        }
        list.addFirst(s);
        return list;
    }
}
