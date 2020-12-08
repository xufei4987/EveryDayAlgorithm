package datastructure.union;

import datastructure.map.HashMap;
import datastructure.map.Map;

import java.util.Objects;

/**
 * 通用的并查集
 * @param <V>
 */
public class GenericUnionFind<V> {
    /**
     * 所属集合的父节点
     */
    private Map<V, Node<V>> nodes = new HashMap<>();

    public void makeSet(V v) {
        nodes.put(v, new Node<>(v));
    }

    public V find(V v) {
        Node<V> node = findNode(v);
        return node == null ? null : node.value;
    }

    private Node<V> findNode(V v) {
        Node<V> node = nodes.get(v);
        if(node == null) return null;
        while (!Objects.equals(node.value,node.parent.value)){
            Node p = node.parent;
            node.parent = node.parent.parent;
            node = p;
        }
        return node;
    }

    public void union(V v1, V v2) {
        Node<V> root1 = findNode(v1);
        Node<V> root2 = findNode(v2);
        if(root1 == null || root2 == null) return;
        if (Objects.equals(root1,root2)) return;
        if (root1.rank > root2.rank) {
            root2.parent = root1;
        } else if(root1.rank < root2.rank){
            root1.parent = root2;
        } else {
            root2.parent = root1;
            root1.rank += 1;
        }
    }

    public boolean isSame(V v1, V v2) {
        return find(v1).equals(find(v2));
    }


    private static class Node<V> {
        V value;
        Node<V> parent = this;
        int rank = 1; //高度

        public Node(V value) {
            this.value = value;
        }
    }
}
