//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
//
//
//
// 示例:
//
// 输入："23"
//输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
//
//
// 说明:
//尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
// Related Topics 深度优先搜索 递归 字符串 回溯算法

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P17LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        Solution solution = new P17LetterCombinationsOfAPhoneNumber().new Solution();
        System.out.println(solution.letterCombinations("23"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};

        public List<String> letterCombinations(String digits) {
            if (digits == null) return null;
            List<String> strings = new ArrayList<>();
            char[] numbers = digits.toCharArray();
            if (numbers.length == 0) return strings;
            char[] cs = new char[numbers.length];
            dfs(0, numbers, cs, strings);
            return strings;
        }

        /**
         * 深度优先遍历
         *
         * @param idx     遍历的层数
         * @param numbers 输入的数字字符数组
         * @param cs      保存字符的变量
         * @param strings 结果集
         */
        private void dfs(int idx, char[] numbers, char[] cs, List<String> strings) {
            if (idx == numbers.length) {
                strings.add(new String(cs));
                return;
            }
            String s = phoneMap.get(numbers[idx]);
            for (int i = 0; i < s.length(); i++) {
                cs[idx] = s.charAt(i);
                dfs(idx + 1, numbers, cs, strings);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
