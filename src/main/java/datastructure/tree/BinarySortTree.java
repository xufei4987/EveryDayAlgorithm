package datastructure.tree;

/**
 * 二叉排序树（BST）:
 * 小于的在左边 大于的在右边
 */
public class BinarySortTree {

    private Node head;

    public BinarySortTree(Node head) {
        this.head = head;
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
    }

    private int delRightTreeMin(Node node) {
        Node curNode = node;
        while (curNode.left != null){
            curNode = curNode.left;
        }
        deleteNode(curNode.value);
        return curNode.value;
    }

    private static class Node {
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

    /**
     *            7
     *      3         10
     *   1     5   9       12
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        BinarySortTree binarySortTree = new BinarySortTree(new Node(arr[0]));
        for (int i = 1; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        binarySortTree.midOrder();
        binarySortTree.deleteNode(7);
        binarySortTree.deleteNode(3);
        binarySortTree.deleteNode(5);
        binarySortTree.deleteNode(9);
        binarySortTree.deleteNode(12);
        binarySortTree.deleteNode(10);
        binarySortTree.deleteNode(1);
        System.out.println("--------------");
        binarySortTree.midOrder();
    }
}
