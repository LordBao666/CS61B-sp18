package com.lordbao;


/**
 * @Author Lord_Bao
 * @Date 2024/10/25 20:42
 * @Version 1.0
 */
public class WeightedQuickUnionDS implements  DisjointSets{
    private int [] parent;

    public WeightedQuickUnionDS(int N){
        parent = new int[N];
        for (int i=0;i<N;i++){
            parent[i]=-1;
        }
    }

    
    private int findParent(int p){
        while (parent[p]>=0){
            p=parent[p];
        }
        return p;
    }

    @Override
    public void connect(int p, int q) {

        int parent1 = findParent(p);
        int parent2 = findParent(q);

        int size1 = -parent[parent1];
        int size2 = -parent[parent2];
        if(size1<size2){
            parent[parent1]=parent2;
            parent[parent2]=-(size1+size2);
        }else {
            parent[parent2]=parent1;
            parent[parent1]=-(size1+size2);
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return findParent(p)==findParent(q);
    }

    public static void main(String[] args) {
        WeightedQuickUnionDS demo = new WeightedQuickUnionDS(9);
        demo.connect(0,1);
        demo.connect(0,2);
        demo.connect(0,3);
        demo.connect(0,4);
        demo.connect(0,5);

        demo.connect(6,7);
        demo.connect(6,8);
    }
}
