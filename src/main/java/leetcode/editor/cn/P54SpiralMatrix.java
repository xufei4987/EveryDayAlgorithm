//给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
//
// 示例 1:
//
// 输入:
//[
// [ 1, 2, 3 ],
// [ 4, 5, 6 ],
// [ 7, 8, 9 ]
//]
//输出: [1,2,3,6,9,8,7,4,5]
//
//
// 示例 2:
//
// 输入:
//[
//  [1, 2, 3, 4],
//  [5, 6, 7, 8],
//  [9,10,11,12]
//]
//输出: [1,2,3,4,8,12,11,10,9,5,6,7]
//
// Related Topics 数组

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class P54SpiralMatrix {
    public static void main(String[] args) {
        Solution solution = new P54SpiralMatrix().new Solution();
        System.out.println(solution.spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9,10,11,12}}));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 定义4个指针，分别指向行的范围以及列的范围
         * @param matrix
         * @return
         */
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> res = new ArrayList<>();
            if(matrix == null || matrix.length == 0) return res;
            int top = 0;
            int bottom = matrix.length - 1;
            int left = 0;
            int right = matrix[0].length - 1;
            while (top <= bottom && left <= right){
                for (int i = left; i <= right; i++) {
                    res.add(matrix[top][i]);
                }
                top++;
                for (int i = top; i <= bottom; i++) {
                    res.add(matrix[i][right]);
                }
                right--;
                if (top > bottom || left > right) break;
                for (int i = right; i >= left ; i--) {
                    res.add(matrix[bottom][i]);
                }
                bottom--;
                for (int i = bottom; i >= top ; i--) {
                    res.add(matrix[i][left]);
                }
                left++;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
