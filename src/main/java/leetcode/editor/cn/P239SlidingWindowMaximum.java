//给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
//
//
// 返回滑动窗口中的最大值。
//
//
//
// 进阶：
//
// 你能在线性时间复杂度内解决此题吗？
//
//
//
// 示例:
//
// 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
//输出: [3,3,5,5,6,7]
//解释:
//
//  滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10^5
// -10^4 <= nums[i] <= 10^4
// 1 <= k <= nums.length
//
// Related Topics 堆 Sliding Window

package leetcode.editor.cn;


import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class P239SlidingWindowMaximum {
    public static void main(String[] args) {
        Solution solution = new P239SlidingWindowMaximum().new Solution();
        int[] ints = solution.maxSlidingWindow(new int[]{-7,-8,7,5,7,1,6,0}, 4);
        System.out.println(Arrays.toString(ints));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 暴力解法优化
         *
         * @param nums
         * @param k
         * @return
         */
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k <= 0) return new int[0];
            if (k == 1) return nums;
            int[] maxes = new int[nums.length - k + 1];
            int maxIdx = 0;
            for (int i = 1; i <= k - 1; i++) {
                if(nums[maxIdx] <= nums[i]){
                    maxIdx = i;
                }
            }
            maxes[0] = nums[maxIdx];
            for (int l = 1; l < maxes.length; l++) {
                int r = l + k - 1;
                if(maxIdx < l){
                    maxIdx = l;
                    for (int i = l + 1; i <= r; i++) {
                        if(nums[maxIdx] <= nums[i]){
                            maxIdx = i;
                        }
                    }
                } else if(nums[maxIdx] < nums[r]){
                    maxIdx = r;
                }
                maxes[l] = nums[maxIdx];
            }
            return maxes;
        }
        /**
         * 利用双端队列
         *
         * @param nums
         * @param k
         * @return
         */
        public int[] maxSlidingWindow1(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k <= 0) return new int[0];
            if (k == 1) return nums;
            int[] maxes = new int[nums.length - k + 1];
            Deque<Integer> deque = new LinkedList<>();
            deque.offerLast(0);
            for (int i = 1; i < nums.length; i++) {
                while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]){
                    deque.pollLast();
                }
                deque.offerLast(i);
                int w = i - k + 1;
                if(w < 0) continue;
                //检查队列中最大值的合法性
                if(deque.peekFirst() < w){
                    deque.pollFirst();
                }
                maxes[w] = nums[deque.peekFirst()];
            }
            return maxes;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
