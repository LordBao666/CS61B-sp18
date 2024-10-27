package com.lordbao.bst;


/**
 * @Author Lord_Bao
 * @Date 2024/10/27 16:53
 * @Version 1.0
 */
public class BST2Test {

    public static  BST2<String> initHelper(){
        BST2<String> bst = new BST2<>();
        String [] arr = {
                "dog","bag","alf","cat","cat","flat","elf","glut","glut","eyes"
        };
        for(String str:arr){
            bst.insert(str);
        }
        return bst;
    }

    public static void main(String[] args) {
        BST2<String> t = initHelper();
        t.traverse();
        t.delete("dog");
        t.traverse();
        t.delete("bag");
        t.traverse();

        System.out.println(t.search("eyes"));
        System.out.println(t.search("eye"));
    }
}
