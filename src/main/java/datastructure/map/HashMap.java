package datastructure.map;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class HashMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_CAPACITY = 1 << 4;
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private int size;
    private Node<K, V>[] table;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    public HashMap(int capacity) {
        this.table = new Node[capacity];
    }

    public HashMap() {
        this(DEFAULT_CAPACITY);
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
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        resize();
        int index = index(key);
        Node<K, V> root = table[index];
        if (root == null) {
            root = createNode(key, value, null);
            table[index] = root;
            size++;
            fixAfterPut(root);
            return null;
        }

        Node<K, V> node = root;
        Node<K, V> parent = root;
        K k1 = key;
        int h1 = k1 == null ? 0 : k1.hashCode();
        int cmp = 0;
        Node<K, V> result = null;
        boolean searched = false;
        while (node != null) {
            parent = node;
            K k2 = node.k;
            int h2 = node.hash;
            if (h1 > h2) {
                cmp = 1;
            } else if (h1 < h2) {
                cmp = -1;
            } else if (Objects.equals(k1, k2)) {
                cmp = 0;
            } else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0) {
                //do nothing
            } else if (searched) {
                cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
            } else {
                if ((node.left != null && (result = node(node.left, k1)) != null)
                        || (node.right != null && (result = node(node.right, k1)) != null)) {
                    node = result;
                    cmp = 0;
                } else {
                    //找不到这个key，通过内存地址做比较，决定放在左边还是右边
                    cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
                    searched = true;
                }
            }

            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                node.k = k1;
                V oldValue = (V) node.v;
                node.v = value;
                return oldValue;
            }
        }
        Node newNode = createNode(k1, value, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
        fixAfterPut(newNode);
        return null;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = node(key);
        return node == null ? null : node.v;
    }

    @Override
    public V remove(K key) {
        return remove(node(key));
    }

    protected V remove(Node<K, V> node) {
        if (node == null) {
            return null;
        }
        Node<K, V> willRemoveNode = node;
        V removedNodeValue = node.v;
        Node<K, V> p = node;
        if (node.hasTwoChildren()) {
            p = predecessor(p);
            node.k = p.k;
            node.v = p.v;
            node.hash = p.hash;
            //需要将前驱节点删除，由于前驱节点的度必为0或者1，所以走下面的删除流程就可以了
        }
        int index = index(node);
        Node<K, V> replacement = p.left != null ? p.left : p.right;
        if (p.isLeaf()) {
            if (p.parent == null) {
                table[index] = null;
                fixAfterRemove(p, null);
            } else {
                if (p.parent.left == p) {
                    p.parent.left = null;
                } else {
                    p.parent.right = null;
                }
                fixAfterRemove(p, null);
            }
        } else {
            //待删除节点度为1
            if (p.parent == null) {
                table[index] = p.left != null ? p.left : p.right;
                table[index].parent = null;
            } else if (p.parent.left == p) {
                p.parent.left = p.left != null ? p.left : p.right;
                p.parent.left.parent = p.parent;
            } else {
                p.parent.right = p.left != null ? p.left : p.right;
                p.parent.right.parent = p.parent;
            }
            fixAfterRemove(p, replacement);
        }
        size--;
        afterRemove(willRemoveNode,p);
        return removedNodeValue;
    }

    protected void afterRemove(Node<K,V> willRemoveNode, Node<K,V> removedNode) {
    }

    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;
            queue.offer(table[i]);
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                if (Objects.equals(node.v, value)) return true;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;
            queue.offer(table[i]);
            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();
                if (visitor.visit(node.k, node.v)) {
                    return;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
    }

    /**
     * 扩容
     */
    private void resize(){
        //装填因子：节点个数/桶的大小
        if(size / table.length <= DEFAULT_LOAD_FACTOR) return;
        Node<K,V>[] oldTable = table;
        table = new Node[table.length << 1];
        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] == null) continue;
            queue.offer(oldTable[i]);
            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                moveNode(node);
            }
        }
    }

    private void moveNode(Node<K, V> newNode) {
        //重置node
        resetNode(newNode);
        //移动到扩容后的table上
        int index = index(newNode.k);
        if (table[index] == null) {
            table[index] = newNode;
            fixAfterPut(newNode);
            return;
        }

        Node<K, V> node = table[index];
        Node<K, V> parent = table[index];
        K k1 = newNode.k;
        int h1 = k1 == null ? 0 : k1.hashCode();
        int cmp = 0;
        while (node != null) {
            parent = node;
            K k2 = node.k;
            int h2 = node.hash;
            if (h1 > h2) {
                cmp = 1;
            } else if (h1 < h2) {
                cmp = -1;
            }  else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0) {
                //do nothing
            } else {
                cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
            }

            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            }
        }
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        newNode.parent = parent;
        fixAfterPut(newNode);
    }

    private void resetNode(Node<K, V> node) {
        node.left = null;
        node.right = null;
        node.parent = null;
        node.color = RED;
    }

    /**
     * 根据Key计算索引
     *
     * @param key
     * @return
     */
    private int index(K key) {
        if (key == null) return 0;
        int hashcode = key.hashCode();
        return (hashcode ^ (hashcode >>> 16)) & (table.length - 1);
    }

    private int index(Node<K, V> node) {
        if (node == null) return 0;
        int hashcode = node.hash;
        return (hashcode ^ (hashcode >>> 16)) & (table.length - 1);
    }

    /**
     * 查找某个节点的前驱节点
     * 前驱节点的定义：中序遍历，某个节点的前一个节点
     *
     * @param node
     * @return
     */
    private Node<K, V> predecessor(Node<K, V> node) {
        if (node == null) {
            return null;
        }
        //如果有左子树，找左子树中最大的那个节点
        if (node.left != null) {
            Node<K, V> p = node.left;
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

    protected Node<K, V> createNode(K k, V v, Node<K, V> parent) {
        return new Node<>(k, v, parent);
    }

    private Node<K, V> node(K k1) {
        Node<K, V> root = table[index(k1)];
        return node(root, k1);
    }

    private Node<K, V> node(Node<K, V> node, K k1) {
        int h1 = k1 == null ? 0 : k1.hashCode();
        Node<K, V> result = null;
        int cmp = 0;
        while (node != null) {
            K k2 = node.k;
            int h2 = node.hash;
            if (h1 > h2) {
                node = node.right;
            } else if (h1 < h2) {
                node = node.left;
            } else if (Objects.equals(k1, k2)) {
                return node;
            } else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0) {
                node = cmp > 0 ? node.right : node.left;
                //hash值相等，但不具备可比较性，也不equals，只能进行扫描查找(递归扫描)
            } else if (node.right != null && (result = node(node.right, k1)) != null) {
                return result;
            } else {
                //右边没找到，只能往左找，减少递归调用
                node = node.left;
            }
//            } else if (node.left != null && (result = node(node.left, k1)) != null) {
//                return result;
//            } else {
//                return null;
//            }
        }
        return null;
    }

    private void fixAfterPut(Node<K, V> node) {
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
            fixAfterPut(red(grand));
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

    private void fixAfterRemove(Node<K, V> node, Node<K, V> replacement) {
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
                    fixAfterRemove(parent, null);
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
                    fixAfterRemove(parent, null);
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
            table[index(grand)] = parent;
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

    protected static class Node<K, V> {
        K k;
        V v;
        int hash;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;
        //默认为红色，可以更快的满足红黑树的性质
        boolean color = RED;

        public Node(K k, V v, Node<K, V> parent) {
            this.k = k;
            this.v = v;
            this.parent = parent;
            this.hash = k == null ? 0 : k.hashCode();
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
