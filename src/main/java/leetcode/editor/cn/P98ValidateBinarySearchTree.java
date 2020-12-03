//给定一个二叉树，判断其是否是一个有效的二叉搜索树。 
//
// 假设一个二叉搜索树具有如下特征： 
//
// 
// 节点的左子树只包含小于当前节点的数。 
// 节点的右子树只包含大于当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 示例 1: 
//
// 输入:
//    2
//   / \
//  1   3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//    5
//   / \
//  1   4
//     / \
//    3   6
//输出: false
//解释: 输入为: [5,1,4,null,null,3,6]。
//     根节点的值为 5 ，但是其右子节点值为 4 。
// 
// Related Topics 树 深度优先搜索 递归


package leetcode.editor.cn;

//Java：验证二叉搜索树
public class P98ValidateBinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new P98ValidateBinarySearchTree().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution {
        /**
         * node.val > 前驱节点，node.val < 后继节点
         *
         * @param root
         * @return
         */
        public boolean isValidBST(TreeNode root) {
            if (root == null) return true;
            if (!isValidBST(root.left) || !isValidBST(root.right)) return false;
            TreeNode prevNode = root.left;
            TreeNode postNode = root.right;
            if (prevNode == null && postNode == null) return true;
            if (prevNode != null) {
                while (prevNode.right != null) {
                    prevNode = prevNode.right;
                }
            }
            if (postNode != null) {
                while (postNode.left != null) {
                    postNode = postNode.left;
                }
            }
            if(prevNode == null){
                return root.val < postNode.val;
            }else if(postNode == null){
                return root.val > prevNode.val;
            }else {
                return root.val < postNode.val && root.val > prevNode.val;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}