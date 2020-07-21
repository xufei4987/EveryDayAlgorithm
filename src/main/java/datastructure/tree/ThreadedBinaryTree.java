package datastructure.tree;

/**
 * 线索化二叉树：
 * 一个顺序存储树二叉树节点的left和right指针可能为空，没有被充分的利用起来
 * 根据公式【2n-(n-1) = n+1】n个节点的顺序存储树二叉树有n+1个空指针
 * 按照中序遍历的顺序，为空的left指向前驱节点，为空的right指向后继节点，即为线索化二叉树
 * 作用：二叉树按照特定的顺序（前序、中序、后序）线索化后，再进行遍历时就可以不用再使用栈进行遍历，顺序遍历即可。
 */
public class ThreadedBinaryTree {

    private ThreadedNode head = new ThreadedNode(null);

    private ThreadedNode preNode = null;

    public ThreadedNode getHead() {
        return head;
    }

    private void threadedNodes(){
        threadedNodes(head);
    }

    /**
     * 中序遍历线索化
     * @param node
     */
    private void threadedNodes(ThreadedNode node){
        if (node == null) {
            return;
        }
        //线索化左子树
        threadedNodes(node.left);
        //线索化当前节点
        if(node.left == null){
            node.left = preNode;
            node.leftType = 1;
        }
        //需要指向后继节点，则需要到处理后继节点时操作preNode
        if(preNode != null && preNode.right == null){
            preNode.right = node;
            preNode.rightType = 1;
        }
        preNode = node;
        //线索化右子树
        threadedNodes(node.right);

    }

    /**
     * 按照线索化后的二叉树进行中序遍历
     */
    public void threadedList(){
        ThreadedNode node = head;
        while (node != null){
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }
            System.out.println(node.getData());
            while (node.getRightType() == 1){
                node = node.getRight();
                System.out.println(node.getData());
            }
            node = node.getRight();
        }
    }

    public static class ThreadedNode<E> {
        private E data;
        private ThreadedNode left;
        private ThreadedNode right;
        private int leftType; //0:表示left指向的是左子树，1：表示left指向的是前驱节点
        private int rightType; //0:表示right指向的是右子树，1：表示right指向的是后继节点

        public ThreadedNode(E data) {
            this.data = data;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public ThreadedNode getLeft() {
            return left;
        }

        public void setLeft(ThreadedNode left) {
            this.left = left;
        }

        public ThreadedNode getRight() {
            return right;
        }

        public void setRight(ThreadedNode right) {
            this.right = right;
        }

        public int getLeftType() {
            return leftType;
        }

        public void setLeftType(int leftType) {
            this.leftType = leftType;
        }

        public int getRightType() {
            return rightType;
        }

        public void setRightType(int rightType) {
            this.rightType = rightType;
        }
    }

    /**
     * 顺序二叉树：1、3、6、8、10、14 中序遍历结果：8、3、10、1、14、6
     * @param args
     */
    public static void main(String[] args) {
        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        ThreadedNode head = tree.getHead();
        head.setData(1);
        head.setLeft(new ThreadedNode(3));
        head.setRight(new ThreadedNode(6));
        head.getLeft().setLeft(new ThreadedNode(8));
        head.getLeft().setRight(new ThreadedNode(10));
        head.getRight().setLeft(new ThreadedNode(14));
        //线索化
        tree.threadedNodes();

//        ThreadedNode threadedNode = head.getLeft().getRight();
//        System.out.println("10号节点的前驱节点：" + threadedNode.getLeft().getData());
//        System.out.println("10号节点的后继节点：" + threadedNode.getRight().getData());

        tree.threadedList();

    }
}
