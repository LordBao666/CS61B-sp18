package com.lordbao;

public class QuickUnionDS implements DisjointSets {
    private int[] parent;

    public QuickUnionDS(int N){
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
        int parentP = findParent(p);
        int parentQ = findParent(q);
        parent[parentP]=parentQ;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return findParent(p)==findParent(q);
    }
}