package datastructure.tree;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 赫夫曼数：
 * 权值最小的树 权值计算方式为：叶子节点的值 = 叶子节点的权值*路径长度（层数-1） 所有叶子节点的值和最小的数为赫夫曼树
 * 构建过程：
 * 1、将数组从小到大排序
 * 2、获取最小的2个节点组成一颗新树，新树的根节点为权值为子节点的权值和
 * 3、将处理过的节点从数组中移除，并将新树的根节点加入数组
 * 4、重复1-3直到数组中只剩下最后一个节点，该节点即为整个赫夫曼树的根节点
 */
public class HuffmanTree {

    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        HuffmanTree huffmanTree = new HuffmanTree();
        Node rootNode = huffmanTree.createHuffmanTree(arr);
        huffmanTree.preorder(rootNode);
    }

    public Node createHuffmanTree(int[] arr){
        ArrayList<Node> nodes = new ArrayList<>();
        for (int val : arr) {
            Node node = new Node(val);
            nodes.add(node);
        }
        while (nodes.size() > 1){
            //从小到大排序
            Collections.sort(nodes);
            System.out.println(nodes);
            //取出权值最小的2个节点（一个节点可以看成一个最简单的二叉树）
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            //形成一颗新的树
            Node parent = new Node(left.value + right.value);
            parent.left = left;
            parent.right = right;
            //移除处理过的节点
            nodes.remove(left);
            nodes.remove(right);
            //添加新节点
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    public void preorder(Node node){
        System.out.println(node);
        if(node.left != null){
            preorder(node.left);
        }
        if(node.right != null){
            preorder(node.right);
        }
    }

    private static class Node implements Comparable<Node> {
        int value;//节点的权值
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }
}
