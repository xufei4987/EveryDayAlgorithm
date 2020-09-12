package algorithm1.sorting;

public abstract class Sort<E extends Comparable<E>> implements Comparable<Sort> {
    protected E[] array;
    private int cmpCount;
    private int swapCount;
    private long time;

    public void sort(E[] arr) {
        if (arr == null || arr.length < 2) {
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

    protected int compare(int idx1, int idx2) {
        cmpCount++;
        return array[idx1].compareTo(array[idx2]);
    }

    protected int compare(E e1, E e2) {
        cmpCount++;
        return e1.compareTo(e2);
    }

    protected void swap(int idx1, int idx2) {
        swapCount++;
        E tmp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = tmp;
    }

    protected boolean isStable(){
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("【").append(this.getClass().getSimpleName()).append("】\n")
                .append("耗时：").append(time / 1000.0).append("秒").append("\t")
                .append("比较：").append(cmpCount).append("次").append("\t")
                .append("交换：").append(swapCount).append("次").append("\t")
                .append("稳定性：").append(isStable()).append("\n")
                .append("--------------------------");
        return sb.toString();
    }


}
