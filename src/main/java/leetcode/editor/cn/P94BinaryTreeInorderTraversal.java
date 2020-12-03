//给定一个二叉树的根节点 root ，返回它的 中序 遍历。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,null,2,3]
//输出：[1,3,2]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：[1]
// 
//
// 示例 4： 
//
// 
//输入：root = [1,2]
//输出：[2,1]
// 
//
// 示例 5： 
//
// 
//输入：root = [1,null,2]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 哈希表


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：二叉树的中序遍历
public class P94BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        Solution solution = new P94BinaryTreeInorderTraversal().new Solution();
        // TO TEST
        TreeNode root = new TreeNode(5);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    public static class TreeNode {
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
         * morris中序遍历
         *
         * @param root
         * @return
         */
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            TreeNode node = root;
            while (node != null) {
                if (node.left != null) {
                    //找前驱节点
                    TreeNode prev = node.left;
                    while (prev.right != null && prev.right != node){
                        prev = prev.right;
                    }
                    if (prev.right == null) {
                        prev.right = node;
                        node = node.left;
                    } else {
                        list.add(node.val);
                        node = node.right;
                        prev.right = null;
                    }
                } else {
                    list.add(node.val);
                    node = node.right;
                }
            }
            return list;
        }

        public List<Integer> inorderTraversal1(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            inorderTraversal(root, list);
            return list;
        }

        private void inorderTraversal(TreeNode root, List<Integer> list) {
            if (root == null) return;
            inorderTraversal(root.left, list);
            list.add(root.val);
            inorderTraversal(root.right, list);
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}