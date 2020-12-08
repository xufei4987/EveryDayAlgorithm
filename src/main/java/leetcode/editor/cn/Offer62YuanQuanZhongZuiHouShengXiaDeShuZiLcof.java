//0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
//
// 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
//
//
//
// 示例 1：
//
// 输入: n = 5, m = 3
//输出: 3
//
//
// 示例 2：
//
// 输入: n = 10, m = 17
//输出: 2
//
//
//
//
// 限制：
//
//
// 1 <= n <= 10^5
// 1 <= m <= 10^6
//
//

package leetcode.editor.cn;

public class Offer62YuanQuanZhongZuiHouShengXiaDeShuZiLcof {
    public static void main(String[] args) {
        Solution solution = new Offer62YuanQuanZhongZuiHouShengXiaDeShuZiLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 利用公式求解：f(n,m) = (f(n-1,m) + m) % n
         * 例如:
         * f(5,3) = (f(4,3) + 3) % 5
         * f(4,3) = (f(3,3) + 3) % 4
         * f(3,3) = (f(2,3) + 3) % 3
         * f(2,3) = (f(1,3) + 3) % 2
         * f(1,3) = 0
         * @param n
         * @param m
         * @return
         */
        public int lastRemaining(int n, int m) {
            int res = 0;
            if (n == 1) return res;
            for (int i = 2; i <= n; i++){
                res = (res + m) % i;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
