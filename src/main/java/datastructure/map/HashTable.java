package datastructure.map;

import java.util.Objects;

/**
 * 自定义实现哈希表
 * @param <K>
 * @param <V>
 */
public class HashTable<K, V> {
    private Node[] table;
    private int size;

    public HashTable(int size) {
        this.size = size;
        this.table = new Node[size];
    }

    public void put(K key, V val) {
        int idx = hash(key);
        Node node = table[idx];
        if (node == null) {
            Node<K, V> head = new Node<>(key,val,null);
            table[idx] = head;
        } else {
            while (node.next != null){
                node = node.next;
            }
            node.next = new Node<>(key,val,null);
        }
    }

    public V get(K key){
        int idx = hash(key);
        Node node = table[idx];
        if (node == null){
            return null;
        }else{
            while (node != null){
                if (Objects.equals(key,node.getKey())) {
                    return (V) node.getValue();
                }
                node = node.next;
            }
            return null;
        }
    }

    private int hash(K key){
        return key.hashCode() % size;
    }

    public void listTable() {
        Node node = null;
        for (int i = 0; i < size; i++) {
            if(table[i] == null){
                System.out.printf("第%d链表为空\n", i+1);
            } else {
                node = table[i];
                System.out.printf("第%d链表:", i+1);
                while (node != null){
                    System.out.print(node.toString() + "\t");
                    node = node.next;
                }
                System.out.println();
            }
        }
    }

    /**
     * 删除hashtable中的元素：
     * 2、如果在头节点上，则删除头结点
     * 3、如果不在头结点，则找到该节点的上一个节点，删除该节点（单链表无法自我删除）
     * @param key
     * @return
     */
    public V delete(K key){
        int idx = hash(key);
        Node node = table[idx];
        if (node == null) {
            return null;
        } else {
            if(Objects.equals(key,node.getKey())){
                table[idx] = node.next;
                return (V) node.getValue();
            }
            while (true){
                if(node.next == null){
                    break;
                }
                if (Objects.equals(key,node.next.getKey())) {
                    Object value = node.next.getValue();
                    node.next = node.next.next;
                    return (V) value;
                }
            }
            return null;
        }
    }

    private static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        public final String toString() {
            return key + "=" + value;
        }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Node) {
                Node e = (Node) o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }
}
