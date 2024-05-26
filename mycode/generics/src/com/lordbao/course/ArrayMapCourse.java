package com.lordbao.course;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Lord_Bao
 * @Date 2024/5/26 16:50
 * @Version 1.0
 * <p>
 * The implementation is taken from course with a little modification.
 */
public class ArrayMapCourse<K, V> implements Map61B<K, V> {

    private final K[] keys;
    private final V[] values;
    int size;

    @SuppressWarnings("unchecked")
    public ArrayMapCourse() {
        keys = (K[]) new Object[100];
        values = (V[]) new Object[100];
        size = 0;
    }

    /**
     * Returns the index of the key, if it exists. Otherwise returns -1.
     **/
    private int keyIndex(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return i;
            }
        }
        return -1;

    }

    public boolean containsKey(K key) {
        int index = keyIndex(key);
        return index > -1;
    }

    public void put(K key, V value) {
        int index = keyIndex(key);
        if (index == -1) {
            keys[size] = key;
            values[size] = value;
            size += 1;
        } else {
            values[index] = value;
        }
    }

    public V get(K key) {
        int index = keyIndex(key);
        return index < 0 ? null : values[index];
    }

    public int size() {
        return size;
    }

    public List<K> keys() {
        List<K> keyList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            keyList.add(keys[i]);
        }
        return keyList;
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
