//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。 
//
// 
//
// 上图为 8 皇后问题的一种解法。 
//
// 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。 
//
// 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 
//
// 示例： 
//
// 输入：4
//输出：[
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
//解释: 4 皇后问题存在两个不同的解法。
// 
//
// 
//
// 提示： 
//
// 
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。 
// 
// Related Topics 回溯算法


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：N 皇后
public class P51NQueens {
    public static void main(String[] args) {
        Solution solution = new P51NQueens().new Solution();
        // TO TEST
        List<List<String>> listList = solution.solveNQueens(4);
        System.out.println(listList);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<List<String>> answer;
        int[] queens;

        public List<List<String>> solveNQueens(int n) {
            if (n < 1) return null;
            answer = new ArrayList<>();
            queens = new int[n];
            placeQueen(0);
            return answer;
        }

        private void placeQueen(int n) {
            if (n == queens.length) {
                List<String> rowList = new ArrayList<>();
                for (int row = 0; row < queens.length; row++) {
                    StringBuilder sb = new StringBuilder();
                    for (int col = 0; col < queens.length; col++) {
                        if (col == queens[row]) {
                            sb.append("Q");
                        } else {
                            sb.append(".");
                        }
                    }
                    rowList.add(sb.toString());
                }
                answer.add(rowList);
                return;
            }
            for (int col = 0; col < queens.length; col++) {
                if(isValid(n,col)){
                    queens[n] = col;
                    placeQueen(n+1);
                }
            }
        }

        private boolean isValid(int row, int col) {
            for (int i = 0; i < row; i++) {
                if (queens[i] == col) return false;
                if (row - i == Math.abs(col - queens[i])) return false;
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}