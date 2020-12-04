//根据一棵树的前序遍历与中序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
// Related Topics 树 深度优先搜索 数组


package leetcode.editor.cn;

import datastructure.map.HashMap;
import datastructure.map.Map;

//Java：从前序与中序遍历序列构造二叉树
public class P105ConstructBinaryTreeFromPreorderAndInorderTraversal{
    public static void main(String[] args) {
        Solution solution = new P105ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
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
        Map<Integer, Integer> idxInorderMap;

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if (preorder == null || inorder == null) return null;
            if (preorder.length == 0 || inorder.length == 0) return null;
            idxInorderMap = new HashMap<>();
            for (int i = 0; i < inorder.length; i++) {
                idxInorderMap.put(inorder[i],i);
            }
            return buildTree(preorder,inorder,0,preorder.length-1,0,inorder.length-1);
        }

        /**
         * 通过前序和中序构建树的根节点
         * @param preorder 前序遍历序列
         * @param inorder 中序遍历序列
         * @param preorder_left 前序遍历开始索引
         * @param preorder_right 前序遍历结束索引
         * @param inorder_left 中序遍历开始索引
         * @param inorder_right 中序遍历结束索引
         * @return
         */
        private TreeNode buildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
            if (preorder_left > preorder_right || inorder_left > inorder_right) return null;
            int rootVal = preorder[preorder_left];
            TreeNode root = new TreeNode(rootVal);
            int rootIdx_inorder = idxInorderMap.get(rootVal);
            int left_length = rootIdx_inorder - inorder_left;
            TreeNode left = buildTree(preorder,inorder,preorder_left+1,preorder_left+left_length,inorder_left,rootIdx_inorder-1);
            TreeNode right = buildTree(preorder,inorder,preorder_left+left_length+1,preorder_right,rootIdx_inorder+1,inorder_right);
            root.left = left;
            root.right = right;
            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}