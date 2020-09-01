package datastructure.set;

import datastructure.tree2.bst.RBTree;

public class TreeSet<E> implements Set<E> {

    private RBTree<E> tree = new RBTree();

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public void clear() {
        tree.clear();
    }

    @Override
    public boolean contains(E e) {
        return tree.contains(e);
    }

    @Override
    public void add(E e) {
        tree.add(e);
    }

    @Override
    public void remove(E e) {
        tree.remove(e);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        tree.inorder();
    }

    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<>();
        set.add(1);
        set.add(1);
        set.add(2);
        set.add(2);
        set.add(4);
        set.add(4);
        set.traversal(null);
    }
}
