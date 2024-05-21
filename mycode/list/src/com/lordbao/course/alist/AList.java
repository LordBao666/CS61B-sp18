package com.lordbao.course.alist;

import java.util.Arrays;

/** Array based list.
 *  @author Josh Hug
 */

public class AList {
    private int[] array ;
    private int size;
    private int capacity;
    /** Creates an empty list. */
    public AList() {
        capacity=100;
        array = new int[capacity];
        size=0;
    }

    /** Inserts X into the back of the list. */
    public void addLast(int x) {
        if(capacity==size){
            resize();
        }
        array[size++]=x;
    }

    /** Returns the item from the back of the list. */
    public int getLast() {
        return array[size-1];
    }
    /** Gets the ith item in the list (0 is the front). */
    public int get(int i) {
        return array[i];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Deletes item from back of the list and
      * returns deleted item. */
    public int removeLast() {
        size--;
        return array[size];
    }

    private void resize(){
        capacity=capacity+(capacity>>1);

//        int [] newArr = new int[capacity];
//        System.arraycopy(array,0,newArr,0,array.length);

        //The code works same as the above one
        array= Arrays.copyOf(array, capacity);
    }
} 