package com.lordbao.hash;


import java.util.List;

/**
 * @Author Lord_Bao
 * @Date 2024/11/3 23:06
 * @Version 1.0
 */
public class MyHashSet<Key> {
    private final MyHashMap<Key,Integer> hashMap;
    private static final Integer VAL = 0;
    public MyHashSet(){
        hashMap = new MyHashMap<>();
    }

    public MyHashSet(double loadFactor){
        hashMap = new MyHashMap<>(loadFactor);
    }

    public void  add(Key key){
        hashMap.add(key,VAL);
    }

    public boolean contains(Key key){
        return hashMap.contains(key);
    }

    public List<Key> getKeys(){
        return hashMap.getKeys();
    }

    public int getSize(){
        return hashMap.getSize();
    }

}
