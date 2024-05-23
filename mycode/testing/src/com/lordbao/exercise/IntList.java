package com.lordbao.exercise;

/**
 * @Author Lord_Bao
 * @Date 2024/5/23 15:20
 * @Version 1.0
 * <p>
 * The exercise comes from https://sp18.datastructur.es/materials/discussion/examprep03.pdf
 */
public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }


    public static IntList list(int... args) {
        if (args == null || args.length == 0) {
            return null;
        }

        IntList p = null;
        for (int i = args.length - 1; i >= 0; i--) {
            p = new IntList(args[i], p);
        }
        return p;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("IntList { ");
        int size = 0;
        IntList p = this;
        while (p != null) {
            sb.append(p.first).append(" ");
            p = p.rest;
            size++;
        }
        sb.append(" }.").append("size : ").append(size);
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        IntList p = (IntList) obj;
        IntList q = this;
        //scan p and q at the same time
        while (p != null && q != null) {
            if (p.first != q.first) {
                return false;
            }
            p = p.rest;
            q = q.rest;
        }
        // p and q have the same data
        return p == null && q == null;
    }

    /**
     * Suppose we have the following IntList class, as defined in lecture and lab, with an
     * added skippify function.
     * Suppose that we define two IntLists as follows.
     * 1 IntList A = IntList.list(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
     * 2 IntList B = IntList.list(9, 8, 7, 6, 5, 4, 3, 2, 1);
     * Fill in the method skippify such that the result of calling skippify on A and B
     * are as below:
     * - After calling A.skippify(), A: (1, 3, 6, 10)
     * - After calling B.skippify(), B: (9, 7, 4)
     */
    public void skippify() {
        int step = 2;
        IntList prev = this;
        IntList q = prev;
        while (q != null) {
            //go to next item
            for (int i = 0; i < step; i++) {
                //the list has been scanned completely
                if (q == null) {
                    break;
                }
                q = q.rest;
            }
            prev.rest = q;
            prev = q;
            step++;
        }

    }

    /**
     * Given a sorted linked list of items - remove duplicates.
     * For example given 1 -> 2 -> 2 -> 2 -> 3,
     * Mutate it to become 1 -> 2 -> 3 (destructively)
     */
    public static void removeDuplicates(IntList p) {
        if (p == null) {
            return;
        }

        IntList current = p.rest;
        IntList previous = p;
        while (current!=null){
            if(current.first!=previous.first){
                previous=current;
                current=current.rest;
            }else {
                current=current.rest;
                previous.rest=current;
            }
        }
    }
}
