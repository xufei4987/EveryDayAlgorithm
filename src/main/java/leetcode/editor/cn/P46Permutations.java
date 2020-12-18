//给定一个 没有重复 数字的序列，返回其所有可能的全排列。
//
// 示例:
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//]
// Related Topics 回溯算法

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class P46Permutations {
    public static void main(String[] args) {
        Solution solution = new P46Permutations().new Solution();
        System.out.println(solution.permute(new int[]{1, 2, 3}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> permute(int[] nums) {
            if (nums == null) return null;
            List<List<Integer>> result = new ArrayList<>();
            if (nums.length == 0) return result;
            List<Integer> ints = new ArrayList<>();
            dfs(0, nums, ints, result);

            return result;
        }

        private void dfs(int idx, int[] nums, List<Integer> ints, List<List<Integer>> result) {
            if (nums.length == idx) {
                result.add(new ArrayList<>(ints));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                if (ints.contains(nums[i])) continue;
                ints.add(nums[i]);
                dfs(idx+1,nums,ints,result);
                ints.remove(ints.size() - 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
