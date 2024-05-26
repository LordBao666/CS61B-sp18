package com.lordbao.course;

import java.awt.image.Kernel;
import java.util.List;

/**
 * @Author Lord_Bao
 * @Date 2024/5/26 16:17
 * @Version 1.0
 */
public interface Map61B<K, V> {
    /**
     * Associate key with value
     */
    void put(K key, V value);

    /**
     * Checks if map contains the key
     */
    boolean containsKey(K key);

    /**
     * Returns value, assuming key exists.
     */
    V get(K key);

    /**
     * Returns a list of all keys.
     */
    List<K> keys();

    /**
     * Returns number of keys
     */
    int size();
}
