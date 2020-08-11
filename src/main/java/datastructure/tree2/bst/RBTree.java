package datastructure.tree2.bst;

import java.util.Comparator;

/**
 * 红黑树：
 * 性质1：节点是红色或者黑色
 * 性质2：根节点是黑色
 * 性质3：叶子节点是黑色
 * 性质4：红色节点的子节点是黑色
 * 性质5：从任一节点到叶子节点的路径上都有相同数目的黑色节点
 * <p>
 * 红黑树叶子节点的情况（不算null节点）一共有4种：
 * 红黑红、红黑、黑红、黑（2个红色的子节点、一个在左边的红色子节点、一个在右边的红色子节点、没有红色子节点）
 * 添加节点一共有12种情况：
 * 1、不需要做任何操作的前4种情况：（红黑）+红、红+（黑红）、红+（黑）、（黑）+红
 * 2、LL、RR的两种情况（uncle节点不是红色）：将parent染成红色，将grand染成黑色，LL进行右旋，RR进行左旋
 * 3、LR、RL的两种情况（uncle节点不是红色）：将child染成黑，将grand染成红色，LR先对parent进行左旋，再对grand进行右旋，RL先对parent进行右旋，再对grand进行左旋
 * 4、LL、RR、LR、RL的四种情况（uncle节点是红色）：这四种情况需要先将parent和uncle节点染成黑色（独立成一个B数节点），然后将grand节点染成红色并当成新加入的节点向上合并（上溢）
 */
public class RBTree<E> extends BalancedBinarySearchTree<E> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree() {
        this(null);
    }

    public RBTree(Comparator comparator) {
        super(comparator);
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element,parent);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;
        //添加的是root节点
        if (parent == null) {
            black(node);
            return;
        }
        //1、父节点是黑色的四种情况,不需要进行额外的处理
        if (isBlack(parent)) {
            return;
        }

        Node<E> uncle = parent.sibling();
        Node<E> grand = parent.parent;
        if (isRed(uncle)) { //2、uncle节点是红色的4种情况
            black(parent);
            black(uncle);
            afterAdd(red(grand));
            return;
        }
        //3、uncle节点是红色的4种情况
        if (parent.isLeftChild()){ //L
            if(node.isLeftChild()){ //LL
                black(parent);
                red(grand);
                rotateRight(grand);
            }else { //LR
                black(node);
                red(grand);
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else { //R
            if(node.isLeftChild()){//RL
                black(node);
                red(grand);
                rotateRight(parent);
                rotateLeft(grand);
            }else {//RR
                black(parent);
                red(grand);
                rotateLeft(grand);
            }
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
        super.afterRemove(node);
    }

    private Node<E> color(Node<E> node, boolean color) {
        if (node == null) {
            return node;
        }
        RBNode rbNode = (RBNode) node;
        rbNode.color = color;
        return rbNode;
    }

    public Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    public Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode) node).color;
    }

    public boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

    public boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    private static class RBNode<E> extends Node<E> {

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        //默认为红色，可以更快的满足红黑树的性质
        boolean color = RED;

        @Override
        public String toString() {
            String c = this.color == BLACK ? "b" : "r";
            return element + "(" + c + ")";
        }
    }
}
