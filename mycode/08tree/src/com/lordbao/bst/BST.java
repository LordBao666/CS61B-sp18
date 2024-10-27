package com.lordbao.bst;


/**
 * @Author Lord_Bao
 * @Date 2024/10/27 10:05
 * @Version 1.0
 */
public class BST<Key> {
    private Key key;
    private BST<Key> left;
    private BST<Key> right;

    private static <Key> void checkKey(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("KEY CAN NOT BE NULL!");
        }
    }

    public BST(Key key, BST<Key> left, BST<Key> right) {
        checkKey(key);
        this.key = key;
        this.left = left;
        this.right = right;
    }

    public BST(Key key) {
        checkKey(key);
        this.key = key;
        //left 和 right 默认为null
    }


    //静态方法声明的泛型于所属类声明的泛型是独立的，即使它们的名称相同

    /***
     * 以T为根节点，查询key对应的子树，如果存在，则返回该子树，如果不存在，则返回null.
     */
    public static <Key extends Comparable<Key>> BST<Key> search(BST<Key> T, Key key) {
        //T非空时，我们已经保证它的key不为null，因此这里无需再对T.key进行校验
        checkKey(key);
        if (T == null) {
            return null;
        }
        if (T.key.compareTo(key)==0) {
            return T;
        }
        if (T.key.compareTo(key) < 0) {
            return search(T.right, key);
        } else {
            return search(T.left, key);
        }
    }

    /**
     * 以T为根节点，插入key，并返回插入key之后的T。特别地，如果T为null，返回一棵新树。
     */
    public static <Key extends Comparable<Key>> BST<Key> insert(BST<Key> T, Key key) {
        checkKey(key);
        if (T == null) {
            return new BST<>(key);
        }
        if (T.key.compareTo(key) < 0) {
            T.right = insert(T.right, key);
        } else if (T.key.compareTo(key) > 0) {
            T.left = insert(T.left, key);
        }
        return T;
    }

    /**
     * 以T为根节点，删除key，返回删除key(无论key是否存在)之后的T。特别地，如果T为null，返回null。
     */
    public static <Key extends Comparable<Key>> BST<Key> delete(BST<Key> T, Key key) {
        checkKey(key);
        if (T == null) {
            return null;
        }
        if (T.key.compareTo(key) == 0) {
            if (T.left == null && T.right == null) {
                return null;
            }
            if (T.left != null && T.right == null) {
                return T.left;
            }
            if (T.right != null && T.left == null) {
                return T.right;
            }

            //此处用左侧树的最右端点来替换当前节点
            BST<Key> pre = T;
            BST<Key> next = T.left;
            while (next.right!=null){
                pre=next;
                next=next.right;
            }
            Key rightMostKey = next.key;
            //删除左侧树的最右端点
            if (pre!=T){
                pre.right=next.left;
            }else {
                pre.left=next.left;
            }
            T.key = rightMostKey;
            return T;

        } else if (T.key.compareTo(key) < 0) {
            T.right = delete(T.right, key);
        } else {
            T.left = delete(T.left, key);
        }
        return T;
    }


    /**
     * 对T进行遍历
     */
    public static <Key extends Comparable<Key>> void traverse(BST<Key> T){
        if(T==null){
            return;
        }

        traverse(T.left);
        System.out.print(T.key+" ");
        traverse(T.right);

    }


}
