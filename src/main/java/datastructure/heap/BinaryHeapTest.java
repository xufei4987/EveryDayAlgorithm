package datastructure.heap;

import datastructure.tree2.printer.BinaryTrees;

public class BinaryHeapTest {
    public static void main(String[] args) {
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
