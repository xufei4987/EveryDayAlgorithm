package datastructure.list;

public class DoubleCircleLinkedList<E> extends AbstractList<E> {
    private Node<E> first;
    private Node<E> last;
    private Node<E> current;

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (index == size) {
            Node<E> prev = last;
            Node<E> newNode = new Node<>(element, prev, null);
            if (prev == null) {
                //添加第一个元素
                first = newNode;
            } else {
                prev.next = newNode;
            }
            last = newNode;
            last.next = first;
            first.prev = last;
        } else {
            Node<E> node = node(index);
            Node<E> prev = node.prev;
            Node<E> newNode = new Node<>(element, prev, node);
            if (prev == null) {
                first = newNode;
            } else {
                prev.next = newNode;
            }
            node.prev = newNode;
        }

        size++;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        E oldValue = node(index).element;
        node(index).element = element;
        return oldValue;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        return remove(node(index));
    }

    private E remove(Node<E> node) {
        if (size == 1) {
            first = null;
            last = null;
        } else {
            Node<E> prev = node.prev;
            Node<E> next = node.next;
            prev.next = next;
            next.prev = prev;
            if (node == first) {
                first = next;
            }
            if (node == last) {
                last = prev;
            }
        }
        size--;
        return node.element;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.element == null) {
                    return i;
                }
                node = node.next;
            }
        } else {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) {
                    return i;
                }
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
    }

    public void reset() {
        current = first;
    }

    public void next() {
        current = current.next;
    }

    //删除current指向的节点，并让current指向下一个节点
    public E remove() {
        if (current == null) return null;
        Node<E> next = current.next;
        E remove = remove(current);
        if(size == 0){
            current = null;
        } else {
            current = next;
        }
        return remove;
    }


    private Node<E> node(int index) {
        rangeCheck(index);
        if (index < (size >> 1)) {
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }

    }

    private static class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.prev.element).append("-").append(this.element).append("-").append(this.next.element);
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size:").append(size).append(" {");
        Node<E> node = first;
        if (first != null) {
            do {
                sb.append(node.toString());
                if (node.next != null) {
                    sb.append(" ");
                }
                node = node.next;
            } while (node != first);
        }
        sb.append("}");
        return sb.toString();
    }
}
