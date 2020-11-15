package algorithm.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 二分查找法
 */
public class BinarySearch {
    private int count = 0;

    public int search0(int[] arr, int val) {
        return search0(arr, 0, arr.length - 1, val);
    }

    public List<Integer> search1(int[] arr, int val) {
        return search1(arr, 0, arr.length - 1, val);
    }

    /**
     * 非递归方式进行二分查找
     *
     * @param arr
     * @param target
     * @return 找到返回索引，没找到返回-1
     */
    private int search(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (target == arr[mid]) {
                return mid;
            } else if (target > arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    //找到目标值并返回下标
    private int search0(int[] arr, int left, int right, int val) {
        System.out.printf("第%d次查找\n", ++count);
        if (left == right && arr[left] != val) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (val < arr[mid]) {
            return search0(arr, left, mid - 1, val);
        } else if (val > arr[mid]) {
            return search0(arr, mid + 1, right, val);
        } else {
            return mid;
        }
    }

    //找到所有的下标值，并返回下标的集合，如果没有则返回一个空集合
    private List<Integer> search1(int[] arr, int left, int right, int val) {
        if (left == right && arr[left] != val) {
            return Collections.EMPTY_LIST;
        }
        System.out.printf("第%d次查找\n", ++count);
        int mid = (left + right) / 2;
        if (val < arr[mid]) {
            return search1(arr, left, mid, val);
        } else if (val > arr[mid]) {
            return search1(arr, mid + 1, right, val);
        } else {
            //找到目标值后，向左右扫描并找到相同的值
            List<Integer> list = new ArrayList<>();
            int tmp = mid - 1;
            while (arr[tmp] == arr[mid] && tmp >= 0) {
                list.add(tmp);
                tmp--;
            }
            list.add(mid);
            tmp = mid + 1;
            while (arr[tmp] == arr[mid] && tmp < arr.length) {
                list.add(tmp);
                tmp++;
            }
            return list;
        }
    }

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        int[] arr = {1, 8, 10, 89, 1000, 2345};
        int idx = binarySearch.search(arr, 1);
        if (idx == -1) {
            System.out.println("没有在集合中找到该值");
        } else {
            System.out.println("已在集合中找到该值，在集合中的下标为：" + idx);
        }
//        int[] arr = {1, 8, 89, 89, 89, 89, 89, 1000, 2345};
//        List<Integer> list = binarySearch.search1(arr, 89);
//        System.out.println(list);
    }
}
