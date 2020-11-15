package datastructure.tree2.test;

import datastructure.tree2.bst.RBTree;
import datastructure.tree2.printer.BinaryTrees;

public class RBTTest {
    public static void main(String[] args) {
        Integer[] data = new Integer[]{83, 98, 13, 55, 46, 67, 94, 9, 30, 26, 2};
        RBTree<Integer> rbTree = new RBTree<>();
        for (int i = 0; i < data.length; i++) {
            rbTree.add(data[i]);
//            BinaryTrees.print(rbTree);
//            System.out.println();
//            System.out.println("-------------------------------------");
        }
        BinaryTrees.print(rbTree);
        System.out.println();
        System.out.println("-------------------------------------");
        for (int i = 0; i < data.length; i++){
            rbTree.remove(data[i]);
            BinaryTrees.print(rbTree);
            System.out.println();
            System.out.println("-------------------------------------");
        }
//        rbTree.add(13);
//        BinaryTrees.print(rbTree);
    }
}
