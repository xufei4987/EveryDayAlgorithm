//给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。 
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4]
//输出：[2,1,4,3]
// 
//
// 示例 2： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 100] 内 
// 0 <= Node.val <= 100 
// 
// Related Topics 链表


package leetcode.editor.cn;
//Java：两两交换链表中的节点
public class P24SwapNodesInPairs{
    public static void main(String[] args) {
        Solution solution = new P24SwapNodesInPairs().new Solution();
        // TO TEST
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, null))));

        System.out.println(solution.swapPairs2(l1));
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
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
        public ListNode swapPairs1(ListNode head) {
            if(head == null) return null;
            if(head.next == null) return head;
            ListNode newHead = head.next;
            head.next = swapPairs1(newHead.next);
            newHead.next = head;
            return newHead;
        }

        public ListNode swapPairs2(ListNode head) {
            if(head == null) return null;
            if(head.next == null) return head;
            ListNode dumyHead = new ListNode(0);
            dumyHead.next = head;
            ListNode tmpNode = dumyHead;
            while (tmpNode.next != null && tmpNode.next.next != null){
                ListNode n1 = tmpNode.next;
                ListNode n2 = tmpNode.next.next;
                tmpNode.next = n2;
                n1.next = n2.next;
                n2.next = n1;
                tmpNode = n1;
            }
            return dumyHead.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}