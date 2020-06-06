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

package com.leetcode.editor.cn;
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubstring().new Solution();
        System.out.println(solution.longestPalindrome2("babad"));
        System.out.println(solution.longestPalindrome2("cbbd"));
        System.out.println(solution.longestPalindrome2("abcba"));
        System.out.println(solution.longestPalindrome2("ac"));
        System.out.println(solution.longestPalindrome2("aa"));
        System.out.println(solution.longestPalindrome2("aaabaaaa"));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //中心扩散法
        public String longestPalindrome1(String s) {
            if (s == null || s.length() == 0) {
                return "";
            }
            int length = s.length();
            int left=0,right=0;
            int curlength=1 ,maxlength=1;
            int maxStart = -1;
            for (int i = 0; i < length; i++){
                left = i - 1;
                right = i + 1;
                while (left >= 0 && s.charAt(left) == s.charAt(i)){
                    left --;
                    curlength ++;
                }
                while (right < length && s.charAt(right) == s.charAt(i)){
                    right ++;
                    curlength ++;
                }
                while (left >= 0 && right < length && s.charAt(left) == s.charAt(right)){
                    left --;
                    right ++;
                    curlength += 2;
                }
                if(curlength > maxlength){
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
            int length = s.length();
            int maxLength = 1;
            int maxStart = 0;
            boolean dp[][] = new boolean[length][length];
            for(int r = 1; r < length; r++){
                for (int l = 0; l < r; l++){
                    if(s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])){
                        dp[l][r] = true;
                        if(maxLength < r - l + 1){
                            maxLength = r - l + 1;
                            maxStart = l;
                        }
                    }
                }
            }
            return s.substring(maxStart,maxStart + maxLength);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}