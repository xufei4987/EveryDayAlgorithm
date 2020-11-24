//请判断一个链表是否为回文链表。
//
// 示例 1:
//
// 输入: 1->2
//输出: false
//
// 示例 2:
//
// 输入: 1->2->2->1
//输出: true
//
//
// 进阶：
//你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
// Related Topics 链表 双指针

package leetcode.editor.cn;

public class P234PalindromeLinkedList {
    public static void main(String[] args) {
        Solution solution = new P234PalindromeLinkedList().new Solution();
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
        /**
         * 回文链表:
         * 方法1、将原链表翻转获得一个新的链表  在和原来的链表进行逐个比较（有额外的空间开销）
         * 方法2、找到链表的中心节点，并把中心节点后的链表进行翻转，从两头进行逐个比较
         *  方法2求解
         * @param head
         * @return
         */
        public boolean isPalindrome(ListNode head) {
            if (head == null || head.next == null) return true;
            if (head.next.next == null) return head.val == head.next.val;
            ListNode mid = midNode(head);
            ListNode lhead = reverseList(mid.next);
            while (lhead != null){
                if (head.val != lhead.val) return false;
                head = head.next;
                lhead = lhead.next;
            }
            return true;
        }

        /**
         * 利用快慢指针找中心节点
         *
         * @param head
         * @return
         */
        private ListNode midNode(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }

        /**
         * 翻转链表
         *
         * @param head
         * @return
         */
        private ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode cur = head;
            while (cur != null) {
                ListNode tmp = cur.next;
                cur.next = prev;
                prev = cur;
                cur = tmp;
            }
            return prev;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
