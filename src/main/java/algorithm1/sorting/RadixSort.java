package algorithm1.sorting;

/**
 * 基数排序
 */
public class RadixSort extends Sort<Integer> {
    @Override
    protected void sort() {
        //取出最大值
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        for (int division = 1; division <= max; division *= 10) {
            countingSort(division);
        }
    }

    private void countingSort(int division) {
        //统计次数
        int[] counts = new int[10];
        for (int i = 0; i < array.length; i++) {
            //保证counts的索引从0开始
            counts[array[i] / division % 10]++;
        }
        //累加次数
        for (int i = 1; i < counts.length; i++) {
            counts[i] = counts[i] + counts[i - 1];
        }
        //从后往前遍历array，并放入有序数组中
        int[] newArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            int countIdx = array[i] / division % 10;
            newArray[--counts[countIdx]] = array[i];
        }
        for (int i = 0; i < newArray.length; i++) {
            array[i] = newArray[i];
        }
    }
}
