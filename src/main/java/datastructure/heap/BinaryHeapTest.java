package datastructure.heap;

import datastructure.tree2.printer.BinaryTrees;

public class BinaryHeapTest {
    public static void main(String[] args) {
        test2();
    }

    private static void test2() {
        Integer[] arr = {10, 40, 30, 90, 20, 70, 55, 33, 22, 11};
        BinaryHeap<Integer> bigTopHeap = new BinaryHeap<>(arr);
        BinaryTrees.println(bigTopHeap);
        BinaryHeap<Integer> smallTopHeap = new BinaryHeap<>(arr, (e1, e2) -> e2 - e1);
        BinaryTrees.println(smallTopHeap);
    }

    private static void test1() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(68);
        heap.add(72);
        heap.add(43);
        heap.add(50);
        heap.add(38);
        heap.add(10);
        heap.add(90);
        heap.add(65);
        BinaryTrees.println(heap);
        heap.remove();
        BinaryTrees.println(heap);
        heap.remove();
        BinaryTrees.println(heap);
    }


}
