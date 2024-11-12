package com.lordbao.graph.alg.shortestPath;


import com.lordbao.graph.Graph;
import com.lordbao.graph.weightedEdgeGraph.WeightedEdge;
import com.lordbao.graph.weightedEdgeGraph.WeightedEdgeGraph;

import java.util.*;

/**
 * @Author Lord_Bao
 * @Date 2024/11/11 11:44
 * @Version 1.0
 */
public class ShortestPath {

    private int[] dist;
    private int[] edgeTo;
    private PriorityQueue<Dist> queue;
    private int startNode;

    private static class Dist implements Comparable<Dist>{
        //节点下标
        private int node;
        //源点到目标节点的距离
        private int dist;

        public Dist(int node,int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Dist o) {
            return this.dist-o.dist;
        }
    }


    public ShortestPath(WeightedEdgeGraph g, int v){
        initHelper(g,v);
        shortestPath(g,v);
    }

    private void initHelper(WeightedEdgeGraph g, int v) {
        int size = g.V();
        dist = new int[size];
        edgeTo = new int[size];
        startNode = v;

        ArrayList<Dist> list = new ArrayList<>();
        //初始化距离
        for(int i=0;i<size;i++){
            dist[i]=Integer.MAX_VALUE;
            list.add(new Dist(i,Integer.MAX_VALUE));
        }
        dist[v]=0;
        list.get(v).dist=0;

        //初始化edgeTo,默认值为-1
        Arrays.fill(edgeTo,-1);

        //建立小根堆
        queue = new PriorityQueue<>(list);
    }

    private void shortestPath(WeightedEdgeGraph g, int v) {
        int n = g.V();
        for(int i=0;i<n;i++){
            Dist smallest = queue.poll();
            int node = smallest.node;
            for(WeightedEdge edge : g.adj(node)){
                //更新dist 和 edgeTo
                int tempDist = dist[node]+edge.weight;
                if(tempDist<dist[edge.to]){
                    dist[edge.to]=tempDist;
                    edgeTo[edge.to]=node;
                    queue.add(new Dist(edge.to,tempDist));
                }
            }
        }
    }


    /**
     * 获得到节点v的距离
     */
    public int getShortestDistanceTo(int v){
        return dist[v];
    }

    /**
     * 获得到节点v的路径
     */
    public List<Integer> getShortestPathTo(int v){
        LinkedList<Integer> list = new LinkedList<>();

        while (v!=startNode){
            list.addFirst(v);
            v = edgeTo[v];
        }
        list.addFirst(startNode);
        return list;
    }
}
