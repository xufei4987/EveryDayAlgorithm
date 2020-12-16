//给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
//
// 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
//
// 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
//
// 示例：
//
// 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 0 -> 8
//原因：342 + 465 = 807
//
// Related Topics 链表 数学

package tx;

public class P2AddTwoNumbers {
    public static void main(String[] args) {
        Solution solution = new P2AddTwoNumbers().new Solution();
        ListNode l11 = new ListNode(1);
        ListNode l12 = new ListNode(0);
        ListNode l13 = new ListNode(3);

        ListNode l21 = new ListNode(5);
        ListNode l22 = new ListNode(0);
        ListNode l23 = new ListNode(4);

        l11.next =l12;
        l12.next =l13;

        l21.next =l22;
        l22.next =l23;
        ListNode listNode = solution.addTwoNumbers(l11, l21);
        while (listNode != null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
            ListNode dummyHead = new ListNode(0);
            ListNode n1 = l1, n2 = l2, cur = dummyHead;
            //carry进位需要带入下一次运算
            int carry = 0;
            while (n1 != null || n2 != null){
                int x = n1 == null ? 0 : n1.val;
                int y = n2 == null ? 0 : n2.val;
                int sum = x + y + carry;
                carry = sum / 10;
                cur = cur.next = new ListNode(sum % 10);
                n1 = n1 == null ? null : n1.next;
                n2 = n2 == null ? null : n2.next;
            }
            //最后一次进位需要加上
            if (carry > 0) {
                cur.next = new ListNode(carry);
            }
            return dummyHead.next;
        }

        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if (l1 == null) return l2;
            if (l2 == null) return l1;
            ListNode dummyHead = new ListNode(0);
            ListNode tail = dummyHead;
            int carry = 0;
            while (l1 != null || l2 != null){
                int v1 = 0;
                if(l1 != null){
                    v1 = l1.val;
                    l1 = l1.next;
                }
                int v2 = 0;
                if(l2 != null){
                    v2 = l2.val;
                    l2 = l2.next;
                }
                int sum = v1 + v2 + carry;
                carry = sum / 10;
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            if (carry > 0) {
                tail.next = new ListNode(carry);
            }
            return dummyHead.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
