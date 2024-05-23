package com.lordbao.course;

/**
 * @Author Lord_Bao
 * @Date 2024/5/23 12:21
 * @Version 1.0
 */
public class Sort {
    /** Sorts strings destructively. */
    public static void sort(String[] x) {

        if(x==null || x.length==0){
            return;
        }

        for(int i=0;i<x.length;i++) {
            // find the smallest item
            int smallestIndex = i;
            for(int j=i+1;j<x.length;j++){
                if(x[j].compareTo(x[smallestIndex])<0){
                    smallestIndex=j;
                }
            }
            //switch the front and the smallest
            String temp = x[i];
            x[i]= x[smallestIndex];
            x[smallestIndex]=temp;

        }
    }
}