//反转一个单链表。 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 进阶: 
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？ 
// Related Topics 链表


package leetcode.editor.cn;
//Java：反转链表
public class P206ReverseLinkedList{
    public static void main(String[] args) {
        Solution solution = new P206ReverseLinkedList().new Solution();
        // TO TEST
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, null))));
        System.out.println(solution.reverseList(l1));
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
        ListNode(int x, ListNode next) { val = x; this.next = next; }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            ListNode listNode = this;
            do {
                sb.append(listNode.val);
                sb.append(" ");
                listNode = listNode.next;
            } while (listNode != null);
            return sb.toString();
        }
    }

    class Solution {
        public ListNode reverseList(ListNode head) {
            if (head == null) return null;
            if (head.next == null) return head;
            ListNode newHead = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            //返回最后一个节点
            return newHead;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}