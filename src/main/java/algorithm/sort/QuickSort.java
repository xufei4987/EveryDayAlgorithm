package algorithm.sort;

import java.util.Arrays;

public class QuickSort {

    public void sort(int[] arr,int lIdx, int rIdx) {
        //递归的返回条件，一定要有
        if(lIdx >= rIdx){
            return;
        }
        int l = lIdx;
        int r = rIdx;
        int pivot = arr[(l + r) / 2];
        /**
         * 将比pivot大的值都放到pivot右边，比pivot小的都放到pivot左边
         */
        while (l < r){
            //找到大于等于pivot的值
            while (arr[l] < pivot){
                l ++;
            }
            //找到小于等于pivot的值
            while (arr[r] > pivot){
                r --;
            }
            if(l >= r){
                break;
            }
            swap(arr, l, r);
            //兼容处理  防止有与pivot相同的数造成死循环
            if(arr[l] == pivot){
                r--;
            }
            if(arr[r] == pivot){
                l++;
            }
        }
        //退出循环后l == r
        sort(arr,lIdx,l-1);
        sort(arr,l+1,rIdx);
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
//        int[] arr = {-9, 23,78, 0, 23 ,70,-567, 70};
//        QuickSort quickSort = new QuickSort();
//        quickSort.sort(arr,0,arr.length-1);
//        System.out.println(Arrays.toString(arr));
        QuickSort quickSort = new QuickSort();
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++){
            arr[i] = (int) (Math.random()*8000000);
        }
        long start = System.currentTimeMillis();
        System.out.println("开始排序...");
        quickSort.sort(arr,0,arr.length-1);
        long end = System.currentTimeMillis();
        System.out.println("排序消耗的时间是：" + (end - start) + "ms");
    }
}
