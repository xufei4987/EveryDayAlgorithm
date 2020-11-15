package datastructure.map;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class TreeMap<K, V> implements Map<K, V> {
    private Comparator<K> comparator;//自定义比较器
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private Node<K, V> root;
    private int size;

    public TreeMap(Comparator comparator) {
        this.comparator = comparator;
    }

    public TreeMap() {
        this(null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        keyNotNullCheck(key);
        if (root == null) {
            root = createNode(key, value, null);
            size++;
            afterPut(root);
            return null;
        }
        Node node = root;
        Node parent = root;
        int cmp = 0;
        while (node != null) {
            cmp = compare(key, (K) node.k);
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                node.k = key;
                V oldValue = (V) node.v;
                node.v = value;
                return oldValue;
            }
        }
        Node newNode = createNode(key, value, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
        afterPut(newNode);
        return null;
    }

    @Override
    public V get(K key) {
        return node(key) == null ? null : node(key).v;
    }

    @Override
    public V remove(K key) {
        Node<K,V> node = node(key);
        if (node == null) {
            return null;
        }
        V removedNodeValue = node.v;
        Node<K,V> p = node;
        if (node.hasTwoChildren()) {
            p = predecessor(p);
            node.k = p.k;
            node.v = p.v;
            //需要将前驱节点删除，由于前驱节点的度必为0或者1，所以走下面的删除流程就可以了
        }
        Node<K,V> replacement = p.left != null ? p.left : p.right;
        if (p.isLeaf()) {
            if (p.parent == null) {
                root = null;
                afterRemove(p, null);
            } else {
                if (p.parent.left == p) {
                    p.parent.left = null;
                } else {
                    p.parent.right = null;
                }
                afterRemove(p, null);
            }
        } else {
            //待删除节点度为1
            if (p.parent == null) {
                root = p.left != null ? p.left : p.right;
                root.parent = null;
            } else if (p.parent.left == p) {
                p.parent.left = p.left != null ? p.left : p.right;
                p.parent.left.parent = p.parent;
            } else {
                p.parent.right = p.left != null ? p.left : p.right;
                p.parent.right.parent = p.parent;
            }
            afterRemove(p, replacement);
        }
        size--;
        return removedNodeValue;
    }

    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        if(root == null) return false;
        //遍历找出value，这里使用层序遍历（用前、中、后序也可以）
        Queue<Node> queue = new LinkedList<>();
        Node<K,V> node = root;
        queue.offer(node);
        while (!queue.isEmpty()){
            Node n = queue.poll();
            if(Objects.equals(n.v,value)){
                return true;
            }
            if(n.left != null){
                queue.offer(n.left);
            }
            if(n.right != null){
                queue.offer(n.right);
            }
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (visitor == null) return;
        traversal(root,visitor);
    }

    //中序遍历
    private void traversal(Node<K,V> node, Visitor<K, V> visitor){
        if(node == null || visitor.stop) return;
        traversal(node.left,visitor);
        if(visitor.stop) return;
        visitor.visit(node.k,node.v);
        traversal(node.right,visitor);
    }

    /**
     * 查找某个节点的前驱节点
     * 前驱节点的定义：中序遍历，某个节点的前一个节点
     *
     * @param node
     * @return
     */
    private Node<K,V> predecessor(Node<K,V> node) {
        if (node == null) {
            return null;
        }
        //如果有左子树，找左子树中最大的那个节点
        if (node.left != null) {
            Node<K,V> p = node.left;
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }
        while (node.parent != null && node.parent.left == node) {
            node = node.parent;
        }
        //node父节点为空，返回null
        //node的父节点不为空，当前节点是父节点的右子节点，返回父节点
        return node.parent;
    }

    private Node<K, V> createNode(K k, V v, Node<K, V> parent) {
        return new Node<>(k, v, parent);
    }

    private Node<K,V> node(K key){
        if (root == null) {
            return null;
        }
        Node<K,V> node = root;
        while (node != null) {
            int compare = compare(key, node.k);
            if (compare == 0) {
                return node;
            } else if (compare > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    private void afterPut(Node<K, V> node) {
        Node<K, V> parent = node.parent;
        //添加的是root节点
        if (parent == null) {
            black(node);
            return;
        }
        //1、父节点是黑色的四种情况,不需要进行额外的处理
        if (isBlack(parent)) {
            return;
        }

        Node<K, V> uncle = parent.sibling();
        Node<K, V> grand = parent.parent;
        if (isRed(uncle)) { //2、uncle节点是红色的4种情况
            black(parent);
            black(uncle);
            afterPut(red(grand));
            return;
        }
        //3、uncle节点是红色的4种情况
        if (parent.isLeftChild()) { //L
            if (node.isLeftChild()) { //LL
                black(parent);
                red(grand);
                rotateRight(grand);
            } else { //LR
                black(node);
                red(grand);
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else { //R
            if (node.isLeftChild()) {//RL
                black(node);
                red(grand);
                rotateRight(parent);
                rotateLeft(grand);
            } else {//RR
                black(parent);
                red(grand);
                rotateLeft(grand);
            }
        }
    }

    private void afterRemove(Node<K, V> node, Node<K, V> replacement) {
        //如果删除的为红色叶子节点不用处理
        if (isRed(node)) return;
        //删除的是带有一个红色子节点的黑色节点
        if (isRed(replacement)) {
            black(replacement);
            return;
        }
        Node<K, V> parent = node.parent;
        //删除的是根节点
        if (parent == null) return;
        /**
         * 删除的是黑色叶子节点
         * 1 兄弟节点是黑色
         *  1.1兄弟节点有至少1个红色子节点 通过旋转借给删除的节点
         *  1.2兄弟节点没有红色子节点，父节点下溢（需要递归处理）
         * 2 兄弟节点是红色
         *  2.1通过旋转，将兄弟节点的儿子变为兄弟，并重复兄弟节点是黑色的处理逻辑
         */
        boolean left = parent.left == null || node.isLeftChild(); //需要兼容父节点向下合并的情况
        Node<K, V> sibling = left ? parent.right : parent.left;
        if (left) { //被删除的节点在左边，兄弟节点在右边
            if (isRed(sibling)) { //将兄弟节点是红色的情况转化为兄弟节点是黑色的情况
                black(sibling);
                red(parent);
                rotateLeft(parent);
                //变更兄弟
                sibling = parent.right;
            }
            if (isBlack(sibling.left) && isBlack(sibling.right)) { //兄弟节点没有红色子节点
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) { //如果父节点为黑色，需要父节点向下合并，再将父节点重复处理
                    afterRemove(parent, null);
                }
            } else { //兄弟节点至少有一个红色子节点
                if (isBlack(sibling.right)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }
                color(sibling, colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }
        } else { //被删除的节点在右边，兄弟节点在左边
            if (isRed(sibling)) { //将兄弟节点是红色的情况转化为兄弟节点是黑色的情况
                black(sibling);
                red(parent);
                rotateRight(parent);
                //变更兄弟
                sibling = parent.left;
            }
            if (isBlack(sibling.left) && isBlack(sibling.right)) { //兄弟节点没有红色子节点
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) { //如果父节点为黑色，需要父节点向下合并，再将父节点重复处理
                    afterRemove(parent, null);
                }
            } else { //兄弟节点至少有一个红色子节点
                if (isBlack(sibling.left)) {
                    rotateLeft(sibling);
                    sibling = parent.left;
                }
                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }
    }

    //左旋
    private void rotateLeft(Node grand) {
        Node parent = grand.right;
        Node leftChild = parent.left;
        grand.right = leftChild;
        parent.left = grand;
        afterRotate(grand, parent, leftChild);

    }

    //右旋
    private void rotateRight(Node grand) {
        Node parent = grand.left;
        Node rightChild = parent.right;
        grand.left = rightChild;
        parent.right = grand;
        afterRotate(grand, parent, rightChild);
    }

    private void afterRotate(Node grand, Node parent, Node child) {
        //更新parent的parent属性
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {//grand是root节点
            root = parent;
        }
        //更新grand、leftChild的parent属性
        if (child != null) {
            child.parent = grand;
        }
        grand.parent = parent;
    }

    private Node<K, V> color(Node<K, V> node, boolean color) {
        if (node == null) {
            return node;
        }
        node.color = color;
        return node;
    }

    private void keyNotNullCheck(K k) {
        if (k == null) {
            throw new IllegalArgumentException("element must not null");
        }
    }

    private int compare(K k1, K k2) {
        if (comparator != null) {
            return comparator.compare(k1, k2);
        }
        return ((Comparable) k1).compareTo(k2);
    }

    private Node<K, V> red(Node<K, V> node) {
        return color(node, RED);
    }

    private Node<K, V> black(Node<K, V> node) {
        return color(node, BLACK);
    }

    private boolean colorOf(Node<K, V> node) {
        return node == null ? BLACK : node.color;
    }

    private boolean isRed(Node<K, V> node) {
        return colorOf(node) == RED;
    }

    private boolean isBlack(Node<K, V> node) {
        return colorOf(node) == BLACK;
    }

    private static class Node<K, V> {
        K k;
        V v;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;
        //默认为红色，可以更快的满足红黑树的性质
        boolean color = RED;

        public Node(K k, V v, Node<K, V> parent) {
            this.k = k;
            this.v = v;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        //兄弟节点
        public Node<K, V> sibling() {
            if (isRightChild()) {
                return this.parent.left;
            }
            if (isLeftChild()) {
                return this.parent.right;
            }
            return null;
        }

        @Override
        public String toString() {
            String c = this.color == BLACK ? "b" : "r";
            return k.toString() + "-" + v.toString() + "(" + c + ")";
        }
    }
}
