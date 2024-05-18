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

        list.addLast(7);
        list.addFirst(5);
        list.addFirst(3);
        list.addFirst(1);
        System.out.println(list);

        list.deleteFirst();
        System.out.println(list);

        list=new SLList(new int[]{1,2,3,4});
        System.out.println(list);
    }
}
