//给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看
//做它自身的一棵子树。
//
// 示例 1:
//给定的树 s:
//
//
//     3
//    / \
//   4   5
//  / \
// 1   2
//
//
// 给定的树 t：
//
//
//   4
//  / \
// 1   2
//
//
// 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
//
// 示例 2:
//给定的树 s：
//
//
//     3
//    / \
//   4   5
//  / \
// 1   2
//    /
//   0
//
//
// 给定的树 t：
//
//
//   4
//  / \
// 1   2
//
//
// 返回 false。
// Related Topics 树

package leetcode.editor.cn;
public class P572SubtreeOfAnotherTree {
    public static void main(String[] args) {
        Solution solution = new P572SubtreeOfAnotherTree().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution {
        public boolean isSubtree(TreeNode s, TreeNode t) {
            StringBuilder sb1 = new StringBuilder("!");
            StringBuilder sb2 = new StringBuilder("!");
            postOrder(s,sb1);
            postOrder(t,sb2);
            if (sb1.toString().contains(sb2.toString())){
                return true;
            }
            return false;
        }

        private void postOrder(TreeNode s, StringBuilder sb) {
            sb.append(s.val).append("!");
            if (s.left != null){
                postOrder(s.left,sb);
            } else {
                sb.append("#!");
            }
            if (s.right != null){
                postOrder(s.right,sb);
            } else {
                sb.append("#!");
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
