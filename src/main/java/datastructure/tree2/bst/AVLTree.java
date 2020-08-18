package datastructure.tree2.bst;

import java.util.Comparator;

/**
 * 平衡因子（balance factor）：左子树的高度-右子树的高度
 * 所有节点的平衡因子的绝对值<=1，则为AVL树
 * 添加操作：可能会导致所有的祖先节点都失去平衡，但让高度最低的失去平衡的祖先节点恢复平衡，则整颗树恢复平衡，时间复杂度O（1）
 * 删除操作：只可能会导致祖先节点失衡（只会导致一个节点失衡），但让父节点恢复平衡后可能会导致更高层的祖先节点失衡，时间复杂度O（logn）
 * @param <E>
 */
public class AVLTree<E> extends BalancedBinarySearchTree<E> {
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
    protected void afterAdd(Node<E> node) {
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

    @Override
    protected void afterRemove(Node<E> node, Node<E> replacement) {
        Node n = node;
        while ((n = n.parent) != null) {
            if (isBalance(n)) {
                updateHeight(n);
            } else {
                rebalance(n);
            }
        }
    }

    //恢复平衡
    private void rebalance2(Node node) {
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

    private void rebalance(Node node) {
        AVLNode grand = (AVLNode) node;
        AVLNode parent = (AVLNode) grand.tallerNode();
        AVLNode child = (AVLNode) parent.tallerNode();
        if (parent.isLeftChild()) { // L
            if (child.isLeftChild()) { // LL
//                rotate(grand,child.left,child,child.right,parent,parent.right,grand,grand.right);
                rotate(grand,child,child.right,parent,parent.right,grand);
            } else { // LR
//                rotate(grand,parent.left,parent,child.left,child,child.right,grand,grand.right);
                rotate(grand,parent,child.left,child,child.right,grand);
            }
        } else { // R
            if (child.isLeftChild()) { // RL
//                rotate(grand,grand.left,grand,child.left,child,child.right,parent,parent.right);
                rotate(grand,grand,child.left,child,child.right,parent);
            } else { //RR
//                rotate(grand,grand.left,grand,parent.left,parent,child.left,child,child.right);
                rotate(grand,grand,parent.left,parent,child.left,child);
            }
        }
    }

    @Override
    protected void afterRotate(Node grand, Node parent, Node child) {
        super.afterRotate(grand, parent, child);
        //更新高度,先更新较低节点的高度，再更新较高节点的高度
        updateHeight(grand);
        updateHeight(parent);
    }

    @Override
    public void rotate(Node r, Node b, Node c, Node d, Node e, Node f) {
        super.rotate(r, b, c, d, e, f);
        updateHeight(b);
        updateHeight(f);
        updateHeight(d);
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
