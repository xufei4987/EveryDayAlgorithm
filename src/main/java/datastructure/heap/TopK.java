package datastructure.heap;

import datastructure.tree2.printer.BinaryTrees;

/**
 * 利用堆解决topK问题
 * 时间复杂度：O（nlogk）
 */
public class TopK {
    public static void main(String[] args) {
        Integer[] arr = {10, 40, 30, 90, 20, 70, 55, 33, 22, 11};
        int k = 4;
        BinaryHeap<Integer> smallTopHeap = new BinaryHeap<>((e1, e2) -> e2 - e1);
        for (int i = 0; i < arr.length; i++) {
            if (i < k) {
                smallTopHeap.add(arr[i]);
            } else if (arr[i] > smallTopHeap.get()) {
                smallTopHeap.replace(arr[i]);
            }
        }
        BinaryTrees.println(smallTopHeap);
    }
}
