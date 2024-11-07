package com.lordbao.pq;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Author Lord_Bao
 * @Date 2024/11/6 11:44
 * @Version 1.0
 */
public class MyPriorityQueue<E> {
    private E[] items;
    //初始容量大小为11
    private static final int DEFAULT_INITIAL_CAPACITY = 11;
    //比较器
    private final Comparator<E>  comp;
    //实际元素个数
    private int size;
    //实际容量
    private int capacity;

    public MyPriorityQueue() {
        this((Comparator<E>) null);
    }

    @SuppressWarnings("unchecked")
    public MyPriorityQueue(Comparator<E> comp) {
        this((E[]) new Object[]{},comp);
    }

    public MyPriorityQueue(List<E> list) {
        this(list, null);
    }

    public MyPriorityQueue(E[] arr) {
        this(arr, null);
    }

    @SuppressWarnings("unchecked")
    public MyPriorityQueue(List<E> list, Comparator<E> comp) {
        this(list.toArray((E[]) new Object[]{}), comp);
    }

    public MyPriorityQueue(E[] arr, Comparator<E> comp) {
        if (arr.length <= DEFAULT_INITIAL_CAPACITY) {
            capacity = DEFAULT_INITIAL_CAPACITY;
        } else {
            //capacity为arr.length 的1.5倍
            capacity = arr.length + (arr.length >> 1);
        }
        this.comp = comp;
        //重新复制数组
        items = Arrays.copyOf(arr, capacity);
        size = arr.length;
        heapify();
    }

    /**
     * 初始建堆
     */
    private void heapify() {
        //i是最后一个非叶子节点
        int n = size, i = (n >> 1) - 1;
        for (; i >= 0; i--) {
            siftDown(i);
        }
    }

    /**
     * 判断以i下标的左孩子是否存在
     */
    private boolean isLeftChildExists(int i) {
        return (i << 1) + 1 < size;
    }

    /**
     * 判断以i为下标的右孩子是否存在
     */
    private boolean isRightChildExists(int i) {
        return (i << 1) + 2 < size;
    }

    /**
     * 左孩子下标
     */
    private int leftChildIndex(int i) {
        return (i << 1) + 1;
    }

    /**右孩子下标*/
    private int rightChildIndex(int i) {
        return (i << 1) + 2;
    }

