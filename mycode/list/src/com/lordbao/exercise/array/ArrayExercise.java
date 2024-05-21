package com.lordbao.exercise.array;

import java.util.Arrays;

/**
 * @Author Lord_Bao
 * @Date 2024/5/21 22:48
 * @Version 1.0
 *
 * The exercises come from
 * https://sp18.datastructur.es/materials/discussion/disc03.pdf
 */
public class ArrayExercise {
    /**
     * Consider a method that inserts item into array arr at the given position. The
     * method should return the resulting array. For example, if x = [5, 9, 14, 15],
     * item = 6, and position = 2, then the method should return [5, 9, 6, 14, 15].
     * If position is past the end of the array, insert item at the end of the array.
     *
     * The solution is for loop which is better than mine.
     * */
    public static int[] insert(int[] arr, int item, int position){
        if(position>=arr.length){
            int[] copy = Arrays.copyOf(arr, arr.length + 1);
            copy[arr.length]=item;
            return copy;
        }else {
            int [] copy = new int[arr.length+1];
            //we will copy arr in two phases
            int firstLength=position;
            int secondLength=arr.length-firstLength;
            System.arraycopy(arr,0,copy,0,firstLength);
            copy[position]=item;
            System.arraycopy(arr,position,copy,position+1,secondLength);
            return copy;
        }
    }
    /**
     * Write a non-destructive method replicate(int[] arr) that replaces the
     * number at index i with arr[i] copies of itself. For example, replicate([3, 2,
     * 1]) would return [3, 3, 3, 2, 2, 1].
     *
     * Assume the elements in arr are all positive
     * */
    public static int[] replicate(int[] arr){
        //it is used to record the number of the resulting array
        int number = 0;
        for(int ele:arr){
            number+=ele;
        }
        int [] resultingArr = new int[number];
        int i=0;//i is used to scan resultingArr
        int j=0;//j is used to scan arr
        while (i<number){
            for(int k=0;k<arr[j];k++){
                resultingArr[i++]=arr[j];
            }
            j++;
        }
        return resultingArr;
    }


    public static void main(String[] args) {
        //test insert
        int [] arr={1,3,5};
        int [] newArr=insert(arr,7,10);
        System.out.println(Arrays.toString(newArr));
        newArr=insert(arr,7,0);
        System.out.println(Arrays.toString(newArr));
        newArr=insert(arr,7,1);
        System.out.println(Arrays.toString(newArr));

        //test replicate
        int [] myArr={3,2,1};
        int[] replicate = replicate(myArr);
        System.out.println(Arrays.toString(replicate));
    }
}
