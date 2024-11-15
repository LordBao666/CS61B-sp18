package com.lordbao.trie;


import java.util.*;

/**
 * @Author Lord_Bao
 * @Date 2024/11/14 11:50
 * @Version 1.0
 */
public class MyTrieMap1 implements MyTrieMap<Integer> {
    private MyTrieNode root;

    private static class MyTrieNode {

        private boolean isKey;
        private final Character c;
        /**
         * 节点的默认值为-1
         */
        private static final Integer DEFAULT_NODE_VAL = -1;
        /**
         * 如果节点不是key，则curNodeVal 默认为-1。默认所有为key的节点的值都为非负数。
         * 因此，实际上可以通过curNodeVal是否为负数来判断是否是key节点。但为了可读性，
         * 还是引入isKey变量.
         */
        private Integer curNodeVal;
        /**
         * bestChildVal存储的是后代节点中为key的节点中val的最大值。
         * 显然仅叶子节点的bestChildVal为默认值-1
         */
        private Integer bestChildVal;
        /**
         * bestChild 指向的是含后代节点中key的节点中val的最大值的直接子代
         * 举例说明，比如当前节点的存a, 它后面字典树可能是pple(权重15),mong(权重18)
         * 那么这里的bestChild指向的就是节点m。
         * 如果没有后代，那么bestChild 为 null
         */
        private MyTrieNode bestChild;
        private final HashMap<Character, MyTrieNode> map;


        public MyTrieNode(boolean isKey, Character c, Integer curNodeVal) {
            this.isKey = isKey;
            this.c = c;
            this.curNodeVal = curNodeVal;
            this.bestChild = null;
            this.bestChildVal = DEFAULT_NODE_VAL;
            this.map = new HashMap<>();
        }


        /**
         * 是否为叶子节点
         */
        private boolean isLeaf() {
            return map.isEmpty();
        }
    }


    public MyTrieMap1() {
        root = new MyTrieNode(false, null, MyTrieNode.DEFAULT_NODE_VAL);
    }

    /**
     * child 是 parent的直接子代,新插入的权值val沿着child的方向进行.
     * e.g 假设要插入一个新的字符串  candy, 权重为15，此时的parent为c，child为a，val=15
     * 本函数是为了修改parent的bestChild 和 bestChildVal
     */
    private void dealWithBestChildOfParent(MyTrieNode parent, MyTrieNode child, Integer val) {
        //parent.bestChild是允许为null的，因为child可能正好是它的第一个孩子
        if (parent.bestChild == null || val >= parent.bestChildVal) {
            parent.bestChildVal = val;
            parent.bestChild = child;
        } else {
            //如果child 正好是 root.bestChild, 那么要做一些特殊处理
            if (child == parent.bestChild) {
                MyTrieNode maxChildNode = null;
                int maxChildValue = -1;
                for (MyTrieNode node : parent.map.values()) {
                    //从非child的孩子节点中挑选最大值
                    if (node != child) {
                        int tempVal;
                        if (node.isKey) {
                            //就算node是叶子节点，即它的bestChildVal=-1,下面的公式也适用
                            tempVal = Math.max(node.curNodeVal, node.bestChildVal);
                        } else {
                            tempVal = node.bestChildVal;
                        }

                        if (tempVal > maxChildValue) {
                            maxChildNode = node;
                            maxChildValue = tempVal;
                        }
                    }
                }
                if (maxChildValue < val) {
                    parent.bestChildVal = val;
                    parent.bestChild = child;
                } else {
                    parent.bestChildVal = maxChildValue;
                    parent.bestChild = maxChildNode;
                }

            }
        }
    }

    /**
     * 以root为字典子树根节点，添加以index为起始下标(含)的key的子串，其中val为key的权重。
     */
    private void addHelper(MyTrieNode root, String key, int index, Integer val) {
        if (index >= key.length()) {
            return;
        }

        boolean isReachStringEnd = key.length() - 1 == index;
        MyTrieNode matchChildNode;
        Character c = key.charAt(index);
        if (root.map.containsKey(c)) {
            matchChildNode = root.map.get(c);
            if (isReachStringEnd) {
                matchChildNode.isKey = true;
                matchChildNode.curNodeVal = val;
                //无需更新matchChildNode.bestChild 和 bestChildVal,因为这是一个相对的操作
                //当addHelper方法执行到matchChildNode 为 某个子节点的root时,自然会更新
                //matchChildNode.bestChild 和 bestChildVal
            }

            //1.无需设置 matchChildNode.isKey=false
            //比如先前已经添加串sam，后来你再添加same的时候，对于m节点，你不应该设置它为false.
            //2.无需设置 curNodeVal,因为它不是key节点
            dealWithBestChildOfParent(root, matchChildNode, val);

        } else {

            matchChildNode = isReachStringEnd ? new MyTrieNode(true, c, val) :
                    new MyTrieNode(false, c, MyTrieNode.DEFAULT_NODE_VAL);
            root.map.put(c, matchChildNode);
            dealWithBestChildOfParent(root, matchChildNode, val);
        }

        addHelper(matchChildNode, key, index + 1, val);

    }

    @Override
    public void add(String key, Integer val) {
        if (key == null || key.length() == 0) throw new IllegalArgumentException("key不能为空串，添加失败");
        addHelper(root, key, 0, val);
    }

