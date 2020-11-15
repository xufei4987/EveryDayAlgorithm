package datastructure.tree2.test;

import datastructure.tree2.bst.AVLTree;
import datastructure.tree2.printer.BinaryTrees;

public class AVLTest {
    public static void main(String[] args) {
        Integer[] data = new Integer[]{83, 98, 13, 55, 46, 67, 94, 9, 30, 26, 2};
        AVLTree<Integer> avlTree = new AVLTree<>();
        for (int i = 0; i < data.length; i++) {
            avlTree.add(data[i]);
//            BinaryTrees.print(avlTree);
//            System.out.println();
//            System.out.println("-------------------------------------");
        }
//        avlTree.add(13);
        BinaryTrees.print(avlTree);
        System.out.println();
        System.out.println("-------------------------------------");
        avlTree.remove(67);
        avlTree.remove(83);
        avlTree.remove(98);
        avlTree.remove(2);

        BinaryTrees.print(avlTree);
    }

}
