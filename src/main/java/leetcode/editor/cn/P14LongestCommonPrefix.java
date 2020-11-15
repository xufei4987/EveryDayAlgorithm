//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 示例 1: 
//
// 输入: ["flower","flow","flight"]
//输出: "fl"
// 
//
// 示例 2: 
//
// 输入: ["dog","racecar","car"]
//输出: ""
//解释: 输入不存在公共前缀。
// 
//
// 说明: 
//
// 所有输入只包含小写字母 a-z 。 
// Related Topics 字符串


package leetcode.editor.cn;
public class P14LongestCommonPrefix{
    public static void main(String[] args) {
        Solution solution = new P14LongestCommonPrefix().new Solution();
        // TO TEST
        System.out.println(solution.longestCommonPrefix(new String[]{"flower","flow","flight"}));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length < 1) return "";
            if (strs.length == 1) return strs[0];
            int minLen = Integer.MAX_VALUE;
            for (String str : strs){
                minLen = Math.min(minLen,str.length());
            }
            int i = 0;
            for (; i < minLen; i++) {
                boolean eq = true;
                for (int j = 1; j < strs.length; j++) {
                    if (strs[j].charAt(i) != strs[j-1].charAt(i)) {
                        eq = false;
                        break;
                    }
                }
                if (!eq){
                    break;
                }
            }
            return i == 0 ? "" : strs[0].substring(0,i);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}