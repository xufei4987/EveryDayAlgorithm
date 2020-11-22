//删除链表中等于给定值 val 的所有节点。
//
// 示例:
//
// 输入: 1->2->6->3->4->5->6, val = 6
//输出: 1->2->3->4->5
//
// Related Topics 链表

package leetcode.editor.cn;
public class P203RemoveLinkedListElements {
    public static void main(String[] args) {
        Solution solution = new P203RemoveLinkedListElements().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    class Solution {
        /**
         * 利用虚拟头节点
         * @param head
         * @param val
         * @return
         */
        public ListNode removeElements(ListNode head, int val) {
            ListNode newHead = new ListNode(0);
            ListNode newTail = newHead;
            while (head != null){
                if(head.val != val){
                    newTail.next = head;
                    newTail = head;
                }
                head = head.next;
            }
            newTail.next = null;
            return newHead.next;
        }

        public ListNode removeElements1(ListNode head, int val) {
            ListNode node = head;
            ListNode prev = null;
            while (node != null){
                if(node.val == val){
                    if(prev == null){
                        head = node.next;
                    } else {
                        prev.next = node.next;
                    }
                }
                prev = node;
                node = node.next;
            }
            return head;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
