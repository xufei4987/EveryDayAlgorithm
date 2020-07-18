package algorithm.sort;

/**
 * 冒泡排序：
 * 时间复杂度 O(n^2)
 * 需要进行n-1次排序
 * 第i次排序需要进行n-1-i次交换
 */
public class BubbleSort {
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void sort(int[] arr) {
        if(arr == null || arr.length == 1){
            return;
        }
        boolean flag = false;
        for(int i = 0; i < arr.length - 1; i++){
            flag = false;
            for (int j = 0; j < arr.length - 1 - i; j++){
                if(arr[j] > arr[j+1]){
                    swap(arr,j,j+1);
                    flag = true;
                }
            }
            //没有进行数据的交换，证明已经有序
            if(!flag){
                break;
            }
        }
    }

    public static void main(String[] args) {
//        int[] arr = {3, 9, -1, 10, -2};
//        BubbleSort bubbleSort = new BubbleSort();
//        bubbleSort.sort(arr);
//        System.out.println(Arrays.toString(arr));
        BubbleSort bubbleSort = new BubbleSort();
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++){
            arr[i] = (int) (Math.random()*8000000);
        }
        long start = System.currentTimeMillis();
        System.out.println("开始排序...");
        bubbleSort.sort(arr);
        long end = System.currentTimeMillis();
        System.out.println("排序消耗的时间是：" + (end - start) + "ms");
        //大概是10m
    }
}
