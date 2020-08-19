package datastructure.list;

public class ArrayList<E> {

    private static final int DEFAULT_CAPACITY = 8;

    private int size;
    private Object[] elements;

    public ArrayList(int capacity) {
        if (capacity < DEFAULT_CAPACITY) {
            capacity = DEFAULT_CAPACITY;
        }
        elements = new Object[capacity];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    public void add(E element) {
        insert(size, element);
    }

    public void insert(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
        }
        ensuerCapacity(size + 1);
        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }
        size++;
        elements[index] = element;
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

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
        }
        return (E) elements[index];
    }

    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
        }
        Object old = elements[index];
        elements[index] = element;
        return (E) old;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
        }
        E old = (E) elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        size--;
        elements[size] = null;
        return old;
    }

    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size:").append(size).append(" [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(elements[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}
