package datastructure.map;

/**
 * 按照添加顺序将node连接起来
 * @param <K>
 * @param <V>
 */
public class LinkedHashMap<K,V> extends HashMap<K,V>{
    private LinkedNode<K,V> first;
    private LinkedNode<K,V> last;

    @Override
    protected Node<K, V> createNode(K k, V v, Node<K, V> parent) {
        LinkedNode<K, V> newNode = new LinkedNode<>(k, v, parent);
        if(first == null){
            first = last = newNode;
        } else {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
        return newNode;
    }

    @Override
    protected void afterRemove(Node<K, V> willRemoveNode, Node<K, V> removedNode) {
        LinkedNode<K,V> node1 = (LinkedNode<K,V>)willRemoveNode;
        LinkedNode<K,V> node2 = (LinkedNode<K,V>)removedNode;
        //删除的节点度为2 需要与被真正删除的节点交换在双向链表中的位置
        if(node1 != node2){
            LinkedNode<K,V> tmp = node1.prev;
            node1.prev = node2.prev;
            node2.prev = tmp;
            if (node1.prev == null){
                first = node1;
            } else {
                node1.prev.next = node1;
            }
            if (node2.prev == null){
                first = node2;
            } else {
                node2.prev.next = node2;
            }

            tmp = node1.next;
            node1.next = node2.next;
            node2.next = tmp;
            if (node1.next == null){
                last = node1;
            } else {
                node1.next.prev = node1;
            }
            if (node2.next == null){
                last = node2;
            } else {
                node2.next.prev = node2;
            }
        }
        //删除链表中的节点
        LinkedNode<K,V> prev = node2.prev;
        LinkedNode<K,V> next = node2.next;
        if(prev == null){
            first = next;
        } else {
            prev.next = next;
        }
        if(next == null){
            last = prev;
        } else {
            next.prev = prev;
        }
    }

    @Override
    public void clear() {
        super.clear();
        first = null;
        last = null;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        LinkedNode<K, V> node = this.first;
        while (node != null){
            if(visitor.visit(node.k,node.v)) return;
            node = node.next;
        }
    }

    private static class LinkedNode<K,V> extends Node<K,V>{
        LinkedNode<K,V> prev;
        LinkedNode<K,V> next;
        public LinkedNode(K k, V v, Node<K, V> parent) {
            super(k, v, parent);
        }
    }
}
