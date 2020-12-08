//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
//
// 示例:
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0]
//
// 说明:
//
//
// 必须在原数组上操作，不能拷贝额外的数组。
// 尽量减少操作次数。
//
// Related Topics 数组 双指针

package leetcode.editor.cn;
public class P283MoveZeroes {
    public static void main(String[] args) {
        Solution solution = new P283MoveZeroes().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 解题思路：
         * 将0挪动到数组的最后，换个想法，就是将非0的都挪动到前面即可
         * @param nums
         */
        public void moveZeroes(int[] nums) {
            if(nums == null) return;
            //cur代表非0元素需要挪动到的位置
            for (int i = 0,cur = 0; i < nums.length; i++) {
                if (nums[i] == 0) continue;
                if (cur != i){
                    nums[cur] = nums[i];
                    nums[i] = 0;
                }
                cur++;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
