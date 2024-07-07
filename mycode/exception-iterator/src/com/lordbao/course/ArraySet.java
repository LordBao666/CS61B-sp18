package com.lordbao.course;

import java.util.Iterator;
import java.util.Objects;

/**
 * Assume the ArraySet is big enough to hold items , in this case
 * we do not consider the expanding issue.
 */
public class ArraySet<T> implements Iterable<T> {

    private final Object[] items;
    private int size;

    public ArraySet() {
        items = new Object[100];
        size = 0;
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     */
    public boolean contains(T x) {
        if (x == null) {
            return false;
        }
        for (Object obj : items) {
            if (Objects.equals(x, obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * Throws an IllegalArgumentException if the key is null.
     */
    public void add(T x) {
        if (x == null) {
            throw new IllegalArgumentException("can not add NULL!");
        }
        if (!contains(x)) { //every element in a set is unique
            items[size++] = x;
        }
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    public int size() {
        return size;
    }

    private class ArraySetIterator implements Iterator<T> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            return (T) items[index++];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ArraySet{").append("size=").append(size).append(", ").append("items={");
        for (int i = 0; i < size - 1; i++) {
            sb.append(items[i].getClass().getSimpleName()).append(":").append(items[i]).append(", ");
        }
        sb.append(items[size - 1].getClass().getSimpleName()).append(":").append(items[size - 1]).append("} }");
        return sb.toString();
    }

    /* EXTRA VIDEO CODE*/
//    @Override
//    public String toString() {
//        List<String> listOfItems = new ArrayList<>();
//        for (T x : this) {
//            listOfItems.add(x.getClass().getSimpleName() + ":" + x.toString());
//        }
//        return "ArraySet{size=" + size + ", " + "items={" + String.join(", ", listOfItems) + "}";
//    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        // Now obj is an instance of ArraySet, however, if obj is ArraySet<String> and
        // T is something else like Integer or even T is superType like Object, what should we do now?
        // in fact there are no generics in run time, according to the link
        // https://stackoverflow.com/questions/25003711/check-if-an-object-is-instance-of-list-of-given-class-name?rq=3
        // we need to take each one out and check it.
        ArraySet<T> otherArrSet = (ArraySet<T>) obj;
        if (otherArrSet.size != this.size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!otherArrSet.contains((T)items[i])) {
                return false;
            }
        }
        return true;
    }

    @SafeVarargs
    public static <E> ArraySet<E> of(E... args) {
        ArraySet<E> set = new ArraySet<>();
        for (E ele : args) {
            set.add(ele);
        }
        return set;
    }
}