package datastructure.tree2.bst;

import datastructure.tree2.printer.BinaryTrees;

public class Main {
    public static void main(String[] args) {
        Integer[] data = new Integer[]{7, 4, 9, 2, 5, 8, 11, 3, 1};
        BinarySearchTree<Integer> bst1 = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst1.add(data[i]);
        }
        BinaryTrees.print(bst1);
        System.out.println();
        System.out.println(bst1.toString());
        System.out.println(bst1.height());
        System.out.println(bst1.height2());
        System.out.println(bst1.isComplete());
        bst1.inverseTree();
        System.out.println();
        BinaryTrees.print(bst1);
//        bst1.levelOrder();
//        System.out.println();
//        bst1.preorder(new Visitor<Integer>() {
//            @Override
//            public boolean visit(Integer integer) {
//                System.out.println("==" + integer + "==");
//                if (2 == integer) {
//                    return true;
//                }
//                return false;
//            }
//        });
//        System.out.println();
//        Car[] cars = new Car[]{
//                new Car(7000),
//                new Car(4000),
//                new Car(9000),
//                new Car(2000),
//                new Car(5000),
//                new Car(8000),
//                new Car(11000),
//                new Car(3000)
//        };
//        BinarySearchTree<Car> bst2 = new BinarySearchTree<>(Comparator.comparingInt(o -> ((Car) o).getFee()));
//        for (int i = 0; i < cars.length; i++) {
//            bst2.add(cars[i]);
//        }
//        BinaryTrees.print(bst2);
//
//        System.out.println();
//
//        bst2.inorder();


    }

}
