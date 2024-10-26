
package synthesizer;
import java.util.Iterator;

/**
 * Make sure to make this class and all of its methods public
 */

public class ArrayRingBuffer<T>  extends AbstractBoundedQueue<T>{
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    @SuppressWarnings("unchecked")
    public ArrayRingBuffer(int capacity) {
        this.capacity=capacity;
        this.fillCount=0;
        first=0;
        last=0;
        rb = (T[])new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if(isFull()){
            throw new RuntimeException("Ring buffer overflow");
        }

        fillCount++;
        rb[last]=x;
        last = (last+1)%capacity;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
       if(isEmpty()){
           throw new RuntimeException("Ring buffer underflow");
       }
       fillCount--;
       T res = rb[first];
       first=(first+1)%capacity;
       return res;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if(isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        return  rb[first];
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
}
