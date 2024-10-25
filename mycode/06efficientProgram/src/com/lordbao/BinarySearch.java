package com.lordbao;

/**
 * @Author Lord_Bao
 * @Date 2024/7/13 14:33
 * @Version 1.0
 */
public class BinarySearch {

    /**
     *
     * Return the index of s in arr, if it does not exist,return -1 instead.
     * Note that arr must be sorted.
     */
    public static int binarySearch(String [] arr, String s , int low,int high){
        if(low>high) return -1;
        int mid = (low +high)>>1;
        int comp = arr[mid].compareTo(s);
        if(comp<0){//arr[mid] is smaller than s
            return binarySearch(arr,s,mid+1,high);
        }else if(comp>0){//arr[mid] is bigger than s
            return binarySearch(arr,s,low,mid-1);
        }else {
            return mid;
        }
    }

    public static void main(String[] args) {
       testBinarySearch();
    }

    private static void testBinarySearch() {
        String [] arr = {"a","b","cc","dd","fff"};
        System.out.println(binarySearch(arr,"a",0,arr.length-1));
        System.out.println(binarySearch(arr,"cc",0,arr.length-1));
        System.out.println(binarySearch(arr,"c",0,arr.length-1));
    }
}
