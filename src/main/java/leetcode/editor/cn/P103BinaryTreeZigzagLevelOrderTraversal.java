//给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。 
//
// 例如： 
//给定二叉树 [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回锯齿形层次遍历如下： 
//
// [
//  [3],
//  [20,9],
//  [15,7]
//]
// 
// Related Topics 栈 树 广度优先搜索


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Java：二叉树的锯齿形层次遍历
public class P103BinaryTreeZigzagLevelOrderTraversal {
    public static void main(String[] args) {
        Solution solution = new P103BinaryTreeZigzagLevelOrderTraversal().new Solution();
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

        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) return result;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            boolean fromLeft = true;
            while (!queue.isEmpty()){
                LinkedList<Integer> level = new LinkedList<>();
                int levelSize = queue.size();
                for (int i = 0; i < levelSize; i++) {
                    TreeNode treeNode = queue.poll();
                    if(treeNode.left != null){
                        queue.offer(treeNode.left);
                    }
                    if(treeNode.right!=null){
                        queue.offer(treeNode.right);
                    }
                    if(fromLeft){
                        level.addLast(treeNode.val);
                    } else {
                        level.addFirst(treeNode.val);
                    }
                }
                result.add(level);
                fromLeft = !fromLeft;
            }
            return result;
        }

        /**
         * 普通层序遍历 对偶数层进行翻转
         * @param root
         * @return
         */
        public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) return result;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int levelCount = 0;
            while (!queue.isEmpty()){
                levelCount++;
                List<Integer> level = new ArrayList<>();
                int levelSize = queue.size();
                for (int i = 0; i < levelSize; i++) {
                    TreeNode treeNode = queue.poll();
                    if(treeNode.left != null){
                        queue.offer(treeNode.left);
                    }
                    if(treeNode.right!=null){
                        queue.offer(treeNode.right);
                    }
                    level.add(treeNode.val);
                }
                if (levelCount % 2 == 0){
                    int left = 0;
                    int right = level.size() - 1;
                    while (left < right){
                        int tmp = level.get(left);
                        level.set(left,level.get(right));
                        level.set(right,tmp);
                        left++;
                        right--;
                    }
                }
                result.add(level);
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}