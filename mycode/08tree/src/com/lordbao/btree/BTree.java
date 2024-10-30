package com.lordbao.btree;


import java.lang.reflect.Array;
import java.util.LinkedList;

/**
 * @Author Lord_Bao
 * @Date 2024/10/30 11:36
 * @Version 1.0
 */
public class BTree<T extends Comparable<T>> {

    /**
     * 传入的type应该和你的T保持一致
     */
    private final Class<T> type;

    public BTree(Class<T> type) {
        this.type = type;
    }

    public BTree(Class<T> type, T[] data) {
        this.type = type;
        for (T ele : data) {
            add(ele);
        }
    }

    private TreeNode root;

    private class TreeNode {
        /**
         * 每个节点的值数量限制
         * 实际上，每个节点的data和children都会多预留一个位置，方便代码操作
         */
        private static final int L = 3;
        private final T[] data;
        private final TreeNode[] children;
        /**
         * 当前节点存储的值数目
         */
        private int size;

        @SuppressWarnings("unchecked")
        public TreeNode() {
            //实际上多预留一个位置方便代码操作
            data = (T[]) Array.newInstance(type, L + 1);
            //实际上多预留一个位置方便代码操作
            children = (TreeNode[]) Array.newInstance(TreeNode.class, L + 2);
            size = 0;
        }
    }

    /**
     * 查看节点(假定其不是null)是否为叶子节点
     */
    private boolean isLeaf(TreeNode p) {
        return p.children[0] == null;
    }


    /**
     * 查看节点(假定其不是null)存储的值数量已经超过限制
     */
    private boolean isOverStuffed(TreeNode p) {
        return p.size > TreeNode.L;
    }

    /**
     * 在叶子节点leaf插入data
     * 1.如果leaf存在与data相同的值，则不进行插入；
     * 2.否则，插入data。此步可能导致leaf中的值数量为L+1，这个在其他方法中进行处理
     */
    private void insertAtLeaf(TreeNode leaf, T data) {
        int i = 0;
        while (i < leaf.size) {
            if (data.compareTo(leaf.data[i]) == 0) {
                return;
            }
            if (data.compareTo(leaf.data[i]) < 0) {
                break;
            }
            i++;
        }
        //代码行至此处,leaf不存在与data相同的值，并且插入位置就是i
        for (int j = leaf.size - 1; j >= i; j--) {
            leaf.data[j + 1] = leaf.data[j];
        }
        leaf.data[i] = data;
        leaf.size++;
    }


