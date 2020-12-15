//给定一个非负整数数组，你最初位于数组的第一个位置。 
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 你的目标是使用最少的跳跃次数到达数组的最后一个位置。 
//
// 示例: 
//
// 输入: [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。
//     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
// 
//
// 说明: 
//
// 假设你总是可以到达数组的最后一个位置。 
// Related Topics 贪心算法 数组


package leetcode.editor.cn;

//Java：跳跃游戏 II
public class P45JumpGameIi {
    public static void main(String[] args) {
        Solution solution = new P45JumpGameIi().new Solution();
        // TO TEST
        System.out.println(solution.jump(new int[]{2, 3, 1, 1, 4}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 利用贪心算法求解，每一步都选择下一步最大的距离
         *
         * @param nums
         * @return
         */
        public int jump(int[] nums) {
            if (nums == null || nums.length == 0) return 0;
            int len = nums.length;
            int step = 0;
            int maxPosition = 0;
            int end = 0;
            for (int i = 0; i < len - 1; i++) {
                maxPosition = Math.max(maxPosition, i + nums[i]);
                if (i == end) {
                    step++;
                    end = maxPosition;
                }
            }
            return step;
        }

        public int jump1(int[] nums) {
            if (nums == null || nums.length == 0) return 0;
            int len = nums.length;
            //跳跃到i位置的最短距离
            int[] dp = new int[len];
            for (int i = 1; i < len; i++) {
                int minCount = Integer.MAX_VALUE;
                for (int j = i - 1; j >= 0; j--) {
                    if (i - j <= nums[j]) {
                        minCount = Math.min(minCount, dp[j]);
                    }
                }
                dp[i] = minCount + 1;
            }
            return dp[len - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}