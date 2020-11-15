package datastructure.trie;

import java.util.HashMap;

/**
 * 字典树 ： 前缀搜索
 *
 * @param <V>
 */
public class Trie<V> {
    private int size;
    private Node<V> root;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        root = null;
    }

    public V get(String key) {
        Node<V> node = node(key);
        return node != null && node.word ? node.value : null;
    }

    public V add(String key, V value) {
        keyCheck(key);
        if (root == null) {
            root = new Node<>(null);
        }
        Node<V> node = this.root;
        for (int i = 0; i < key.length(); i++) {
            if (node.children == null) {
                node.children = new HashMap<>();
            }
            Node<V> childNode = node.children.get(key.charAt(i));
            if (childNode == null) {
                childNode = new Node<>(node);
                childNode.c = key.charAt(i);
                node.children.put(key.charAt(i), childNode);
            }
            node = childNode;
        }
        if (!node.word) {
            node.value = value;
            node.word = true;
            size++;
            return null;
        }
        V oldValue = node.value;
        node.value = value;
        return oldValue;
    }

    public V remove(String key) {
        //找到最后一个节点
        Node<V> node = node(key);
        //没有找到或者找到了但不是一个单词
        if(node == null || !node.word) return null;
        size --;
        V oldValue = node.value;
        if(node.children != null && !node.children.isEmpty()) {
            node.word = false;
            node.value = null;
            return oldValue;
        }
        Node<V> parent = null;
        while ((parent = node.parent) != null){
            parent.children.remove(node.c);
            if(parent.word || !parent.children.isEmpty()) break;
            node = parent;
        }
        return oldValue;
    }

    public boolean contains(String key) {
        Node<V> node = node(key);
        return node != null && node.word;
    }

    public boolean startWith(String prefix) {
        return node(prefix) != null;
    }

    private Node<V> node(String key) {
        keyCheck(key);
        Node<V> node = this.root;
        for (int i = 0; i < key.length(); i++) {
            if (node.children == null || node.children.isEmpty()) return null;
            node = node.children.get(key.charAt(i));
            if (node == null) return null;
        }
        return node;
    }

    private void keyCheck(String key) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("illegal key");
        }
    }

    private static class Node<V> {
        Node<V> parent;
        private HashMap<Character, Node<V>> children;
        Character c;
        V value;
        boolean word; //该节点是否是单词的结尾

        public Node(Node<V> parent) {
            this.parent = parent;
        }
    }

    public static void main(String[] args) {
        Trie<Integer> trie = new Trie<>();
        trie.add("aa", 1);
        trie.add("bcdac", 2);
        trie.add("bcd", 20);
        trie.add("133ddd", 3);
        trie.add("133dabc", 30);
        trie.add("游戏", 4);
        trie.add("helloword", 5);
        System.out.println(trie.size());
        System.out.println(trie.get("aa"));
        System.out.println(trie.get("游戏"));
        System.out.println(trie.startWith("133"));
        System.out.println(trie.startWith("132"));
        System.out.println(trie.remove("133ddd"));
        System.out.println(trie.remove("bcdac"));
        System.out.println(trie.get("133dabc"));
        System.out.println(trie.get("bcd"));
    }
}
