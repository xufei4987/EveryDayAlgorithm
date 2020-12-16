//实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
//
// 示例 1：
//
// 输入: s = "leetcode"
//输出: false
//
//
// 示例 2：
//
// 输入: s = "abc"
//输出: true
//
//
// 限制：
//
// 0 <= len(s) <= 100
// 如果你不使用额外的数据结构，会很加分。
//
// Related Topics 数组


package leetcode.editor.cn;
//Java：判定字符是否唯一
public class MST0101_IsUniqueLcci {
    public static void main(String[] args) {
        Solution solution = new MST0101_IsUniqueLcci().new Solution();
        // TO TEST
        System.out.println(solution.isUnique("abc"));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isUnique(String astr) {
            if (astr == null || astr.length() == 0) return true;
            char[] chars = astr.toCharArray();
            int[] asc = new int[128];
            for (int i = 0; i < chars.length; i++){
                if (asc[chars[i]] != 0) {
                    return false;
                }
                asc[chars[i]]++;
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}