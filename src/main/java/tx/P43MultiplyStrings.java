//给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。 
//
// 示例 1: 
//
// 输入: num1 = "2", num2 = "3"
//输出: "6" 
//
// 示例 2: 
//
// 输入: num1 = "123", num2 = "456"
//输出: "56088" 
//
// 说明： 
//
// 
// num1 和 num2 的长度小于110。 
// num1 和 num2 只包含数字 0-9。 
// num1 和 num2 均不以零开头，除非是数字 0 本身。 
// 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。 
// 
// Related Topics 数学 字符串


package tx;
//Java：字符串相乘
public class P43MultiplyStrings{
    public static void main(String[] args) {
        Solution solution = new P43MultiplyStrings().new Solution();
        // TO TEST
        System.out.println(solution.multiply("123", "456"));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String multiply(String num1, String num2) {
            if (num1.equals("0") || num2.equals("0")) return "0";
            if (num1.equals("1")) return num2;
            if (num2.equals("1")) return num1;
            int l1 = num1.length();
            int l2 = num2.length();
            char[] cs1 = num1.toCharArray();
            char[] cs2 = num2.toCharArray();
            int[] result = new int[l1 + l2];
            for (int i = l1 - 1; i >= 0; i--) {
                for (int j = l2 - 1; j >= 0; j--) {
                    int n1 = cs1[i] - '0';
                    int n2 = cs2[j] - '0';
                    //进位在后续处理
                    result[(l1 - i - 1) + (l2 - j - 1)] += n1 * n2;
                }
            }
            for (int i = 0; i < result.length; i++) {
                if (result[i] > 9){
                    result[i+1] += result[i]/10;
                    result[i] = result[i]%10;
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i = result.length - 1; i >= 0; i--) {
                if(i == result.length - 1 && result[i] == 0) continue;
                sb.append(String.valueOf(result[i]));
            }
            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}