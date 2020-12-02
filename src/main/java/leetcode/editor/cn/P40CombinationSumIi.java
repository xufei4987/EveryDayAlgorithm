//给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。 
//
// candidates 中的每个数字在每个组合中只能使用一次。 
//
// 说明： 
//
// 
// 所有数字（包括目标数）都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1: 
//
// 输入: candidates = [10,1,2,7,6,1,5], target = 8,
//所求解集为:
//[
//  [1, 7],
//  [1, 2, 5],
//  [2, 6],
//  [1, 1, 6]
//]
// 
//
// 示例 2: 
//
// 输入: candidates = [2,5,2,1,2], target = 5,
//所求解集为:
//[
//  [1,2,2],
//  [5]
//] 
// Related Topics 数组 回溯算法


package leetcode.editor.cn;

import java.util.*;

//Java：组合总和 II
public class P40CombinationSumIi{
    public static void main(String[] args) {
        Solution solution = new P40CombinationSumIi().new Solution();
        // TO TEST
        System.out.println(solution.combinationSum(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> results = new ArrayList<>();
            List<Integer> result = new ArrayList<>();
            Arrays.sort(candidates);
            dfs(candidates,target,results,result,0);
            return results;
        }

        private void dfs(int[] candidates, int target, List<List<Integer>> results, List<Integer> result, int idx) {
            if (target < 0){
                System.out.println(result);
                return;
            }
            if(target == 0){
                System.out.println(result);
                results.add(new ArrayList<>(result));
                return;
            }
            for (int i = idx; i < candidates.length; i++){
                if(target < candidates[i]){
                    break;
                }
                if(i > idx && candidates[i] == candidates[i-1]){
                    continue;
                }
                result.add(candidates[i]);
                dfs(candidates,target - candidates[i], results, result, i + 1);
                result.remove(result.size() - 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}