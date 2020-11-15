package datastructure.tree;

/**
 * 顺序存储二叉树：
 * 特点：完全二叉树 当前节点的下标为n  则左子节点为2n+1  右节点为2n+2
 */
public class ArrayBinaryTree {
    private int[] array;

    public ArrayBinaryTree(int[] array) {
        this.array = array;
    }

    public void preorder(){
        if (array == null || array.length <= 0) {
            return;
        }
        preorder(0);
    }

    private void preorder(int idx) {
        System.out.println(array[idx]);
        if (idx * 2 + 1 < array.length) {
            preorder(idx * 2 + 1);
        }
        if (idx * 2 + 2 < array.length) {
            preorder(idx * 2 + 2);
        }
    }

    public void midorder(){
        if (array == null || array.length <= 0) {
            return;
        }
        midorder(0);
    }

    private void midorder(int idx) {
        if (idx * 2 + 1 < array.length) {
            midorder(idx * 2 + 1);
        }
        System.out.println(array[idx]);
        if (idx * 2 + 2 < array.length) {
            midorder(idx * 2 + 2);
        }
    }

    public void postorder(){
        if (array == null || array.length <= 0) {
            return;
        }
        postorder(0);
    }

    private void postorder(int idx) {
        if (idx * 2 + 1 < array.length) {
            postorder(idx * 2 + 1);
        }
        if (idx * 2 + 2 < array.length) {
            postorder(idx * 2 + 2);
        }
        System.out.println(array[idx]);
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrayBinaryTree tree = new ArrayBinaryTree(arr);
        System.out.println("preorder");
        tree.preorder(); //1 2 4 5 3 6 7
        System.out.println("midorder");
        tree.midorder(); //4 2 5 1 6 3 7
        System.out.println("postorder");
        tree.postorder(); //4 5 2 6 7 3 1
    }
}
