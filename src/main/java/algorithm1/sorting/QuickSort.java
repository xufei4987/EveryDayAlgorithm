package algorithm1.sorting;

public class QuickSort<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
        sort(0,array.length);
    }

    @Override
    protected boolean isStable() {
        return false;
    }

    /**
     * [begin, end)
     *
     * @param begin
     * @param end
     */
    private void sort(int begin, int end) {
        if (end - begin < 2) return;
        //计算出轴点
        int pivot = pivotIndex(begin, end);
        //再对子序列进行快速排序
        sort(begin, pivot);
        sort(pivot + 1, end);
    }

    /**
     * 指定begin位置的元素为pivot，比pivot大的放右边，比pivot小的放左边，并返回最终pivot的位置
     *
     * @param begin
     * @param end
     * @return
     */
    private int pivotIndex(int begin, int end) {
        //随机选择pivot，减少最坏情况的出现
        swap(begin, begin + (int)Math.random()*(end - begin));
        E pivotVal = array[begin];
        //end指向最后一个元素
        end--;
        while (begin < end){
            while (begin < end) {
                if (compare(pivotVal, array[end]) < 0) {
                    end--;
                } else {
                    array[begin] = array[end];
                    begin++;
                    break;
                }
            }
            while (begin < end) {
                if (compare(pivotVal, array[begin]) > 0) {
                    begin++;
                } else {
                    array[end] = array[begin];
                    end--;
                    break;
                }
            }
        }
        //此时begin == end
        array[begin] = pivotVal;
        //返回轴点元素的位置
        return begin;
    }
}
