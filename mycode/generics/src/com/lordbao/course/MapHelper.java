package com.lordbao.course;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * @Author Lord_Bao
 * @Date 2024/5/26 18:12
 * @Version 1.0
 */
public class MapHelper {
    public static <K, V> V get(Map61B<K, V> map, K key) {
        if (map == null) {
            return null;
        }
        return map.get(key);
    }

    /**
     * Returns the maximum of all keys in the given Map.
     * Works only if keys can be compared.
     */
    public static <K extends Comparable<? super K>, V> K maxKey(Map61B<K, V> map) {
        if (map == null) {
            return null;
        }
        List<K> keys = map.keys();
        K maxKey = keys.get(0);
        for (K key : keys) {
            if (maxKey.compareTo(key) < 0) {
                maxKey = key;
            }
        }
        return maxKey;
    }

    /**
     * Returns the maximum of all keys in the given Map given the comparator provided.
     * Assume comp is not null
     */
    public static <K, V> K maxKey(Map61B<K, V> map, Comparator<? super K> comp) {
        if (map == null) {
            return null;
        }
        List<K> keys = map.keys();
        K maxKey = keys.get(0);
        for (K key : keys) {
            if (comp.compare(maxKey, key) < 0) {
                maxKey = key;
            }
        }
        return maxKey;
    }
}
