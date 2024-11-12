package com.lordbao.graph.alg.mst;


import com.lordbao.graph.weightedEdgeGraph.WeightedEdge;
import com.lordbao.graph.weightedEdgeGraph.WeightedEdgeUnDirectedGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author Lord_Bao
 * @Date 2024/11/12 10:49
 * @Version 1.0
 */
public class PrimMST {

    /**
     * 最小生成树的总体费用
     */
    private int totalCost;

    /**
     * 举例说明
     * edgeTo[1]=2，表明边2-1是最小生成树的一边
     * edgeTo[3]=2，表明边2-3是最小生成树的一边
     */
    private int[] edgeTo;
    /**
     * 下面属性应该配合edgeTo 一起解读。
     * 若 dist[3]=5,且edgeTo[3]=2，表明边2-3是最小生成树的一边，且该边花费为5
     */
    private int[] dist;

    /**Prim算法的默认起始节点为0*/
    private static final int DEFAULT_START_NODE = 0;



    private void initHelper(int size){
        //初始化dist
        dist = new int[size];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[DEFAULT_START_NODE] = 0;


        //初始化edgeTo为-1
        edgeTo = new int[size];
        Arrays.fill(edgeTo, -1);

        totalCost=0;
    }

    public PrimMST(WeightedEdgeUnDirectedGraph graph) {
        //初始化相关数据
        initHelper(graph.V());
        //构建MST
        createMST(graph);
    }

    private void createMST(WeightedEdgeUnDirectedGraph graph){
        int size = graph.V();

        //构建小根堆
        List<Dist> list = new ArrayList<>();
        //到非起始节点的距离通通为Integer.MAX_VALUE
        for (int i = 0; i < size; i++) {
            list.add(new Dist(i, Integer.MAX_VALUE));
        }
        list.get(DEFAULT_START_NODE).dist = 0;
        PriorityQueue<Dist> queue = new PriorityQueue<>(list);

        //表明节点是否被访问过
        boolean[] marked = new boolean[size];


        //执行size次循环即可得到最终的MST
        for (int i = 0; i < size; i++) {
            while (marked[queue.peek().to]) {
                queue.poll();
            }
            Dist top = queue.poll();
            totalCost+=top.dist;
            marked[top.to] = true;
            for (WeightedEdge edge : graph.adj(top.to)) {
                //如果邻居未被访问过
                if (!marked[edge.to]) {
                    //贪心体现
                    if (edge.weight < dist[edge.to]) {
                        dist[edge.to] = edge.weight;
                        edgeTo[edge.to] = top.to;
                        queue.add(new Dist(edge.to, edge.weight));
                    }
                }
            }
        }
    }



    private static class Dist implements Comparable<Dist> {
        /**
         * to表示MST的潜在目标节点，假设0已经是MST的一个节点，1不是MST的一个节点，
         * 0-1是一条边，那么0-1中的1就是这里的to，而0则在edgeTo中保存，即edgeTo[1]=0
         */
        public int to;
        /**
         * 距离，比如上面提到的0-1的距离为15，则此处就是15
         */
        public int dist;

        public Dist(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Dist o) {
            return this.dist - o.dist;
        }
    }

    public int getTotalCost(){
        return totalCost;
    }


    public void printMST() {
        int size = edgeTo.length;
        for(int i=0;i<size;i++){
            if(i!=DEFAULT_START_NODE){
                System.out.println(edgeTo[i]+" - "+i+" : "+dist[i]);
            }
        }
    }
}
