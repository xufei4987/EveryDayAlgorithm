//给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
//
//
// 二叉树的根是数组中的最大元素。
// 左子树是通过数组中最大值左边部分构造出的最大二叉树。
// 右子树是通过数组中最大值右边部分构造出的最大二叉树。
//
//
// 通过给定的数组构建最大二叉树，并且输出这个树的根节点。
//
//
//
// 示例 ：
//
// 输入：[3,2,1,6,0,5]
//输出：返回下面这棵树的根节点：
//
//      6
//    /   \
//   3     5
//    \    /
//     2  0
//       \
//        1
//
//
//
//
// 提示：
//
//
// 给定的数组的大小在 [1, 1000] 之间。
//
// Related Topics 树

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Stack;

public class P654MaximumBinaryTree {
    public static void main(String[] args) {
        Solution solution = new P654MaximumBinaryTree().new Solution();
        solution.findParentIndex(new int[]{3, 2, 1, 6, 0, 5});
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
        public TreeNode constructMaximumBinaryTree(int[] nums) {
            if (nums == null || nums.length == 0) return null;
            if (nums.length == 1) return new TreeNode(nums[0]);
            return findroot(nums, 0, nums.length);
        }

        /**
         * 在[l,r)的范围内返回根节点
         *
         * @param nums
         * @param l
         * @param r
         * @return
         */
        private TreeNode findroot(int[] nums, int l, int r) {
            if (l == r) return null;
            int maxIdx = l;
            for (int i = l + 1; i < r; i++) {
                if (nums[i] > nums[maxIdx]) {
                    maxIdx = i;
                }
            }
            TreeNode root = new TreeNode(nums[maxIdx]);
            root.left = findroot(nums, l, maxIdx);
            root.right = findroot(nums, maxIdx + 1, r);
            return root;
        }

        /**
         * 变种题目：返回每个节点对应父节点的索引（每个节点向左向右找到最大值，其中两个最大值中较小的为父节点）
         * 找最大值利用单调递减栈
         * [-1, 0, 1, -1, 3, 3]
         * [3, 3, 3, -1, 5, -1]
         * [3, 0, 1, -1, 5, 3]
         * @param nums
         * @return
         */
        public int[] findParentIndex(int[] nums) {
            Stack<Integer> stack = new Stack<>();
            int[] leftMax = new int[nums.length];//存储节点左边第一个比他大的值索引
            int[] rightMax = new int[nums.length];//存储节点右边第一个比他大值索引
            //初始化
            for (int i = 0; i < nums.length; i++) {
                leftMax[i] = -1;
                rightMax[i] = -1;
            }
            for (int i = 0; i < nums.length; i++) {
                //当前元素比栈顶元素大，弹出栈顶元素，并且当前元素是栈顶元素右边第一个比栈顶元素大的元素
                while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                    rightMax[stack.pop()] = i;
                }
                //当前元素比栈顶元素小，则栈顶元素是当前元素左边第一个比当前元素大的值
                if (!stack.isEmpty()) {
                    leftMax[i] = stack.peek();
                }
                stack.push(i);
            }
            System.out.println(Arrays.toString(leftMax));
            System.out.println(Arrays.toString(rightMax));
            int[] parent = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                if (leftMax[i] == -1 && rightMax[i] == -1) {
                    parent[i] = -1;
                } else if (leftMax[i] == -1) {
                    parent[i] = rightMax[i];
                } else if (rightMax[i] == -1) {
                    parent[i] = leftMax[i];
                } else if (nums[leftMax[i]] > nums[rightMax[i]]) {
                    parent[i] = rightMax[i];
                } else {
                    parent[i] = leftMax[i];
                }
            }
            System.out.println(Arrays.toString(parent));
            return parent;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
