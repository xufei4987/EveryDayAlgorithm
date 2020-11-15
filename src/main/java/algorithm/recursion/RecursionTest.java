package algorithm.recursion;

public class RecursionTest {
    public static void main(String[] args) {
        test(10);
        System.out.println(factorial(5));
    }

    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        }
        System.out.println("n = " + n);
    }

    /**
     * n的阶乘
     *
     * @param n
     * @return
     */
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
