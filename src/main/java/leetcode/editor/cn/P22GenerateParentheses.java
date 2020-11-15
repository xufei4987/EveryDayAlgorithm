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
        public List<String> generateParenthesis(int n) {
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