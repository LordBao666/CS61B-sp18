package com.lordbao.sort;


import java.util.Comparator;

/**
 * @Author Lord_Bao
 * @Date 2024/11/8 15:24
 * @Version 1.0
 *
 *我这里归并排序写得就比较简单，必须要求传入一个比较器，
 * 而没有考虑泛型E到底能不能够比较。这只是一些代码是否好用的问题，与归并排序本身效率无关。
 */
public class MergeSort {

    /**
     *对数组arr的起始下标start(含),终止下标stop(不含)进行归并排序
     */
    private static<E> void mergeSort(E [] arr, E[] helperArr, int start, int stop, Comparator<E> comp){
        if(start>=stop-1){
            return;
        }

        int mid = start+((stop-start)>>1);
        mergeSort(arr, helperArr, start, mid, comp);
        mergeSort(arr, helperArr, mid, stop, comp);
        merge(arr,helperArr,start,stop,comp);
    }

    private static <E> void merge(E[] arr, E[] helperArr, int start, int stop, Comparator<E> comp) {
        int mid = start+((stop-start)>>1);
        int i=start,j=mid;
        int begin=start;//begin记录start的开始位置
        while (i<mid && j<stop){
            if(comp.compare(arr[i],arr[j])<0){
                helperArr[start]=arr[i];
                i++;
            }else {
                helperArr[start]=arr[j];
                j++;
            }
            start++;
        }
        while (i<mid){
            helperArr[start]=arr[i];
            i++;
            start++;
        }
        while (j<stop){
            helperArr[start]=arr[j];
            j++;
            start++;
        }

        System.arraycopy(helperArr,begin,arr,begin,stop-begin);
    }


    @SuppressWarnings("unchecked")
    public static<E> void mergeSort(E [] arr, Comparator<E> comp){
        E[] helperArr = (E[]) new Object[arr.length];
        mergeSort(arr,helperArr,0,arr.length,comp);
    }
}
