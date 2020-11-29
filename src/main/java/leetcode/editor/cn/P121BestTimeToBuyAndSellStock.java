//给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
//
// 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
//
// 注意：你不能在买入股票前卖出股票。
//
//
//
// 示例 1:
//
// 输入: [7,1,5,3,6,4]
//输出: 5
//解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
//
//
// 示例 2:
//
// 输入: [7,6,4,3,1]
//输出: 0
//解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
//
// Related Topics 数组 动态规划


package leetcode.editor.cn;

public class P121BestTimeToBuyAndSellStock{
    public static void main(String[] args) {
        Solution solution = new P121BestTimeToBuyAndSellStock().new Solution();
        // TO TEST
        System.out.println(solution.maxProfit1(new int[]{7, 1, 5, 3, 6, 4}));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length < 2) return 0;
            int min = Integer.MAX_VALUE;
            int maxProfit = 0;
            for (int i = 0; i < prices.length; i++) {
                min = Math.min(min,prices[i]);
                maxProfit = Math.max(maxProfit,prices[i] - min);
            }
            return maxProfit;
        }

        /**
         * 动态规划解法：
         * 1、计算出相邻2天的利润差
         * 2、转换为求最大连续子数组的方法求最大利润
         * @param prices
         * @return
         */
        public int maxProfit1(int[] prices) {
            if (prices == null || prices.length < 2) return 0;
            int[] profitDiff = new int[prices.length - 1];
            for (int i = 1; i < prices.length; i++) {
                profitDiff[i - 1] = prices[i] - prices[i-1];
            }
            //求以下标i结尾的最大连续子数组
            int[] dp = new int[profitDiff.length];
            dp[0] = profitDiff[0];
            int maxProfit = dp[0];
            for (int i = 1; i < dp.length; i++) {
                dp[i] = Math.max(dp[i-1] + profitDiff[i],profitDiff[i]);
                maxProfit = Math.max(maxProfit,dp[i]);
            }
            maxProfit = Math.max(maxProfit,0);
            return maxProfit;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
