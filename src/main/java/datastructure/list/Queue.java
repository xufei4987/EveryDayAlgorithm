package datastructure.list;

public class Queue<E> {
    List<E> list = new DoubleLinkedList();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void enQueue(E e) {
        list.add(e);
    }

    public E deQueue() {
        return list.remove(0);
    }

    public E front() {
        return list.get(0);
    }

    public void clear(){
        list.clear();
    }
}
