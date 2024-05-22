/**
 * @Author Lord_Bao
 * @Date 2024/5/22 17:41
 * @Version 1.0
 *
 * I implemented ArrayDeque by using circular array as
 * https://sp18.datastructur.es/materials/proj/proj1a/proj1a mentioned
 */
public class ArrayDeque<T> {
    private Object []items;
    private int size;
    private int nextFirst;//the position to add item in backward direction
    private int nextLast;//the position to add item in forward direction
    private int capacity;

    public ArrayDeque() {
        capacity=8;//The default capacity is 8
        size=0;
        //nextFirst does not have to be 3 and nextLast does not have to be 4
        //as long as nextFirst is just in front of nextLast.For instance,
        //nextFirst can be 2,and nextLast can be 3 as well
        nextFirst=3;
        nextLast=4;
        items=new Object[capacity];
    }

    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public boolean isFull(){
        return size==capacity;
    }

    /**though we can not initialize generic array, we can pass generic array
     * Here we add these data from the end.
     * */
    @SafeVarargs
    public final void  add(T... data){
            for(T ele:data){
                addLast(ele);
            }
    }

    public void addFirst(T data){
        if(isFull()){
            expand();
        }
        items[nextFirst]=data;
        nextFirst=(nextFirst-1+capacity)%capacity;
        size++;
    }

    public void addLast(T data){
        if(isFull()){
            expand();
        }
        items[nextLast]=data;
        nextLast=(nextLast+1)%capacity;
        size++;
    }


    private void resize(int newCapacity){
        Object [] newItems = new Object[newCapacity];
        int p = (nextFirst+1)%capacity;//p is used to scan the original items
        int q = p%newCapacity;//q is used to scan the new items
        for (int i=0;i<size;i++){//The number of items copied is size
            newItems[q]=items[p];
            items[p]=null;//avoid loitering
            p=(p+1)%capacity;//move p
            q=(q+1)%newCapacity;//move q
        }
//        nextFirst=nextFirst; //nextFirst doesn't change
        nextLast=q;
        capacity=newCapacity;
        items=newItems;
    }

    /**expand the items in the ratio of 1.5*/
    private void expand(){
        int newCapacity=capacity+(capacity>>1);
        resize(newCapacity);
    }

    /**Prints the items in the deque from first to last, separated by a space*/
    public void printDeque(){
        // p is the first element
        int p = (nextFirst+1)%capacity;
        int count = size;
        System.out.print("ArrayDeque{ ");
        while (count>0){
            System.out.print(items[p]+" ");
            count--;
            p=(p+1)%capacity;
        }
        System.out.print("}");
        System.out.println();
    }

    /** check if the items to be shrunk:
     *  if the ratio of usage is below .25 and capacity>=16, return true,
     *  otherwise,return false.
     * */
    private boolean isShrinkAble(){
        return (size * 4 < capacity) && ( capacity>=16);
    }

    /**halve the items*/
    private void shrink(){
        int newCapacity=(capacity>>1);
        resize(newCapacity);
    }

    /**Removes and returns the item at the front of the deque. If no such item exists, returns null.*/
    @SuppressWarnings("unchecked")
    public T removeFirst(){
        if(isEmpty()) return null;
        int first = (nextFirst+1)%capacity;
        T ele= (T)items[first];
        items[first]=null;//avoid loitering
        nextFirst=first;
        size--;
        if(isShrinkAble()){
            shrink();
        }
        return ele;
    }

    /**Removes and returns the item at the back of the deque. If no such item exists, returns null.*/
    @SuppressWarnings("unchecked")
    public T removeLast(){
        if(isEmpty()) return null;
        int last = (nextLast-1+capacity)%capacity;
        T ele= (T)items[last];
        items[last]=null;//avoid loitering
        nextLast=last;
        size--;
        if(isShrinkAble()){
            shrink();
        }
        return ele;
    }

    /**Gets the item at the given index, where 0 is the front,
     * 1 is the next item, and so forth. If no such item exists,
     * returns null. Must not alter the deque!*/
    @SuppressWarnings("unchecked")
    public T get(int index){
        if(index>=size || index<0) return null;
        return (T)(items[(nextFirst+1+index)%capacity]);
    }
}
