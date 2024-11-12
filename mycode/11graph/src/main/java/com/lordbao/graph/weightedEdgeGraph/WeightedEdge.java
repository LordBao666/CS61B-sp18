package com.lordbao.graph.weightedEdgeGraph;

import java.util.Objects;

public class WeightedEdge {
    //边指向节点的下标. 比如边0-3,那么这里的to就是3.
    //对于邻接列表而言，无需存储边来源节点的下标.
    public int to;
    //边的权重
    public int weight;

    public WeightedEdge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeightedEdge that = (WeightedEdge) o;
        return to == that.to && weight == that.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(to, weight);
    }

    @Override
    public String toString() {
        return "{" +
                "to=" + to +
                ", weight=" + weight +
                '}';
    }
}