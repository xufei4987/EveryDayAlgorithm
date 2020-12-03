package leetcode.editor.cn;

/**
 * 找到最大的二叉搜索子树的节点数量
 */
public class P333LargestBstSubtree {
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

    public class TreeInfo {
        //最大bst子树的根节点
        public TreeNode root;
        //最大bst子树的最大值
        public int max;
        //最大bst子树的最小值
        public int min;
        //最大bst子树的节点数量
        public int size;

        public TreeInfo(TreeNode root, int max, int min, int size) {
            this.root = root;
            this.max = max;
            this.min = min;
            this.size = size;
        }
    }

    /**
     * 利用前序遍历求解，自顶向下进行查找
     * @param root
     * @return
     */
    public int largestBstSubtree(TreeNode root) {
        if (root == null) return 0;
        if (isBst(root)) return getCount(root);
        return Math.max(largestBstSubtree(root.left),largestBstSubtree(root.right));
    }

    private boolean isBst(TreeNode root) {
        return isBst(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }
    private boolean isBst(TreeNode root,int min,int max) {
        if (root == null) return true;
        return min < root.val
                && root.val < max
                && isBst(root.left,min,root.val)
                && isBst(root.right,root.val,max);
    }

    private int getCount(TreeNode root) {
        if (root == null) return 0;
        return getCount(root.left) + getCount(root.right) + 1;
    }

    /**
     * 利用后续遍历，自底向上进行查找
     * 查询以root为根节点的最大bst子树信息
     *
     * @param root
     * @return
     */
    public TreeInfo largestBstSubtree1(TreeNode root) {
        if (root == null) return null;
        TreeInfo lti = largestBstSubtree1(root.left);
        TreeInfo rti = largestBstSubtree1(root.right);

        int leftBstSize = -1;
        int rightBstSize = -1;
        int min = root.val;
        int max = root.val;

        if (lti == null) {
            leftBstSize = 0;
        } else if (lti.root == root.left && lti.max < root.val) {
            leftBstSize = lti.size;
            min = lti.min;
        }

        if (rti == null) {
            rightBstSize = 0;
        } else if (rti.root == root.right && rti.min > root.val) {
            rightBstSize = rti.size;
            max = rti.max;
        }
        //1、leftBstSize = 0 且 rightBstSize = 0 最大bstSubtree就是root
        //2、leftBstSize > 0 且 rightBstSize = 0 最大bstSubtree就是root + 左子树
        //3、leftBstSize = 0 且 rightBstSize > 0 最大bstSubtree就是root + 右子树
        //4、leftBstSize > 0 且 rightBstSize > 0 最大bstSubtree就是root + 左右子树
        if (leftBstSize >= 0 && rightBstSize >= 0) {
            return new TreeInfo(root, max, min, leftBstSize + rightBstSize + 1);
        }
        //下面是左右子树都无法和根节点组成bst的情况
        if (lti != null && rti != null) {
            return lti.size > rti.size ? lti : rti;
        }
        return lti != null ? lti : rti;
    }


    public static void main(String[] args) {
        TreeNode n10 = new TreeNode(10);
        TreeNode n14 = new TreeNode(14);
        TreeNode n15 = new TreeNode(15);
        TreeNode n30 = new TreeNode(30);
        TreeNode n16 = new TreeNode(16);
        TreeNode n18 = new TreeNode(18);
        TreeNode n11 = new TreeNode(11);
        TreeNode n25 = new TreeNode(25);
        TreeNode n8 = new TreeNode(8);
        TreeNode n13 = new TreeNode(13);
        TreeNode n22 = new TreeNode(22);
        TreeNode n29 = new TreeNode(29);
        n10.left = n14;
        n10.right = n15;
        n14.left = n30;
        n14.right = n16;
        n15.right = n18;
        n16.left = n11;
        n16.right = n25;
        n11.left = n8;
        n11.right = n13;
        n25.left = n22;
        n25.right = n29;

        P333LargestBstSubtree p333LargestBstSubtree = new P333LargestBstSubtree();
        System.out.println(p333LargestBstSubtree.largestBstSubtree(n10));
    }

}
