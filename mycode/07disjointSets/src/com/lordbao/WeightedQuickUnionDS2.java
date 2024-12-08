package com.lordbao;


/**
 * @Author Lord_Bao
 * @Date 2024/10/25 20:42
 * @Version 1.0
 *
 * 树低挂树高
 */
public class WeightedQuickUnionDS2 implements  DisjointSets{
    private int [] parent;

    public WeightedQuickUnionDS2(int N){
        parent = new int[N];
        for (int i=0;i<N;i++){
            parent[i]=-1;
        }
    }

    //找到p节点在其子树的深度
    private int height(int p){
        int count = 1;
        while (parent[p]>=0){
            count++;
            p=parent[p];
        }
        return count;
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
        //如果p和q已经属于同一个分量,则程序结束
        //更重要的是,此处避免了当p=q时,使得parent[p]=p本身的错误代码
        if(parent1==parent2){
            return;
        }

        int height1 = height(p);
        int height2 = height(q);
        if(height1<height2){
            parent[parent1]=parent2;
        }else {
            parent[parent2]=parent1;
        }

    }

    @Override
    public boolean isConnected(int p, int q) {
        return findParent(p)==findParent(q);
    }

    public static void main(String[] args) {
        WeightedQuickUnionDS2 demo = new WeightedQuickUnionDS2(9);
        demo.connect(0,1);
        demo.connect(0,2);
        demo.connect(0,3);
        demo.connect(0,4);
        demo.connect(0,5);

        demo.connect(6,7);
        demo.connect(6,8);
    }
}
