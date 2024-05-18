package com.lordbao.course.sllist;

/**
 * @Author Lord_Bao
 * @Date 2024/5/18 10:23
 * @Version 1.0
 */
public class SLList {

    private   static class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int item, IntNode next) {
            this.item = item;
            this.next = next;
        }
    }


    private final IntNode sentinel=new IntNode(-999,null);//the item does not matter here.
    private int size;
    public SLList(){
        size=0;
    }
    public SLList(int data){
        sentinel.next=new IntNode(data,null);
        size=1;
    }
    public  SLList(int [] arr){
        size=0;
        for(int ele:arr){
            addFirst(ele);
        }
    }

    public  void addFirst(int data){
        sentinel.next=new IntNode(data,sentinel.next);
        size++;
    }

    public  int getFirst(){
        if(sentinel.next==null){
            System.out.println("The first element is null! return -999");
            return -999;
        }else {
            return sentinel.next.item;
        }
    }

    /** Adds an item to the end of the list. */
    public void addLast(int x) {
        /* Your Code Here! */
        IntNode p =sentinel;

        //find the last intNode
        while (p.next!=null){
            p=p.next;
        }
        p.next=new IntNode(x,null);
        size++;
    }

    public  int size(){
        return size;
    }


    public void deleteFirst(){
        if(sentinel.next!=null){
            sentinel.next=sentinel.next.next;
            size--;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SLList{ ");
        IntNode p = sentinel.next;
        while (p!=null){
            sb.append(p.item).append(" ");
            p=p.next;
        }
        sb.append("}. size = ").append(size);

        return sb.toString();
    }

    /*The comment below are size() methods without the instance variable of size.
    * As the number of items grow, efficiency of  size() methods will drop fast.
    * The tradeoff is acceptable that we introduce size variable for better effect
    * though it will increase the cost of memory.
    * */
//    /** Returns the number of items in the list using recursion. */
//    public int size() {
//        return size(first);
//    }
//    /** return the number of items in  a list if p is the first node of the  list*/
//    private int size(IntNode p){
//        if(p==null) return 0;
//        return 1+ size(p.next);
//    }
//
//    /** Returns the number of items in the list using iteration. */
//    public int sizeByIteration(){
//        int size=0;
//        IntNode p=first;
//        while (p!=null){
//            size++;
//            p=p.next;
//        }
//        return size;
//    }
}
