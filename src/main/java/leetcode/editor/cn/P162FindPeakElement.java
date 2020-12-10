//峰值元素是指其值大于左右相邻值的元素。 
//
// 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。 
//
// 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。 
//
// 你可以假设 nums[-1] = nums[n] = -∞。 
//
// 示例 1: 
//
// 输入: nums = [1,2,3,1]
//输出: 2
//解释: 3 是峰值元素，你的函数应该返回其索引 2。 
//
// 示例 2: 
//
// 输入: nums = [1,2,1,3,5,6,4]
//输出: 1 或 5 
//解释: 你的函数可以返回索引 1，其峰值元素为 2；
//     或者返回索引 5， 其峰值元素为 6。
// 
//
// 说明: 
//
// 你的解法应该是 O(logN) 时间复杂度的。 
// Related Topics 数组 二分查找


package leetcode.editor.cn;

//Java：寻找峰值
public class P162FindPeakElement {
    public static void main(String[] args) {
        Solution solution = new P162FindPeakElement().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findPeakElement(int[] nums) {
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] > nums[i + 1])
                    return i;
            }
            return nums.length - 1;
        }

        public int findPeakElement1(int[] nums) {
            if (nums == null || nums.length == 0) return Integer.MIN_VALUE;
            if (nums.length == 1) return 0;
            if (nums.length == 2) return nums[0] > nums[1] ? 0 : 1;
            for (int i = 0; i < nums.length; i++) {
                if(i == 0){
                    if (Integer.MIN_VALUE < nums[i] && nums[i] > nums[i+1]){
                        return i;
                    }
                }else if (i == nums.length - 1){
                    if (nums[i - 1] < nums[i] && nums[i] > Integer.MIN_VALUE){
                        return i;
                    }
                }else {
                    if (nums[i - 1] < nums[i] && nums[i] > nums[i+1]){
                        return i;
                    }
                }

            }
            return nums.length-1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}