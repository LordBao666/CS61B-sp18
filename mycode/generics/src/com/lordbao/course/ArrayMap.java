package com.lordbao.course;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author Lord_Bao
 * @Date 2024/5/26 16:22
 * @Version 1.0
 * <p>
 * In this implementation,we assume the map is full enough
 * to hold items.
 */
public class ArrayMap<K, V> implements Map61B<K, V> {
    private final Object[] keys;
    private final Object[] values;
    private int size;

    public ArrayMap() {
        int capacity = 100;
        keys = new Object[capacity];
        values = new Object[capacity];
        size = 0;
    }

    @Override
    public void put(K key, V value) {
        int keyIndex = getKeyIndex(key);
        //key is new
        if (keyIndex < 0) {
            keys[size] = key;
            values[size] = value;
            size++;
        } else { //key already exists, then override the value
            values[keyIndex] = value;
        }
    }

    @Override
    public boolean containsKey(K key) {
        int keyIndex = getKeyIndex(key);
        return keyIndex > -1;
    }

    /**
     * return the index of key if exists, or return -1
     */
    private int getKeyIndex(K key) {
        int i = 0;
        while (i < size) {
            if (Objects.equals(keys[i], key)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V get(K key) {
        int keyIndex = getKeyIndex(key);
        //no such key
        if (keyIndex < 0) {
            return null;
        } else {
            return (V) values[keyIndex];
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<K> keys() {
        List<K> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add((K)keys[i]);//here cast is legal
        }
        return list;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("this map is:\n");
        for (int i = 0; i < size; i++) {
            sb.append("key: ").append(keys[i]).append(",  value: ").append(values[i]).append("\n");
        }
        return sb.toString();
    }
}
