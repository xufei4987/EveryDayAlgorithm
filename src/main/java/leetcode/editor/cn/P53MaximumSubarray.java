//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。 
//
// 示例: 
//
// 输入: [-2,1,-3,4,-1,2,1,-5,4]
//输出: 6
//解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
// 
//
// 进阶: 
//
// 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。 
// Related Topics 数组 分治算法 动态规划


package leetcode.editor.cn;

//Java：最大子序和
public class P53MaximumSubarray {
    public static void main(String[] args) {
        Solution solution = new P53MaximumSubarray().new Solution();
        // TO TEST
        int[] arr = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(solution.maxSubArray1(arr));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * dp
         * @param nums
         * @return
         */
        public int maxSubArray1(int[] nums) {
            if (nums.length == 1) return nums[0];
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            int max = dp[0];
            for (int i = 1; i < nums.length; i++) {
                dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
                max = Math.max(dp[i],max);
            }
            return max;
        }

        /**
         * 分治策略
         * @param nums
         * @return
         */
        public int maxSubArray(int[] nums) {
            int left = 0;
            int right = nums.length;
            return maxSubArray(nums,left,right);
        }

        private int maxSubArray(int[] nums, int left, int right) {
            if(left == right - 1) return nums[left];
            int mid = (left + right) / 2;
            int maxLeft = maxSubArray(nums,left,mid);
            int maxRight = maxSubArray(nums,mid,right);
            int midLeftMax = Integer.MIN_VALUE;
            int midRightMax = Integer.MIN_VALUE;
            int midLeftSum = 0;
            int midRightSum = 0;
            for (int l = mid - 1; l >= left; l--){
                midLeftSum += nums[l];
                midLeftMax = Math.max(midLeftMax,midLeftSum);
            }
            for (int r = mid; r < right; r++){
                midRightSum += nums[r];
                midRightMax = Math.max(midRightMax,midRightSum);
            }
            return Math.max(Math.max(maxLeft,maxRight),midLeftMax+midRightMax);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}