    @Override
    public boolean contains(String key) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("key不能为空串，查找失败");
        }
        return get(key) != null;
    }

    /**
     * 返回key对应的权重，如果key不存在，则返回null
     */
    @Override
    public Integer get(String key) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("key不能为空串，查找失败");
        }
        int i = 0;
        MyTrieNode node = root;
        while (i < key.length()) {
            if (node.map.containsKey(key.charAt(i))) {
                node = node.map.get(key.charAt(i));
                i++;
            } else {
                return null;
            }
        }
        if (node.isKey) {
            return node.curNodeVal;
        } else {
            return null;
        }
    }

    /**
     *返回pattern的最长前缀的最后一个字符的下标+1.
     * 举例，假设pattern 为 hello，而字典树中存取有hell，he，
     * 那么此函数应该返回3+1，即4.
     * 如果不存在最长前缀，则返回-1
     */
    private int longestPrefixOfHelper(String pattern) {
        MyTrieNode node = root;
        int index = 0;
        int d = -1;
        while (index < pattern.length() && node.map.containsKey(pattern.charAt(index))) {
            node = node.map.get(pattern.charAt(index));
            index++;
            if (node.isKey) {
                d = index;
            }
        }
        return d;
    }

    @Override
    public String longestPrefixOf(String pattern) {
        if (pattern == null || pattern.length() == 0) {
            return null;
        }
        int index = longestPrefixOfHelper(pattern);
        if (index < 0) {
            return null;
        }
        return pattern.substring(0, index);
    }

    private static class MyTrieNodePair {
        private final MyTrieNode node;
        private final StringBuilder sb;

        public MyTrieNodePair(MyTrieNode node, StringBuilder sb) {
            this.node = node;
            this.sb = sb;
        }
    }

    /**
     * 收集以root为字典树的根节点的权重最大的targetNum 个字符串到list中，如果不足targetNum个，则按实际
     * 情况收集。
     * 注意不考虑root本身，只考虑root的孩子节点.
     * prefix表示已经拼接的部分前缀。
     */
    private void collectHelper(MyTrieNode root, String prefix, List<String> list, int targetNum) {
        Comparator<MyTrieNodePair> comp = (o1, o2) -> {
            int val1, val2;
            if (!o1.node.isKey) {
                val1 = o1.node.bestChildVal;
            } else {
                //就算o1是叶子节点，即bestChild为-1时，下面公式也成立. o2同理.
                val1 = Math.max(o1.node.bestChildVal, o1.node.curNodeVal);
            }
            if (!o2.node.isKey) {
                val2 = o2.node.bestChildVal;
            } else {
                val2 = Math.max(o2.node.bestChildVal, o2.node.curNodeVal);
            }
            //建立大根堆，用val2-val1
            return val2 - val1;
        };

        PriorityQueue<MyTrieNodePair> queue = new PriorityQueue<>(comp);
        for (MyTrieNode node : root.map.values()) {
            queue.add(new MyTrieNodePair(node, new StringBuilder(prefix)));
        }

        while (!queue.isEmpty() && list.size() < targetNum) {
            MyTrieNodePair nodePair = queue.poll();
            MyTrieNode node = nodePair.node;
            StringBuilder sb = nodePair.sb;
            if (node.isKey) {
                //这说明node必然有着权重更大的孩子
                //那么必须要将当前node克隆一份(不再包含任何孩子),然后加入到优先级队列
                if (node.curNodeVal < node.bestChildVal) {
                    queue.add(new MyTrieNodePair(new MyTrieNode(true, node.c, node.curNodeVal),
                            new StringBuilder(sb)));
                    sb.append(node.c);
                } else {
                    sb.append(node.c);
                    list.add(sb.toString());
                }
            } else {
                sb.append(node.c);
            }

            for (MyTrieNode child : node.map.values()) {
                queue.add(new MyTrieNodePair(child, new StringBuilder(sb)));
            }
        }
    }

    /**
     * 返回权重最大的targetNum个字符串，如果实际数量没有targetNum,返回实际数量的字符串即可
     */
    public List<String> collect(int targetNum) {
        List<String> list = new ArrayList<>();
        collectHelper(root, "", list, targetNum);
        return list;
    }

    /**
     * 返回匹配pattern的MyTrieNode
     * e.g root->存a的节点1->存b的节点2， 如果我传入的参数是 "ab",那么就需要返回节点2。
     * 如果这样的节点不存在，则返回null。
     * pattern不能为空串，空串则抛出异常。
     */
    private MyTrieNode find(String pattern) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("pattern 不能为空串，查找失败");
        }

        MyTrieNode node = root;
        int i = 0;
        while (i < pattern.length()) {
            if (node.map.containsKey(pattern.charAt(i))) {
                node = node.map.get(pattern.charAt(i));
                i++;
            } else {
                return null;
            }
        }
        return node;
    }

    /**
     * pattern不能为空串，空串则抛出异常。
     * 如果你想返回整个字典树权重最高的targetNum个字符串，请使用collect函数。
     */
    @Override
    public List<String> keysWithPrefix(String pattern, int targetNum) {
        List<String> list = new ArrayList<>();
        MyTrieNode node = find(pattern);
        if (node == null) {
            return list;
        }
        collectHelper(node, pattern, list, targetNum);
        return list;
    }
}
