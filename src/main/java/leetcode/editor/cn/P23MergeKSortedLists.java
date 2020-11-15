//给你一个链表数组，每个链表都已经按升序排列。 
//
// 请你将所有链表合并到一个升序链表中，返回合并后的链表。 
//
// 
//
// 示例 1： 
//
// 输入：lists = [[1,4,5],[1,3,4],[2,6]]
//输出：[1,1,2,3,4,4,5,6]
//解释：链表数组如下：
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//将它们合并到一个有序链表中得到。
//1->1->2->3->4->4->5->6
// 
//
// 示例 2： 
//
// 输入：lists = []
//输出：[]
// 
//
// 示例 3： 
//
// 输入：lists = [[]]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// k == lists.length 
// 0 <= k <= 10^4 
// 0 <= lists[i].length <= 500 
// -10^4 <= lists[i][j] <= 10^4 
// lists[i] 按 升序 排列 
// lists[i].length 的总和不超过 10^4 
// 
// Related Topics 堆 链表 分治算法


package leetcode.editor.cn;

//Java：合并K个升序链表
public class P23MergeKSortedLists {
    public static void main(String[] args) {
        Solution solution = new P23MergeKSortedLists().new Solution();
        // TO TEST
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4, null)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(7, null)));
        ListNode l3 = new ListNode(4, new ListNode(5, new ListNode(8, null)));
        System.out.println(solution.mergeKLists(new ListNode[]{l1, l2, l3}));
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

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
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null) return null;
            if (lists.length < 1) return null;
            return mergeKLists(lists, 0, lists.length);
        }

        /**
         * 合并[left,right)范围内的list
         *
         * @param lists
         * @param left
         * @param right
         */
        private ListNode mergeKLists(ListNode[] lists, int left, int right) {
            if (right == left) {
                return null;
            }
            if (right - left == 1) {
                return lists[left];
            }
            if (right - left == 2) {
                return mergeTwoLists(lists[left], lists[left + 1]);
            }
            int mid = (left + right) / 2;
            ListNode mergedLeftList = mergeKLists(lists,left,mid);
            ListNode mergedRightList = mergeKLists(lists,mid,right);
            return mergeTwoLists(mergedLeftList,mergedRightList);
        }

        private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null && l2 == null) return null;
            if (l1 == null) return l2;
            if (l2 == null) return l1;
            if (l1.val < l2.val) {
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            } else {
                l2.next = mergeTwoLists(l1, l2.next);
                return l2;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}