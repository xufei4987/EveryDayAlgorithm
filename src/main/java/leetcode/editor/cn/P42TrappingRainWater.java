//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
//
//
//
// 示例 1：
//
//
//
//
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
//
//
// 示例 2：
//
//
//输入：height = [4,2,0,3,2,5]
//输出：9
//
//
//
//
// 提示：
//
//
// n == height.length
// 0 <= n <= 3 * 104
// 0 <= height[i] <= 105
//
// Related Topics 栈 数组 双指针


package leetcode.editor.cn;
//Java：接雨水
public class P42TrappingRainWater{
    public static void main(String[] args) {
        Solution solution = new P42TrappingRainWater().new Solution();
        // TO TEST
        System.out.println(solution.trap(new int[]{4,2,0,3,2,5}));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 暴力解法，当前位置能够收集的雨水高度 = 两边最大高度中较低高度 - 当前位置高度
         * @param height
         * @return
         */
        public int trap(int[] height) {
            if (height == null || height.length < 3) return 0;
            int sum = 0;
            for (int i = 1; i < height.length - 1; i++) {
                int leftMax = -1;
                for (int l = 0; l < i; l++) {
                    leftMax = Math.max(leftMax,height[l]);
                }
                int rightMax = -1;
                for (int r = i+1; r < height.length; r++){
                    rightMax = Math.max(rightMax,height[r]);
                }
                int result = Math.min(leftMax,rightMax) - height[i];
                sum += result > 0 ? result : 0;
            }
            return sum;
        }
        /**
         * 暴力解法优化，当前位置能够收集的雨水高度 = 两边最大高度中较低高度 - 当前位置高度
         * 两边最大高度用动态规划进行求解：
         * maxLeft[i] = math.max(maxLeft[i-1],height[i-1])
         * maxRight[i] = math.max(maxRight[i+1],height[i+1])
         * @param height
         * @return
         */
        public int trap1(int[] height) {
            if (height == null || height.length < 3) return 0;
            int[] maxLeft = new int[height.length];
            maxLeft[0] = 0;
            for (int i = 1; i < maxLeft.length; i++) {
                maxLeft[i] = Math.max(maxLeft[i-1],height[i-1]);
            }
            int[] maxRight = new int[height.length];
            maxRight[height.length-1] = 0;
            for (int i = height.length-2; i >= 0 ; i--) {
                maxRight[i] = Math.max(maxRight[i+1],height[i+1]);
            }
            int sum = 0;
            for (int i = 1; i < height.length - 1; i++) {
                int result = Math.min(maxLeft[i],maxRight[i]) - height[i];
                sum += result > 0 ? result : 0;
            }
            return sum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
