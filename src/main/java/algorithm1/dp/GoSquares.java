package algorithm1.dp;

/**
 * 走方格  从左上方开始走，只能向右或者向下走 求走到（x,y）有多少种走法
 */
public class GoSquares {
    public static void main(String[] args) {
        System.out.println(goSquares(3,3));
    }

    static int goSquares(int x, int y){
        //定义动态规划状态
        int[][] dp = new int[x][y];
        //初始化dp
        for (int i = 0; i < x; i++){
            dp[i][0] = 1;
        }
        for (int i = 0; i < y; i++){
            dp[0][i] = 1;
        }
        return goSquares(dp,x-1,y-1);
    }

    static int goSquares(int[][] dp, int x, int y) {
        if(dp[x][y] == 0){
            dp[x][y] = goSquares(dp,x-1,y) + goSquares(dp,x,y-1);
        }
        return dp[x][y];
    }
}
