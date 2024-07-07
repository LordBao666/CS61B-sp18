package com.lordbao.course;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @Author Lord_Bao
 * @Date 2024/5/26 18:35
 * @Version 1.0
 */
public class TestMapHelper {
    @Test
    public void testMaxKey(){
//        Map61B<Integer,Integer> map = new ArrayMap<>();
        Map61B<Integer,Integer> map = new ArrayMapCourse<>();
        for(int i=0;i<10;i++){
            map.put(i,i+1);
        }

        assertEquals(Integer.valueOf(9),MapHelper.maxKey(map));
        assertEquals(Integer.valueOf(0),MapHelper.maxKey(map, (o1, o2) -> o2-o1));
    }
}
