package com.lordbao.hash;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @Author Lord_Bao
 * @Date 2024/11/3 20:10
 * @Version 1.0
 */
public class MyHashMap<K, V> {

    /**
     * 负载因子 默认为1.5
     */
    private final double loadFactor;
    /**
     * buckets的初始数量为8
     * bucket的实际数量就是hashTable的length
     */
    private static final int INITIAL_BUCKET_SIZE = 8;

    /**
     * 存储元素个数
     */
    private int size;
    private List<HashNode>[] hashTable;

    public MyHashMap() {
        //默认装载因子为1.5
        this(1.5);
    }

    @SuppressWarnings("unchecked")
    public MyHashMap(double loadFactor) {
        this.loadFactor = loadFactor;
        hashTable = new LinkedList[INITIAL_BUCKET_SIZE];
        size = 0;
    }

    private class HashNode {
        private K key;
        private V val;

        public HashNode(K key, V val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public String toString() {
            return "Key: "+key+", "+"Val: "+val;
        }
    }

    public class EntrySet{
        public K key;
        public V val;

        public EntrySet(K key, V val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public String toString() {
            return "EntrySet{" +
                    "key=" + key +
                    ", val=" + val +
                    '}';
        }
    }

    /**
     * 根据key 和 bucketSize 返回对应的bucket分区
     */
    private int getBucketSec(K key, int bucketsSize) {
        int hash = key.hashCode();
        return Math.floorMod(hash, bucketsSize);
    }

    @SuppressWarnings("unchecked")
    public void add(K key, V val) {
        int bucketSize = hashTable.length;
        int sec = getBucketSec(key, bucketSize);
        hashTable[sec] = add(hashTable[sec], key, val, true);
        //进行可能的扩容
        resize();
    }

    /**
     * 该方法将 key-val 插入到指定bucket中。
     * 1 如果key存在于bucket中，则更新val；
     * 2 如果key不存在于bucket中，则添加key-val到bucket的尾部。
     * <p>
     * bucket允许为null，此时添加key-val，并返回新的bucket。
     * 如果isModifiedSize 为true，表示此方法需要顺带将size加1，否则不能修改size
     */
    private List<HashNode> add(List<HashNode> bucket, K key, V val, boolean isModifiedSize) {
        if (bucket == null) {
            LinkedList<HashNode> newBucket = new LinkedList<>();
            newBucket.addLast(new HashNode(key, val));
            if (isModifiedSize) {
                size++;
            }
            return newBucket;
        }
        for (HashNode node : bucket) {
            if (Objects.equals(node.key, key)) {
                node.val = val;
                return bucket;
            }
        }
        //代码行到此处bucket不为null，并且不存在key
        bucket.addLast(new HashNode(key, val));
        if (isModifiedSize) {
            size++;
        }
        return bucket;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int bucketsSize = hashTable.length;
        //size/bucketSize> loadFactor ，按照2倍扩容
        if (size > loadFactor * bucketsSize) {

            int newBucketSize = bucketsSize * 2;
            List<HashNode>[] newHashTable = new LinkedList[newBucketSize];

            for (List<HashNode> bucket : hashTable) {
                if (bucket != null) {
                    for (HashNode node : bucket) {
                        int sec = getBucketSec(node.key, newBucketSize);
                        newHashTable[sec] = add(newHashTable[sec], node.key, node.val, false);
                    }
                }
            }

            hashTable=newHashTable;
        }
    }

    @SuppressWarnings("unchecked")
    public boolean contains(K key) {
        return get(key)!=null;
    }


    @SuppressWarnings("unchecked")
    public List<K> getKeys() {
        List<K> list = new ArrayList<>();
        for (List<HashNode> bucket : hashTable) {
            if (bucket != null) {
                for (HashNode node : bucket) {
                    list.add(node.key);
                }
            }
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<V> getVals() {
        List<V> list = new ArrayList<>();
        for (List<HashNode> bucket : hashTable) {
            if (bucket != null) {
                for (HashNode node : bucket) {
                    list.add(node.val);
                }
            }
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public void printHashTables() {
        if (size == 0) {
            System.out.println(this.getClass().getSimpleName() + "is empty");
            return;
        }
        System.out.println(this.getClass().getSimpleName() + " is as follows:");
        for (List<HashNode> bucket : hashTable) {
            if (bucket != null) {
                for (HashNode node : bucket) {
                    System.out.println("Key:" + node.key + ", Val:" + node.val);
                }
            }
        }
    }

    /**
     *返回key对应的val，如果不存在，则返回null。
     */
    @SuppressWarnings("unchecked")
    public V get(K key){
        int bucketSize =hashTable.length;
        int sec = getBucketSec(key, bucketSize);
        List<HashNode> bucket = hashTable[sec];
        if(bucket==null){
            return null;
        }

        for(HashNode node:bucket){
            if(Objects.equals(node.key,key)){
                return node.val;
            }
        }
        return null;
    }

    public List<EntrySet> getEntrySet(){
        List<EntrySet> list = new ArrayList<>();
        for (List<HashNode> bucket : hashTable) {
            if (bucket != null) {
                for (HashNode node : bucket) {
                    list.add(new EntrySet(node.key,node.val));
                }
            }
        }
        return list;
    }


    public int getSize(){
        return size;
    }
}
