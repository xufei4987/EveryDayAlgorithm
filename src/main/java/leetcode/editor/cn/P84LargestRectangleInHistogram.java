//给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。 
//
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。 
//
// 
//
// 
//
// 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。 
//
// 
//
// 
//
// 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。 
//
// 
//
// 示例: 
//
// 输入: [2,1,5,6,2,3]
//输出: 10 
// Related Topics 栈 数组


package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class P84LargestRectangleInHistogram {
    public static void main(String[] args) {
        Solution solution = new P84LargestRectangleInHistogram().new Solution();
        // TO TEST
        System.out.println(solution.largestRectangleArea1(new int[]{2, 1, 5, 6, 2, 3}));
        System.out.println(solution.largestRectangleArea(new int[]{0,2}));
        System.out.println(solution.largestRectangleArea(new int[]{2}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 暴力解法
         * @param heights
         * @return
         */
        public int largestRectangleArea(int[] heights) {
            if (heights == null || heights.length < 1) return 0;
            int max = 0;
            for (int i = 0; i < heights.length; i++) {
                int minHeight = heights[i];
                for (int j = i; j < heights.length; j++) {
                    minHeight = Math.min(minHeight, heights[j]);
                    max = Math.max(max, (j - i + 1) * minHeight);
                }
            }
            return max;
        }

        /**
         * 利用单调栈进行优化
         * @param heights
         * @return
         */
        public int largestRectangleArea1(int[] heights){
            int len = heights.length;
            if (len == 0) {
                return 0;
            }

            if (len == 1) {
                return heights[0];
            }

            int res = 0;

            int[] newHeights = new int[len + 2];
            newHeights[0] = 0;
            System.arraycopy(heights, 0, newHeights, 1, len);
            newHeights[len + 1] = 0;
            len += 2;
            heights = newHeights;

            Deque<Integer> stack = new ArrayDeque<>(len);
            // 先放入哨兵，在循环里就不用做非空判断
            stack.addLast(0);

            for (int i = 1; i < len; i++) {
                while (heights[i] < heights[stack.peekLast()]) {
                    int curHeight = heights[stack.pollLast()];
                    int curWidth = i - stack.peekLast() - 1;
                    res = Math.max(res, curHeight * curWidth);
                }
                stack.addLast(i);
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}