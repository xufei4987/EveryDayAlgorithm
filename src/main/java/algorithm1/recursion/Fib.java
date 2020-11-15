package algorithm1.recursion;

/**
 * 斐波那契数列
 * 1、1、2、3、5、8、13
 */
public class Fib {
    /**
     * O(2^n)
     * @param n
     * @return
     */
    public static int fib1(int n){
        if(n <= 2) return 1;
        return fib1(n-1) + fib1(n-2);
    }
    /**
     * 使用数组进行优化
     * @param n
     * @return
     */
    public static int fib2(int n){
        if(n <= 2) return 1;
        int[] arr = new int[n + 1];
        arr[1] = 1;
        arr[2] = 1;
        return fib2(n,arr);
    }

    private static int fib2(int n, int[] arr) {
        if(arr[n] == 0){
            arr[n] = fib2(n-1,arr) + fib2(n-2,arr);
        }
        return arr[n];
    }

    /**
     * 使用滚动数组进行优化
     * % 2 等价于 & 1   取余效率比较低
     * @param n
     * @return
     */
    public static int fib3(int n){
        if(n <= 2) return 1;
        int[] arr = new int[]{1,1};
        for (int i = 3; i <= n; i++){
//            arr[i % 2] = arr[(i-1) % 2] + arr[(i-2) % 2];
            arr[i & 1] = arr[(i-1) & 1] + arr[(i-2) & 1];
        }
        return arr[n & 1];
    }


    public static void main(String[] args) {
        int n = 10;
        System.out.println(fib1(n));
        System.out.println(fib2(n));
        System.out.println(fib3(n));
    }
}
