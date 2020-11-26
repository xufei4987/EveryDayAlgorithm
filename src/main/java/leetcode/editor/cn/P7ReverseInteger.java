//给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
//
// 示例 1:
//
// 输入: 123
//输出: 321
//
//
// 示例 2:
//
// 输入: -123
//输出: -321
//
//
// 示例 3:
//
// 输入: 120
//输出: 21
//
//
// 注意:
//
// 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231, 231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
// Related Topics 数学

package leetcode.editor.cn;

import java.util.Stack;

public class P7ReverseInteger {
    public static void main(String[] args) {
        Solution solution = new P7ReverseInteger().new Solution();
        System.out.println(solution.reverse2(1463847412));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int reverse1(int x) {
            String s = String.valueOf(x);
            Stack<Character> stack = new Stack<>();
            for (char c : s.toCharArray()){
                stack.push(c);
            }
            Character character = null;
            StringBuilder sb = new StringBuilder();
            if(x < 0){
                sb.append("-");
            }
            do {
                character = stack.pop();
                if(character >= '0' && character <= '9'){
                    sb.append(character);
                }
            }while (!stack.empty());
            String reverseStr = sb.toString();
            if(Long.valueOf(reverseStr) > Integer.MAX_VALUE || Long.valueOf(reverseStr) < Integer.MIN_VALUE){
                return 0;
            }
            return Integer.valueOf(reverseStr);
        }

        /**
         * 最大的int值为2,147,483,647
         * 最小的int值为-2,147,483,648
         */
        public int reverse2(int x) {
            int reverse = 0;
            while (x != 0){
                int pop = x % 10;
                x = x/10;
                if(reverse > Integer.MAX_VALUE/10 || (reverse == Integer.MAX_VALUE/10 && pop > 7)){
                    return 0;
                }
                if(reverse < Integer.MIN_VALUE/10 || (reverse == Integer.MAX_VALUE/10 && pop < -8)){
                    return 0;
                }
                reverse = reverse * 10 + pop;
            }
            return reverse;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
