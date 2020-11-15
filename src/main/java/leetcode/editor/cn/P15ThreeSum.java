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


package leetcode.editor.cn;

import java.util.*;


public class P15ThreeSum{
    public static void main(String[] args) {
        Solution solution = new P15ThreeSum().new Solution();
        // TO TEST
        int[] nums = {-1, -1, 0, 1};
        System.out.println(solution.threeSum(nums));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            if(nums == null || nums.length < 3) return Collections.emptyList();
            Arrays.sort(nums);
            if(nums[0] > 0 || nums[nums.length - 1] < 0) return Collections.emptyList();
            List<List<Integer>> lists = new ArrayList();
            for (int one = 0; one < nums.length; one++) {
                if (one > 0 && nums[one] == nums[one - 1]) continue;
                int three = nums.length - 1;
                for (int two = one + 1; two < nums.length; two++) {
                    if (two > one + 1 && nums[two] == nums[two - 1]) continue;
                    while (two < three && nums[one] + nums[two] + nums[three] > 0){
                        three--;
                    }
                    if(two == three) break;
                    if(nums[one] + nums[two] + nums[three] == 0){
                        lists.add(Arrays.asList(nums[one],nums[two],nums[three]));
                    }
                }
            }
            return lists;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}