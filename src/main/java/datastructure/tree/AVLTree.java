package datastructure.tree;

/**
 * 自平衡二叉树（需要是一颗二叉排序树，且根节点左右子树的高度差的绝度值不大于1）
 */
public class AVLTree {

    private Node head;

    public Node getHead() {
        return head;
    }

    public AVLTree(Node head) {
        this.head = head;
    }

    public int getLeftHeight(){
        if(head.left == null){
            return 0;
        }
        return height0(head.left);
    }

    public int getLeftHeight(Node node){
        if(node.left == null){
            return 0;
        }
        return height0(node.left);
    }

    public int getRightHeight(){
        if(head.right == null){
            return 0;
        }
        return height0(head.right);
    }

    public int getRightHeight(Node node){
        if(node.right == null){
            return 0;
        }
        return height0(node.right);
    }

    public int height(){
        return height0(head);
    }

    private int height0(Node node) {
        return Math.max(node.left == null ? 0 : height0(node.left), node.right == null ? 0 : height0(node.right)) + 1;
    }

    public void add(Node node) {
        if (node == null || head == null) {
            return;
        }
        add0(head, node);
    }

    private void add0(Node curNode, Node node) {
        if (node.value < curNode.value) {
            if (curNode.left == null) {
                curNode.left = node;
            } else {
                add0(curNode.left, node);
            }
        } else {
            if (curNode.right == null) {
                curNode.right = node;
            } else {
                add0(curNode.right, node);
            }
        }
        rebalance(curNode);
    }

    /**
     * 查询值为value的节点
     *
     * @param value
     * @return
     */
    public Node search(int value) {
        if (head == null) {
            return null;
        }
        return search0(head, value);
    }

    private Node search0(Node node, int value) {
        if (node.value == value) {
            return node;
        } else if (value < node.value) {
            if (node.left == null) {
                return null;
            }
            return search0(node.left, value);
        } else {
            if (node.right == null) {
                return null;
            }
            return search0(node.right, value);
        }
    }

    /**
     * 查询值为value的节点的父节点
     *
     * @param value
     * @return
     */
    public Node searchParent(int value) {
        if (head == null || value == head.value) {
            return null;
        }
        return searchParent0(head, value);
    }

    private Node searchParent0(Node node, int value) {
        if ((node.left != null && node.left.value == value) ||
                (node.right != null && node.right.value == value)) {
            return node;
        } else if (value < node.value && node.left != null) {
            return searchParent0(node.left, value);
        } else if (value >= node.value && node.right != null) {
            return searchParent0(node.right, value);
        } else {
            return null;
        }
    }

    public void deleteNode(int value) {
        if (head == null) {
            return;
        }
        Node targetNode = search(value);
        //没找到targetNode
        if (targetNode == null) {
            return;
        }
        //找到targetNode,且正好是head,且只有head一个节点
        if (head.left == null && head.right == null) {
            head = null;
            return;
        }
        Node parentNode = searchParent(value);
        //如果删除的是叶子节点
        if (targetNode.left == null && targetNode.right == null) {
            if (parentNode.left != null && parentNode.left.value == value) {
                parentNode.left = null;
            } else if (parentNode.right != null && parentNode.right.value == value) {
                parentNode.right = null;
            }
        } else if (targetNode.left != null && targetNode.right != null) { //如果删除的节点有左右子节点
            //从左子树找最大的叶子节点，删除该节点并把该值赋值给当前节点（或者从右子树找最小的叶子结点，删除该节点并把该值赋值给当前节点）
            int minVal = delRightTreeMin(targetNode.right);
            targetNode.value = minVal;
        } else { //如果删除的节点只有一个子节点
            if(targetNode.left != null){
                if(parentNode != null){
                    if(parentNode.left.value == value){
                        parentNode.left = targetNode.left;
                    }else {
                        parentNode.right = targetNode.left;
                    }
                }else {
                    head = targetNode.left;
                }
            }else{
                if(parentNode != null){
                    if(parentNode.left.value == value){
                        parentNode.left = targetNode.right;
                    }else {
                        parentNode.right = targetNode.right;
                    }
                }else {
                    head = targetNode.right;
                }
            }
        }
        //删除完毕后对父节点进行平衡处理
        rebalance(parentNode);
    }

