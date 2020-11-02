package algorithm1.recursion;

public class NQueenProblem1 {
    /**
     * 下标表示行号，值表示列号
     */
    private int cols[];
    /**
     * 解法数量
     */
    private int nAnswer;

    /**
     * 摆放n皇后
     *
     * @param n
     */
    public void placeQueens(int n) {
        if (n < 1) return;
        cols = new int[n];
        place(0);
        System.out.println(n + "皇后一共有" + nAnswer + "种摆法");
    }

    /**
     * 摆放第row行的皇后
     *
     * @param row
     */
    private void place(int row) {
        if (row == cols.length) {
            nAnswer++;
            return;
        }
        for (int col = 0; col < cols.length; col++) {
            if (isValid(row, col)) {
                cols[row] = col;
                place(row + 1);
            }
        }
    }

    private boolean isValid(int row, int col) {
        for (int i = 0; i < row; i++) {
            //第col列已经有皇后
            if (cols[i] == col) return false;
            //45°对角线有皇后（斜率的绝对值=1）
            if (row - i == Math.abs(col - cols[i])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        new NQueenProblem1().placeQueens(13);
    }
}
