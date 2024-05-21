package com.lordbao.course.sllist;

/**
 * @Author Lord_Bao
 * @Date 2024/5/18 13:08
 * @Version 1.0
 */

public class Main {
    public static void main(String[] args) {
        SLList list = new SLList();
        System.out.println(list);

        //test add
        list.addLast(7);
        list.addFirst(5);
        list.addFirst(3);
        list.addFirst(1);
        System.out.println(list);

        //test deleteFirst
        list.deleteFirst();
        System.out.println(list);

        //test constructor with an array as the parameter
        list=new SLList(new int[]{1,2,3,4});
        System.out.println(list);

        //test reverseByIteration()
        list.reverseByIteration();
        System.out.println(list);

        //test reverseByRecursion
        list.reverseByRecursion();
        System.out.println(list);
    }
}
