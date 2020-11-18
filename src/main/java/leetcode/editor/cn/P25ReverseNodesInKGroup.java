//给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。 
//
// k 是一个正整数，它的值小于或等于链表的长度。 
//
// 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
//
// 
//
// 示例： 
//
// 给你这个链表：1->2->3->4->5 
//
// 当 k = 2 时，应当返回: 2->1->4->3->5 
//
// 当 k = 3 时，应当返回: 3->2->1->4->5 
//
// 
//
// 说明： 
//
// 
// 你的算法只能使用常数的额外空间。 
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。 
// 
// Related Topics 链表


package leetcode.editor.cn;
//Java：K 个一组翻转链表
public class P25ReverseNodesInKGroup{
    public static void main(String[] args) {
        Solution solution = new P25ReverseNodesInKGroup().new Solution();
        // TO TEST
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        System.out.println(solution.reverseKGroup(l1,3));

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
        public ListNode reverseKGroup(ListNode head, int k) {
            if(head == null || head.next == null) return head;
            ListNode tail = head;
            for (int i = 0; i < k; i++) {
                //剩余数量小于k的话，则不需要反转
                if(tail == null){
                    return head;
                }
                tail = tail.next;
            }
            // 反转前 k 个元素
            ListNode newHead = reverse(head,tail);
            //下一轮的开始的地方就是tail
            head.next = reverseKGroup(tail,k);
            return newHead;
        }

        /**
         * 左闭右开区间
         * @param head
         * @param tail
         * @return
         */
        private ListNode reverse(ListNode head,ListNode tail){
            ListNode prev=null, next = null;
            while (head != tail){
                next = head.next;
                head.next = prev;
                prev = head;
                head = next;
            }
            return prev;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}