package datastructure.list;

public class CircleLinkedList<E> extends AbstractList<E> {

    private Node<E> first;

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (index == 0) {
            Node<E> newFirst = new Node<>(element, first);
            Node<E> last = (size == 0) ? newFirst : node(size - 1);
            last.next = newFirst;
            first = newFirst;
        } else {
            Node<E> prev = node(index - 1);
            Node<E> newNode = new Node<>(element, prev.next);
            prev.next = newNode;
        }
        size++;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node<E> node = first;
        if (index == 0) {
            if(size == 0){
                first = null;
            } else {
                Node<E> last = node(size - 1);
                first = node.next;
                last.next = first;
            }
        } else {
            Node<E> prev = node(index - 1);
            node = prev.next;
            prev.next = node.next;
        }
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
    }

    //反转链表（迭代方式）
    public void reverse() {
        if (first == null || first.next == null) return;
        Node<E> newFirst = null;
        Node<E> tmp = null;
        while (first != null){
            tmp = first.next;
            first.next = newFirst;
            newFirst = first;
            first = tmp;
        }
        first = newFirst;
    }
    //反转链表（递归方式）
    public void reverse1(){
        Node<E> newFirst = reverse1(this.first);
        this.first = newFirst;
    }
    //判断链表是否有环（快慢指针）
    public boolean hasCircle(){
        if (first == null || first.next == null) return false;
        Node<E> slow = first;
        Node<E> fast = first.next;
        while (fast != null && fast.next != null){
            if(slow == fast) return true;
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    private Node<E> reverse1(Node<E> first) {
        if (first == null || first.next == null) return first;
        Node<E> newFirst = reverse1(first.next);
        first.next.next = first;
        first.next = null;
        return newFirst;
    }

    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size:").append(size).append(" {");
        Node<E> node = first;
        while (node != null) {
            sb.append(node.element.toString());
            if (node.next != null) {
                sb.append(" -> ");
            }
            node = node.next;
        }
        sb.append("}");
        return sb.toString();
    }

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }
}
