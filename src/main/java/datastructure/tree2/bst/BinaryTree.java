package datastructure.tree2.bst;

import datastructure.tree2.printer.BinaryTreeInfo;

import java.util.LinkedList;

public class BinaryTree<E> implements BinaryTreeInfo {

    protected Node<E> root;
    protected int size;

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

    protected Node<E> createNode(E element, Node<E> parent){
        return new Node<>(element,parent);
    }

    /**
     * 查找某个节点的前驱节点
     * 前驱节点的定义：中序遍历，某个节点的前一个节点
     *
     * @param node
     * @return
     */
    protected Node<E> predecessor(Node<E> node) {
        if (node == null) {
            return null;
        }
        //如果有左子树，找左子树中最大的那个节点
        if (node.left != null) {
            Node<E> p = node.left;
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

    /**
     * 查找某个节点的后继节点
     * 前驱节点的定义：中序遍历，某个节点的后一个节点
     *
     * @param node
     * @return
     */
    protected Node<E> successor(Node<E> node) {
        if (node == null) {
            return null;
        }
        //如果有右子树，找右子树中最小的那个节点
        if (node.right != null) {
            Node<E> p = node.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }
        while (node.parent != null && node.parent.right == node) {
            node = node.parent;
        }
        //node父节点为空，返回null
        //node的父节点不为空，当前节点是父节点的左子节点，返回父节点
        return node.parent;
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
            if (leaf && !node.isLeaf()) {
                return false;
            }
            if (node.hasTwoChildren()) {
                queue.offer(node.left);
                queue.offer(node.right);
            } else if (node.left == null && node.right != null) {
                return false;
            } else if (node.left != null && node.right == null) {
                queue.offer(node.left);
                //左子节点不为空，右子节点为空，说明剩下的未遍历的节点都需要是叶子节点
                leaf = true;
            } else {
                //左右子节点都为空，说明剩下的未遍历的节点都需要是叶子节点
                leaf = true;
            }
        }
        return true;
    }

    //翻转二叉树
    public void inverseTree() {
        inverseTree(root);
    }

    private void inverseTree(Node<E> node) {
        if (node == null) {
            return;
        }
        Node tmp = node.left;
        node.left = node.right;
        node.right = tmp;
        inverseTree(node.left);
        inverseTree(node.right);
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
        Node n = (Node) node;
        if(n.parent == null){
            return n.toString() + "_(null)";
        } else {
            return n.toString() + "_(" + n.parent.element + ")";
        }
    }

    protected static class Node<E> {
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

        public boolean isLeftChild(){
            return parent != null && this == parent.left;
        }

        public boolean isRightChild(){
            return parent != null && this == parent.right;
        }
        //兄弟节点
        public Node<E> sibling(){
            if(isRightChild()){
                return this.parent.left;
            }
            if(isLeftChild()){
                return this.parent.right;
            }
            return null;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }
}
