//给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。 
//
// 
//
// 示例： 
//
// 输入：3
//输出：
//[
//  [1,null,3,2],
//  [3,2,null,1],
//  [3,1,null,null,2],
//  [2,1,3],
//  [1,null,2,null,3]
//]
//解释：
//以上的输出对应以下 5 种不同结构的二叉搜索树：
//
//   1         3     3      2      1
//    \       /     /      / \      \
//     3     2     1      1   3      2
//    /     /       \                 \
//   2     1         2                 3
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n <= 8 
// 
// Related Topics 树 动态规划


package leetcode.editor.cn;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

//Java：不同的二叉搜索树 II
public class P95UniqueBinarySearchTreesIi {
    public static void main(String[] args) {
        Solution solution = new P95UniqueBinarySearchTreesIi().new Solution();
        // TO TEST
        List<TreeNode> treeNodes = solution.generateTrees(3);
        System.out.println(treeNodes);
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
        public List<TreeNode> generateTrees(int n) {
            List<TreeNode> result = new ArrayList<>();
            if(n > 0) {
                result.addAll(generateTrees(1,n));
            }
            return result;
        }

        /**
         * 函数功能：返回以[start,end]为根节点的所有bst
         * @param start
         * @param end
         * @return
         */
        private List<TreeNode> generateTrees(int start, int end) {
            List<TreeNode> list = new ArrayList<>();
            if(start > end){
                //需要添加null节点，以组成度<2的节点
                list.add(null);
                return list;
            }
            for (int i = start; i <= end; i++) {
                //返回以[start,i-1]为根节点的所有bst
                List<TreeNode> ll = generateTrees(start,i-1);
                //返回以[i+1,end]为根节点的所有bst
                List<TreeNode> rl = generateTrees(i+1,end);
                for (TreeNode ln : ll){
                    for (TreeNode rn : rl){
                        //和i组成一颗树
                        TreeNode cur = new TreeNode(i);
                        cur.left = ln;
                        cur.right = rn;
                        list.add(cur);
                    }
                }
            }
            return list;

        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}