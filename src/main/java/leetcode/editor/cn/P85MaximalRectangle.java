//给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [['1','0','1','0','0'],['1','0','1','1','1'],['1','1','1','1','1']
//,['1','0','0','1','0']]
//输出：6
//解释：最大矩形如上图所示。
// 
//
// 示例 2： 
//
// 
//输入：matrix = []
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：matrix = [['0']]
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：matrix = [['1']]
//输出：1
// 
//
// 示例 5： 
//
// 
//输入：matrix = [['0','0']]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// rows == matrix.length 
// cols == matrix.length 
// 0 <= row, cols <= 200 
// matrix[i][j] 为 '0' 或 '1' 
// 
// Related Topics 栈 数组 哈希表 动态规划


package leetcode.editor.cn;
public class P85MaximalRectangle{
    public static void main(String[] args) {
        Solution solution = new P85MaximalRectangle().new Solution();
        // TO TEST
        char[][] matrix = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}};
        System.out.println(solution.maximalRectangle(matrix));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maximalRectangle(char[][] matrix) {
            if (matrix == null || matrix.length < 1) return 0;
            //定义状态 dp[i][j]表示以坐标（i,j）为右下角顶点的最大宽度
            int[][] dp = new int[matrix.length][matrix[0].length];
            int max = 0;
            for (int row = 0; row < matrix.length; row++) {
                for (int col = 0; col < matrix[0].length; col++) {
                    if(matrix[row][col] == '1'){
                        dp[row][col] = col == 0 ? 1 : dp[row][col-1] + 1;
                    }
                    int width = dp[row][col];

                    for (int i = row; i >= 0; i--) {
                        width = Math.min(width,dp[i][col]);
                        max = Math.max(max,width * (row - i + 1));
                    }
                }
            }

            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}