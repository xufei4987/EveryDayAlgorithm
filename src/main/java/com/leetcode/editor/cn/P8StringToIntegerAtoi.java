//请你来实现一个 atoi 函数，使其能将字符串转换成整数。 
//
// 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下： 
//
// 
// 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。 
// 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。 
// 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。 
// 
//
// 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。 
//
// 在任何情况下，若函数不能进行有效的转换时，请返回 0 。 
//
// 提示： 
//
// 
// 本题中的空白字符只包括空格字符 ' ' 。 
// 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231, 231 − 1]。如果数值超过这个范围，请返回 INT_MAX (231
// − 1) 或 INT_MIN (−231) 。 
// 
//
// 
//
// 示例 1: 
//
// 输入: "42"
//输出: 42
// 
//
// 示例 2: 
//
// 输入: "   -42"
//输出: -42
//解释: 第一个非空白字符为 '-', 它是一个负号。
//     我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
// 
//
// 示例 3: 
//
// 输入: "4193 with words"
//输出: 4193
//解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
// 
//
// 示例 4: 
//
// 输入: "words and 987"
//输出: 0
//解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
//     因此无法执行有效的转换。 
//
// 示例 5: 
//
// 输入: "-91283472332"
//输出: -2147483648
//解释: 数字 "-91283472332" 超过 32 位有符号整数范围。 
//     因此返回 INT_MIN (−231) 。
// 
// Related Topics 数学 字符串


package com.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

//Java：字符串转换整数 (atoi)
public class P8StringToIntegerAtoi {
    public static void main(String[] args) {
        Solution solution = new P8StringToIntegerAtoi().new Solution();
        // TO TEST
        System.out.println(solution.myAtoi2("-123132131313132"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //方法1：判断多种边界条件
        public int myAtoi1(String str) {
            if (str == null || str.length() == 0 || str.trim().length() == 0) {
                return 0;
            }
            String afterTrim = str.trim();
            if (afterTrim.charAt(0) != '-'
                    && afterTrim.charAt(0) != '+'
                    && (afterTrim.charAt(0) < '0'
                    || afterTrim.charAt(0) > '9')) {
                return 0;
            }
            int result = 0;
            boolean symbol = true;
            boolean flag = false;
            for (int i = 0; i < str.length(); i++) {

                if (i == 0 && afterTrim.charAt(i) == '+') {
                    symbol = true;
                    continue;
                }
                if (i == 0 && afterTrim.charAt(i) == '-') {
                    symbol = false;
                    continue;
                }
                if ((i == 0 && afterTrim.charAt(i) == '0')
                        || (i == 1 && (afterTrim.charAt(0) == '-' || afterTrim.charAt(0) == '+') && afterTrim.charAt(i) == '0')) {
                    flag = true;
                    continue;
                }
                if (flag && afterTrim.charAt(i) == '0') {
                    continue;
                }
                if (afterTrim.charAt(i) < '0' || afterTrim.charAt(i) > '9') {
                    break;
                }
                flag = false;
                if ((result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && (afterTrim.charAt(i) - '0') > 7)) && symbol) {
                    return Integer.MAX_VALUE;
                }
                if ((result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && (afterTrim.charAt(i) - '0') > 8)) && !symbol) {
                    return Integer.MIN_VALUE;
                }
                result = result * 10 + (afterTrim.charAt(i) - '0');
            }
            return symbol ? result : 0 - result;
        }
        //方法二：通过状态机处理

        /**
         * ''      +/-     number      other
         * start：  start   signed  i_number      end
         * signed:  end     end     i_number      end
         * int:     end     end     i_number      end
         * end:     end     end     end           end
         */
        public int myAtoi2(String str) {
            if (str == null || str.length() == 0) {
                return 0;
            }
            //初始化状态机
            Map<String, List<String>> stateMap = new HashMap<>();
            stateMap.put("start", Arrays.asList("start", "signed", "int", "end"));
            stateMap.put("signed", Arrays.asList("end", "end", "int", "end"));
            stateMap.put("int", Arrays.asList("end", "end", "int", "end"));
            stateMap.put("end", Arrays.asList("end", "end", "end", "end"));
            //定义输入信号
            Function<Character, Integer> getStateIdx = (input) -> {
                if (input == ' ') {
                    return 0;
                } else if (input == '-' || input == '+') {
                    return 1;
                } else if (Character.isDigit(input)) {
                    return 2;
                } else {
                    return 3;
                }
            };
            List<String> curState = stateMap.get("start");
            int result = 0;
            boolean signed = true;
            for (int i = 0; i < str.length(); i++) {
                Integer idx = getStateIdx.apply(str.charAt(i));
                if (curState.get(idx) == "start") {
                    curState = stateMap.get("start");
                } else if (curState.get(idx) == "signed") {
                    curState = stateMap.get("signed");
                    if (str.charAt(i) == '-') {
                        signed = false;
                    }
                } else if (curState.get(idx) == "int") {
                    curState = stateMap.get("int");
                    if ((result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && (str.charAt(i) - '0') > 7)) && signed) {
                        return Integer.MAX_VALUE;
                    }
                    if ((result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && (str.charAt(i) - '0') > 8)) && !signed) {
                        return Integer.MIN_VALUE;
                    }
                    result = result * 10 + (str.charAt(i) - '0');
                } else if (curState.get(idx) == "end") {
                    curState = stateMap.get("end");
                }
            }
            return signed ? result : 0 - result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}