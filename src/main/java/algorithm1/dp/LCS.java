package algorithm1.dp;

/**
 * 动态规划求最长公共子序列
 */
public class LCS {
    public static void main(String[] args) {
        System.out.println(lengthOfLCS3(new int[]{1,3,6,7,9,10,11},
                                        new int[]{1,2,3,4,5,6,7}));
    }

    /**
     * 递归
     * @param arr1
     * @param arr2
     * @return
     */
    static int lengthOfLCS1(int[] arr1,int[] arr2){
        if(arr1 == null || arr1.length < 1) return 0;
        if(arr2 == null || arr2.length < 1) return 0;
        return lcs(arr1,arr1.length,arr2,arr2.length);
    }

    private static int lcs(int[] arr1, int i, int[] arr2, int j) {
        if(i == 0 || j == 0) return 0;
        if (arr1[i - 1] == arr2[j - 1]){
            return lcs(arr1,i - 1,arr2,j - 1) + 1;
        } else {
            return Math.max(lcs(arr1,i - 1,arr2,j),lcs(arr1,i,arr2,j - 1));
        }
    }

    /**
     * 动态规划
     * @param arr1
     * @param arr2
     * @return
     */
    static int lengthOfLCS2(int[] arr1,int[] arr2){
        if(arr1 == null || arr1.length < 1) return 0;
        if(arr2 == null || arr2.length < 1) return 0;
        //定义dp状态 dp[i][j]表示以i、j结尾的最长公共子串的长度
        int[][] dp = new int[arr1.length + 1][arr2.length + 1];
        //初始化dp 二维数组值为0  不用初始化
        //定义状态转换方程
        for (int i = 1; i <= arr1.length; i++) {
            for (int j = 1; j <= arr2.length; j++) {
                if(arr1[i - 1] == arr2[j - 1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[arr1.length][arr2.length];
    }

    /**
     * 动态规划(利用滚动数组进行优化)
     * @param arr1
     * @param arr2
     * @return
     */
    static int lengthOfLCS3(int[] arr1,int[] arr2){
        if(arr1 == null || arr1.length < 1) return 0;
        if(arr2 == null || arr2.length < 1) return 0;
        //定义dp状态 dp[i][j]表示以i、j结尾的最长公共子串的长度
        int[][] dp = new int[2][arr2.length + 1];
        //初始化dp 二维数组值为0  不用初始化
        //定义状态转换方程
        for (int i = 1; i <= arr1.length; i++) {
            int row = i & 1;
            int preRow = (i-1) & 1;
            for (int j = 1; j <= arr2.length; j++) {
                if(arr1[i - 1] == arr2[j - 1]){
                    dp[row][j] = dp[preRow][j-1] + 1;
                } else {
                    dp[row][j] = Math.max(dp[preRow][j],dp[row][j-1]);
                }
            }
        }
        return dp[arr1.length & 1][arr2.length];
    }
}
