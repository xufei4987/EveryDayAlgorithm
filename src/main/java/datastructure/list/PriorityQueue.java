package datastructure.list;

import datastructure.heap.BinaryHeap;
import datastructure.heap.Heap;

import java.util.Comparator;

public class PriorityQueue<E> {
    private Heap<E> heap;

    public PriorityQueue(Comparator<E> comparator) {
        this.heap = new BinaryHeap<E>(comparator);
    }
    public PriorityQueue() {
        this.heap = new BinaryHeap<E>();
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void enQueue(E e) {
        heap.add(e);
    }

    public E deQueue() {
        return heap.remove();
    }

    public E front() {
        return heap.get();
    }

    public void clear(){
        heap.clear();
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.enQueue(5);
        priorityQueue.enQueue(3);
        priorityQueue.enQueue(7);
        priorityQueue.enQueue(9);
        priorityQueue.enQueue(11);
        while (!priorityQueue.isEmpty()){
            System.out.println(priorityQueue.deQueue());
        }
    }
}
