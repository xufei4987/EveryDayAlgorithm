package datastructure.list;

public interface List<E> {

    int ELEMENT_NOT_FOUND = -1;

    int size();

    boolean isEmpty();

    boolean contains(E element);

    void add(E element);

    void add(int index, E element);

    E get(int index);

    E set(int index, E element);

    E remove(int index);

    int indexOf(E element);

    void clear();
}
