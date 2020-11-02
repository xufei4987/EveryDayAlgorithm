package algorithm1.recursion;

import java.util.concurrent.Callable;

/**
 * 最大连续子数组
 * -2、1、-3、4、-1、2、1、-5、4 的最大连续子数组为 4 + (-1) + 2 + 1 = 6
 *
 */
public class MaxSubArray {
    public static void main(String[] args) throws Exception {
        int[] arr = new int[]{-2,1,-3,4,-1,2,1,-5,4,5,8,2,-4,-9,-3,-2,-8,6,7,8,9,1};
        timeTest(() -> maxSubArray1(arr));
        timeTest(() -> maxSubArray2(arr));
        timeTest(() -> maxSubArray3(arr));
    }

    private static void timeTest(Callable<Integer> callable) throws Exception {
        long start = System.currentTimeMillis();
        Integer result = callable.call();
        System.out.println("结果为：" + result + ",消耗时间：" + (System.currentTimeMillis() - start) + "ms");
    }

    /**
     * 暴力做法  性能最差
     * O(n^3)
     * @param arr
     * @return
     */
    public static int maxSubArray1(int[] arr){
        if (arr == null || arr.length < 1) return 0;
        int max = 0;
        for (int start = 0; start < arr.length; start++){
            for (int end = start; end < arr.length; end++) {
                int sum = 0;
                for (int n = start; n <= end; n++){
                    sum += arr[n];
                }
                max = Math.max(max,sum);
            }
        }
        return max;
    }

    /**
     * 对重复计算进行优化
     * O(n^2)
     * @param arr
     * @return
     */
    public static int maxSubArray2(int[] arr){
        if (arr == null || arr.length < 1) return 0;
        int max = 0;
        for (int start = 0; start < arr.length; start++){
            int sum = 0;
            for (int end = start; end < arr.length; end++) {
                sum += arr[end];
                max = Math.max(max,sum);
            }
        }
        return max;
    }

    /**
     * 利用分治法求解 O(nlogn)
     * @param arr
     * @return
     */
    public static int maxSubArray3(int[] arr){
        if (arr == null || arr.length < 1) return 0;
        return maxSubArray(arr,0,arr.length);
    }

    /**
     * 求[start,end)的最大子数组和
     * 分3种情况：1、最大子数组和在左边 2、最大子数组和在右边 3、最大子数组和在中间
     * @param arr
     * @param start
     * @param end
     * @return
     */
    private static int maxSubArray(int[] arr, int start, int end) {
        if(end - start < 2) return arr[start];
        int mid = (start + end) >> 1;
        //求从中间往左的最大子序列和
        int midLeftMax = Integer.MIN_VALUE;
        int midLeftSum = 0;
        for (int i = mid - 1; i >= start; i--){
            midLeftSum += arr[i];
            midLeftMax = Math.max(midLeftMax,midLeftSum);
        }
        //求从中间往右的最大子序列和
        int midRightMax = Integer.MIN_VALUE;
        int midRightSum = 0;
        for (int i = mid; i < end; i++){
            midRightSum += arr[i];
            midRightMax = Math.max(midRightMax,midRightSum);
        }
        int midMax = midLeftMax + midRightMax;
        int leftMax = maxSubArray(arr,start,mid);
        int rightMax = maxSubArray(arr,mid,end);
        return Math.max(Math.max(midMax,leftMax),rightMax);
    }

}
