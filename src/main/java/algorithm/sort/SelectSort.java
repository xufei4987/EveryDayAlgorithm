package algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序：
 * 时间复杂度O(n^2)
 * 需要进行n-1次排序
 * 第i次排序需要遍历n-i-1个数找到最小值
 */
public class SelectSort {
    public void sort(int[] arr) {
        if(arr == null || arr.length == 1){
            return;
        }
        int minIdx;
        for (int i = 0; i < arr.length - 1; i++){
            minIdx = i;
            for (int j = i + 1; j < arr.length; j++){
                if(arr[minIdx] > arr[j]){
                    minIdx = j;
                }
            }
            //minIdx没有发送变化则不需要交换
            if(minIdx != i){
                swap(arr,i,minIdx);
            }
        }

    }

    private void swap(int[] arr, int i, int j) {
        if(i == j || arr[i] == arr[j]){
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
//        int[] arr = {3, 9, -1, 10, -2};
//        SelectSort selectSort = new SelectSort();
//        selectSort.sort(arr);
//        System.out.println(Arrays.toString(arr));
        SelectSort selectSort = new SelectSort();
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++){
            arr[i] = (int) (Math.random()*8000000);
        }
        long start = System.currentTimeMillis();
        System.out.println("开始排序...");
        selectSort.sort(arr);
        long end = System.currentTimeMillis();
        System.out.println("排序消耗的时间是：" + (end - start) + "ms");
        //大概是3s
    }
}
