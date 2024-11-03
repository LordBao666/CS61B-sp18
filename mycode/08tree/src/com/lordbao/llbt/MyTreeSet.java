package com.lordbao.llbt;


import java.util.List;
import java.util.Objects;

/**
 * @Author Lord_Bao
 * @Date 2024/11/1 15:26
 * @Version 1.0
 */
public class MyTreeSet <Key extends  Comparable<Key>>{
    private static final Integer VAL = 0;
    private final  MyTreeMap<Key,Integer> map;


    public MyTreeSet(){
        map = new MyTreeMap<>();
    }

    public void  add(Key key){
        map.add(key,VAL);
    }

    public boolean contains(Key key){
        return map.contains(key);
    }

    public List<Key> getKeys(){
        return map.getKeys();
    }

}
