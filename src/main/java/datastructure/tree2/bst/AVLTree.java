package datastructure.tree2.bst;

import java.util.Comparator;

/**
 * 平衡因子（balance factor）：左子树的高度-右子树的高度
 * 所有节点的平衡因子的绝对值<=1，则为AVL树
 *
 * @param <E>
 */
public class AVLTree<E> extends BinarySearchTree<E> {
    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    @Override
    protected void afterAdd(Node node) {
        Node n = node;
        while ((n = n.parent) != null) {
            if (isBalance(n)) {
                updateHeight(n);
            } else {
                rebalance(n);
                break;
            }
        }
    }

    //恢复平衡
    private void rebalance(Node node) {
        AVLNode grand = (AVLNode) node;
        AVLNode parent = (AVLNode) grand.tallerNode();
        AVLNode child = (AVLNode) parent.tallerNode();
        if (parent.isLeftChild()) { // L
            if (child.isLeftChild()) { // LL
                //右旋转
                rotateRight(grand);
            } else { // LR
                //先左旋 再右旋
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else { // R
            if (child.isLeftChild()) { // RL
                //先右旋 再左旋
                rotateRight(parent);
                rotateLeft(grand);
            } else { //RR
                //左旋转
                rotateLeft(grand);
            }
        }
    }

    //左旋
    private void rotateLeft(AVLNode grand) {
        Node parent = grand.right;
        Node leftChild = parent.left;
        grand.right = leftChild;
        parent.left = grand;
        afterRotate(grand, parent, leftChild);

    }

    //右旋
    private void rotateRight(AVLNode grand) {
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
        //更新高度,先更新较低节点的高度，再更新较高节点的高度
        updateHeight(grand);
        updateHeight(parent);
    }

    private boolean isBalance(Node<E> node) {
        return Math.abs(((AVLNode) node).balanceFactor()) <= 1;
    }

    private void updateHeight(Node<E> node) {
        ((AVLNode) node).updateHeight();
    }

    private static class AVLNode<E> extends Node<E> {
        //高度
        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        //计算平衡因子
        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode) right).height;
            return leftHeight - rightHeight;
        }

        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode) right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        public Node<E> tallerNode() {
            int leftHeight = left == null ? 0 : ((AVLNode) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode) right).height;
            if (leftHeight < rightHeight) {
                return right;
            } else if (leftHeight > rightHeight) {
                return left;
            } else {
                //左右子树相等，则规定当前节点是左子节点就返回left 是右子节点就返回right
                return this.isLeftChild() ? left : right;
            }
        }

    }
}
