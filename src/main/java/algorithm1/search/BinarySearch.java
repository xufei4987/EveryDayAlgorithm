package algorithm1.search;

public class BinarySearch {
    public static int indexOf(Integer[] arr, int value) {
        if (arr == null || arr.length == 0) return -1;
        int begin = 0;
        int end = arr.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (value < arr[mid]) {
                end = mid;
            } else if (value > arr[mid]) {
                begin = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 查询元素value在有序数组arr中的插入位置，插入规则为插入到第一个比value大的元素的位置，如果value为最大，插入到末尾
     * @param arr
     * @param value
     * @return
     */
    public static int searchInsertIndex(Integer[] arr, int value){
        if (arr == null || arr.length == 0) return -1;
        int begin = 0;
        int end = arr.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (value < arr[mid]) {
                end = mid;
            } else {
                begin = mid + 1;
            }
        }
        return begin;
    }
}
