package leetcode.editor.cn;
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
            if (word1 == null || word2 == null) return 0;
            char[] s1 = word1.toCharArray();
            char[] s2 = word2.toCharArray();
            //dp[i][j]代表s1[0,i)转化为s2[0,j)的最少操作数
            int[][] dp = new int[s1.length + 1][s2.length + 1];
            //初始化
            dp[0][0] = 0;
            for (int i = 1; i < s1.length + 1; i++) {
                dp[i][0] = i;
            }
            for (int j = 1; j < s2.length + 1; j++) {
                dp[0][j] = j;
            }
            for (int i = 1; i < s1.length + 1; i++) {
                for (int j = 1; j < s2.length + 1; j++) {
                    //一共3种情况：
                    //情况1：s1删除一个字符的次数1 加上 s1[0,i-1) 转化为 s2[0,j)的最少操作次数
                    int left = dp[i - 1][j] + 1;
                    //情况2：s1插入一个字符的次数1 加上 s1[0,i) 转化为 s2[0,j-1)的最少操作次数
                    int top = dp[i][j - 1] + 1;
                    //情况3：s1[i] == s2[j] 则dp[i][j] = dp[i - 1][j - 1] ，不相等则改变最后一个字符
                    int leftTop = s1[i - 1] == s2[j - 1] ? dp[i - 1][j - 1] : dp[i - 1][j - 1] + 1;
                    dp[i][j] = Math.min(Math.min(left, top), leftTop);
                }
            }
            return dp[s1.length][s2.length];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
