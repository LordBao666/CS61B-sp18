package com.lordbao.llbt;


/**
 * @Author Lord_Bao
 * @Date 2024/11/1 15:39
 * @Version 1.0
 */
public class MyTreeSetTest {

    public static void test1(){
        MyTreeSet<Integer> treeSet = new MyTreeSet<>();
        treeSet.add(1);
        treeSet.add(7);
        treeSet.add(5);
        treeSet.add(4);
        treeSet.add(3);
        treeSet.add(6);
        treeSet.add(2);
        treeSet.add(9);
        treeSet.add(8);

        //添加冗余值
        treeSet.add(1);
        treeSet.add(1);

        System.out.println(treeSet.contains(1));
        System.out.println(treeSet.contains(0));
        System.out.println(treeSet.getKeys());
    }

    public static void main(String[] args) {
        test1();
    }
}
