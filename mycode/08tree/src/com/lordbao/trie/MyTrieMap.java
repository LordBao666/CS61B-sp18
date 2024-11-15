package com.lordbao.trie;


import java.util.List;

/**
 * @Author Lord_Bao
 * @Date 2024/11/14 11:40
 * @Version 1.0
 *
 * 数据结构Trie的key类型为String，因此只需提供泛型Val
 */
public interface MyTrieMap<Val> {
    /**
     * 添加key-val对
     */
    public void add(String key,Val val);

    /**
     * 返回是否含key
     */
    public boolean contains(String key);

    /**
     * 返回key 对应的val，如果key不存在，则返回null
     */
    public Val get(String key);

    /**
     * 返回pattern的最长前缀匹配。如果不存在匹配，则返回null.
     * 比如假设字典树存储了apple,app,
     * 1.当参数为apples时,应该返回apple,
     * 2.当参数为appl时,应该返回app,
     * 3.当参数为bpp 或 ap时，应该返回null.
     */
    public String  longestPrefixOf(String pattern);

    /**
     * 返回以pattern为前缀的key，期望返回数量为targetNum的若干最高权重的字符串。
     * 如果不足targetNum，则返回实际数量的匹配字符串，
     * 特别地，当不存在匹配pattern的String，返回一个空的List(注意不是返回null)。
     */
    public List<String> keysWithPrefix(String pattern,int targetNum);

    /**
     * 返回权重最大的targetNum个字符串，如果实际数量没有targetNum,返回实际数量的字符串即可
     */
    public List<String> collect(int targetNum);
}
