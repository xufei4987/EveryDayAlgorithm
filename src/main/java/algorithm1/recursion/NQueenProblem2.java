package algorithm1.recursion;

public class NQueenProblem2 {
    /**
     * 下标表示行号，值表示列号
     */
    private int queens[];
    /**
     * 某一列是否有皇后
     */
    private boolean cols[];
    /**
     * 左斜线上是否有皇后
     */
    private boolean leftTop[];
    /**
     * 右斜线上是否有皇后
     */
    private boolean rightTop[];
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
        queens = new int[n];
        cols = new boolean[n];
        leftTop = new boolean[2 * n - 1];
        rightTop = new boolean[leftTop.length];
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
            if (cols[col]) continue;
            int lt = row + col;
            if(leftTop[lt]) continue;
            int rt = row - col + (cols.length - 1);
            if(rightTop[rt]) continue;

            cols[col] = true;
            leftTop[lt] = true;
            rightTop[rt] = true;
            queens[row] = col;
            place(row + 1);
            //回溯时要还原现场
            cols[col] = false;
            leftTop[lt] = false;
            rightTop[rt] = false;
        }
    }


    public static void main(String[] args) {
        new NQueenProblem2().placeQueens(8);
    }
}
