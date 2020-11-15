package algorithm1.dp;

/**
 * 动态规划求最长公共子串（字符串必须是连续的）
 */
public class LongestCommonSubstring {
    public static void main(String[] args) {
        System.out.println(lcs2("ABCD", "BABC"));
    }

    /**
     * 用一维数据进行优化
     * @param str1
     * @param str2
     * @return
     */
    private static int lcs2(String str1, String str2) {
        if (str1 == null || str1.length() < 1) return 0;
        if (str2 == null || str2.length() < 1) return 0;
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        char[] rowChars = chars1;
        char[] colChars = chars2;
        if (chars1.length < chars2.length){
            rowChars = chars2;
            colChars = chars1;
        }

        int[] dp = new int[colChars.length + 1];
        int max = 0;
        for (int row = 1; row <= rowChars.length; row++) {
            int cur = 0;
            for (int col = 1; col <= colChars.length; col++) {
                int leftTop = cur;
                cur = dp[col];
                if (rowChars[row - 1] != colChars[col - 1]){
                    dp[col] = 0;
                }else {
                    dp[col] = leftTop + 1;
                    max = Math.max(max, dp[col]);
                }
            }
        }
        return max;
    }

    private static int lcs1(String str1, String str2) {
        if (str1 == null || str1.length() < 1) return 0;
        if (str2 == null || str2.length() < 1) return 0;
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        //dp表示以i,j结尾的字符的最长公共子串
        int[][] dp = new int[chars1.length + 1][chars2.length + 1];
        int max = 0;
        for (int i = 1; i <= chars1.length; i++) {
            for (int j = 1; j <= chars2.length; j++) {
                if (chars1[i - 1] != chars2[j - 1]) continue;
                //chars1[i - 1]、chars2[j - 1]的字符相等 dp[i][j]则至少为1
                dp[i][j] = dp[i - 1][j - 1] + 1;
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }


}