    /**交换下标i和j的元素*/
    private void swap(int i, int j) {
        E temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    /**
     * 采用比较器对下标i的元素进行下潜
     * 这里假定comp不为空，并且 0<=i<size
     */
    private void siftDownWithComparator(int i) {
        while (isLeftChildExists(i)) {
            int minChildIndex = leftChildIndex(i);
            if (isRightChildExists(i)) {
                int leftChildIndex = leftChildIndex(i);
                int rightChildIndex = rightChildIndex(i);
                minChildIndex = comp.compare(items[leftChildIndex], items[rightChildIndex]) < 0 ?
                        leftChildIndex : rightChildIndex;
            }
            //如果items[i]更大则需要下潜
            if (comp.compare(items[minChildIndex], items[i]) < 0) {
                swap(minChildIndex, i);
                i = minChildIndex;
            } else {
                break;
            }
        }
    }

    /**
     * 对下标i的元素进行下潜,假定0<=i<size.
     * 假定items中的元素是可比较的，如果不可比较，则会发生转换异常。
     */
    @SuppressWarnings("unchecked")
    private void siftDownComparable(int i) {
        //如果元素不可比较，此处会发生转换错误
        Comparable<E> e  = (Comparable<E>) items[i];

        while (isLeftChildExists(i)) {
            int minChildIndex = leftChildIndex(i);
            if (isRightChildExists(i)) {
                int leftChildIndex = leftChildIndex(i);
                int rightChildIndex = rightChildIndex(i);
                Comparable<E> leftChild  = (Comparable<E>) items[leftChildIndex];
                minChildIndex = leftChild.compareTo(items[rightChildIndex]) < 0 ?
                        leftChildIndex : rightChildIndex;
            }

            //如果e更大则需要下潜
            if (e.compareTo(items[minChildIndex])> 0) {
                swap(minChildIndex, i);
                i = minChildIndex;
            } else {
                break;
            }
        }
    }

    /**
     * 对下标为i的元素进行下潜
     */
    private void siftDown(int i) {
        if (i < 0 || i >= size) {
            throw new IllegalArgumentException("下潜失败" + i + "越界");
        }
        if(comp==null){
            siftDownComparable(i);
        }else {
            siftDownWithComparator(i);
        }
    }



    /**
     * 对下标为i的元素进行上浮
     */
    private void siftUp(int i) {
        if (i < 0 || i >= size) {
            throw new IllegalArgumentException("上浮失败" + i + "越界");
        }
        if(comp==null){
            siftUpComparable(i);
        }else {
            siftUpWithComparator(i);
        }
    }
    /**
     * 返回坐标i的父节点下标
     */
    private int parentIndex(int i){
        return (i-1)>>1;
    }
    /**
     * 采用比较器对下标i的元素进行上浮
     * 这里假定comp不为空，并且 0<=i<size
     */
    private void siftUpWithComparator(int i) {
        while (i>0){
            int parentIndex = parentIndex(i);
            //items[i] 更小，需要上浮
            if(comp.compare(items[i],items[parentIndex])<0){
                swap(i,parentIndex);
                i=parentIndex;
            }else {
                break;
            }
        }
    }

    /**
     * 对下标i的元素进行上移,假定0<=i<size.
     * 假定items中的元素是可比较的，如果不可比较，则会发生转换异常。
     */
    @SuppressWarnings("unchecked")
    private void siftUpComparable(int i) {
        Comparable<E> e =(Comparable<E>) items[i];
        while (i>0){
            int parentIndex = parentIndex(i);
            //e 更小，需要上浮
            if(e.compareTo(items[parentIndex])<0){
                swap(i,parentIndex);
                i=parentIndex;
            }else {
                break;
            }
        }
    }

    private boolean isFull(){
        return size==capacity;
    }

    private void resize(int newCapacity){
        items = Arrays.copyOf(items, newCapacity);
        capacity=newCapacity;
    }
    private void expand(){
        if(isFull()){
            int newCapacity = capacity + (capacity>>1);
            resize(newCapacity);
        }
    }
    public void  add(E e){
        expand();
        items[size++]=e;
        siftUp(size-1);
    }

    public E getTop(){
        if(size==0){
            throw new IllegalStateException("堆已空，无法取得堆顶元素");
        }
        return items[0];
    }

    public E removeTop(){
        if(size==0){
            throw new IllegalStateException("堆已空，无法取得堆顶元素");
        }
        E e = items[0];
        items[0]=items[size-1];
        items[size-1]=null;//帮助垃圾回收
        size--;
        siftDown(0);
        shrink();
        return e;
    }

    private void shrink() {
        //如果容量大于默认容量并且 利用率低于0.25时，缩容一半
        if(capacity>DEFAULT_INITIAL_CAPACITY && size<capacity*0.25){
            resize(capacity>>1);
        }
    }

    public int getSize(){
        return size;
    }



    /**
     * 从arr中提取m个最小元素。假定m小于等于arr的大小。
     * 注意这里的小是相对的，你想取得最小的m个元素，那么你的比较器comp应该是反着来的，因为我们要构建大顶堆。
     */
    public   static <E> List<E> getSmallest( E [] arr, int m,Comparator<E> comp){

        E[] newArr = Arrays.copyOf(arr, m);
        MyPriorityQueue<E> queue = new MyPriorityQueue<>(newArr, comp);
        for(int i=m;i<arr.length;i++){
            queue.add(arr[i]);
            queue.removeTop();
        }

        return Arrays.asList(Arrays.copyOf(queue.items,queue.size));
    }


}
