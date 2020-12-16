//给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
//
// 示例 1：
//
// 输入: s1 = "abc", s2 = "bca"
//输出: true
//
//
// 示例 2：
//
// 输入: s1 = "abc", s2 = "bad"
//输出: false
//
//
// 说明：
//
//
// 0 <= len(s1) <= 100
// 0 <= len(s2) <= 100
//
// Related Topics 数组 字符串


package leetcode.editor.cn;

//Java：判定是否互为字符重排
public class MST0102_CheckPermutationLcci {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // TO TEST
        System.out.println(solution.CheckPermutation("abc", "cab"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public boolean CheckPermutation(String s1, String s2) {
            if (s1 == null || s2 == null) return false;
            if (s1 == s2) return true;
            char[] c1 = s1.toCharArray();
            char[] c2 = s2.toCharArray();
            int[] asc = new int[128];
            for (int i = 0; i < c1.length; i++) {
                asc[c1[i]]++;
            }
            for (int i = 0; i < c2.length; i++) {
                asc[c2[i]]--;
            }
            for (int i = 0; i < asc.length; i++) {
                if (asc[i] != 0) {
                    return false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}