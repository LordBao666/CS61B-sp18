package com.lordbao.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Lord_Bao
 * @Date 2024/11/8 15:53
 * @Version 1.0
 */
class MergeSortTest {

    @Test
    void mergeSort() {
        Integer [] arr0 = {8,4,6};
        Integer [] arr1 = {8,4,6,2,1,9,0,3,7,5};
        Integer [] arr2 = {8,4,6,2,1,9,0,3,7,5};
        MergeSort.mergeSort(arr0,(o1,o2)->o1-o2);
        MergeSort.mergeSort(arr1,(o1,o2)->o1-o2);
        MergeSort.mergeSort(arr2,((o1, o2) -> o2-o1));
        System.out.println(Arrays.toString(arr0));
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));

    }
}