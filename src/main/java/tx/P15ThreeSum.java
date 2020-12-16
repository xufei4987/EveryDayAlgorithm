//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复
//的三元组。
//
// 注意：答案中不可以包含重复的三元组。
//
//
//
// 示例：
//
// 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
//
//满足要求的三元组集合为：
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
//
// Related Topics 数组 双指针


package tx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class P15ThreeSum {
    public static void main(String[] args) {
        Solution solution = new P15ThreeSum().new Solution();
        // TO TEST
        int[] nums = {-1, -1, 0, 1};
        System.out.println(solution.threeSum(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 解题思路：定义3指针i、l、r，分别对应三个数，排序后逐渐向中间靠拢求解,需要注意跳过重复的值
         *
         * @param nums
         * @return
         */
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> lists = new ArrayList();
            if (nums == null || nums.length < 3) return lists;
            Arrays.sort(nums);
            if (nums[0] > 0 || nums[nums.length - 1] < 0) return lists;

            for (int i = 0; i <= nums.length - 3; i++) {
                //去重
                if (i != 0 && nums[i - 1] == nums[i]) continue;
                int l = i + 1;
                int r = nums.length - 1;
                while (l < r) {
                    if (nums[i] + nums[l] + nums[r] == 0){
                        lists.add(Arrays.asList(nums[i] , nums[l] , nums[r]));
                        //去重
                        while (l < r && nums[l]== nums[l+1]){
                            l++;
                        }
                        while (l < r && nums[r]== nums[r-1]){
                            r--;
                        }
                        l++;
                        r--;
                    } else if (nums[i] + nums[l] + nums[r] > 0){
                        r--;
                    } else {
                        l++;
                    }
                }
            }
            return lists;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
