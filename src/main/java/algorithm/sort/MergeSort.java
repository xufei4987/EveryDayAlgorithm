package algorithm.sort;

/**
 * 归并排序
 * 时间复杂度为O(nlogn)
 * 采用分治法 分解成小的问题并递归求解 需要用到额外的空间
 */
public class MergeSort {

    public void sort(int[] arr) {
        int[] tmp = new int[arr.length];
        int left = 0;
        int right = arr.length - 1;
        sort0(arr, left, right, tmp);
    }

    private void sort0(int[] arr, int left, int right, int[] tmp) {
        if (left < right) {
            int mid = (left + right) / 2;
            sort0(arr, left, mid, tmp);
            sort0(arr, mid + 1, right, tmp);
            merge(arr, left, mid, right, tmp);
        }
    }

    private void merge(int[] arr, int left, int mid, int right, int[] tmp) {
        int l = left;
        int r = mid + 1;
        int t = 0;//tmp[]的索引
        while (l <= mid && r <= right) {
            //将较小的数填充到tmp[]
            if (arr[l] < arr[r]) {
                tmp[t++] = arr[l++];
            } else {
                tmp[t++] = arr[r++];
            }
        }
        //将剩余的数填充到tmp[]
        while (l <= mid) {
            tmp[t++] = arr[l++];
        }
        while (r <= right) {
            tmp[t++] = arr[r++];
        }
        int tmpLeft = left;
        t = 0;
        //将本次的归并结果拷贝到原数组中
        while (tmpLeft <= right) {
            arr[tmpLeft++] = tmp[t++];
        }
    }

    public static void main(String[] args) {
//        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
//        MergeSort mergeSort = new MergeSort();
//        mergeSort.sort(arr);
//        System.out.println(Arrays.toString(arr));
        MergeSort mergeSort = new MergeSort();
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long start = System.currentTimeMillis();
        System.out.println("开始排序...");
        mergeSort.sort(arr);
        long end = System.currentTimeMillis();
        System.out.println("排序消耗的时间是：" + (end - start) + "ms");
    }
}
