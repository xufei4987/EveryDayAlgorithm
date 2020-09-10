package algorithm1.sorting;

/**
 * 选取一个最大的值，放到最后面
 * 优化方案：使用堆进行优化，堆排序
 */
public class SelectionSort extends Sort{

    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            int maxIdx = 0;
            for (int begin = 1; begin <= end; begin++) {
                if (compare(begin,maxIdx) >= 0) {
                    maxIdx = begin;
                }
            }
            swap(maxIdx, end);
        }
    }
}
