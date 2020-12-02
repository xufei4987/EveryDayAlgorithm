//给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。 
//
// 示例 1: 
//
// 输入: "(()"
//输出: 2
//解释: 最长有效括号子串为 "()"
// 
//
// 示例 2: 
//
// 输入: ")()())"
//输出: 4
//解释: 最长有效括号子串为 "()()"
// 
// Related Topics 字符串 动态规划


package leetcode.editor.cn;

public class P32LongestValidParentheses {
    public static void main(String[] args) {
        Solution solution = new P32LongestValidParentheses().new Solution();
        // TO TEST
        System.out.println(solution.longestValidParentheses("()()))"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestValidParentheses(String s) {
            if (s == null || s.length() == 0) return 0;
            //dp[i]表示以下标为i字符结尾的连续有效括号的最大长度
            int[] dp = new int[s.length()];
            int max = 0;
            for (int i = 1; i < s.length(); i++) {
                if(s.charAt(i) == ')'){
                    if(s.charAt(i-1) == '('){
                        dp[i] = i > 1 ? dp[i-2] + 2 : 2;
                    } else if(i-dp[i-1] > 0 && s.charAt(i-dp[i-1]-1) == '('){
                        if(i-dp[i-1] >= 2){
                            dp[i] = dp[i-1] + 2 + dp[i-dp[i-1]-2];
                        } else {
                            dp[i] = dp[i-1] + 2;
                        }
                    }
                    max = Math.max(max,dp[i]);
                }
            }
            return max;
        }

        public int longestValidParentheses1(String s) {
            if (s == null || s.length() == 0) return 0;
            int left = 0, right = 0;
            int max = 0;
            //从左向右遍历：此时会漏掉“(()”这种情况
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    left++;
                } else if (s.charAt(i) == ')') {
                    right++;
                }
                if (left == right) {
                    max = Math.max(max, left * 2);
                } else if (left < right) {
                    left = right = 0;
                }
            }
            left = right = 0;
            //反向再遍历一遍即可覆盖所有情况
            for (int i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) == '(') {
                    left++;
                } else if (s.charAt(i) == ')') {
                    right++;
                }
                if (left == right) {
                    max = Math.max(max, left * 2);
                } else if (left > right) {
                    left = right = 0;
                }
            }
            return max;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}