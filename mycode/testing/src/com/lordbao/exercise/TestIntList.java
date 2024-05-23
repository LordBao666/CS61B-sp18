package com.lordbao.exercise;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @Author Lord_Bao
 * @Date 2024/5/23 15:31
 * @Version 1.0
 */
public class TestIntList {
    @Test
    public void testlist() {
        IntList list = IntList.list(1, 3, 5, 7);
        System.out.println(list);
    }

    @Test
    public void testEquals() {
        IntList list = IntList.list(1, 3, 5);
        IntList list1 = IntList.list(1, 3, 5, 7);
        IntList list2 = IntList.list(1, 3, 5, 7, 9);
        assertEquals(list1, list1);
        assertNotEquals(list1, list);
        assertNotEquals(list1, list2);
    }

    @Test
    public void testSkippy() {
        IntList A = IntList.list(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        IntList B = IntList.list(9, 8, 7, 6, 5, 4, 3, 2, 1);
        A.skippify();
        B.skippify();
        assertEquals(IntList.list(1, 3, 6, 10), A);
        assertEquals(IntList.list(9, 7, 4), B);
    }

    @Test
    public void testRemoveDuplicates() {
        IntList list = IntList.list(1, 2, 2, 2, 3);
        IntList.removeDuplicates(list);
        assertEquals(IntList.list(1, 2, 3), list);

    }
}
