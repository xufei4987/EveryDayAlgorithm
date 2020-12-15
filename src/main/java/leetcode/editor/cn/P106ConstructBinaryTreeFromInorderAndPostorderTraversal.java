//根据一棵树的中序遍历与后序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 中序遍历 inorder = [9,3,15,20,7]
//后序遍历 postorder = [9,15,7,20,3] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
// Related Topics 树 深度优先搜索 数组


package leetcode.editor.cn;

import datastructure.map.HashMap;

//Java：从中序与后序遍历序列构造二叉树
public class P106ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public static void main(String[] args) {
        Solution solution = new P106ConstructBinaryTreeFromInorderAndPostorderTraversal().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            if (inorder == null || inorder.length == 0) return null;
            if (postorder == null || postorder.length == 0) return null;
            if (inorder.length != postorder.length) return null;
            if (inorder.length == 1 || postorder.length == 1) return new TreeNode(inorder[0]);
            //中序遍历节点值对应的下标(为了快速找到根节点)
            HashMap<Integer, Integer> midIdxMap = new HashMap<>();
            for (int i = 0; i < inorder.length; i++) {
                midIdxMap.put(inorder[i], i);
            }
            return buildTree(inorder, postorder, midIdxMap, 0, inorder.length-1, 0, postorder.length-1);
        }

        /**
         * 通过中序遍历和后序构建树的根节点
         *
         * @param inorder   中序遍历结果
         * @param postorder 后序遍历结果
         * @param midIdxMap 中序遍历节点值对应的下标
         * @param inStart   中序遍历开始索引
         * @param inEnd     中序遍历结束索引
         * @param postStart 后序遍历开始索引
         * @param postEnd   后序遍历结束索引
         * @return
         */
        private TreeNode buildTree(int[] inorder, int[] postorder, HashMap<Integer, Integer> midIdxMap, int inStart, int inEnd, int postStart, int postEnd) {
            if (inStart > inEnd || postStart > postEnd) return null;
            TreeNode root = new TreeNode(postorder[postEnd]);
            int rootIdxInMid = midIdxMap.get(postorder[postEnd]);
            int leftLength = rootIdxInMid - inStart;
            TreeNode leftNode = buildTree(inorder,postorder,midIdxMap,inStart,inStart+leftLength-1,postStart,postStart+leftLength-1);
            TreeNode rightNode = buildTree(inorder,postorder,midIdxMap,rootIdxInMid+1,inEnd,postStart+leftLength,postEnd-1);
            root.left = leftNode;
            root.right = rightNode;
            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}