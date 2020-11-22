//给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
//
// 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
//
//
//
// 进阶：
//
// 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
//
//
//
// 示例：
//
// 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 8 -> 0 -> 7
//
// Related Topics 链表

package leetcode.editor.cn;

import datastructure.list.Stack;

public class P445AddTwoNumbersIi {
    public static void main(String[] args) {
        Solution solution = new P445AddTwoNumbersIi().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if (l1 == null) return l2;
            if (l2 == null) return l1;
            ListNode dummyHead = new ListNode(0);
            ListNode tail = dummyHead;
            Stack<Integer> s1 = new Stack<>();
            Stack<Integer> s2 = new Stack<>();
            Stack<Integer> s3 = new Stack<>();
            int carry = 0;
            while (l1 != null || l2 != null){
                if (l1 != null){
                    s1.push(l1.val);
                    l1 = l1.next;
                }
                if (l2 != null){
                    s2.push(l2.val);
                    l2 = l2.next;
                }
            }
            while (!s1.isEmpty() || !s2.isEmpty()){
                int v1 = 0;
                if (!s1.isEmpty()) {
                    v1 = s1.pop();
                }
                int v2 = 0;
                if (!s2.isEmpty()) {
                    v2 = s2.pop();
                }
                int sum = v1 + v2 + carry;
                carry = sum / 10;
                s3.push(sum% 10);
            }
            if (carry > 0){
                s3.push(carry);
            }
            while (!s3.isEmpty()){
                tail.next = new ListNode(s3.pop());
                tail = tail.next;
            }
            return dummyHead.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
