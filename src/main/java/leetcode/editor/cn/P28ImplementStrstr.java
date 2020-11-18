//实现 strStr() 函数。 
//
// 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如
//果不存在，则返回 -1。 
//
// 示例 1: 
//
// 输入: haystack = "hello", needle = "ll"
//输出: 2
// 
//
// 示例 2: 
//
// 输入: haystack = "aaaaa", needle = "bba"
//输出: -1
// 
//
// 说明: 
//
// 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。 
//
// 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。 
// Related Topics 双指针 字符串


package leetcode.editor.cn;

//Java：实现 strStr()
public class P28ImplementStrstr {
    public static void main(String[] args) {
        Solution solution = new P28ImplementStrstr().new Solution();
        // TO TEST
        System.out.println(solution.strStr("aaa", "aaa"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int strStr(String haystack, String needle) {
            if (haystack == null) return 0;
            if (needle == null) return 0;
            int lt = haystack.length();
            int lp = needle.length();
            if (lp == 0) return 0;
            if (lp > lt || lt == 0) return -1;
            int pi = 0;
            int ti = 0;
            int tiMax = lt - lp;
            while (pi < lp && ti - pi <= tiMax) {
                if (haystack.charAt(ti) == needle.charAt(pi)) {
                    ti++;
                    pi++;
                } else {
                    ti = ti - pi + 1;
                    pi = 0;
                }
            }
            return pi == lp ? ti - pi : -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}