package com.lordbao.course;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author Lord_Bao
 * @Date 2024/5/28 10:11
 * @Version 1.0
 */
public class TestArraySet {

    @Test
    public void testEquals() {
//        ArraySet<String> set = new ArraySet<>();
        ArraySetCourse<String> set = new ArraySetCourse<>();
        set.add("hi");
        set.add("hello");
//        ArraySet<Object> set1 = new ArraySet<>();
        ArraySetCourse<Object> set1 = new ArraySetCourse<>();
        set1.add("hi");
        set1.add("hello");
        assertEquals(set, set1);

        set1.add(1);
        assertNotEquals(set, set1);

        set.add("1");
        assertNotEquals(set, set1);

        System.out.println(set);
        System.out.println(set1);
    }

    @Test
    public void testToString() {
        ArraySet<Object> set = new ArraySet<>();
        set.add("hi");
        set.add("hello");
        set.add(1);
        System.out.println(set);
    }

    @Test
    public void testContainsAndSize() {
//        ArraySet<String> s = new ArraySet<>();
        ArraySetCourse<String> s = new ArraySetCourse<>();
        s.add("horse");
        s.add("fish");
        s.add("house");
        s.add("fish");
        System.out.println(s.contains("horse"));
        System.out.println(s.size());
    }


    @Test
    public void testForEach() {
        ArraySet<String> s = new ArraySet<>();
//        ArraySetCourse<String> s = new ArraySetCourse<>();
        s.add("horse");
        s.add("fish");
        s.add("house");
        s.add("fish");
        for (String ele : s) {
            System.out.println(ele);
        }
    }

    @Test
    public void tesOf() {
        ArraySetCourse<String> set = ArraySetCourse.of("how", "are", "you", "you");
//        ArraySet<String> set = ArraySet.of("how", "are", "you","you");
        System.out.println(set);
    }


}
