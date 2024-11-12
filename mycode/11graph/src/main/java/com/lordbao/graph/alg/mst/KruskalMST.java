package com.lordbao.graph.alg.mst;


import com.lordbao.WQUPathCompression;
import com.lordbao.graph.weightedEdgeGraph.WeightedEdge;
import com.lordbao.graph.weightedEdgeGraph.WeightedEdgeUnDirectedGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author Lord_Bao
 * @Date 2024/11/12 15:40
 * @Version 1.0
 */
public class KruskalMST {

    /**最终构成MST的所有边集*/
    private List<InnerWeightedEdge> mstEdges;
    /**MST花费*/
    private int totalCost;

    private List<InnerWeightedEdge>  dealWithGraph(WeightedEdgeUnDirectedGraph graph){

        List<InnerWeightedEdge> list = new ArrayList<>();
        int size =graph.V();
        for(int i=0;i<size;i++){
            for (WeightedEdge edge: graph.adj(i)){
                if(edge.to>i){
                    list.add(new InnerWeightedEdge(i,edge.to,edge.weight));
                }
            }
        }
        return list;
    }

    public KruskalMST(WeightedEdgeUnDirectedGraph graph){
        List<InnerWeightedEdge> list = dealWithGraph(graph);
        PriorityQueue<InnerWeightedEdge> queue = new PriorityQueue<>(list);
        int size = graph.V();
        WQUPathCompression set = new WQUPathCompression(size);
        mstEdges = new ArrayList<>();

        int i=0;
        while (i<size-1){
            InnerWeightedEdge edge = queue.poll();
            //如果不含环
            if(!set.isConnected(edge.from,edge.to)){
                i++;
                set.connect(edge.from,edge.to);
                mstEdges.add(edge);
                totalCost+=edge.weight;
            }
        }

    }

    private static class InnerWeightedEdge implements Comparable<InnerWeightedEdge>{
        public int from;
        public int to;
        public int weight;

        public InnerWeightedEdge( int from,int to,int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(InnerWeightedEdge o) {
            return this.weight-o.weight;
        }
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void printMST(){
        for(InnerWeightedEdge edge : mstEdges){
            System.out.println(edge.from+" - "+edge.to+" : "+edge.weight);
        }
    }
}
