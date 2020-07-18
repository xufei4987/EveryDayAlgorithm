package algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序:
 * 时间复杂度 O(n^2)
 * 先把第一个数作为一个有序的列表，剩余数作为待排序列表
 * 从第二个数开始往有序列表中插入
 * 插入时需要找到适当的位置进行插入，则意味则在有序列表中比待插入数大的数需要后移
 * arr={101,34,119,1,-1,89}
 * (101),34,119,1,-1,89
 * (34,101),119,1,-1,89
 * (34,101,119),1,-1,89
 * (1,34,101,119),-1,89
 * (-1,1,34,101,119),89
 * (-1,1,34,89,101,119)
 */
public class InsertSort {

    public void sort(int[] arr){
        //需要插入arr.length次
        int insertIdx;
        int insertVal;
        for(int i = 1; i < arr.length; i++){
            insertIdx = i - 1;
            insertVal = arr[i];
            //有序列表中比待插入数大的数需要后移
            while (insertIdx >= 0 && arr[insertIdx] < insertVal){
                arr[insertIdx + 1] = arr[insertIdx];
                insertIdx --;
            }
            //找到插入的位置进行插入
            arr[insertIdx + 1] = insertVal;
        }
    }

    public static void main(String[] args) {
//        InsertSort insertSort = new InsertSort();
//        int[] arr = {101,34,119,1,-1,89};
//        insertSort.sort(arr);
//        System.out.println(Arrays.toString(arr));
        InsertSort insertSort = new InsertSort();
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++){
            arr[i] = (int) (Math.random()*8000000);
        }
        long start = System.currentTimeMillis();
        System.out.println("开始排序...");
        insertSort.sort(arr);
        long end = System.currentTimeMillis();
        System.out.println("排序消耗的时间是：" + (end - start) + "ms");
        //大概是559ms
    }
}
