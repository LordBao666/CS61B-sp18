package com.lordbao.course;

/**
 * @Author Lord_Bao
 * @Date 2024/5/14 16:38
 * @Version 1.0
 */
public class DIYList <T>{
    public T data;
    public DIYList<T> next;

    public DIYList(T data, DIYList<T> next) {
        this.data = data;
        this.next = next;
    }


    /**
     *  get this DIYList's size.
     *  I skip size() using the recursion
     */
    public int size(){
        int count=0;
        DIYList<T> cur=this;
        while (cur!=null){
            count++;
            cur=cur.next;
        }
        return count;
    }

    /**
     * get the ith item of this DIYList
     * For convenience,we don't care if i is out of range,namely,i is too small or too big.
     * So ALWAYS assume i is legal
     */
    public T get(int i){
        DIYList<T> cur = this;
        while (i!=0){
            cur=cur.next;
            i--;
        }
        return cur.data;
    }

    /**
     * get the ith item of this DIYList by recursion.
     * Assume i is leagl like the method of get(int i)
     */
    public T getByRecursion(int i){
        if(i==0){
            return data;
        }
        return next.getByRecursion(i-1);
    }

    public static void main(String[] args) {
        DIYList<Integer> list = new DIYList<>(1,null);
        list.next=new DIYList<>(2,null);
        list.next.next=new DIYList<>(3,null);
        System.out.println(list.get(2));
        System.out.println(list.size());
    }
}
