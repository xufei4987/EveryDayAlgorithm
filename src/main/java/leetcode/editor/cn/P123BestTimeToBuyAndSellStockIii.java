//给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。 
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。 
//
// 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 示例 1: 
//
// 输入: [3,3,5,0,0,3,1,4]
//输出: 6
//解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
//     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。 
//
// 示例 2: 
//
// 输入: [1,2,3,4,5]
//输出: 4
//解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。   
//     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。   
//     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
// 
//
// 示例 3: 
//
// 输入: [7,6,4,3,1] 
//输出: 0 
//解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。 
// Related Topics 数组 动态规划


package leetcode.editor.cn;

public class P123BestTimeToBuyAndSellStockIii {
    public static void main(String[] args) {
        Solution solution = new P123BestTimeToBuyAndSellStockIii().new Solution();
        // TO TEST
        System.out.println(solution.maxProfit(new int[]{1,2,3,4,5}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length < 2) return 0;
            int n = prices.length;
            //dp表示第i天第j次操作后的利润总和
            int[][] dp = new int[n][5];
            dp[0][0] = 0;           //第一天未买卖(初始状态)
            dp[0][1] = -prices[0];  //第一天第一次买入
            dp[0][2] = 0;           //第一天第一次卖出
            dp[0][3] = -prices[0];  //第一天第二次买入
            dp[0][4] = 0;           //第一天第二次卖出
            for (int i = 1; i < n; i++) {
                dp[i][0] = dp[i - 1][0]; //初始状态状态转移
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]); //第一次买入：昨天已经买入 或者 今天买入
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]); //第一次卖出：昨天已经卖出 或者 今天卖出
                dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
                dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
            }
            return Math.max(Math.max(Math.max(dp[n - 1][0], dp[n - 1][1]), Math.max(dp[n - 1][2], dp[n - 1][3])), dp[n - 1][4]);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}