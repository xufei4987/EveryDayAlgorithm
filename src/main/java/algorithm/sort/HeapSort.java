package algorithm.sort;

import java.util.Arrays;

/**
 * 堆排序
 * 时间复杂度O(nlogn)
 * 步骤：
 * 1、首先需要构建大顶堆（升序）arr[n] >= arr[2n+1]&&arr[n] >= arr[2n+2] （或者 小顶堆（降序）arr[n] <= arr[2n+1]&&arr[n] <= arr[2n+2]）
 * 2、将堆顶元素与堆尾元素交换，将最大的元素沉到数组的末尾
 * 3、对剩余的元素继续构建大顶堆，再将堆顶元素与堆尾元素交换，反复执行调整+交换，直到有序
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {6, 3, 7, 9, 5, 33, 22, 11};
        HeapSort heapSort = new HeapSort();
        heapSort.sort(arr);
        System.out.println(Arrays.toString(arr));
//        HeapSort heapSort = new HeapSort();
//        int[] arr = new int[800000];
//        for (int i = 0; i < 800000; i++) {
//            arr[i] = (int) (Math.random() * 8000000);
//        }
//        long start = System.currentTimeMillis();
//        System.out.println("开始排序...");
//        heapSort.sort(arr);
//        long end = System.currentTimeMillis();
//        System.out.println("排序消耗的时间是：" + (end - start) + "ms");
    }

    public void sort(int[] arr) {
        //构建一个大顶堆，从下到上，从右到左调整
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        //将堆顶元素与数组末尾的进行交换，再从堆顶进行调整(因为前面已经整体调整过，所以不需要再从最后一个非叶子节点开始调整了)
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            adjustHeap(arr, 0, i);
        }
    }

    /**
     * @param arr    构建大顶堆的数组
     * @param i      需要构建大顶堆的非叶子节点下标
     * @param length 需要构建大顶堆用到的数组的长度
     */
    private void adjustHeap(int[] arr, int i, int length) {
        //记录arr[i]的值
        int tmp = arr[i];
        for (int j = 2 * i + 1; j < length; j = 2 * j + 1) {
            //找出左右叶子节点中较大的节点
            if (j + 1 < length && arr[j] < arr[j + 1]) {
                j++;
            }
            if (arr[j] > tmp) {
                arr[i] = arr[j];
                i = j;//记录非叶子节点需要被移动到的位置
            } else {
                break;
            }
        }
        arr[i] = tmp;//最后将该值赋值给最后确定的位置 完成交换
    }

    private void swap(int[] arr, int i, int j) {
        if (i == j || arr[i] == arr[j]) {
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
