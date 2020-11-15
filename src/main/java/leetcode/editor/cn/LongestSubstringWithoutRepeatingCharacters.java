//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 示例 1: 
//
// 输入: "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window

package leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //滑动窗口
        public int lengthOfLongestSubstring(String s) {
            int max = 0;
            int lenth = s.length();
            Set<Character> set = new HashSet<>();
            int r_idx = -1;
            for(int l_idx = 0; l_idx < lenth; l_idx++){
                if(l_idx != 0){
                    //左指针左移后，移除掉第一个元素
                    set.remove(s.charAt(l_idx-1));
                }
                while (r_idx + 1 < lenth && !set.contains(s.charAt(r_idx + 1))){
                    set.add(s.charAt(r_idx + 1));
                    r_idx++;
                }
                max = Math.max(max,r_idx - l_idx + 1);
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}