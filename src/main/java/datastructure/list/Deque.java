package datastructure.list;

public class Deque<E> {
    List<E> list = new DoubleLinkedList();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void enQueueFront(E e) {
        list.add(e);
    }

    public E deQueueFront() {
        return list.remove(size()-1);
    }

    public void enQueueRear(E e) {
        list.add(0,e);
    }

    public E deQueueRear() {
        return list.remove(0);
    }

    public E front() {
        return list.get(0);
    }

    public E rear() {
        return list.get(size() - 1);
    }

    public void clear(){
        list.clear();
    }
}
