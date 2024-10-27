package com.lordbao.bst;


/**
 * @Author Lord_Bao
 * @Date 2024/10/27 15:58
 * @Version 1.0
 */
public class BST2<Key extends Comparable<Key>> {
    private TreeNode root;

    private class TreeNode {
        private Key key;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(Key key, TreeNode left, TreeNode right) {
            checkKey(key);
            this.left = left;
            this.right = right;
            this.key = key;
        }

        public TreeNode(Key key) {
            checkKey(key);
            this.key = key;
        }
    }

    public BST2() {
    }

    private void checkKey(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("KEY CAN NOT BE NULL!");
        }
    }

    private TreeNode insert(TreeNode treeNode, Key key) {
        checkKey(key);
        if (treeNode == null) {
            return new TreeNode(key);
        }
        if (treeNode.key.compareTo(key) < 0) {
            treeNode.right = insert(treeNode.right, key);
        } else if (treeNode.key.compareTo(key) > 0) {
            treeNode.left = insert(treeNode.left, key);
        }
        return treeNode;
    }

    public void insert(Key key) {
       root= insert(root, key);
    }


    private TreeNode search(TreeNode treeNode, Key key) {
        checkKey(key);
        if (treeNode == null) {
            return null;
        }
        if (treeNode.key.compareTo(key) == 0) {
            return treeNode;
        }

        if (treeNode.key.compareTo(key) < 0) {
            return search(treeNode.right, key);
        } else
            return search(treeNode.left, key);
    }


    /**
     * 检验key是否存在于二分查找树中
     */
    public boolean search(Key key) {
        return search(root,key)!=null;
    }

    private TreeNode  delete(TreeNode treeNode,Key key){
        checkKey(key);
        if (treeNode == null) {
            return null;
        }
        if (treeNode.key.compareTo(key) == 0) {
            if (treeNode.left == null && treeNode.right == null) {
                return null;
            }
            if (treeNode.left != null && treeNode.right == null) {
                return treeNode.left;
            }
            if (treeNode.right != null && treeNode.left == null) {
                return treeNode.right;
            }

            //此处用左侧树的最右端点来替换当前节点
            TreeNode pre = treeNode;
            TreeNode next = treeNode.left;
            while (next.right!=null){
                pre=next;
                next=next.right;
            }
            Key rightMostKey = next.key;
            //删除左侧树的最右端点
            if (pre!=treeNode){
                pre.right=next.left;
            }else {
                pre.left=next.left;
            }
            treeNode.key = rightMostKey;
            return treeNode;

        } else if (treeNode.key.compareTo(key) < 0) {
            treeNode.right = delete(treeNode.right, key);
        } else {
            treeNode.left = delete(treeNode.left, key);
        }
        return treeNode;
    }


    public void delete(Key key){
       root= delete(root,key);
    }

    private void traverse(TreeNode treeNode){
        if(treeNode==null){
            return;
        }
        traverse(treeNode.left);
        System.out.print(treeNode.key+" ");
        traverse(treeNode.right);
    }


    public void traverse(){
        System.out.print("BST[");
        traverse(root);
        System.out.println("]");
    }

}



