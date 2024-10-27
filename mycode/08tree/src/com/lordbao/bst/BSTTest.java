package com.lordbao.bst;


/**
 * @Author Lord_Bao
 * @Date 2024/10/27 14:54
 * @Version 1.0
 */
public class BSTTest {
    public static  BST<String> initHelper(){
        BST<String> t = BST.insert(null, "dog");
        String [] arr = {
                "bag","alf","cat","cat","flat","elf","glut","glut","eyes"
        };
        for(String str:arr){
            BST.insert(t,str);
        }
        return t;
    }

    public static void main(String[] args) {
        BST<String> t = initHelper();
        BST.traverse(t);
        System.out.println();
        t = BST.delete(t, "dog");
        BST.traverse(t);
        System.out.println();
        t = BST.delete(t, "bag");
        BST.traverse(t);
    }
}
