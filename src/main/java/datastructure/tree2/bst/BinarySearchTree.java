package datastructure.tree2.bst;

import java.util.Comparator;

public class BinarySearchTree<E> extends BinaryTree<E> {

    protected Comparator<E> comparator;//自定义比较器

    public BinarySearchTree(Comparator comparator) {
        super();
        this.comparator = comparator;
    }

    public BinarySearchTree() {
        this(null);
    }

    public void add(E e) {
        elementNotNullCheck(e);
        if (root == null) {
            root = createNode(e, null);
            size++;
            afterAdd(root);
            return;
        }
        Node node = root;
        Node parent = root;
        int cmp = 0;
        while (node != null) {
            cmp = compare(e, (E) node.element);
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                node.element = e;
                return; //相同直接返回
            }
        }
        Node newNode = createNode(e, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
        afterAdd(newNode);
    }

    protected void afterAdd(Node node) {
        //do nothing
    }

    /**
     * 删除节点：
     * 1、待删除节点度为0（叶子节点）
     * 2、待删除节点度为2
     * 3、待删除节点度为1
     *
     * @param e
     */
    public void remove(E e) {
        remove(getNode(e));
    }

    private void remove(Node<E> node) {
        if (node == null) {
            return;
        }
        Node<E> p = node;
        if (node.hasTwoChildren()) {
            p = predecessor(p);
            node.element = p.element;
            //需要将前驱节点删除，由于前驱节点的度必为0或者1，所以走下面的删除流程就可以了
        }
        if (p.isLeaf()) {
            if (p.parent == null) {
                root = null;
            } else {
                if (p.parent.left == p) {
                    p.parent.left = null;
                } else {
                    p.parent.right = null;
                }
            }
        } else {
            //待删除节点度为1
            if (p.parent == null) {
                root = p.left != null ? p.left : p.right;
            } else if (p.parent.left == p) {
                p.parent.left = p.left != null ? p.left : p.right;
            } else {
                p.parent.right = p.left != null ? p.left : p.right;
            }
        }
        size--;
    }

    private Node<E> getNode(E e) {
        if (root == null) {
            return null;
        }
        Node<E> node = root;
        while (node != null) {
            int compare = compare(e, node.element);
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

    public boolean contains(E e) {
        return getNode(e) != null;
    }

    private void elementNotNullCheck(E e) {
        if (e == null) {
            throw new IllegalArgumentException("element must not null");
        }
    }

    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable) e1).compareTo(e2);
    }

    @Override
    public String toString() {
        if (root == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        String prefix = "【root】";
        toString(root, sb, prefix);
        return sb.toString();
    }

    private void toString(Node<E> node, StringBuilder sb, String prefix) {
        if (node == null) {
            return;
        }
        sb.append(prefix).append(node.element).append("\n");
        toString(node.left, sb, prefix + "【left】");
        toString(node.right, sb, prefix + "【right】");
    }
}
