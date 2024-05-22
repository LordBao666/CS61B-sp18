import org.junit.Test;

/**
 * @Author Lord_Bao
 * @Date 2024/5/22 19:24
 * @Version 1.0
 */

public class ArrayDequeTest {
    @Test
    public void testAddFirstAndAddLast() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(3);
        deque.addLast(4);
        deque.printDeque();
        System.out.println(deque.size());
    }

    @Test
    public void testAdd() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.add(1, 3, 5, 7, 9);
        deque.printDeque();
    }

    //you had better run the code in debug mode,and the Java
    //Visualizer can tell you everything!!!
    @Test
    public void testExpand() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.add(1, 2, 3, 4, 5, 6, 7, 8);
        deque.printDeque();
        System.out.println(deque.size());
        deque.addLast(9);
        deque.printDeque();
        System.out.println(deque.size());
    }

    @Test
    public void testRemove() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.add(1, 2, 3, 4, 5, 6, 7, 8, 9);
        deque.printDeque();
        System.out.println(deque.size());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        deque.printDeque();
        System.out.println(deque.size());
    }

    //you had better run the code in debug mode and find out
    //when the array is shrunk.
    @Test
    public void testShrink() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.add(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        for (int i = 0; i < 17; i++) {
            deque.removeLast();
        }
        System.out.println(deque.size());
    }

    @Test
    public void testGet() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.add(1, 2, 3, 4);
        System.out.println(deque.get(-1));
        System.out.println(deque.get(0));
        System.out.println(deque.get(1));
        System.out.println(deque.get(2));
        System.out.println(deque.get(3));
        System.out.println(deque.get(4));
    }
}
