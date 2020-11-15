package algorithm1.sorting;

/**
 * 计数排序：o(n) 只能对整数进行排序
 */
public class CountingSort extends Sort<Integer> {
    /**
     * 计数排序改进版
     * 稳定，能对负数进行排序，减少空间浪费
     */
    @Override
    protected void sort() {
        //找出最大值和最小值
        int max = array[0];
        int min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        //统计次数
        int[] counts = new int[max - min + 1];
        for (int i = 0; i < array.length; i++) {
            //保证counts的索引从0开始
            counts[array[i] - min]++;
        }
        //累加次数
        for (int i = 1; i < counts.length; i++) {
            counts[i] = counts[i] + counts[i - 1];
        }
        //从后往前遍历array，并放入有序数组中
        int[] newArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            int countIdx = array[i] - min;
            newArray[--counts[countIdx]] = array[i];
        }
        for (int i = 0; i < newArray.length; i++) {
            array[i] = newArray[i];
        }
    }

    /**
     * 计数排序基础版
     * 不稳定，不能对负数进行排序，浪费空间
     */
    private void sort0() {
        //找出最大值
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        //通过最大值创建数组
        int[] countArr = new int[max + 1];
        for (int i = 0; i < array.length; i++) {
            countArr[array[i]] = countArr[array[i]] + 1;
        }
        //遍历输出结果
        int p = 0;
        for (int i = 0; i < countArr.length; i++) {
            int count = countArr[i];
            while (count-- > 0) {
                array[p++] = i;
            }
        }
    }
}
