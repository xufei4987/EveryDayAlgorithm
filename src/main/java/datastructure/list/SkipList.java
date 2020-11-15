package datastructure.list;

import java.util.Comparator;

/**
 * 跳表（对有序链表的优化）
 * redis中的sortset就是跳表的实现
 */
public class SkipList<K, V> {
    private static final int MAX_LEVEL = 32; //redis中Nexts数组最多为32
    private static final double P = 0.25; //
    private int size;
    private Comparator<K> comparator;
    private Node<K, V> first;
    private int level;//有效层数

    public SkipList() {
        first = new Node<>(null, null, MAX_LEVEL);
    }

    public SkipList(Comparator<K> comparator) {
        this.comparator = comparator;
        first = new Node<>(null, null, MAX_LEVEL);
    }

    private int compare(K k1, K k2) {
        return comparator != null ? comparator.compare(k1, k2) : ((Comparable) k1).compareTo(k2);
    }

    private void keyCheck(K key) {
        if (key == null) {
            throw new IllegalArgumentException("非法的参数！");
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public V get(K key) {
        keyCheck(key);
        Node<K, V> node = first;
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            while (node.nexts[i] != null && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            if (cmp == 0) return node.nexts[i].value;
        }
        return null;
    }

    public V put(K key, V value) {
        keyCheck(key);
        Node<K, V> node = first;
        Node<K, V>[] preNodes = new Node[level];
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            while (node.nexts[i] != null && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            if (cmp == 0) {
                V oldValue = node.nexts[i].value;
                node.nexts[i].value = value;
                return oldValue;
            }
            //保存前驱节点，方便添加节点
            preNodes[i] = node;
        }
        //redis新节点level的生成方法
        int newLevel = randomLevel();
        Node<K, V> newNode = new Node<>(key, value, newLevel);
        for (int i = 0; i < newLevel; i++) {
            if (i >= level) {
                //新节点的level超过当前最大的level，则让头节点的next指向新节点
                first.nexts[i] = newNode;
            } else {
                newNode.nexts[i] = preNodes[i].nexts[i];
                preNodes[i].nexts[i] = newNode;
            }
        }
        //更新size;
        size++;
        //更新level
        level = Math.max(level, newLevel);
        return null;
    }

    public V remove(K key) {
        keyCheck(key);
        Node<K, V> node = first;
        Node<K, V>[] preNodes = new Node[level];
        boolean find = false;
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            while (node.nexts[i] != null && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            if (cmp == 0) {
                find = true;
            }
            //保存前驱节点，方便删除节点
            preNodes[i] = node;
        }
        //没有找到该节点，直接返回
        if (!find) return null;
        //获取需要删除的节点
        Node<K, V> removedNode = node.nexts[0];
        //删除该节点
        for (int i = 0; i < removedNode.nexts.length; i++) {
            preNodes[i].nexts[i] = removedNode.nexts[i];
        }
        //更新size
        size--;
        //更新level,如果需要删除的节点的level就是跳表的最大level，则需要更新level
        int newLevel = level;
        while (--newLevel >= 0 && first.nexts[newLevel] == null) {
            level = newLevel;
        }
        return removedNode.value;
    }

    private int randomLevel() {
        int level = 1;
        while (Math.random() < P && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<K, V>[] nexts = first.nexts;
        for (int i = level - 1; i >= 0; i--) {
            Node<K, V> node = nexts[i];
            while (node != null) {
                sb.append(node.toString());
                node = node.nexts[i];
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V>[] nexts;

        public Node(K key, V value, int level) {
            this.key = key;
            this.value = value;
            this.nexts = new Node[level];
        }

        @Override
        public String toString() {
            return " (" + key + "," + value + ") ";
        }
    }

    public static void main(String[] args) {
        SkipList<Integer, Integer> skipList = new SkipList<>();
        for (int i = 0; i < 100; i++) {
            skipList.put(i, i + 10);
        }
        System.out.println(skipList);
        for (int i = 0; i < 100; i++) {
            test(skipList.get(i) == i + 10);
        }
        test(skipList.size() == 100);
        for (int i = 0; i < 100; i++) {
            test(skipList.remove(i) == i + 10);
        }
        test(skipList.size() == 0);
    }

    private static void test(boolean b) {
        if (!b) {
            throw new RuntimeException("测试不通过");
        }
    }
}
