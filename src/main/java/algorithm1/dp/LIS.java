package algorithm1.dp;

/**
 * 动态规划求最长上升子序列
 */
public class LIS {
    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{1,-1,3,7,6,10,-2}));
    }

    static int lengthOfLIS(int[] arr){
        //定义状态，dp[i]表示已第i个数结尾的最大上升子序列的长度
        int[] dp = new int[arr.length];
        //初始化状态
        int max = dp[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            dp[i] = 1;
            //遍历之前的dp，尝试找到拼接后的最长上升子串
            for (int j = 0; j < i; j++) {
                if(arr[j] >= arr[i]) continue;
                //状态转移方程
                dp[i] = Math.max(dp[i],dp[j] + 1);
            }
            max = Math.max(dp[i],max);
        }
        return max;
    }
}
