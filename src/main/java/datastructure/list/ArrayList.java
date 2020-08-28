package datastructure.list;

public class ArrayList<E> extends AbstractList<E> {

    private static final int DEFAULT_CAPACITY = 8;

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

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
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

    @Override
    public E get(int index) {
        rangeCheck(index);
        return (E) elements[index];
    }

    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        Object old = elements[index];
        elements[index] = element;
        return (E) old;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        E old = (E) elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        size--;
        elements[size] = null;
        trim();
        return old;
    }

    //缩容
    private void trim() {
        int oldCapacity = elements.length;
        if (size > (oldCapacity >> 1) || size < DEFAULT_CAPACITY) {
            return;
        }
        //缩容1.5倍
        Object[] newElements = new Object[oldCapacity - (oldCapacity >> 1)];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
        System.out.println("容量由" + oldCapacity + "缩容为" + elements.length);
    }

    @Override
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
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public void clear() {
        if(size > DEFAULT_CAPACITY){
            elements = new Object[DEFAULT_CAPACITY];
        } else {
            for (int i = 0; i < size; i++) {
                elements[i] = null;
            }
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
