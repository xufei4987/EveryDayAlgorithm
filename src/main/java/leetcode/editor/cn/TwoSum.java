//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
// 示例: 
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
// 
// Related Topics 数组 哈希表

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        Solution solution = new TwoSum().new Solution();
        int[] ints = solution.twoSum2(new int[]{2, 7, 11, 15}, 26);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
    class Solution {
        //数据解法
        public int[] twoSum(int[] nums, int target) {
            for(int i = 0; i < nums.length - 1; i++){
                for(int j = i + 1; j < nums.length; j++){
                    if(nums[i] + nums[j] == target){
                        return new int[]{i,j};
                    }
                }
            }
            return null;
        }
        //hash解法
        public int[] twoSum2(int[] nums, int target) {
            Map<Integer,Integer> map = new HashMap();
            for (int i = 0; i < nums.length; i++) {
                map.put(nums[i],i);
            }
            for (int j = 0; j < nums.length; j++) {
                int result = target - nums[j];
                if(map.containsKey(result) && map.get(result) != j){
                    return new int[]{j,map.get(result)};
                }
            }
            return null;
        }
    }

}