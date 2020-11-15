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
 * 删除节点一共有8种情况：
 * 1、删除红色节点的4种情况，由于删除后还是满足红黑树的性质，所以不用处理
 * 2、删除有2个红色子节点的黑色节点的1种情况，度为2的节点是删除其前驱或者后继，而其前驱或者后继为红色，所以删除后也不用处理。
 * 3、删除一个拥有红色子节点的黑色节点的1种情况，
 * 4、删除叶子节点为黑色的情况
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
        return new RBNode<>(element, parent);
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

    @Override
    protected void afterRemove(Node<E> node, Node<E> replacement) {
        //如果删除的为红色叶子节点不用处理
        if (isRed(node)) return;
        //删除的是带有一个红色子节点的黑色节点
        if (isRed(replacement)) {
            black(replacement);
            return;
        }
        Node<E> parent = node.parent;
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
        Node<E> sibling = left ? parent.right : parent.left;
        if (left) { //被删除的节点在左边，兄弟节点在右边
            if (isRed(sibling)) { //将兄弟节点是红色的情况转化为兄弟节点是黑色的情况
                black(sibling);
                red(parent);
                rotateLeft(parent);
                //变更兄弟
                sibling = parent.right;
            }
            if(isBlack(sibling.left) && isBlack(sibling.right)){ //兄弟节点没有红色子节点
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if(parentBlack){ //如果父节点为黑色，需要父节点向下合并，再将父节点重复处理
                    afterRemove(parent,null);
                }
            } else { //兄弟节点至少有一个红色子节点
                if (isBlack(sibling.right)){
                    rotateRight(sibling);
                    sibling = parent.right;
                }
                color(sibling,colorOf(parent));
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
            if(isBlack(sibling.left) && isBlack(sibling.right)){ //兄弟节点没有红色子节点
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if(parentBlack){ //如果父节点为黑色，需要父节点向下合并，再将父节点重复处理
                    afterRemove(parent,null);
                }
            } else { //兄弟节点至少有一个红色子节点
                if (isBlack(sibling.left)){
                    rotateLeft(sibling);
                    sibling = parent.left;
                }
                color(sibling,colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }

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
