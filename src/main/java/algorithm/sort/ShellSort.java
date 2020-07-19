package algorithm.sort;

import java.util.Arrays;

/**
 * 希尔排序
 * 时间复杂度为O(nlogn)
 * gap由大到小  使数据先局部有序  再整体有序
 *
 */
public class ShellSort {
    public void sortBySwap(int[] arr) {
        //计算增量gap
        for(int gap = arr.length/2; gap > 0; gap = gap / 2){
            //处理分组后的数据   这个循环的含义是对分组后的每一个数据都要进行交换处理，保证分组后是有序的
            for (int i = gap; i < arr.length; i++){
                //通过交换的方式使局部有序
                for (int j = i - gap; j >= 0; j = j - gap){
                    //使用交换法7162ms
                    if(arr[j] > arr[j+gap]){
                        swap(arr,j,j+gap);
                    }
                }
            }
        }
    }

    public void sortByMove(int[] arr) {
        //计算增量gap
        for(int gap = arr.length/2; gap > 0; gap = gap / 2){
            //处理分组后的数据   这个循环的含义是对分组后的每一个数据都要进行交换处理，保证分组后是有序的
            for (int i = gap; i < arr.length; i++){
                //通过位移的方式使局部有序 14ms
                int j = i;
                int tmp = arr[j];
                while (j - gap >= 0 && tmp < arr[j - gap]){
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = tmp;
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
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        ShellSort shellSort = new ShellSort();
//        shellSort.sortByMove(arr);
//        System.out.println(Arrays.toString(arr));
        ShellSort shellSort = new ShellSort();
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++){
            arr[i] = (int) (Math.random()*8000000);
        }
        long start = System.currentTimeMillis();
        System.out.println("开始排序...");
        shellSort.sortByMove(arr);
        long end = System.currentTimeMillis();
        System.out.println("排序消耗的时间是：" + (end - start) + "ms");
    }
}
