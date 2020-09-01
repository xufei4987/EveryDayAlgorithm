package datastructure.set;

import datastructure.list.DoubleLinkedList;
import datastructure.list.List;

public class ListSet<E> implements Set<E> {
    private List<E> list = new DoubleLinkedList<>();
    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public void add(E e) {
        if (!list.contains(e)){
            list.add(e);
        }
    }

    @Override
    public void remove(E e) {
        int index = list.indexOf(e);
        if(index != List.ELEMENT_NOT_FOUND){
            list.remove(index);
        }
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        if(visitor == null) return;
        for (int i = 0; i < size(); i++) {
            if (visitor.visit(list.get(i))) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        Set<Integer> set = new ListSet<>();
        set.add(1);
        set.add(1);
        set.add(2);
        set.add(2);
        set.traversal(new Visitor<Integer>() {
            @Override
            boolean visit(Integer integer) {
                System.out.println(integer);
                return false;
            }
        });
    }
}
