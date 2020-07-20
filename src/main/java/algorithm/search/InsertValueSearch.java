package algorithm.search;

import java.util.Arrays;

/**
 * 差值查找：
 * 当数据分布比较均匀时，用差值查找算法效率更高
 * 二分查找法计算中值  mid = (right+left)/2 = left + 0.5*(right - left)
 * 插值查找法计算中值 mid = left + (val - arr[left])*(right - left)/(arr[right] - arr[left])
 */
public class InsertValueSearch {
    private int count = 0;
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        System.out.println(Arrays.toString(arr));
        InsertValueSearch insertValueSearch = new InsertValueSearch();
        int idx = insertValueSearch.search(arr, 101);
        if(idx == -1){
            System.out.println("没有在集合中找到该值");
        }else {
            System.out.println("已在集合中找到该值，在集合中的下标为：" + idx);
        }
    }

    public int search(int[] arr, int val) {
        int left = 0;
        int right = arr.length - 1;
        return search0(arr, left, right, val);
    }

    private int search0(int[] arr, int left, int right, int val) {
        if ((left == right && arr[left] != val) || val > arr[right] || val < arr[left]) {
            return -1;
        }
        System.out.printf("第%d次查找\n", ++count);
        int mid = left + (val - arr[left]) * (right - left) / (arr[right] - arr[left]);
        if (val < arr[mid]) {
            return search0(arr, left, mid - 1, val);
        } else if (val > arr[mid]) {
            return search0(arr, mid + 1, right, val);
        } else {
            return mid;
        }
    }
}
