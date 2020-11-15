//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 注意空字符串可被认为是有效字符串。 
//
// 示例 1: 
//
// 输入: "()"
//输出: true
// 
//
// 示例 2: 
//
// 输入: "()[]{}"
//输出: true
// 
//
// 示例 3: 
//
// 输入: "(]"
//输出: false
// 
//
// 示例 4: 
//
// 输入: "([)]"
//输出: false
// 
//
// 示例 5: 
//
// 输入: "{[]}"
//输出: true 
// Related Topics 栈 字符串


package leetcode.editor.cn;

import java.util.Stack;

public class P20ValidParentheses{
    public static void main(String[] args) {
        Solution solution = new P20ValidParentheses().new Solution();
        // TO TEST
        System.out.println(solution.isValid("{[]}"));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isValid(String s) {
            if(s == null || s.length() < 2) return false;
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == '[' || s.charAt(i) == '{' || s.charAt(i) == '('){
                    stack.push(s.charAt(i));
                }
                if(s.charAt(i) == '}'){
                    if (stack.isEmpty()) return false;
                    Character c = stack.pop();
                    if(c == null || c != '{') return false;
                }else if(s.charAt(i) == ']'){
                    if (stack.isEmpty()) return false;
                    Character c = stack.pop();
                    if(c == null || c != '[') return false;
                }else if(s.charAt(i) == ')'){
                    if (stack.isEmpty()) return false;
                    Character c = stack.pop();
                    if(c == null || c != '(') return false;
                }
            }
            return stack.isEmpty();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}