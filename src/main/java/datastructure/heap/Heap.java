package datastructure.heap;

public interface Heap<E> {
    int size();

    boolean isEmpty();

    void clear();

    void add(E e);

    E get();

    E remove();

    E replace(E e);
}