    /***
     *查找child 在 parent 中的索引，如果不存在，则返回-1
     */
    private int findChildIndex(TreeNode parent, TreeNode child) {
        //注意 i<=parent.size,因为分区数比值数多1
        for (int i = 0; i <= parent.size; i++) {
            if (parent.children[i] == child) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 基于splitChild 生成左节点
     */
    private TreeNode createLeftChild(TreeNode splitTreeNode) {
        //拆分点
        int splitIndex = TreeNode.L / 2;
        return cloneTreeNode(splitTreeNode, 0, splitIndex);
    }

    /**
     * 基于splitChild 生成右节点
     */
    private TreeNode createRightChild(TreeNode splitTreeNode) {
        //拆分点
        int splitIndex = TreeNode.L / 2;
        return cloneTreeNode(splitTreeNode, splitIndex + 1, splitTreeNode.size);
    }


    /**
     * @param node  被复制的node
     * @param start 被复制的node 的data 的 起始下标(含)
     * @param stop  被复制的node 的 data 的 终止下标(不含)
     * @return 返回复制后的node
     */
    private TreeNode cloneTreeNode(TreeNode node, int start, int stop) {
        TreeNode p = new TreeNode();
        int i = 0;
        int j = start;
        while (j < stop) {
            p.data[i] = node.data[j];
            p.children[i] = node.children[j];
            i++;
            j++;
        }
        p.children[i] = node.children[j];
        p.size = i;
        return p;
    }

    /**
     * 从节点p开始分裂，stack中存储的是沿途上的父节点
     */
    private void split(TreeNode p, LinkedList<TreeNode> stack) {
        while (!stack.isEmpty() && isOverStuffed(p)) {
            //取出节点的父节点
            TreeNode parent = stack.removeLast();
            //p在父亲节点中的索引，该索引就等于p中上移元素将在parent插入的位置
            int childIndex = findChildIndex(parent, p);
            //data为上移元素
            int splitIndex = TreeNode.L / 2;
            T data = p.data[splitIndex];

            //拆分出左右2个孩子
            TreeNode leftChild = createLeftChild(p);
            TreeNode rightChild = createRightChild(p);

            //父节点将待插入点的值和区间往后移
            for (int j = parent.size - 1; j >= childIndex; j--) {
                parent.data[j + 1] = parent.data[j];
                parent.children[j + 2] = parent.children[j + 1];
            }
            parent.data[childIndex] = data;
            parent.children[childIndex] = leftChild;
            parent.children[childIndex + 1] = rightChild;
            parent.size++;

            p = parent;
        }

        //代码行至此处,isOverStuffed(p)为false，或stack.isEmpty()为true
        //如果isOverStuffed(p)为true，那么stack.isEmpty必然为true，此时代表
        //根节点不平衡，需要继续处理
        if (isOverStuffed(p)) {
            TreeNode leftChild = createLeftChild(p);
            TreeNode rightChild = createRightChild(p);
            int splitIndex = TreeNode.L / 2;
            T data = p.data[splitIndex];
            TreeNode rootNode = new TreeNode();
            rootNode.data[0] = data;
            rootNode.children[0] = leftChild;
            rootNode.children[1] = rightChild;
            rootNode.size = 1;
            root = rootNode;
        }

    }


    /**
     * 向平衡二叉树插入数据
     */
    public void add(T data) {
        if (root == null) {
            root = new TreeNode();
            root.data[0] = data;
            root.size = 1;
            return;
        }

        TreeNode p = root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (!isLeaf(p)) {
            stack.addLast(p);
            int i = 0;
            //如果data与节点p的某值相同，则程序结束，否则就需要与p的某个子节点继续比较
            TreeNode q = null;
            for (; i < p.size; i++) {
                if (data.compareTo(p.data[i]) == 0) {
                    return;
                } else if (data.compareTo(p.data[i]) < 0) {
                    q = p.children[i];
                    break;
                }
            }
            if (i == p.size) {
                q = p.children[p.size];
            }
            p = q;
        }

        //代码行到此处p必然是leaf
        insertAtLeaf(p, data);
        //进行可能的split
        split(p, stack);
    }

    private void traverse(TreeNode node) {
        if (node == null) {
            return;
        }
        for (int i = 0; i < node.size; i++) {
            traverse(node.children[i]);
            System.out.print(node.data[i] + " ");
        }
        traverse(node.children[node.size]);
    }

    /***
     * 对B树进行遍历，按照数据升序进行打印
     */
    public void traverse() {
        System.out.print("BTree[");
        traverse(root);
        System.out.println("]");
    }

    private boolean contains(TreeNode node, T data) {
        if (node == null) {
            return false;
        }
        int i = 0;
        for (; i < node.size; i++) {
            if (data.compareTo(node.data[i]) == 0) {
                return true;
            } else if (data.compareTo(node.data[i]) < 0) {
                return contains(node.children[i], data);
            }
        }
        return contains(node.children[i], data);
    }

    /***
     *检验平衡二叉树是否含data
     */
    public boolean contains(T data) {
        return contains(root, data);
    }

    public static void main(String[] args) {
        Integer[] arr = {13, 1, 2, 9, 10, 11, 5, 6, 12, 7, 3, 8, 14, 4};
        BTree<Integer> bTree = new BTree<>(Integer.class, arr);
        bTree.traverse();
        System.out.println(bTree.contains(1));
        System.out.println(bTree.contains(0));
        bTree.add(1);
        bTree.add(2);
        bTree.add(9);
        bTree.traverse();


        arr = new Integer[]{9, 11, 5, 6, 7, 2, 1, 8, 12, 4, 13, 10, 14, 3};
        bTree = new BTree<>(Integer.class, arr);
        bTree.traverse();


        arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        bTree = new BTree<>(Integer.class, arr);
        bTree.traverse();

        //1到200的随机排序
        arr = new Integer[]{163, 59, 86, 99, 129, 124, 157, 123, 101, 76, 78,
                160, 4, 63, 69, 148, 108, 120, 109, 194, 68, 13, 153, 177, 147,
                197, 39, 81, 103, 165, 110, 181, 184, 75, 35, 60, 80, 61, 92,
                100, 42, 46, 1, 187, 53, 128, 162, 62, 125, 49, 192, 104, 64,
                139, 45, 146, 14, 50, 10, 119, 85, 102, 179, 142, 167, 74, 117,
                34, 131, 170, 106, 173, 165, 77, 200, 114, 105, 162, 113, 56, 96,
                94, 116, 166, 25, 149, 7, 54, 6, 164, 155, 137, 112, 136, 141,
                84, 115, 22, 170, 130, 33, 57, 152, 51, 58, 97, 194, 93, 128, 154,
                19, 38, 72, 70, 175, 87, 171, 83, 137, 103, 24, 95, 20, 162, 66,
                132, 44, 90, 26, 178, 186, 127, 140, 191, 30, 48, 3, 18, 15, 199,
                8, 71, 73, 143, 133, 152, 107, 40, 47, 36, 2, 91, 23, 190, 55, 79,
                88, 43, 27, 89, 32, 180, 121, 106, 183, 41, 28, 21, 67, 158, 111,
                196, 9, 161, 165, 11, 176, 145, 31, 188, 82, 17, 126, 37, 138, 167,
                12, 187, 183, 175, 118, 122, 166, 172, 174, 189, 151, 16, 105, 31,
                157, 119, 173, 156, 180, 162, 152, 172, 154, 65, 29, 195, 181, 67,
                190, 5, 161, 191, 26, 168, 150, 143, 180, 113, 115, 98, 116, 157, 78,
                185, 189, 198, 52, 154, 101, 193, 183, 159, 9, 18, 141, 150, 84, 63,
                11, 125, 124, 152, 14, 144, 179, 2, 94, 38, 72, 188, 27, 115, 143, 95,
                177, 49, 94, 82, 48, 193, 186, 194, 40, 108, 72, 34, 125, 141, 22, 97,
                28, 179, 43, 39, 64, 0, 139, 170, 132, 87, 145, 142, 135, 197, 93, 166,
                76, 175, 109, 179, 32, 81, 66, 45, 107, 167, 199, 191, 1, 61, 136, 128,
                119, 51, 182, 97, 157, 175, 88, 163, 96, 53, 120, 35, 168, 105, 139, 65,
                153, 169, 4, 156, 10, 19, 3, 190, 37, 191, 4, 162, 197, 178};
        bTree = new BTree<>(Integer.class, arr);
        bTree.traverse();


    }
}
