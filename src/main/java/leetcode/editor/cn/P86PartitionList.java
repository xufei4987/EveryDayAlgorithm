//给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
//
// 你应当保留两个分区中每个节点的初始相对位置。
//
//
//
// 示例:
//
// 输入: head = 1->4->3->2->5->2, x = 3
//输出: 1->2->2->4->3->5
//
// Related Topics 链表 双指针

package leetcode.editor.cn;
public class P86PartitionList {
    public static void main(String[] args) {
        Solution solution = new P86PartitionList().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    class Solution {
        public ListNode partition(ListNode head, int x) {
            if (head == null) return null;
            ListNode lhead = new ListNode(0);
            ListNode ltail = lhead;
            ListNode rhead = new ListNode(0);
            ListNode rtail = rhead;
            while (head != null){
                if(head.val < x){
                    ltail.next = head;
                    ltail = head;
                } else {
                    rtail.next = head;
                    rtail = head;
                }
                head = head.next;
            }
            //右边链表的尾部有可能还指向原链表的元素  所以需要置空
            rtail.next = null;
            ltail.next = rhead.next;
            return lhead.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
