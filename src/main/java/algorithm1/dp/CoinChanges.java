package algorithm1.dp;

/**
 * 有面值为1、5、20、25的硬币  求用最少的硬币凑出指定的金额
 */
public class CoinChanges {
    public static void main(String[] args) {
        CoinChanges coinChanges = new CoinChanges();
        System.out.println(coinChanges.coins4(41));
    }

    /**
     * 递推，自底向上求解
     *
     * @param n 指定的金额
     * @return 最少硬币数
     */
    public int coins4(int n) {
        //不合理的
        if (n < 1) return Integer.MAX_VALUE;
        int[] dp = new int[n + 1];
        int[] faces = new int[dp.length];
        for (int i = 1; i < dp.length; i++) {
            int min = Integer.MAX_VALUE;
            if (i >= 1 && dp[i - 1] < min) {
                min = dp[i - 1];
                //指定i金额的最后一个硬币的面值
                faces[i] = 1;
            }
            if (i >= 5 && dp[i - 5] < min) {
                min = dp[i - 5];
                faces[i] = 5;
            }
            if (i >= 20 && dp[i - 20] < min) {
                min = dp[i - 20];
                faces[i] = 20;
            }
            if (i >= 25 && dp[i - 25] < min) {
                min = dp[i - 25];
                faces[i] = 25;
            }
            dp[i] = min + 1;
        }
        printFaces(faces, n);
        return dp[n];
    }

    private void printFaces(int[] faces, int n) {
        while (n > 0){
            System.out.println(faces[n]);
            n -= faces[n];
        }
    }

    /**
     * 递推，自底向上求解
     *
     * @param n 指定的金额
     * @return 最少硬币数
     */
    public int coins3(int n) {
        //不合理的
        if (n < 1) return Integer.MAX_VALUE;
        int[] dp = new int[n + 1];
        for (int i = 1; i < dp.length; i++) {
//            int min = dp[i - 1];
            int min = Integer.MAX_VALUE;
            if (i >= 1) min = Math.min(dp[i - 1], min);
            if (i >= 5) min = Math.min(dp[i - 5], min);
            if (i >= 20) min = Math.min(dp[i - 20], min);
            if (i >= 25) min = Math.min(dp[i - 25], min);
            dp[i] = min + 1;
        }
        return dp[n];
    }

    /**
     * 记忆化搜索，自定向下的调用
     *
     * @param n 指定的金额
     * @return 最少硬币数
     */
    public int coins2(int n) {
        //不合理的
        if (n < 1) return Integer.MAX_VALUE;
        int[] dp = new int[n + 1];
        int[] faces = {1, 5, 20, 25};
        //防止n<face数组越界
        for (int face : faces) {
            if (n < face) {
                break;
            }
            dp[face] = 1;
        }
        return coins2(n, dp);
    }

    private int coins2(int n, int[] dp) {
        if (n < 1) return Integer.MAX_VALUE;
        if (dp[n] == 0) {
            int min1 = Math.min(coins2(n - 1, dp), coins2(n - 5, dp));
            int min2 = Math.min(coins2(n - 20, dp), coins2(n - 25, dp));
            dp[n] = Math.min(min1, min2) + 1;
        }
        return dp[n];
    }

    /**
     * 暴力递归，自定向下的调用，出现了重复的子问题
     *
     * @param n 指定的金额
     * @return 最少硬币数
     */
    public int coins1(int n) {
        //不合理的
        if (n < 1) return Integer.MAX_VALUE;
        if (n == 25 || n == 20 || n == 5 || n == 1) return 1;
        int min1 = Math.min(coins1(n - 1), coins1(n - 5));
        int min2 = Math.min(coins1(n - 20), coins1(n - 25));
        return Math.min(min1, min2) + 1;
    }
}
