//给定一个字符串，逐个翻转字符串中的每个单词。
//
// 说明：
//
//
// 无空格字符构成一个 单词 。
// 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
// 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
//
//
//
//
// 示例 1：
//
// 输入："the sky is blue"
//输出："blue is sky the"
//
//
// 示例 2：
//
// 输入："  hello world!  "
//输出："world! hello"
//解释：输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
//
//
// 示例 3：
//
// 输入："a good   example"
//输出："example good a"
//解释：如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
//
//
// 示例 4：
//
// 输入：s = "  Bob    Loves  Alice   "
//输出："Alice Loves Bob"
//
//
// 示例 5：
//
// 输入：s = "Alice does not even like bob"
//输出："bob like even not does Alice"
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 104
// s 包含英文大小写字母、数字和空格 ' '
// s 中 至少存在一个 单词
//
//
//
//
//
//
//
// 进阶：
//
//
// 请尝试使用 O(1) 额外空间复杂度的原地解法。
//
// Related Topics 字符串

package leetcode.editor.cn;
public class P151ReverseWordsInAString {
    public static void main(String[] args) {
        Solution solution = new P151ReverseWordsInAString().new Solution();
        System.out.println(solution.reverseWords("the sky is blue"));
        System.out.println(solution.reverseWords("  Bob    Loves  Alice   "));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 总体思路：
         * 1、先去掉多余的空格
         * 2、对整个字符串进行翻转
         * 3、再对每个单词进行翻转
         * @param s
         * @return
         */
        public String reverseWords(String s) {
            if (s == null || s.length() == 0) return "";
            char[] chars = s.toCharArray();
            int cur = 0;//指向字符需要挪动到的地方
            boolean isSpace = true;//表示-1位置是一个空格
            for (int i = 0; i < chars.length; i++) {
                if(chars[i] != ' '){
                    isSpace = false;
                    chars[cur++] = chars[i];
                } else if(!isSpace){
                    isSpace = true;
                    chars[cur++] = chars[i];
                }
            }
            int lenth = isSpace ? cur - 1 : cur;
            System.out.println("lenth:" + lenth);
            //翻转整个字符串
            reverseStr(chars,0,lenth);
            System.out.println(new String(chars,0,lenth));
            int prevSpaceIdx = -1;//表示-1位置是一个空格
            //翻转每个单词
            for (int i = 0; i < lenth; i++) {
                if(chars[i] != ' ') continue;
                reverseStr(chars,prevSpaceIdx + 1,i);
                prevSpaceIdx = i;
            }
            //还剩下最后一个单词没有翻转
            reverseStr(chars,prevSpaceIdx+1,lenth);
            return new String(chars,0,lenth);
        }

        /**
         * 翻转[li,ri)的字符串
         * @param chars
         * @param li
         * @param ri
         */
        private void reverseStr(char[] chars, int li, int ri) {
            ri--;
            while (li < ri){
                char tmp = chars[li];
                chars[li] = chars[ri];
                chars[ri] = tmp;
                li++;
                ri--;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
