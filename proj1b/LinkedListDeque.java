/**
 * @Author Lord_Bao
 * @Date 2024/5/19 17:23
 * @Version 1.0
 * <p>
 * I implement LinkedListDeque in the way of circular sentinel topology as the link
 * https://joshhug.gitbooks.io/hug61b/content/chap2/chap23.html#:~:text=An-,alternate%20approach,-is%20to%20implement
 * shows.
 * <p>
 * Some invariants must be  NOTED:
 * 1 If size = 0, sentinel node's next and sentinel node's prev must be sentinel itself.
 * 2 If size > 0,
 * 2.1 the sentinel node's next must be the first item ;
 * 2.2 the sentinel node's prev must be the last item ;
 * 2.3 the last item's next is sentinel ;
 * 2.4 the first item's prev is sentinel.
 */

public class LinkedListDeque<T> {

    private static class Node<E> {
        public Node<E> prev;
        public Node<E> next;
        public E data;

        public Node(Node<E> prev, Node<E> next, E data) {
            this.prev = prev;
            this.next = next;
            this.data = data;
        }
    }

    private final Node<T> sentinel;
    private int size;


    public LinkedListDeque() {
        sentinel = new Node<>(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(T data) {
        this();
        addFirst(data);
    }

    /**
     * return the number of items in the LinkedListDeque
     */
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public void addFirst(T data) {
        Node<T> node = new Node<>(sentinel, sentinel.next, data);
        sentinel.next.prev = node;
        sentinel.next = node;
        size++;
    }


    public void addLast(T data) {
        Node<T> node = new Node<>(sentinel.prev, sentinel, data);
        sentinel.prev.next = node;
        sentinel.prev = node;
        size++;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    public void printDeque() {
        if (isEmpty()) {
            return;
        }

        Node<T> p = sentinel.next;
        Node<T> last = sentinel.prev;
        while (p != last) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println(p.data + " ");
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node<T> p = sentinel.next;
        p.next.prev = sentinel;
        sentinel.next = p.next;
        size--;
        return p.data;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node<T> p = sentinel.prev;
        p.prev.next = sentinel;
        sentinel.prev = p.prev;
        size--;
        return p.data;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node<T> p;
        int count;
        if (index < (size >> 1)) {
            p = sentinel.next;
            count = 0;
            while (count != index) {
                p = p.next;
                count++;
            }
        } else {
            p = sentinel.prev;
            count = size - 1;
            while (count != index) {
                p = p.prev;
                count--;
            }
        }
        return p.data;
    }

    /**
     * get the item at the index by using recursion
     */
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }

    /**
     * assume node is not null and the first node,  0 <= index <size
     * return the item at the index.
     */
    private T getRecursive(Node<T> node, int index) {
        if (index == 0) {
            return node.data;
        } else {
            return getRecursive(node.next, index - 1);
        }
    }
}