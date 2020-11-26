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

public class P3LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new P3LongestSubstringWithoutRepeatingCharacters().new Solution();
        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            if (s == null || s.length() == 0) return 0;
            char[] cs = s.toCharArray();
            //保存字符上一次出现的位置(单字节字符一共有128个)
            int[] prevPos = new int[128];
            for (int i = 0; i < prevPos.length; i++) {
                prevPos[i] = -1;
            }
            prevPos[cs[0]] = 0;
            int li = 0;//表示i-1字符无重复字符的起始位置
            int max = 1;
            for (int i = 1; i < cs.length; i++) {
                //字符cs[i]上一次出现的位置
                int pi = prevPos[cs[i]];
                // pi在li的右边，则更新li的位置,[li,i]内都没有重复的字符
                if(pi >= li){
                    li = pi + 1;
                }
                prevPos[cs[i]] = i;
                max = Math.max(max,i - li + 1);
            }
            return max;
        }
        //滑动窗口
        public int lengthOfLongestSubstring1(String s) {
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
