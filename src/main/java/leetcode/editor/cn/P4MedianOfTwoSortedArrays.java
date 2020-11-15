//给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
//
// 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
//
// 你可以假设 nums1 和 nums2 不会同时为空。
//
//
//
// 示例 1:
//
// nums1 = [1, 3]
//nums2 = [2]
//
//则中位数是 2.0
//
//
// 示例 2:
//
// nums1 = [1, 2]
//nums2 = [3, 4]
//
//则中位数是 (2 + 3)/2 = 2.5
//
// Related Topics 数组 二分查找 分治算法
package leetcode.editor.cn;

public class P4MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        Solution solution = new P4MedianOfTwoSortedArrays().new Solution();
        // TO TEST
        System.out.println(solution.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //整体思路 先归并 在选出中位数
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int m = nums1.length;
            int n = nums2.length;
            if (m == 0) {
                if (n % 2 == 0) {
                    return (nums2[n / 2 - 1] + nums2[n / 2]) / 2.0;
                } else {
                    return nums2[n / 2];
                }
            }
            if (n == 0) {
                if (m % 2 == 0) {
                    return (nums1[m / 2 - 1] + nums1[m / 2]) / 2.0;
                } else {
                    return nums1[m / 2];
                }
            }
            int[] arr = new int[m + n];
            int count = 0;
            int i = 0, j = 0;
            while (count != m + n) {
                if (i == m){
                    while (j != n){
                        arr[count++] = nums2[j++];
                    }
                    continue;
                }
                if(j == n){
                    while (i != m){
                        arr[count++] = nums1[i++];
                    }
                    continue;
                }
                if(nums1[i] < nums2[j]){
                    arr[count++] = nums1[i++];
                }else {
                    arr[count++] = nums2[j++];
                }
            }
            if (count % 2 == 0) {
                return (arr[count / 2 - 1] + arr[count / 2]) / 2.0;
            } else {
                return arr[count / 2];
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}
