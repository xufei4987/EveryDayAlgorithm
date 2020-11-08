package algorithm1.dp;

/**
 * 动态规划求解0-1背包问题
 */
public class Knapsack {
    public static void main(String[] args) {
        System.out.println(maxKnapsackValue(new int[]{2, 6, 3, 4, 6}, new int[]{3, 5, 1, 4, 2},10));
    }

    /**
     * 用一维数据进行优化
     * @param weights
     * @param values
     * @param capacity
     * @return
     */
    private static int maxKnapsackValue(int[] weights, int[] values, int capacity) {
        if (weights == null || weights.length < 1) return 0;
        if (values == null || values.length < 1) return 0;
        if (weights.length != values.length || capacity < 1) return 0;
        //dp[i][j]表示前i件物品在容量为j情况下能凑出的最大价值
        int[] dp = new int[capacity + 1];
        for (int i = 1; i <= values.length; i++) {
            for (int j = capacity; j >= 1; j--) {
                if (weights[i - 1] > j){
                    continue;
                }
                //物品体积小于背包容量，当前物品有选择权，可以选，也可以不选
                //选择：当前物品价值 加上 容量去除当前物品体积后的最大价值
                //不选择：当前容量不考虑当前物品的最大价值
                dp[j] =Math.max(dp[j], values[i-1] + dp[j-weights[i-1]]);

            }
        }
        return dp[capacity];
    }

    private static int maxKnapsackValue1(int[] weights, int[] values, int capacity) {
        if (weights == null || weights.length < 1) return 0;
        if (values == null || values.length < 1) return 0;
        if (weights.length != values.length || capacity < 1) return 0;
        //dp[i][j]表示前i件物品在容量为j情况下能凑出的最大价值
        int[][] dp = new int[values.length + 1][capacity + 1];
        for (int i = 1; i <= values.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (weights[i - 1] > j){
                    //物品体积大于背包容量，就不考虑当前物品
                    dp[i][j] = dp[i-1][j];
                } else {
                    //物品体积小于背包容量，当前物品有选择权，可以选，也可以不选
                    //选择：当前物品价值 加上 容量去除当前物品体积后的最大价值
                    //不选择：当前容量不考虑当前物品的最大价值
                    dp[i][j] =Math.max(dp[i-1][j], values[i-1] + dp[i-1][j-weights[i-1]]);
                }
            }
        }
        return dp[values.length][capacity];
    }
}
