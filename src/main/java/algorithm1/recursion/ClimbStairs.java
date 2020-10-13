package algorithm1.recursion;

/**
 * 跳台阶：一次能跳1阶或者2阶 求n阶台阶的跳法
 * f(n) = f(n-1) + f(n-2)
 * f(1) = 1  f(2) = 2
 */
public class ClimbStairs {

    public static int climbStairs(int n) {
        if (n <= 2) return n;
        int first = 1;
        int second = 2;
        for (int i = 3; i <=n; i++) {
            second = first + second;
            first = second - first;
        }
        return second;
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(3));
        System.out.println(climbStairs(4));
        System.out.println(climbStairs(5));
    }

}
