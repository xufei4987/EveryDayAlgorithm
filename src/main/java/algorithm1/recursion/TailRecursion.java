package algorithm1.recursion;

/**
 * 尾递归：尾调用的特殊情况，有一些编译器可以对尾调用进行优化，调用函数时不开辟新的栈空间
 * jvm只能对尾递归进行优化，因为无法改变栈帧的大小
 */
public class TailRecursion {
    public static int factorial(int n) {
        return factorial(n, 1);
    }

    public static int factorial(int n, int result) {
        if (n <= 1) return result;
        return factorial(n - 1, result * n);
    }

    public static void main(String[] args) {
        System.out.println(factorial(5));
    }
}
