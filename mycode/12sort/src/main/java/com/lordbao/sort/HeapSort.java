package com.lordbao.sort;


import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author Lord_Bao
 * @Date 2024/11/8 12:25
 * @Version 1.0
 *
 * 如果不提供比较器，默认按照升序排序。我们逻辑上构建的是大根堆。
 * 底层为数组，下标从0开始。
 */
public class HeapSort{

    public static <E> void sort(E [] arr){
        sort(arr,null);
    }
    public static  <E> void sort(E [] arr,Comparator<E> comp){
        InnerMaxHeap<E> heap = new InnerMaxHeap<>(arr,comp);
        for(int i=arr.length-1;i>=0;i--){
            heap.swap(0,i);
            heap.siftDown(0, i);
        }
    }

    /***
     *不破坏原数组
     */
    public static <E> E[] sortNoHarm(E [] arr){
        return sortNoHarm(arr,null);
    }
    /***
     *不破坏原数组
     */
    public static  <E> E[] sortNoHarm(E [] arr,Comparator<E> comp){
        E[] newArr = Arrays.copyOf(arr, arr.length);
        sort(newArr,comp);
        return newArr;
    }



    /***
     *内置大根堆
     */
    private static class InnerMaxHeap<E> {
        private final E [] arr;
        private final Comparator<E> comp;
        public InnerMaxHeap(E [] arr, Comparator<E> comp){
            this.arr = arr;
            this.comp = comp;
            heapify();
        }
        public InnerMaxHeap(E [] arr){
            this(arr,null);
        }

        /**初始建堆算法*/
        private void heapify(){
            int n = arr.length;
            //最后一个非叶节点的索引
            int i = (n>>1)-1;
            for(;i>=0;i--){
                siftDown(i,n);
            }
        }



        /**
         *
         * subLen表示arr前subLen个元素构成的子数组大小，对坐标i的元素在由该数组构成的子树中下潜
         * */
        private void siftDown(int i,int subLen) {
            if(comp!=null){
                siftDownWithComparator(i,subLen);
            }else{
                siftDownComparable(i,subLen);
            }
        }

        /**
         * 对下标i和j的元素进行交换
         */
        private void swap(int i,int j){
            E temp = arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
        }

        /**
         *
         * subLen表示arr前subLen个元素构成的子数组大小，检验以i为节点的左孩子在该子数组构成的子树中是否存在。
         */
        private boolean isLeftChildExists(int i,int subLen){
            return (i<<1)+1<subLen;
        }
        /**
         * subLen表示arr前subLen个元素构成的子数组大小，检验以i为节点的右孩子在该子数组构成的子树中是否存在。
         */
        private boolean isRightChildExists(int i,int subLen){
            return (i<<1)+2<subLen;
        }

        /**返回左孩子下标*/
        private int leftChildIndex(int i){
            return  (i<<1)+1;
        }
        /**返回右孩子下标*/
        private int rightChildIndex(int i){
            return  (i<<1)+2;
        }

        /**
         * comp 为null时，调用此函数下潜。如果元素不可比较，
         * 将发生转换异常。
         * subLen表示arr前subLen个元素构成的子数组大小，对坐标i的元素在由该数组构成的子树中下潜
         */
        @SuppressWarnings("unchecked")
        private void siftDownComparable(int i,int subLen) {
            Comparable<E> e = (Comparable<E>) arr[i];
            while (isLeftChildExists(i,subLen)){
                int maxIndex = leftChildIndex(i);
                if(isRightChildExists(i,subLen)){
                    Comparable<E> leftChild = (Comparable<E>)arr[leftChildIndex(i)];
                    //如果右孩子更大
                    if(leftChild.compareTo(arr[rightChildIndex(i)])<0){
                        maxIndex=rightChildIndex(i);
                    }
                }
                if(e.compareTo(arr[maxIndex])<0){
                    swap(i,maxIndex);
                    i=maxIndex;
                }else {
                    break;
                }
            }
        }

        /**
         * comp 不为null时，调用此函数下潜
         * subLen表示arr前subLen个元素构成的子数组大小，对坐标i的元素在由该数组构成的子树中下潜
         */
        private void siftDownWithComparator(int i,int subLen) {
            while (isLeftChildExists(i,subLen)){
                int maxIndex = leftChildIndex(i);
                if(isRightChildExists(i,subLen)){
                    //右孩子更大
                    if(comp.compare(arr[leftChildIndex(i)],arr[rightChildIndex(i)])<0){
                        maxIndex=rightChildIndex(i);
                    }
                }
                if(comp.compare(arr[i],arr[maxIndex])<0){
                    swap(i,maxIndex);
                    i=maxIndex;
                }else {
                    break;
                }
            }
        }
    }
}

