//给定一个 n × n 的二维矩阵表示一个图像。
//
// 将图像顺时针旋转 90 度。
//
// 说明：
//
// 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
//
// 示例 1:
//
// 给定 matrix =
//[
//  [1,2,3],
//  [4,5,6],
//  [7,8,9]
//],
//
//原地旋转输入矩阵，使其变为:
//[
//  [7,4,1],
//  [8,5,2],
//  [9,6,3]
//]
//
//
// 示例 2:
//
// 给定 matrix =
//[
//  [ 5, 1, 9,11],
//  [ 2, 4, 8,10],
//  [13, 3, 6, 7],
//  [15,14,12,16]
//],
//
//原地旋转输入矩阵，使其变为:
//[
//  [15,13, 2, 5],
//  [14, 3, 4, 1],
//  [12, 6, 8, 9],
//  [16, 7,10,11]
//]
//
// Related Topics 数组


package leetcode.editor.cn;
//Java：旋转图像
public class P48RotateImage{
    public static void main(String[] args) {
        Solution solution = new P48RotateImage().new Solution();
        // TO TEST
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        solution.rotate(matrix);
        System.out.println(matrix);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void rotate(int[][] matrix) {
            //转置矩阵
            for (int row = 1; row < matrix.length; row++){
                for (int col = 0; col < row; col++){
                    int tmp = matrix[row][col];
                    matrix[row][col] = matrix[col][row];
                    matrix[col][row] = tmp;
                }
            }
            //中轴旋转
            for (int row = 0; row < matrix.length; row++){
                for (int col = 0; col < matrix.length / 2; col++){
                    int tmp = matrix[row][col];
                    matrix[row][col] = matrix[row][matrix.length - col - 1];
                    matrix[row][matrix.length - col - 1] = tmp;
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}