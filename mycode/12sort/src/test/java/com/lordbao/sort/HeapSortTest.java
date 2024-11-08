package com.lordbao.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @Author Lord_Bao
 * @Date 2024/11/8 14:38
 * @Version 1.0
 */
class HeapSortTest {

    @Test
    void sort() {
        Integer [] arr1 = {8,4,6,2,1,9,0,3,7,5};
        Integer [] arr2 = {8,4,6,2,1,9,0,3,7,5};
        Integer [] arr3 = {8,4,6,2,1,9,0,3,7,5};
        HeapSort.sort(arr1);
        HeapSort.sort(arr2,((o1, o2) -> o1-o2));
        HeapSort.sort(arr3,((o1, o2) -> o2-o1));
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        System.out.println(Arrays.toString(arr3));
    }

    @Test
    void sortNoHarm() {
        Integer [] arr1 = {8,4,6,2,1,9,0,3,7,5};
        Integer [] arr2 = {8,4,6,2,1,9,0,3,7,5};
        Integer [] arr3 = {8,4,6,2,1,9,0,3,7,5};
        System.out.println(Arrays.toString( HeapSort.sortNoHarm(arr1)));
        System.out.println(Arrays.toString(HeapSort.sortNoHarm(arr2,((o1, o2) -> o1-o2))));
        System.out.println(Arrays.toString(HeapSort.sortNoHarm(arr3,((o1, o2) -> o2-o1))));
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        System.out.println(Arrays.toString(arr3));
    }
}