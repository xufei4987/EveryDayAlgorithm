package datastructure.list;

import java.util.Arrays;

/**
 * 循环队列
 * @param <E>
 */
public class CircleQueue<E> {
    private static final int DEFAULT_CAPACITY = 8;
    private int size;
    private Object[] elements;
    private int front;//队头下标

    public CircleQueue() {
        this(DEFAULT_CAPACITY);
    }
    public CircleQueue(int capacity) {
        elements = new Object[capacity];
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void enQueue(E e){
        elements[(front + size) % elements.length] = e;
        size++;
    }

    public E deQueue(){
        E e = (E) elements[front];
        elements[front] = null;
        front = (front + 1) % elements.length;
        size--;
        return e;
    }

    public void clear(){
        for (int i = front; i < front + size; i++){
            elements[i % elements.length] = null;
        }
        front = 0;
        size = 0;
    }

    public static void main(String[] args) {
        CircleQueue<Integer> circleQueue = new CircleQueue<Integer>();
        for (int i = 1; i <= 8; i++) {
            circleQueue.enQueue(i);
        }
        System.out.println(circleQueue);
        for (int i = 1; i <= 4; i++) {
            circleQueue.deQueue();
        }
        System.out.println(circleQueue);
        for (int i = 9; i <= 10; i++) {
            circleQueue.enQueue(i);
        }
        System.out.println(circleQueue);
//        for (int i = 0; i < 5; i++) {
//            circleQueue.deQueue();
//        }
        circleQueue.clear();
        System.out.println(circleQueue);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size=").append(size).append(",front=").append(elements[front]).append(" {");
        for (int i = 0; i < elements.length; i++) {
            if(i == 0){
                sb.append(elements[i]);
            } else {
                sb.append(",").append(elements[i]);
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
