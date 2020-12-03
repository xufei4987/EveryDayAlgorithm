//给定一个二叉树，检查它是否是镜像对称的。 
//
// 
//
// 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。 
//
//     1
//   / \
//  2   2
// / \ / \
//3  4 4  3
// 
//
// 
//
// 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的: 
//
//     1
//   / \
//  2   2
//   \   \
//   3    3
// 
//
// 
//
// 进阶： 
//
// 你可以运用递归和迭代两种方法解决这个问题吗？ 
// Related Topics 树 深度优先搜索 广度优先搜索


package leetcode.editor.cn;
//Java：对称二叉树
public class P101SymmetricTree{
    public static void main(String[] args) {
        Solution solution = new P101SymmetricTree().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    class Solution {
        public boolean isSymmetric(TreeNode root) {
            if(root == null) return true;
            return isSymmetric(root.left,root.right);
        }

        /**
         * 左右子树对称
         * @param t1
         * @param t2
         * @return
         */
        private boolean isSymmetric(TreeNode t1, TreeNode t2) {
            if(t1 == null && t2 == null) return true;
            if(t1 == null) return false;
            if(t2 == null) return false;
            if(!isSymmetric(t1.left,t2.right) || !isSymmetric(t1.right,t2.left)) return false;
            return t1.val == t2.val;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}