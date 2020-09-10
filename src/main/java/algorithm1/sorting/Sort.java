package algorithm1.sorting;

import java.util.Arrays;

public abstract class Sort implements Comparable<Sort>{
    protected Integer[] array;
    private int cmpCount;
    private int swapCount;
    private long time;

    public void sort(Integer[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        this.array = arr;
        long start = System.currentTimeMillis();
        sort();
        time = System.currentTimeMillis() - start;
    }

    protected abstract void sort();

    @Override
    public int compareTo(Sort o) {
        return (int) (this.time - o.time);
    }

    protected int compare(int idx1, int idx2){
        cmpCount++;
        return array[idx1] - array[idx2];
    }

    protected void swap(int idx1,int idx2){
        swapCount++;
        array[idx1] = array[idx1] ^ array[idx2];
        array[idx2] = array[idx1] ^ array[idx2];
        array[idx1] = array[idx1] ^ array[idx2];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("【").append(this.getClass().getSimpleName()).append("】\n")
                .append("耗时：").append(time / 1000.0).append("秒").append("\n")
                .append("比较：").append(cmpCount).append("次").append("\n")
                .append("交换：").append(swapCount).append("次").append("\n")
                .append("--------------------------");
        return sb.toString();
    }
}
