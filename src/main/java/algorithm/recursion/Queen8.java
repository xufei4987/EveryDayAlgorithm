package algorithm.recursion;

public class Queen8 {
    private static final int MAX = 8;
    private int[] arr = new int[MAX]; //存放八皇后问题的解法
    private int count = 0;
    private void print() {
        for (int e : arr) {
            System.out.print(e + "\t");
        }
        System.out.println();
    }

    /**
     * 放置低n个皇后时，是否与前面的皇后冲突
     * 规则：不在同一行、同一列、同一斜线就是不冲突
     * @param n n从0开始  代表第一个皇后
     * @return 不冲突返回true
     */
    private boolean judge(int n){
        for(int i = 0; i < n; i++) {
            if(arr[n] == arr[i] || Math.abs(arr[n] - arr[i]) == Math.abs(n - i)){
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param n
     */
    private void check(int n){
        //n==8时其实是放置第九个皇后了，说明前面8个皇后已经放好了，直接打印并返回
        if(n == MAX){
            count++;
            print();
            return;
        }
        for(int i = 0; i < MAX; i++){
            arr[n] = i;
            if(judge(n)){
                check(n + 1);
            }
        }
    }

    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.printf("8皇后一共%d种解法",queen8.count);
    }
}
