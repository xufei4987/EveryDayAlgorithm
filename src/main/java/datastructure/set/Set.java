package datastructure.set;

public interface Set<E> {

    int size();

    boolean isEmpty();

    void clear();

    boolean contains(E e);

    void add(E e);

    void remove(E e);

    void traversal(Visitor<E> visitor);

    public static abstract class Visitor<E> {
        boolean stop;
        abstract boolean visit(E e);
    }
}
