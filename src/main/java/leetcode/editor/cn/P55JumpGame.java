//给定一个非负整数数组，你最初位于数组的第一个位置。 
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 判断你是否能够到达最后一个位置。 
//
// 示例 1: 
//
// 输入: [2,3,1,1,4]
//输出: true
//解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
// 
//
// 示例 2: 
//
// 输入: [3,2,1,0,4]
//输出: false
//解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
// 
// Related Topics 贪心算法 数组


package leetcode.editor.cn;

//Java：跳跃游戏
public class P55JumpGame {
    public static void main(String[] args) {
        Solution solution = new P55JumpGame().new Solution();
        // TO TEST
        System.out.println(solution.canJump(new int[]{2,3,1,1,4}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public boolean canJump(int[] nums) {
            if (nums == null || nums.length == 0) return false;
            if (nums.length == 1) return true;
            int maxIdx = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                if (i <= maxIdx){
                    maxIdx = Math.max(maxIdx,nums[i]+i);
                    if (maxIdx >= nums.length -1){
                        return true;
                    }
                }
            }
            return false;
        }

//        public boolean canJump(int[] nums) {
//            if (nums == null || nums.length == 0) return false;
//            return canJump(nums,0);
//        }
//
//        private boolean canJump(int[] nums, int i) {
//            if (i == nums.length - 1) return true;
//            if (i >= nums.length) return false;
//            if (nums[i] == 0) return false;
//            for (int j = nums[i]; j >= 1; j--) {
//                if(canJump(nums,i+j)) return true;
//            }
//            return false;
//        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}