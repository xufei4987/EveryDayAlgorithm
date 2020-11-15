package algorithm.sort;

/**
 * 基数排序
 * 基本思想：创建10个bucket对应0-9, 从最低位到最高位的顺序，分别放入对应的桶内，通过不断的放入和取出达到整体有序的结果。
 * 空间换时间的经典算法
 * 如何处理负数：
 * 将数组中的负数整数分开，使它们成为正数，然后使用基数排序为正值，然后反转并附加排序后的非负数组。
 */
public class RadixSort {
    private static final int BUCKET_COUNT = 10;

    public void sort(int[] arr) {
        //找出最大值
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //获取最大值的位数
        int digitCount = (max + "").length();
        //定义桶和桶的计数器
        int[][] bucket = new int[BUCKET_COUNT][arr.length];
        int[] bucketElementCount = new int[BUCKET_COUNT];
        int tail = 0;
        int idx = 0;
        for (int i = 0, n = 1; i < digitCount; i++, n *= 10) {
            //放入桶中
            for (int j = 0; j < arr.length; j++) {
                tail = arr[j] / n % 10;
                bucket[tail][bucketElementCount[tail]] = arr[j];
                bucketElementCount[tail]++;
            }
            //从桶中取出
            for (int j = 0; j < BUCKET_COUNT; j++) {
                if (bucketElementCount[j] != 0) {
                    for (int k = 0; k < bucketElementCount[j]; k++) {
                        arr[idx++] = bucket[j][k];
                    }
                    //用完后需要清空
                    bucketElementCount[j] = 0;
                }
            }
            idx = 0;
        }

    }

    public static void main(String[] args) {
//        int[] arr = {53, 3, 542, 748, 14, 214};
//        RadixSort radixSort = new RadixSort();
//        radixSort.sort(arr);
//        System.out.println(Arrays.toString(arr));

        RadixSort radixSort = new RadixSort();
        int[] arr = new int[800000];
        for (int i = 0; i < 800000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long start = System.currentTimeMillis();
        System.out.println("开始排序...");
        radixSort.sort(arr);
        long end = System.currentTimeMillis();
        System.out.println("排序消耗的时间是：" + (end - start) + "ms");
    }
}
