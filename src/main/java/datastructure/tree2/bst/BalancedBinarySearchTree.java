package datastructure.tree2.bst;

import java.util.Comparator;

public class BalancedBinarySearchTree<E> extends BinarySearchTree<E> {

    public BalancedBinarySearchTree(Comparator comparator) {
        super(comparator);
    }

    public BalancedBinarySearchTree() {
        this(null);
    }

    /**
     * 统一旋转操作 从小到大为子树节点编号a-g，发现所有情况恢复平衡后都只有1种情况，所以可以统一处理
     *  a、g可以不处理
     * @param r 子树的根节点
    //     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param f
    //     * @param g
     */
    public void rotate(Node r,
            /*Node a,*/ Node b, Node c,
                       Node d,
                       Node e, Node f/*, Node g*/) {
        //处理根节点
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else {
            root = d;
        }
        //处理a、b、c
        b.right = c;
//        b.left = a;
//        if(a != null){
//            a.parent = b;
//        }
        if(c != null){
            c.parent = b;
        }
        //处理e、f、g
        f.left = e;
        if(e != null){
            e.parent = f;
        }
//        f.right = g;
//        if(g != null){
//            g.parent = f;
//        }
        //处理b、d、f的关系
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
    }

    //左旋
    protected void rotateLeft(Node grand) {
        Node parent = grand.right;
        Node leftChild = parent.left;
        grand.right = leftChild;
        parent.left = grand;
        afterRotate(grand, parent, leftChild);

    }

    //右旋
    protected void rotateRight(Node grand) {
        Node parent = grand.left;
        Node rightChild = parent.right;
        grand.left = rightChild;
        parent.right = grand;
        afterRotate(grand, parent, rightChild);
    }

    protected void afterRotate(Node grand, Node parent, Node child) {
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
}
