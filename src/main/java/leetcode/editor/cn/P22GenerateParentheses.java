//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
//
//
//
// 示例：
//
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
//
// Related Topics 字符串 回溯算法


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class P22GenerateParentheses {
    public static void main(String[] args) {
        Solution solution = new P22GenerateParentheses().new Solution();
        // TO TEST
        System.out.println(solution.generateParenthesis(3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 利用dfs枚举出符合条件的情况：
         * 通过观察'('的选择条件是：'('的剩余数量大于0，')'的选择条件是 >0且!=左括号的数量
         * @param n
         * @return
         */
        public List<String> generateParenthesis(int n) {
            List<String> list = new ArrayList<>();
            if (n < 0) return list;
            char[] cs = new char[n * 2];
            dfs(0,cs,n,n,list);
            return list;
        }

        /**
         *
         * @param idx dfs层数
         * @param cs 存储中间结果的字符数组
         * @param remainLeft 左括号的剩余数量
         * @param remainRight 右括号的剩余数量
         * @param list 结果集
         */
        private void dfs(int idx, char[] cs, int remainLeft, int remainRight, List<String> list) {
            if(idx == cs.length){
                list.add(new String(cs));
                return;
            }
            if (remainLeft > 0){
                cs[idx] = '(';
                dfs(idx + 1,cs,remainLeft-1,remainRight,list);
            }
            if(remainRight > 0 && remainLeft != remainRight){
                cs[idx] = ')';
                dfs(idx + 1,cs,remainLeft,remainRight-1,list);
            }
        }

        /**
         * 利用dfs枚举出所有情况，再对其进行校验，选出合法的
         * @param n
         * @return
         */
        public List<String> generateParenthesis1(int n) {
            char[] cs = new char[n * 2];
            List<String> list = new ArrayList<>();
            generate(cs, 0, list);
            return list;
        }

        private void generate(char[] cs, int idx, List<String> list) {
            if(idx == cs.length){
                if(isValid(cs)){
                    list.add(String.valueOf(cs));
                }
                return;
            }
            cs[idx] = '(';
            generate(cs,idx+1,list);
            cs[idx] = ')';
            generate(cs,idx+1,list);
        }

        private boolean isValid(char[] cs) {
            int blance = 0;
            for (int i = 0; i < cs.length; i++) {
                if (cs[i] == '(') {
                    blance++;
                } else {
                    blance--;
                }
                if (blance < 0) return false;
            }
            return blance == 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
