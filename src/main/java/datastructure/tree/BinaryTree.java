package datastructure.tree;

/**
 * 二叉树的遍历（父节点的输出顺序决定是哪一种遍历）：
 * 1、前序遍历：先遍历父节点，再遍历左子树，再遍历右子树
 * 2、中序遍历：先遍历左子树，再遍历父节点，再遍历右子树
 * 3、后序遍历：先遍历左子树，再遍历右子树，再遍历父节点
 *                1
 *          2         3
 *      4       5   6      7
 */
public class BinaryTree<E> {
    public static class Node<E> {
        private E data;
        private Node left;
        private Node right;

        public Node(E data) {
            this.data = data;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

    private Node head = new Node(null);

    public Node getHead() {
        return head;
    }

    //前序遍历
    public void preorder() {
        if (head.data == null) {
            return;
        }
        preorder0(head);
    }

    private void preorder0(Node node) {
        System.out.println(node.data);
        if (node.left != null) {
            preorder0(node.left);
        }
        if (node.right != null) {
            preorder0(node.right);
        }
    }

    //中序遍历
    public void midorder() {
        if (head.data == null) {
            return;
        }
        midorder0(head);
    }

    private void midorder0(Node node) {
        if (node.left != null) {
            midorder0(node.left);
        }
        System.out.println(node.data);
        if (node.right != null) {
            midorder0(node.right);
        }
    }

    //后序遍历
    public void postorder() {
        if (head.data == null) {
            return;
        }
        postorder0(head);
    }

    private void postorder0(Node node) {
        if (node.left != null) {
            postorder0(node.left);
        }
        if (node.right != null) {
            postorder0(node.right);
        }
        System.out.println(node.data);
    }

    public boolean preorderSearch(E e) {
        if (head == null) {
            return false;
        }
        return preorderSearch0(head, e);
    }

    private boolean preorderSearch0(Node node, E e) {
        System.out.println("preorderSearch compare");
        if (node.getData().equals(e)) {
            return true;
        }
        boolean find = false;
        if (node.getLeft() != null) {
            find = preorderSearch0(node.getLeft(), e);
        }
        if (find) {
            return find;
        }
        if (node.getRight() != null) {
            find = preorderSearch0(node.getRight(), e);
        }
        return find;
    }

    public boolean midorderSearch(E e) {
        if (head == null) {
            return false;
        }
        return midorderSearch0(head, e);
    }

    private boolean midorderSearch0(Node node, E e) {
        boolean find = false;
        if (node.getLeft() != null) {
            find = midorderSearch0(node.getLeft(), e);
        }
        if (find) {
            return find;
        }
        System.out.println("midorderSearch compare");
        if (node.getData().equals(e)) {
            return true;
        }
        if (node.getRight() != null) {
            find = midorderSearch0(node.getRight(), e);
        }
        return find;
    }

    public boolean postorderSearch(E e) {
        if (head == null) {
            return false;
        }
        return postorderSearch0(head, e);
    }

    private boolean postorderSearch0(Node node, E e) {
        boolean find = false;
        if (node.getLeft() != null) {
            find = postorderSearch0(node.getLeft(), e);
        }
        if (find) {
            return find;
        }
        if (node.getRight() != null) {
            find = postorderSearch0(node.getRight(), e);
        }
        if (find) {
            return find;
        }
        System.out.println("postorderSearch compare");
        if (node.getData().equals(e)) {
            return true;
        }
        return false;
    }

    public void remove(E e){
        if (head == null){
            return;
        } else if(head.getData().equals(e)){
            head.setRight(null);
            head.setLeft(null);
            head.setData(null);
            return;
        }
        remove0(head,e);
    }
    //需要找到待删除节点的父节点对其进行删除
    private void remove0(Node node, E e) {
        if (node.left != null && node.left.getData().equals(e)){
            node.left = null;
            return;
        }
        if (node.right != null && node.right.getData().equals(e)){
            node.right = null;
            return;
        }
        //向左递归删除
        if (node.left != null){
            remove0(node.left, e);
        }
        //向右递归删除
        if(node.right != null){
            remove0(node.right, e);
        }
    }

    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        Node head = binaryTree.getHead();
        head.setData(1);
        head.setLeft(new Node(2));
        head.setRight(new Node(3));
        head.getLeft().setLeft(new Node(4));
        head.getLeft().setRight(new Node(5));
        head.getRight().setLeft(new Node(6));
        head.getRight().setRight(new Node(7));
        binaryTree.remove(3);
        System.out.println("前序遍历");
        binaryTree.preorder();
//        System.out.println("中序遍历");
//        binaryTree.midorder();
//        System.out.println("后序遍历");
//        binaryTree.postorder();
//        System.out.println("前序查询");
//        System.out.println(binaryTree.preorderSearch(1));
//        System.out.println(binaryTree.preorderSearch(0));
//        System.out.println("中序查询");
//        System.out.println(binaryTree.midorderSearch(1));
//        System.out.println(binaryTree.midorderSearch(0));
//        System.out.println("后序查询");
//        System.out.println(binaryTree.postorderSearch(1));
//        System.out.println(binaryTree.postorderSearch(0));
    }
}
