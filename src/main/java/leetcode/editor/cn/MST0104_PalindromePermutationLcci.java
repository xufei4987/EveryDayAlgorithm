//给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
//
// 回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
//
// 回文串不一定是字典当中的单词。
//
//
//
// 示例1：
//
// 输入："tactcoa"
//输出：true（排列有"tacocat"、"atcocta"，等等）
//
//
//
// Related Topics 哈希表 字符串


package leetcode.editor.cn;
//Java：回文排列
public class MST0104_PalindromePermutationLcci{
public static void main(String[] args) {
        Solution solution = new MST0104_PalindromePermutationLcci().new Solution();
        // TO TEST
        }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean canPermutePalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        char[] chars = s.toCharArray();
        int[] asc = new int[128];
        for (int i = 0; i < chars.length; i++) {
            asc[chars[i]]++;
        }
        int oddCount = 0;

        for (int i = 0; i < asc.length; i++) {
            if ((asc[i] & 1) == 1)
                oddCount++;
        }
        //奇数个字符的个数  要么是0个 要么是1个  才能有回文排列
        if (oddCount > 1) return false;
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}