package com.lordbao.llbt;


import java.util.ArrayList;
import java.util.List;

/**
 * @Author Lord_Bao
 * @Date 2024/11/1 12:44
 * @Version 1.0
 */
public class MyTreeMap<Key extends Comparable<Key>, Val> {
    private Node root;

    private class Node {
        private static final boolean RED = true;
        private static final boolean BLACK = false;

        private Key key;
        private Val val;
        //指向父节点的链接颜色
        private boolean isRed;
        private Node left;
        private Node right;

        public Node() {
            this(null, null, RED, null, null);
        }

        public Node(Key key, Val val) {
            this(key, val, RED, null, null);
        }

        public Node(Key key, Val val, boolean isRed) {
            this(key, val, isRed, null, null);
        }

        public Node(Key key, Val val, boolean isRed, Node right, Node left) {
            this.key = key;
            this.left = left;
            this.isRed = isRed;
            this.right = right;
            this.val = val;
        }

    }

    public void add(Key key, Val val) {
        root = add(root, key, val);
    }

    /**
     * 判断一个节点指向其父节点的链是否为红色。
     * 注意如果node为null，我们返回Black
     */
    private boolean isRed(Node node) {
        if (node == null) {
            return Node.BLACK;
        }
        return node.isRed;
    }

    /**
     * 对节点node进行右旋，假定node不为空，且node.left不为空。
     * 返回旋转之后的根节点。注意，node 和 node.left指向父节点的着色要互换。
     */
    private Node rotateRight(Node node) {
        //右旋
        Node leftChild = node.left;
        node.left = leftChild.right;
        leftChild.right = node;

        //换色
        boolean color = node.isRed;
        node.isRed = leftChild.isRed;
        leftChild.isRed = color;
        return leftChild;
    }

    /**
     * 对节点node进行左旋，假定node不为空，且node.right不为空。
     * 返回旋转之后的根节点。注意，node 和 node.right指向父节点的着色要互换。
     */
    private Node rotateLeft(Node node) {
        //左旋
        Node rightChild = node.right;
        node.right = rightChild.left;
        rightChild.left = node;

        //换色
        boolean color = node.isRed;
        node.isRed = rightChild.isRed;
        rightChild.isRed = color;
        return rightChild;
    }

    /**
     * 假定node 和 node.left 和 node.right均不为空
     * node的左右节点的isRed设置为black，表示指向父节点的链为black
     * node的isRed设置为red，表示指向父节点的链为red
     */
    private void flipColors(Node node) {
        node.left.isRed = Node.BLACK;
        node.right.isRed = Node.BLACK;
        node.isRed = Node.RED;
    }

    /***
     * 以node为根节点，返回根据key 添加 val后的子树的根节点
     * 特别地，如果node为null，返回一个指向父节点为红链的节点。
     */
    private Node add(Node node, Key key, Val val) {
        if (node == null) {
            //设计规则，添加新节点的链必须是红色
            return new Node(key, val, Node.RED);
        }
        int cmp = node.key.compareTo(key);
        if (cmp < 0) {
            node.right = add(node.right, key, val);
        } else if (cmp > 0) {
            node.left = add(node.left, key, val);
        } else {
            node.val = val;
        }
        //左黑右红的处理是左旋
        if (!isRed(node.left) && isRed(node.right)) {
            node = rotateLeft(node);
            //双左红的处理是右旋
            //此处无需考虑node.left.left是否出现空指针,
            // 因为isRed(node.left)为true，表示node.left必然非空
        } else if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
            //左右均红的处理是换色
        } else if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }

    public boolean contains(Key key) {
        Node node = getHelper(root, key);
        return node != null;
    }

    /**
     * 返回key对应的value，如果不存在，则返回null
     */
    public Val get(Key key) {
        Node node = getHelper(root, key);
        return node == null ? null : node.val;
    }

    /***
     *以node为根节点，如果含key，则返回对应的节点，否在返回null
     */
    private Node getHelper(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = node.key.compareTo(key);
        if (cmp == 0) {
            return node;
        } else if (cmp < 0) {
            return getHelper(node.right, key);
        } else {
            return getHelper(node.left, key);
        }
    }


    public void traverse() {
        if (root == null) {
            System.out.println(this.getClass().getSimpleName() + " is empty");
            return;
        }
        System.out.println(this.getClass().getSimpleName() + ":");
        traverse(root);
    }

    private void traverse(Node node) {
        if (node == null) {
            return;
        }
        traverse(node.left);
        System.out.println(node.key + ":" + node.val);
        traverse(node.right);
    }

    public List<Key> getKeys(){
        return getKeys(root,new ArrayList<>());
    }
    private List<Key> getKeys(Node node,List<Key> keys){
        if(node==null){
            return keys;
        }
        keys = getKeys(node.left,keys);
        keys.add(node.key);
        keys = getKeys(node.right,keys);
        return keys;
    }

    public List<Val> getVals(){
        return getVals(root,new ArrayList<>());
    }
    private List<Val> getVals(Node node,List<Val> vals){
        if(node==null){
            return vals;
        }
        vals = getVals(node.left,vals);
        vals.add(node.val);
        vals = getVals(node.right,vals);
        return vals;
    }
}
