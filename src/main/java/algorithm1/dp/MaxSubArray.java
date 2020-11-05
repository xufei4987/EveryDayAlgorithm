package algorithm1.dp;

import java.util.concurrent.Callable;

/**
 * 最大连续子数组
 * -2、1、-3、4、-1、2、1、-5、4 的最大连续子数组为 4 + (-1) + 2 + 1 = 6
 *
 */
public class MaxSubArray {
    public static void main(String[] args) throws Exception {
        int[] arr = new int[]{-2,1,-3,4,-1,2,1};
        timeTest(() -> maxSubArray1(arr));
    }

    private static void timeTest(Callable<Integer> callable) throws Exception {
        long start = System.currentTimeMillis();
        Integer result = callable.call();
        System.out.println("结果为：" + result + ",消耗时间：" + (System.currentTimeMillis() - start) + "ms");
    }

    /**
     * 动态规划求最大连续子序列和
     * @param arr
     * @return
     */
    static int maxSubArray1(int[] arr){
        //定义状态，dp[i]表示已第i个数结尾的最大连续子序列和
        int[] dp = new int[arr.length];
        //初始化状态
        dp[0] = arr[0];
        int max = dp[0];
        for (int i = 1; i < arr.length; i++) {
            //状态转移方程
            dp[i] = Math.max(arr[i],dp[i-1] + arr[i]);
            max = Math.max(dp[i],max);
        }
        return max;
    }

}
