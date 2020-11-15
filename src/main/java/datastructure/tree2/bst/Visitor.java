package datastructure.tree2.bst;

public abstract class Visitor<E> {
    boolean stop = false;
    public abstract boolean visit(E e);
}
