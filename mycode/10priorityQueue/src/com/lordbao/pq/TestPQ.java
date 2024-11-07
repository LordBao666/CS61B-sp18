package com.lordbao.pq;


import java.util.*;

/**
 * @Author Lord_Bao
 * @Date 2024/11/6 14:18
 * @Version 1.0
 */
public class TestPQ {


    public void testHeapify(){
        Integer [] arr = {11, 8, 2, 6, 1, 4, 3, 9, 7, 5, 10};
        MyPriorityQueue<Integer> queue1 = new MyPriorityQueue<>(arr);
        MyPriorityQueue<Integer> queue2 = new MyPriorityQueue<>(arr, (o1, o2) -> o2-o1);
        System.out.println();
    }

    public void testConstructor(){
//        Integer [] arr = {11, 8, 2, 6, 1, 4, 3, 9, 7, 5, 10};
        Integer [] arr = {11, 8, 2, 6, 1, 4, 3, 9, 7, 5, 10,12};
        MyPriorityQueue<Integer> queue1 = new MyPriorityQueue<>(arr);
        MyPriorityQueue<Integer>  queue2 = new MyPriorityQueue<>(Arrays.asList(arr));
        MyPriorityQueue<Integer> queue3 = new MyPriorityQueue<>();
        MyPriorityQueue<Integer> queue4 = new MyPriorityQueue<>(((o1, o2) -> o2-o1));

        System.out.println();

    }

    public void testAdd(){
        Integer [] arr = {11, 8, 2, 6, 1, 4, 3, 9, 7, 5, 10};
        MyPriorityQueue<Integer> queue1 = new MyPriorityQueue<>(arr);
        MyPriorityQueue<Integer> queue2 = new MyPriorityQueue<>(arr,(Comparator.comparingInt(o -> o)));
        queue1.add(-1);
        queue2.add(-1);

        MyPriorityQueue<Integer> queue3 = new MyPriorityQueue<>();
        queue3.add(5);
        queue3.add(4);
        queue3.add(3);
        queue3.add(2);
        queue3.add(1);
        //测试加重复的数字
        queue3.add(1);
        queue3.add(1);
        queue3.add(1);
        queue3.add(1);
        queue3.add(2);
        queue3.add(2);
        //执行下一步添加时，观察扩容
        queue3.add(2);

    }


    public void testRemoveTop(){
        Integer [] arr = {11, 8, 2, 6, 1, 4, 3, 9, 7, 5, 10,2,1,2};
        MyPriorityQueue<Integer> queue = new MyPriorityQueue<>(arr);
        System.out.println(queue.getTop());
        System.out.println(queue.removeTop());
        System.out.println(queue.removeTop());
        System.out.println(queue.removeTop());
        System.out.println(queue.removeTop());
        System.out.println(queue.removeTop());
        System.out.println(queue.removeTop());
        System.out.println(queue.removeTop());
        System.out.println(queue.removeTop());
        System.out.println(queue.removeTop());
        System.out.println(queue.removeTop());
        System.out.println(queue.removeTop());
    }

    public void getM(){
        Integer [] arr = {11, 8, 2, 6, 1, 4, 3, 9, 7, 5, 10};
        int m = 5;
        List<Integer> smallestList = MyPriorityQueue.getSmallest(arr, m, (o1, o2) -> o2 - o1);
        List<Integer> largestList = MyPriorityQueue.getSmallest(arr, m, (o1, o2) -> o1 - o2);
        System.out.println(smallestList);
        System.out.println(largestList);
    }

    public static void main(String[] args) {
        TestPQ demo = new TestPQ();
//        demo.testHeapify();
//        demo.testConstructor();
//        demo.testAdd();
//        demo.testRemoveTop();
        demo.getM();
    }
}
