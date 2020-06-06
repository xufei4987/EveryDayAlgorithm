package easy;

/**
 * Level: Easy Tags: [Binary Search]
 * Given a target number and an integer array A sorted in ascending order,
 * find the index i in A such that A[i] is closest to the given target.
 * Return -1 if there is no element in the array.
 * Example
 * Given [1, 2, 3] and target = 2, return 1.
 * Given [1, 4, 6] and target = 3, return 1.
 * Given [1, 4, 6] and target = 5, return 1 or 2.
 * Given [1, 3, 3, 4] and target = 2, return 0 or 1 or 2.
 * Note
 * There can be duplicate elements in the array, and we can return any of the indices with same value.
 */
public class ClosestNumber {

    public static int getClosestNumber(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int start = 0;
        int end = arr.length - 1;
        int mid = 0;
        //处理3个数及以上的情况
        while (start + 1 < end) {
            mid = (end - start) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (mid - 1 >= 0 && arr[mid - 1] <= target && target < arr[mid]) {
                return (target - arr[mid - 1]) < (arr[mid] - target) ? mid - 1 : mid;
            } else if (mid + 1 <= end && arr[mid] < target && target <= arr[mid + 1]) {
                return (target - arr[mid]) < (arr[mid + 1] - target) ? mid : mid + 1;
            } else if (arr[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        //处理2个数的情况
        return (target - arr[start]) < (arr[end] - target) ? start : end;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 3};
        System.out.println(getClosestNumber(arr1, 2));

        int[] arr2 = new int[]{1, 4, 6};
        System.out.println(getClosestNumber(arr2, 3));

        int[] arr3 = new int[]{1, 4, 6};
        System.out.println(getClosestNumber(arr3, 5));

        int[] arr4 = new int[]{1, 3, 3, 4};
        System.out.println(getClosestNumber(arr4, 2));
    }

}
