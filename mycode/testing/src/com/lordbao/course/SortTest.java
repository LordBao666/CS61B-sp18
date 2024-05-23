package com.lordbao.course;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author Lord_Bao
 * @Date 2024/5/23 12:21
 * @Version 1.0
 */
public class SortTest {
    @Test
    public void  testSort(){
        String [] arr ={"i","have","an","egg"};
        String [] expected = {"an","egg","have","i"};
        Sort.sort(arr);
        Assert.assertArrayEquals(expected,arr);
    }
}
