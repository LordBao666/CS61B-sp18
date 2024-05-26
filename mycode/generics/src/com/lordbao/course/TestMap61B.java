package com.lordbao.course;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author Lord_Bao
 * @Date 2024/5/26 17:08
 * @Version 1.0
 */
public class TestMap61B {
    @Test
    public void testPut(){
//        Map61B<Integer,Integer> map = new ArrayMap<>();
        Map61B<Integer,Integer> map = new ArrayMapCourse<>();
        for(int i=0;i<10;i++){
            map.put(i,-i);
        }
        System.out.println(map);
    }

    @Test
    public void testSizeGetKeyAndContainsKey(){
        Map61B<Integer,Integer> map = new ArrayMap<>();
//        Map61B<Integer,Integer> map = new ArrayMapCourse<>();
        for(int i=0;i<10;i++){
            map.put(i,-i);
        }
        assertTrue(map.containsKey(0));
        assertEquals(10,map.size());
        assertEquals(-1,(int)map.get(1));
        assertNull(map.get(-1));
        map.put(0,0);
        assertEquals(10,map.size());
    }

    @Test
    public void testKeys(){
//        Map61B<Integer,Integer> map = new ArrayMap<>();
        Map61B<Integer,Integer> map = new ArrayMapCourse<>();
        for(int i=0;i<10;i++){
            map.put(i,-i);
        }
        map.keys().forEach(System.out::println);


    }

}
