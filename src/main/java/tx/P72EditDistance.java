package tx;
//给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
//
// 你可以对一个单词进行如下三种操作：
//
//
// 插入一个字符
// 删除一个字符
// 替换一个字符
//
//
//
//
// 示例 1：
//
//
//输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
//
//
// 示例 2：
//
//
//输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
//
//
//
//
// 提示：
//
//
// 0 <= word1.length, word2.length <= 500
// word1 和 word2 由小写英文字母组成
//
// Related Topics 字符串 动态规划

public class P72EditDistance {
    public static void main(String[] args) {
        Solution solution = new P72EditDistance().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minDistance(String word1, String word2) {
            char[] cs1 = word1.toCharArray();
            char[] cs2 = word2.toCharArray();
            if (cs1.length == 0) return cs2.length;
            if (cs2.length == 0) return cs1.length;
            //dp[i][j]表示cs1[0,i)转化为cs2[0,j)的最少编辑次数
            int[][] dp = new int[cs1.length + 1][cs2.length + 1];
            for (int i = 1; i <= cs1.length; i++) {
                dp[i][0] = i;
            }
            for (int j = 1; j <= cs2.length; j++) {
                dp[0][j] = j;
            }
            for (int i = 1; i <= cs1.length; i++) {
                for (int j = 1; j <= cs2.length; j++) {
                    int left = dp[i][j-1] + 1;
                    int top = dp[i-1][j] + 1;
                    //区分当前字符是否相同的情况,不相同则替换一个字符
                    int leftTop = cs1[i-1] == cs2[j-1] ? dp[i-1][j-1] : dp[i-1][j-1] + 1;
                    dp[i][j] = Math.min(Math.min(left,top),leftTop);
                }
            }
            return dp[cs1.length][cs2.length];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
