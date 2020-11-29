//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
//
// 示例 1：
//
// 输入: "babad"
//输出: "bab"
//注意: "aba" 也是一个有效答案。
//
//
// 示例 2：
//
// 输入: "cbbd"
//输出: "bb"
//
// Related Topics 字符串 动态规划

package leetcode.editor.cn;

public class P5LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution solution = new P5LongestPalindromicSubstring().new Solution();
        System.out.println(solution.longestPalindrome3("babad"));
        System.out.println(solution.longestPalindrome3("cbbd"));
        System.out.println(solution.longestPalindrome3("abcba"));
        System.out.println(solution.longestPalindrome3("ac"));
        System.out.println(solution.longestPalindrome3("aa"));
        System.out.println(solution.longestPalindrome3("aaabaaaa"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //中心扩散法
        public String longestPalindrome1(String s) {
            if (s == null || s.length() == 0) {
                return "";
            }
            int length = s.length();
            int left = 0, right = 0;
            int curlength = 1, maxlength = 1;
            int maxStart = -1;
            for (int i = 0; i < length; i++) {
                left = i - 1;
                right = i + 1;
                while (left >= 0 && s.charAt(left) == s.charAt(i)) {
                    left--;
                    curlength++;
                }
                while (right < length && s.charAt(right) == s.charAt(i)) {
                    right++;
                    curlength++;
                }
                while (left >= 0 && right < length && s.charAt(left) == s.charAt(right)) {
                    left--;
                    right++;
                    curlength += 2;
                }
                if (curlength > maxlength) {
                    maxlength = curlength;
                    maxStart = left;
                }
                curlength = 1;
            }
            return s.substring(maxStart + 1, maxStart + 1 + maxlength);
        }

        /**
         * 动态规划
         * dp[l][r]是回文  且  r-l>=2 那么dp[l+1][r-1]也必然是个回文
         */
        public String longestPalindrome2(String s) {
            if (s == null || s.length() < 2) {
                return s;
            }
            int maxLength = 1;
            int start = 0;
            char[] cs = s.toCharArray();
            //dp[l][r] == true表示 cs[l,r]是一个回文子串
            boolean[][] dp = new boolean[cs.length][cs.length];
            //从下至上，从左至右进行求解
            for (int l = cs.length - 1; l >= 0; l--) {
                for (int r = l; r < cs.length; r++) {
                    int len = r - l + 1;
                    dp[l][r] = len <= 2 ? cs[l] == cs[r] : cs[l] == cs[r] && dp[l + 1][r - 1];
                    if (dp[l][r] && len > maxLength) {
                        start = l;
                        maxLength = len;
                    }
                }
            }
            return s.substring(start, start + maxLength);
        }

        /**
         * 基于相同字符的中心扩散算法
         * 如 abbbaccad 以a、bbb、a、cc、a、d为中心向两边扩散
         */
        public String longestPalindrome3(String s) {
            if (s == null || s.length() < 2) {
                return s;
            }
            char[] cs = s.toCharArray();
            int maxLength = 1;
            int maxBegin = 0;
            for (int i = 0; i < cs.length; ) {
                //找到第一个与cs[i]不相同字符的下标
                int r = i;
                int l = i - 1;
                while (r < cs.length && cs[r] == cs[i]) {
                    r++;
                }
                //下一次的中心从第一个不等于cs[i]的开始 即r
                i = r;
                //以l和r向左向右扩散
                while (l >= 0 && r < cs.length && cs[l] == cs[r]) {
                    l--;
                    r++;
                }
                int begin = l + 1;
                int length = r - begin;
                if (length > maxLength) {
                    maxBegin = begin;
                    maxLength = length;
                }

            }
            return new String(cs, maxBegin, maxLength);
        }
//leetcode submit region end(Prohibit modification and deletion)

    }
}
