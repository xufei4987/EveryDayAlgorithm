package algorithm1.sorting;

/**
 * 归并排序
 *
 * @param <E>
 */
public class MergeSort<E extends Comparable<E>> extends Sort<E> {

    private Comparable<E>[] leftArray;

    @Override
    protected void sort() {
        //用一半的空间做缓存
        this.leftArray = new Comparable[array.length >> 1];
        //范围 [0,array.lenth) 左闭右开
        sort(0, array.length);
    }

    private void sort(int begin, int end) {
        if (end - begin < 2) return;
        int mid = (begin + end) >> 1;
        sort(begin, mid);
        sort(mid, end);
        merge(begin, mid, end);
    }

    private void merge(int begin, int mid, int end) {
        int li = 0; //一半临时数组的开始索引（左边）
        int le = mid - begin;//一半临时数组的结束索引（左边）
        int ri = mid;//右边数组的开始索引
        int re = end;//右边数组的结束索引
        int ai = begin;//原数组索引
        //数组左半部分备份
        for (int i = 0; i < mid - begin; i++) {
            leftArray[i] = array[begin + i];
        }
        //左边数组挪完即完成排序
        while (li < le) {
            if (ri < re && compare(array[ri], (E) leftArray[li]) < 0) {
                array[ai++] = array[ri++];
            } else {
                array[ai++] = (E) leftArray[li++];
            }
        }
    }
}
