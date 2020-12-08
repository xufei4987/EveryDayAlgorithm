//实现 pow(x, n) ，即计算 x 的 n 次幂函数。
//
// 示例 1:
//
// 输入: 2.00000, 10
//输出: 1024.00000
//
//
// 示例 2:
//
// 输入: 2.10000, 3
//输出: 9.26100
//
//
// 示例 3:
//
// 输入: 2.00000, -2
//输出: 0.25000
//解释: 2-2 = 1/22 = 1/4 = 0.25
//
// 说明:
//
//
// -100.0 < x < 100.0
// n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
//
// Related Topics 数学 二分查找

package leetcode.editor.cn;

public class P50PowxN {
    public static void main(String[] args) {
        Solution solution = new P50PowxN().new Solution();
        System.out.println(solution.myPow(2.0, 10));
        System.out.println(solution.powMod(123,456,789));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 利用数学定理进行求解(快速幂)
         *
         * @param x
         * @param n
         * @return
         */
        public double myPow(double x, int n) {
            if (n == 0) return 1;
            boolean neg = n < 0;
            //由于-2的31次方转换为正数后大于int的最大值（越界） 所以需要转换为long
            long y = neg ? -((long) n) : n;
            double res = 1;
            while (y > 0) {
                if (y % 2 == 1) {
                    res *= x;
                }
                x *= x;
                y = y >> 1;
            }
            return neg ? 1 / res : res;
        }

        /**
         * 利用分治的思想进行解决
         *
         * @param x
         * @param n
         * @return
         */
        public double myPow1(double x, int n) {
            if (n == 0) return 1;
            boolean neg = n < 0;
            //由于-2的31次方转换为正数后大于int的最大值（越界） 所以需要转换为long
            long y = neg ? -((long) n) : n;
            return neg ? 1 / pow(x, y) : pow(x, y);
        }

        private double pow(double x, long y) {
            if (y == 1) return x;
            boolean odd = y % 2 == 1;
            double half = pow(x, y >> 1);
            return odd ? half * half * x : half * half;
        }

        /**
         * 求解x^y%z:
         * 由于直接计算x^y次方会溢出，所以需要转换
         * x^2%z == ((x%z)*(x%z))%z
         * x >= 0; y > 0; z != 0
         */
        public int powMod(int x, int y, int z) {
            if (x == 0) return 0;
            if (y == 1) return x % z;
            boolean odd = (y & 1) == 1;
            int half = powMod(x, y >> 1, z) % z;
            return odd ? (half * half * x % z) % z : (half * half) % z;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
