package com.lordbao.discussion;

import com.lordbao.course.intro.IntList;
import com.lordbao.exercise.intro.Lists1Exercises;

/**
 * @Author Lord_Bao
 * @Date 2024/5/14 19:55
 * @Version 1.0
 *
 *
 * The exercise comes from
 * https://sp18.datastructur.es/materials/discussion/disc02.pdf
 *
 * This one is quite similar to List1Exercises1
 * @see Lists1Exercises
 *
 */
public class SquareList {
    /**
     * return a  new IntList by squaring each element of L without mutating L
     *
     */
    public static IntList square(IntList L) {
        if(L==null)return null;
        IntList p=new IntList(L.first * L.first,null);
        IntList q=p;
        L=L.rest;
        while (L!=null){
            q.rest=new IntList(L.first * L.first,null);
            q=q.rest;
            L=L.rest;
        }
        return p;
    }

    /**
     * return L with squaring each element of L.
     * I skip the recursion version.
     */
    public static IntList squareMutative(IntList L) {
        IntList p=L;
        while (p!=null){
            p.first=p.first * p.first;
            p=p.rest;
        }
        return L;
    }

    public static void main(String[] args) {
        IntList list = new IntList(3, null);
        list = new IntList(2,list);
        list = new IntList(1,list);
        System.out.println(square(list));
        System.out.println(squareMutative(list));
    }
}
