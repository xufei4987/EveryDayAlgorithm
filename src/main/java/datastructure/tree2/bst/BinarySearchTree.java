package datastructure.tree2.bst;

import datastructure.tree2.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;

public class BinarySearchTree<E> implements BinaryTreeInfo {
    private Node<E> root;
    private int size;
    private Comparator<E> comparator;//自定义比较器

    public BinarySearchTree(Comparator comparator) {
        this.comparator = comparator;
    }

    public BinarySearchTree() {
        this(null);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public void add(E e) {
        elementNotNullCheck(e);
        if (root == null) {
            root = new Node<>(e, null);
            size++;
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
        Node newNode = new Node<>(e, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
    }

    public void remove(E e) {

    }

    public boolean contains(E e) {
        return true;
    }

    public void preorder(Visitor<E> visitor) {
        if (root == null) {
            return;
        }
        preorder(root, visitor);
    }

    //visitor可以控制遍历的对遍历元素进行何种操作，stop可以控制遍历到哪停止
    private void preorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) {
            return;
        }
        if (visitor == null) {
            System.out.println(node.element);
        } else {
            if (visitor.stop) {
                return;
            }
            visitor.stop = visitor.visit(node.element);
        }
        preorder(node.left, visitor);
        preorder(node.right, visitor);
    }

    public void inorder() {
        if (root == null) {
            return;
        }
        inorder(root);
    }

    private void inorder(Node<E> node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        System.out.println(node.element);
        inorder(node.right);
    }

    public void postorder() {
        if (root == null) {
            return;
        }
        postorder(root);
    }

    private void postorder(Node<E> node) {
        if (node == null) {
            return;
        }
        postorder(node.left);
        postorder(node.right);
        System.out.println(node.element);
    }

    //层序遍历
    public void levelOrder() {
        if (root == null) {
            return;
        }
        Node node = null;
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            node = queue.poll();
            System.out.println(node.element);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    //求一棵树的高度
    public int height() {
        return height(root);
    }

    //利用递归求一棵树的高度
    private int height(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    //利用层遍历求一棵树的高度，原理：每一层遍历完，队列中的节点的个数即为下一层需要遍历的个数
    public int height2() {
        if (root == null) {
            return 0;
        }
        Node node = null;
        int levelSize = 1;
        int height = 0;
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            node = queue.poll();
            levelSize--;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (levelSize == 0) {
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }

    //判断是否是完全二叉树，利用层序遍历的原理
    public boolean isComplete() {
        if (root == null) {
            return false;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);
        Node node = null;
        boolean leaf = false;
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (leaf && !node.isLeaf()){
                return false;
            }
            if (node.hasTwoChildren()) {
                queue.offer(node.left);
                queue.offer(node.right);
            } else if (node.left == null && node.right != null) {
                return false;
            } else if (node.left != null && node.right == null){
                queue.offer(node.left);
                //左子节点不为空，右子节点为空，说明剩下的未遍历的节点都需要是叶子节点
                leaf = true;
            }else {
                //左右子节点都为空，说明剩下的未遍历的节点都需要是叶子节点
                leaf = true;
            }
        }
        return true;
    }
    //翻转二叉树
    public void inverseTree(){
        inverseTree(root);
    }

    private void inverseTree(Node<E> node) {
        if(node == null){
            return;
        }
        Node tmp = node.left;
        node.left = node.right;
        node.right = tmp;
        inverseTree(node.left);
        inverseTree(node.right);
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

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node) node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node) node).element;
    }

    private static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
    }

}
