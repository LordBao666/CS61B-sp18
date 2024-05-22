/**
 * @Author Lord_Bao
 * @Date 2024/5/19 17:23
 * @Version 1.0
 * <p>
 * I implement LinkedListDeque in the way of circular sentinel topology as the link
 * https://joshhug.gitbooks.io/hug61b/content/chap2/chap23.html#:~:text=An-,alternate%20approach,-is%20to%20implement
 * shows.
 * <p>
 * However,I change it a little, I state 2 more invariants:
 * 1 the sentinel node's next must be the first item.
 * 2 The sentinel node's prev must be the last item.
 * The invariants  just make the code more understandable.
 * <p>
 * Some invariants must be  NOTED:
 * 1 the first item's(if exists) prev must be the last item.
 * 2 the last item's(if exists) next must be the first item.
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
        size = 0;
    }

    public LinkedListDeque(T data) {
        Node<T> node = new Node<>(null, null, data);
        node.prev = node;
        node.next = node;
        sentinel = new Node<>(node, node, null);
        size = 1;
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
        if (isEmpty()) {
            Node<T> node = new Node<>(null, null, data);
            node.next = node;
            node.prev = node;
            sentinel.prev = node;
            sentinel.next = node;
            size = 1;
        } else {
            //node's prev is the last item,and its next is the first item
            Node<T> node = new Node<>(sentinel.prev, sentinel.next, data);
            //the first item's prev is node now
            sentinel.next.prev = node;
            //the last item's next is node now
            sentinel.prev.next = node;
            //NOW ,the first item is node
            sentinel.next = node;
            size++;
        }
    }

    //The logic is similar to addFirst
    public void addLast(T data) {
        if (isEmpty()) {
            addFirst(data);
        } else {
            //The code of the first three lines is same as the one of addFirst,
            // draw a picture ,and you will know.
            Node<T> node = new Node<>(sentinel.prev, sentinel.next, data);
            sentinel.next.prev = node;
            sentinel.prev.next = node;
            sentinel.prev = node;
            size++;
        }
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
        p.next.prev = sentinel.prev;
        sentinel.prev.next = p.next;
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
        p.prev.next = sentinel.next;
        sentinel.next.prev = p.prev;
        sentinel.prev = p.prev;
        size--;
        return p.data;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque
     */
    public T get(int index) {
        if (isEmpty()) {
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
}
