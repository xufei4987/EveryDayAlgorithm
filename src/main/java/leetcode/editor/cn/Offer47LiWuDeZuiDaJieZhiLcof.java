//在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直
//到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
//
//
//
// 示例 1:
//
// 输入:
//[
//  [1,3,1],
//  [1,5,1],
//  [4,2,1]
//]
//输出: 12
//解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
//
//
//
// 提示：
//
//
// 0 < grid.length <= 200
// 0 < grid[0].length <= 200
//
// Related Topics 动态规划

package leetcode.editor.cn;

public class Offer47LiWuDeZuiDaJieZhiLcof {
    public static void main(String[] args) {
        Solution solution = new Offer47LiWuDeZuiDaJieZhiLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxValue(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return 0;
            int row = grid.length;
            int col = grid[0].length;
            //当前位置获取礼物的最大价值
            int[][] dp = new int[row][col];
            //初始化
            dp[0][0] = grid[0][0];
            for (int r = 1; r < row; r++) {
                dp[r][0] = dp[r - 1][0] + grid[r][0];
            }
            for (int c = 1; c < col; c++) {
                dp[0][c] = dp[0][c - 1] + grid[0][c];
            }
            for (int i = 1; i < row; i++) {
                for (int j = 1; j < col; j++) {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]) + grid[i][j];
                }
            }
            return dp[row-1][col-1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
