//判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。 
//
// 
// 数字 1-9 在每一行只能出现一次。 
// 数字 1-9 在每一列只能出现一次。 
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。 
// 
//
// 
//
// 上图是一个部分填充的有效的数独。 
//
// 数独部分空格内已填入了数字，空白格用 '.' 表示。 
//
// 示例 1: 
//
// 输入:
//[
//  ["5","3",".",".","7",".",".",".","."],
//  ["6",".",".","1","9","5",".",".","."],
//  [".","9","8",".",".",".",".","6","."],
//  ["8",".",".",".","6",".",".",".","3"],
//  ["4",".",".","8",".","3",".",".","1"],
//  ["7",".",".",".","2",".",".",".","6"],
//  [".","6",".",".",".",".","2","8","."],
//  [".",".",".","4","1","9",".",".","5"],
//  [".",".",".",".","8",".",".","7","9"]
//]
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//[
//  ["8","3",".",".","7",".",".",".","."],
//  ["6",".",".","1","9","5",".",".","."],
//  [".","9","8",".",".",".",".","6","."],
//  ["8",".",".",".","6",".",".",".","3"],
//  ["4",".",".","8",".","3",".",".","1"],
//  ["7",".",".",".","2",".",".",".","6"],
//  [".","6",".",".",".",".","2","8","."],
//  [".",".",".","4","1","9",".",".","5"],
//  [".",".",".",".","8",".",".","7","9"]
//]
//输出: false
//解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
//     但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。 
//
// 说明: 
//
// 
// 一个有效的数独（部分已被填充）不一定是可解的。 
// 只需要根据以上规则，验证已经填入的数字是否有效即可。 
// 给定数独序列只包含数字 1-9 和字符 '.' 。 
// 给定数独永远是 9x9 形式的。 
// 
// Related Topics 哈希表


package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//Java：有效的数独
public class P36ValidSudoku{
    public static void main(String[] args) {
        Solution solution = new P36ValidSudoku().new Solution();
        // TO TEST
        char[][] board = {
                            {'5','3','1','.','7','.','.','.','.'},
                            {'6','.','.','1','9','5','.','.','.'},
                            {'.','9','8','.','.','.','.','6','.'},
                            {'8','.','.','.','6','.','.','.','3'},
                            {'4','.','.','8','.','3','.','.','1'},
                            {'7','.','.','.','2','.','.','.','6'},
                            {'.','6','.','.','.','.','2','8','.'},
                            {'.','.','.','4','1','9','.','.','5'},
                            {'.','.','.','.','8','.','.','7','9'}
                        };
        System.out.println(solution.isValidSudoku(board));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isValidSudoku(char[][] board) {
            Map<Character, Boolean> map = new HashMap<>();
            for (int row = 0; row < board.length; row++){
                map.clear();
                for (int col = 0; col < board.length; col++){
                    if (map.get(board[row][col]) != null && map.get(board[row][col]) == true && board[row][col] != '.') return false;
                    map.put(board[row][col],true);
                }
            }
            for (int col = 0; col < board.length; col++){
                map.clear();
                for (int row = 0; row < board.length; row++){
                    if (map.get(board[row][col]) != null && map.get(board[row][col]) == true && board[row][col] != '.') return false;
                    map.put(board[row][col],true);
                }
            }
            for (int a = 0; a < board.length; a += 3){
                for (int b = 0; b < board.length; b += 3){
                    map.clear();
                    for (int row = a; row < a + 3; row++){
                        for (int col = b; col < b + 3; col++){
                            if (map.get(board[row][col]) != null && map.get(board[row][col]) == true && board[row][col] != '.') return false;
                            map.put(board[row][col],true);
                        }
                    }
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}