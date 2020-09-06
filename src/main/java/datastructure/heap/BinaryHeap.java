package datastructure.heap;

import datastructure.tree2.printer.BinaryTreeInfo;

import java.util.Comparator;

public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {
    private int size;
    private Object[] elements;
    private Comparator<E> comparator;
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap(Comparator<E> comparator) {
        super(comparator);
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    public BinaryHeap() {
        this(null);
    }


    @Override
    public void clear() {
        size = 0;
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
    }

    @Override
    public void add(E e) {
        elementNotNullCheck(e);
        ensuerCapacity(size + 1);
        elements[size++] = e;
        siftUp(size - 1);
    }

    @Override
    public E get() {
        emptyCheck();
        return (E) elements[0];
    }

    @Override
    public E remove() {
        emptyCheck();
        int lastIdx = --size;
        E root = (E) elements[0];
        elements[0] = elements[lastIdx];
        elements[lastIdx] = null;
        siftDown(0);
        return root;
    }

    /**
     * 删除堆顶元素并插入一个新元素
     * @param e
     * @return
     */
    @Override
    public E replace(E e) {
        elementNotNullCheck(e);
        E root = null;
        if (size == 0) {
            elements[0] = e;
            size++;
        } else {
            root = (E) elements[0];
            elements[0] = e;
            siftDown(0);
        }
        return root;

    }

    /**
     * 将给定索引的元素上滤
     * @param index
     */
//    private void siftUp(int index){
//        E e = (E) elements[index];
//        while (index > 0){
//            int pIndex = (index - 1) >> 1;
//            E p = (E) elements[pIndex];
//            if(compare(e,p) <= 0) return;
//            //比父节点大就交换
//            E tmp = (E) elements[index];
//            elements[index] = elements[pIndex];
//            elements[pIndex] = tmp;
//            //重新赋值index
//            index = pIndex;
//        }
//    }

    /**
     * 将给定索引的元素上滤(优化后)
     *
     * @param index
     */
    private void siftUp(int index) {
        E e = (E) elements[index];
        while (index > 0) {
            int pIndex = (index - 1) >> 1;
            E p = (E) elements[pIndex];
            if (compare(e, p) <= 0) break;
            //暂存父元素的值
            elements[index] = p;
            //重新赋值index
            index = pIndex;
        }
        elements[index] = e;
    }

    /**
     * 将给定索引的元素下滤
     *
     * @param index
     */
    private void siftDown(int index) {
        E e = (E) elements[index];
        //有子节点（第一个叶子节点的index = size / 2）
        while ((index << 1) + 1 < size) {
            int childIdx = (index << 1) + 1;
            E child = (E) elements[childIdx];
            int rightChildIdx = childIdx + 1;
            //选出子节点中最大的一个
            if (rightChildIdx < size && compare((E) elements[rightChildIdx], (E) elements[childIdx]) > 0) {
                child = (E) elements[rightChildIdx];
                childIdx = rightChildIdx;
            }
            if (compare(e, child) >= 0) break;
            elements[index] = child;
            index = childIdx;
        }
        elements[index] = e;
    }


    private void emptyCheck() {
        if (size == 0) {
            throw new RuntimeException("heap is empty");
        }
    }

    private void elementNotNullCheck(E e) {
        if (e == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    private void ensuerCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (capacity <= oldCapacity) {
            return;
        }
        //扩容1.5倍
        Object[] newElements = new Object[oldCapacity + (oldCapacity >> 1)];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
        System.out.println("容量由" + oldCapacity + "扩容为" + elements.length);
    }

    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        Integer idx = (Integer) node;
        return (idx << 1) + 1 >= size ? null : (idx << 1) + 1;
    }

    @Override
    public Object right(Object node) {
        Integer idx = (Integer) node;
        return (idx << 1) + 2 >= size ? null : (idx << 1) + 2;
    }

    @Override
    public Object string(Object node) {
        Integer idx = (Integer) node;
        return elements[idx];
    }
}