    private int delRightTreeMin(Node node) {
        Node curNode = node;
        while (curNode.left != null){
            curNode = curNode.left;
        }
        deleteNode(curNode.value);
        return curNode.value;
    }

    /**
     * 进行平衡处理
     * @param node 当前节点
     */
    private void rebalance(Node node) {
        //当前节点右子树的高度 - 左子树的高度 > 1 需要进行左旋
        if(getRightHeight(node) - getLeftHeight(node) > 1){
            //如果当前节点右子树的左子树高度大于它的右子树高度， 则左旋后还是不平衡的 需要进行进一步处理
            if(getLeftHeight(node.right) > getRightHeight(node.right)){
                //对当前节点的右子节点进行右旋
                rightRotate(node.right);
            }
            leftRotate(node);
            //平衡后直接返回
            return;
        }
        //当前节点左子树的高度 - 右子树的高度 > 1 需要进行右旋
        if(getLeftHeight(node) - getRightHeight(node) > 1){
            //如果当前节点左子树的右子树高度大于它的左子树高度， 则右旋后还是不平衡的 需要进行进一步处理
            if(getRightHeight(node.left) > getLeftHeight(node.left)){
                //对当前节点的左子节点进行左旋
                leftRotate(node.left);
            }
            rightRotate(node);
        }
    }

    public void midOrder() {
        if (head == null) {
            System.out.println("没有节点可以遍历");
            return;
        }
        midOrder0(head);
    }

    private void midOrder0(Node node) {
        if (node.left != null) {
            midOrder0(node.left);
        }
        System.out.println(node);
        if (node.right != null) {
            midOrder0(node.right);
        }
    }

    public void reverseOrder() {
        if (head == null) {
            System.out.println("没有节点可以遍历");
            return;
        }
        reverseOrder0(head);
    }

    private void reverseOrder0(Node node) {
        if (node.right != null) {
            reverseOrder0(node.right);
        }
        System.out.println(node);
        if (node.left != null) {
            reverseOrder0(node.left);
        }
    }


    /**
     * 当前节点 右子树的高度 - 左子树的高度 > 1 需要进行左旋
     * @param node 当前节点
     */
    private void leftRotate(Node node){
        //创建一个新的节点，节点的值为当前节点的值
        Node newNode = new Node(node.value);
        //将新节点的左子节点设置为当前节点的左子节点
        newNode.left = node.left;
        //将新节点的右子节点设置为当前节点的右子节点的左子节点
        newNode.right = node.right.left;
        //把当前节点的值替换为右子节点的值
        node.value = node.right.value;
        //把当前右子节点设置为当前节点右子节点的右子节点
        node.right = node.right.right;
        //把当前的左子节点设置为新的节点
        node.left = newNode;
    }

    /**
     * 当前节点 左子树的高度 - 右子树的高度 > 1 需要进行右旋
     * @param node
     */
    private void rightRotate(Node node){
        Node newNode = new Node(node.value);
        newNode.right = node.right;
        newNode.left = node.left.right;
        node.value = node.left.value;
        node.left = node.left.left;
        node.right = newNode;

    }

    private static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }

    public static void main(String[] args) {
        int[] arr = {4,3,6,5,7,8};
//        int[] arr = {10,12,8,9,7,6};
//        int[] arr = {10,11,7,6,8,9};
        AVLTree avlTree = new AVLTree(new Node(4));
        for (int i = 1; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        avlTree.midOrder();
        System.out.println("树的高度：" + avlTree.height());
        System.out.println("左子树的高度：" + avlTree.getLeftHeight());
        System.out.println("右子树的高度：" + avlTree.getRightHeight());
//        avlTree.reverseOrder();
        System.out.println("---------删除后---------");
        avlTree.deleteNode(3);
        avlTree.deleteNode(5);
        avlTree.deleteNode(4);
        System.out.println("树的高度：" + avlTree.height());
        System.out.println("左子树的高度：" + avlTree.getLeftHeight());
        System.out.println("右子树的高度：" + avlTree.getRightHeight());
        System.out.println(avlTree.getHead());
        System.out.println(avlTree.getHead().left);
        System.out.println(avlTree.getHead().right);
    }
}
