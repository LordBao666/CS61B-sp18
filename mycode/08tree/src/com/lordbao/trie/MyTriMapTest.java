package com.lordbao.trie;


/**
 * @Author Lord_Bao
 * @Date 2024/11/14 15:39
 * @Version 1.0
 */
public class MyTriMapTest {

    public static void testAdd(){
        //添加buck，并测试
        MyTrieMap1 trie = new MyTrieMap1();
        trie.add("buck",10);
        System.out.println(trie.contains("bu"));//false
        System.out.println(trie.contains("buck"));//true
        System.out.println(trie.contains("buccck"));//false
        if(trie.contains("buck")){
            System.out.println(trie.get("buck"));
        }


        //添加一个重复的key,将val降低,看val是否会更新
        trie.add("buck",5);
        System.out.println(trie.get("buck"));
        //添加一个重复的key,将val提高,看val是否会更新
        trie.add("buck",10);
        System.out.println(trie.get("buck"));


        //添加一个子串
        trie.add("bu",10);
        if(trie.contains("bu")){
            System.out.println(trie.get("bu"));
        }
        //添加一个长串
        trie.add("bucks",10);
        if(trie.contains("bucks")){
            System.out.println(trie.get("bucks"));
        }


    }

    public static void testKeysWithPrefixAndCollect(){

        MyTrieMap1 trie = new MyTrieMap1();
        trie.add("buck",10);
        trie.add("sad",12);
        trie.add("smog",5);
        trie.add("spit",15);
        trie.add("spite",20);
        trie.add("spy",7);

        System.out.println(trie.keysWithPrefix("sp",3));
        //targetNum超过实际数量
        System.out.println(trie.keysWithPrefix("sp",4));
        System.out.println(trie.keysWithPrefix("s",3));

        System.out.println(trie.collect(3));
        System.out.println(trie.collect(4));
        System.out.println(trie.collect(5));
    }

    public static void testLongestPrefixOf(){
        MyTrieMap1 trie = new MyTrieMap1();
        trie.add("buck",10);
        trie.add("sad",12);
        trie.add("smog",5);
        trie.add("spit",15);
        trie.add("spite",20);
        trie.add("spy",7);

        System.out.println(trie.longestPrefixOf("spiteeeeeess"));
        System.out.println(trie.longestPrefixOf("spit"));
        if(trie.longestPrefixOf("bu")==null){
            System.out.println("bu fail" );
        }
        if(trie.longestPrefixOf("s")==null){
            System.out.println("s fail");
        }
    }
    public static void main(String[] args) {
//        testAdd();
        testKeysWithPrefixAndCollect();
//        testLongestPrefixOf();
    }
}
